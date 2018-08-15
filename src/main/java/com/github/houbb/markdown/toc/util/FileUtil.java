package com.github.houbb.markdown.toc.util;


import com.github.houbb.markdown.toc.constant.VersionConstant;
import com.github.houbb.markdown.toc.exception.MarkdownTocRuntimeException;
import com.github.houbb.markdown.toc.support.MarkdownFileWalker;

import org.apiguardian.api.API;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 文件工具类
 *
 * @author bbhou
 * @version 1.1.0
 */
@API(status = API.Status.MAINTAINED, since = VersionConstant.V_1_0_0)
public final class FileUtil {

    private FileUtil() {
    }

    /**
     * 是否为 md 文件
     * @param filePath 文件路径
     * @return {@code true} 是
     */
    public static boolean isMdFile(final String filePath) {
        if(StringUtil.isEmpty(filePath)) {
            return false;
        }
        return filePath.endsWith(".md")
                || filePath.endsWith(".markdown");
    }
    /**
     * md 文件对应的列表
     *
     * @param dirPath    文件夹路径
     * @param subTree  包含子元素
     * @return 对应的列表
     */
    public static List<Path> getMdFilePathList(final Path dirPath,
                                               final boolean subTree) {
        try {

            if(subTree) {
                MarkdownFileWalker markdownFileWalker = new MarkdownFileWalker();
                Files.walkFileTree(dirPath, markdownFileWalker);
                return markdownFileWalker.getPathList();
            }

            File dir = dirPath.toFile();
            File[] files = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return FileUtil.isMdFile(name);
                }
            });
            if(ArrayUtil.isNotEmpty(files)) {
                List<Path> paths = new ArrayList<>();

                for(File file : files) {
                    paths.add(file.toPath());
                }
                return paths;
            }

            return Collections.emptyList();
        } catch (IOException e) {
            throw new MarkdownTocRuntimeException(e);
        }
    }

    /**
     * 获取指定文件的每一行内容
     * [TWR](http://blog.csdn.net/doctor_who2004/article/details/50901195)
     *
     * @param inputStream 输入流
     * @param initLine    初始读取行数
     * @return 错误返回空列表
     * @since 1.7
     */
    public static List<String> getFileContentEachLine(InputStream inputStream, int initLine) {
        List<String> contentList = new LinkedList<>();


        //暂时使用此编码
        String charset = "UTF-8";
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            // 用于记录行号
            int lineNo = 0;
            while (lineNo < initLine) {
                lineNo++;
                String ignore = bufferedReader.readLine();
            }

            //每一行的内容
            String dataEachLine;
            while ((dataEachLine = bufferedReader.readLine()) != null) {
                lineNo++;
                contentList.add(dataEachLine);
            }
        } catch (IOException e) {
            throw new MarkdownTocRuntimeException(e);
        }

        return contentList;
    }

}
