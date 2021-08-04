package com.github.houbb.markdown.toc.util;

import com.github.houbb.markdown.toc.core.IMarkdownTocText;
import com.github.houbb.markdown.toc.core.MarkdownTocTextContext;
import com.github.houbb.markdown.toc.core.impl.AtxMarkdownTocText;

import java.util.List;

/**
 * 文本帮助类
 * @author binbin.hou
 * @since 1.1.0
 */
public final class MdTocTextHelper {

    /**
     * 获取头信息
     * @param lines 行
     * @return 结果
     * @since 1.1.0
     */
    public static List<String> getTocList(final List<String> lines) {
        return getTocList(lines, false);
    }

    /**
     * 获取目录信息
     * @param lines 行
     * @param orderNum 编号
     * @return 结果
     * @since 1.1.0
     */
    public static List<String> getTocList(final List<String> lines, boolean orderNum) {
        MarkdownTocTextContext context = new MarkdownTocTextContext();
        context.lines(lines).order(orderNum);

        IMarkdownTocText tocText = new AtxMarkdownTocText(context);

        return tocText.getTocList();
    }

}
