package com.domor.controller.basic;

import com.domor.service.basic.IndexService;
import com.domor.common.MyException;
import com.domor.model.Result;
import com.domor.utils.DigestUtil;
import com.domor.utils.MapUtils;
import com.domor.utils.ParamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("index")
public class IndexController {

	@Autowired
	private IndexService service;

	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		return "/index/home";
	}

	@RequestMapping("/home1")
	public String home1(HttpServletRequest request) {
		return "/index/home1";
	}

	@RequestMapping("/home2")
	public String home2(HttpServletRequest request) {
		return "index/home2";
	}

	@RequestMapping("/userInfo")
	public String userInfo(HttpServletRequest request) {
		return "index/userInfo";
	}

	@RequestMapping("/password")
	public String password(HttpServletRequest request) {
		return "index/password";
	}

	@RequestMapping("/message")
	public String message(HttpServletRequest request) {
		return "index/message";
	}

	@ResponseBody
	@RequestMapping("/savePwd")
	public Object savePwd(HttpServletRequest request) {
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		Map<String,Object> params = ParamUtils.getParameterMap(request);
		String md5pwd = DigestUtil.md5(MapUtils.getStringValue(params, "oldpwd"));
		String passwrod = MapUtils.getStringValue(user, "password");
		String username = MapUtils.getStringValue(user, "username");

		if(md5pwd!=null && md5pwd.equals(passwrod)) {
			String md5npwd = DigestUtil.md5(MapUtils.getStringValue(params, "newpwd"));
			params.put("username", username);
			params.put("password", md5npwd);
			service.savePwd(params);
		}else {
			throw new MyException("原始密码输入错误！");
		}
		return Result.success();
	}

}
