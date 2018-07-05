# Github Markdown Href 

生成规则如下：

- 所有的大写字母会被转化为小写

如 **AB**，结果为 **ab**

- 所有的空格会被替换为-

一个空格对应一个 `-`

如 **a b**，结果为 **a-b**

- 所有的特殊字符会被过滤

比如 `&` `<` `>` `!` `|` 之类的

如 **a&b**，结果为 **ab**

> [特殊字符文件](../src/main/resources/special_char.txt)

> [特殊字符测试](SpecialCharTest.md)

- 如果标题重复

第一个出现的标题正常。
后面的名称依次递增。

如下：

如果有

```
ab
ab
ab
ab
```

四个标题

则结果转化如下

```
ab
ab-1
ab-2
ab-3
```



  