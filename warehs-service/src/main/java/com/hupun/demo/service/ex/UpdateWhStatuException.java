package com.hupun.demo.service.ex;

/**
 * 修改仓库状态呢异常
 */
public class UpdateWhStatuException extends BaseException {
    public UpdateWhStatuException() {
        super();
    }

    public UpdateWhStatuException(String message) {
        super(message);
    }

    public UpdateWhStatuException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateWhStatuException(Throwable cause) {
        super(cause);
    }

    protected UpdateWhStatuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
