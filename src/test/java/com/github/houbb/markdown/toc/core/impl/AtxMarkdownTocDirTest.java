/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.core.impl;

import com.github.houbb.markdown.toc.util.TestPathUtil;
import com.github.houbb.markdown.toc.vo.TocGen;

import org.junit.Test;

import java.util.List;

/**
 * AtxMarkdownToc 文件夹测试
 *
 * @author author
 * @version 1.0
 * @since 2018-01-30 15:11:47.256
 */
public class AtxMarkdownTocDirTest {

    /**
     * 不包含子元素-测试
     */
    @Test
    public void notSubTreeTest() {
        String path = TestPathUtil.getAppRootPath("sub");
        List<TocGen> tocGens = AtxMarkdownToc.newInstance()
                .subTree(false)
                .genTocDir(path);

        System.out.println(tocGens.size());
    }

    /**
     * 普通-测试
     * 1. 默认进行文件的递归遍历
     */
    @Test
    public void commonTest() {
        String path = TestPathUtil.getAppRootPath("sub");
        List<TocGen> tocGens = AtxMarkdownToc.newInstance()
                .genTocDir(path);
        System.out.println(tocGens.size());
    }

}
