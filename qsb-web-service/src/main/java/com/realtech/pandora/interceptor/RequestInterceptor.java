package com.realtech.pandora.interceptor;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.realtech.pandora.auth.AuthCons;
import com.realtech.pandora.common.exception.ProjectException;
import com.realtech.pandora.common.response.ResponseResult;
import com.realtech.pandora.common.response.ResultStatus;
import com.realtech.pandora.common.utils.Common;
import com.realtech.pandora.common.utils.JVMCache;
import com.realtech.pandora.common.utils.LoginUserInfo;
import com.realtech.pandora.dao.UUMSUserMapper;
import com.realtech.pandora.domain.UUMSUser;
import com.realtech.pandora.webCode.RCode;

import lombok.extern.slf4j.Slf4j;

/**
 * 请求拦截器
 *
 * @author wuml
 * @version 1.0
 * @since 2017-12-20
 */
@Slf4j
@Component
public class RequestInterceptor implements AuthCons,HandlerInterceptor {
    @Value("${system.auth.login-host}")
    private String loginUrl;

    @Value("${system.suspect.trace}")
    private boolean traceEnable;

    @Value("${system.auth.login-port}")
    private Integer loginPort;

    @Value("${system.auth.login-api-host}")
    private String loginApiUrl;

    @Value("${system.auth.login-api-port}")
    private Integer loginApiPort;

    @Value("${system.token.checkTokenUrl}")
    private String checkTokenUrl;

    @Value("${system.token.portalKey}")
    private String portalKey;

    @Value("${system.token.homepage}")
    private String homepage;

    @Value("${system.uumsPrefix}")
    private  String uumsPrefix ;
    
    @Autowired
    private Common common;
    
    @Autowired
    private UUMSUserMapper uUMSUserMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
       // log.info("请求的url:{}....正在执行远程token校验....", request.getRequestURL() );
        boolean preHandleResult = true;
        String token = "&" + _PANDORA_P_ACC_ + "=" + request.getParameter(_PANDORA_P_ACC_);
        String ip = request.getRemoteAddr();
        if (StringUtils.isEmpty(request.getParameter(_PANDORA_P_ACC_))){
            token = "";
        }
        String uumsPrefixTmp = common.trimUrl(uumsPrefix);
        String checkToken = "http://" + loginApiUrl + ":" + loginApiPort+ "/" + uumsPrefixTmp + checkTokenUrl + "?" + _DSK_ + "=" + portalKey + "&remoteIp=" + ip + token;
        log.info("远程调用地址:{}", checkToken);
        String result = common.requestUrl(checkToken);
        JSONObject resultJSON = JSONObject.parseObject(result);
        Integer resultCode = (Integer) resultJSON.getJSONObject("response").get("code");
        
        if (resultCode == 10){
        	log.info("用户调用{}接口未通过验证........", request.getRequestURI());
        	//此处改成out流输出
        	JSONObject rCode = new JSONObject();
        	JSONObject responseResult = new JSONObject();
        	responseResult.put("code", 10);
        	responseResult.put("name", "需要登录");
        	responseResult.put("msg", getLoginUrl());
        	rCode.put("response", responseResult);
        	response.setContentType("application/json;charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.write(rCode.toJSONString());
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				out.write("服务器异常!");
			}
			return false;
           // throw new ProjectException(ResultStatus.USER_NEED_LOGIN, getLoginUrl());
        }else {
            preHandleResult = true;
        } 
        if(StringUtils.isEmpty(result)) {
            throw new ProjectException(ResultStatus.DEFINE_ERROR, "Interface of user information check abnormal");
        }
        //此处应当从返回结果中 拿出工厂等数据...缓存进map
        LoginUserInfo loginUserInfo = JSONObject.parseObject(resultJSON.getJSONObject("response").get("data").toString(), LoginUserInfo.class);
        if (JVMCache.TOKEN_AND_USER.get(request.getParameter(_PANDORA_P_ACC_)) == null) { //令牌为空,则写入缓存
        	UUMSUser user = uUMSUserMapper.selectByPrimaryKey(loginUserInfo.getUserId());
        	if (user != null) {
        		loginUserInfo.setUserName(user.getLocalName());
        	} else {
        		loginUserInfo.setUserName("未知用户");
        	}
        	JVMCache.TOKEN_AND_USER.put(request.getParameter(_PANDORA_P_ACC_), loginUserInfo);//登录信息写入缓存
        	log.info("当前缓存中的数据:{}", JSONObject.toJSONString(JVMCache.TOKEN_AND_USER.get(request.getParameter(_PANDORA_P_ACC_))));
        }
        return preHandleResult;
    }

    public String getLoginUrl() {
        try {
            return  "http://" + loginUrl + ":" + loginPort + "/login/index.html#/?redirectUrl=" + URLEncoder.encode(homepage,"UTF-8") + "&" + _PANDORA_P_S_ + "=" + "JP9NYFO8DZ4C25AGW8R6" + "&" + _DSK_ + "=" + portalKey;
        } catch (UnsupportedEncodingException e) {
            throw new ProjectException(e.getMessage());
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
    
    
    
}
