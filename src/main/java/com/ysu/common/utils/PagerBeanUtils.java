package com.ysu.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

public class PagerBeanUtils {
	
	 @SuppressWarnings("unchecked")
	public static String getPagerJson(List list,Pager pager){
		 PagerBean pagerBean=new PagerBean();
		 pagerBean.setList(list);
		 pagerBean.setPager(pager);
		 return JSON.toJSONString(pagerBean, SerializerFeature.UseSingleQuotes);
		}

}
