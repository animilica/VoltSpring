package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.work.dao.PermissionDAO;
import com.work.domain.Permission;
import com.work.domain.dto.PermissionDTO;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionDAO permDao;

	@Override
	public List<Permission> findAll() {
		return permDao.findAll();
	}

	@Override
	public String save(PermissionDTO p) {
		Assert.notNull(p, "Request contains no data");
		return permDao.save(p);
	}

	@Override
	public String update(PermissionDTO p) {
		Assert.notNull(p, "Request contains no data");
		return permDao.update(p);
	}

	@Override
	public String delete(String id) {
		Assert.notNull(id, "No ID inside the request");
		return permDao.delete(id);
	}

	@Override
	public Permission findById(String id) {
		Assert.notNull(id, "No ID inside the request");
		return permDao.findById(id);
	}

	@Override
	public List<Permission> findByRoleId(String id) {
		Assert.notNull(id, "No ID inside the request");
		return permDao.findByRoleId(id);
	}

	@Override
	public List<Permission> findByEntity(String entity) {
		Assert.notNull(entity, "No Entity inside the request");
		return permDao.findByEntity(entity);
	}
	
}
