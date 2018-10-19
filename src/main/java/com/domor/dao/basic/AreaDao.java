package com.domor.dao.basic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.domor.model.Area;

@Mapper
public interface AreaDao {
	
	List<Area> queryAll(Map<String, Object> params);
	
}
