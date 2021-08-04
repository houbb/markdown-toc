package com.github.houbb.markdown.toc.util;

import com.github.houbb.heaven.util.util.CollectionUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本的测试
 *
 * @since 1.1.0
 */
public class MdTocTextHelperTest {

    @Test
    public void helloTest() {
        List<String> lines = new ArrayList<>();
        lines.add("# 标题1");
        lines.add("这是一行内容");
        lines.add("# 标题2");
        lines.add("这也是一行内容");

        List<String> tocList = MdTocTextHelper.getTocList(lines);
        CollectionUtil.foreachPrint(tocList);
    }

    @Test
    public void orderNumTest() {
        List<String> lines = new ArrayList<>();
        lines.add("# 标题1");
        lines.add("这是一行内容");
        lines.add("# 标题2");
        lines.add("这也是一行内容");

        List<String> tocList = MdTocTextHelper.getTocList(lines, true);
        CollectionUtil.foreachPrint(tocList);
    }

}
