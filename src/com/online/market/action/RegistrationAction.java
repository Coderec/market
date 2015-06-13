package com.online.market.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.onlineframework.commons.util.MD5Util;

import com.fivestars.interfaces.bbs.client.Client;
import com.online.market.common.Constant;
import com.online.market.model.User;
import com.online.market.service.IUserManager;
import com.opensymphony.xwork2.ActionContext;

public class RegistrationAction extends BaseAction {

	/*
	 * 属性说明：
	 * account 用户名
	 * pwd 密码
	 * pwd1 确认密码
	 * email 邮箱
	 * 
	 */
	private static final long serialVersionUID = 1568843859113882427L;
	private String account;
	private String pwd;
	private String pwd1;
	private String email;
	private IUserManager userManager;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwd1() {
		return pwd1;
	}
	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public IUserManager getUserManager() {
		return userManager;
	}
	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}
	
	/*
	 * 使用UCentre系统注册用户，如果注册失败报错，如果成功就同时存入跳蚤市场数据库，然后更新session 
	 */
	public String registration(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			if(pwd.equals(pwd1)){
				Client uc = new Client();

				//setcookie('Example_auth', '', -86400);
//			生成同步退出的代码
				String $returns = uc.uc_user_register(account, pwd ,email );
				int $uid = Integer.parseInt($returns);
				if($uid <= 0) {
					if($uid == -1) {
						request.setAttribute("errorMsg", "用户名不合法");
						return "registration";
					} else if($uid == -2) {
						request.setAttribute("errorMsg", "包含不允许注册的词语");
						return "registration";
					} else if($uid == -3) {
						request.setAttribute("errorMsg", "用户名已经存在");
						return "registration";
					} else if($uid == -4) {
						request.setAttribute("errorMsg", "Email 格式有误");
						return "registration";
					} else if($uid == -5) {
						request.setAttribute("errorMsg", "Email 不允许注册");
						return "registration";
					} else if($uid == -6) {
						request.setAttribute("errorMsg", "该 Email 已经被注册");
						return "registration";
					} else {
						request.setAttribute("errorMsg", "未知错误");
						return "registration";
					}
				} else {
//				System.out.println("OK:"+$returns);
					User user=new User();
					user.setUltUid($uid);
					user.setUname(account);
					user.setUemail(email);
					user.setUpwd(MD5Util.getMD5(pwd));
					user.setUgids("");
					user.setUvalid(true);
					userManager.save(user);
					ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_ISLOGINED, "true");
					ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_USER, user);
					ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_USERMSG, 0);
					ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_USERCOLLECT, 0);
					return "index";
				}
			}else{
				System.out.println("两次密码不同");
				return "registration";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}return null;
		
		
	}

}
