/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.core.impl;

import com.github.houbb.markdown.toc.util.TestPathUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * AtxMarkdownToc 国家化编码测试
 *
 * @author author
 * @version 1.0
 * @since 2018-01-30 15:11:47.256
 */
public class AtxMarkdownI18NTest {

    @Test
    public void pathMustDirEnglish() {
        try {
            Locale.setDefault(new Locale("en", "US"));
            String path = TestPathUtil.getAppRootPath("subNotExists");
            AtxMarkdownToc.newInstance()
                    .genTocDir(path);
        } catch (Exception e) {
            final String expectedMsg = "D:\\github\\markdown-toc/src/test/resources/subNotExists path is not a directory";
            final String msg = e.getMessage();
            Assert.assertEquals(expectedMsg, msg);
        }
    }

    @Test
    public void pathMustDirChinese() {
        try {
            Locale.setDefault(new Locale("zh", "CN"));
            String path = TestPathUtil.getAppRootPath("subNotExists");
            AtxMarkdownToc.newInstance()
                    .genTocDir(path);
        } catch (Exception e) {
            final String expectedMsg = "D:\\github\\markdown-toc/src/test/resources/subNotExists 文件路径必须是文件夹";
            final String msg = e.getMessage();
            Assert.assertEquals(expectedMsg, msg);
        }
    }

}
