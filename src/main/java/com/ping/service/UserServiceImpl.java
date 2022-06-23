package com.ping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ping.mapper.UserMapper;
import com.ping.pojo.Shipping;
import com.ping.pojo.User;
/**
 * 用户信息

 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	//检查用户是否存在
	@Override
	public boolean checkUserIsExist(String username) {
		//调用dao层，查询用户是否存在
		Long checkUserIsExist = userMapper.checkUserIsExist(username);
		return checkUserIsExist>0?true:false;
	}
	//用户注册
	
	public boolean addRegisterInfo(User user) {
		//调用dao层，将用户信息插入数据库
		int isnotSuccess = userMapper.addRegisterInfo(user);
		return isnotSuccess>0?true:false;
	}

	//用户登录
	@Override
	public User selectByUserNameAndPassword(User user) {
		//调用dao层查询用户是否存在
	  User isExistUser = userMapper.selectByUserNameAndPassword(user);
		return isExistUser;
	}

	//修改密码
	@Override
	public boolean updateChangePassword(User user) {
		// TODO Auto-generated method stub
		int isnotSuccess=userMapper.updateChangePassword(user);
		return isnotSuccess>0?true:false;
	}

	//根据用户id查询收货地址信息
	@Override
	public Shipping selectUserShippingInfo(Integer uid) {
		// TODO Auto-generated method stub
		Shipping ship=userMapper.selectUserShippingInfo(uid);
		return ship;
	}

	//更新收货地址
	@Override
	public boolean updateShippingInfo(Shipping shipinfo) {
		// TODO Auto-generated method stub
		int isnotSuccess=userMapper.updateShippingInfo(shipinfo);
		return isnotSuccess>0?true:false;
	}

	//保存收货地址
	@Override
	public boolean saveShippingInfo(Shipping shipobj) {
		// TODO Auto-generated method stub
		int isnotSuccess=userMapper.saveShippingInfo(shipobj);
		return isnotSuccess>0?true:false;
	}

	//更新用户头像
	@Override
	public void updateUserImg(User user) {
		// TODO Auto-generated method stub
		userMapper.updateUserImg(user);
	}

	//查询用户所有收货地址
	@Override
	public List<Shipping> selectUserAllShipping(Integer uid) {
		// TODO Auto-generated method stub
		List<Shipping> shipList=userMapper.selectUserAllShipping(uid);
		return shipList;
	}

	//更新收货地址是否默认
	@Override
	public void updateShipDefaultFlag(Shipping ship) {
		// TODO Auto-generated method stub
		userMapper.updateShipDefaultFlag(ship);
	}

	//删除收货地址
	@Override
	public void deleteShip(Shipping shipobj) {
		// TODO Auto-generated method stub
		userMapper.deleteShip(shipobj);
	}

	@Override
	public Shipping selectUserShippingInfoById(Shipping shipobj) {
		Shipping ship=userMapper.selectUserShippingInfoById(shipobj);
		return ship;
	}

	//个人资料修改
	@Override
	public boolean updateUserInfo(User user) {
		// TODO Auto-generated method stub
		int isnotSuccess = userMapper.updateUserInfo(user);
		return isnotSuccess>0?true:false;
	}

	//更加uid查询用户信息
	@Override
	public User selectUserById(Integer uid) {
		// TODO Auto-generated method stub
		User user=userMapper.selectUserById(uid);
		return user;
	}

	
}
