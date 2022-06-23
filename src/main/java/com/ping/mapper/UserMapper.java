package com.ping.mapper;

import java.util.List;

import com.ping.pojo.Shipping;
import com.ping.pojo.User;

public interface UserMapper {

	//检查用户是否存在
	public Long checkUserIsExist(String username);
	//用户注册
	public int addRegisterInfo(User user);
	//用户登录
	public User selectByUserNameAndPassword(User user);
	//修改密码
	public int updateChangePassword(User user);
	//根据用户id查询用户收货地址信息
	public Shipping selectUserShippingInfo(Integer uid);
	//更新收货地址
	public int updateShippingInfo(Shipping shipinfo);
	//保存收货地址
	public int saveShippingInfo(Shipping shipobj);
	//更新用户头像
	public void updateUserImg(User user);
	//查询用户所有收货地址
	public List<Shipping> selectUserAllShipping(Integer uid);
	
	//更新收货地址是否为默认
	public void updateShipDefaultFlag(Shipping ship);
	//删除收货地址
	public void deleteShip(Shipping shipobj);
	//根据id查询用户收货地址信息
	public Shipping selectUserShippingInfoById(Shipping shipobj);
	//个人资料修改
	public int updateUserInfo(User user);
	//根据uid查询用户信息
	public User selectUserById(Integer uid);
	
	
}
