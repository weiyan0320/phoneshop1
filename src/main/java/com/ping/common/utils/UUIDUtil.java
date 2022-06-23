package com.ping.common.utils;

import java.util.UUID;
/**
 * UUIDUtil工具类
 */
public class UUIDUtil {

	public static String getUUID(){
		long currentTime=System.currentTimeMillis();
		return String.valueOf(currentTime+currentTime%10);
	}
	
}
