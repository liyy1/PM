package com.domor.service.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.domor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.domor.dao.basic.SystemDao;
import com.domor.model.Menu;
import com.domor.utils.StringUtils;

@Service
public class SystemService {

	@Autowired
	private SystemDao dao;

	public User getUser(String username) {
		User user = dao.getUser(username);
		if(user!=null)
			user.setActions(dao.getRoleActions(user.getRole()));
		return user;
	}

	@Cacheable(cacheNames = "system.actions")
	public List<String> getAllActions(){
		return dao.getAllActions();
	}


	//查询用户拥有权限的菜单，以树的形式返回
	public List<Menu> getRoleMenus(int role) {
	    List<Menu> menus = dao.getRoleMenus(role);
	    List<Menu> menuList = new ArrayList<Menu>();
	    // 先找到所有的一级菜单
	    for(Menu menu:menus) {
	    	if(menu.getParentId()==0) {
	    		menuList.add(menu);
	    	}
	    }
	    // 为一级菜单设置子菜单，getChild是递归调用的
	    for (Menu menu : menuList) {
	        menu.setChildren(getChild(menu.getMenuId(), menus));
	    }
		return menuList;
	}
	private List<Menu> getChild(int id, List<Menu> menus) {
	    List<Menu> childList = new ArrayList<>();
	    for (Menu menu : menus) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentId().equals(id)) {
                childList.add(menu);
            }
	    }
	    // 把子菜单的子菜单再循环一遍
	    for (Menu menu : childList) {// 没有url子菜单还有子菜单
	        if (StringUtils.isEmpty(menu.getMenuUrl())) {
	            menu.setChildren(getChild(menu.getMenuId(), menus));
	        }
	    } // 递归退出条件
	    if (childList.size() == 0)
	        return null;
	    return childList;
	}
	
}
