package com.domor.model;

import java.util.List;

import lombok.Data;

@Data
public class User {

	private String username;// 用户名称
	private String password;// 用户密码
	private String realname;// 用户姓名
	private String phone;// 手机
	private Integer role;// 角色ID
	private String roleName;// 角色名称
	private List<String> Actions;// 用户拥有的菜单权限url

}
