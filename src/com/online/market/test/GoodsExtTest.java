package com.online.market.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.onlineframework.commons.IPage;
import org.onlineframework.commons.jdbc.IRowHandler;

import com.online.market.model.Goods;
import com.online.market.model.User;
import com.online.market.service.ext.IGoodsManagerExt;

public class GoodsExtTest extends SpringUtils {
	public IGoodsManagerExt goodsManagerExt=(IGoodsManagerExt)applicationContext.getBean("goodsManagerExt");
//	@Test
//	public void addGoodsBySql(){
//		goodsManagerExt.addGoods();
//	}
//	@Test
//	public void updateGoodsBySql(){
//		goodsManagerExt.updateGoods();
//	}
//	@Test
//	public void findGoodsByContent(){
//		IPage<Goods> goodses=goodsManagerExt.findGoodsByContent();
//		System.out.println(goodses.getTotalCount());
//		for(Goods goods:goodses.getPageElements()){
//			System.out.println(goods.getGid());
//		}
//	}
//	
//	@Test
//	public void findAllGoods(){
//		IPage<Goods> goodses=goodsManagerExt.findAllGoods();
//		System.out.println(goodses.getTotalCount());
//		for(Goods goods:goodses.getPageElements()){
//			System.out.println(goods.getGid());
//		}
//	}
//	@Test
//	public void deleteGoodsById(){
//		goodsManagerExt.deleteGoodsByContent();
//	}
	@Test
	public void findGoodsByUserId(){
	Map<String,Object> params=new HashMap<String, Object>();
	params.put("name","m");
	IRowHandler<Goods> rh=new IRowHandler<Goods>() {

		@Override
		public Goods handleRow(ResultSet rs) {
			try {
				Goods goods=new Goods();
//				goods.setGid(rs.getString("gd.g_id"));
//				goods.setGdegree(rs.getString("gd.g_degree"));
				goods.setGname(rs.getString("gname"));
//				goods.setGimg1(rs.getString("gimg1"));
				return goods;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}
	};


	try {
		IPage<Goods>goodses=goodsManagerExt.findPage("GoodsManager.findGoodsByContainName.query", "GoodsManager.findGoodsByContainName.count",params, 1, 2, rh);;
//		System.out.println(goodses.getTotalCount());
		for(Goods goods:goodses.getPageElements()){
			System.out.println(goods.getGname());
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//		IPage<Goods>goodses=goodsManagerExt.findGoodsByUserId();
//		System.out.println(goodses.getTotalCount());
//		for(Goods goods:goodses.getPageElements()){
//			System.out.println(goods.getGname()+":"+goods.getUser().getUid());
//		}
	}
}
