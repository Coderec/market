package com.online.market.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.onlineframework.commons.IPage;

import com.online.market.common.Constant;
import com.online.market.model.Goods;
import com.online.market.model.Needs;
import com.online.market.model.User;
import com.online.market.service.IGoodsManager;
import com.online.market.service.INeedsManager;
import com.online.market.util.ZipImage;
import com.opensymphony.xwork2.ActionContext;

public class ToClassifiedDetailsAction extends PageAction<Goods>{

	/*
	 * 与 ClassifiedDetailsChangePageAction基本完全相同
	 * 区别只是这个action默认返回第一页，而非可定义的任意页
	 * 
	 */
	private static final long serialVersionUID = -1511092809084174006L;
	private String className;
//	private String pre;
	private IGoodsManager goodsManager;
	private INeedsManager needsManager;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
//	public String getPre() {
//		return pre;
//	}
//	public void setPre(String pre) {
//		this.pre = pre;
//	}
	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}
	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}
	
	public INeedsManager getNeedsManager() {
		return needsManager;
	}
	public void setNeedsManager(INeedsManager needsManager) {
		this.needsManager = needsManager;
	}
	private String getRealPath(String dic,String fileName){
		HttpSession session=ServletActionContext.getRequest().getSession();
		String basePath=session.getServletContext().getRealPath("/");
		if(fileName==null)
			return basePath+dic;
		else
			return basePath+dic+File.separator+fileName;
	}
	private void zipImage(List<Goods> goodses,String requestAttributeName,int width,int height){

		//生成temp图片存在tempUploadImages，由request传入
		List<String> ImagesList = new ArrayList<String>();
		for (Goods goods : goodses) {
			String fileRealPath2=getRealPath("uploadImages",null);
			File file = new File(fileRealPath2,goods.getGimg1().substring(goods.getGimg1().lastIndexOf('/')+1));

			String fileRealPath1 = getRealPath("tempUploadImages", null);
			File file1 = new File(fileRealPath1, goods.getGid()+goods.getGimg1().substring(goods.getGimg1().lastIndexOf(".")));
			if (!file1.getParentFile().exists())
				file1.getParentFile().mkdirs();
			String str = ZipImage.zipImageFile(file, file1, width, height, 1);
			ImagesList.add(ServletActionContext.getRequest().getSession().getServletContext().getContextPath()+"/tempUploadImages/"+str);
		}
		ActionContext.getContext().getSession().put(requestAttributeName, ImagesList);
		
	}

	public String toClassifiedDetails(){
		//update UserMsgNum
		Object tempSessionUser = ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USER);
		if(tempSessionUser!=null){
			String commentQuery = "GoodsManager.findValidGoodsByUidDesc.query";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id",
					((User) ActionContext.getContext().getSession()
							.get(Constant.KEY_LOGIN_USER)).getUid());
			int j = 0;
			try {
				List<Goods> goodses = (List<Goods>) goodsManager
						.findListByDynamicQuery(commentQuery, params);
				for (Goods goods : goodses) {
					j = j + goods.getGcommentNum();
				}
			} catch (NullPointerException r) {
				System.out.println("toItemDetailsAction,msg查询为空");
			}
			ActionContext.getContext().getSession()
					.put(Constant.KEY_LOGIN_USERMSG, j);
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("goodsList", this.findAllGoodsByCategory());
		request.setAttribute("needsList", this.findAllNeedsByCategory());
		request.setAttribute("className", className);
		request.setAttribute("curNum", 1);
		
		return "classifiedDetails";
	}
	
	public IPage<Goods> findAllGoodsByCategory(){
		String dqQuery="GoodsManager.findValidGoodsByCategoryDesc.query";
		String dqCount="GoodsManager.findValidGoodsByCategoryDesc.count";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("category", className);
		IPage<Goods> goodses=goodsManager.findByDynamicQuery(dqQuery, dqCount,map,1,Constant.KEY_ClassPageSize);
		
		//在生成temp之前清空temp文件夹
		File tempFile=new File(getRealPath("tempUploadImages",null));
		File files[] = tempFile.listFiles();               //声明目录下所有的文件 files[];
		for(int t=0;t<files.length;t++){            //遍历目录下所有的文件
	    	 files[t].delete();
	     }
		zipImage(goodses.getPageElements(),"classifiedImages",172,180);
		return goodses;
	}
	public IPage<Needs> findAllNeedsByCategory(){
		String dqQuery="NeedsManager.findValidNeedsByCategoryDesc.query";
		String dqCount="NeedsManager.findValidNeedsByCategoryDesc.count";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("category", className);
		IPage<Needs> needses=needsManager.findByDynamicQuery(dqQuery, dqCount,map,1,Constant.KEY_ClassPageSize);
		
		return needses;
	}
}
