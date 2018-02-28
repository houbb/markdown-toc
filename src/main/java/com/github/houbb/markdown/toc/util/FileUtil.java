package com.github.houbb.markdown.toc.util;



import com.github.houbb.markdown.toc.exception.MarkdownTocRuntimeException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 文件工具类
 *
 * @author bbhou
 * @version 1.1.0
 */
public final class FileUtil {

    private FileUtil() {
    }


    /**
     * 获取指定文件的每一行内容
     * [TWR](http://blog.csdn.net/doctor_who2004/article/details/50901195)
     *
     * @param inputStream     输入流
     * @param initLine 初始读取行数
     * @return 错误返回空列表
     * @since 1.7
     */
    public static List<String> getFileContentEachLine(InputStream inputStream, int initLine) {
        List<String> contentList = new LinkedList<>();


        String charset = "UTF-8";  //暂时使用此编码
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            int lineNo = 0;// 用于记录行号
            while (lineNo < initLine) {
                lineNo++;
                String ignore = bufferedReader.readLine();
            }

            String dataEachLine;   //每一行的内容
            while ((dataEachLine = bufferedReader.readLine()) != null) {
                lineNo++;
                //跳过空白行
                if (Objects.equals("", dataEachLine)) {
                    continue;
                }
                contentList.add(dataEachLine);
            }
        } catch (IOException e) {
            throw new MarkdownTocRuntimeException(e);
        }

        return contentList;
    }

}
