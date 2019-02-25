package com.ysu.common.utils;

import java.util.ArrayList;
import java.util.List;

public class PagerBean {
	@SuppressWarnings("unchecked")
	private List list = new ArrayList();
	private Pager pager=new Pager();


	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}

	public static PagerBean empty(Pager page){
		PagerBean pagerBean = new PagerBean();
		pagerBean.setPager(page);
		pagerBean.getPager().setTotalCount(0);
		return pagerBean;
	}

}
