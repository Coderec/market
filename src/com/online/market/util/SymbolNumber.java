package com.online.market.util;

import java.util.regex.*;

/* by tony 20120322 正则表达式匹配字符中多少个字母，数字，空格  */
public class SymbolNumber{


	public static void main(String[] args) {
		long last = System.nanoTime() ;
		
		SymbolNumber symbolNumber = new SymbolNumber();
		String s = "6sabcss sfsfs33中国";
		System.out.println(symbolNumber.string(s,"[\u4e00-\u9fa5]")); 		//汉字个数
		System.out.println(symbolNumber.string(s,"[a-z|A-Z]")); 			//字母个数
		System.out.println(symbolNumber.string(s,"[0-9]")); 				//数字个数

		System.out.println(symbolNumber.del(s,"[a|b|3]")); 					//去掉a、b、3
  
		long now = System.nanoTime();
		System.out.println(now - last);
  
	}
	
	/* 字符串中字母的个数 */
	public int string(String s, String pattern){
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		int i = 0;
		while (m.find()) {
			i++;
		}
		
		return i;
	}
	
	/* 删除字符串中指定的多个内容 */
	public String del(String s, String pattern){
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		
		return sb.toString();
	}
}