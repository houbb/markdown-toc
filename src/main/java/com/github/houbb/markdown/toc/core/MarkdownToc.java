package com.github.houbb.markdown.toc.core;

/**
 * markdown toc
 * @author bbhou
 * @version 1.0.0
 * @since 1.0.0, 2018/01/30
 */
public interface MarkdownToc {

    /**
     * 文件 URL
     * @param url 文件地址
     * @param charset 文件编码
     */
    void genToc(final String url, final String charset);

}
