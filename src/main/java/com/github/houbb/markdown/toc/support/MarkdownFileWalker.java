/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.support;

import com.github.houbb.markdown.toc.constant.VersionConstant;
import com.github.houbb.markdown.toc.util.FileUtil;

import org.apiguardian.api.API;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p> MD 文件遍历器 </p>
 *
 * <pre> Created: 2018/7/5 上午11:10  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_0)
public class MarkdownFileWalker extends SimpleFileVisitor<Path> {

    /**
     * 存放路径列表
     */
    private List<Path> pathList = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (FileUtil.isMdFile(file.toString())) {
            pathList.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * 获取文件路径列表
     *
     * @return 文件路径列表
     */
    public List<Path> getPathList() {
        return Collections.unmodifiableList(pathList);
    }

}
