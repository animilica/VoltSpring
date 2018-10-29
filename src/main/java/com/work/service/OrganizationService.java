package com.work.service;

import java.util.List;

import com.work.domain.Organization;
import com.work.domain.dto.OrganizationDTO;

public interface OrganizationService {
	List <Organization> findAll();
	String save(OrganizationDTO o);
	String update(OrganizationDTO o);
	String delete(String id);
	List<Organization> findMaster();
	List<Organization> findByName(String name);
	Organization findById(String id);
}
