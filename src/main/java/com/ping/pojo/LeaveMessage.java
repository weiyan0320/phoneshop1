package com.ping.pojo;
/**
 * 用户留言

 */
public class LeaveMessage {

	private Integer leave_id;      
	private User leave_User;    //留言用户id
	private String leave_telephone;    //留言电话
	private String leave_email;        //留言邮箱
	private String leave_content;         //留言内容
	private String leave_time;    //留言时间
	
	private String leave_username;
	public Integer getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(Integer leave_id) {
		this.leave_id = leave_id;
	}
	public User getLeave_User() {
		return leave_User;
	}
	public void setLeave_User(User leave_User) {
		this.leave_User = leave_User;
	}
	public String getLeave_telephone() {
		return leave_telephone;
	}
	public void setLeave_telephone(String leave_telephone) {
		this.leave_telephone = leave_telephone;
	}
	public String getLeave_email() {
		return leave_email;
	}
	public void setLeave_email(String leave_email) {
		this.leave_email = leave_email;
	}
	public String getLeave_content() {
		return leave_content;
	}
	public void setLeave_content(String leave_content) {
		this.leave_content = leave_content;
	}
	public String getLeave_time() {
		return leave_time;
	}
	public void setLeave_time(String leave_time) {
		this.leave_time = leave_time;
	}
	public String getLeave_username() {
		return leave_username;
	}
	public void setLeave_username(String leave_username) {
		this.leave_username = leave_username;
	}
	
}
