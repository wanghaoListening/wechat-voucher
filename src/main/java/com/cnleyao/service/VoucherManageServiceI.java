package com.cnleyao.service;

import java.util.List;

import com.cnleyao.dto.VerificationPage;
import com.cnleyao.dto.VoucherPage;
import com.cnleyao.entity.CardVoucher;

/**
 * <p>Title:<p>
 * <p>Description:对卡券管理的业务处理<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年10月17日
 * */
public interface VoucherManageServiceI {
	
	/**
	 * 查询商户的卡券并分页展示
	 * @param voucherPage
	 * @return List<CardVoucher>
	 * */
	List<CardVoucher> findVouchers(VoucherPage voucherPage);
	/**
	 * 保存卡券的而基础信息
	 * @Param cardVoucher
	 * @return 卡券的ID
	 * */
	public void saveVoucher(CardVoucher cardVoucher);
	/**
	 * 保存卡券核销信息
	 * @Param cardVoucher
	 * 
	 * */
	public void saveVerificationMsg(VerificationPage verificationPage);
	/**
	 * 根据条件对卡券的信息进行查询
	 * @Param cardVoucher
	 * 
	 * */
	List<CardVoucher> findVoucherByWhere(VoucherPage voucherPage);
	/**
	 *保存卡券的详情信息
	 * */
	public void saveVoucDetls(VerificationPage verificationPage);
	/**
	 * 通过ID获取卡券
	 * */
	CardVoucher getVoucher(Long voucherId);
	
	/**
	 * 设置卡券的适用商户
	 * */
	public void setAdaptedStore(VerificationPage verificationPage);
	Long saveType(Integer type);
	/**
	 * 激活卡券，使其状态变为启用
	 * */
	public void activeVoucher(String voucherId);
	/**
	 * 通过商户的名称或卡券的名称获取统计结果
	 * */
	List<CardVoucher> getBusinsStatisByWhere(VoucherPage voucherPage);
	/**
	 * 禁用卡券
	 * */
	public void disableVoucher(Long voucherId);
	/**
	 * 商户发行卡券的统计
	 * */
	Long getTotalCount(VoucherPage voucherPage);
	/**
	 * 更改卡券模板的而库存
	 * */
	public void changeStock(CardVoucher voucher);
	/**
	 * 
	 * 根据多个商户的id查询商户的模板
	 * */
	List<CardVoucher> findVoucherByIds(Long[] businessId);
	

	
	
	
}
