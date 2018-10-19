package com.domor.dao.basic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.domor.model.Dept;

@Mapper
public interface DeptDao {
	
	String getNewCode1();
	String getNewCode2(String pcode);
	
	int insert(Map<String,Object> params);
	int update(Map<String,Object> params);
	int delete(int id);
	
	Dept findById(int deptId);
	
	List<Map<String,Object>> getChildDepts(String username);
	
	List<Map<String,Object>> getDeptForComb(String username,String area);
	
	List<Map<String,Object>> getAreaTreeData();
	
	List<Map<String,Object>> getChildDeptsForTree(Map<String, Object> params);
	
	List<Dept> query(Map<String, Object> params);
	
	
}
