package com.online.market.model;

// Generated 2014-3-30 13:25:23 by Hibernate Tools 3.3.0.GA

/**
 * Dictionary generated by hbm2java
 */
public class Dictionary implements java.io.Serializable {
	private static final long serialVersionUID = -2878000674793734227L;
	private String did;
	private String dcategory;
	private String dname;
	private Boolean dvalid;

	public Dictionary() {
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getDcategory() {
		return dcategory;
	}

	public void setDcategory(String dcategory) {
		this.dcategory = dcategory;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Boolean getDvalid() {
		return dvalid;
	}

	public void setDvalid(Boolean dvalid) {
		this.dvalid = dvalid;
	}

	public Dictionary(String did, String dcategory, String dname, Boolean dvalid) {
		this.did = did;
		this.dcategory = dcategory;
		this.dname = dname;
		this.dvalid = dvalid;
	}
	
}