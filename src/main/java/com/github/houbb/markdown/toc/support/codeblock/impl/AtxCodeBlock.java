package com.github.houbb.markdown.toc.support.codeblock.impl;

import com.github.houbb.markdown.toc.constant.TocConstant;
import com.github.houbb.markdown.toc.support.codeblock.ICodeBlock;
import com.github.houbb.markdown.toc.util.StringUtil;

import java.util.Stack;

/**
 * 注意：
 * （1）此处只考虑 ``` 开头的情况，不处理 tab 键对应的代码块。
 * （2）如果上一行为空行，或者不存在，且本行以 4 个空格开头，则认为也是代码块。
 * @author binbin.hou
 * @since 1.0.7
 */
public class AtxCodeBlock implements ICodeBlock {

    /**
     * 上一行的内容
     */
    private String previous = "";

    /**
     * 本行的内容
     */
    private String current = "";

    /**
     * 代码块栈信息
     * （1）``` 开头或者 ```XXXX 入栈
     * （2）再次遇到 ```，则出栈
     *
     * 如果，栈内信息为空，则认为是普通信息。
     * 如果栈内信息不为空，则认为是代码块信息。
     */
    private Stack<String> codeBlockStack = new Stack<>();

    /**
     * 创建新的实例
     * @return 实例
     */
    public static AtxCodeBlock newInstance() {
        return new AtxCodeBlock();
    }

    @Override
    public boolean isCodeBlock() {
        //1. 空格型的 code block
        if(isCodeBlockLine(current)) {
            return true;
        }

        //2. 根据栈的信息
        return !codeBlockStack.isEmpty();
    }

    /**
     * 1. 设置当前信息，保留上一行的信息
     * 2. 进行 stack 的判断
     *
     * 2.0 如果为代码块行，直接跳过
     * 2.1 stack 为空
     * ``` 开头，则入栈
     * 2.2 stack 不为空
     * 只有信息等于 ```，将 stack 信息出栈
     * @param newLine 新的一行内容
     */
    @Override
    public void put(String newLine) {
        previous = current;
        current = newLine;

        //1. 代码块行
        if(isCodeBlockLine(current)) {
            return;
        }

        final String currentTrim = current.trim();
        //2. stack 为空
        if(codeBlockStack.isEmpty()
            && currentTrim.startsWith(TocConstant.MD_CODE_BLOCK)) {
            codeBlockStack.push(current);
            return;
        }
        //3. stack 不为空
        if(!codeBlockStack.isEmpty()
            && TocConstant.MD_CODE_BLOCK.equals(currentTrim)) {
            codeBlockStack.pop();
            return;
        }
    }

    /**
     * 是否为代码块行
     * @param current 当前内容
     * @return 是否
     */
    private boolean isCodeBlockLine(final String current) {
        if(StringUtil.isEmpty(previous.trim())
                && current.startsWith(TocConstant.FOUR_BLANK)) {
            return true;
        }
        return false;
    }

}
