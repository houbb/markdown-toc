/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.support;

import com.github.houbb.markdown.toc.constant.VersionConstant;

import org.apiguardian.api.API;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p> i18n 编码支持 </p>
 *
 * <pre> Created: 2018/7/28 下午4:53  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 * @version 1.0.3
 * @since 1.0.3, 2018-07-28 16:53:13
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_3)
public final class I18N {

    /**
     * 默认的配置文件
     */
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "i18n.MarkdownToc";

    /**
     * 获取属性的值
     * @param key 键值
     * @return 属性 I18n
     */
    public static String get(final String key) {
        Locale currentLocale = Locale.getDefault();
        ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_PROPERTIES_FILE_NAME, currentLocale);
        return myResources.getString(key);
    }

    /**
     * 固定的键值标识
     */
    public static class Key {

        /**
         * 文件路径必须为目录
         */
        public static final String pathIsNotDirectory = "pathIsNotDirectory";

        /**
         * 文件路径不可为空
         */
        public static final String pathIsNotAllowEmpty = "pathIsNotAllowEmpty";

        /**
         * 只支持 markdown 文件
         */
        public static final String onlySupportMdFile = "onlySupportMdFile";
    }

}
