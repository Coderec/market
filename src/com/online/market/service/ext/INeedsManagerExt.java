package com.online.market.service.ext;

import org.onlineframework.commons.IPage;
import org.onlineframework.commons.jdbc.IJdbcRootManager;

import com.online.market.model.Goods;
import com.online.market.model.Needs;

public interface INeedsManagerExt extends IJdbcRootManager {
	public void addNeeds();
	public void updateNeeds();
	public IPage<Needs> findNeedsByContent();
	public IPage<Needs> findAllNeeds();
	public void deleteNeedsByContent();
	public IPage<Needs> findNeedsByUserId();
}
