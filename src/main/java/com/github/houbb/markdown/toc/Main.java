package com.github.houbb.markdown.toc;

import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;

/**
 * 主要
 * @author bbhou
 * @version 1.0.0
 * @since 1.0.0, 2018/01/30
 */
public class Main {

    /**    
     * 主要    
     * 1. 默认参数 1 为路径
     * 2. 默认参数 2 位文件编码
     * @param args 参数    
     */    
    public static void main(String[] args) {
        try {
            if(null == args
                    || args.length == 0) {
                System.out.println("请正确输入 markdown 文件的文件路径");
                return;
            }

            final String filePath = args[0];
            String charset = getCharset(args);

            System.out.println("开始生成：【文件路径】"+filePath+", 【文件编码】"+charset);
            new AtxMarkdownToc().genToc(filePath, charset);
            System.out.println("Markdown toc 生成完成");
        } catch (Exception e) {
            System.err.println("程序运行遇到错误，错误详情如下：");
            e.printStackTrace();
        }
    }

    /**
     * 获取文件编码
     * @param args 入参
     * @return 对应的文件编码
     */
    private static String getCharset(String[] args) {
        if(args.length <= 1) {
            return "UTF-8";
        }
        String argsOne = args[1];
        if(null == argsOne
                || argsOne.trim().equals("")) {
            return "UTF-8";
        }
        return argsOne;
    }

}
