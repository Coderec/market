package com.online.market.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.online.market.model.Goods;
import com.online.market.service.IGoodsManager;

public class AdminGoodsTableAction extends BaseAction {

	private List<Goods> goodses;
	private IGoodsManager goodsManager;
	public List<Goods> getGoodses() {
		return goodses;
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public String goodsTable() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = ServletActionContext.getServletContext().getRealPath("/");
		String gQuery = "GoodsManager.findGoodsByGid.query";
		String gid = request.getParameter("gid");
		String bool = request.getParameter("valid");
		String b = request.getParameter("b");
		if (gid != null) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", gid);
			List<Goods> dgoodses = (List<Goods>) goodsManager
					.findListByDynamicQuery(gQuery, map);
			try {
				Goods tmp = dgoodses.get(0);
				if (b.equals("d")) {
					goodsManager.remove(tmp);
					
				
					try {					
						
						if (tmp.getGimg1() != null){
							File file = new File(path+tmp.getGimg1().substring(7));
							file.delete();
						}if (tmp.getGimg2() != null){
							File file = new File(path+tmp.getGimg2().substring(7));
							file.delete();
						}if (tmp.getGimg3() != null){
							File file = new File(path+tmp.getGimg3().substring(7));
							file.delete();
						}if (tmp.getGimg4() != null){
							File file = new File(path+tmp.getGimg4().substring(7));
							file.delete();
						}if (tmp.getGimg5() != null){
							File file = new File(path+tmp.getGimg5().substring(7));
							file.delete();
						}if (tmp.getGimg6() != null){
							File file = new File(path+tmp.getGimg6().substring(7));
							file.delete();
						}
					} catch (Exception e) {
						System.out.println("删除本地文件错误");
						e.printStackTrace();
					}
					

				}else if(b.equals("v")){
					if(bool.equals("true")){					
						tmp.setGvalid(false);				
					}else{
						tmp.setGvalid(true);
					}
					goodsManager.save(tmp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.goodses = (List<Goods>) goodsManager.findAll();

		request.setAttribute("goodsList", goodses);

		return "success";
	}

	public void setGoodses(List<Goods> goodses) {
		this.goodses = goodses;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

}
