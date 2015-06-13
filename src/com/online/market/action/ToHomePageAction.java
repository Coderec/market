package com.online.market.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.online.market.common.Constant;
import com.online.market.model.AdminMsg;
import com.online.market.model.GComment;
import com.online.market.model.Goods;
import com.online.market.model.Needs;
import com.online.market.model.User;
import com.online.market.model.UserMsg;
import com.online.market.service.IAdminMsgManager;
import com.online.market.service.IGComentManager;
import com.online.market.service.IGoodsManager;
import com.online.market.service.INCommentManager;
import com.online.market.service.INeedsManager;
import com.online.market.service.IUserManager;
import com.online.market.service.IUserMsgManager;
import com.opensymphony.xwork2.ActionContext;

public class ToHomePageAction extends BaseAction{

	/*
	 * 进入个人主页之前的操作action
	 * host 用户名
	 */
	private static final long serialVersionUID = 6332301576459186648L;
	private	String host;
	private IGoodsManager goodsManager;
	private INeedsManager needsManager;
	private IUserManager userManager;
	private IGComentManager gCommentManager;
	private INCommentManager nCommentManager;
	private IUserMsgManager userMsgManager;
	private IAdminMsgManager adminMsgManager;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
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
	public IUserManager getUserManager() {
		return userManager;
	}
	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}
	public IGComentManager getgCommentManager() {
		return gCommentManager;
	}
	public void setgCommentManager(IGComentManager gCommentManager) {
		this.gCommentManager = gCommentManager;
	}
	public INCommentManager getnCommentManager() {
		return nCommentManager;
	}
	public void setnCommentManager(INCommentManager nCommentManager) {
		this.nCommentManager = nCommentManager;
	}
	public IUserMsgManager getUserMsgManager() {
		return userMsgManager;
	}
	public void setUserMsgManager(IUserMsgManager userMsgManager) {
		this.userMsgManager = userMsgManager;
	}
	public IAdminMsgManager getAdminMsgManager() {
		return adminMsgManager;
	}
	public void setAdminMsgManager(IAdminMsgManager adminMsgManager) {
		this.adminMsgManager = adminMsgManager;
	}
	
	public String toHomePage(){
		//request  :  gidsList  goodsList  needsList  userMsgList userMsgForNameUserList(用于获取userMsg来源用户的名字而不是id)  adminMsgList
		//gCommentList/gCommetnGoodsList/gCommentUsersList	gCommentGoodsList用来存放评论是属于哪个商品的	gCommentGoodsList用来存放评论是属于哪个用户的	
		
		//update UserMsgNum
		Object tempSessionUser = ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USER);
		if(tempSessionUser!=null){
			String commentQuery = "GoodsManager.findValidGoodsByUidDesc.query";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id",
					((User) ActionContext.getContext().getSession()
							.get(Constant.KEY_LOGIN_USER)).getUid());
			int j = 0;
			try {
				List<Goods> goodses = (List<Goods>) goodsManager
						.findListByDynamicQuery(commentQuery, params);
				for (Goods goods : goodses) {
					j = j + goods.getGcommentNum();
				}
			} catch (NullPointerException r) {
				System.out.println("toHomepageAction,msg查询为空");
			}
			ActionContext.getContext().getSession()
					.put(Constant.KEY_LOGIN_USERMSG, j);
		}
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String hostId=null;
			//验证是否存在user
			String collectQuery = "UserManager.findUserByUname.query";
			Map<String,Object> cltMap = new HashMap<String,Object>();
			cltMap.put("name", host);
			List<User> users = (List<User>)userManager.findListByDynamicQuery(collectQuery,cltMap);
			
			//如果user不存在返回null，否则继续查询
			if(users.isEmpty()){
				return null;
			}else{

				hostId = users.get(0).getUid();
				Map<String,Object> uidMap = new HashMap<String,Object>();
				uidMap.put("id", hostId);
				
				//查找到收藏的宝贝
				if(users.get(0).getUgids()!=null && !users.get(0).getUgids().isEmpty()){
					List<Goods> goodses = new ArrayList<Goods>();
					List<String> gids = Arrays.asList(users.get(0).getUgids().split(","));
					for(String gid:gids){
						String gidQuery = "GoodsManager.findGoodsByGid.query";
						Map<String,Object> gidMap = new HashMap<String,Object>();
						gidMap.put("id", gid);
						List<Goods> goods = (List<Goods>)goodsManager.findListByDynamicQuery(gidQuery,gidMap);
						goodses.add(goods.get(0));
					}
					request.setAttribute("gidsList", goodses);
				}
				
				//查找到出售物品
				String goodsQuery = "GoodsManager.findValidGoodsByUidDesc.query";
				List<Goods> goodses = (List<Goods>)goodsManager.findListByDynamicQuery(goodsQuery,uidMap);
				if(!goodses.isEmpty()){
					request.setAttribute("goodsList", goodses);
				}
				
				//查询求购物品
				String needsQuery = "NeedsManager.findNeedsByUid.query";
				List<Needs> needses = (List<Needs>)needsManager.findListByDynamicQuery(needsQuery,uidMap);
				if(!needses.isEmpty()){
					request.setAttribute("needsList", needses);
				}
				
				if((User) ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USER)!=null){
					if(host.equals(((User) ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USER)).getUname())){
//						//查询用户间留言
//						try {
//							
//							//uids用于获取userMsg来源用户的名字而不是id
//							List<User> uids = new ArrayList<User>();
//							String msgQuery = "UserMsgManager.findUserMsgByReceivedUid.query";
//							List<UserMsg> msgs = (List<UserMsg>)userMsgManager.findListByDynamicQuery(msgQuery,uidMap);
//							if(!msgs.isEmpty()){
//								for(UserMsg msg:msgs){
//									String str = "UserManager.findUserByUid.query";
//									Map<String,Object> tempMap = new HashMap<String,Object>();
//									tempMap.put("id", msg.getSendId());
//									List<User> tempUser = (List<User>)userManager.findListByDynamicQuery(str, tempMap);
//									uids.add(tempUser.get(0));
//								}
//								request.setAttribute("userMsgForNameUserList", uids);
//								request.setAttribute("userMsgList", msgs);
//							}
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
////						e.printStackTrace();
//						}
						
						//查询管理员留言
						String adMsgQuery = "AdminMsgManager.findAdminMsgByUid.query";
						List<AdminMsg> adMsgs = (List<AdminMsg>)adminMsgManager.findListByDynamicQuery(adMsgQuery,uidMap);
						if(!adMsgs.isEmpty()){
							request.setAttribute("adminMsgList", adMsgs);
						}
						
						//查询评论
						if(!goodses.isEmpty()){
							List<GComment> gCommentList = new ArrayList<GComment>();
							// gCommentGoodsList用来存放评论是属于哪个商品的
							List<Goods> gCommentGoodsList = new ArrayList<Goods>();
							List<User> gCommentUsersList = new ArrayList<User>();
							String gCommentUserQuery = "UserManager.findUserByUid.query";
							Map<String,Object> gCommentUserMap = new HashMap<String,Object>();
							for(Goods goods:goodses){
								
								String gCommentQuery = "GCommentManager.findGCommentByGidDesc.query";
								Map<String,Object> gCommentMap = new HashMap<String,Object>();
								gCommentMap.put("id", goods.getGid());
								List<GComment> gComments=gCommentManager.findListByDynamicQuery(gCommentQuery, gCommentMap);
								
								if(gComments!=null && !gComments.isEmpty()){
									for(GComment gComment:gComments){
										gCommentUserMap.put("id", gComment.getUid());
										List<User> gCommentUser = userManager.findListByDynamicQuery(gCommentUserQuery, gCommentUserMap);
										gCommentList.add(gComment);
										gCommentGoodsList.add(goods);
										gCommentUsersList.add(gCommentUser.get(0));
									}
								}
							}
							request.setAttribute("gCommentList", gCommentList);
							request.setAttribute("gCommentGoodsList", gCommentGoodsList);
							request.setAttribute("gCommentUsersList", gCommentUsersList);
						}
						
						request.setAttribute("isMe", true);
					}
				}
				
				
				//写入request
				return "homepage";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	return null;
		}		
}
