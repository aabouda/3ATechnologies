package com.entities;

import org.springframework.stereotype.Component;

@Component
public class ConnexionDB {
	private String loginDB;
	private String pwDB;
	private String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLoginDB() {
		return loginDB;
	}
	
	public void setLoginDB(String loginDB) {
		this.loginDB = loginDB;
	}
	
	public String getPwDB() {
		return pwDB;
	}
	
	public void setPwDB(String pwDB) {
		this.pwDB = pwDB;
	}
	
	public ConnexionDB(String loginDB, String pwDB, String path) {
		super();
		this.loginDB = loginDB;
		this.pwDB = pwDB;
		this.path = path;
	}
	
	public void ConnexionDBInit() {
		//this.loginDB = "uelearning";
		this.loginDB = "aladin";
		this.pwDB = "Tunage9*";
		this.path = "jdbc:mysql://localhost:3306/3ATechnologies?serverTimezone=UTC&useSSL=false";
	}
	
	public ConnexionDB() {
		super();
		ConnexionDBInit();
		// TODO Auto-generated constructor stub
	}
	
	

}
