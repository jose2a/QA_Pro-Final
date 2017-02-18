package com.qapro.entity;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class MemberInfo {

	private Long id;
	private Date registrationTime;
	@NotNull
	private String userName;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String memberRole;
	private Boolean isActive;
	private ArrayList<String> privileges;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}

	public ArrayList<String> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(ArrayList<String> privileges) {
		this.privileges = privileges;
	}	

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "MemberInfo [id=" + id + ", registrationTime=" + registrationTime + ", userName=" + userName
				+ ", password=" + password + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", memberRole=" + memberRole + ", isActive=" + isActive + ", privileges=" + privileges + "]";
	}
	
	

}
