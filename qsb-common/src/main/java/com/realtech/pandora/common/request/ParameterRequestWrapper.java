package com.realtech.pandora.common.request;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 自定义request 用来替换参数请求
 * @author yangyiwei
 * @date 2018年8月8日
 * @time 下午2:12:49
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {
	
	//自定义parameter参数
	private Map<String, String[]> params = new HashMap<String, String[]>();

	public ParameterRequestWrapper(HttpServletRequest request) {
		// 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
		super(request);
		// 将参数表，赋予给当前的Map以便于持有request中的参数
		params.putAll(request.getParameterMap());
	}

	// 重载一个构造方法
	public ParameterRequestWrapper(HttpServletRequest request, Map<String, Object> extendParams) {
		this(request);
		addAllParameters(extendParams);// 这里将扩展参数写入参数表
	}


	@Override
	public String getParameter(String name) {// 重写getParameter，代表参数从当前类中的map获取
		String[] values = params.get(name);
		if (values == null || values.length == 0) {
			return null;
		}
		return values[0];
	}

	public String[] getParameterValues(String name) {// 同上
		return params.get(name);
	}

	public void addAllParameters(Map<String, Object> otherParams) {// 增加多个参数
		for (Map.Entry<String, Object> entry : otherParams.entrySet()) {
			addParameter(entry.getKey(), entry.getValue());
		}
	}

	public void addParameter(String name, Object value) {// 增加参数
		if (value != null) {
			if (value instanceof String[]) {
				params.put(name, (String[]) value);
			} else if (value instanceof String) {
				params.put(name, new String[] { (String) value });
			} else {
				params.put(name, new String[] { String.valueOf(value) });
			}
		}
	}
}
