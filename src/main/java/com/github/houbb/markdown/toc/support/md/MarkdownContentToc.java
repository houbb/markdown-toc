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
     * 生成单个文件的 toc
     * @param contentLines 文件内容
     * @param trimToc 是否过滤 toc
     * @return 生成的结果信息
     */
    List<String> getTocLines(final List<String> contentLines, boolean trimToc);

    /**
     * 获取过滤掉 toc 的内容
     * @param contentLines 原始内容
     * @return 过滤掉 toc 后的内容列表
     */
    List<String> trimToc(final List<String> contentLines);

}
