/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.util;

import com.github.houbb.markdown.toc.constant.VersionConstant;

import org.apiguardian.api.API;

/**
 * <p> 数组工具类 </p>
 *
 * <pre> Created: 2018/7/5 下午3:04  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 */
@API(status = API.Status.MAINTAINED, since = VersionConstant.V_1_0_0)
public final class ArrayUtil {

    private ArrayUtil(){}


    /**
     * 是否为空
     * @param objects 数组
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] objects) {
        return null == objects
                || 0 == objects.length;
    }

    /**
     * 是否不为空
     * @param objects 数组
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }
}
