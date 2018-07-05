/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.util;

import org.junit.Test;

import java.io.File;

/**
 * <p> 测试路径工具类 </p>
 *
 * <pre> Created: 2018/7/5 下午2:22  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class TestPathUtil {


    /**
     * 获取项目根路径+测试资源文件路径+XXX
     * @param relativeTest 相对路径
     * @return 绝对路径
     */
    public static String getAppRootPath(final String relativeTest) {
        File emptyFile = new File("");
        String root = emptyFile.getAbsolutePath();
        return root+"/src/test/resources/"+relativeTest;
    }

    @Test
    public void getAppRootPathTest() {
        System.out.println(getAppRootPath("common.md"));
    }

}
