package com.hupun.demo.service.ex;
/**
 * 修改仓库信息异常
 */
public class UpdateStockException extends BaseException{
    public UpdateStockException() {
        super();
    }

    public UpdateStockException(String message) {
        super(message);
    }

    public UpdateStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateStockException(Throwable cause) {
        super(cause);
    }

    protected UpdateStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
