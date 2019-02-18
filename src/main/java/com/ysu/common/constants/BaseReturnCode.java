package com.ysu.common.constants;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/18 21:34
 * @Description:
 **/
public interface BaseReturnCode {

    // 成功
    ReturnCode SUCCESS              = ReturnCode.build(0, "success");
    // 缺少参数
    ReturnCode PARAM_MISSING        = ReturnCode.build(-1, "param_missing");
    // 参数格式错误
    ReturnCode PARAM_FORMAT_ERROR   = ReturnCode.build(-2, "param_format_error");
    // 无此数据
    ReturnCode DATA_NOT_FOUND       = ReturnCode.build(-3, "no_data");
    // 没有权限
    ReturnCode NO_AUTH              = ReturnCode.build(-4, "no_auth");
    // 参数包含特殊符号
    ReturnCode PARAM_SPECIAL_SYMBOL = ReturnCode.build(-5, "param_special_symbol");

    // 旧接口已作废
    ReturnCode REST_ERROR = ReturnCode.build(-6, "接口已作废");
    // 旧接口已作废
    ReturnCode PARAM_ERROR = ReturnCode.build(-8, "param_error");
    // 系统错误
    ReturnCode SYS_ERROR            = ReturnCode.build(-99, "system_error");

    //校验失败
    ReturnCode TOKEN_CHECK_FAIL = ReturnCode.build(-15, "token_check_fail");
}
