package com.online.market.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.online.market.model.Goods;
import com.online.market.service.IGoodsManager;

public class GoodsAction extends BaseAction {

	private static final long serialVersionUID = 171676246561545791L;
	private IGoodsManager goodsManager;
	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}
	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}
	
	public String getList(){
		List<Goods> goodses=goodsManager.findAll();
		for(Goods goods:goodses){
			System.out.println(goods.getGname());
		}
		return null;
	}
}
