package com.online.market.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.online.market.model.User;
import com.online.market.service.IUserManager;

public class AdminDetailAction extends BaseAction{
	
	private IUserManager userManager;

	
	public IUserManager getUserManager() {
		return userManager;
	}


	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}


	public String detail(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String uid = request.getParameter("uid");
		
		String uQuery = "UserManager.findUserByUid.query";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", uid);
		List<User> utemp = (List<User>) userManager
				.findListByDynamicQuery(uQuery, map);
		request.setAttribute("user", utemp.get(0));
		
		return "success";
	}
	
}
