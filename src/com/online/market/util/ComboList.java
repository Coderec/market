package com.online.market.util;

import java.util.ArrayList;
import java.util.List;

import org.onlineframework.commons.util.UtilFuns;

public class ComboList {

	/*
	 * by tony 2013.4.12
	 * 下拉框封装类，形成html片段，目的使准备下拉框数据代码简洁，同时，灵活度胜过struts2标签；例如用于js拼接中
	 * 
	 * dList为hql查询完返回的对象集合，hql必须列出字段，例如 "select o.id,o.name from SysCode o"
	 * 这样返回为Object数组，而不是对象
	 */

	public String combo(List<String> dList, String curValue) {
		StringBuffer sBuf = new StringBuffer();
	
		UtilFuns utilFuns = new UtilFuns();
		List<String> aList = new ArrayList<String>();
		String _value = null;

		for (int i = 0; i < dList.size();) {
			_value = dList.get(i++);
			sBuf.append("<option value='").append(_value).append("'");
				if(curValue!=null){
					if (curValue.compareTo(_value) == 0) {
						sBuf.append(" selected");				//设置选中
					}
				}
				sBuf.append(">").append(dList.get(i++)).append("</option>"); // text
		}
		return sBuf.toString();
	}

	//通过数组，循环产生下拉框的内容结构
	public String combo(String[] values,String[] texts, String curValue) {
		int i = 0;
		StringBuffer sBuf = new StringBuffer();

		for (i = 0; i < texts.length; i++) {
			sBuf.append("<option value='").append(values[i]).append("'");
			if(curValue!=null){
				if (curValue.compareTo(values[i]) == 0) {
					sBuf.append(" selected");					//设置选中
				}
			}
			sBuf.append(">").append(texts[i]).append("</option>");
		}
		return sBuf.toString();
	}
	
}
