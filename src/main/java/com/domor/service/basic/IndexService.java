package com.domor.service.basic;

import com.domor.dao.basic.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IndexService {

	@Autowired
	private IndexDao dao;

	public void savePwd(Map<String, Object> params) {
		dao.savePwd(params);
	}

}
