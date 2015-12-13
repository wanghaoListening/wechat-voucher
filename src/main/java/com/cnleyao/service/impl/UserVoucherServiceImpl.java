package com.cnleyao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.cnleyao.dao.BaseDaoI;
import com.cnleyao.dto.VoucherPage;
import com.cnleyao.entity.UserVoucher;
import com.cnleyao.entity.CardVoucher;
import com.cnleyao.service.UserVoucherServiceI;
import com.cnleyao.utils.CommonUtils;
/**
 * <p>Title:用户统计模块<p>
 * <p>Description:主要用于用户卡券的统计管理<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年10月24日
 * */
@Service
@Transactional
public class UserVoucherServiceImpl implements UserVoucherServiceI{
	private static final Logger logger = Logger.getLogger(UserVoucherServiceI.class);
	@Autowired
	private BaseDaoI<UserVoucher> userVoucherDao;
	@Autowired
	private BaseDaoI<CardVoucher> voucherDao;

	public List<UserVoucher> getUserUseVoucher(VoucherPage voucherPage) {
		Assert.notNull(voucherPage, "voucherPage must not be null");
		Map<Integer,Object> params = new HashMap<Integer,Object>();
		String hql = null;
		String condition = voucherPage.getCondition();
		if(condition!=null && !"".equals(condition)){
			//剔除condition两顿的空格
			condition = condition.trim();
			//正则表达式匹配12位数字code
			String regex = "^[0-9]{12}$";
			if(condition.matches(regex)){
				//说明为数字code
				hql = "from UserVoucher bv where bv.userId is not null and bv.code=?";
				params.put(0, Long.parseLong(condition));

			}else{
				//按卡券名称进行查询
				hql = "from UserVoucher bv where bv.userId is not null and bv.cardvoucher.name=?";
				params.put(0, condition);
			}
		}
		else{
			CardVoucher cardVoucher = checkCondition(voucherPage);
			//查询的卡券都是已用户被领取的
			hql = "from UserVoucher uv where uv.userId is not null and uv.cardvoucher=?";
			params = new HashMap<Integer,Object>();
			params.put(0, cardVoucher);
		}
		return userVoucherDao.find(hql, params, voucherPage.getPage(), voucherPage.getRows());
	}
	public Long getTotalRows(VoucherPage voucherPage) {
		Assert.notNull(voucherPage, "voucherPage must not be null");
		String condition = voucherPage.getCondition();
		Map<Integer,Object> params = new HashMap<Integer,Object>();
		String hql = null;
		if(condition!=null && !"".equals(condition)){
			//剔除condition两顿的空格
			condition = condition.trim();
			//正则表达式匹配12位数字code
			String regex = "^[0-9]{12}$";
			if(condition.matches(regex)){
				//说明为数字code
				hql = "select count(*) from UserVoucher bv where bv.userId is not null and code=?";
				params.put(0, Long.parseLong(condition));

			}else{
				//按卡券名称进行查询
				hql = "select count(*) from UserVoucher bv where bv.userId is not null and bv.cardvoucher.name=?";
				params.put(0, condition);
			}
		}else{
			CardVoucher cardVoucher = checkCondition(voucherPage);
			//查询的卡券都是已用户被领取的
			hql ="select count(*) from UserVoucher bv where bv.userId is not null and bv.cardvoucher=?";		
			params.put(0, cardVoucher);	
		}
		return userVoucherDao.count(hql, params);
	}
	/**
	 * 校验voucherPage的合法性
	 * */
	public CardVoucher checkCondition(VoucherPage voucherPage){
		//voucherPage
		Assert.notNull(voucherPage.getVoucherId(),"voucherId must be not null");
		CardVoucher cardVoucher = voucherDao.get(CardVoucher.class, voucherPage.getVoucherId());
		return cardVoucher;
	}
	public UserVoucher searchCode(String code) {
		Assert.hasText(code,"the code must be not null");
		String regex = "^[0-9]{12}$";		
		if(!code.matches(regex))	
			return null;
		String hql = "from UserVoucher bv where bv.userId is not null and bv.code=?";
		Map<Integer,Object> params = new HashMap<Integer,Object>();
		params.put(0, Long.parseLong(code));		
		return userVoucherDao.get(hql, params);
	}
	public void setVoucherVerific(String code) {
		//核销之前再验证一次
		UserVoucher userVoucher = searchCode(code);
		Assert.notNull(userVoucher);
		Date date = new Date();
		userVoucher.setUseDate(date);
		userVoucher.setVerificated(true);
		//获取模板卡券设置其使用次数加一
		CardVoucher cardVoucher = userVoucher.getCardvoucher();
		cardVoucher.setUseTimes(cardVoucher.getUseTimes()+1);
		userVoucherDao.update(userVoucher);
		logger.info("To write off the success of the "+userVoucher.getCode()+"[Time="+(date.toString())+"]");

	}
	public List<UserVoucher> getStatisByName(VoucherPage voucherPage) {
		Assert.notNull(voucherPage);
		Assert.notNull(voucherPage.getBusinessId(), "businessId must be not null");
		Assert.hasText(voucherPage.getName(), "name must be not null");
		String hql = "from UserVoucher bv where bv.userId is not null and bv.cardvoucher.businessId=? and bv.cardvoucher.name=?";
		Map<Integer,Object> params = new HashMap<Integer,Object>();
		params.put(0, voucherPage.getBusinessId());
		params.put(1, voucherPage.getName().trim());

		return userVoucherDao.find(hql, params, voucherPage.getPage(), voucherPage.getRows());
	}
	public Long getTotalRowsByNmae(VoucherPage voucherPage) {
		String hql = "select count(*) from UserVoucher bv where bv.userId is not null and bv.cardvoucher.businessId=? and bv.cardvoucher.name=?";
		Map<Integer,Object> params = new HashMap<Integer,Object>();
		params.put(0, voucherPage.getBusinessId());
		params.put(1, voucherPage.getName().trim());
		return userVoucherDao.count(hql, params);
	}
	public Long acquireVoucher(String userId,Long voucherId) {
		Assert.notNull(voucherId);
		UserVoucher userVoucher = new UserVoucher();
		userVoucher.setId(CommonUtils.ranTime());
		userVoucher.setCode(CommonUtils.calenTime());
		userVoucher.setReceiveDate(new Date());
		//设置领取用户的openid
		userVoucher.setUserId(userId);
		//根据卡券模板的id获取卡券的数据库实例
		CardVoucher dboucher = voucherDao.get(CardVoucher.class, voucherId);
		//每次领取卡券时卡券的领取次数加一
		if(dboucher!=null){
			dboucher.setReceiveTimes(dboucher.getReceiveTimes()+1);
			userVoucher.setCardvoucher(dboucher);
			userVoucherDao.save(userVoucher);
		}
		return userVoucher.getCode();
	}

	public List<UserVoucher> getVerificRecords(VoucherPage voucherPage) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CardVoucher getVoucherByCode(UserVoucher userVoucher) {
		Assert.notNull(userVoucher);
		String hql = "from UserVoucher u where u.userId=? and u.code=?";
		Map<Integer,Object> params = new HashMap<Integer,Object>();
		params.put(0, userVoucher.getUserId());
		params.put(1, userVoucher.getCode());
		UserVoucher dbUVoucher = userVoucherDao.get(hql, params);
		if(dbUVoucher!=null)
			return dbUVoucher.getCardvoucher();

		return null;
	}
	@Override
	public List<CardVoucher> getVoucherByUid(String userId) {
		if(userId!=null && userId.length()>0){
			String hql = "from UserVoucher u where u.userId=?";
			Map<Integer,Object> params = new HashMap<Integer,Object>();
			params.put(0, userId);
			List<UserVoucher> uVouchers = userVoucherDao.find(hql, params);
			List<CardVoucher> vouchers = new ArrayList<CardVoucher>(uVouchers.size());
			for(UserVoucher userVoucher : uVouchers){
				vouchers.add(userVoucher.getCardvoucher());
			}
			return vouchers;
		}

		return null;
	}
	@Override
	public boolean getStateByCode(String code) {
		String hql = "from UserVoucher u where u.code=?";
		Map<Integer,Object> params = new HashMap<Integer,Object>(1);
		params.put(0, Long.parseLong(code));
		UserVoucher userVoucher = userVoucherDao.get(hql, params);
		return userVoucher.getVerificated();
	}
	
	@Override
	public UserVoucher findUserVoucher(String userId, Long voucherId) {
		Assert.hasText(userId);
		Assert.notNull(voucherId);
		String hql = "from UserVoucher where userId=? and voucherId=?";
		Map<Integer,Object> params = new HashMap<Integer,Object>(2);
		params.put(0, userId);
		params.put(1, voucherId);
		return userVoucherDao.get(hql, params);
	}



}
