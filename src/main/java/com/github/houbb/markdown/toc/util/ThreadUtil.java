/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.util;

import com.github.houbb.markdown.toc.constant.VersionConstant;

import org.apiguardian.api.API;

/**
 * 线程 工具类
 * @author bbhou
 * @since 1.0.3, 2018-07-25 11:12:08
 * @version 1.0.3
 */
@API(status = API.Status.MAINTAINED, since = VersionConstant.V_1_0_3)
public final class ThreadUtil {

    /**
     * @since 1.0.8
     */
    private ThreadUtil(){}

    /**
     * 获取 cpu 数量
     * @return cpu 数量
     */
    private static int cpuNum() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 最佳线程数量
     * @return 线程数量
     */
    public static int bestThreadNum() {
        int cpuNum = cpuNum();
        return cpuNum * 3;
    }

    /**
     * 最佳线程数量
     * 1. 如果目标值较小，则返回较少的即可。
     * @param targetSize 目标大小
     * @return 线程数量
     */
    public static int bestThreadNum(final int targetSize) {
        int bestNum = bestThreadNum();
        if(targetSize < bestNum) {
            return targetSize;
        }
        return bestNum;
    }

}
