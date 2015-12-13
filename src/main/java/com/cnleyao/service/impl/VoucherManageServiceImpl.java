package com.cnleyao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.cnleyao.dao.BaseDaoI;
import com.cnleyao.dto.Store;
import com.cnleyao.dto.VerificationPage;
import com.cnleyao.dto.VoucherPage;
import com.cnleyao.entity.CardVoucher;
import com.cnleyao.service.VoucherManageServiceI;
import com.cnleyao.utils.ApacheHttpClient;
import com.cnleyao.utils.CommonUtils;
import com.cnleyao.utils.HttpResponse;

@Service
@Transactional
public class VoucherManageServiceImpl implements VoucherManageServiceI{
	private static final Logger logger = Logger.getLogger(VoucherManageServiceI.class); 
	@Autowired
	private BaseDaoI<CardVoucher> voucherDao;

	public List<CardVoucher> findVouchers(VoucherPage voucherPage) {
		List<CardVoucher> vouchers = null;
		if(voucherPage!=null && voucherPage.getBusinessId()!=null){
			Long businessId = voucherPage.getBusinessId();
			int  page = voucherPage.getPage();
			int  rows = voucherPage.getRows();
			//查询ID为businessId的商户卡券模板
			String hql = "from CardVoucher c where c.businessId=?";
			Map<Integer,Object> params = new HashMap<Integer,Object>();
			params.put(0, businessId);
			vouchers = voucherDao.find(hql, params, page, rows);

		}
		//为卡券设置门店的名称
		if(vouchers != null){
			for(CardVoucher voucher : vouchers){
				ApacheHttpClient httpClient  = new ApacheHttpClient(); 
				HttpResponse response = httpClient.get("http://114.215.138.165/api/shops/"+voucherPage.getBusinessId());
				String data = response.getResponse();

				if(data !=null && !"".equals(data)){
					Store store = JSON.parseObject(data,Store.class);
					voucher.setStoreName(store.getName());
				}
			}
		}

		return vouchers;
	}

	public void saveVoucher(CardVoucher cardVoucher) {

		Assert.notNull(cardVoucher);
		Assert.notNull(cardVoucher.getVoucherId());
		CardVoucher dbVoucher = voucherDao.get(CardVoucher.class, cardVoucher.getVoucherId());
		dbVoucher.setName(cardVoucher.getName());
		dbVoucher.setBusinessId(cardVoucher.getBusinessId());
		dbVoucher.setBackground(cardVoucher.getBackground());
		dbVoucher.setTitle(cardVoucher.getTitle());
		dbVoucher.setSubtitle(cardVoucher.getSubtitle());
		dbVoucher.setStartTime(cardVoucher.getStartTime());
		dbVoucher.setEndTime(cardVoucher.getEndTime());
		dbVoucher.setGiftName(cardVoucher.getGiftName());
		voucherDao.update(dbVoucher);
		logger.info("finished to create voucher base information ");
	}

	public void saveVerificationMsg(VerificationPage verificationPage) {
		Long voucherId = verificationPage.getVoucherId();
		Assert.notNull(voucherId, "voucherId must not be null");
		CardVoucher cardVoucher = voucherDao.get(CardVoucher.class, voucherId);
		if(cardVoucher != null){
			cardVoucher.setLedVoucherLimit(verificationPage.getLedVoucherLimit());
			cardVoucher.setStock(verificationPage.getStock());
			String[] roles = verificationPage.getVoucherUseRole();
			StringBuilder sb = new StringBuilder();
			if(roles!=null){
				for(String role : roles){
					sb.append(role);
					sb.append(",");
				}
				sb.deleteCharAt(sb.length()-1);
				cardVoucher.setVoucherUseRole(sb.toString());
			}
			voucherDao.saveOrUpdate(cardVoucher);
		}


	}

	public List<CardVoucher> findVoucherByWhere(VoucherPage voucherPage) {
		//首先获取用户的而选择条件
		Long businessId = voucherPage.getBusinessId();
		String type = voucherPage.getType();
		String name = voucherPage.getName();
		//去除name两端的空格
		if(name!=null && !"".equals(name))
		name = name.trim();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", name);
		//三木表达式防止把空字符串当为int值注入sql语句
		params.put("type", (type==null||"".equals(type))?null:Integer.parseInt(type));
		params.put("businessId", businessId);

		return voucherDao.find(CardVoucher.class, params, voucherPage.getPage(), voucherPage.getRows());

	}

	public void saveVoucDetls(VerificationPage verificationPage) {
		Long voucherId = verificationPage.getVoucherId();
		if(verificationPage!=null && (voucherId!=null)){
			CardVoucher cardVoucher = voucherDao.get(CardVoucher.class, voucherId);
			//设置相应的卡圈数据
			cardVoucher.setUsageNotes(verificationPage.getUsageNotes());
			cardVoucher.setOfferDetail(verificationPage.getOfferDetail());
			cardVoucher.setPhone(verificationPage.getPhone());
			voucherDao.update(cardVoucher);
		}

	}

	public CardVoucher getVoucher(Long voucherId) {
		Assert.notNull(voucherId, "voucherId must be not null");
		return voucherDao.get(CardVoucher.class, voucherId);

	}


	public void setAdaptedStore(VerificationPage verificationPage) {

		CardVoucher cardVoucher = voucherDao.get(CardVoucher.class, verificationPage.getVoucherId());
		cardVoucher.setAdaptedStore(verificationPage.getAdaptedStore());
		voucherDao.update(cardVoucher);
	}

	public Long saveType(Integer type) {
		Assert.notNull(type, "type is null");
		Long voucherId = null;
		try {
			CardVoucher cardVoucher = new CardVoucher();
			voucherId = CommonUtils.ranTime();
			cardVoucher.setVoucherId(voucherId);
			cardVoucher.setType(type);
			voucherDao.save(cardVoucher);
		} catch (Exception e) {

			logger.error("create CardVoucher and save type of CardVoucher is failure"+voucherId);
		}
		logger.info("create CardVoucher and save type of CardVoucher is success"+voucherId);
		return voucherId;
	}

	public void activeVoucher(String voucherId) {
		Assert.hasText(voucherId, "want to active voucher but the voucherId is null");
		CardVoucher cardVoucher = voucherDao.get(CardVoucher.class,Long.parseLong(voucherId));
		if(cardVoucher!=null)
			cardVoucher.setState(CardVoucher.ON_STATE);
		voucherDao.update(cardVoucher);

		logger.info("Card coupon template"+cardVoucher.getVoucherId()+" is created and activated to complete[Time="+(new Date().toString())+"]");
	}

	public List<CardVoucher> getBusinsStatisByWhere(VoucherPage voucherPage) {
		//由于是通过text文本框传值，所以name和businessId可能两端会存在空格
		Assert.notNull(voucherPage, "voucherPage must be not null");
		String name = voucherPage.getName();
		if(name!=null&&!"".equals(name))
		name.trim();
		Long businessId = voucherPage.getBusinessId();
		Assert.notNull(businessId, "businessId must be not null");
		Map<String ,Object> params = new HashMap<String,Object>();
		params.put("name", name);
		params.put("businessId", businessId);
		
		return voucherDao.find(CardVoucher.class, params, voucherPage.getPage(), voucherPage.getRows());
	}

	public void disableVoucher(Long voucherId) {
		Assert.notNull(voucherId,"voucherId must bt not null");
		CardVoucher cardVoucher = voucherDao.get(CardVoucher.class, voucherId);
		if(cardVoucher!=null)
			cardVoucher.setState(CardVoucher.OFF_STATE);
	}
	/**
	 * 查询分四种条件
	 * 1，商户和卡券名都为空时就提交
	 * 2，商户为空卡券不为空时提交
	 * 3，商户和卡券都不为空时提交
	 * 4，商户不为空而卡券为空时提交
	 * */
	public Long getTotalCount(VoucherPage voucherPage) {
		Assert.notNull(voucherPage, "voucherPage must be not null");
		Long businessId = voucherPage.getBusinessId();
		String name = voucherPage.getName();

		String hql = null;
		Map<Integer,Object> params = new HashMap<Integer,Object>();
		if((businessId==null || "".equals(businessId))&&(name==null || "".equals(name))){
			hql = "select count(*) from CardVoucher";
			params = null;
		}
		else if((businessId==null || "".equals(businessId))&&(name!=null && !"".equals(name))){
			hql = "select count(*) from CardVoucher cv where cv.name=?";
			params.put(0, name);
		}
		else if((businessId!=null && !"".equals(businessId))&&(name!=null && !"".equals(name))){
			hql = "select count(*) from CardVoucher cv where cv.name=? and cv.businessId=?";
			params.put(0, name);
			params.put(1, businessId);
		}
		else{
			hql = "select count(*) from CardVoucher cv where cv.businessId=?";
			params.put(0, businessId);
		}
		return voucherDao.count(hql, params);
	}

	public void changeStock(CardVoucher voucher) {

		Assert.notNull(voucher);
		Assert.notNull(voucher.getVoucherId(),"voucherId must be not null");
		CardVoucher dboucher = voucherDao.get(CardVoucher.class, voucher.getVoucherId());
		dboucher.setStock(voucher.getStock());
		voucherDao.update(dboucher);
	}

	@Override
	public List<CardVoucher> findVoucherByIds(Long[] businessId) {
		Assert.notEmpty(businessId);
		String hql = "from CardVoucher where businessId IN (:ids)";
		return voucherDao.find(hql, businessId);
	}

	



}
