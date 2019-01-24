package com.bitGallon.complaintMgmt.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table; 

@Entity
@Table(name = "BG_EmployeeArea_Mapping")
public class EmployeeAreaMapping extends BaseEntity<String> implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "BG_EmployeeId", referencedColumnName = "id")
	private Employee employee;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "BG_AreaId", referencedColumnName = "id")
	private Area area;
	public long getId() { 
		return id; 
	}
	public void setId(long id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) { 
		this.employee = employee;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}

	
}
