package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.Area;
import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.entity.Role;

@Repository
@Transactional
public class RoleRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
