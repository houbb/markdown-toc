package com.github.houbb.markdown.toc;

import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;

public class Main {

    public static void main(String[] args) {
        try {
            if(null == args
                    || args.length == 0) {
                System.out.println("请正确输入 markdown 文件的文件路径");
                return;
            }

            final String filePath = args[0];
            new AtxMarkdownToc().genToc(filePath);
            System.out.println("Markdown toc 生成完成");
        } catch (Exception e) {
            System.err.println("程序运行遇到错误，错误详情如下：");
            e.printStackTrace();
        }
    }

}
