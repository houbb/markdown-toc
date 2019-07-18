package com.github.houbb.markdown.toc.constant;

import org.apiguardian.api.API;

/**
 * TOC 常量
 * @author bbhou
 * @version 1.0.0
 * @since 1.0.0, 2018/01/30
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_0)
public final class TocConstant {

    private TocConstant(){}

    /**
     * 两个空格
     */
    public static final String TWO_BLANK = "  ";

    /**
     * 四个空格
     * @since 1.0.7
     */
    public static final String FOUR_BLANK = "    ";

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
     * 减号字符
     * @since 1.0.8
     */
    public static final char MINUS_CHAR = '-';

    /**
     * 下划线
     * @since 1.0.8
     */
    public static final String UNDERLINE = "_";

    /**
     * TOC 格式化
     */
    public static final String TOC_FORMAT = "* %s[%s](%s)";

    /**
     * 默认的 toc 开头
     */
    public static final String DEFAULT_TOC_HEAD = "# Table of Contents";

    /**
     * 通用换行符
     */
    public static final String RETURN_LINE = System.getProperty("line.separator");

    /**
     * 点
     */
    public static final String DOT = ".";

    /**
     * markdown 代码块开始
     *
     * 示例：
     * ```
     * 代码块
     * ```
     * or
     *
     * ```c
     * 代码块
     * ```
     * @since 1.0.7
     */
    public static final String MD_CODE_BLOCK = "```";

}
