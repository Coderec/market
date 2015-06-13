package com.online.market.action;

import com.online.market.common.Constant;
import com.opensymphony.xwork2.ActionContext;

public class ExitAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 304416091192088332L;
	
	public String exit(){
		ActionContext.getContext().getSession().remove(Constant.KEY_LOGIN_USER);
		ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_ISLOGINED, "false");
		ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_USERMSG, 0);
		ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_USERCOLLECT, 0);
		return "index";
	}
}
