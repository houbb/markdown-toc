package com.github.houbb.markdown.toc.util;

import org.junit.Assert;
import org.junit.Test;

/**
* StringUtil Tester.
*
* @author author
* @since 2018-01-30 16:36:24.273
* @version 1.0
*/
public class StringUtilTest {

    /**
    *
    * Method: isEmpty(string)
    */
    @Test
    public void isEmptyTest() throws Exception {
        Assert.assertTrue(StringUtil.isEmpty(null));
        Assert.assertTrue(StringUtil.isEmpty(""));
    }


}
