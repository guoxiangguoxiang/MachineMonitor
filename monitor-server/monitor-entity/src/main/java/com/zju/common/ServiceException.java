package com.zju.common;

/**
 * 统一Service层的异常
 */
public class ServiceException extends RuntimeException {

    private int status = Result.FAIL;
    private String msg = "";

    public int getStatus() {
        return status;
    }
    public String getMsg() {
        return msg;
    }

    public ServiceException(int status, String message) {
        super(message);
        this.msg = message;
        this.status = status;
    }

    public ServiceException(String message) {
        this(Result.FAIL, message);
    }

    public ServiceException(ErrorStatusAndMessage errorStatusAndMessage) {
        this(errorStatusAndMessage.status, errorStatusAndMessage.msg);
    }

    public Result getResult() {
        return Result.isFail(this);
    }

    public JSONResult getJSONResult() {
        return JSONResult.isFail(this);
    }
}
