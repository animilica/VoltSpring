package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.work.dao.AdminRoleDAO;
import com.work.domain.AdminRole;
import com.work.domain.dto.AdminRoleDTO;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {
	
	@Autowired
	private AdminRoleDAO arDao;

	@Override
	public List<AdminRole> findAll() {
		return arDao.findAll();
	}

	@Override
	public String save(AdminRoleDTO ar) {
		Assert.notNull(ar, "Request contains no data");
		return arDao.save(ar);
	}

	@Override
	public String update(AdminRoleDTO ar) {
		Assert.notNull(ar, "Request contains no data");
		return arDao.update(ar);
	}

	@Override
	public String delete(String id) {
		Assert.notNull(id, "No ID inside the request");
		return arDao.delete(id);
	}

	@Override
	public AdminRole findById(String id) {
		Assert.notNull(id, "No ID inside the request");
		return arDao.findById(id);
	}

	@Override
	public List<AdminRole> findByAdminId(String adminId) {
		Assert.notNull(adminId, "No ID inside the request");
		return arDao.findByAdminId(adminId);
	}

	@Override
	public AdminRole findByRoleId(String roleId) {
		Assert.notNull(roleId, "No ID inside the request");
		return arDao.findByRoleId(roleId);
	}
	
}
