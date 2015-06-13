package com.online.market.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.onlineframework.commons.IPage;
import org.onlineframework.commons.jdbc.IRowHandler;
import org.onlineframework.commons.jdbc.ISqlElement;
import org.onlineframework.commons.jdbc.JdbcRootManager;

import com.online.market.model.Needs;
import com.online.market.model.User;
import com.online.market.service.ext.INeedsManagerExt;

public class NeedsManagerExt extends JdbcRootManager implements
		INeedsManagerExt {
	
	public void addNeeds() {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("nId", "asdfghsersx");
		params.put("nName", "一本书");
		params.put("nCategory", "九成新");
		params.put("nStartTime", new Date());
		ISqlElement sqlElment;
		try {
			sqlElment = processSql(params,"NeedsManagerExt.addNeeds.query");
			this.getJdbcTemplate().update(sqlElment.getSql(), sqlElment.getParams());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateNeeds() {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("nId", "asdfghsersx");
		params.put("nName", "两本书");
		params.put("nStartTime", new Date());
		ISqlElement sqlElment;
		try {
			sqlElment = processSql(params,"NeedsManagerExt.updateGoods.query");
			this.getJdbcTemplate().update(sqlElment.getSql(), sqlElment.getParams());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public IPage<Needs> findNeedsByContent() {
		Map<String,Object> parpams=new HashMap<String, Object>();
		
		parpams.put("gName","一本书");
		//parpams.put("uid", uid);
		
		
			IRowHandler<Needs> rowHandler = new IRowHandler<Needs>() {
			
					public Needs handleRow(ResultSet rs) {
								try {
									Needs needs = new Needs();
									needs.setNid(rs.getString("n_id"));
									needs.setNname(rs.getString("n_name"));
									needs.setNcategory(rs.getString("n_category"));
									return needs;
								} catch (Exception e) {
								}
						return null;
					}
				};
				try {
					this.setOldJdbc(true);
					
					return 	this.findPage("NeedsManagerExt.findNeedsByContent.query", "NeedsManagerExt.findNeedsByContent.count",parpams,1, 2,rowHandler);
				} catch (Exception e) {
					return null;
				}
		}

	@Override
	public IPage<Needs> findAllNeeds() {
		Map<String,Object> parpams=new HashMap<String, Object>();
		IRowHandler<Needs> rowHandler = new IRowHandler<Needs>() {
			
			public Needs handleRow(ResultSet rs) {
						try {
							Needs needs = new Needs();
							needs.setNid(rs.getString("n_id"));
							needs.setNname(rs.getString("n_name"));
							needs.setNcategory(rs.getString("n_category"));
							return needs;
						} catch (Exception e) {
						}
				return null;
			}
		};
		try {
			this.setOldJdbc(true);
			
			return 	this.findPage("NeedsManagerExt.findNeedsByContent.query", "NeedsManagerExt.findNeedsByContent.count",parpams,1, 2,rowHandler);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteNeedsByContent() {
		Map<String,Object> parpams=new HashMap<String, Object>();
		parpams.put("nId","asdfghsersxdfsadfxcvasdf");
		ISqlElement sqlElment;
		try {
			sqlElment = processSql(parpams,"NeedsManagerExt.deleteNeedsByContent.query");
			this.getJdbcTemplate().update(sqlElment.getSql(), sqlElment.getParams());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public IPage<Needs> findNeedsByUserId() {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("uId","2c9292e94550e7b7014550e7ba750002");
		IRowHandler<Needs> rh=new IRowHandler<Needs>() {

			@Override
			public Needs handleRow(ResultSet rs) {
				try {
					Needs needs=new Needs();
					User user =new User();
					needs.setNid(rs.getString("nd.n_id"));
					needs.setNname(rs.getString("nd.n_name"));
					user.setUid(rs.getString("u.u_id"));
					user.setUname(rs.getString("u.u_name"));
					needs.setUser(user);
					return needs;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return null;
			}
		};
		try {
		return this.findPage("NeedsManagerExt.findNeedsByUserId.query", "NeedsManagerExt.findNeedsByUserId.count",params, 1, 2, rh);
		} catch (Exception e) {
			return null;
		}
	}

}
