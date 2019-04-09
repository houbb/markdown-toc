/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.util;

import com.github.houbb.markdown.toc.constant.VersionConstant;

import org.apiguardian.api.API;

import java.util.Collection;
import java.util.List;

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
@API(status = API.Status.MAINTAINED, since = VersionConstant.V_1_0_0)
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

    /**
     * @since 1.0.5
     * @param objects 对象
     * @param separator 分隔符
     * @return 拼接的结果
     */
    public static String join(final List<?> objects, final String separator) {
        if(CollectionUtil.isEmpty(objects)) {
            return StringUtil.EMPTY;
        }

        //null 使用空格代替  避免NPE，也让根据 _ 分割时不存在问题。
        final String firstStr = objects.get(0).toString();
        StringBuilder stringBuilder = new StringBuilder(firstStr);
        for(int i = 1; i < objects.size(); i++){
            String actualStr = objects.get(i).toString();
            stringBuilder.append(separator).append(actualStr);
        }
        return stringBuilder.toString();
    }

}
