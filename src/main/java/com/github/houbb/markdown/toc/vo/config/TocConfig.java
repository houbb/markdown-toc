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
 * @since 1.0.5 进行调整
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_0)
public class TocConfig {

    /**
     * 编码(默认为utf-8)
     */
    private Charset charset;

    /**
     * 是否包含子文件夹下的文件(默认为包含)
     */
    private boolean subTree;

    /**
     * 是否写入到文件(默认写入)
     */
    private boolean write;

    /**
     * 是否添加编号
     * ps: 此处不再使用 immutable 模式，直接 getter/setter 处理
     * @since 1.0.5
     */
    private boolean order;

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public boolean isSubTree() {
        return subTree;
    }

    public void setSubTree(boolean subTree) {
        this.subTree = subTree;
    }

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }
}
