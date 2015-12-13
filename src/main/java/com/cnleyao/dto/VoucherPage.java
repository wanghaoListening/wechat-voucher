package com.cnleyao.dto;

import java.io.Serializable;
/**
 * <p>Title:用于卡券类<p>
 * <p>Description:此类用于前后端数据的交互和卡券分页使用<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年10月19日
 * */
public class VoucherPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//提供默认的构造方法
	public VoucherPage() {
		super();

	}

	private Long businessId;

	private Long voucherId;
	//开始页默认从第一页开始
	private int page = 1 ;
	//结束页
	private int endPage = 5;
	//默认每页展示8条记录
	private int rows = 8;
	//防止数据转换错误
	private String type;
	private String name;
	//总的页数
	private int totalPage;
	//查询出的总记录数
	private long count;
	//核销记录时的查询条件
	private String condition;
	//商户的名称
	private String businessName;
	//适用门店
	private String adaptedStore;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public int getTotalPage() {
		this.totalPage = (int) (((this.count%this.rows)==0)?(this.count/this.rows):((this.count/this.rows)+1));
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	//计算分页时的开始页和结束页
	public void calculate(){
		if(this.totalPage>5){
			if(this.page>=this.totalPage){
				this.endPage = this.totalPage;
				this.page = this.endPage-4;
			}else if(this.page<=1){
				this.page = 1;
				this.endPage = this.page+4;
			}else{
				if((this.page-2)<=1){
					this.page = 1;
					this.endPage = this.page+4;
				}else if((this.page+2)>=this.totalPage){
					this.endPage = this.totalPage;
					this.page = this.endPage-4;
				}else{
					this.page = this.page-2;
					this.endPage = this.page+4;
				}
			}
		}

	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public Long getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getAdaptedStore() {
		return adaptedStore;
	}
	public void setAdaptedStore(String adaptedStore) {
		this.adaptedStore = adaptedStore;
	}
	
	
}
