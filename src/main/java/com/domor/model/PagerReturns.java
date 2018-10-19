package com.domor.model;

import lombok.Data;

/***
 * 服务端返回数据给easyui表格插件的总记录数以及具体返回的数据的封装类
 *
 * @author liyy
 */
@Data
public class PagerReturns {
	private long total; // 总记录数
	private Object rows;// 返回给表格插件的数据
	private Object footer;
	
	public PagerReturns() {}
	
	public PagerReturns(Object rows) {
		this.rows = rows;
	}
	
	public PagerReturns(Object rows, long total) {
		this.rows = rows;
		this.total = total;
	}
	
	public PagerReturns(Object rows, Object footer, long total) {
		this.rows = rows;
		this.footer = footer;
		this.total = total;
	}

}
