package com.ping.service;

import com.ping.pojo.Admins;

public interface AdminService {
	Admins login(String name,String pass);
	Integer updateAdmin(Admins admin);
	Admins findAdminById(Integer id);
}
