package com.github.houbb.markdown.toc.core.impl;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.markdown.toc.constant.TocConstant;
import com.github.houbb.markdown.toc.core.IMarkdownTocText;
import com.github.houbb.markdown.toc.core.MarkdownTocTextContext;
import com.github.houbb.markdown.toc.support.IncreaseMap;
import com.github.houbb.markdown.toc.support.codeblock.ICodeBlock;
import com.github.houbb.markdown.toc.support.codeblock.impl.AtxCodeBlock;
import com.github.houbb.markdown.toc.vo.TocVo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author binbin.hou
 * @since 1.1.0
 */
public class AtxMarkdownTocText implements IMarkdownTocText {

    /**
     * 配置信息
     * @since 1.0.5
     */
    private final MarkdownTocTextContext context;

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

    public AtxMarkdownTocText(MarkdownTocTextContext context) {
        this.context = context;
    }

    @Override
    public List<String> getTocList() {
        List<String> lines = context.lines();

        if(CollectionUtil.isEmpty(lines)) {
            return resultList;
        }

        //2. 所有 toc 行
        ICodeBlock codeBlock = AtxCodeBlock.newInstance();
        for (String string : lines) {
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
        if(context.order()) {
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
