package com.domor.model;

/**
 * @ClassName: ServerMessage
 * @Description: 服务端发送消息实体
 * @author liyy
 * @date 2018年4月27日 下午4:25:26
 */
public class ServerMessage {
	
	private String responseMessage;

	public ServerMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}