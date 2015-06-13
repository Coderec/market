package com.online.market.test;

import java.util.List;

import org.junit.Test;
import org.onlineframework.commons.util.MD5Util;

import com.online.market.model.Admin;
import com.online.market.service.IAdminManager;

public class AdminManagerTest extends SpringUtils{
	public IAdminManager adminManager=(IAdminManager)applicationContext.getBean("adminManager");
//	@Test
//	public void save(){
//		Admin admin=new Admin();
//		admin.setName("张三");
//		admin.setPassword(MD5Util.getMD5("12345"));
//		admin.setRole("管理员");
//		admin.setValid(true);
//		adminManager.save(admin);
//		System.out.println("ok");
//	}
	/*
	@Test
	public void get(){
		Admin admin=adminManager.get("40280e81450d339a01450d33a0270001");
		System.out.println(admin.getName());
	}*/
	/*@Test
	public void remove(){
		adminManager.removeById("40280e81450d339a01450d33a0270001");
	}*/
	@Test
	public void findAll(){
		//List<Admin>admins=adminManager.findAll();
		Admin admin=new Admin();
		System.out.println(adminManager.findByExample(admin));
	}
}
