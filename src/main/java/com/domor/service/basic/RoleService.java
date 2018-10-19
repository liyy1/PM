package com.domor.service.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.domor.model.PagerReturns;
import com.domor.utils.ParamUtils;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domor.dao.basic.RoleDao;
import com.domor.model.Menu;
import com.domor.model.Role;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public PagerReturns query(Map<String, Object> params){
		Page page = ParamUtils.startPage(params);
		return new PagerReturns(roleDao.query(params), page.getTotal());
	}
	
	public Role findById(int roleId) {
		return roleDao.findById(roleId);
	}
	
	public List<Map<Object, Object>> getRole(String roleName) {
		return roleDao.getRole(roleName);
	}
	
	public Integer role_add(Role role) {
		return roleDao.role_add(role);
	}

	public Integer role_edit(Role role) {
		return roleDao.role_edit(role);
	}

	public Integer role_del(Integer roleId) {
		return roleDao.role_del(roleId);
	}


	@Transactional
	public void updateRoleRight(Integer roleId, String menuIds) {
		// 删除角色原有权限
		roleDao.delRightByRoleId(roleId);

		String[] ids = menuIds.split(",");
		for (String idStr : ids) {
			Integer menuId = Integer.parseInt(idStr);
			Menu menu = roleDao.getMenu(menuId);
			if (menu.getMenuLevel() == 4) {
				roleDao.addRight(roleId, menuId);
				// 三级菜单已经存在
				if (roleDao.isExistRight(roleId, menu.getParentId()) > 0) {
					continue;
				} else { // 三级菜单不存在
					roleDao.addRight(roleId, menu.getParentId());
					// 三级菜单
					Menu thirdMenu = roleDao.getMenu(menu.getParentId());
					// 二级菜单已存在
					if (roleDao.isExistRight(roleId, thirdMenu.getParentId()) > 0) {
						continue;
					} else {// 二级菜单不存在
						roleDao.addRight(roleId, thirdMenu.getParentId());
						// 二级菜单菜单
						Menu secondMenu = roleDao.getMenu(thirdMenu.getParentId());
						// 根菜单已存在
						if (roleDao.isExistRight(roleId, secondMenu.getParentId()) > 0) {
							continue;
						} else { // 根菜单不存在
							roleDao.addRight(roleId, secondMenu.getParentId());
						}
					}
				}
			}
			if (menu.getMenuLevel() == 3) {
				roleDao.addRight(roleId, menuId);
				// 二级菜单已经存在
				if (roleDao.isExistRight(roleId, menu.getParentId()) > 0) {
					continue;
				} else {
					roleDao.addRight(roleId, menu.getParentId());
					Menu secondMenu = roleDao.getMenu(menu.getParentId());
					// 根菜单已经存在
					if (roleDao.isExistRight(roleId, secondMenu.getParentId()) > 0) {
						continue;
					} else {
						roleDao.addRight(roleId, secondMenu.getParentId());
					}
				}
			}
			if (menu.getMenuLevel() == 2) {
				if (roleDao.isExistRight(roleId, menu.getMenuId()) > 0) {
					continue;
				} else {
					roleDao.addRight(roleId, menu.getMenuId());
				}
				if (roleDao.isExistRight(roleId, menu.getParentId()) > 0) {
					continue;
				} else {
					roleDao.addRight(roleId, menu.getParentId());
				}
			}
			if (menu.getMenuLevel() == 1) {
				if (roleDao.isExistRight(roleId, menu.getMenuId()) > 0) {
					continue;
				} else {
					roleDao.addRight(roleId, menu.getMenuId());
				}
			}
		}
	}

	public List<Map<String, Object>> dataForCombo() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("delete_flag", 0);
		List<Role> roles = roleDao.getRoleList(params);
		
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for(Role role : roles) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", role.getRoleId());
			data.put("text", role.getRoleName());
			datas.add(data);
		}
		return datas;
	}
	
}
