package com.domor.controller.basic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.domor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domor.service.basic.SystemService;
import com.domor.model.Menu;
import com.domor.model.Result;
import com.domor.utils.DigestUtil;

@Controller
public class SystemController {

	@Autowired
	private SystemService service;

	@RequestMapping("/")
    public String index() {
		return "redirect:/index";
    }
	
	@RequestMapping("/index")
	public String index(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Menu> menus = service.getRoleMenus(user.getRole());
		model.addAttribute("menus", menus);
		model.addAttribute("user", user);
		return "index";
	}
	
    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String login_view(){
        return "login";
    }

    @RequestMapping(value = "/noAuth",method = RequestMethod.GET)
    public String noAuth(){
        return "noAuth";
    }

	@RequestMapping("/exit")
	public void exit(HttpSession session) {
		session.removeAttribute("user");
	}

    @ResponseBody
	@RequestMapping(value = "/login")
	public Object login(HttpServletRequest request,String username,String password) {
		String md5pwd = DigestUtil.md5(password);
		User user = service.getUser(username);
		if (user == null) {
			return Result.error("手机号或用户名不存在！");
		} else if(!user.getPassword().equals(md5pwd)){
			return Result.error("密码输入错误!");
		} else {
			request.getSession().setAttribute("user", user);
		}
		return Result.success();
	}

    @ResponseBody
	@RequestMapping(value = "/getUserInfo")
	public Object getUserInfo(String username) {
		return Result.success(service.getUser(username));
	}

}
