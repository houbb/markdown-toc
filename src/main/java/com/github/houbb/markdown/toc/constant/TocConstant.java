package com.github.houbb.markdown.toc.constant;

/**
 * TOC 常量
 * @author bbhou
 * @version 1.0.0
 * @since 1.0.0, 2018/01/30
 */
public final class TocConstant {

    private TocConstant(){}

    /**
     * 两个空格
     */
    public static final String TWO_BLANK = "  ";

    /**
     * 井号
     */
    public static final String ASTERISK = "#";

    /**
     * 星号
     */
    public static final String STAR = "*";

    /**
     * 减号
     */
    public static final String MINUS = "-";

    /**
     * TOC 格式化
     */
    public static final String TOC_FORMAT = "* [%s](%s)";

    /**
     * 默认的 toc 开头
     */
    public static final String DEFAULT_TOC_HEAD = "# Table of Contents";

    /**
     * 通用换行符
     */
    public static final String RETURN_LINE = System.getProperty("line.separator");

}
