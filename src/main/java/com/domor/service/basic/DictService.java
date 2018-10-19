package com.domor.service.basic;

import java.util.List;
import java.util.Map;

import com.domor.model.PagerReturns;
import com.domor.utils.ParamUtils;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domor.dao.basic.DictDao;

@Service
public class DictService {

	@Autowired
	private  DictDao dao;
	
	public int save(Map<String,Object> dict){
		return dao.save(dict);
	}
	
	public void update(Map<String,Object> dict){
		dao.update(dict);
	}
	
	public String getByKey(String key){
		return dao.getByKey(key);
	}
	
	public List<Map<String,Object>> getByType(String type){
		return dao.getByType(type);
	}
	
	public List<Map<String,Object>> getAll(){
		return dao.getAll();
	}
	
	public PagerReturns query(Map<String,Object> params){
		Page page = ParamUtils.startPage(params);
		return new PagerReturns(dao.query(params), page.getTotal());
	}

	public int getRepeat(Map<String,Object> params) {
		return dao.getRepeat(params);
	}

	public Map<String, Object> getById(int id) {
		return dao.getById(id);
	}

}
