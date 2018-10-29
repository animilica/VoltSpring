package com.work.dao;

import java.util.List;

import com.work.domain.Admin;
import com.work.domain.dto.AdminDTO;

public interface AdminDAO {
	List <Admin> findAll();
	String save(AdminDTO a);
	String update(AdminDTO a);
	String delete(String id);
	String changePassword(AdminDTO a);
	List<Admin> findByFirstName(String first_name);
	List<Admin> findByLastName(String last_name);
	Admin findByEmail(String email);
	Admin findById(String id);
	Admin findByOrganization(String orgId);
}
