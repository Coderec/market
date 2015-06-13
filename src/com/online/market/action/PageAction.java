/**
 * 
 */
package com.online.market.action;

import org.onlineframework.commons.IPage;

public abstract class PageAction<E> extends BaseAction{

	private static final long serialVersionUID = 8057924321162887532L;

	public int pageSize = 10;

	public int pageNo = 1;
	
	
	protected IPage<E> pageObj;

	/**
	 * @return the pageObj
	 */
	public IPage<E> getPageObj() {
		return pageObj;
	}

}
