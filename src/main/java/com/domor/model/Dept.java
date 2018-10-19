package com.domor.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Dept {

	private int id;
	private String name;
	private String remark;
	private int parentId;
	private Integer deptLevel;
	private String memo ;
	private List<Dept> children;
	
	private String creator;//创建人
	private Date createTime;//创建时间
	private String editor;//修改人
	private Date editTime;//修改时间
	private Integer delete_flag = 0;//是否可用
	
}
