package com.online.market.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.online.market.model.Admin;
import com.online.market.model.PwdMsg;
import com.online.market.model.User;
import com.online.market.service.IAdminManager;
import com.online.market.service.IPwdMsgManager;
import com.online.market.service.IUserManager;

public class PwdMsgAction extends BaseAction{
	/*
	 * 用户用来给管理员发找回密码信息
	 * 用户提交用户名与注册邮箱
	 * 管理员将密码重置，发回其邮箱
	 * 
	 * 但是现在并未用到这个功能，都是使用的论坛帐号的找回机制
	 * 如果用到这个功能应该实现自动邮件重置密码功能
	 */
	private static final long serialVersionUID = -4398440934076496953L;
	private String username;
	private String email;
	private IPwdMsgManager pwdmsg;
	private IUserManager userManager;
	private IAdminManager adminManager;

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public IPwdMsgManager getPwdmsg() {
		return pwdmsg;
	}

	public void setPwdmsg(IPwdMsgManager pwdmsg) {
		this.pwdmsg = pwdmsg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public IAdminManager getAdminManager() {
		return adminManager;
	}

	public void setAdminManager(IAdminManager adminManager) {
		this.adminManager = adminManager;
	}

	public String pwdmsg() {
		
		try {
			String adminQuery = "AdminManager.findAdminByAdminName.query";
			Map<String, Object> adMap = new HashMap<String, Object>();
			adMap.put("name", "marketPwdAdmin");
			List<Admin> admin = (List<Admin>) adminManager.findListByDynamicQuery(adminQuery, adMap);
			
			PwdMsg pwdmsg = new PwdMsg();
			String dquser = "UserManager.findEmailAndNameByUName.pwdmsg";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", username);
			map.put("email", email); // 判断。。。。。。。。。。
			List<User> users = (List<User>) userManager.findListByDynamicQuery(
					dquser, map);

			if (!users.isEmpty()) {
				if (users.get(0).getUname().equals(username) && users.get(0).getUemail().equals(email)) {
					pwdmsg.setUname(this.username);
					pwdmsg.setPisNew(true);
					pwdmsg.setAdmin(admin.get(0));
					return "index";
				}

				else 
					return "login";

			} else
				return "login";
		} catch (Exception e) {
			System.out.println("出错！！！！！");
		}
		return null;
	}

}