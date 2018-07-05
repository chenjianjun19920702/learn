package com.cjj.learn.java.tree;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：树形结构服务类
 */
public interface TreeInterface<T extends BaseTreeObj<T,ID>, ID extends Serializable>{
	/**
	 * 获得指定节点下所有归档
	 */
	public List<T> getChildTreeObjects(List<T> list,ID parentId);
	
	/**
	 * 递归列表
	 */
	public void recursionFn(List<T> list,T t);
	
	/**
	 * 获得指定节点下的所有子节点
	 */
	public List<T> getChildList(List<T> list,T t);
	
	/**
	 * 判断是否还有下一个子节点
	 */
	public boolean hasChild(List<T> list, T t);
}
