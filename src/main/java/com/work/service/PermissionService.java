package com.work.service;

import java.util.List;

import com.work.domain.Permission;
import com.work.domain.dto.PermissionDTO;

public interface PermissionService {

	List <Permission> findAll();
	String save (PermissionDTO p);
	String update(PermissionDTO p);
	String delete(String id);
	Permission findById(String id);
	List<Permission> findByRoleId(String id);
	List<Permission> findByEntity(String entity);
}
