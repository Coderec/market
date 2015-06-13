package com.online.market.model;

// Generated 2014-3-30 13:25:23 by Hibernate Tools 3.3.0.GA

import java.util.Date;

/**
 * NComment generated by hbm2java
 */
public class NComment implements java.io.Serializable {
	private static final long serialVersionUID = 2483465389411612744L;
	private String ncId;
	private Needs needs;
	private String ncMsg;
	private Date ncTime;
	private Boolean ncIsNew;
	private String uid;

	public NComment() {
	}

	public NComment(String ncId) {
		this.ncId = ncId;
	}

	public NComment(String ncId, Needs needs, String ncMsg, Date ncTime,
			Boolean ncIsNew, String uid) {
		this.ncId = ncId;
		this.needs = needs;
		this.ncMsg = ncMsg;
		this.ncTime = ncTime;
		this.ncIsNew = ncIsNew;
		this.uid = uid;
	}

	public String getNcId() {
		return this.ncId;
	}

	public void setNcId(String ncId) {
		this.ncId = ncId;
	}

	public Needs getNeeds() {
		return this.needs;
	}

	public void setNeeds(Needs needs) {
		this.needs = needs;
	}

	public String getNcMsg() {
		return this.ncMsg;
	}

	public void setNcMsg(String ncMsg) {
		this.ncMsg = ncMsg;
	}

	public Date getNcTime() {
		return this.ncTime;
	}

	public void setNcTime(Date ncTime) {
		this.ncTime = ncTime;
	}

	public Boolean getNcIsNew() {
		return this.ncIsNew;
	}

	public void setNcIsNew(Boolean ncIsNew) {
		this.ncIsNew = ncIsNew;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	
}