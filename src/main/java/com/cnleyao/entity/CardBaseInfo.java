package com.cnleyao.entity;
/**
 * 卡券固定类型的抽取
 * @author wanghao
 * @since 2015/10/15
 * */
public interface CardBaseInfo {
	//礼品券类型在数据库对应4
	public static final Integer GIFT_TYPE = 4;
	//折扣券类型在数据库对应3
	public static final Integer DISCOUNT_TYPE = 3;
	//代金券类型在数据库对应2
	public static final Integer CASH_TYPE = 2;
	//优惠券类型在数据库对应1
	public static final Integer COUPON_TYPE = 1;
	//卡券的状态为停止
	public static final boolean OFF_STATE = false;
	//卡券的状态为启用
	public static final boolean ON_STATE = true;
}
