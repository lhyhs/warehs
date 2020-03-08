package com.hupun.demo.service.ex;

/**
 * 商品编码重复，商品已存在
 */
public class GoodsAlreadyException extends BaseException{
    public GoodsAlreadyException() {
        super();
    }

    public GoodsAlreadyException(String message) {
        super(message);
    }

    public GoodsAlreadyException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoodsAlreadyException(Throwable cause) {
        super(cause);
    }

    protected GoodsAlreadyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
