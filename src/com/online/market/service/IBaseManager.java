package com.online.market.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.onlineframework.commons.hibernate.IRootManager;

  
public interface IBaseManager<T, K extends Serializable> extends IRootManager<T, K> {
	/**
	 * 通过Id获取某一对象
	 * @param id
	 * @return
	 */
	public T get(K id);
	/**
	 * 保存或者更新某一对象
	 * @param instance
	 */
	public void save(T instance);
	/**
	 * 删除某一对象
	 * @param instance
	 */
	public void remove(T instance);
	/**
	 * 通过Id删除某一对象
	 * @param id
	 */
	public void removeById(K id);
	/**
	 * 查找所有的对象
	 * @return
	 */
	public List<T> findAll();
	/**
	 * 通过某一对象获取该对象集合
	 * @param instance
	 * @return
	 */
	public List<T> findByExample(T instance);
	public List<T> findByExample(T instance, int firstResult, int maxResults);	
}
