package com.domor.controller.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.domor.service.basic.DeptService;
import com.domor.model.Dept;
import com.domor.model.Result;
import com.domor.model.TreeNode;
import com.domor.utils.MapUtils;
import com.domor.utils.ParamUtils;

@Controller
@RequestMapping("dept")
public class DeptController {

	@Autowired
	private DeptService service;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model) {
		return "/basic/dept/index";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertGet(HttpServletRequest request) {
		return "/basic/dept/add";
	}

	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insertPost(HttpServletRequest request) {
		Map<String, Object> params = ParamUtils.getParameterMap(request);
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		params.put("creator", MapUtils.getStringValue(user, "username"));
		service.insert(params);
		return Result.success();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public Object updateGet(HttpServletRequest request, Model model , String code,String pcode) {
		int deptId = ParamUtils.getIntParameter(request, "deptId");
		Dept dept = service.findById(deptId);
		ModelAndView mv = new ModelAndView("/basic/dept/edit");
		mv.addObject("dept", dept);
		if(dept.getParentId()!=0){
			mv.addObject("parent", service.findById(dept.getParentId()));
		}
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object updatePost(HttpServletRequest request) {
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		Map<String, Object> params = ParamUtils.getParameterMap(request);		
		params.put("editor", MapUtils.getStringValue(user, "username"));
		service.update(params) ;
		return Result.success();
	}
	
	@ResponseBody
	@RequestMapping("/query")
	public Object query(HttpServletRequest request) {
		Map<String, Object> params = ParamUtils.getParameterMap(request);
		List<Dept> buildTreeGrid = service.buildTreeGrid(params);
		return buildTreeGrid;
	}
	
	@ResponseBody
	@RequestMapping("/getChildDepts")
	public Object getChildDepts(HttpServletRequest request) {
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		List<Map<String, Object>> childDepts = service.getChildDepts( MapUtils.getStringValue(user, "username"));
		return childDepts;
	}
	
	@ResponseBody
	@RequestMapping("/getDeptList")
	public Object getDeptList(HttpServletRequest request) {
		return service.getDeptList();
	}
	
	@RequestMapping(value = "/areaSelectDialog", method = RequestMethod.GET)
	public String areaSelectDialog(HttpServletRequest request) {
		return "/basic/dept/areaSelectDialog";
	}
	
	@ResponseBody
	@RequestMapping("/getDeptForComb")
	public Object getDeptForComb(HttpServletRequest request,int hasBlank,String area) {
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		List<Map<String, Object>> depts = service.getDeptForComb( MapUtils.getStringValue(user, "username"),area);
		if (hasBlank == 1) {
			Map<String, Object> blankOption = new HashMap<String, Object>();
			blankOption.put("code", "");
			blankOption.put("name", "-选择部门-");
			depts.add(0, blankOption);
		}
		return depts;
	}
	
	//----------------上级菜单--------------------
	@ResponseBody
	@RequestMapping("/tree")
	public Object tree(HttpServletRequest request) {
		int delete_flag = ParamUtils.getIntParameter(request, "delete_flag", 0);
		 List<TreeNode> buildTree = service.buildTree(delete_flag);
		return buildTree;
	}
	
	
}
