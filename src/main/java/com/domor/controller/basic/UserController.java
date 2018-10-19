package com.domor.controller.basic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.domor.common.MyException;
import com.domor.model.Result;
import com.domor.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.domor.service.basic.UserService;
import com.domor.utils.ParamUtils;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return "basic/user/index";
	}

    @ResponseBody
    @RequestMapping("/query")
    public Object query(HttpServletRequest request) {
        Map<String, Object> params = ParamUtils.getParameterMap(request);
        System.out.println("adfadsfadf11d1");
        return userService.query(params);
    }
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public ModelAndView insertGet(HttpServletRequest request) {
		return new ModelAndView("basic/user/add");
	}
	
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insertPost(HttpServletRequest request) {
		Map<String,Object> user = ParamUtils.getParameterMap(request);
		String phone = MapUtils.getStringValue(user, "phone");
		if(userService.getByPhone(phone)>0){
			throw new MyException("手机号[" +phone+"]已存在！") ;
		}else {
			userService.insert(user);
		}
        return Result.success();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public Object updateGet(HttpServletRequest request) {
		String username = ParamUtils.getStringParameter(request, "username");
		Map<String,Object> user = userService.getByName(username);
		return new ModelAndView("basic/user/edit", "user", user);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object updatePost(HttpServletRequest request) {
		Map<String,Object> user = ParamUtils.getParameterMap(request);
		userService.update(user);
        return Result.success();
	}

	@ResponseBody
	@RequestMapping("/user_initPwd")
	public Object user_initPwd(String username) {
		userService.initUserPwd(username);
		return Result.success();
	}

}
