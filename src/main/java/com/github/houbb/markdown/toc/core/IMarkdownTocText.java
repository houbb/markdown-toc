package com.github.houbb.markdown.toc.core;

import java.util.List;

/**
 * 纯文本的目录生成
 *
 * @author bbhou
 * @since 1.1.0
 */
public interface IMarkdownTocText {

    /**
     * 获取文本对应的目录结构信息
     * @return 生成的结果信息
     * @since 1.1.0
     */
    List<String> getTocList();

}
