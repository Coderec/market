package com.online.market.util;

import java.util.List;

public class Tree {

	  /* 编辑树，点击树节点调用ajax获取下级内容  
	   * power by tony 20130413
	   * 
	   * name 树对象名称，一个页面多个时，必须不同名称
	   * aList 3个值数组 例如："select SYS_CODE_ID,PARENT_ID,NAME from SYS_CODE_B order by PARENT_ID,ORDER_NO"
	   * path 所在页面相对JS的位置 例如：../
	   * level 限定支持几级树，理论支持无限
	   *  
	   *  */
	
	  public String getTreeForEdit(String name, List<String> treeList, String path, int level ){
		String id = "", parent_id = "";
		String nl = System.getProperty("line.separator");		//换行符号
	    StringBuffer sBuf = new StringBuffer();
	    
	    sBuf.append("<div class=\"dtree\">").append(nl);
	    sBuf.append("<script type=\"text/javascript\">").append(nl);
	    sBuf.append("dtree_").append(name).append(" = new dTree(\"dtree_").append(name).append("\",\"").append(path).append("/js/dtree/tdtree/\");").append(nl);
	    
	    for (int i=0;i<treeList.size();){  
	    	id = treeList.get(i++);
	    	parent_id = treeList.get(i++);
	    	sBuf.append("dtree_").append(name).append(".add(\"").append(id).append("\",\"").append(parent_id).append("\",\"").append(treeList.get(i++)).append("\",");
	    	if(parent_id.length()<=level){		//限定只有level层节点
	    		sBuf.append("\"javascript:clearDelId();sndReqFind('").append(id).append("');\"");
	    	}else{
	    		sBuf.append("\"javascript:clearDelId();\"");
	    	}
	    	sBuf.append(");").append(nl);
	    }
	    sBuf.append("document.write(dtree_").append(name).append(");").append(nl);
	    sBuf.append("</script>").append(nl);
	    sBuf.append("</div>").append(nl);
	    return sBuf.toString();
	  }
}
