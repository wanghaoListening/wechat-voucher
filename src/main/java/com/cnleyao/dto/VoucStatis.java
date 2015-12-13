package com.cnleyao.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:<p>
 * <p>Description:对商户卡券使用的统计类<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年10月20日
 * */
public class VoucStatis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//此卡券是否已经核销默认是false，没有核销
	private boolean isVerificated = false;
	//领取卡券的用户ID
	private String userId;
	//卡券的领取日期
	private Date receiveDate;
	//卡券的使用日期
	private Date useDate;
	//使用门店
	private String businessId;
	//来源，自己领取，还是他人领取
	private String receiveSource;
	public boolean isVerificated() {
		return isVerificated;
	}
	public void setVerificated(boolean isVerificated) {
		this.isVerificated = isVerificated;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getReceiveSource() {
		return receiveSource;
	}
	public void setReceiveSource(String receiveSource) {
		this.receiveSource = receiveSource;
	}
	
	

}
