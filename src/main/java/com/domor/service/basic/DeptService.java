package com.domor.service.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domor.dao.basic.DeptDao;
import com.domor.model.Dept;
import com.domor.model.TreeNode;
import com.domor.utils.MapUtils;
import com.domor.utils.ObjectUtils;
import com.domor.utils.TreeNodeUtils;

@Service
public class DeptService {

	@Autowired
	private  DeptDao dao;
	
	public String getNewCode1(){
		return dao.getNewCode1();
	}
	public String getNewCode2(String pcode){
		return dao.getNewCode2(pcode);
	}
	
	@Transactional
	public void insert(Map<String,Object> params) {
		dao.insert(params);
	}
	
	public int update(Map<String,Object> params) {
		return dao.update(params);
	}
	
	public int delete(String code) {
		return 0;
	}
	
	public Dept findById(int deptId) {
		return dao.findById(deptId);
	}

	public List<Map<String,Object>> getChildDepts(String username){
		return dao.getChildDepts(username);
	}
	
	public List<Map<String,Object>> getDeptForComb(String username,String area){
		return dao.getDeptForComb(username,area);
	}

	
	public List<Map<String,Object>> getDepts(Map<String, Object> params){
		return dao.getChildDeptsForTree(params);
	}
	
	public List<Map<String,Object>> getTopAreasForTree(List<Map<String,Object>> list,int topGrade){
		List<Map<String,Object>> listTop = new ArrayList<Map<String,Object>>();
		for(int i = 0;i<list.size();i++){
			Map<String,Object> dept = list.get(i);
			int grade = MapUtils.getIntValue(dept,"level");
			if(grade==topGrade){
				listTop.add(dept);
			}	
		}
		return listTop;
	}
	//-----------------------------------------------------------------------
	public List<Dept> buildTreeGrid(Map<String, Object> params) {
		return getFatherDepts(query(params));
	}
	
	public List<Dept> query(Map<String, Object> params) {
		if(ObjectUtils.isNull(params)) {
			params = new HashMap<String, Object>();
		}
		return dao.query(params);
	}
	
	private List<Dept> getFatherDepts(List<Dept> depts) {
		List<Dept> resultDepts = new ArrayList<Dept>();
		for(Dept dept : depts) {
			if(dept.getParentId() == 0) {
				dept.setChildren(getChildrenDepts(dept.getId(), depts));
				resultDepts.add(dept);
			}
		}
		return resultDepts;
	}
 
	private List<Dept> getChildrenDepts(int parentId, List<Dept> depts) {
		List<Dept> resultDepts = new ArrayList<Dept>();
		for(Dept dept : depts) {
			if(dept.getParentId() == 0) {
				continue;
			}
			if(parentId == dept.getParentId()) {
				dept.setChildren(getChildrenDepts(dept.getId(), depts));
				resultDepts.add(dept);
			}
		}
		return resultDepts;
	}
	//----------------上级菜单--------------------
	public List<TreeNode> buildTree(int delete_flag) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("delete_flag", delete_flag);
		List<Dept> depts = query(params);
		return TreeNodeUtils.getFatherNodes1(convert2TreeNodes(depts));
	}
	
	private List<TreeNode> convert2TreeNodes(List<Dept> depts) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for(Dept dept : depts) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(dept.getId()+"");
			treeNode.setParentId(dept.getParentId()+"");
			treeNode.setText(dept.getName());
			treeNode.setLevel(dept.getDeptLevel());
			treeNodes.add(treeNode);
		}
		return treeNodes;
	}
	public Object getDeptList() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("delete_flag", 0);
		List<Dept> depts = dao.query(params);
		
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for(Dept dept : depts) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", dept.getId());
			data.put("text", dept.getName());
			datas.add(data);
		}
		return datas;
	}
	
}
