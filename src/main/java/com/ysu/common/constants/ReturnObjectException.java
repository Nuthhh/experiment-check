package com.ysu.common.constants;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/18 22:08
 * @Description:
 **/
public class ReturnObjectException extends RuntimeException{
    private ReturnObject returnObject;

    public ReturnObjectException(ReturnObject returnObject, String appendMessage) {
        super(returnObject.getResult().getCode() + ":" + returnObject.getResult().getMsg() + (appendMessage != null ? appendMessage : ""));
        this.returnObject = returnObject;
    }

    public ReturnObjectException(ReturnObject returnObject, String appendMessage, Throwable cause) {
        super(returnObject.getResult().getCode() + ":" + returnObject.getResult().getMsg() + (appendMessage != null ? appendMessage : ""), cause);
        this.returnObject = returnObject;
    }

    public ReturnObject getReturnObject() {
        return this.returnObject;
    }
}
