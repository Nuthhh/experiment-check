package com.ysu.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class JsonUtil {
	// 把JSON文本parse为JavaBean 
	public static final <T> T parseObject(String json, Class<T> clazz) {
		return (T) JSON.parseObject(json,clazz);
	}
	// 将JavaBean序列化为JSON文本 
	public static final String toJSONString(Object obj){
		return JSON.toJSONString(obj);
	}
	//把JSON文本parse成JavaBean集合
	public static final <T> List<T> parseArray(String text, Class<T> clazz){
		return JSON.parseArray(text, clazz);
	}
	// 把JSON文本parse为JSONObject或者JSONArray
	public static final Object parse(String text){
		return JSON.parse(text);
	}
	// 把JSON文本parse成JSONObject
	public static final JSONObject parseObject(String text){
		return JSON.parseObject(text);
	}
	// 把JSON文本parse成JSONArray
	public static final JSONArray parseArray(String text){
		return JSON.parseArray(text);
	}
	// 将JavaBean序列化为带格式的JSON文本 
	public static final String toJSONString(Object object, boolean prettyFormat){
		return JSON.toJSONString(object,prettyFormat);
	}
	// 将JavaBean转换为JSONObject或者JSONArray
	public static final Object toJSON(Object javaObject){
		return JSON.toJSON(javaObject);
	}

}