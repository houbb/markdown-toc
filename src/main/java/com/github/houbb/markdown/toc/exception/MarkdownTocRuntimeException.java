package com.github.houbb.markdown.toc.exception;

/**
 * markdown toc 运行时异常
 * @author bbhou
 * @version 1.0.0
 * @since 1.0.0, 2018/01/30
 */
public class MarkdownTocRuntimeException extends RuntimeException{

    public MarkdownTocRuntimeException() {
    }

    public MarkdownTocRuntimeException(String message) {
        super(message);
    }

    public MarkdownTocRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarkdownTocRuntimeException(Throwable cause) {
        super(cause);
    }

    public MarkdownTocRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
