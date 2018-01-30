package com.github.houbb.markdown.toc.core.impl;

import com.github.houbb.markdown.toc.constant.TocConstant;
import com.github.houbb.markdown.toc.core.MarkdownToc;
import com.github.houbb.markdown.toc.exception.MarkdownTocRuntimeException;
import com.github.houbb.markdown.toc.vo.TocVo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * # 的标题形式
 * @author bbhou
 * @version 1.0.0
 * @since 1.0.0, 2018/01/30
 */
public class AtxMarkdownToc implements MarkdownToc {

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

    /**    
     * 生成toc    
     *    
     * @param url 网址    
     */    
    public void genToc(String url, final String charsetStr) {
        try {
            //1. 属性初始化
            Path path = Paths.get(url);
            Charset charset = Charset.forName(charsetStr);

            //2. 校验文件后缀

            //3. 文件内容
            fileContentList = Files.readAllLines(path, charset);

            //4. toc 相关属性
            init();

            //5. 回写
            resultList.addAll(fileContentList);
            Files.write(path, resultList, charset);
        } catch (IOException e) {
            throw new MarkdownTocRuntimeException(e);
        }
    }


    /**    
     * 初始化    
     */    
    private void init() {
        //1. ATX 默认文件头
        resultList.add("# Table of Contents\n");

        //2. 所有 toc 行
        for (String string : fileContentList) {
            String trim = string.trim();
            if (trim.startsWith("#")) {
                tocStrList.add(trim);
            }
        }

        //3. 构建 tocVo
        TocVo root = TocVo.rootToc();
        root.setParent(null);
        tocVoList.add(root); //初始化根节点
        previous = root;
        for(String string : tocStrList) {
            addNewToc(string);
        }

        //4. 展现
        showToc(root.getChildren());
        resultList.add("\n");
    }

    /**    
     * 显示toc    
     *    
     * @param tocVoList toc值对象列表    
     */    
    private void showToc(List<TocVo> tocVoList) {
        if(tocStrList.isEmpty()) {
            return;
        }
        for(TocVo tocVo : tocVoList) {
            String suffix = getSuffix(tocVo.getLevel());
            String tocVoContent = suffix+String.format(TocConstant.TOC_FORMAT,
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
        for(int i = 0; i < level-1; i++) {
            result.append(TocConstant.TWO_BLANK);
        }
        return result.toString();
    }


    /**    
     * 添加新的toc    
     *    
     * @param tocTrimStr toc trim str    
     */    
    private void addNewToc(String tocTrimStr) {
        TocVo current = new TocVo(tocTrimStr);

        if(current.getLevel() == 1) {       //1 级目录
            TocVo root = tocVoList.get(0);
            current.setParent(root);
            root.getChildren().add(current);    //添加到根目录
        } else if(current.getLevel() <= previous.getLevel()) {
            TocVo previousParent = previous.getParent();
            current.setParent(previousParent);
            previousParent.getChildren().add(current);
        } else if(current.getLevel() > previous.getLevel()){    //上一节点的子节点
            current.setParent(previous);
            previous.getChildren().add(current);
        }

        previous = current;
    }

}
