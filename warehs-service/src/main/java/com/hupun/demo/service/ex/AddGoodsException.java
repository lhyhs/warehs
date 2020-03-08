package com.hupun.demo.service.ex;

/**
 * 添加商品异常
 */
public class AddGoodsException extends BaseException {
    public AddGoodsException() {
        super();
    }

    public AddGoodsException(String message) {
        super(message);
    }

    public AddGoodsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddGoodsException(Throwable cause) {
        super(cause);
    }

    protected AddGoodsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
