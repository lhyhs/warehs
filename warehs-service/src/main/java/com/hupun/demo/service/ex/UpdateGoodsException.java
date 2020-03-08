package com.hupun.demo.service.ex;

/**
 * 修改商品信息
 */
public class UpdateGoodsException extends BaseException {
    public UpdateGoodsException() {
        super();
    }

    public UpdateGoodsException(String message) {
        super(message);
    }

    public UpdateGoodsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateGoodsException(Throwable cause) {
        super(cause);
    }

    protected UpdateGoodsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
