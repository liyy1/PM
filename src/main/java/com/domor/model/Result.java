package com.domor.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 异步返回结果封装
 */
@Data
public class Result implements Serializable {

	private final static int SUCCESS = 0;
	private final static int ERROR = 1;
	private final static int TIMEOUT = 2;
	private final static int NOAUTH = 3;

	private int code;//状态码
	private String message;//提示信息
	private Object data;//返回数据

	public static Result success(){
		Result result = new Result();
		result.code = SUCCESS;
		return result;
	}

	public static Result success(String message){
		Result result = new Result();
		result.code = SUCCESS;
		result.message = message;
		return result;
	}

	public static Result success(Object data){
		Result result = new Result();
		result.code = SUCCESS;
		result.data = data;
		return result;
	}

	public static Result error(String message){
		Result result = new Result();
		result.code = ERROR;
		result.message = message;
		return result;
	}

	public static Result noAuth(){
		Result result = new Result();
		result.code = NOAUTH;
		return result;
	}

	public static Result timeout(){
		Result result = new Result();
		result.code = TIMEOUT;
		return result;
	}

}
