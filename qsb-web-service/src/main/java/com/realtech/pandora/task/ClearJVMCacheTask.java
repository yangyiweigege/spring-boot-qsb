package com.realtech.pandora.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.realtech.pandora.common.utils.JVMCache;
import com.realtech.pandora.service.FactoryLocationService;

import lombok.extern.slf4j.Slf4j;

/**
 * 定期清除JVMCACHE中的令牌信息
 * 
 * @author yangyiwei
 * @date 2018年8月17日
 * @time 上午9:51:49
 */
@Component
@Slf4j
public class ClearJVMCacheTask {

	@Autowired
	private FactoryLocationService factoryLocationService;

	/**
	 * 每隔1小时清理一次token信息
	 */
	@Scheduled(fixedRate = 1000 * 60 * 60)
	public void clearToken() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		log.info("当前时间{},正在清理token缓存....", df.format(new Date()));
		JVMCache.TOKEN_AND_USER.clear();
	}
	
	/**
	 * 每隔半小时重新生成缓存树
	 */
	@Scheduled(fixedRate = 1000 * 60 * 60)
	public void generateFontTree() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		log.info("当前时间{},正在重新生成树缓存....", df.format(new Date()));
		long startTime = System.currentTimeMillis();
		JVMCache.fontTreeCache = null;
		JVMCache.fontTreeCache = factoryLocationService.generateLocationTree();
		long endTime = System.currentTimeMillis(); // 获取结束时间
		log.info("本次生成缓存树....耗时" + (endTime - startTime) + "毫秒.....");
	}
}
