package com.ping.pojo;
/**
 * 收货地址实体类

 */
public class Shipping {

	private Integer id;        //id
	private Integer uid;       //用户id
	private String receiver_name;    //收货人姓名
	private String receiver_phone;    //收货人电话
	private String receiver_province;  //省份
	private String receiver_city;    //城市
	private String receiver_district;    //区/县
	private String receiver_address;    //详细地址
	private String default_flag;    //是否默认地址，1表示默认，否则不是
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	public String getReceiver_province() {
		return receiver_province;
	}
	public void setReceiver_province(String receiver_province) {
		this.receiver_province = receiver_province;
	}
	public String getReceiver_city() {
		return receiver_city;
	}
	public void setReceiver_city(String receiver_city) {
		this.receiver_city = receiver_city;
	}
	public String getReceiver_district() {
		return receiver_district;
	}
	public void setReceiver_district(String receiver_district) {
		this.receiver_district = receiver_district;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public String getDefault_flag() {
		return default_flag;
	}
	public void setDefault_flag(String default_flag) {
		this.default_flag = default_flag;
	}
	 
}
