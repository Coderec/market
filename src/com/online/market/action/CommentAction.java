package com.online.market.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.online.market.common.Constant;
import com.online.market.model.GComment;
import com.online.market.model.Goods;
import com.online.market.model.NComment;
import com.online.market.model.Needs;
import com.online.market.model.User;
import com.online.market.service.IGComentManager;
import com.online.market.service.IGoodsManager;
import com.online.market.service.INCommentManager;
import com.online.market.service.INeedsManager;
import com.online.market.service.IUserManager;
import com.opensymphony.xwork2.ActionContext;

public class CommentAction extends BaseAction{

	/*
	 * 评论功能
	 * 
	 * 属性说明：
	 * id 物品id
	 * pre 区别needs还是goods
	 * msg 评论内容
	 * 
	 */
	private static final long serialVersionUID = 1774743368428530252L;
	private String pre;
	private String id;
	private String msg;
	private IGoodsManager goodsManager;
	private INeedsManager needsManager;
	private IGComentManager gCommentManager;
	private INCommentManager nCommentManager;
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
	
	/*
	 * 1.获得当前用户
	 * 2.若是goods
	 * 根据id查出goods，设置评论数+1
	 * 新建一行评论数据在goods评论表中
	 * 3.若是needs
	 * 根据id查出needs，设置评论数+1
	 * 新建一行评论数据在needs评论表中
	 */
	public String comment(){
		try {
			User user=(User) ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USER);
			if(user==null){
				return "login";
			}
			if("g".equals(pre)){
				String dqQuery="GoodsManager.findGoodsByGid.query";
				Map<String,Object>map = new HashMap<String,Object>();
				map.put("id", id);
				List<Goods> goodses=(List<Goods>)goodsManager.findListByDynamicQuery(dqQuery,map);
				goodses.get(0).setGcommentNum(goodses.get(0).getGcommentNum()+1);
				goodsManager.save(goodses.get(0));
				
				GComment gComment = new GComment();
				gComment.setGcMsg(msg);
				gComment.setGoods(goodses.get(0));
				gComment.setUid(user.getUid());
				gComment.setGcIsNew(true);
				gCommentManager.save(gComment);
			}
			if("n".equals(pre)){
				String dqQuery="NeedsManager.findNeedsByNid.query";
				Map<String,Object>map = new HashMap<String,Object>();
				map.put("id", id);
				List<Needs> needses=(List<Needs>)needsManager.findListByDynamicQuery(dqQuery,map);
				needses.get(0).setNcommentNum(needses.get(0).getNcommentNum()+1);
				
				NComment nComment = new NComment();
				nComment.setNcMsg(msg);
				nComment.setNeeds(needses.get(0));
				nComment.setUid(user.getUid());
				nComment.setNcIsNew(true);
				nCommentManager.save(nComment);
			}
			
//			System.out.println(pre);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "toItemDetailsAction";
	}
}
