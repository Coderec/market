package com.online.market.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.online.market.model.GComment;
import com.online.market.model.Goods;
import com.online.market.service.IGComentManager;
import com.online.market.service.IGoodsManager;

public class AdminGoodsDetailAction extends BaseAction{
	private IGComentManager gCommentManager;
	private List<GComment> gcomments;
	private Goods goods;
	private IGoodsManager goodsManager;


	public IGComentManager getgCommentManager() {
		return gCommentManager;
	}


	public List<GComment> getGcomments() {
		return gcomments;
	}


	public Goods getGoods() {
		return goods;
	}
	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}



	public void setgCommentManager(IGComentManager gCommentManager) {
		this.gCommentManager = gCommentManager;
	}


	public void setGcomments(List<GComment> gcomments) {
		this.gcomments = gcomments;
	}


	public void setGoods(Goods goods) {
		this.goods = goods;
	}


	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}
	
	
	public String goodsDetails(){
		
		System.out.println("aaaa");
		HttpServletRequest request = ServletActionContext.getRequest();
		String gcQuery = "GCommentManager.findGCommentByGid.query";
		String gQuery = "GoodsManager.findGoodsByGid.query";
		String gid = request.getParameter("gid");
		
		if (gid != null) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", gid);
			List<Goods> dgoodses = (List<Goods>) goodsManager
					.findListByDynamicQuery(gQuery, map);
			
			this.goods = dgoodses.get(0);
			this.gcomments = (List<GComment>) gCommentManager
					.findListByDynamicQuery(gcQuery, map);		
		}
		
		
		
		return "success";
	}
	
	public String deleteGComment(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String gcQuery = "GCommentManager.findGCommentByGcid.query";
		String gcId = request.getParameter("gcId");

		if (gcId != null) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", gcId);

			try {
				List<GComment> comments = (List<GComment>) gCommentManager
						.findListByDynamicQuery(gcQuery, map);	
				GComment comment = comments.get(0);
				Goods good = comment.getGoods();
				int commentNum = good.getGcommentNum();
				commentNum -= 1;
				good.setGcollectNum(commentNum);
				goodsManager.save(good);
				( ((GComment) comment).getGoods()).setGcommentNum((((GComment) comment).getGoods()).getGcommentNum()-1);
				gCommentManager.remove(comment);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			
			return "delete";
		}
		
		return null;
	}
	
}
