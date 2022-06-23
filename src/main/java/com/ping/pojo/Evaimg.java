package com.ping.pojo;

import java.io.Serializable;
/**
 * 评价图片

 */
public class Evaimg  implements Serializable{
	private Integer evaimg_id;
	private String evaimg_name;
	private Integer eva_Id;
	
	public Evaimg(Integer evaimg_id, String evaimg_name, Integer eva_Id) {
		super();
		this.evaimg_id = evaimg_id;
		this.evaimg_name = evaimg_name;
		this.eva_Id = eva_Id;
	}
	public Evaimg(String evaimg_name, Integer eva_Id) {
		super();
		this.evaimg_name = evaimg_name;
		this.eva_Id = eva_Id;
	}
	public Integer getEvaimg_id() {
		return evaimg_id;
	}
	public void setEvaimg_id(Integer evaimg_id) {
		this.evaimg_id = evaimg_id;
	}
	public String getEvaimg_name() {
		return evaimg_name;
	}
	public void setEvaimg_name(String evaimg_name) {
		this.evaimg_name = evaimg_name;
	}
	public Integer getEva_Id() {
		return eva_Id;
	}
	public void setEva_Id(Integer eva_Id) {
		this.eva_Id = eva_Id;
	}
	
	
}
