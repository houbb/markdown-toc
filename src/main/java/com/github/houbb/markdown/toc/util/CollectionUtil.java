/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.util;

import org.apiguardian.api.API;

import java.util.Collection;

/**
 * <p> 集合工具类 </p>
 *
 * <pre> Created: 2018/7/5 下午3:04  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since 1.0.3, 2018-07-28 12:49:19
 */
@API(status = API.Status.MAINTAINED)
public final class CollectionUtil {

    private CollectionUtil(){}


    /**
     * 是否为空
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection collection) {
        return null == collection
                || 0 == collection.size();
    }

    /**
     * 是否不为空
     * @param collection 集合
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
}
