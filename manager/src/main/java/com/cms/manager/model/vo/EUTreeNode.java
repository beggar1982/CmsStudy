package com.cms.manager.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * easyUI树形控件节点格式
 * <p>Title: EUTreeNode</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月4日上午9:13:14
 * @version 1.0
 */
public class EUTreeNode implements Serializable {

	private static final long serialVersionUID = 7769253425993703747L;

	private long id;

	private String text;

	private String state;

	private List<EUTreeNode> children;

	public EUTreeNode() {
		children = new ArrayList<EUTreeNode>();
	}

	public List<EUTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<EUTreeNode> children) {
		this.children = children;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
