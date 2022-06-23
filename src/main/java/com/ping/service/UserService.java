package com.ping.service;

import java.util.List;

import com.ping.pojo.Shipping;
import com.ping.pojo.User;
/**
 * 用户信息

 */
public interface UserService {

	//检查用户名是否存在
	public boolean checkUserIsExist(String username);
	
    //用户注册
	public boolean addRegisterInfo(User user);
	
    //用户登录
	public User selectByUserNameAndPassword(User user);

	//修改密码
	public boolean updateChangePassword(User user);

	//根据用户id查询收货地址信息
	public Shipping selectUserShippingInfo(Integer uid);
	//更新收货地址
	public boolean updateShippingInfo(Shipping shipinfo);
	//保存收货地址
	public boolean saveShippingInfo(Shipping shipobj);

	//更新用户头像
	public void updateUserImg(User user);
	
	//查询用户所有收货地址
	public List<Shipping> selectUserAllShipping(Integer uid);

	//更新收货地址是否默认
	public void updateShipDefaultFlag(Shipping ship);

	//删除收货地址
	public void deleteShip(Shipping shipobj);
	//根据id查询收货地址信息
	public Shipping selectUserShippingInfoById(Shipping shipobj);

	//个人资料修改
	public boolean updateUserInfo(User user);

	//根据uid查询用户信息
	public User selectUserById(Integer uid);

	
	
	
	
}
