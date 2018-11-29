package com.bitGallon.complaintMgmt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.Area;
import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.entity.Role;


@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public Employee getEmployee(String mobileNumber) {
		List<Employee> emplist = getSession()
				.createQuery("FROM Employee emp WHERE emp.registeredMobileNo =:p1")
				.setParameter("p1", mobileNumber).list();
		if(emplist.size() != 0) {
			return emplist.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployee(Role role, Area area) {
		List<Employee> emplist = getSession()
				.createQuery("FROM Employee emp WHERE emp.role.id =:p1 and emp.area.id =:p2 and emp.isActive = 1")
				.setParameter("p1", role.getId())
				.setParameter("p2", area.getId()).list();

		if(emplist.size() != 0) {
			return emplist;
		}
		return null;
	}
	
	
}
