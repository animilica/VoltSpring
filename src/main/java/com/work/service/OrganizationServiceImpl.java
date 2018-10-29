package com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.work.dao.OrganizationDAO;
import com.work.domain.Organization;
import com.work.domain.dto.OrganizationDTO;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDAO orgDao;
	
	@Override
	public List<Organization> findAll() {
		return orgDao.findAll();
	}

	@Override
	public String save(OrganizationDTO o) {
		Assert.notNull(o, "Request contains no data");
		return orgDao.save(o);
	}

	@Override
	public String update(OrganizationDTO o) {
		Assert.notNull(o, "Request contains no data");
		return orgDao.update(o);
	}

	@Override
	public String delete(String id) {
		Assert.notNull(id, "No ID inside the request");
		return orgDao.delete(id);
	}

	@Override
	public List<Organization> findMaster() {
		return null;
	}

	@Override
	public List<Organization> findByName(String name) {
		Assert.notNull(name, "No name inside the request");
		return orgDao.findByName(name);
	}

	@Override
	public Organization findById(String id) {
		Assert.notNull(id, "No ID inside the request");
		return orgDao.findById(id);
	}
	
}
