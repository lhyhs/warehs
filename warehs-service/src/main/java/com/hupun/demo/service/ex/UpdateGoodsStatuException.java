package com.hupun.demo.service.ex;

/**
 * 修改商品状态异常
 */
public class UpdateGoodsStatuException extends BaseException {
    public UpdateGoodsStatuException() {
        super();
    }

    public UpdateGoodsStatuException(String message) {
        super(message);
    }

    public UpdateGoodsStatuException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateGoodsStatuException(Throwable cause) {
        super(cause);
    }

    protected UpdateGoodsStatuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
