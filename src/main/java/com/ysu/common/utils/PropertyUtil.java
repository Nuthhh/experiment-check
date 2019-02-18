package com.ysu.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;


public class PropertyUtil {
	private static final Logger logger = LogManager.getLogger(PropertyUtil.class);
	private static Properties properties;
	static {
		// 加载属性文件
		try {
			InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream("up366aicmsg.properties");
			try {
				properties = new Properties();
				properties.load(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				inputStream.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	/**
	 * 加载整型配置
	 * @param key 配置名称
	 * @return 配置值
	 */
	public static int getIntProperty(String key) {
		return getIntProperty(key, 0);
	}

	/**
	 * 加载整型配置
	 * @param key 配置名称
	 * @param def 默认值
	 * @return 配置值
	 */
	public static int getIntProperty(String key, int def) {
		String value = properties.getProperty(key);
		if(StringUtil.isNotEmpty(value))	
		return Integer.valueOf(value);
		return def;
	}

	/**
	 * 加载String配置
	 * @param key 配置名称
	 * @param defualtVal 默认值
	 * @return 配置值
	 */
	public static String getStringProperty(String key, String defualtVal) {
		String value = properties.getProperty(key);
		if(StringUtil.isNotEmpty(value))
			return value;
		return defualtVal;
	}

	
	public static boolean getBoolProperty(String aName) {
		String value = getProperty(aName);
		try {
			return value.equals("true");
		} catch (Throwable t) {
			throw new IllegalArgumentException("Illegal property value: "
					+ aName + " val=" + value);
		}
	}
}
