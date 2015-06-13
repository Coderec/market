package com.online.market.util;

import java.util.ArrayList;
import java.util.List;

import org.onlineframework.commons.util.UtilFuns;

public class Convert {
	
	//by tony 2013-04-13	将对象数组中每个元素转为一个字符串数组
	public List<String> toStringList(List srcList){
		List<String> targetList = new ArrayList();
		StringBuffer sBuf = new StringBuffer();
		UtilFuns utilFuns = new UtilFuns();
		String _value = null;

		for (int i = 0; i < srcList.size(); i++) {
			Object ele = srcList.get(i);
			Object[] subele = (Object[]) ele;
			for (int j = 0; j < subele.length;j++) {
				targetList.add(utilFuns.convertNull(subele[j])); 		// 防止为null时，返回"null"字符串
			}
		}
		return targetList;
	}
}
