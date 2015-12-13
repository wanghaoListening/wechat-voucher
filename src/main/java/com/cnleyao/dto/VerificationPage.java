package com.cnleyao.dto;

import java.io.Serializable;
/**
 * <p>Title:<p>
 * <p>Description:用于前后端交互及数据的传输<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年10月19日
 * */
public class VerificationPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long voucherId;
	private int stock;
	
	private int ledVoucherLimit=1;
	
	private String[] voucherUseRole;
	
	private String offerDetail;
	private String usageNotes;
	private String adaptedStore;
	//商户的电话号码
	private String phone;
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getLedVoucherLimit() {
		return ledVoucherLimit;
	}

	public void setLedVoucherLimit(int ledVoucherLimit) {
		this.ledVoucherLimit = ledVoucherLimit;
	}

	public String[] getVoucherUseRole() {
		return voucherUseRole;
	}

	public void setVoucherUseRole(String[] voucherUseRole) {
		this.voucherUseRole = voucherUseRole;
	}

	
	public String getOfferDetail() {
		return offerDetail;
	}

	public void setOfferDetail(String offerDetail) {
		this.offerDetail = offerDetail;
	}

	public String getUsageNotes() {
		return usageNotes;
	}

	public void setUsageNotes(String usageNotes) {
		this.usageNotes = usageNotes;
	}

	public String getAdaptedStore() {
		return adaptedStore;
	}

	public void setAdaptedStore(String adaptedStore) {
		this.adaptedStore = adaptedStore;
	}

	public Long getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
