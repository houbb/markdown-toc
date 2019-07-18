# Table of Contents

* 1 [markdown-toc](#markdown-toc)
  * 1.1 [变更日志](#变更日志)
  * 1.2 [Features](#features)
* 2 [环境依赖](#环境依赖)
  * 2.1 [JDK](#jdk)
  * 2.2 [Maven](#maven)
* 3 [快速入门](#快速入门)
  * 3.1 [maven 引入](#maven-引入)
  * 3.2 [md 文件](#md-文件)
  * 3.3 [快速开始](#快速开始)
* 4 [属性配置](#属性配置)
  * 4.1 [属性说明](#属性说明)
  * 4.2 [返回值说明](#返回值说明)
* 5 [测试案例](#测试案例)
* 6 [其他](#其他)


# markdown-toc


```
  _ __ ___   __ _ _ __| | ____| | _____      ___ __       | |_ ___   ___ 
 | '_ ` _ \ / _` | '__| |/ / _` |/ _ \ \ /\ / / '_ \ _____| __/ _ \ / __|
 | | | | | | (_| | |  |   < (_| | (_) \ V  V /| | | |_____| || (_) | (__ 
 |_| |_| |_|\__,_|_|  |_|\_\__,_|\___/ \_/\_/ |_| |_|      \__\___/ \___|
 
```

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/markdown-toc/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/markdown-toc)
[![Build Status](https://www.travis-ci.org/houbb/markdown-toc.svg?branch=release_1.0.2)](https://www.travis-ci.org/houbb/markdown-toc?branch=release_1.0.2)
[![Coverage Status](https://coveralls.io/repos/github/houbb/markdown-toc/badge.svg?branch=release_1.0.2)](https://coveralls.io/github/houbb/markdown-toc?branch=release_1.0.2)

Markdown-toc 可以用来生成 markdown 页面的目录，便于 github 页面展现。


- 文档

[中文说明](README.md) | [English Readme](README-ENGLISH.md)

> 备注

对于标题，md 有两种语法 [setext](http://docutils.sourceforge.net/mirror/setext.html) 
和 [atx](http://www.aaronsw.com/2002/atx/) 模式。

暂时只支持 **atx** 形式。

## 变更日志

[变更日志](doc/changelog/CHANGELOG.md)

## Features

- Github Markdown 文件一键生成目录

- 支持 fluent 优雅的写法

- 支持多次生成

- 支持重复标题的生成

- 支持特殊字符的过滤

- 支持指定不同的文件编码

- 支持文件夹的文件批量处理(可指定是否包含子文件夹文件)

- 支持是否写入文件，可返回目录的内容，便于用户自行处理

- 支持多线程写文件

- 支持 i18n

- 支持目录编号生成(1.0.5)

## v1.0.8 更新日志

1. 优化 github 对应的链接地址，修复中文符号 BUG

# 环境依赖

## JDK 

1.0.5 及其以前为 jdk8 编译, 请确保 JDK 设置正确。

1.0.6 版本使用 jdk7 编译上传。

后续 1.XX 版本都将支持 jdk7，更便于使用。

## Maven

Jar 使用 [Maven](http://maven.apache.org/) 进行统一管理。 

# 快速入门

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>markdown-toc</artifactId>
    <version>${maven-version}</version>
</dependency>
```

## md 文件

本项目支持的 md 文件后缀名称为 `.md` 或者 `.markdown`

## 快速开始

- 单个文件

```java
AtxMarkdownToc.newInstance().genTocFile(path);
```

其中 path 为 md 文件的路径

- 指定文件夹

```java
AtxMarkdownToc.newInstance().genTocFile(path);
```

其中 path 为 md 文件的父类文件夹

# 属性配置

- 代码示例

```java
AtxMarkdownToc.newInstance()
                .charset("UTF-8")
                .write(true)
                .subTree(true);
```

## 属性说明 

| 序号 | 属性 | 默认值 |  说明 |
|:----|:----|:----|:----|
| 1 | charset | `UTF-8` | 文件编码 | 
| 2 | write | `true` | 是否将 toc 写入文件(默认写入) | 
| 3 | subTree | `true` | 是否包含子文件夹的文件(默认包含) | 
| 4 | order | `false` | 是否生成目录编号(默认不生成，1.0.5以后) | 

## 返回值说明

`genTocFile()` 返回 TocGen，`genTocDir()` 返回 List<TocGen>

- TocGen 属性说明

| 序号 | 属性 |  类型 |  说明 |
|:----|:----|:----| :----|
| 1 | filePath | String | 当前 md 的文件路径 |
| 2 | tocLines | List<String> | 当前 md 文件对应的目录内容 |

# 测试案例

[单个文件-目录生成测试案例](https://github.com/houbb/markdown-toc/blob/release_1.0.2/src/test/java/com/github/houbb/markdown/toc/core/impl/AtxMarkdownTocFileTest.java)

[文件夹-目录生成测试案例](https://github.com/houbb/markdown-toc/blob/release_1.0.2/src/test/java/com/github/houbb/markdown/toc/core/impl/AtxMarkdownTocDirTest.java)

# 其他

> [Issues & Bugs](https://github.com/houbb/markdown-toc/issues)
