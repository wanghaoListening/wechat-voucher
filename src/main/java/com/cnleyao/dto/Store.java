package com.cnleyao.dto;

import java.io.Serializable;

//{"Id":144622272179535148,"ShopId":144622269107968586,"Name":"华泰店","Tel":"12345678","Address":"华泰路","CreateTime":"Oct 31, 2015 12:32:01 AM"},
//{"Id":144622274433494088,"ShopId":144622269107968586,"Name":"山河店","Tel":"123","Address":"山河路","CreateTime":"Oct 31, 2015 12:32:24 AM"}]-----
public class Store implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	//门店所对应的商户的id
	private String shopId;
	//门店的名字
	private String name;
	//此门店的电话
	private String tel;
	private String address;
	private String createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
