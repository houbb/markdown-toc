/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.vo.config;

import com.github.houbb.markdown.toc.constant.VersionConstant;

import org.apiguardian.api.API;

import java.nio.charset.Charset;

/**
 * <p> 配置文件 </p>
 *
 * <pre> Created: 2018/7/27 下午2:58  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_0)
public class TocConfig {

    /**
     * 编码(默认为utf-8)
     */
    private final Charset charset;

    /**
     * 是否包含子文件夹下的文件(默认为包含)
     */
    private final boolean subTree;

    /**
     * 是否写入到文件(默认写入)
     */
    private final boolean write;

    public TocConfig(Charset charset, boolean subTree, boolean write) {
        this.charset = charset;
        this.subTree = subTree;
        this.write = write;
    }

    public Charset getCharset() {
        return charset;
    }

    public boolean isSubTree() {
        return subTree;
    }

    public boolean isWrite() {
        return write;
    }
}
