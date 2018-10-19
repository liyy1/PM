package com.domor.dao.basic;

import java.util.List;
import java.util.Map;

import com.domor.model.User;
import org.apache.ibatis.annotations.Mapper;

import com.domor.model.Menu;

@Mapper
public interface SystemDao {

	User getUser(String username);

	List<String> getAllActions();

	List<String> getRoleActions(int role);

	List<Menu> getRoleMenus(int role);

}