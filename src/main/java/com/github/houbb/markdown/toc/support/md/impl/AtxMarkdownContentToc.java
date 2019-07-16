/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.support.md.impl;

import com.github.houbb.markdown.toc.constant.TocConstant;
import com.github.houbb.markdown.toc.constant.VersionConstant;
import com.github.houbb.markdown.toc.support.IncreaseMap;
import com.github.houbb.markdown.toc.support.codeblock.ICodeBlock;
import com.github.houbb.markdown.toc.support.codeblock.impl.AtxCodeBlock;
import com.github.houbb.markdown.toc.support.md.MarkdownContentToc;
import com.github.houbb.markdown.toc.util.CollectionUtil;
import com.github.houbb.markdown.toc.util.StringUtil;
import com.github.houbb.markdown.toc.vo.TocVo;

import com.github.houbb.markdown.toc.vo.config.TocConfig;
import org.apiguardian.api.API;

import java.util.*;

/**
 * <p> ATX 根据内容返回目录 </p>
 *
 * <pre> Created: 2018/8/15 下午2:53  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_4)
public class AtxMarkdownContentToc implements MarkdownContentToc {

    //region 内部属性

    /**
     * 配置信息
     * @since 1.0.5
     */
    private final TocConfig config;

    /**
     * toc str列表
     */
    private List<String> tocStrList = new LinkedList<>();

    /**
     * toc值对象列表
     */
    private List<TocVo> tocVoList = new LinkedList<>();

    /**
     * 结果列表
     */
    private List<String> resultList = new LinkedList<>();

    /**
     * 上一个节点
     */
    private TocVo previous;

    public AtxMarkdownContentToc(TocConfig config) {
        this.config = config;
    }
    //endregion

    @Override
    public List<String> getPureTocList(List<String> contentLines) {
        if (CollectionUtil.isEmpty(contentLines)) {
            return Collections.emptyList();
        }

        return buildTocList(contentLines);
    }

    @Override
    public List<String> getPureContentList(List<String> contentLines) {
        List<String> resultList = new LinkedList<>(contentLines);

        //原先的目录过滤
        String firstLine = resultList.get(0);
        if (firstLine.startsWith(TocConstant.DEFAULT_TOC_HEAD)) {
            Iterator<String> stringIterator = resultList.iterator();
            //开头
            nextAndRemove(stringIterator, 2);
            while (stringIterator.hasNext()) {
                String contentTrim = stringIterator.next().trim();
                if (contentTrim.startsWith(TocConstant.STAR)) {
                    stringIterator.remove();
                } else {
                    //直接跳出循环
                    break;
                }
            }
            //移除当前换行
            if (stringIterator.hasNext()) {
                stringIterator.remove();
            }
            //最后的换行
            nextAndRemove(stringIterator, 1);
        }

        return resultList;
    }

    /**
     * 构建目录列表
     * v1.0.7 修复 bug：当 # 放在代码块中，则不认为是标题信息。
     * @param contentList 内容列表
     * @return 目录列表
     */
    private List<String> buildTocList(final List<String> contentList) {
        if (CollectionUtil.isEmpty(contentList)) {
            return Collections.emptyList();
        }

        //1. ATX 默认文件头
        resultList.add(TocConstant.DEFAULT_TOC_HEAD + TocConstant.RETURN_LINE);

        //2. 所有 toc 行
        ICodeBlock codeBlock = AtxCodeBlock.newInstance();
        for (String string : contentList) {
            // 是否为代码块的处理
            codeBlock.put(string);
            if(codeBlock.isCodeBlock()) {
                continue;
            }

            String trim = string.trim();
            if (trim.startsWith(TocConstant.ASTERISK)) {
                tocStrList.add(trim);
            }
        }

        //3. 构建 tocVo
        //3.1 创建一个自增 map，用来处理重复名称
        IncreaseMap increaseMap = new IncreaseMap();

        TocVo root = TocVo.rootToc(increaseMap);
        root.setParent(null);
        //初始化根节点
        tocVoList.add(root);
        previous = root;
        for (String string : tocStrList) {
            addNewToc(string, increaseMap);
        }

        //4. 展现
        showToc(root.getChildren());
        resultList.add(TocConstant.RETURN_LINE);

        return resultList;
    }


    /**
     * 显示toc
     * v1.0.5 开始支持编号，此处默认从1开始。
     * @param tocVoList toc值对象列表
     */
    private void showToc(List<TocVo> tocVoList) {
        if (tocStrList.isEmpty()
            || CollectionUtil.isEmpty(tocVoList)) {
            return;
        }
        //v1.0.5 添加编号支持
        for (TocVo tocVo : tocVoList) {
            this.fillTocVo(tocVo);
            final String intent = tocVo.getIndent();
            String tocVoContent = intent + String.format(TocConstant.TOC_FORMAT,
                    tocVo.getOrderDesc(), tocVo.getTocTitle(), tocVo.getTocHref());
            resultList.add(tocVoContent);
            showToc(tocVo.getChildren());
        }
    }

    /**
     * 填充信息
     * @param tocVo 原始对象
     */
    private void fillTocVo(TocVo tocVo) {
        final int level = tocVo.getLevel();

        //1. 前缀空格信息处理
        StringBuilder prefixBuilder = new StringBuilder();
        for (int i = 0; i < level - 1; i++) {
            prefixBuilder.append(TocConstant.TWO_BLANK);
        }
        tocVo.setIndent(prefixBuilder.toString());

        //2. 编号处理
        if(config.isOrder()) {
            // 递归获取对应的编号信息
            String orderDesc = buildOrderDesc(tocVo);
            tocVo.setOrderDesc(orderDesc);
        } else {
            tocVo.setOrderDesc(StringUtil.EMPTY);
        }
    }

    /**
     * 递归获取顺序描述
     * 1. 依次获取父类的 order，放在一个列表中。
     * 2. 终止的条件是当父类为 root
     * 3. 列表最后 reverse+用点连接起来。
     * @param tocVo 信息
     * @return 结果
     * @since 1.0.5
     */
    private String buildOrderDesc(final TocVo tocVo) {
        List<Integer> orderList = new ArrayList<>();
        orderList.add(tocVo.getOrder());

        TocVo current = tocVo;
        while(current.getLevel() >= 1) {
            current = current.getParent();
            if(current.getLevel() >= 1) {
                orderList.add(current.getOrder());
            }
        }

        Collections.reverse(orderList);
        return CollectionUtil.join(orderList, TocConstant.DOT)+StringUtil.BLANK;
    }

    /**
     * 下一个并且移除元素
     *
     * @param stringIterator 迭代
     * @param times          次数
     */
    private void nextAndRemove(Iterator<String> stringIterator, int times) {
        for (int i = 0; i < times; i++) {
            if (stringIterator.hasNext()) {
                String content = stringIterator.next();
                stringIterator.remove();
            }
        }
    }

    /**
     * 添加新的toc
     *
     * @param tocTrimStr toc trim str
     */
    private void addNewToc(String tocTrimStr, IncreaseMap increaseMap) {
        TocVo current = new TocVo(tocTrimStr, increaseMap);

        //1 级目录
        if (current.getLevel() == 1) {
            TocVo root = tocVoList.get(0);
            current.setParent(root);
            //添加到根目录
            root.getChildren().add(current);
        } else if (current.getLevel() <= previous.getLevel()) {
            TocVo previousParent = previous.getParent();
            current.setParent(previousParent);
            previousParent.getChildren().add(current);
            //上一节点的子节点
        } else if (current.getLevel() > previous.getLevel()) {
            current.setParent(previous);
            previous.getChildren().add(current);
        }
        // 统一设置 order 信息 1.0.5
        // 编号的下标从1开始
        TocVo parentToc = current.getParent();
        List<TocVo> childrenList = parentToc.getChildren();
        current.setOrder(childrenList.size());

        previous = current;
    }

}
