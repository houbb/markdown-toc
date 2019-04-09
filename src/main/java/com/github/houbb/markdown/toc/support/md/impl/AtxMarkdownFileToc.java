/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.support.md.impl;

import com.github.houbb.markdown.toc.constant.VersionConstant;
import com.github.houbb.markdown.toc.exception.MarkdownTocRuntimeException;
import com.github.houbb.markdown.toc.support.I18N;
import com.github.houbb.markdown.toc.support.md.MarkdownContentToc;
import com.github.houbb.markdown.toc.support.md.MarkdownFileToc;
import com.github.houbb.markdown.toc.util.FileUtil;
import com.github.houbb.markdown.toc.vo.TocGen;
import com.github.houbb.markdown.toc.vo.config.TocConfig;

import org.apiguardian.api.API;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> ATX 单个文件生成 </p>
 *
 * <pre> Created: 2018/7/27 下午2:53  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_0)
public class AtxMarkdownFileToc implements MarkdownFileToc {

    @Override
    public TocGen genTocFile(String filePath, TocConfig config) {
        Path path = Paths.get(filePath);
        return genTocForFile(path, config);
    }

    /**
     * 为单个文件生成 toc
     *
     * 1. 这里锁需要进行优化
     * 2. 粒度可以更加细致
     * @param path       文件路径
     * @param config 配置
     */
    private TocGen genTocForFile(final Path path, TocConfig config) {
        try {
            MarkdownContentToc markdownContentToc = new AtxMarkdownContentToc(config);

            //1. 校验文件后缀
            if(!FileUtil.isMdFile(path.toString())) {
                throw new MarkdownTocRuntimeException(I18N.get(I18N.Key.onlySupportMdFile));
            }

            //2. 获取 toc 列表
            List<String> contentList = Files.readAllLines(path, config.getCharset());
            List<String> trimTocContentList = markdownContentToc.getPureContentList(contentList);
            List<String> tocList = markdownContentToc.getPureTocList(trimTocContentList);

            //3. 回写
            TocGen tocGen = new TocGen(path.toString(), tocList);
            if(config.isWrite()) {
                // 构建新的回写内容
                List<String> resultList = new ArrayList<>(tocList);
                resultList.addAll(trimTocContentList);

                Files.write(path, resultList, config.getCharset());
            }
            return tocGen;
        } catch (IOException e) {
            throw new MarkdownTocRuntimeException(e);
        }
    }

}
