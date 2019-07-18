package com.github.houbb.markdown.toc.util;

import com.github.houbb.markdown.toc.constant.VersionConstant;

import org.apiguardian.api.API;

/**
 * String 工具类
 * @author bbhou
 * @since 1.0.0
 * @version 1.0.0
 */
@API(status = API.Status.MAINTAINED, since = VersionConstant.V_1_0_0)
public final class StringUtil {

    private StringUtil(){}

    /**
     * 空白字符串
     */
    public static final String EMPTY = "";
    /**
     * 空格
     * @since 1.0.5
     */
    public static final String BLANK = " ";

    /**
     * 是否为空
     * @param string 字符串
     * @return {@code true} 是
     */
    public static boolean isEmpty(final String string) {
        return null == string
                || string.equals(EMPTY);
    }

    /**
     * 根据任意多的空格进行分割字符串。
     * 1. 入参为空,则返回空字符串数组
     *
     * @param string 字符串
     * @return 割字符串数组
     */
    public static String[] splitByAnyBlank(final String string) {
        if (StringUtil.isEmpty(string)) {
            return new String[0];
        }

        final String pattern = "\\s+";
        return string.split(pattern);
    }

}
