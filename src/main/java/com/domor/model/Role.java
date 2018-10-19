package com.domor.model;

import java.util.Date;

import lombok.Data;

@Data
public class Role{

	private Integer roleId;
	private String roleName;
	private String roleType;
	
	private String creator;//创建人
	private Date createTime;//创建时间
	private String editor;//修改人
	private Date editTime;//修改时间
	private Integer delete_flag = 0;//是否可用

}
