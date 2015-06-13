package com.online.market.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.online.market.common.Constant;
import com.online.market.model.GComment;
import com.online.market.model.Goods;
import com.online.market.model.NComment;
import com.online.market.model.Needs;
import com.online.market.model.User;
import com.online.market.service.IGComentManager;
import com.online.market.service.IGoodsManager;
import com.online.market.service.INCommentManager;
import com.online.market.service.INeedsManager;
import com.online.market.service.IUserManager;
import com.online.market.util.ZipImage;
import com.opensymphony.xwork2.ActionContext;

public class ToItemDetailsAction extends BaseAction{

	/*
	 * 到达ItemDetails页面之前执行的动作 
	 */
	private static final long serialVersionUID = 3957949041563613667L;
	private String pre;
	private String id;
	private IGoodsManager goodsManager;
	private IGComentManager gCommentManager;
	private INeedsManager needsManager;
	private INCommentManager nCommentManager;
	private IUserManager userManager;
	public String getPre() {
		return pre;
	}
	public void setPre(String pre) {
		this.pre = pre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}
	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}
	
	public IGComentManager getgCommentManager() {
		return gCommentManager;
	}
	public void setgCommentManager(IGComentManager gCommentManager) {
		this.gCommentManager = gCommentManager;
	}
	public INeedsManager getNeedsManager() {
		return needsManager;
	}
	public void setNeedsManager(INeedsManager needsManager) {
		this.needsManager = needsManager;
	}
	public INCommentManager getnCommentManager() {
		return nCommentManager;
	}
	public void setnCommentManager(INCommentManager nCommentManager) {
		this.nCommentManager = nCommentManager;
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

	/*
	 * 压缩物品的6张图片
	 */
	private List<String> zip6Image(List<Goods> goodses){
		List<String> ImagesList = new ArrayList<String>();
		String fileRealPath2=getRealPath("uploadImages",null);
		String fileRealPath1 = getRealPath("tempUploadImages", null);
		if(goodses.get(0).getGimg1()!=null && !goodses.get(0).getGimg1().isEmpty()){
			File file1 = new File(fileRealPath2,goodses.get(0).getGimg1().substring(goodses.get(0).getGimg1().lastIndexOf('/')+1));

			File file11 = new File(fileRealPath1, goodses.get(0).getGid()+"1"+goodses.get(0).getGimg1().substring(goodses.get(0).getGimg1().lastIndexOf(".")));
			if (!file11.getParentFile().exists())
				file11.getParentFile().mkdirs();
			String str1 = ZipImage.zipImageFile(file1, file11, 512, 512, 1);
			ImagesList.add(ServletActionContext.getRequest().getSession().getServletContext().getContextPath()+"/tempUploadImages/"+str1);
		}
		
		if(goodses.get(0).getGimg2()!=null && !goodses.get(0).getGimg2().isEmpty()){
			File file2 = new File(fileRealPath2,goodses.get(0).getGimg2().substring(goodses.get(0).getGimg2().lastIndexOf('/')+1));

			File file12 = new File(fileRealPath1, goodses.get(0).getGid()+"2"+goodses.get(0).getGimg2().substring(goodses.get(0).getGimg2().lastIndexOf(".")));
			if (!file12.getParentFile().exists())
				file12.getParentFile().mkdirs();
			String str2 = ZipImage.zipImageFile(file2, file12, 512, 512, 1);
			ImagesList.add(ServletActionContext.getRequest().getSession().getServletContext().getContextPath()+"/tempUploadImages/"+str2);
		}
		
		if(goodses.get(0).getGimg3()!=null && !goodses.get(0).getGimg3().isEmpty()){
			File file3 = new File(fileRealPath2,goodses.get(0).getGimg3().substring(goodses.get(0).getGimg3().lastIndexOf('/')+1));

			File file13 = new File(fileRealPath1, goodses.get(0).getGid()+"3"+goodses.get(0).getGimg3().substring(goodses.get(0).getGimg3().lastIndexOf(".")));
			if (!file13.getParentFile().exists())
				file13.getParentFile().mkdirs();
			String str3 = ZipImage.zipImageFile(file3, file13, 512, 512, 1);
			ImagesList.add(ServletActionContext.getRequest().getSession().getServletContext().getContextPath()+"/tempUploadImages/"+str3);
		}
		
		if(goodses.get(0).getGimg4()!=null && !goodses.get(0).getGimg4().isEmpty()){
			File file4 = new File(fileRealPath2,goodses.get(0).getGimg4().substring(goodses.get(0).getGimg4().lastIndexOf('/')+1));

			File file14 = new File(fileRealPath1, goodses.get(0).getGid()+"4"+goodses.get(0).getGimg4().substring(goodses.get(0).getGimg4().lastIndexOf(".")));
			if (!file14.getParentFile().exists())
				file14.getParentFile().mkdirs();
			String str4 = ZipImage.zipImageFile(file4, file14, 512, 512, 1);
			ImagesList.add(ServletActionContext.getRequest().getSession().getServletContext().getContextPath()+"/tempUploadImages/"+str4);
		}
		
		if(goodses.get(0).getGimg5()!=null && !goodses.get(0).getGimg5().isEmpty()){
			File file5 = new File(fileRealPath2,goodses.get(0).getGimg5().substring(goodses.get(0).getGimg5().lastIndexOf('/')+1));

			File file15 = new File(fileRealPath1, goodses.get(0).getGid()+"5"+goodses.get(0).getGimg5().substring(goodses.get(0).getGimg5().lastIndexOf(".")));
			if (!file15.getParentFile().exists())
				file15.getParentFile().mkdirs();
			String str5 = ZipImage.zipImageFile(file5, file15, 512, 512, 1);
			ImagesList.add(ServletActionContext.getRequest().getSession().getServletContext().getContextPath()+"/tempUploadImages/"+str5);
		}
		
		if(goodses.get(0).getGimg6()!=null && !goodses.get(0).getGimg6().isEmpty()){
			File file6 = new File(fileRealPath2,goodses.get(0).getGimg6().substring(goodses.get(0).getGimg6().lastIndexOf('/')+1));

			File file16 = new File(fileRealPath1, goodses.get(0).getGid()+"6"+goodses.get(0).getGimg6().substring(goodses.get(0).getGimg6().lastIndexOf(".")));
			if (!file16.getParentFile().exists())
				file16.getParentFile().mkdirs();
			String str6 = ZipImage.zipImageFile(file6, file16, 512, 512, 1);
			ImagesList.add(ServletActionContext.getRequest().getSession().getServletContext().getContextPath()+"/tempUploadImages/"+str6);
		}
		return ImagesList;
	}

	public String toItemDetails(){
		//request  :  gCommentList/nCommentList  and  goodsList/needsList and pre and id and gCommentFromUsersList/nCommentFromUsersList
		
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
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("pre", pre);
			if("g".equals(pre)){
				String dqQuery1="GoodsManager.findGoodsByGid.query";
				String dqQuery2="GCommentManager.findGCommentByGidDesc.query";
				Map<String,Object>map1 = new HashMap<String,Object>();
				Map<String,Object>map2 = new HashMap<String,Object>();
				map1.put("id", id);
				map2.put("id", id);
				List<Goods> goodses=goodsManager.findListByDynamicQuery(dqQuery1,map1);
				try {
					List<GComment> gComments=gCommentManager.findListByDynamicQuery(dqQuery2,map2);
					//uids用于获取gComment来源用户的名字而不是id
					List<User> uids = new ArrayList<User>();
					if(gComments!=null){
						for(GComment gComment:gComments){
							String str = "UserManager.findUserByUid.query";
							Map<String,Object> tempMap = new HashMap<String,Object>();
							tempMap.put("id", gComment.getUid());
							List<User> tempUser = (List<User>)userManager.findListByDynamicQuery(str, tempMap);
							uids.add(tempUser.get(0));
						}
						request.setAttribute("gCommentFromUsersList", uids);
						request.setAttribute("gCommentList", gComments);
					}
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
				}
				//判断是否已经收藏
				User currUser=(User) ActionContext.getContext().getSession().get("session_user");
				if(currUser!=null && currUser.getUgids()!=null){
					List<String> gids = Arrays.asList(currUser.getUgids().split(","));
					for(String gid:gids){
//						System.out.println("迭代："+gid);
//						System.out.println("传值："+id);
						if(gid.equals(id)){
							request.setAttribute("everCollected", true);
						}
					}
				}
				
				//在生成temp之前清空temp文件夹
				File tempFile=new File(getRealPath("tempUploadImages",null));
				File files[] = tempFile.listFiles();               //声明目录下所有的文件 files[];
				if(files!=null){
					for(int t=0;t<files.length;t++){            //遍历目录下所有的文件
						 files[t].delete();
					 }
				}
				ActionContext.getContext().getSession().put("goodsImage", zip6Image(goodses));
				
				if(!goodses.isEmpty()){
					request.setAttribute("goodsList", goodses);
					request.setAttribute("pre", pre);
					request.setAttribute("id", goodses.get(0).getGid());
					return "item-details";
				}
				return null;
			}
			if("n".equals(pre)){
				String dqQuery1="NeedsManager.findNeedsByNid.query";
				String dqQuery2="NCommentManager.findNCommentByNidDesc.query";
				Map<String,Object>map1 = new HashMap<String,Object>();
				Map<String,Object>map2 = new HashMap<String,Object>();
				map1.put("id", id);
				map2.put("id", id);
				List<Needs> needses=needsManager.findListByDynamicQuery(dqQuery1,map1);
				try {
					List<NComment> nComments=nCommentManager.findListByDynamicQuery(dqQuery2,map2);
					//uids用于获取gComment来源用户的名字而不是id
					List<User> uids = new ArrayList<User>();
					if(!nComments.isEmpty()){
						for(NComment nComment:nComments){
							String str = "UserManager.findUserByUid.query";
							Map<String,Object> tempMap = new HashMap<String,Object>();
							tempMap.put("id", nComment.getUid());
							List<User> tempUser = (List<User>)userManager.findListByDynamicQuery(str, tempMap);
							uids.add(tempUser.get(0));
						}
						request.setAttribute("nCommentFromUsersList", uids);
						request.setAttribute("nCommentList", nComments);
					}
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
				
				if(!needses.isEmpty()){
					request.setAttribute("needsList", needses);
					request.setAttribute("pre", pre);
					request.setAttribute("id", needses.get(0).getNid());
					return "item-details";
				}
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
