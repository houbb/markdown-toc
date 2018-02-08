package com.github.houbb.markdown.toc.core.impl;

import org.junit.Test;

/**
* AtxMarkdownToc Tester.
*
* @author author
* @since 2018-01-30 15:11:47.256
* @version 1.0
*/
public class AtxMarkdownTocTest {

    /**
    *
    * Method: genToc(url, charsetStr)
    */
    @Test
    public void genTocTest() throws Exception {
        final String filePath = "D:\\CODE\\_OTHER\\markdown-toc\\README.md";
        final String charsetStr = "UTF-8";

        //测试时请将下面这段话取消注释(Please cancel comment when you test.)
        new AtxMarkdownToc().genToc(filePath, charsetStr);
    }

}
