package com.cnleyao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 用户实际领到的卡券
 * @author wanghao
 * @since 2015/10/15
 * */
@Entity
@Table(name="Voucher_User")
public class UserVoucher implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	//此卡券是否已经核销默认是false，没有核销
	private boolean isVerificated = false;
	//领取卡券的用户ID对应微信的openid
	private String userId;
	private Date receiveDate;
	//卡券的使用日期
	private Date useDate;
	//此卡券所消费的门店
	private String useStore;
	//来源：自己领取，转赠,默认情况下由自己使用
	private boolean ownUse = true;
	//用于显示在用户卡券上
	private Long code;
	//此用户卡券所属卡券模板
	private CardVoucher cardvoucher;
	
	
	@Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public UserVoucher() {
		super();
		// TODO Auto-generated constructor stub
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="voucherId")
	public CardVoucher getCardvoucher() {
		return cardvoucher;
	}
	
	public void setCardvoucher(CardVoucher cardvoucher) {
		this.cardvoucher = cardvoucher;
	}
	public boolean getVerificated() {
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
	public boolean isVerificated() {
		return isVerificated;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public String getUseStore() {
		return useStore;
	}
	public void setUseStore(String useStore) {
		this.useStore = useStore;
	}
	public boolean isOwnUse() {
		return ownUse;
	}
	public void setOwnUse(boolean ownUse) {
		this.ownUse = ownUse;
	}
	@Column(length=15)
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	
	
}
