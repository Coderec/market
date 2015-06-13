package com.online.market.service.ext;

import java.util.List;

import org.onlineframework.commons.IPage;
import org.onlineframework.commons.jdbc.IJdbcRootManager;

import com.online.market.model.Goods;

public interface IGoodsManagerExt extends IJdbcRootManager {
	public void addGoods();
	public void updateGoods();
	public IPage<Goods> findGoodsByContent();
	public IPage<Goods> findAllGoods();
	public void deleteGoodsByContent();
	public IPage<Goods> findGoodsByUserId();
	public List<Goods> getGoodsByUserId();
}
