package com.online.market.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.sql.DATE;

import org.apache.struts2.ServletActionContext;

import com.online.market.common.Constant;
import com.online.market.model.Goods;
import com.online.market.model.Needs;
import com.online.market.model.User;
import com.online.market.service.IGoodsManager;
import com.online.market.service.INeedsManager;
import com.online.market.util.ZipImage;
import com.opensymphony.xwork2.ActionContext;

public class PostAction extends BaseAction{

	/*
	 * 物品发布action
	 * 
	 * 属性说明：
	 * kind 区分发布出售物品还是求购物品
	 * pre 区分发布出售物品还是求购物品，在xml中使用，添加get请求参数
	 * id 物品id，在xml中使用，添加get请求参数
	 * name 物品名称
	 * category 物品类别
	 * degree 新旧程度
	 * price 定价
	 * pre_price 原价
	 * phone 手机号
	 * place 文昌或者南湖
	 * imgs 图片List
	 * imgsFileName 图片名List
	 * description 物品描述
	 * 
	 */
	private static final long serialVersionUID = -9015103511317319412L;
	private String kind;
	private String pre;
	private String id;
	private String name;
	private String category;
	private String degree;
	private Double price;
	private Double pre_price;
	private String phone;
	private String place;
	private List<File> imgs;
	private List<String>imgsFileName;
	private String description;
	private IGoodsManager goodsManager;
	private INeedsManager needsManager;
	
	public List<String> getImgsFileName() {
		return imgsFileName;
	}
	public void setImgsFileName(List<String> imgsFileName) {
		this.imgsFileName = imgsFileName;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPre_price() {
		return pre_price;
	}
	public void setPre_price(Double pre_price) {
		this.pre_price = pre_price;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public List<File> getImgs() {
		return imgs;
	}
	public void setImgs(List<File> imgs) {
		this.imgs = imgs;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	 * 将物品存入数据库
	 * 
	 * 除了正常的set方法，最重要的是对图片的处理部分
	 * 
	 */
	public String post(){
//		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			if(name==null||"".equals(name)){
				
			}else{
				if("g".equals(kind)){
					Goods goods =new Goods();
					goods.setGname(name);
					goods.setGcategory(category);
					goods.setGdegree(degree);
					goods.setGprice(price);
					goods.setGprePrice(pre_price);
					goods.setGphone(phone);
					if("nanhu".equals(place)){
						goods.setGplace("南湖");
					}
					if("wenchang".equals(place)){
						goods.setGplace("文昌");
					}
					goods.setGvalid(true);
					
					
					try {
						if (!imgs.isEmpty()) {
							OutputStream output = null;
							InputStream input = null;
							String fileRealPath=getRealPath("tempUploadImages",null);
							for (int i=0;i<imgs.size();++i) {
								String fileName=name+"-"+((User) ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USER)).getUname()
													+"-"+System.currentTimeMillis()+imgsFileName.get(i).substring(imgsFileName.get(i).lastIndexOf("."));
								File file=new File(fileRealPath,fileName);
								
								if(!file.getParentFile().exists())
									file.getParentFile().mkdirs();

								output = new FileOutputStream(file);
								byte[] bs = new byte[1024];
								input = new FileInputStream(imgs.get(i));
								int length = 0;
								while ((length = input.read(bs)) > 0) {
									output.write(bs, 0, length);
								}
								//压缩用，util包
								String fileRealPath1=getRealPath("uploadImages",null);
								File file1 = new File(fileRealPath1, fileName);
								if (!file1.getParentFile().exists())
									file1.getParentFile().mkdirs();
								String str = ZipImage.zipImageFile(file,file1,1000,1000,1);
								
								HttpSession session=ServletActionContext.getRequest().getSession();
								switch(i){
								case 0:
									goods.setGimg1(session.getServletContext().getContextPath()+"/uploadImages/"+str);
									break;
								case 1:
									goods.setGimg2(session.getServletContext().getContextPath()+"/uploadImages/"+str);
									break;
								case 2:
									goods.setGimg3(session.getServletContext().getContextPath()+"/uploadImages/"+str);
									break;
								case 3:
									goods.setGimg4(session.getServletContext().getContextPath()+"/uploadImages/"+str);
									break;
								case 4:
									goods.setGimg5(session.getServletContext().getContextPath()+"/uploadImages/"+str);
									break;
								case 5:
									goods.setGimg6(session.getServletContext().getContextPath()+"/uploadImages/"+str);
								}
								
							}
							input.close();
							output.close();
							// 清空tempUploadImages
							File tempFile=new File(fileRealPath);
							File files[] = tempFile.listFiles();               //声明目录下所有的文件 files[];
							for(int t=0;t<files.length;t++){            //遍历目录下所有的文件
						    	 files[t].delete();
						     }
						}
					} catch (Exception e) {
//						e.printStackTrace();
					}
					
					goods.setGdescription(description);
					goods.setGcollectNum(0);
					goods.setGcommentNum(0);
					goods.setGstartTime(new Date());
					goods.setGtime(new Date());
					goods.setUser((User)ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USER));
					goodsManager.save(goods);
					
					pre="g";
					id=goods.getGid();
				}else if("n".equals(kind)){
					Needs needs =new Needs();
					needs.setNname(name);
					needs.setNcategory(category);
					needs.setNdegree(degree);
					needs.setNphone(phone);
					needs.setNlessPrice(price);
					needs.setNhighesPricet(pre_price);
					if("nanhu".equals(place)){
						needs.setNplace("南湖");
					}
					if("wenchang".equals(place)){
						needs.setNplace("文昌");
					}
					needs.setNvalid(true);
					needs.setNdescription(description);
					needs.setNcommentNum(0);
					needs.setNtime(new Date());
					needs.setUser((User)ActionContext.getContext().getSession().get(Constant.KEY_LOGIN_USER));
					needsManager.save(needs);
					
					pre="n";
					id=needs.getNid();
				}
				
				return "toItemDetailsAction";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "post";
	}
}
