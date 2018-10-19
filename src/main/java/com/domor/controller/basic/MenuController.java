package com.domor.controller.basic;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.domor.service.basic.MenuService;
import com.domor.model.Menu;
import com.domor.model.Result;
import com.domor.utils.MapUtils;
import com.domor.utils.ParamUtils;

@Controller
@RequestMapping("menu")
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insertGet(HttpServletRequest request) {
        int parentId = ParamUtils.getIntParameter(request, "parentId");
        logger.debug("添加菜单，上级菜单ID为：{}", parentId);
        Menu parent = new Menu();
        if (parentId > 0)
            parent = menuService.findById(parentId);
        return new ModelAndView("/basic/menu/menu_add", "parent", parent);
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Object insertPost(HttpServletRequest request, Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
        menu.setCreator(MapUtils.getStringValue(user, "username"));
        menu.setCreateTime(new Date());
        menuService.insert(menu);
        return Result.success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Object updateGet(HttpServletRequest request) {
        int menuId = ParamUtils.getIntParameter(request, "menuId");
        Menu menu = menuService.findById(menuId);
        ModelAndView mv = new ModelAndView("/basic/menu/menu_edit");
        mv.addObject("menu", menu);
        mv.addObject("parent", menuService.findById(menu.getParentId()));
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updatePost(HttpServletRequest request, Menu menu) {
        Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
        menu.setEditor(MapUtils.getStringValue(user, "username"));
        menu.setEditTime(new Date());
        menuService.update(menu);
        return Result.success();
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request) {
        int menuId = ParamUtils.getIntParameter(request, "menuId");
        menuService.delete(menuId);
        return Result.success();
    }

    @RequestMapping("/index")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("/basic/menu/menu");
    }

    @ResponseBody
    @RequestMapping("/query")
    public Object query(HttpServletRequest request) {
        Map<String, Object> params = ParamUtils.getParameterMap(request);
        return menuService.buildTreeGrid(params);
    }

    @ResponseBody
    @RequestMapping("/tree")
    public Object tree(HttpServletRequest request) {
        int menuType = ParamUtils.getIntParameter(request, "menuType");
        int roleId = ParamUtils.getIntParameter(request, "roleId");
        int delete_flag = ParamUtils.getIntParameter(request, "delete_flag", 0);
        return menuService.buildTree(menuType, roleId, delete_flag);
    }

    @ResponseBody
    @RequestMapping("/updateRights")
    public Object updateRights(Integer roleId, String menuIds) {
        menuService.updateRights(roleId, menuIds);
        return Result.success();
    }
}
