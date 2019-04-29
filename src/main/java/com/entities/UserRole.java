package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userRoleId;
	private Long userid;
	private Long roleid;

	public Long getUser_role_id() {
		return userRoleId;
	}

	public void setUser_role_id(Long user_role_id) {
		this.userRoleId = user_role_id;
	}

	public Long getUserId() {
		return userid;
	}

	public void setUserId(Long userId) {
		this.userid = userId;
	}

	public UserRole(Long user_role_id, Long userId, Long roleId) {
		super();
		this.userRoleId = user_role_id;
		this.userid = userId;
		this.roleid = roleId;
	}

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getRoleId() {
		return roleid;
	}

	public void setRoleId(Long roleId) {
		this.roleid = roleId;
	}

}
