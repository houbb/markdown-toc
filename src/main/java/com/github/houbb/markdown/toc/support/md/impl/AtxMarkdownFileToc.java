/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.support.md.impl;

import com.github.houbb.markdown.toc.constant.TocConstant;
import com.github.houbb.markdown.toc.exception.MarkdownTocRuntimeException;
import com.github.houbb.markdown.toc.support.I18N;
import com.github.houbb.markdown.toc.support.IncreaseMap;
import com.github.houbb.markdown.toc.support.md.MarkdownFileToc;
import com.github.houbb.markdown.toc.util.FileUtil;
import com.github.houbb.markdown.toc.vo.TocGen;
import com.github.houbb.markdown.toc.vo.TocVo;
import com.github.houbb.markdown.toc.vo.config.TocConfig;

import org.apiguardian.api.API;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <p> ATX 单个文件生成 </p>
 *
 * <pre> Created: 2018/7/27 下午2:53  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 */
@API(status = API.Status.INTERNAL)
public class AtxMarkdownFileToc implements MarkdownFileToc {

    //region 内部属性
    /**
     * 文件内容列表
     */
    private List<String> fileContentList = new LinkedList<>();

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
    //endregion

    @Override
    public TocGen genTocFile(String filePath, TocConfig config) {
        Path path = Paths.get(filePath);
        return genTocForFile(path, config);
    }

    /**
     * 为单个文件生成 toc
     *
     * 1. 这里锁需要进行优化
     * 2. 粒度可以更加细致
     * @param path       文件路径
     * @param config 配置
     */
    private TocGen genTocForFile(final Path path, TocConfig config) {
        try {
            //2. 校验文件后缀
            if(!FileUtil.isMdFile(path.toString())) {
                throw new MarkdownTocRuntimeException(I18N.get(I18N.Key.onlySupportMdFile));
            }

            //3. 文件内容
            initFileContentList(path, config.getCharset());

            //4. toc 相关属性
            initToc();

            //5. 回写
            TocGen tocGen = new TocGen(path.toString(), resultList);
            resultList.addAll(fileContentList);
            if(config.isWrite()) {
                Files.write(path, resultList, config.getCharset());
            }
            return tocGen;
        } catch (IOException e) {
            throw new MarkdownTocRuntimeException(e);
        }
    }

    /**
     * 初始化文件内容
     * 1. 如果文件已经包含了 toc 目录，则进行删除。
     *
     * @param path    文件路径
     * @param charset 文件编码
     * @throws IOException if any
     */
    private void initFileContentList(Path path, Charset charset) throws IOException {
        fileContentList.clear();
        fileContentList = Files.readAllLines(path, charset);

        //原先的目录过滤
        String firstLine = fileContentList.get(0);
        if (firstLine.startsWith(TocConstant.DEFAULT_TOC_HEAD)) {
            Iterator<String> stringIterator = fileContentList.iterator();
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
            stringIterator.remove();
            //最后的换行
            nextAndRemove(stringIterator, 1);
        }
    }

    /**
     * 下一个并且移除元素
     *
     * @param stringIterator 迭代
     * @param times          次数
     */
    private void nextAndRemove(Iterator<String> stringIterator, int times) {
        for (int i = 0; i < times; i++) {
            String content = stringIterator.next();
            stringIterator.remove();
        }
    }

    /**
     * 初始化 toc 内容
     */
    private void initToc() {
        //0. 清空原始的数据信息
        resultList.clear();
        tocStrList.clear();
        tocVoList.clear();

        //1. ATX 默认文件头
        resultList.add(TocConstant.DEFAULT_TOC_HEAD + TocConstant.RETURN_LINE);

        //2. 所有 toc 行
        for (String string : fileContentList) {
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
    }

    /**
     * 显示toc
     *
     * @param tocVoList toc值对象列表
     */
    private void showToc(List<TocVo> tocVoList) {
        if (tocStrList.isEmpty()) {
            return;
        }
        for (TocVo tocVo : tocVoList) {
            String suffix = getSuffix(tocVo.getLevel());
            String tocVoContent = suffix + String.format(TocConstant.TOC_FORMAT,
                    tocVo.getTocTitle(), tocVo.getTocHref());
            resultList.add(tocVoContent);
            showToc(tocVo.getChildren());
        }
    }

    /**
     * 得到后缀
     *
     * @param level 水平
     * @return java.lang.String
     */
    private String getSuffix(int level) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < level - 1; i++) {
            result.append(TocConstant.TWO_BLANK);
        }
        return result.toString();
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

        previous = current;
    }

}
