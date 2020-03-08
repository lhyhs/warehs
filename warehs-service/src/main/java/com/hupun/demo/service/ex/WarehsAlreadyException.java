package com.hupun.demo.service.ex;

/**
 * 仓库已存在异常
 */
public class WarehsAlreadyException extends BaseException {
    public WarehsAlreadyException() {
        super();
    }

    public WarehsAlreadyException(String message) {
        super(message);
    }

    public WarehsAlreadyException(String message, Throwable cause) {
        super(message, cause);
    }

    public WarehsAlreadyException(Throwable cause) {
        super(cause);
    }

    protected WarehsAlreadyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
