package com.online.market.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.onlineframework.commons.util.MD5Util;

import com.online.market.model.Admin;
import com.online.market.model.AdminMsg;
import com.online.market.model.Goods;
import com.online.market.model.User;
import com.online.market.service.IAdminManager;
import com.online.market.service.IAdminMsgManager;
import com.online.market.service.IUserManager;

public class AdminUserTableAction extends BaseAction {

	private IAdminManager adminManager;
	private IUserManager userManager;

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public IAdminManager getAdminManager() {
		return adminManager;
	}

	public void setAdminManager(IAdminManager adminManager) {
		this.adminManager = adminManager;
	}

	public String userTable() {

		HttpServletRequest request = ServletActionContext.getRequest();
		List<Admin> admins = (List<Admin>) adminManager.findAll();
		List<User> users = (List<User>) userManager.findAll();

		request.setAttribute("adminList", admins);
		request.setAttribute("userList", users);

		return "success";
	}

	public String deleteUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Admin> admins = (List<Admin>) adminManager.findAll();
		List<User> users = (List<User>) userManager.findAll();

		

		request.setAttribute("adminList", admins);
		request.setAttribute("userList", users);

		String uid = request.getParameter("uid");

		String uQuery = "UserManager.findUserByUid.query";
		if (uid != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", uid);
			List<User> utemp = (List<User>) userManager.findListByDynamicQuery(
					uQuery, map);
			try {
				User tmp = utemp.get(0);
				userManager.remove(tmp);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.out.println(uid);

		return "deleteUser";
	}

	public String changePwd(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Admin> admins = (List<Admin>) adminManager.findAll();
		List<User> users = (List<User>) userManager.findAll();

		request.setAttribute("adminList", admins);
		request.setAttribute("userList", users);
		String uid = request.getParameter("uid");
		String newPwd = request.getParameter("newPwd");

		String uQuery = "UserManager.findUserByUid.query";
		if (uid != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", uid);
			List<User> utemp = (List<User>) userManager.findListByDynamicQuery(
					uQuery, map);
			try {
				User tmp = utemp.get(0);
				System.out.println(tmp.getUname());
				tmp.setUpwd(MD5Util.getMD5(newPwd));
				userManager.save(tmp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		return "changePwd";
	}
}
