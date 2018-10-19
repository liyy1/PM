package com.domor.dao.basic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

	int insert(Map<String,Object> user);
	int update(Map<String,Object> user);		
	int delete(Map<String,Object> user);
	
	Map<String,Object> getByName(String username);
	int getByPhone(String phone);

	List<Map<String,Object>> query(Map<String, Object> params);

	String generatUsername(String string);

	void initUserPwd(Map<String, Object> params);

}
