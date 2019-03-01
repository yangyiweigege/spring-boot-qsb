package com.realtech.pandora.common.utils;
import static com.alibaba.fastjson.JSON.parseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.realtech.pandora.common.exception.ProjectException;
import com.realtech.pandora.common.response.ResultStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by dell on 2018/7/24.
 */
@Component
@Data
@Slf4j
public class Common {
	@Value("${system.auth.login-api-host}")
	public String loginApiUrl;

	@Value("${system.auth.login-api-port}")
	public Integer loginApiPort;

	@Value("${system.token.portalKey}")
	public String portalKey;

	@Value("${system.token.homepage}")
	public String homepage;

	@Value("${system.uumsPrefix}")
	public String uumsPrefix;
	
	@Autowired
	private RestTemplate restTemplate;

	public String getPersonCodeById(String personId) {
		String url = "http://" + loginApiUrl + ":" + loginApiPort + uumsPrefix + "/api/person/code?personId="
				+ personId;
		String rest = null;
		try {
			rest = requestUrl(url);
			Integer code = (Integer) parseObject(rest).getJSONObject("response").get("code");
			if (code == 1) {
				return (String) parseObject(rest).getJSONObject("response").get("data");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ProjectException(ResultStatus.UNKNOW_ERROR, "获取人员信息失败！");
		}
		throw new ProjectException(ResultStatus.UNKNOW_ERROR, "人员信息错误！");
	}

	public String getPositionName(String personCode) {
		String url = "http://" + loginApiUrl + ":" + loginApiPort + uumsPrefix
				+ "/api/person/info?personCode=" + personCode;
		String rest = null;
		try {
			rest = requestUrl(url);
			Integer code = (Integer) parseObject(rest).getJSONObject("response").get("code");
			if (code == 1) {
				rest = (String) parseObject(rest).getJSONObject("response").getJSONObject("data")
						.getJSONObject("position").get("name");
			} else {
				rest = null;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return rest;
	}

	public String getDepartmentName(String personCode) {
		
		String url = "http://" +loginApiUrl + ":" + loginApiPort + uumsPrefix
				+ "/api/person/info?personCode=" + personCode;
		String rest = null;
		try {
			rest = requestUrl(url);
			Integer code = (Integer) parseObject(rest).getJSONObject("response").get("code");
			if (code == 1) {
				rest = (String) parseObject(rest).getJSONObject("response").getJSONObject("data")
						.getJSONObject("department").get("name");
			} else {
				rest = null;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return rest;
	}

	public String trimUrl(String uumsPrefix) {
		if (uumsPrefix != null && !"".equals(uumsPrefix)) {
			uumsPrefix = "/" + uumsPrefix;
			return uumsPrefix;
		}
		return "";
	}

	/**
	 * 使用 GET 方式请求。
	 *
	 * @param url
	 *            String
	 * @return the string
	 */
	public String requestUrl(String url) {
		return restTemplate.getForObject(url, String.class);
		/*StringBuffer result = new StringBuffer("");

		try {
			URL link = new URL(url);
			InputStream inputStream = link.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String line;

			while ((line = reader.readLine()) != null) {
				result.append(line).append("\n");
			}
			reader.close();
			inputStream.close();
		} catch (Exception e) {
			throw new ProjectException(e.getMessage());
		}
		return result.toString();*/
	}
}
