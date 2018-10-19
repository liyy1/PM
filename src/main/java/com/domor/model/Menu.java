package com.domor.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Menu {

	private Integer menuId;//菜单ID
	private String menuName;//菜单显示名称
	private String menuUrl;//菜单地址
	private String iconUrl;//图标地址(使用fontawesome)
	private Integer parentId;//上级菜单ID
	private Integer menuLevel;//菜单层级：从1开始
	private Integer isLeaf = 1;//是否是叶子节点：0-不是叶子节点，即含有子菜单；1-是叶子节点
	private Integer menuType;//菜单类型：1-菜单；2-按钮
	private Integer menuOrder;//菜单排序，降序排列，例：6,5,4,3,2,1
	private Integer oldParentId;//旧的父节点ID
	private List<Menu> children;//菜单子节点
	
	private String creator;//创建人username
	private Date createTime;//创建时间
	private String editor;//编辑人username
	private Date editTime;//编辑时间
	private Integer delete_flag = 0;//删除标识：0-可用；1-已删除

}
