:: 用于生成 markdown toc(windows)
:: author: houbb
:: LastUpdateTime:  2018-1-30 13:37:07
:: 用法：双击运行，或者当前路径 cmd 直接输入 markdownToc.bat

:: 关闭回显
@echo OFF

ECHO ----------------------
ECHO Markdown file path is required, such as: E:\CODE_GEN\markdown-toc\README.md
ECHO Markdown file charset is optional, default value is UTF-8
ECHO ----------------------

:: 初始化设置
SET filePath=
set charset=

:: 获取设置
set /p filePath="Markdown file path: "
set /p charset="Markdown file charset: "
echo.

ECHO You input path: %filePath%
ECHO You input charset: %charset%

:: 执行
java -jar markdown-toc.jar %filePath% %charset%
