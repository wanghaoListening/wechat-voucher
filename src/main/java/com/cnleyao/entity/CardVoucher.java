package com.cnleyao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 商户卡券模板类的基本信息
 * @author wanghao
 * @since 2015/10/15
 * */
@Entity
@Table(name="voucher_Template")
public class CardVoucher implements CardBaseInfo,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//卡券的ID号
	private Long voucherId;
	//卡券名称
	private String name;
	//卡券的主标题
	private String title;
	//卡券的副标题
	private String subtitle;
	//卡券的类型(卡券共有四种类型)
	private Integer type;
	//卡券的领取次数为其福默认值以防数据库存在null
	private Integer receiveTimes=0;
	//卡券的使用次数
	private Integer useTimes=0;
	//卡券的库存，意味发放此种卡券的总数量
	private Integer stock=0;
	//卡券的背景颜色
	private String background;
	//卡券的状态,默认是禁用的状态
	private boolean state = CardVoucher.OFF_STATE;
	//卡券使用开始日期
	private Date startTime;
	//卡券使用截止日期
	private Date endTime;
	//卡券发放截止日期
	private Date deadlineTime;
	//卡券兑换的礼品名称
	private String giftName;
	//卡券所属的商户
	private Long businessId;
	//商户的名称
	private String storeName;
	//用户的对此种卡券的使用权限
	//1.用户可以分享领券链接
	//2.用户领券后可转赠其他好友
	private String voucherUseRole;
	//优惠详情
	private String offerDetail;
	//用户领券的限制默认是一张
	private Integer ledVoucherLimit=1;
	//使用须知
	private String usageNotes;
	//适应门店，此卡券模板所适应的门店
	private String adaptedStore;
	//此种卡券所包含的卡券
	private Set<UserVoucher> batchvoucher;
	//卡券推广商户的联系电话
	private String phone;
	
	
	
	public CardVoucher() {
		super();
		
	}
	@Id
	@Column(nullable = false, length = 36)
	public Long getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}	
	
	public String getBackground() {
		return background;
	}
	
	public void setBackground(String background) {
		this.background = background;
	}
	
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	//时间采用timestamp类型
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	//时间采用timestamp类型
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	//时间采用timestamp类型
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDeadlineTime() {
		return deadlineTime;
	}
	public void setDeadlineTime(Date deadlineTime) {
		this.deadlineTime = deadlineTime;
	}
	@OneToMany(mappedBy="cardvoucher",cascade = CascadeType.ALL)
	public Set<UserVoucher> getBatchvoucher() {
		return batchvoucher;
	}
	public void setBatchvoucher(Set<UserVoucher> batchvoucher) {
		this.batchvoucher = batchvoucher;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getVoucherUseRole() {
		return voucherUseRole;
	}
	public void setVoucherUseRole(String voucherUseRole) {
		this.voucherUseRole = voucherUseRole;
	}
	/**
	 * 指定获取信息和使用详情的长度不超过255
	 * */
	@Column(length=300)
	public String getOfferDetail() {
		return offerDetail;
	}
	public void setOfferDetail(String offerDetail) {
		this.offerDetail = offerDetail;
	}
	@Column(length=300)
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
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	//该属性并非一个到数据库表的字段的映射
	@Transient
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getReceiveTimes() {
		return receiveTimes;
	}
	public void setReceiveTimes(Integer receiveTimes) {
		this.receiveTimes = receiveTimes;
	}
	public Integer getUseTimes() {
		return useTimes;
	}
	public void setUseTimes(Integer useTimes) {
		this.useTimes = useTimes;
	}
	
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getLedVoucherLimit() {
		return ledVoucherLimit;
	}
	public void setLedVoucherLimit(Integer ledVoucherLimit) {
		this.ledVoucherLimit = ledVoucherLimit;
	}
	
	
	
}
