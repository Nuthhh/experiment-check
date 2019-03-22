package com.ysu.common.constants;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/18 21:35
 * @Description: 储存程序中涉及到常量
 **/
public interface Constants {

    // 配置中的常量值
    String CONFIG_PAGESIZE_NAME = "pageSize"; // 系统默认分页大小
    int CONFIG_PAGESIZE = 10;

    String CONFIG_INDEXPAGECOUNT_NAME = "pageSize"; // 系统分页索引数
    int CONFIG_INDEXPAGECOUNT = 10;

    String REQUEST_PAPER_CURRENT_PAGE_NAME = "pager.currentPage"; // 请求参数中的当前页面索引属性信息
    int REQUEST_PAPER_CURRENT_PAGE = 1;

    String REQUEST_PAPER_PAGE_SIZE_NAME = "pager.pageSize"; // 请求参数中的请求页面大小属性信息
    String REQUEST_PAPER_PAGE_PRE_ID_NAME = "pager.id"; // 请求参数中的请求页面前一个id属性信息
    String REQUEST_PAPER_PAGE_PRE_FLOOR_NAME = "pager.floor"; // 请求参数中的请求页面前一个楼层属性信息


    String FILE_SERVER_ADDRESS_PRE = "http://39.105.156.250/"; // 文件服务器地址
    String REPORT_SERVER_ADRESS_PRE = "file/document/"; // 实验报告上传地址
    String EXE_SERVER_ADDRESS_PRE = "file/test/"; // 学生提交程序储存临时地址

    Integer HAMMING_DISTANCE_WARN = 3;  // 汉明距离

    Short EXE_LANG_JAVA = 1; // 程序编写语言为Java
    Short EXE_LANG_CC = 2; // 程序编写语言为C++
}
