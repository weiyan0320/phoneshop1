package com.ping.pojo;
/**
 * 返回结果信息
 */
public class ResultUtil {
	String code;//信息标识,00表示成功，01表示失败
	String message;//返回响应信息
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
