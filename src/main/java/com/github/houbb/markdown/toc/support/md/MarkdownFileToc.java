/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.support.md;

import com.github.houbb.markdown.toc.constant.VersionConstant;
import com.github.houbb.markdown.toc.vo.TocGen;
import com.github.houbb.markdown.toc.vo.config.TocConfig;

import org.apiguardian.api.API;

/**
 * <p> 单个文件生成接口 </p>
 *
 * <pre> Created: 2018/7/27 下午2:53  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_0)
public interface MarkdownFileToc {

    /**
     * 生成单个文件的 toc
     * @param filePath 文件路径
     * @param config 生成配置
     * @return 生成的结果信息
     */
    TocGen genTocFile(final String filePath, final TocConfig config);

}
