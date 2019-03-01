package com.realtech.pandora.service;

import com.realtech.pandora.common.tree.FontTree;

/**
 * 工厂地点业务逻辑层
 * @author yangyiwei
 * @date 2018年7月19日
 * @time 下午3:52:55
 */
public interface FactoryLocationService {

	/**
	 * 形成工厂地图树
	 * @return
	 */
	public FontTree generateLocationTree();

}
