package com.github.houbb.markdown.toc.support.codeblock;

/**
 * 代码块接口
 * @author binbin.hou
 * @since 1.0.7
 */
public interface ICodeBlock {

    /**
     * 是否为代码块
     * @return 是否
     */
    boolean isCodeBlock();

    /**
     * 添加内容
     * @param newLine 新的一行内容
     */
    void put(final String newLine);

}
