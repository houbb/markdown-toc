# markdown-toc

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/markdown-toc/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/markdown-toc)
[![Build Status](https://www.travis-ci.org/houbb/markdown-toc.svg?branch=master)](https://www.travis-ci.org/houbb/markdown-toc?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/markdown-toc/badge.svg?branch=master)](https://coveralls.io/github/houbb/markdown-toc?branch=master)

Markdown-toc 可以用来生成 markdown 页面的目录，便于 github 页面展现。

> 备注

对于标题，md 有两种语法 [setext](http://docutils.sourceforge.net/mirror/setext.html) 
和 [atx](http://www.aaronsw.com/2002/atx/) 模式。

暂时只支持 **atx** 形式。

## Features

- Github Markdown 文件一键生成目录

- 支持 fluent 优雅的写法

- 支持多次生成

- 支持重复标题的生成

- 支持特殊字符的过滤

- 支持指定不同的文件编码

- 支持文件夹的文件批量处理(可指定是否包含子文件夹文件)

- 支持是否写入文件，可返回目录的内容，便于用户自行处理

- ...



# 环境依赖

## JDK 

JDK7+, 请确保 JDK 设置正确。

## Maven

Jar 使用 [Maven](http://maven.apache.org/) 进行统一管理。 

## 变更日志

> [变更日志](doc/changelog/CHANGELOG.md)

# 快速入门

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>markdown-toc</artifactId>
    <version>1.0.2</version>
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

## 返回值说明

`genTocFile()` 返回 TocGen，`genTocDir()` 返回 List<TocGen>

TocGen 属性说明

| 序号 | 属性 |  类型 |  说明 |
|:----|:----|:----| :----|
| 1 | filePath | String | 当前 md 的文件路径 |
| 2 | tocLines | List<String> | 当前 md 文件对应的目录内容 |

# 测试案例



# 其他

> [Issues & Bugs](https://github.com/houbb/markdown-toc/issues)