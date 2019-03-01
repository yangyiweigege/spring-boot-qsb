package com.realtech.pandora.common.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.realtech.pandora.common.tree.FontTree;

/**
 * JAVA虚拟机缓存
 * 
 * @author yangyiwei
 * @date 2018年8月15日
 * @time 下午5:51:46
 */
public class JVMCache {
	//存放 token-user的映射
	public final static Map<String, LoginUserInfo> TOKEN_AND_USER = new ConcurrentHashMap<String, LoginUserInfo>();
	
	//存放地图缓存
	public static FontTree fontTreeCache;
}
