package com.rrtx.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zhangting
 */
public class JsonMapCoverUtill {

	/**
	 * 将json字符串转换成map
	 * @param str
	 * @return map
	 */
	public static Map<String, Object> coverJsonString2Map(String str) {

		//第一种方式
		Map maps = (Map) JSON.parse(str);
		return maps;
	}

	/**
	 * 将String转换成Map
	 * @param string
	 * @return
	 */
	public static Map<String, Object> coverString2Map(String string) {
		Map<String, Object> map = new HashMap<String, Object>();
		string = string.replace(" ", "");
		String[] arr = string.split(",");
		for (String str : arr) {
			String[] params = str.split("=");
			if(params.length>1){
				String key = params[0];
				String value = params[1];
				map.put(key, value);
			} 
		}
		return map;
	}
}
