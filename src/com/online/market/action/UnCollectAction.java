package com.online.market.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.online.market.common.Constant;
import com.online.market.model.Goods;
import com.online.market.model.Needs;
import com.online.market.model.User;
import com.online.market.service.IGoodsManager;
import com.online.market.service.INeedsManager;
import com.online.market.service.IUserManager;
import com.opensymphony.xwork2.ActionContext;

public class UnCollectAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9195769174064426442L;
	private String id;
	private String pre;
	private String unCollect;
	private String delete;
	private IUserManager userManager;
	private IGoodsManager goodsManager;
	private INeedsManager needsManager;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPre() {
		return pre;
	}
	public void setPre(String pre) {
		this.pre = pre;
	}
	public String getUnCollect() {
		return unCollect;
	}
	public void setUnCollect(String unCollect) {
		this.unCollect = unCollect;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
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
	public String unCollect(){
		try {
			User user;
			try {
				user = (User) ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USER);
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				return "login";
			}
			
			if(pre.isEmpty()){
				return null;
			}
			if("g".equals(pre)){
				String dqQuery = "GoodsManager.findGoodsByGid.query";
				Map<String,Object>map = new HashMap<String,Object>();
				map.put("id", id);
				List<Goods>goodses=(List<Goods>)goodsManager.findListByDynamicQuery(dqQuery, map);
				
				
				
				if("1".equals(unCollect)){
					String dqQuery1 = "UserManager.findUserByUid.query";
					Map<String,Object>map1 = new HashMap<String,Object>();
					map1.put("id", user.getUid());
					List<User> users = (List<User>)userManager.findListByDynamicQuery(dqQuery1, map1);
					if(!users.isEmpty()){
						List<String> gids= Arrays.asList(users.get(0).getUgids().split(","));
						
						if(gids!=null && !gids.isEmpty()){
							String str=null;
							for(String gid:gids){
								if(!gid.equals(id)){
									if(str==null || ""==str||str.isEmpty()){
										str= gid;
									}else{
										str+= (","+gid);
									}
								}
							}
							users.get(0).setUgids(str);
						}else{
							users.get(0).setUgids("");
						}
						
						userManager.save(users.get(0));
						
						if(!goodses.isEmpty()){
							goodses.get(0).setGcollectNum(goodses.get(0).getGcollectNum()-1);
							goodsManager.save(goodses.get(0));
						}
						ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_USERCOLLECT, 
								(Integer) ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USERCOLLECT)-1);
						ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_USER, users.get(0));
					}
				}
				if("1".equals(delete)){
					if(!goodses.isEmpty()){
						goodses.get(0).setGvalid(false);
						goodses.get(0).setGendTime(new Date());
						goodsManager.save(goodses.get(0));
					}
				}
			}
			
			if("n".equals(pre)){
				if("1".equals(delete)){
					String dqQuery = "NeedsManager.findNeedsByNid.query";
					Map<String,Object>map = new HashMap<String,Object>();
					map.put("id", id);
					List<Needs>needses=(List<Needs>)needsManager.findListByDynamicQuery(dqQuery, map);
					if(!needses.isEmpty()){
						needses.get(0).setNvalid(false);
						needses.get(0).setNendTime(new Date());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "toItemDetailsAction";
	}
}
