/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.core.impl;

import com.github.houbb.markdown.toc.util.TestPathUtil;
import com.github.houbb.markdown.toc.vo.TocGen;

import org.junit.Assert;
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

        final int size = tocGens.size();
        Assert.assertEquals(2, size);
    }

    /**
     * 无 md 文件测试
     */
    @Test
    public void noMdTest() {
        String path = TestPathUtil.getAppRootPath("sub0");
        List<TocGen> tocGens = AtxMarkdownToc.newInstance()
                .subTree(false)
                .write(false)
                .genTocDir(path);

        final int size = tocGens.size();
        Assert.assertEquals(0, size);
    }

    /**
     * 单 md 文件测试
     */
    @Test
    public void oneMdTest() {
        String path = TestPathUtil.getAppRootPath("sub1");
        List<TocGen> tocGens = AtxMarkdownToc.newInstance()
                .subTree(false)
                .write(false)
                .genTocDir(path);

        final int size = tocGens.size();
        Assert.assertEquals(1, size);
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
        final int size = tocGens.size();
        Assert.assertEquals(6, size);
    }

    /**
     * 特殊符号测试
     */
    @Test
    public void specialTest() {
        String path = TestPathUtil.getAppRootPath("special");
        List<TocGen> tocGens = AtxMarkdownToc.newInstance()
                .subTree(true)
                .genTocDir(path);
    }

}
