package com.ping.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 商品评价
 */
public class Evaluate  implements Serializable{
	private Integer eva_Id;
	private User evaUser;
	private String eva_Content;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date eva_Date;
	private Product product;
	private Integer eva_State;
	private Integer eva_level;
	private List<Evaimg> imgList;
	
	private String eva_username;
	private String eva_productname;
	public Integer getEva_Id() {
		return eva_Id;
	}
	public void setEva_Id(Integer eva_Id) {
		this.eva_Id = eva_Id;
	}
	public User getEvaUser() {
		return evaUser;
	}
	public void setEvaUser(User evaUser) {
		this.evaUser = evaUser;
	}
	public String getEva_Content() {
		return eva_Content;
	}
	public void setEva_Content(String eva_Content) {
		this.eva_Content = eva_Content;
	}
	public Date getEva_Date() {
		return eva_Date;
	}
	public void setEva_Date(Date eva_Date) {
		this.eva_Date = eva_Date;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getEva_State() {
		return eva_State;
	}
	public void setEva_State(Integer eva_State) {
		this.eva_State = eva_State;
	}
	public Integer getEva_level() {
		return eva_level;
	}
	public void setEva_level(Integer eva_level) {
		this.eva_level = eva_level;
	}
	public List<Evaimg> getImgList() {
		return imgList;
	}
	public void setImgList(List<Evaimg> imgList) {
		this.imgList = imgList;
	}
	public String getEva_username() {
		return eva_username;
	}
	public void setEva_username(String eva_username) {
		this.eva_username = eva_username;
	}
	public String getEva_productname() {
		return eva_productname;
	}
	public void setEva_productname(String eva_productname) {
		this.eva_productname = eva_productname;
	}
	
	
	
}
