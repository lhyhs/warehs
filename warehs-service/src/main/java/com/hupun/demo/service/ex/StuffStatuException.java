package com.hupun.demo.service.ex;

/**
 * 员工状态异常
 */
public class StuffStatuException extends BaseException{
    public StuffStatuException() {
        super();
    }

    public StuffStatuException(String message) {
        super(message);
    }

    public StuffStatuException(String message, Throwable cause) {
        super(message, cause);
    }

    public StuffStatuException(Throwable cause) {
        super(cause);
    }

    protected StuffStatuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
