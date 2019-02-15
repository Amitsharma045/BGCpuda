package com.bitGallon.complaintMgmt.manager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.entity.User;
import com.bitGallon.complaintMgmt.repository.EmployeeRepository;

/**
 * @author rpsingh
 *
 */
@Service
@Transactional
public class EmployeeManager {
	
	@Autowired
	private EmployeeRepository repository;
	
	public Employee getEmployee(String mobileNumber) {
		return repository.getEmployee(mobileNumber);
	}
	public void saveUpdateUser(Employee employee) throws Exception {
		repository.saveUpdateUser(employee);
	}
}
