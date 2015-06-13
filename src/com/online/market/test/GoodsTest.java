package com.online.market.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.onlineframework.commons.IPage;

import com.online.market.model.Goods;
import com.online.market.model.Needs;
import com.online.market.model.User;
import com.online.market.service.IGoodsManager;
import com.online.market.service.INeedsManager;
import com.online.market.service.IUserManager;

public class GoodsTest extends SpringUtils {
	public IGoodsManager goodsManager=(IGoodsManager)applicationContext.getBean("goodsManager");
	public INeedsManager needsManager=(INeedsManager)applicationContext.getBean("needsManager");
	public IUserManager userManager=(IUserManager)applicationContext.getBean("userManager");
//	@Test  
//	public void save(){
//		User user=new User();
//		user.setUname("wangwu");
//		user.setUphone("15262003545");
//		user.setUpwd(MD5Util.getMD5("12345"));
//		user.setUpwd("547800048@qq.com");
//		
//		Goods goods=new Goods();
//		goods.setUser(user);
//		goods.setGname("手机");
//		goods.setGdegree("八成新");
//		goods.setGstartTime(new Date(1000000));
//		goods.setGendTime(new Date());
//		
//		GComment comment=new GComment();
//		comment.setGcIsNew(true);
//		comment.setGcMsg("我需要该商品");
//		Set<GComment>comments=new HashSet<GComment>(0);
//		comments.add(comment);
//		goods.setGcomments(comments);
//		goodsManager.save(goods);
//	}
//	@Test
//	public void delete(){
//		goodsManager.removeById("2c9292e2455421d201455421d6a40001");
//	}
//	@Test
//	public void hqlTestNotParam(){
//		String dqQuery="GoodsManager.findAllGoods.query";
//		String dqCount="GoodsManager.findAllGoods.count";
//		
//		IPage<Goods> goods=goodsManager.findByDynamicQuery(dqQuery, dqCount, 1, 10);
//		System.out.println(goods.getTotalCount());
//	}
//	@Test
//	public void hqlTestHasParamToMap(){
//		String dqQuery="GoodsManager.findAllGoodsByParam.query";
//		String dqCount="GoodsManager.findAllGoodsByParam.count";
//		Map<String,Object>map=new HashMap<String,Object>();
//		map.put("name","手机");
//		IPage<Goods> goods=goodsManager.findByDynamicQuery(dqQuery, dqCount,map,1,2);
//		System.out.println(goods.getTotalCount()+"::"+goods.getPageSize());
//		System.out.println("Abc");
//	}
//	@Test
//	public void hqlTestNotParamToList(){
//		String dqQuery="GoodsManager.findAllGoods.query";
//		List<Goods> goodses=(List<Goods>)goodsManager.findListByDynamicQuery(dqQuery);
//		for(Goods goods:goodses){
//			System.out.println(goods.getName());
//		}
//	}
	@Test
	public void hqlTestHasParamToList(){
		String dqQuery1="NeedsManager.findNeedsByNid.query";
		Map<String,Object>map1=new HashMap<String,Object>();
		map1.put("id","34fd6e0d45bd07da0145bd09c6f50001");
		List<Needs> needs=(List<Needs>)needsManager.findListByDynamicQuery(dqQuery1, map1);
		System.out.println(needs.get(0).getNname());
//		String dqQuery="GoodsManager.findValidGoodsByContainName.query";
//		Map<String,Object>map=new HashMap<String,Object>();
////		map.put("degree","八成新");
//		map.put("name", "a");
//		System.out.println(map.get("name"));
//		List<Goods> goodses=(List<Goods>)goodsManager.findListByDynamicQuery(dqQuery,map);
//		for(Goods goods:goodses){
//			System.out.println(goods.getGname());
//		}
	}
}
