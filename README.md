# markdown-toc

初衷：手写 markdown 时没有目录，内容较多不便于阅读。

作用：为 markdown 文件生成 toc，当前直接写入文件开头。

备注：

对于标题，md 由两种语法 [setext](http://docutils.sourceforge.net/mirror/setext.html) 和 [atx](http://www.aaronsw.com/2002/atx/) 模式。

暂时只支持 **atx** 形式。

## 环境依赖

- JDK 

JDK7+, 请确保 JDK 设置正确。

# 快速入门

## 下载

使用 git 或者 直接下载本项目到本地。约定项目的根路径为 `${BASE_DIR}`

## 脚本方式

到 `${BASE_DIR}/bin` 下，文件如下：

```
markdown-toc.jar
markdownToc.bat
markdownToc.sh
```

### windows

运行 [markdownToc.bat](bin/markdownToc.bat) 脚本文件，根据提示输入对应的 md 文件信息。

- 属性说明

| 属性 | 说明 | 备注 | 
|:---|:---|:---|
| filePath | md 文件的路径 | 必填。|
| charset | md 文件的编码 | 选填。默认值 `UTF-8` |
 
- 实际日志

```
E:\CODE_GEN\markdown-toc\bin>markdownToc.bat
----------------------
Markdown file path is required, such as: E:\CODE_GEN\markdown-toc\README.md
Markdown file charset is optional, default value is UTF-8
----------------------
Markdown file path: E:\CODE_GEN\markdown-toc\README.md
Markdown file charset:

You input path: E:\CODE_GEN\markdown-toc\README.md
You input charset:
开始生成：【文件路径】E:\CODE_GEN\markdown-toc\README.md, 【文件编码】UTF-8
Markdown toc 生成完成
```

- 效果对比

比原来的文件在文件头新增内容:

```

```

### linux

运行 [markdownToc.sh](bin/markdownToc.sh) 脚本文件，根据提示输入对应的 md 文件信息。

其他同上。

## Java 运行方式

- 环境依赖 

需要 mvn 环境

- 参考

直接参考 [Main.java](src/main/java/com/github/houbb/markdown/toc/Main.java) 编写测试代码。

或者参考类: [AtxMarkdownTocTest.java](src/test/java/com/github/houbb/markdown/toc/core/impl/AtxMarkdownTocTest.java) 编写测试代码。

## english title    test
