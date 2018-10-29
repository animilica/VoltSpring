package com.work.service;

import java.util.List;

import com.work.domain.AdminRole;
import com.work.domain.dto.AdminRoleDTO;

public interface AdminRoleService {

	List <AdminRole> findAll();
	String save (AdminRoleDTO ar);
	String update(AdminRoleDTO ar);
	String delete(String id);
	AdminRole findById(String id);
	List<AdminRole> findByAdminId(String adminId);
	AdminRole findByRoleId(String roleId);
}
