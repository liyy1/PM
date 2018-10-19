package com.domor.common;

import com.alibaba.fastjson.JSONObject;
import com.domor.model.Result;
import com.domor.model.User;
import com.domor.service.basic.SystemService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

	@Resource
	private SystemService systemService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		System.out.println(request.getRequestURI());
		// 验证session是否存在
		Object obj = request.getSession().getAttribute("user");
		String requestType = request.getHeader("X-Requested-With");
		String method = request.getMethod();
		if (obj == null) {
			if("XMLHttpRequest".equals(requestType)){
				sendJsonMessage(response, Result.timeout());
			}else{
				//TODO BUG 超时后在子页面内点击按钮，只有子页面会跳转到登录页。
				System.out.println("非AJAX请求..");
				PrintWriter out = response.getWriter();
				out.println("<html><script>");
				out.println("window.open ('/toLogin','_parent')"); //作为父窗口打开
				out.println("</script></html>");
				return false;
			}
		}else{
			// 从数据库中获取所有权限URL，并判断当前路径是否包含在数据库
			String requestURI = request.getRequestURI();
			List<String> menus = systemService.getAllActions();
			//如果存在，需要进行验证是否授权，如果不存在，放行
			if(menus.contains(requestURI)){
				List<String> userURls = ((User)obj).getActions();
				if(!userURls.contains(requestURI)){
					if(method.equals("POST")){
						sendJsonMessage(response, Result.noAuth());
					}else{
						response.sendRedirect("/noAuth");
					}
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
	}

	private static void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print(JSONObject.toJSONString(obj));
		writer.close();
		response.flushBuffer();
	}

}
