package com.ping.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.ping.pojo.Admins;


@Repository
public interface AdminsMapper {
	Admins findAdminById(Integer id);
	Integer addAdmins(Admins admin);
	Integer deleteAdmin(Integer id);
	Integer disableAdmin(Integer id);
	Integer updateAdmin(Admins admin);
	List<Admins> findAllAdmin();
	List<Admins> findAdminByName(String name);
}
