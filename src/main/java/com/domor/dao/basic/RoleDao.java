package com.domor.dao.basic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.domor.model.Menu;
import com.domor.model.Role;

@Mapper
public interface RoleDao {

	List<Map<Object, Object>> query(Map<String, Object> params);
	List<Role> getRoleList(Map<String, Object> params);
	
	Role findById(int id);
	List<Map<Object, Object>> getRole(String roleName);
	Menu getMenu(Integer menuId);
	
	Integer role_add(Role role);
	Integer role_edit(Role role);
	Integer role_del(Integer roleId);

	Integer isExistRight(Integer roleId, Integer menuId);
	Integer addRight(Integer roleId, Integer menuId);
	Integer delRightByRoleId(Integer roleId);

}
