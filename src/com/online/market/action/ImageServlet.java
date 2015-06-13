package com.online.market.action;

import java.io.IOException;
import java.io.OutputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int width=130;
			private static final int height=30;
	 
			
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedImage image =new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics g =image.getGraphics();
		
		setBackground(g);
		setBorser(g);//$$$$$$$$$$$$$$$$$$$44
		drawRandomLine(g); 
		String random=drawRandomNum((Graphics2D)g);
		request.getSession().setAttribute("imagecheckcode", random);
		
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		response.setContentType("image/jpeg");
		OutputStream out=response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		
	}
       
	private void setBackground(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,0,width,height);
	}
	
	private void setBorser(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(1, 1, width-2, height-2);
	}
	
	private void drawRandomLine(Graphics g){
		g.setColor(Color.GREEN);
		
		for(int i=0;i<5;i++){
			int x1=new Random().nextInt(width);
			int y1=new Random().nextInt(height);
			
			int x2=new Random().nextInt(width);
			int y2=new Random().nextInt(height);
			
			g.drawLine(x1,y1,x2,y2);
		}
	}
	
	private String drawRandomNum(Graphics2D g){
		g.setColor(Color.red);
		g.setFont(new Font("宋体",Font.BOLD,20));
		String base="\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3\u8981\u4e0b\u770b\u5929\u65f6\u8fc7\u51fa\u5c0f\u4e48\u8d77\u4f60\u90fd\u628a\u597d\u8fd8\u591a\u6ca1\u4e3a\u53c8\u53ef\u5bb6\u5b66\u53ea\u4ee5\u4e3b\u4f1a\u6837\u5e74\u60f3\u751f\u540c\u8001\u4e2d\u5341\u4ece\u81ea\u9762\u524d\u5934\u9053\u5b83\u540e\u7136\u8d70\u5f88\u50cf\u89c1\u4e248c5\u9876\u6025\u6797\u505c\u606f\u53e5\u533a\u8863\u822c\u62a5\u53f6\u538b\u6162\u53d4\u80cc\u7ec6";
			
		StringBuffer sb=new StringBuffer();

		int x=10;
		for(int i=0;i<4;i++){
			String ch=base.charAt(new Random().nextInt(base.length()))+"";
			sb.append(ch);
			int degree=new Random().nextInt()%10;
			g.rotate(degree*Math.PI/180,x,20);
			g.drawString(ch, x, 20);
			g.rotate(-degree*Math.PI/180,x,20);
			x=x+30;
		}
				
		return sb.toString();
		
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
		
	
	}

	
}
