/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.util;

import com.github.houbb.markdown.toc.constant.VersionConstant;
import com.github.houbb.markdown.toc.support.md.MarkdownFileToc;
import com.github.houbb.markdown.toc.support.md.impl.AtxMarkdownFileToc;
import com.github.houbb.markdown.toc.vo.TocGen;
import com.github.houbb.markdown.toc.vo.config.TocConfig;

import org.apiguardian.api.API;

/**
 * <p> threadlocal 工具类 </p>
 *
 * <pre> Created: 2018/7/27 下午6:49  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_0)
public class ThreadLocalUtil {

    /**
     * @since 1.0.8
     */
    private ThreadLocalUtil(){}

    /**
     * 用于保存当前线程的信息
     */
    private static final ThreadLocal<MarkdownFileToc> FILE_TOC_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 生成文件
     * @param filePath 文件路径
     * @param tocConfig 配置信息
     * @return 目录结构
     */
    public static TocGen genTocFile(final String filePath, TocConfig tocConfig) {
        MarkdownFileToc fileToc = getMarkdownFileToc();
        return fileToc.genTocFile(filePath, tocConfig);
    }

    /**
     * 清空
     * 1. 建议在每个线程执行结束，调用
     */
    public static void clear() {
        FILE_TOC_THREAD_LOCAL.remove();
    }

    /**
     * 使用 threadLocal 获取分词器对象
     * @return 当前线程的分词器
     */
    private static MarkdownFileToc getMarkdownFileToc() {
        MarkdownFileToc fileToc = FILE_TOC_THREAD_LOCAL.get();
        if(null == fileToc) {
            fileToc = new AtxMarkdownFileToc();
            FILE_TOC_THREAD_LOCAL.set(fileToc);
        }
        return fileToc;
    }

}
