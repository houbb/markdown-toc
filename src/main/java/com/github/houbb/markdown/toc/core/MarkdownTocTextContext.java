package com.github.houbb.markdown.toc.core;

import java.util.List;

/**
 * 纯文本的目录生成
 *
 * @author bbhou
 * @since 1.1.0
 */
public class MarkdownTocTextContext {

    /**
     * markdown 纯文本
     * @since 1.1.0
     */
    private List<String> lines;

    /**
     * 是否指定编号
     * @since 1.1.0
     */
    private boolean order;

    public List<String> lines() {
        return lines;
    }

    public MarkdownTocTextContext lines(List<String> lines) {
        this.lines = lines;
        return this;
    }

    public boolean order() {
        return order;
    }

    public MarkdownTocTextContext order(boolean order) {
        this.order = order;
        return this;
    }

    @Override
    public String toString() {
        return "MarkdownTocTextContext{" +
                "lines=" + lines +
                ", order=" + order +
                '}';
    }

}
