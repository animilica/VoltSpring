package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.work.dao.RoleDAO;
import com.work.domain.Role;
import com.work.domain.dto.RoleDTO;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO roleDao;

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public String save(RoleDTO r) {
		Assert.notNull(r, "Request contains no data");
		return roleDao.save(r);
	}

	@Override
	public String update(RoleDTO r) {
		Assert.notNull(r, "Request contains no data");
		return roleDao.update(r);
	}

	@Override
	public String delete(String id) {
		Assert.notNull(id, "No ID inside the request");
		return roleDao.delete(id);
	}

	@Override
	public Role findById(String id) {
		Assert.notNull(id, "No ID inside the request");
		return roleDao.findById(id);
	}

	@Override
	public List<Role> findByName(String name) {
		Assert.notNull(name, "No name inside the request");
		return roleDao.findByName(name);
	}

	@Override
	public List<Role> findByOrganization(String orgId) {
		Assert.notNull(orgId, "No ID inside the request");
		return roleDao.findByOrganization(orgId);
	}
	
}