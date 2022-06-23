package com.ping.mapper;

import java.util.List;

import com.ping.pojo.User;

public interface AdminUserMapper {

	List<User> findAllUserLikeName(String keyword);

	List<User> findAllUser();

	Integer deleteUser(Integer uid);

	Integer updateUser(User user);

}
