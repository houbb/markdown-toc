# Table of Contents

* [markdown-toc](#markdown-toc)
  * [环境依赖](#环境依赖)
  * [Features](#features)
  * [变更日志](#变更日志)
* [快速入门](#快速入门)
  * [下载](#下载)
  * [脚本方式](#脚本方式)
    * [windows](#windows)
* [Table of Contents](#table-of-contents)
    * [linux](#linux)
  * [Java 运行方式](#java-运行方式)

# markdown-toc

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/markdown-toc/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/markdown-toc)
[![Build Status](https://www.travis-ci.org/houbb/markdown-toc.svg?branch=master)](https://www.travis-ci.org/houbb/markdown-toc?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/markdown-toc/badge.svg?branch=master)](https://coveralls.io/github/houbb/markdown-toc?branch=master)

Markdown-toc 可以用来生成 markdown 页面的目录，便于 github 页面展现。

- 初衷

手写 github markdown 时没有目录，内容较多不便于阅读。

- 作用

为 markdown 文件生成 toc，直接写入文件开头。

- 备注

对于标题，md 有两种语法 [setext](http://docutils.sourceforge.net/mirror/setext.html) 
和 [atx](http://www.aaronsw.com/2002/atx/) 模式。

暂时只支持 **atx** 形式。

## 环境依赖

### JDK 

JDK7+, 请确保 JDK 设置正确。

### Maven

Jar 使用 [Maven](http://maven.apache.org/) 进行统一管理。 

## Features

- Github Markdown 文件一键生成目录，且支持多次生成

- 支持重复标题的生成

- 支持特殊字符的过滤

## 变更日志

> [变更日志](doc/CHANGELOG.md)

# 快速入门

## maven 引入

```xml
<dependency><
```

- 属性说明

| 属性 | 说明 | 备注 | 
|:---|:---|:---|
| filePath | md 文件的路径 | 必填。|
| charset | md 文件的编码 | 选填。默认值 `UTF-8` |

## Java 运行方式

- 环境依赖 

需要 mvn 环境

- 参考

直接参考 [Main.java](src/main/java/com/github/houbb/markdown/toc/Main.java) 编写测试代码。

或者参考类: [AtxMarkdownTocTest.java](src/test/java/com/github/houbb/markdown/toc/core/impl/AtxMarkdownTocTest.java) 编写测试代码。
