package com.hupun.demo.service.ex;

/***
 * 修改员工状态异常
 */
public class UpdateStuffStauException extends BaseException{
    public UpdateStuffStauException() {
        super();
    }

    public UpdateStuffStauException(String message) {
        super(message);
    }

    public UpdateStuffStauException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateStuffStauException(Throwable cause) {
        super(cause);
    }

    protected UpdateStuffStauException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
