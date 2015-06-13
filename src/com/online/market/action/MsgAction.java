package com.online.market.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.online.market.common.Constant;
import com.online.market.model.Goods;
import com.online.market.model.Needs;
import com.online.market.model.User;
import com.online.market.model.UserMsg;
import com.online.market.service.IGoodsManager;
import com.online.market.service.INeedsManager;
import com.online.market.service.IUserManager;
import com.online.market.service.IUserMsgManager;
import com.opensymphony.xwork2.ActionContext;

public class MsgAction extends BaseAction{

	/**
	 * 用于用户之间发送消息，但是1.2版没有用到这个功能，也就是说这个action从未被执行。
	 */
	private static final long serialVersionUID = -3868656860407759353L;
	private String pre;
	private String id;
	private String msg;
	private IUserManager userManager;
	private IGoodsManager goodsManager;
	private INeedsManager needsManager;
	private IUserMsgManager userMsgManager;
	public String getPre() {
		return pre;
	}
	public void setPre(String pre) {
		this.pre = pre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public INeedsManager getNeedsManager() {
		return needsManager;
	}
	public void setNeedsManager(INeedsManager needsManager) {
		this.needsManager = needsManager;
	}
	public IUserMsgManager getUserMsgManager() {
		return userMsgManager;
	}
	public void setUserMsgManager(IUserMsgManager userMsgManager) {
		this.userMsgManager = userMsgManager;
	}
	
	public String msg(){
		User user=(User) ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USER);
		if(user.equals(null)){
			return "login";
		}
		if("g".equals(pre)){
			String dqQuery="GoodsManager.findGoodsByGid.query";
			Map<String,Object>map = new HashMap<String,Object>();
			map.put("id", id);
			List<Goods> goodses=(List<Goods>)goodsManager.findListByDynamicQuery(dqQuery,map);
			UserMsg userMsg = new UserMsg();
			userMsg.setSendId(user.getUid());
			userMsg.setUmMsg(msg);
			userMsg.setUmIsNew(true);
			userMsg.setUser(goodses.get(0).getUser());
			userMsgManager.save(userMsg);
		}
		if("n".equals(pre)){
			String dqQuery="NeedsManager.findNeedsByNid.query";
			Map<String,Object>map = new HashMap<String,Object>();
			map.put("id", id);
			List<Needs> needses=(List<Needs>)needsManager.findListByDynamicQuery(dqQuery,map);
			UserMsg userMsg = new UserMsg();
			userMsg.setSendId(user.getUid());
			userMsg.setUmMsg(msg);
			userMsg.setUmIsNew(true);
			userMsg.setUser(needses.get(0).getUser());
			userMsgManager.save(userMsg);
		}
		
		return "toItemDetailsAction";
	}
}
