package com.github.houbb.markdown.toc.core.impl;

import com.github.houbb.markdown.toc.core.MarkdownToc;
import com.github.houbb.markdown.toc.util.TestPathUtil;
import com.github.houbb.markdown.toc.vo.TocGen;

import org.junit.Assert;
import org.junit.Test;

/**
 * AtxMarkdownToc 单个文件测试
 *
 * @author author
 * @version 1.0
 * @since 2018-01-30 15:11:47.256
 */
public class AtxMarkdownTocFileTest {

    /**
     * 普通-fluent 写法
     */
    @Test
    public void commonFluentTest() {
        String path = TestPathUtil.getAppRootPath("common.md");
        TocGen tocGen = AtxMarkdownToc.newInstance()
                .genTocFile(path);

        Assert.assertEquals(8, tocGen.getTocLines().size());
    }

    /**
     * 普通-新建接口的方式
     */
    @Test
    public void commonInterfaceTest() {
        String path = TestPathUtil.getAppRootPath("common.md");

        MarkdownToc markdownToc = new AtxMarkdownToc();
        TocGen tocGen = markdownToc.genTocFile(path);

        Assert.assertEquals(8, tocGen.getTocLines().size());
    }

    /**
     * 中文名称测试
     */
    @Test
    public void chineseFileNameTest() {
        String path = TestPathUtil.getAppRootPath("中文名称.md");
        TocGen tocGen = AtxMarkdownToc.newInstance()
                .genTocFile(path);

        Assert.assertEquals(10, tocGen.getTocLines().size());
    }

    /**
     * 特殊文件编码测试
     */
    @Test
    public void charsetGbkTest() {
        String path = TestPathUtil.getAppRootPath("README-GBK.md");
        TocGen tocGen = AtxMarkdownToc.newInstance()
                .charset("GBK")
                .genTocFile(path);

        Assert.assertEquals(13, tocGen.getTocLines().size());
    }

    /**
     * 不写入文件测试
     */
    @Test
    public void notWriteTest() {
        String path = TestPathUtil.getAppRootPath("common.md");

        TocGen tocGen = AtxMarkdownToc.newInstance()
                .write(false)
                .genTocFile(path);

        Assert.assertEquals(8, tocGen.getTocLines().size());
    }

    /**
     * 编号测试
     */
    @Test
    public void orderTest() {
        String path = TestPathUtil.getAppRootPath("common.md");

        TocGen tocGen = AtxMarkdownToc.newInstance()
                .order(true)
                .genTocFile(path);

        Assert.assertEquals(8, tocGen.getTocLines().size());
    }

    /**
     * 多层目录测试
     */
    @Test
    public void nLevelTest() {
        String path = TestPathUtil.getAppRootPath("N-LEVEL.md");

        TocGen tocGen = AtxMarkdownToc.newInstance()
                .genTocFile(path);

        Assert.assertEquals(21, tocGen.getTocLines().size());
    }

    /**
     * readme测试
     */
    @Test
    public void readMeTest() {
        String path = TestPathUtil.getAppRootPath("readme/README.md");

        TocGen tocGen = AtxMarkdownToc.newInstance()
                .order(true)
                .genTocFile(path);

        Assert.assertEquals(17, tocGen.getTocLines().size());
    }

    /**
     * 代码块测试
     * @since 1.0.7
     */
    @Test
    public void codeBlockTest() {
        String path = TestPathUtil.getAppRootPath("CODE_BLOCK.md");

        TocGen tocGen = AtxMarkdownToc.newInstance()
                .order(true)
                .genTocFile(path);

        Assert.assertEquals(15, tocGen.getTocLines().size());
    }

    /**
     * 演示配置测试
     */
    @Test
    public void justConfigTest() {
        AtxMarkdownToc.newInstance()
                .charset("UTF-8")
                .write(true)
                .subTree(true)
                .order(true);
    }

}
