package com.online.market.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.core.RowMapper;

import com.online.market.common.Constant;
import com.online.market.model.Goods;
import com.online.market.model.User;
import com.online.market.service.IGoodsManager;
import com.online.market.service.ext.IGoodsManagerExt;
import com.online.market.util.ZipImage;
import com.opensymphony.xwork2.ActionContext;

public class IndexAction extends BaseAction{

	/*
	 * getRealPath和zipImage方法与ClassifiedDetailsChangePageAction中重复，考虑添加到util中
	 */
	private static final long serialVersionUID = -4685283921755402119L;
	private IGoodsManager goodsManager;
	private IGoodsManagerExt goodsManagerExt;
	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}
	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}
	
	public IGoodsManagerExt getGoodsManagerExt() {
		return goodsManagerExt;
	}
	public void setGoodsManagerExt(IGoodsManagerExt goodsManagerExt) {
		this.goodsManagerExt = goodsManagerExt;
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
	 * 将所有压缩后的文件路径存在Session中
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
	 * 因为各个种类的goodslist都需要先清空temp文件夹所以放在主函数里执行
	 * 将查询得到的各个类别的goodslist装进request
	 * 设置request.agf为"yes"，如果不是，访问index。jsp会直接访问本action
	 * 获取用户的评论数
	 * 返回index.jsp
	 */
	public String index(){
		try {
			//在生成temp之前清空temp文件夹
			File tempFile=new File(getRealPath("tempUploadImages",null));
			File files[] = tempFile.listFiles();               //声明目录下所有的文件 files[];
			if(files!=null){
				for(int t=0;t<files.length;t++){            //遍历目录下所有的文件
					 files[t].delete();
				 }
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("newestGoodsList", newestGoods());
			request.setAttribute("favorGoodsList", favorGoods());
			request.setAttribute("goodsList1", goodsList1());
			request.setAttribute("goodsList2", goodsList2());
			request.setAttribute("goodsList3", goodsList3());
			request.setAttribute("goodsList4", goodsList4());
			request.setAttribute("goodsList5", goodsList5());
			
			request.setAttribute("agf", "yes");
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
//				System.out.println("indexAction,msg查询为空");
				}
				ActionContext.getContext().getSession()
						.put(Constant.KEY_LOGIN_USERMSG, j);
			}
			return "index";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
	}
	

	/*
	 * 以下所有获取goodslist方法，结构相同，全部使用基于jdbc的manager操作
	 * 获取最新goods列表、猜你喜欢goods(这里只是使用了随机)、各个类别的goodslist
	 * 
	 * 1.构建RowMapper设置查询条件
	 * 2.查询
	 * 3.压缩
	 * 4.返回list
	 * 
	 * 注意：这里的Query是在spring包中的sql配置文件中设置了limit 10的SQL语句。
	 */
	private List<Goods> newestGoods(){
		RowMapper rm1 = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Goods goods = new Goods();
				goods.setGid(rs.getString("g_id"));
				goods.setGname(rs.getString("g_name"));
				goods.setGimg1(rs.getString("g_img1"));
				goods.setGprice(rs.getDouble("g_price"));;
				return goods;
			}
		};
		try {
			List<Goods> newestGoodses = goodsManagerExt.findList("GoodsManagerExt.findNewestGoodsByTime.query", rm1);

			if(newestGoodses!=null){
				zipImage(newestGoodses,"newestImagesList",172,180);
			}
			
			return newestGoodses;
		} catch (Exception e) {
			System.out.println("获取newestGoods时发生异常");
		}
		return null;
	}
	
	private List<Goods> favorGoods(){
		RowMapper rm1 = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Goods goods = new Goods();
				goods.setGid(rs.getString("g_id"));
				goods.setGname(rs.getString("g_name"));
				goods.setGimg1(rs.getString("g_img1"));
				goods.setGprice(rs.getDouble("g_price"));
				return goods;
			}
		};
		try {
			List<Goods> favorGoodses = goodsManagerExt.findList("GoodsManagerExt.findGoodsByRandom.query", rm1);
			
			if(favorGoodses!=null){
				zipImage(favorGoodses,"favorImagesList",172,180);
			}
			
			return favorGoodses;
		} catch (Exception e) {
			System.out.println("获取favorGoods时发生异常");
		}
		return null;
	}
	
	private List<Goods> goodsList1(){
		String dqQuery = "GoodsManagerExt.findGoodsByCategoryRandom.query";
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("gCategory","闲置数码");

		RowMapper rm = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Goods goods = new Goods();
				goods.setGid(rs.getString("gd.g_id"));
				goods.setGname(rs.getString("gd.g_name"));
				goods.setGimg1(rs.getString("gd.g_img1"));
				goods.setGprice(rs.getDouble("g_price"));
				return goods;
			}
		};
		try {
			List<Goods> goodses = goodsManagerExt.findList(dqQuery, params, rm);
			if(goodses!=null){
				zipImage(goodses,"goodses1ImagesList",172,180);
			}
			
			return goodses;
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("获取goodsList1时发生异常");
		}
		return null;
		
	}
	private List<Goods> goodsList2(){
		String dqQuery = "GoodsManagerExt.findGoodsByCategoryRandom.query";
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("gCategory","书籍杂志");

		RowMapper rm = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Goods goods = new Goods();
				goods.setGid(rs.getString("gd.g_id"));
				goods.setGname(rs.getString("gd.g_name"));
				goods.setGimg1(rs.getString("gd.g_img1"));
				goods.setGprice(rs.getDouble("g_price"));
				return goods;
			}
		};
		try {
			List<Goods> goodses = goodsManagerExt.findList(dqQuery, params, rm);

			if(goodses!=null){
				zipImage(goodses,"goodses2ImagesList",172,180);
			}
			
			return goodses;
		} catch (Exception e) {
			System.out.println("获取goodsList2时发生异常");
		}
		return null;
		
	}
	private List<Goods> goodsList3(){
		String dqQuery = "GoodsManagerExt.findGoodsByCategoryRandom.query";
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("gCategory","家具日用");

		RowMapper rm = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Goods goods = new Goods();
				goods.setGid(rs.getString("gd.g_id"));
				goods.setGname(rs.getString("gd.g_name"));
				goods.setGimg1(rs.getString("gd.g_img1"));
				goods.setGprice(rs.getDouble("g_price"));
				return goods;
			}
		};
		try {
			List<Goods> goodses = goodsManagerExt.findList(dqQuery, params, rm);

			if(goodses!=null){
				zipImage(goodses,"goodses3ImagesList",172,180);
			}
			
			return goodses;
		} catch (Exception e) {
			System.out.println("获取goodsList3时发生异常");
		}
		return null;
		
	}
	private List<Goods> goodsList4(){
		String dqQuery = "GoodsManagerExt.findGoodsByCategoryRandom.query";
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("gCategory","服鞋配饰");

		RowMapper rm = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Goods goods = new Goods();
				goods.setGid(rs.getString("gd.g_id"));
				goods.setGname(rs.getString("gd.g_name"));
				goods.setGimg1(rs.getString("gd.g_img1"));
				goods.setGprice(rs.getDouble("g_price"));
				return goods;
			}
		};
		try {
			List<Goods> goodses = goodsManagerExt.findList(dqQuery, params, rm);
			
			if(goodses!=null){
				zipImage(goodses,"goodses4ImagesList",172,180);
			}
			
			return goodses;
		} catch (Exception e) {
			System.out.println("获取goodsList4时发生异常");
		}
		return null;
		
	}
	private List<Goods> goodsList5(){
		String dqQuery = "GoodsManagerExt.findGoodsByCategoryRandom.query";
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("gCategory","竹苑小市场");

		RowMapper rm = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Goods goods = new Goods();
				goods.setGid(rs.getString("gd.g_id"));
				goods.setGname(rs.getString("gd.g_name"));
				goods.setGimg1(rs.getString("gd.g_img1"));
				goods.setGprice(rs.getDouble("g_price"));
				return goods;
			}
		};
		try {
			List<Goods> goodses = goodsManagerExt.findList(dqQuery, params, rm);
			
			if(goodses!=null){
				zipImage(goodses,"goodses5ImagesList",172,180);
			}
			
			return goodses;
		} catch (Exception e) {
			System.out.println("获取goodsList5时发生异常");
		}
		return null;
		
	}
}
