package com.ping.service;

import com.github.pagehelper.PageInfo;
import com.ping.pojo.User;

public interface AdminUserService {

	PageInfo<User> findAllUsersBySplitPage(Integer page, Integer limit, String keyword);

	Integer deleteUser(Integer uid);

	Integer updateUserInfo(User user);

}
