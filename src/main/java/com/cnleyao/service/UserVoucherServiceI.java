package com.cnleyao.service;

import java.util.List;

import com.cnleyao.dto.VoucherPage;
import com.cnleyao.entity.CardVoucher;
import com.cnleyao.entity.UserVoucher;

public interface UserVoucherServiceI {
	/**
	 * 根据卡券模板ID查询用户卡券
	 * */
	List<UserVoucher> getUserUseVoucher(VoucherPage voucherPage);
	/**
	 * 根据voucherID查找统计此种类型卡券的领取使用数
	 * */
	Long getTotalRows(VoucherPage voucherPage);
	/**
	 * 根据code查询BatchVoucher
	 * */
	public UserVoucher searchCode(String code);
	/**
	 * 根据指定的code对用户卡券进行核销设置
	 * */
	public void setVoucherVerific(String code);
	/**
	 * 通过商户的businessid 和卡券的名称获取卡券
	 * */
	List<UserVoucher> getStatisByName(VoucherPage voucherPage);
	/**
	 * 通过卡券的名字得到卡券的总条数
	 * */
	Long getTotalRowsByNmae(VoucherPage voucherPage);
	/**
	 * 用户获取卡券
	 * */
	Long acquireVoucher(String userId,Long voucherId);
	/**
	 * 根据voucherId获取卡券的核销记录
	 * */
	List<UserVoucher> getVerificRecords(VoucherPage voucherPage);
	/**
	 * 手机端根据code获取卡券
	 * */
	CardVoucher getVoucherByCode(UserVoucher userVoucher);
	/**
	 * 根据用户的id查看用户自己的卡包
	 * */
	List<CardVoucher> getVoucherByUid(String userId);
	/**
	 * 查看用户卡券是否已被核销
	 * */
	boolean getStateByCode(String code);
	/**
	 * 根据用户的id和卡券的id查看用户是否已经领取模板
	 * */
	UserVoucher findUserVoucher(String userId, Long voucherId);
	
}
