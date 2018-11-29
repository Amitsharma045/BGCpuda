package com.bitGallon.complaintMgmt.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BG_Role")
public class Role extends BaseEntity<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "RoleName", nullable = false)
	private String roleName;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "BG_CategoryId", referencedColumnName = "id")
	private Category category;
	@Column(name = "RoleHierarchy", nullable = false)
	private int roleHierarchy;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getRoleHierarchy() {
		return roleHierarchy;
	}
	public void setRoleHierarchy(int roleHierarchy) {
		this.roleHierarchy = roleHierarchy;
	}

}
