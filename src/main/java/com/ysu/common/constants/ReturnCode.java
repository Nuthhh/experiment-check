package com.ysu.common.constants;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/18 21:36
 * @Description: api接口返回标准
 **/
public class ReturnCode {

    private Integer code;
    private String msg;

    private ReturnCode() {
    }

    public static ReturnCode build(int code, String msg) {
        ReturnCode codeObj = new ReturnCode();
        codeObj.code = code;
        codeObj.msg = msg;
        return codeObj;
    }

    /**
     * 产生一个新的ReturnObject对象信息
     *
     * @param data data字段数据信息
     * @return ReturnObject信息
     */
    public ReturnObject toObject(Object data) {
        ReturnObject returnObject = new ReturnObject();
        ReturnResult result = new ReturnResult();
        result.setCode(this.code);
        result.setMsg(this.msg);
        returnObject.setResult(result);
        returnObject.setData(data);
        return returnObject;
    }

    /**
     * 产生一个新的ReturnObject对象信息, 不传入任何的data数据
     *
     * @return ReturnObject信息
     */
    public ReturnObject emptyObject() {
        return toObject(null);
    }

    /**
     * 用于确定当前执行中出现业务逻辑错误
     *
     * @param logExtendMessage 用于附加到log4j日志输出中使用, 一般可用于附加当前参数中必要的信息记录到日志中
     */
    public void throwFailedException(String logExtendMessage) {
        throwFailedException(logExtendMessage, null, null);
    }

    /**
     * 用于确定当前执行中出现业务逻辑错误
     *
     * @param logExtendMessage 用于附加到log4j日志输出中使用, 一般可用于附加当前参数中必要的信息记录到日志中
     * @param data             错误信息内容, 该值会返回值ReturnObject中的data数据中
     */
    public void throwFailedException(String logExtendMessage, Object data) {
        throwFailedException(logExtendMessage, data, null);
    }

    /**
     * 用于确定当前执行中出现业务逻辑错误
     *
     * @param logExtendMessage 用于附加到log4j日志输出中使用, 一般可用于附加当前参数中必要的信息记录到日志中
     * @param data             用于确定当前执行中出现业务逻辑错误
     * @param throwable        用于指定上层错误信息使用
     */
    public void throwFailedException(String logExtendMessage, Object data, Throwable throwable) {
        ReturnObject returnObject = toObject(data);
        // throw new ReturnObjectException(returnObject, logExtendMessage, throwable);
    }


}
