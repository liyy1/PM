package com.domor.controller.basic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.domor.model.Result;
import com.domor.utils.ParamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domor.service.basic.RoleService;
import com.domor.model.Role;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/index")
    public String index() {
        return "/basic/role/right";
    }

    @ResponseBody
    @RequestMapping("/getPagedRoleList")
    public Object getPagedRoleList(HttpServletRequest request) {
        return roleService.query(ParamUtils.getParameterMap(request));
    }

    @RequestMapping("/role_add")
    public String role_add() {
        return "/basic/role/role_add";
    }

    @ResponseBody
    @RequestMapping("/role_add_save")
    public Object role_add_save(HttpServletRequest request, Role role) {
        Map<Object, Object> user = (Map<Object, Object>) request.getSession().getAttribute("user");
        role.setCreator(String.valueOf(user.get("username")));
        role.setCreateTime(new Date());
        roleService.role_add(role);
        return Result.success();
    }

    @RequestMapping("/role_edit")
    public String role_edit(Model model, Integer roleId) {
        Role role = roleService.findById(roleId);
        model.addAttribute("role", role);
        return "/basic/role/role_edit";
    }

    @ResponseBody
    @RequestMapping("/isExistRole")
    public Object isExistRole(String roleName) {
        List<Map<Object, Object>> roles = roleService.getRole(roleName);
        return roles.size() > 0;
    }

    @ResponseBody
    @RequestMapping("/role_edit_save")
    public Object role_edit_save(HttpServletRequest request, Role role) {
        Map<Object, Object> user = (Map<Object, Object>) request.getSession().getAttribute("user");
        role.setEditor(String.valueOf(user.get("username")));
        role.setEditTime(new Date());
        role.setDelete_flag(role.getDelete_flag());
        roleService.role_edit(role);
        return Result.success();
    }

    @ResponseBody
    @RequestMapping("/getRoles")
    public Object getRoles(HttpServletRequest request) {
        return roleService.dataForCombo();
    }

}
