package com.online.market.action;

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

public class CollectAction extends BaseAction{

	/*
	 * 收藏物品action
	 * 
	 * 属性说明：
	 * id 被收藏物品id
	 * pre 标记，注明是goods还是needs，接受两种值："n"、"g"
	 * collect 标记是否是收藏动作，由jsp设定默认值" "，在点击收藏后由js复制为"1"
	 * delete 标记是否是删除动作，由jsp设定默认值" "，在点击收藏后由js复制为"1"
	 * 
	 */
	private static final long serialVersionUID = -8886323881776847326L;
	private String id;
	private String pre;
	private String collect;
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
	public String getCollect() {
		return collect;
	}
	public void setCollect(String collect) {
		this.collect = collect;
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

	/*
	 * 1.获取当前用户，若没有登录则返回登录页面
	 * 2.根据pre判断是needs还是goods
	 * 3."g".equals(pre)
	 * 		根据当前物品id获取物品，判断是collect还是delete操作
	 * 		"1".equals(collect)
	 * 			在用户表中的gid属性中添加物品id，将物品表中的collectNum属性+1
	 * 			更新session中收藏数等相关信息
	 * 		"1".equals(delete)
	 * 			设置物品为不可用，并设定物品终止时间
	 * 4."n".equals(pre)
	 * 		只有删除一种情况，求购物品不能收藏
	 * 		设置物品为不可用，并设定物品终止时间
	 */
	public String collect(){
		User user = null;
		try {
			user = (User) ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USER);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			return "login";
		}finally{
			if(user==null){
				return "login";
			}
		}
		
		if(pre.isEmpty()){
			return null;
		}
		if("g".equals(pre)){
			String dqQuery = "GoodsManager.findGoodsByGid.query";
			Map<String,Object>map = new HashMap<String,Object>();
			map.put("id", id);
			List<Goods>goodses=(List<Goods>)goodsManager.findListByDynamicQuery(dqQuery, map);
			
			
			
			if("1".equals(collect)){
				String dqQuery1 = "UserManager.findUserByUid.query";
				Map<String,Object>map1 = new HashMap<String,Object>();
				map1.put("id", user.getUid());
				List<User> users = (List<User>)userManager.findListByDynamicQuery(dqQuery1, map1);
				if(!users.isEmpty()){
					String gids="";
					if(""==users.get(0).getUgids()||users.get(0).getUgids()==null||users.get(0).getUgids().isEmpty()){
						gids= id;
					}else{
						gids= users.get(0).getUgids()+","+id;
					}
//				System.out.println(gids);
					users.get(0).setUgids(gids);
					userManager.save(users.get(0));
					
					if(!goodses.isEmpty()){
						goodses.get(0).setGcollectNum(goodses.get(0).getGcollectNum()+1);
						goodsManager.save(goodses.get(0));
					}
					ActionContext.getContext().getSession().put(Constant.KEY_LOGIN_USERCOLLECT, 
							(Integer) ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USERCOLLECT)+1);
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
					needsManager.save(needses.get(0));
				}
			}
		}
		
		return "toItemDetailsAction";
	}
}
