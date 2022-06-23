package com.ping.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.ping.pojo.Role;


@Repository
public interface RoleMapper {
	Role findById(Integer id);
	List<Role> findAllRole();
	Role findRolesFunsById(Integer roleId);
}
