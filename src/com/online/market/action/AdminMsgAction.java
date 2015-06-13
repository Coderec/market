package com.online.market.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.online.market.model.AdminMsg;
import com.online.market.model.Goods;
import com.online.market.service.IAdminMsgManager;

public class AdminMsgAction extends BaseAction{

	private IAdminMsgManager adminMsgManager;
	
	
	public IAdminMsgManager getAdminMsgManager() {
		return adminMsgManager;
	}


	public void setAdminMsgManager(IAdminMsgManager adminMsgManager) {
		this.adminMsgManager = adminMsgManager;
	}


	public String message(){
		
		System.out.println("adsfas");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		List<AdminMsg> adminMsgs = (List<AdminMsg>) adminMsgManager.findAll();
		request.setAttribute("adminMsgs",adminMsgs);
		
		return "success";
	}
	
}
