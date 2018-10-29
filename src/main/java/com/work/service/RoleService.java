package com.work.service;

import java.util.List;

import com.work.domain.Role;
import com.work.domain.dto.RoleDTO;

public interface RoleService {
	List <Role> findAll();
	String save (RoleDTO r);
	String update (RoleDTO r);
	String delete(String id);
	Role findById(String id);
	List<Role> findByName(String name);
	List<Role> findByOrganization(String orgId);
}