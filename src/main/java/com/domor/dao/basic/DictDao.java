package com.domor.dao.basic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictDao {
	
	int save(Map<String,Object> dict);
	
	void update(Map<String,Object> dict);
	
	String getByKey(String key);
	
	List<Map<String,Object>> getByType(String type);
	
	List<Map<String,Object>> getAll();
	
	List<Map<String, Object>> query(Map<String,Object> params);

	int getRepeat(Map<String,Object> params);

	Map<String, Object> getById(int id);
	
}
