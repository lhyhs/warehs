package com.hupun.demo.service.ex;

/**
 * 添加仓库异常
 */
public class AddStockException extends BaseException {
    public AddStockException() {
        super();
    }

    public AddStockException(String message) {
        super(message);
    }

    public AddStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddStockException(Throwable cause) {
        super(cause);
    }

    protected AddStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
