package com.dcx.poz.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dcx.poz.util.ConstantUtil;
import com.dcx.poz.util.DateUtil;
import com.dcx.poz.util.ErrorCodeUtil;
import com.dcx.poz.util.GlobalContext;
import com.dcx.poz.util.SignUtil;
import com.dcx.poz.util.StringUtil;
import com.dcx.poz.util.redis.RedisClientTemplate;

/**
 * 
 * @author dcx
 * 请求认证过滤器
 */
public class ReqAuthFilter implements Filter {

	public RedisClientTemplate redis = (RedisClientTemplate) GlobalContext.getBean("redisClientTemplate");
	
	private static List<String> greenUrlList = new ArrayList<String>();
	private static List<String> greenExtList = new ArrayList<String>();
	static{
		greenUrlList.add("/");
		greenUrlList.add("/auth/check/error");
		
		greenExtList.add("js");
		greenExtList.add("css");
		greenExtList.add("jsp");
		greenExtList.add("png");
		greenExtList.add("html");
	}
	/**
	 * 获取所有参数
	 * @param req
	 * @return
	 */
	public Map<String, String> getParameters(HttpServletRequest req ){
		Map<String, String> params = new LinkedHashMap<String, String>();
		@SuppressWarnings("rawtypes")
		Enumeration e = req.getParameterNames();
		while(e.hasMoreElements()&&!e.nextElement().equals("signature")){
			String name = (String) e.nextElement();
			String value = req.getParameter(name);
			params.put(name, value);
		}
		return params;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req  = (HttpServletRequest)request;
		String reqUrl = req.getRequestURI();
		//2.得到用户想访问的资源的后缀名
		String ext = reqUrl.substring(reqUrl.lastIndexOf(".")+1);
		reqUrl = reqUrl.substring(reqUrl.indexOf("/", 1));
		if(greenUrlList.contains(reqUrl) || greenExtList.contains(ext)){
			chain.doFilter(request, response);
			return;
		}
		HttpServletResponse res = (HttpServletResponse) response;
		Map<String, String> params = getParameters(req);
		/**
		 * 校验时间戳
		 */
		String timestamp = params.get("timestamp");
		//请求中没有时间戳字段
		if(StringUtil.isEmpty(timestamp)){
			res.sendRedirect(ConstantUtil.SERVER_IP + "/auth/check/error?errorCode="+ErrorCodeUtil.e10000);
			return;
		}
		//时间戳格式不正确
		if(!DateUtil.isTimeStamp(timestamp)){
			res.sendRedirect(ConstantUtil.SERVER_IP + "/auth/check/error?errorCode="+ErrorCodeUtil.e10000);
			return;
		}
		//时间戳为5分钟以内的时间戳
		long millionSeconds = System.currentTimeMillis() - Long.parseLong(timestamp);
		if(millionSeconds< 0l || millionSeconds > 5*60*1000){
			res.sendRedirect(ConstantUtil.SERVER_IP + "/auth/check/error?errorCode="+ErrorCodeUtil.e10012);
			return;
		}
		//得到签名
		String signature = params.get("signature");
		if(StringUtil.isEmpty(signature)){
			res.sendRedirect(ConstantUtil.SERVER_IP + "/auth/check/error?errorCode="+ErrorCodeUtil.e10021);
			return;
		}
		/**
		 * 是否重入攻击
		 */
		if(redis.sismember("signatures", signature)){
			res.sendRedirect(ConstantUtil.SERVER_IP + "/auth/check/error?errorCode="+ErrorCodeUtil.e10020);
			return;
		}
		boolean isSignatureValid = SignUtil.checkSignature(params,signature);
		if(isSignatureValid){
			redis.sadd("signatures", signature);
		}else{
			res.sendRedirect(ConstantUtil.SERVER_IP + "/auth/check/error?errorCode="+ErrorCodeUtil.e10022);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
}
