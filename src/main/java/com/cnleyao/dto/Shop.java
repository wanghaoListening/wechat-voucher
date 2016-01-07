package com.cnleyao.dto;

import java.io.Serializable;

public class Shop implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 原本id是long类型的，但是考虑到long类型
	 * 的数据用jquery进行解析会损失精度
	 * */
	private String id;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
