package com.domor.model;

import java.util.Date;

public class Message {
	private int id;
	private int eid;
	private int type;//1:进度提醒；2待办提醒
	private int etype;//事件类型（1事件，2雾炮，3夜间报备）
	private String content;
	private int status;//0:未提醒；1：已提醒
	private String receiver;
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getEtype() {
		return etype;
	}
	public void setEtype(int etype) {
		this.etype = etype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
