package com.domor.service.basic;

import java.util.HashMap;
import java.util.Map;

import com.domor.dao.basic.DictDao;
import com.domor.dao.basic.UserDao;
import com.domor.model.PagerReturns;
import com.domor.utils.DigestUtil;
import com.domor.utils.ParamUtils;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao dao;
	@Autowired
	private DictDao dictDao;

	public PagerReturns query(Map<String,Object> params){
		Page page = ParamUtils.startPage(params);
        return new PagerReturns(dao.query(params), page.getTotal());
	}

	public Map<String,Object> getByName(String username) {
		return dao.getByName(username);
	}

	public int getByPhone(String phone) {
		return dao.getByPhone(phone);
	}
	
	public void insert(Map<String,Object> user) {
		user.put("username",dao.generatUsername("pt_"));
		user.put("password", DigestUtil.md5(dictDao.getByKey("password")));
		dao.insert(user);		
	}
	
	public void update(Map<String,Object> user) {
		dao.update(user);
	}
	
	public int delete(Map<String,Object> user) {
		return dao.delete(user);
	}
	
	public void initUserPwd(String username) {
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("password", DigestUtil.md5(dictDao.getByKey("password")));
		dao.initUserPwd(params);
	}
	
}
