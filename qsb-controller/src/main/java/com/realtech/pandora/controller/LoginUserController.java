package com.realtech.pandora.controller;
import com.realtech.pandora.common.enums.VerificationEnum;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.common.utils.JVMCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.utils.LoginUserInfo;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/login/user")
@Api(value = "login-user-controller", description = "返回登陆信息")
public class LoginUserController {
	
	
	public ResponseResult<LoginUserInfo> getUserDetail() {
		return null;	
	}

	@GetMapping(value = "/operate")
	public ResponseResult<Boolean> operate(String _pandora_p_acc_) {
		LoginUserInfo loginUserInfo = JVMCache.TOKEN_AND_USER.get(_pandora_p_acc_);
		//JQ303EHVOAEH5NBZ45CR为集团用户id
		if (! loginUserInfo.getFactoryId().equals(VerificationEnum.FACTORY_ID.getMsg())) {
			return new ResponseResult<>(ResultStatus.SUCCESS,true);
		}else
			return new ResponseResult<>(ResultStatus.SUCCESS,false);
	}

}
