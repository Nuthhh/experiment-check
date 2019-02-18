package com.ysu.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 专门用于构建分页查询中的Map信息, 可以直接通过#{@link PagerSearchMapBuilder#on(Pager)} 或者 #{@link PagerSearchMapBuilder#empty()} 来进行构建,最后通过toSearchMap()方法来进行构建结果
 *
 * @author 刘晗
 */
public class PagerSearchMapBuilder {

    private Map<String, Object> searchMap = new HashMap<String, Object>();

    // 分页中的相关key
    public static final String SEARCH_PAGE_NO_KEY = "pageNo";       // 当前页面
    public static final String SEARCH_PAGE_SIZE_KEY = "pageSize";   // 页面展示数量
    public static final String SEARCH_PAGE_PRE_ID = "pagePreId";   // 页面前一id

    private PagerSearchMapBuilder(){}

    /**
     * 通过该方法进行创建一个搜索参数构建器
     * @param paper 分页信息
     * @return mapper参数构建器
     */
    public static PagerSearchMapBuilder on(Pager paper) {
        if (paper == null) {
            return empty();
        }
        PagerSearchMapBuilder builder = new PagerSearchMapBuilder();
        builder.searchMap.put(SEARCH_PAGE_NO_KEY, paper.getOffset());
        builder.searchMap.put(SEARCH_PAGE_SIZE_KEY, paper.getPageSize());
        if(null!=paper.getId()){
        	builder.searchMap.put(SEARCH_PAGE_PRE_ID, paper.getId());
        	builder.searchMap.put(SEARCH_PAGE_NO_KEY, 0);
        }
        return builder;
    }

    /**
     * 通过该方法构建一个没有任何条件的参数构建器
     * @return mapper参数构建器
     */
    public static PagerSearchMapBuilder empty() {
        return new PagerSearchMapBuilder();
    }

    /**
     * 增加一个信息的参数信息, 值必须不为空
     * @param key 参数key
     * @param val 参数值
     * @return 构建器自身
     */
    public PagerSearchMapBuilder append(String key, Object val) {
       return append(key, val, true);
    }

    public PagerSearchMapBuilder append(String key, Object val, boolean mustNotNull) {
        if (!(Validator.isNull(val) && mustNotNull)) {
            searchMap.put(key, val);
        }
        return this;
    }

    /**
     * 返回查询Map
     * @return 可直接用于转换为Mapper中的查询条件
     */
    public Map<String, Object> toSearchMap(){
        return searchMap;
    }

}
