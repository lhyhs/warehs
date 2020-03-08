package com.hupun.demo.service.ex;

/**
 * 员工已存在异常
 */
public class StuffAlreadyException extends BaseException {
    public StuffAlreadyException() {
        super();
    }

    public StuffAlreadyException(String message) {
        super(message);
    }

    public StuffAlreadyException(String message, Throwable cause) {
        super(message, cause);
    }

    public StuffAlreadyException(Throwable cause) {
        super(cause);
    }

    protected StuffAlreadyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
