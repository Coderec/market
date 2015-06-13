package com.online.market.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.online.market.model.AdminMsg;
import com.online.market.model.Goods;
import com.online.market.model.User;
import com.online.market.service.IAdminMsgManager;
import com.online.market.service.IGoodsManager;
import com.online.market.service.IUserManager;

public class AdminIndexAction extends BaseAction{

	private IUserManager userManager;
	private IGoodsManager goodsManager;
	private IAdminMsgManager adminMsgManager;
	
	
	public IAdminMsgManager getAdminMsgManager() {
		return adminMsgManager;
	}
	public void setAdminMsgManager(IAdminMsgManager adminMsgManager) {
		this.adminMsgManager = adminMsgManager;
	}
	public IUserManager getUserManager() {
		return userManager;
	}
	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}
	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}
	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}



	public String adminIndex(){
		
		try {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			List<User> users = (List<User>) userManager.findAll();
			List<Goods> goodses = (List<Goods>) goodsManager.findAll();
			List<AdminMsg> adminMsgs = (List<AdminMsg>) adminMsgManager.findAll();
			request.setAttribute("userCount", users.size());
			request.setAttribute("goodsCount", goodses.size());
			request.setAttribute("adminMsgCount", adminMsgs.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
}
