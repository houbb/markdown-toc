package com.github.houbb.markdown.toc.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class SpecialCharUtilTest {

    @Test
    public void getSpecialCharSetTest() {
        Set<String> stringSet = SpecialCharUtil.getSpecialCharSet();
        Assert.assertFalse(stringSet.isEmpty());
    }

    @Test
    public void buildSpecialTitle() {
        Set<String> stringSet = SpecialCharUtil.getSpecialCharSet();
        final String format = "# A%sB\n" +
                "A%sB\n";
        for(String string : stringSet) {
            System.out.println(String.format(format, string, string));
        }
    }
}
