package com.hupun.demo.service.ex;

/**
 * 用户名不存在异常
 */
public class StuffNotFoundException extends BaseException {
    public StuffNotFoundException() {
        super();
    }

    public StuffNotFoundException(String message) {
        super(message);
    }

    public StuffNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StuffNotFoundException(Throwable cause) {
        super(cause);
    }

    protected StuffNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
