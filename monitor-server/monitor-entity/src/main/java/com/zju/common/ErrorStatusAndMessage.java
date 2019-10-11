package com.zju.common;

/**
 * 错误码和错误信息
 */
public enum ErrorStatusAndMessage {

    Something_not_know(Result.FAIL, "未知错误！");

    protected int status;
    protected String msg;

    ErrorStatusAndMessage(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
