/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.support.md;

import com.github.houbb.markdown.toc.constant.VersionConstant;

import org.apiguardian.api.API;

import java.util.List;

/**
 * <p> 单个文件生成接口 </p>
 *
 * <pre> Created: 2018/7/27 下午2:53  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_4)
public interface MarkdownContentToc {

    /**
     * 获取纯净的 toc 列表
     * @param contentLines 文件内容
     * @return 生成的结果信息
     * @since 1.0.5 调整接口名称
     */
    List<String> getPureTocList(final List<String> contentLines);

    /**
     * 获取纯净的内容列表。
     * 1. 排除掉 toc 信息
     * @param contentLines 原始内容
     * @return 过滤掉 toc 后的内容列表
     * @since 1.0.5 调整接口名称
     */
    List<String> getPureContentList(final List<String> contentLines);

}
