package com.online.market.action;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport{

	private static final long serialVersionUID = -5664992912624944881L;
	public String message;
	public String errorMsg;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
	public Object toLoginPage(){
		return "login.jsp";
	}
}
