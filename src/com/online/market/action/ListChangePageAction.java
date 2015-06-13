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
import com.online.market.service.IUserManager;
import com.online.market.util.ZipImage;
import com.opensymphony.xwork2.ActionContext;

public class ListChangePageAction extends BaseAction{

	/*
	 * 搜索结果页面翻页action
	 * 
	 * @param searchName 搜索名称
	 * 
	 */
	private static final long serialVersionUID = -8938996508343785028L;
	private String searchName;
	private String pre;
	private int pageNum;
	private List<User> userses=new ArrayList<User>();
	private IGoodsManager goodsManager;
	private INeedsManager needsManager;
	private IUserManager userManager;
	
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

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
	
	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
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
	 * 1.更新用户消息数
	 * 2.将查询到的物品分页对象和物品对应的用户传入request
	 * 
	 * 物品查询结果在request.list
	 * 对应的用户在session.userses
	 * 
	 */
	public String listChangePage(){
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
				System.out.println("listChangePageAction,msg查询为空");
			}
			ActionContext.getContext().getSession()
					.put(Constant.KEY_LOGIN_USERMSG, j);
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		if("g".equals(pre)){
			request.setAttribute("list", this.findAllGoodsByContainName());
			request.setAttribute("searchName", searchName);
			request.setAttribute("pre", "g");
			ActionContext.getContext().getSession().put("userses", userses);
		}
		if("n".equals(pre)){
			request.setAttribute("list", this.findAllNeedsByContainName());
			request.setAttribute("searchName", searchName);
			request.setAttribute("pre", "n");
			ActionContext.getContext().getSession().put("userses", userses);
		}
		return "list";
	}
	
	/*
	 * 分页查询得出物品
	 * 遍历物品得出物品用户，并添加进userses
	 * 压缩物品图片
	 */
	public IPage<Goods> findAllGoodsByContainName(){
		String dqQuery="GoodsManager.findValidGoodsByContainName.query";
		String dqCount="GoodsManager.findValidGoodsByContainName.count";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("name",searchName);
		IPage<Goods> goodses=goodsManager.findByDynamicQuery(dqQuery, dqCount,map,pageNum,Constant.KEY_PageSize);
		for(Goods goods:goodses.getPageElements()){
			String dqQuery1="UserManager.findUserByUid.query";
			Map<String,Object>map1=new HashMap<String,Object>();
			map1.put("id",goods.getUser().getUid());
			List<User> users=(List<User>)userManager.findListByDynamicQuery(dqQuery1, map1);
//			System.out.println(goods.getGname()+" "+users.get(0).getUname());
			userses.add(users.get(0));
		}	
		
		//在生成temp之前清空temp文件夹
		File tempFile=new File(getRealPath("tempUploadImages",null));
		File files[] = tempFile.listFiles();               //声明目录下所有的文件 files[];
		if(files!=null){
			for(int t=0;t<files.length;t++){            //遍历目录下所有的文件
				 files[t].delete();
			 }
		}
		zipImage(goodses.getPageElements(),"listImages",172,180);
		return goodses;
	}
	
	/*
	 * 分页查询得出物品
	 * 遍历物品得出物品用户，并添加进userses
	 */
	public IPage<Needs> findAllNeedsByContainName(){
		String dqQuery="NeedsManager.findValidNeedsByContainName.query";
		String dqCount="NeedsManager.findValidNeedsByContainName.count";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("name",searchName);
		IPage<Needs> needses=needsManager.findByDynamicQuery(dqQuery, dqCount,map,pageNum,Constant.KEY_PageSize);
		for(Needs needs:needses.getPageElements()){
			String dqQuery1="UserManager.findUserByUid.query";
			Map<String,Object>map1=new HashMap<String,Object>();
			map1.put("id",needs.getUser().getUid());
			List<User> users=(List<User>)userManager.findListByDynamicQuery(dqQuery1, map1);
			userses.add(users.get(0));
		}
		return needses;
	}

}
