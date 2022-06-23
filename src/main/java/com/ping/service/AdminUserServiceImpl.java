package com.ping.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ping.mapper.AdminUserMapper;
import com.ping.pojo.User;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserMapper adminUserMapper;
	
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public PageInfo<User> findAllUsersBySplitPage(Integer page, Integer limit, String keyword) {
		PageHelper.startPage(page, limit);
		List<User> list=new ArrayList<User>();
		if(keyword!=null&&!keyword.trim().equals("")){
			list=adminUserMapper.findAllUserLikeName(keyword);
		}else{
			list=adminUserMapper.findAllUser();
		}
		PageInfo<User> info=new PageInfo<User>(list);
		return info;
	}


	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer deleteUser(Integer uid) {
		return adminUserMapper.deleteUser(uid);
	}


	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer updateUserInfo(User user) {
		return adminUserMapper.updateUser(user);
	}
}
