package com.github.houbb.markdown.toc.util;

/**
 * String 工具类
 */
public final class StringUtil {

    /**
     * 对于首字母如果是
     * @param tocTitle toc 标题
     * @return 对应的连接
     */
    public static String getTocHref(final String tocTitle) {
        if(isEmpty(tocTitle)) {
            return tocTitle;
        }

        if (tocTitle.length() == 1) {
            return tocTitle.toLowerCase();
        }
        return tocTitle.substring(0, 1).toLowerCase() + tocTitle.substring(1);
    }

    /**
     * 是否为空
     * @param string 字符串
     * @return {@code true} 是
     */
    public static boolean isEmpty(final String string) {
        return null == string
                || string.equals("");
    }

}
