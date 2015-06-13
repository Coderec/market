package com.online.market.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.online.market.common.Constant;
import com.online.market.model.Admin;
import com.online.market.model.User;
import com.online.market.service.IAdminManager;
import com.opensymphony.xwork2.ActionContext;

public class AdminLoginAction extends BaseAction {

	private String adminName;
	private String adminPassword;
	private IAdminManager adminManager;

	public IAdminManager getAdminManager() {
		return adminManager;
	}

	public void setAdminManager(IAdminManager adminManager) {
		this.adminManager = adminManager;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String adminLogin() {

		try {
			String dqQuery = "AdminMsgManager.findAdminName.query";
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(adminName + "...." + adminPassword);
			map.put("name", adminName);
			List<Admin> admins = (List<Admin>) adminManager.findListByDynamicQuery(
					dqQuery, map);
			System.out.println(admins);
			if (admins.get(0).getPassword().equals(adminPassword)) {
				ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_ISLOGINED, "true");
				return "success";
			}else{
				ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_ISLOGINED, "false");
				return "fail";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

}
