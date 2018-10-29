package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.work.dao.AdminDAO;
import com.work.domain.Admin;
import com.work.domain.dto.AdminDTO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDao;

	@Override
	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	@Override
	public String save(AdminDTO a) {
		Assert.notNull(a, "Request contains no data");
		return adminDao.save(a);
	}

	@Override
	public String update(AdminDTO a) {
		Assert.notNull(a, "Request contains no data");
		return adminDao.update(a);
	}

	@Override
	public String delete(String id) {
		Assert.notNull(id, "No ID inside the request");
		return adminDao.delete(id);
	}

	@Override
	public List<Admin> findByFirstName(String first_name) {
		Assert.notNull(first_name, "No first name inside the request");
		return adminDao.findByFirstName(first_name);
	}

	@Override
	public List<Admin> findByLastName(String last_name) {
		Assert.notNull(last_name, "No last name inside the request");
		return adminDao.findByLastName(last_name);
	}

	@Override
	public Admin findByEmail(String email) {
		Assert.notNull(email, "No email inside the request");
		return adminDao.findByEmail(email);
	}

	@Override
	public Admin findById(String id) {
		Assert.notNull(id, "No ID inside the request");
		return adminDao.findById(id);
	}

	@Override
	public Admin findByOrganization(String orgId) {
		Assert.notNull(orgId, "No ID inside the request");
		return adminDao.findByOrganization(orgId);
	}

	@Override
	public String changePassword(AdminDTO a) {
		Assert.notNull(a, "Request contains no data");
		return adminDao.changePassword(a);
	}
	
}
