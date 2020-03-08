package com.hupun.demo.service.ex;

/**
 * 新增员工失败
 */
public class AddStuffException extends BaseException{
    public AddStuffException() {
        super();
    }

    public AddStuffException(String message) {
        super(message);
    }

    public AddStuffException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddStuffException(Throwable cause) {
        super(cause);
    }

    protected AddStuffException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
