package com.online.market.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.onlineframework.commons.IPage;
import org.onlineframework.commons.jdbc.IRowHandler;
import org.onlineframework.commons.jdbc.ISqlElement;
import org.onlineframework.commons.jdbc.JdbcRootManager;
import org.springframework.jdbc.core.RowMapper;

import com.online.market.model.Goods;
import com.online.market.model.User;
import com.online.market.service.ext.IGoodsManagerExt;

public class GoodsManagerExt extends JdbcRootManager implements
		IGoodsManagerExt {
	
	public void addGoods() {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("gId", "asdfghsersx");
		params.put("gName", "一本书");
		params.put("gCategory", "九成新");
		params.put("gStartTime", new Date());
		ISqlElement sqlElment;
		try {
			sqlElment = processSql(params,"GoodsManagerExt.addGoods.query");
			this.getJdbcTemplate().update(sqlElment.getSql(), sqlElment.getParams());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateGoods() {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("gId", "asdfghsersx");
		params.put("gName", "两本书");
		params.put("gStartTime", new Date());
		ISqlElement sqlElment;
		try {
			sqlElment = processSql(params,"GoodsManagerExt.updateGoods.query");
			this.getJdbcTemplate().update(sqlElment.getSql(), sqlElment.getParams());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public IPage<Goods> findGoodsByContent() {
		Map<String,Object> parpams=new HashMap<String, Object>();
		
		parpams.put("gName","一本书");
		//parpams.put("uid", uid);
		
		
			IRowHandler<Goods> rowHandler = new IRowHandler<Goods>() {
			
					public Goods handleRow(ResultSet rs) {
								try {
									Goods goods = new Goods();
									goods.setGid(rs.getString("g_id"));
									goods.setGname(rs.getString("g_name"));
									goods.setGcategory(rs.getString("g_category"));
									goods.setGdegree(rs.getString("g_degree"));
									return goods;
								} catch (Exception e) {
								}
						return null;
					}
				};
				try {
					this.setOldJdbc(true);
					
					return 	this.findPage("GoodsManagerExt.findGoodsByContent.query", "GoodsManagerExt.findGoodsByContent.count",parpams,1, 2,rowHandler);
				} catch (Exception e) {
					return null;
				}
		}

	@Override
	public IPage<Goods> findAllGoods() {
		Map<String,Object> parpams=new HashMap<String, Object>();
		IRowHandler<Goods> rowHandler = new IRowHandler<Goods>() {
			
			public Goods handleRow(ResultSet rs) {
						try {
							Goods goods = new Goods();
							goods.setGid(rs.getString("g_id"));
							goods.setGname(rs.getString("g_name"));
							goods.setGcategory(rs.getString("g_category"));
							goods.setGdegree(rs.getString("g_degree"));
							return goods;
						} catch (Exception e) {
						}
				return null;
			}
		};
		try {
			this.setOldJdbc(true);
			
			return 	this.findPage("GoodsManagerExt.findGoodsByContent.query", "GoodsManagerExt.findGoodsByContent.count",parpams,1, 2,rowHandler);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteGoodsByContent() {
		Map<String,Object> parpams=new HashMap<String, Object>();
		parpams.put("gId","asdfghsersxdfsadfxcvasdf");
		ISqlElement sqlElment;
		try {
			sqlElment = processSql(parpams,"GoodsManagerExt.deleteGoodsByContent.query");
			this.getJdbcTemplate().update(sqlElment.getSql(), sqlElment.getParams());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public IPage<Goods> findGoodsByUserId() {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("uId","2c9292e94550e7b7014550e7ba750002");
		IRowHandler<Goods> rh=new IRowHandler<Goods>() {

			@Override
			public Goods handleRow(ResultSet rs) {
				try {
					Goods goods=new Goods();
					User user =new User();
					goods.setGid(rs.getString("gd.g_id"));
					goods.setGdegree(rs.getString("gd.g_degree"));
					goods.setGname(rs.getString("gd.g_name"));
					user.setUid(rs.getString("u.u_id"));
					user.setUname(rs.getString("u.u_name"));
					goods.setUser(user);
					return goods;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return null;
			}
		};
		try {
		return this.findPage("GoodsManagerExt.findGoodsByUserId.query", "GoodsManagerExt.findGoodsByUserId.count",params, 1, 2, rh);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Goods> getGoodsByUserId() {
		Map<String,Object> params=new HashMap<String, Object>();
		//params.put("uId","2c9292e94550e7b7014550e7ba750002");
		RowMapper rm=new RowMapper() {
			
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Goods goods=new Goods();
				User user =new User();
				goods.setGid(rs.getString("gd.g_id"));
				goods.setGdegree(rs.getString("gd.g_degree"));
				goods.setGname(rs.getString("gd.g_name"));
				user.setUid(rs.getString("u.u_id"));
				user.setUname(rs.getString("u.u_name"));
				goods.setUser(user);
				return goods;
			}
		};
		try {
			return this.findList("GoodsManagerExt.findGoodsByUserId.query",params,rm);
			} catch (Exception e) {
				return null;
			}
	}

}
