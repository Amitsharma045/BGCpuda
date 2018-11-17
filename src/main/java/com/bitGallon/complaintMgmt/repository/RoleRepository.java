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
	
	@SuppressWarnings("unchecked")
	public Role getRole(Area area, Category categry) {
		List<Role> rolelist = getSession()
				.createQuery("FROM Role rl WHERE rl.area.id =:p1 and rl.category.id =:p2")
				.setParameter("p1", area.getId())
				.setParameter("p2", categry.getId()).list();
		if(rolelist.size() != 0) {
			return rolelist.get(0);
		}
		return null;
	}
}
