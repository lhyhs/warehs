package com.hupun.demo.service.ex;

public class UpdateStuffException extends BaseException {
    public UpdateStuffException() {
        super();
    }

    public UpdateStuffException(String message) {
        super(message);
    }

    public UpdateStuffException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateStuffException(Throwable cause) {
        super(cause);
    }

    protected UpdateStuffException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
