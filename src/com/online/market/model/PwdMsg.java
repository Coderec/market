package com.online.market.model;

// Generated 2014-3-30 13:25:23 by Hibernate Tools 3.3.0.GA

import java.util.Date;

/**
 * PwdMsg generated by hbm2java
 */
public class PwdMsg implements java.io.Serializable {

	private static final long serialVersionUID = -2048330260846404770L;
	private String pid;
	private Admin admin;
	private String uname;
	private Date ptime;
	private Boolean pisNew;

	public PwdMsg() {
	}

	

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}



	public PwdMsg(String pid, Admin admin, String uname, Date ptime,
			Boolean pisNew) {
		super();
		this.pid = pid;
		this.admin = admin;
		this.uname = uname;
		this.ptime = ptime;
		this.pisNew = pisNew;
	}



	public String getUname() {
		return uname;
	}



	public void setUname(String uname) {
		this.uname = uname;
	}



	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	public Boolean getPisNew() {
		return pisNew;
	}

	public void setPisNew(Boolean pisNew) {
		this.pisNew = pisNew;
	}

	
}
