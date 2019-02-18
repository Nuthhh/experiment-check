package com.ysu.common.constants;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/18 21:59
 * @Description:
 **/
public class ReturnObject {

    private ReturnResult result;

    private Object data;

    public ReturnResult getResult() {
        return result;
    }

    public void setResult(ReturnResult result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
