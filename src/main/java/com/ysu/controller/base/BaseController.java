package com.ysu.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ysu.common.constants.BaseReturnCode;
import com.ysu.common.constants.Constants;
import com.ysu.common.constants.ReturnCode;
import com.ysu.common.utils.Pager;
import com.ysu.common.utils.StringUtil;
import com.ysu.common.utils.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/18 22:35
 * @Description:
 **/
public class BaseController implements Constants {

    protected final Logger LOGGER = LogManager.getLogger(getClass());

    /**
     * 转跳至404页面
     */
    public static final String TO_404 = "redirect:../error/400";

    /**
     * 转跳至500页面
     */
    public static final String TO_500 = "redirect:../error/500";

    /**
     * 缺少参数的json字符串
     */
    public static String PARAM_MISSING_STRING = null;

    /**
     * 参数格式不正确
     */
    public static String PARAM_FORMAT_ERROR_STRING = null;

    /**
     * 参数不合法
     * */
    public static String PARAM_NOT_LEGAL = null;
    /**
     * 参数中存在特殊符号
     */
    public static String PARAM_SPECIAL_SYMBOL_STRING = null;

    //校验失败
    public static String TOKEN_CHECK_FAIL = null;

    /**
     * 接口已作废
     * */
    public static String REST_LEGAL = null;

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");

    public static final Pattern MOBILE_PATTERN = Pattern.compile("^(13|15|18|14|17|19|16)[0-9]{9}$");

    public BaseController() {
        if (PARAM_MISSING_STRING == null) {
            PARAM_MISSING_STRING = json(BaseReturnCode.PARAM_MISSING.emptyObject());
        }
        if (PARAM_FORMAT_ERROR_STRING == null) {
            PARAM_FORMAT_ERROR_STRING = json(BaseReturnCode.PARAM_FORMAT_ERROR.emptyObject());
        }
        if (PARAM_SPECIAL_SYMBOL_STRING == null) {
            PARAM_SPECIAL_SYMBOL_STRING = json(BaseReturnCode.PARAM_SPECIAL_SYMBOL.emptyObject());
        }
        if (PARAM_NOT_LEGAL == null) {
            PARAM_NOT_LEGAL = json(BaseReturnCode.DATA_NOT_FOUND.emptyObject());
        }

        if (REST_LEGAL == null) {
            REST_LEGAL = json(BaseReturnCode.REST_ERROR.emptyObject());
        }

        if (TOKEN_CHECK_FAIL == null) {
            TOKEN_CHECK_FAIL = json(BaseReturnCode.TOKEN_CHECK_FAIL.emptyObject());
        }
    }

    /**
     * 将对象转换为字符串(JSON)
     * @param obj 对象信息
     * @return json字符串
     */
    public String json(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            LOGGER.error("系统转换JSON字符串出现异常", e);
            return "ERROR";
        }
    }

    /**
     * 失败字符串
     * @param returnCode 失败枚举信息, 默认为系统错误
     * @return 失败的JSON串
     */
    public String jsonFailed(ReturnCode returnCode) {
        if (returnCode == null) {
            returnCode = BaseReturnCode.SYS_ERROR;
        }
        return json(returnCode.toObject(null));
    }

    /**
     * 成功字符串
     * @return 成功信息
     */
    public String jsonSuccess() {
        return json(BaseReturnCode.SUCCESS.emptyObject());
    }

    /**
     * 错误信息默认处理器
     * @param request 请求信息
     * @param e 异常信息
     * @return 转跳至500页面,并且日志记录错误信息
     */
    @ExceptionHandler
    public String exp(HttpServletRequest request, Exception e) {
        request.setAttribute("ex", e);
        LOGGER.error("进行处理发生错误!", e);
        return json(BaseReturnCode.SYS_ERROR.emptyObject());
    }


    /**
     * 获取分页信息,从request中获取
     * @param request 请求信息
     * @return 分页信息,包括页面展示大小和需要展示的页面信息
     */
    public Pager getPaper(HttpServletRequest request) {
        Pager pager = new Pager();
        // 当前分页
        int currentPage = REQUEST_PAPER_CURRENT_PAGE;
        String currentPageStr = request.getParameter(REQUEST_PAPER_CURRENT_PAGE_NAME);
        if (StringUtil.isNotEmpty(currentPageStr)) {
            try {
                currentPage = Integer.parseInt(currentPageStr);
            } catch (NumberFormatException e) {
                LOGGER.warn("获取分页信息中的请求页面信息出错, 使用默认值!", e);
            }
        }
        // 分页大小
        int pageSize = CONFIG_PAGESIZE;
        String pageSizeStr = request.getParameter(REQUEST_PAPER_PAGE_SIZE_NAME);
        if (StringUtil.isNotEmpty(pageSizeStr)) {
            try {
                pageSize = Integer.parseInt(pageSizeStr);
            } catch (NumberFormatException e) {
                LOGGER.warn("获取当前分页显示的数量失败, 使用默认值!", e);
            }
        }
        Integer id=null;
        String pagePreIdStr = request.getParameter(REQUEST_PAPER_PAGE_PRE_ID_NAME);
        if (StringUtil.isNotEmpty(pagePreIdStr)) {
            try {
                id = Integer.parseInt(pagePreIdStr);
            } catch (NumberFormatException e) {
                LOGGER.warn("获取当前分页显示的前一个id失败, 使用默认值!", e);
            }
        }
        Integer floor=null;
        String pagePreFloorStr = request.getParameter(REQUEST_PAPER_PAGE_PRE_FLOOR_NAME);
        if (StringUtil.isNotEmpty(pagePreFloorStr)) {
            try {
                floor = Integer.parseInt(pagePreFloorStr);
            } catch (NumberFormatException e) {
                LOGGER.warn("获取当前分页显示的前一个楼层失败, 使用默认值!", e);
            }
        }
        pager.setCurrentPage(currentPage);
        pager.setPageSize(pageSize);
        pager.setId(id);
        pager.setFloor(floor);
        return pager;
    }

    /**
     * 检查是否为null, 如果存在null,则直接返回true
     * @param params 参数列表
     * @return
     */
    public boolean checkNull(Object... params) {
        if (params != null) {
            for (Object param : params) {
                if (param instanceof String) {
                    if (StringUtil.isEmpty((String) param)) {
                        return true;
                    }
                } else if (Validator.isNull(param)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查是否为整形, 包含类型(byte, short, int, long)这几种类型, 如果存在不是整形的话则直接返回true
     * @param params 参数列表
     * @return
     */
    public boolean checkInteger(String... params){
        if (params != null) {
            for (String param : params) {
                if (StringUtil.isNotEmpty(param)) {
                    for (int i = param.length();--i>=0;){
                        char ch = param.charAt(i);
                        if (i == 0 && (ch == '+' || ch == '-')) {
                            continue;
                        }
                        if (!Character.isDigit(ch)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检查是否为电子邮件, 如果存在不是邮箱的字符串, 则直接返回true
     * @param params 参数列表
     * @return
     */
    public boolean checkEmail(String... params){
        if (params != null) {
            for (String param : params) {
                if (StringUtil.isNotEmpty(param)) {
                    Matcher matcher = EMAIL_PATTERN.matcher(param);
                    if (!matcher.find()){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 将字符串转换为数字
     * @param data 需要转换的参数
     * @return 转换的结果数字,如果转换出错,则直接返回null
     */
    public Integer parseInt(String data) {
        return parseInt(data, null);
    }

    /**
     * 将字符串转换为数字
     * @param data 需要转换的参数
     * @param defaultVal 如果参数为空或者转换失败的情况,则返回默认值
     * @return 数字
     */
    public Integer parseInt(String data, Integer defaultVal) {
        if (StringUtil.isEmpty(data)) {
            return defaultVal;
        }
        try {
            return Integer.parseInt(data);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /* *
     * 检测json格式的数据是否正确
     * @Param [params]
     * @return boolean
     */
    public boolean checkJson(String... params) {
        if(params != null){
            for(String param : params){
                try{
                    JSONObject.parseObject(param);
                }catch(Exception e){
                    return false;
                }
            }
        }
        return true;
    }
}
