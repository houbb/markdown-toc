#!/usr/bin/env bash
# 用于生成 markdown toc(linux)
# 使用方式：
# 1. 赋值权限： chmod +x ./markdownToc.sh
# 2. 执行： ./markdownToc.sh
# Last Update Time: 2018-1-30 15:14:59
# Author:   houbb

ECHO "----------------------"
ECHO "Markdown file path is required, such as: E:\CODE_GEN\markdown-toc\README.md"
ECHO "Markdown file charset is optional, default value is UTF-8"
ECHO "----------------------"

filePath=
charset=

ECHO -n "Markdown file path: "
read filePath

ECHO -n "Markdown file charset: "
read charset

ECHO \n
ECHO "You input path: ${filePath}"
ECHO "You input charset: ${charset}"

# 执行
java -jar markdown-toc.jar ${filePath} ${charset}