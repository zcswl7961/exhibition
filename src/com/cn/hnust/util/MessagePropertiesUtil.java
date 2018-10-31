package com.cn.hnust.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 访问地址url配置文件解析
 * @author zcswl
 * @time 2017-9-24
 *
 */
public class MessagePropertiesUtil {

	private static final Properties config = loadConfig("message.properties");
	
	private static Logger logger = LoggerFactory.getLogger(MessagePropertiesUtil.class);
	
	private static Properties loadConfig(String fileName){
		Properties prop = new Properties();
		try {
			prop.load(new InputStreamReader(MessagePropertiesUtil.class.getClassLoader()
					.getResourceAsStream(fileName), "UTF-8"));
			System.out.println("配置文件加载成功！");
		} catch (IOException e) {
			logger.warn("从配置文件加载数据同步配置异常", e);
		}
		return prop;
	}
	public static String getProperties(String name){
		String returnStr=config.getProperty(name);
		if(!"".equals(returnStr)&&returnStr!=null)
			return returnStr;
		return "";
		
	}
}
