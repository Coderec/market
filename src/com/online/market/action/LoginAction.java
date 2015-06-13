package com.online.market.action;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fivestars.interfaces.bbs.client.Client;
import com.fivestars.interfaces.bbs.util.XMLHelper;
import com.online.market.common.*;

import org.apache.struts2.ServletActionContext;
import org.onlineframework.commons.util.MD5Util;

import com.online.market.model.Goods;
import com.online.market.model.User;
import com.online.market.model.UserMsg;
import com.online.market.service.IGoodsManager;
import com.online.market.service.IUserManager;
import com.online.market.service.IUserMsgManager;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8294826384565516781L;
	private String account;
	private String pwd;
	private IUserManager userManager;
	private IUserMsgManager userMsgManager;
	private IGoodsManager goodsManager;

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

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager usermanager) {
		this.userManager = usermanager;
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	/*
	 * 使用UCentre系统登录，如果失败返回重新登录页，
	 * 如果成功
	 * 		如果本地数据库存在用户
	 * 			查出收藏品个数i
	 * 			查询评论数j
	 * 			更新session
	 * 		如果本地数据库不存在用户
	 * 			存入数据库
	 * 			设置评论数、收藏数为0
	 * 			更新session
	 */
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
			String c_checkcode = request.getParameter("checkcode");
			String s_checkcode = (String) request.getSession().getAttribute(
					"imagecheckcode");
			if (c_checkcode != null && s_checkcode != null
					&& c_checkcode.equals(s_checkcode)) {
//				System.out.println("cheng");
				Client e = new Client();
				String result = e.uc_user_login(account, pwd);
				// String result = e.uc_user_login("next", "171120");
				LinkedList<String> rs = XMLHelper.uc_unserialize(result);
				// System.out.println(Charset.defaultCharset().name());
				if (rs.size() > 0) {
					int $uid = Integer.parseInt(rs.get(0));
					String $username = rs.get(1);
					String $password = rs.get(2);
					String $email = rs.get(3);
					if ($uid > 0) {
						// System.out.println("登录成功");
						// System.out.println($username);
						// System.out.println($password);
						// System.out.println($email);
						//
						// String $ucsynlogin = e.uc_user_synlogin($uid);
						// System.out.println("登录成功"+$ucsynlogin);

						// 本地登陆代码
						// TODO ... ....
						String dqQuery = "UserManager.findUserByUltUid.query";
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("ultUid", $uid);
						List<User> users = (List<User>) userManager
								.findListByDynamicQuery(dqQuery, map);
						if (users != null && !users.isEmpty()) {
							if (!users.get(0).getUvalid()) {
								return "login";
							}

							// 查出收藏品个数i
							int i = 0;
							if (users.get(0).getUgids() != null
									&& !users.get(0).getUgids().isEmpty()) {
								List<String> gids = Arrays.asList(users.get(0)
										.getUgids().split(","));
								for (String gid : gids) {
									++i;
								}
							}

							// 查询评论数
							String commentQuery = "GoodsManager.findValidGoodsByUidDesc.query";
							Map<String, Object> params = new HashMap<String, Object>();
							params.put("id", users.get(0).getUid());
							int j = 0;
							try {
								List<Goods> goodses = (List<Goods>) goodsManager
										.findListByDynamicQuery(commentQuery,
												params);
								for (Goods goods : goodses) {
									j = j + goods.getGcommentNum();
								}
							} catch (NullPointerException r) {
								System.out.println("UserMsg查询为空");
							}
							// 写入session
							ActionContext.getContext().getSession()
									.put(Constant.KEY_LOGIN_ISLOGINED, "true");
							ActionContext.getContext().getSession()
									.put(Constant.KEY_LOGIN_USER, users.get(0));
							ActionContext.getContext().getSession()
									.put(Constant.KEY_LOGIN_USERMSG, j);
							ActionContext.getContext().getSession()
									.put(Constant.KEY_LOGIN_USERCOLLECT, i);

						} else {
							User user = new User();
							user.setUname($username);
							user.setUemail($email);
							user.setUltUid($uid);
							user.setUpwd(MD5Util.getMD5($password));
							user.setUgids("");
							user.setUvalid(true);
							userManager.save(user);
							ActionContext.getContext().getSession()
									.put(Constant.KEY_LOGIN_ISLOGINED, "true");
							ActionContext.getContext().getSession()
									.put(Constant.KEY_LOGIN_USER, user);
							ActionContext.getContext().getSession()
									.put(Constant.KEY_LOGIN_USERMSG, 0);
							ActionContext.getContext().getSession()
									.put(Constant.KEY_LOGIN_USERCOLLECT, 0);
						}
						return "index";

					} else if ($uid == -1) {
						request.setAttribute("errorMsg", "用户不存在,或者被删除");
						return "login";
					} else if ($uid == -2) {
						request.setAttribute("errorMsg", "密码错误");
						return "login";
					} else {
						request.setAttribute("errorMsg", "发生未知错误");
						return "login";
					}
				} else {
					System.out.println("Login failed");
					System.out.println(result);
					return "login";
				}
			} else {
//				System.out.println("shibai");
				return "login";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
