package com.realtech.pandora.common.utils;

import lombok.Data;

@Data
public class LoginUserInfo {
	
	//用户令牌
	private String _pandora_p_acc_;
	
	//登录用户id
	private String userId;
	
	//登录用户姓名
	private String userName;
	
	//工厂id
	private String factoryId;
	
	//工厂名
	private String factoryName;
	
	//公司id
	private String companyId;
	
	//用户登录ip
	private String remoteIp;
	
	
	
	//sessionAuth
	//private String sessionAuth;

}
