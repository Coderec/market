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

public class ClassifiedDetailsChangePageAction extends BaseAction{

	/*
	 * 该action用来完成分类显示页面的翻页功能
	 * 
	 * 属性说明：
	 * className 类别名称
	 * pageNum 即将跳转到的页码
	 * 操作相关表的goodsManager和needsManager由Spring通过setter方法注入
	 * 
	 */
	private static final long serialVersionUID = -1638319063407673976L;
	private String className;
	private int pageNum;
//	private String pre;
	private IGoodsManager goodsManager;
	private INeedsManager needsManager;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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
	/*
	 * @param dic 文件夹目录名字
	 * @param fileName 文件名
	 * @return 文件夹或者文件在服务器上的路径
	 * 
	 */
	private String getRealPath(String dic,String fileName){
		HttpSession session=ServletActionContext.getRequest().getSession();
		String basePath=session.getServletContext().getRealPath("/");
		if(fileName==null)
			return basePath+dic;
		else
			return basePath+dic+File.separator+fileName;
	}
	/* 生成temp图片存在tempUploadImages
	 * 将所有压缩后的文件路径存在Session中，以便在jsp中显示图片(img src=路径)
	 * 
	 * @param requestAttributeName Session中的Attribute名
	 * @param width 压缩后的图片width
	 * @param height 压缩后的图片height
	 * 
	 */
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
	
	/*
	 * 首先查询出用户所有发布过的物品，然后遍历查出所有物品评论，
	 * 将评论数存入session.session_userMsg中，以此更新用户消息数
	 * 根据类别名和当前页数查出出售物品和求购物品
	 * 将查询到的出售物品和求购物品以及类别名和当前页数存入request
	 * 
	 * 注意：当前页数没有必要传入request可以根据分页对象获取
	 */
	public String classifiedDetailsChangePage(){
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
				System.out.println("classifiedDetailsChangePageAction,msg查询为空");
			}
			ActionContext.getContext().getSession()
					.put(Constant.KEY_LOGIN_USERMSG, j);
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("goodsList", this.findAllGoodsByCategory());
		request.setAttribute("needsList", this.findAllNeedsByCategory());
		request.setAttribute("className", className);
		request.setAttribute("curNum", pageNum);
		
		return "classifiedDetails";
	}
	
	/*
	 * 首先清空tempUploadImages文件夹
	 * 将查询出的图片重新压缩至tempUploadImages文件夹，并将图片路径存进session
	 * 
	 * @return 分页对象
	 */
	public IPage<Goods> findAllGoodsByCategory(){
		try {
			String dqQuery="GoodsManager.findValidGoodsByCategory.query";
			String dqCount="GoodsManager.findValidGoodsByCategory.count";
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("category", className);
			IPage<Goods> goodses=goodsManager.findByDynamicQuery(dqQuery, dqCount,map,pageNum,Constant.KEY_ClassPageSize);
			//在生成temp之前清空temp文件夹
			File tempFile=new File(getRealPath("tempUploadImages",null));
			File files[] = tempFile.listFiles();               //声明目录下所有的文件 files[];
			for(int t=0;t<files.length;t++){            //遍历目录下所有的文件
		    	 files[t].delete();
		     }
			zipImage(goodses.getPageElements(),"classifiedImages",172,180);
			return goodses;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
	}

	/*
	 * needs没有图片，因此无需清空图片以及压缩图片
	 * 直接返回分页对象
	 */
	public IPage<Needs> findAllNeedsByCategory(){
		String dqQuery="NeedsManager.findValidNeedsByCategory.query";
		String dqCount="NeedsManager.findValidNeedsByCategory.count";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("category", className);
		IPage<Needs> needses=needsManager.findByDynamicQuery(dqQuery, dqCount,map,pageNum,Constant.KEY_ClassPageSize);
		
		return needses;
	}
}
