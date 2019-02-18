package com.ysu.common.utils;


import com.ysu.common.constants.Constants;
import java.util.Collections;
import java.util.List;

/**
 * 分页加工类
 * @author alan_c 2013-06-17
 */
public class Pager {
	private int offset = 0; // 当前偏移，从0开始
	private int pageSize = 0; // 分页大小
	private int currentPage = 0; // 当前页,从1开始，如果设置 则以这个优先
	private int totalCount = 0; // 总记录数
	private int indexPageCount = 0; // 分页显示数
	private Integer id;
	private Integer floor;

	/**
	 * 获取当前分页的偏移值
	 * @return
	 */
	public int getOffset() {
		if (this.offset < 0){
			return 0;
        }
        //如果有设置当前页，则以这个优先
		if(currentPage > 0) {
            this.offset = (currentPage - 1) * this.getPageSize();
        }
		return this.offset;
	}

	/**
	 * 设置当前分页的偏移值
	 * @param offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * 获取分页大小，默认从配置中读取
	 * @return
	 */
	public int getPageSize() {
		if (pageSize < 1){
			pageSize = PropertyUtil.getIntProperty(
					Constants.CONFIG_PAGESIZE_NAME, Constants.CONFIG_PAGESIZE);
        }

		return pageSize;
	}

	/**
	 * 设置分大小
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取当前页
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 设置当前页，如果设置该值，之前setOffset值无效，以此为准
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		if (currentPage < 1){
			currentPage = 1;
        }
		this.currentPage = currentPage;
	}

	/**
	 * 获取记录总数
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置记录总数
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if(currentPage > this.getPageCount()) {
		    currentPage = this.getPageCount();
        }
	}

	/**
	 * 获取索引页数量，超过的数量使用..表示 如 1，2，3，4，5 ... 100
	 * 默认从配置文件中加载
	 * @return
	 */
	public int getIndexPageCount() {
		if (indexPageCount < 1){
			indexPageCount = PropertyUtil.getIntProperty(
					Constants.CONFIG_INDEXPAGECOUNT_NAME,
					Constants.CONFIG_INDEXPAGECOUNT);
        }
		return indexPageCount;
	}

	/**
	 * 设置索引页数量
	 * @param indexPageCount
	 */
	public void setIndexPageCount(int indexPageCount) {
		this.indexPageCount = indexPageCount;
	}

	/**
	 * 获取分页总数
	 * @return
	 */
	public int getPageCount() {
		return (int) Math.ceil((double) totalCount / getPageSize());
	}

	/**
	 * 将结果转换为JsonString的形式
	 * @param totalCount 总共的数量
	 * @param list 列表结果信息
	 * @return 分页信息和结果信息的JSON字符串
	 */
	@SuppressWarnings("unchecked")
	public PagerBean toShowPageBean(int totalCount, List list) {
		this.setTotalCount(totalCount);

		PagerBean pagerBean = new PagerBean();
		pagerBean.setPager(this);
		pagerBean.setList(list == null ? Collections.emptyList() : list);
		return pagerBean;
	}

	public PagerBean toShowPageBean(List list) {
		int count=list.size();
		int endNum=this.pageSize * this.currentPage;
		int startNum=this.pageSize * (this.currentPage-1);
		if(count<startNum){
			return this.toShowPageBean(count,null);
		}
		list=list.subList(startNum, count < endNum ? count : endNum);
		return this.toShowPageBean(count, list);

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}



}
