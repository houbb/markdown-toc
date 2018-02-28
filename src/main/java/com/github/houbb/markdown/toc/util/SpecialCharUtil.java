package com.github.houbb.markdown.toc.util;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 特殊字符工具类
 * @author bbhou
 * @since 1.0.1
 * @version 1.0.1
 */
public class SpecialCharUtil {

    /**
     * 获取特殊字符的集合
     */
    public static Set<String> getSpecialCharSet() {
        Set<String> stringSet = new HashSet<>();
        InputStream inputStream = SpecialCharUtil.class.getResourceAsStream("/special_char.txt");
        List<String> stringList = FileUtil.getFileContentEachLine(inputStream, 0);
        for(String string: stringList) {
            String[] strings = StringUtil.splitByAnyBlank(string);
            stringSet.add(strings[0].trim());
        }
        return stringSet;
    }

}
