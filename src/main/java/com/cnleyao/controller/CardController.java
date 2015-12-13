
package com.cnleyao.controller;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnleyao.Message;
import com.cnleyao.Token;
import com.cnleyao.dto.Shop;
import com.cnleyao.dto.Store;
import com.cnleyao.dto.VerificationPage;
import com.cnleyao.dto.VoucherPage;
import com.cnleyao.entity.UserVoucher;
import com.cnleyao.entity.CardVoucher;
import com.cnleyao.service.UserVoucherServiceI;
import com.cnleyao.service.VoucherManageServiceI;
/**
 * <p>Title:卡券控制器类<p>
 * <p>Description:卡券管理的业务转向控制器<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年10月17日
 * */
@Controller
@RequestMapping("/cardManagement")
public class CardController extends BaseController{

	@Autowired
	private VoucherManageServiceI vService;
	@Autowired
	private UserVoucherServiceI uService;



	/**
	 * 进入卡券管理列表
	 * 此方法只接受get请求
	 * */
	@Token(save=true)
	@RequestMapping(value="/enterManagement",method={RequestMethod.GET,RequestMethod.POST})
	public String enterManagement(VoucherPage voucherPage,Model model,HttpSession session){
		List<CardVoucher> vouchers = vService.findVouchers(voucherPage);
		Long totalpage = vService.getTotalCount(voucherPage);
		//给voucherPage设置查询出的总记录数
		if(vouchers!=null){
			voucherPage.setCount(totalpage);
			voucherPage.calculate();
			model.addAttribute("vouchers", vouchers);
		}
		//将商户的ID放入session中以备商户统计时用
		session.setAttribute("businessId", voucherPage.getBusinessId());
		model.addAttribute("voucherPage", voucherPage);
		return "pages/manage/manage";
	}


	/**
	 * 获得卡券的类型
	 * @throws IOException 
	 * @throws ServletException 
	 * 没有选择在此方法上加remove=true对表单的重复提交做处理，
	 * 为了请求可以转向createVoucher页面
	 * 之所以先获取商户的id并从主框架获取商户所对应的门店，(目的先校验商户id的准确性，一旦校验失败咱返回)
	 * 主要是防止商户id伪造，并不是主程序传过来的
	 * */
	@RequestMapping("/voucherType")
	public String getVoucherType(Model model,Integer type,HttpSession session,HttpServletRequest request){
		try {
			//从 session中获取此用商户的id
			Long businessId = (Long) session.getAttribute("businessId");
			//获取此商户所对应的门店用于卡券商户的显示
			String dataShop = getHttpData("http://114.215.138.165/api/shops/"+businessId);
			Shop shop = JSON.parseObject(dataShop,Shop.class);
			String clientToken = request.getParameter("token");
			String serverToken = (String) session.getAttribute("token");
			Long voucherId = null;
			//如果不是重复提交则保存到数据库当中
			if(serverToken.equals(clientToken)){
				voucherId = vService.saveType(type);
				session.setAttribute("token", UUID.randomUUID().toString());
				//将voucherId放到session中以防客户重复提交之需
				session.setAttribute("voucherId", voucherId);
			}else{
				if(voucherId == null)
					voucherId = (Long) session.getAttribute("voucherId");
			}
			model.addAttribute("voucherId", voucherId);
			model.addAttribute("type", type);
			model.addAttribute("shop", shop);
			model.addAttribute("businessId", businessId);
		} catch (Exception e) {
			return "forward:enterManagement";
		}
		return "pages/manage/createVoucher";
	}

	/**
	 * 创建卡券
	 * */
	@Token(remove=true)
	@ResponseBody
	@RequestMapping("/createVoucher")
	public String createVoucher(CardVoucher cardVoucher,Model model,HttpSession session){
		vService.saveVoucher(cardVoucher);
		model.addAttribute("voucherId", cardVoucher.getVoucherId());
		//将在保存卡券类型时放到session中voucherId移除以免后续造成不必要的麻烦
		session.removeAttribute("voucherId");
		return Message.SUCCESS_MSG;
	}


	/**
	 * 卡券的核销设置
	 * */
	@ResponseBody
	@RequestMapping("/verificationSet")
	public String verificationSet(VerificationPage verificationPage,Model model){
		vService.saveVerificationMsg(verificationPage);
		model.addAttribute("voucherId", verificationPage.getVoucherId());
		return Message.SUCCESS_MSG;
	}

	/**
	 * 多个商户id对商户卡券模板进行查询
	 * */
	@ResponseBody
	@RequestMapping("/findVoucherByBids")
	public List<CardVoucher> findVoucherByIds(Long[] businessId){
		List<CardVoucher> cardVoucher = null;
		if(businessId.length>0&&businessId!=null)
			cardVoucher = vService.findVoucherByIds(businessId);
		return cardVoucher;
	}
	/**
	 * 对卡券进行条件选择筛选
	 * 
	 * */
	@RequestMapping("/filterVoucher")
	public String filterVoucher(VoucherPage voucherPage,Model model){
		List<CardVoucher> vouchers = vService.findVoucherByWhere(voucherPage);
		model.addAttribute("vouchers", vouchers);
		voucherPage.setCount(vouchers.size());
		model.addAttribute("voucherPage", voucherPage);
		return "pages/manage/manage";
	}

	/**
	 * 创建卡券的详情
	 * */
	@ResponseBody
	@RequestMapping("/createVoucDetls")
	public String createVoucDetls(VerificationPage verificationPage,Model model){
		try{
			vService.saveVoucDetls(verificationPage);
			return Message.SUCCESS_MSG;
		}catch(Exception e){
			return Message.ERROR_MSG;
		}
	}


	@RequestMapping("/getVerificRecords")
	public String getVerificRecords(VoucherPage voucherPage,Model model){

		List<UserVoucher> userVouchers = uService.getUserUseVoucher(voucherPage);
		Long totalRows = uService.getTotalRows(voucherPage);
		voucherPage.setCount(totalRows);
		model.addAttribute("userVouchers", userVouchers);
		model.addAttribute("voucherPage", voucherPage);

		return "pages/manage/verificRecord";
	}
	/**
	 * 更改卡券模板的库存
	 * */
	@ResponseBody
	@RequestMapping("/changeStock")
	public String changeStock(CardVoucher voucher){
		try{
			vService.changeStock(voucher);
			return Message.SUCCESS_MSG;
		}catch(Exception e){
			return Message.ERROR_MSG;
		}
	}

	@RequestMapping("/businStatis")
	public String useStatistical(VoucherPage voucherPage,Model model,HttpSession session){
		if(voucherPage==null || voucherPage.getBusinessId()==null){
			voucherPage = new VoucherPage();
			voucherPage.setBusinessId((Long)session.getAttribute("businessId"));
		}
		List<CardVoucher> vouchers = vService.findVouchers(voucherPage);
		model.addAttribute("vouchers", vouchers);
		Long totalCount = vService.getTotalCount(voucherPage);
		voucherPage.setCount(totalCount);
		model.addAttribute("voucherPage", voucherPage);
		return "pages/manage/businStatistic";

	}
	/**
	 * 设置使用的门店
	 * */
	@ResponseBody
	@RequestMapping("/setAdaptedStore")
	public String setAdaptedStore(VerificationPage verificationPage,Model model){

		if(verificationPage!=null&&(verificationPage.getVoucherId()!=null)&&(!"".equals(verificationPage.getVoucherId()))){
			Long voucherId = verificationPage.getVoucherId();
			vService.setAdaptedStore(verificationPage);
			model.addAttribute("voucherId", voucherId);

			return Message.SUCCESS_MSG;
		}
		return Message.ERROR_MSG;
	}
	/**
	 * 启用创建的卡券
	 * */
	@RequestMapping("/activeVoucher")
	public String activeVoucher(String businessId,Model model,String voucherId){
		vService.activeVoucher(voucherId);
		model.addAttribute("businessId", businessId);
		return "forward:enterManagement";
	}
	/**
	 * 得到商户卡券模板的统计
	 * */
	@RequestMapping("/getBusinsStatis")
	public String getBusinsStatis(VoucherPage voucherPage,Model model){
		List<CardVoucher> vouchers = vService.getBusinsStatisByWhere(voucherPage);
		Long totalCounts = vService.getTotalCount(voucherPage);
		voucherPage.setCount(totalCounts);
		String data = getHttpData("http://114.215.138.165/api/shops/"+voucherPage.getBusinessId());
		Shop  shop = JSON.parseObject(data, Shop.class);
		model.addAttribute("shop", shop);
		model.addAttribute("vouchers", vouchers);
		model.addAttribute("voucherPage", voucherPage);
		return "pages/manage/businStatistic";

	}
	/**
	 * 禁止卡券的功效
	 * */
	@ResponseBody
	@RequestMapping("/disableState")
	public String disableState(Long voucherId){
		try{
			vService.disableVoucher(voucherId);
			return Message.SUCCESS_MSG;
		}catch(Exception e){
			return Message.ERROR_MSG;
		}
	}

	/**
	 * 根据卡券的id获取卡券模板的基本信息用于预览
	 * */
	@ResponseBody
	@RequestMapping("/previewVoucher")
	public String previewVoucher(Long voucherId,HttpSession session){

		try {
			CardVoucher	cardVoucher =vService.getVoucher(voucherId);
			session.setAttribute("previewVoucher", cardVoucher);
			return Message.SUCCESS_MSG;
		} catch (Exception e) {

			return Message.ERROR_MSG;
		}
	}

	/**
	 * 得到某卡券模板的详细统计结果
	 * */
	@RequestMapping("/getDetailStatis")
	public String getDetailStatis(VoucherPage voucherPage,Model model){
		List<UserVoucher> userVouchers = uService.getUserUseVoucher(voucherPage);
		Long voucherCounts = uService.getTotalRows(voucherPage);
		voucherPage.setCount(voucherCounts);
		model.addAttribute("userVouchers", userVouchers);
		model.addAttribute("voucherPage", voucherPage);
		return "pages/manage/detailStatis";

	}

	/**
	 * 查询核销卡券的code
	 * */
	@ResponseBody
	@RequestMapping("/searchCode")
	public UserVoucher searchCode(String code){
		UserVoucher userVoucher = uService.searchCode(code);
		return userVoucher;
	}

	/**
	 * 
	 * 设置卡券核销
	 * */
	@ResponseBody
	@RequestMapping(value="/setVerific")
	public String setVerific(String code){
		try{
			uService.setVoucherVerific(code);
			return Message.SUCCESS_MSG;
		}catch(Exception e){
			return Message.ERROR_MSG;
		}
	}
	/**
	 * 根据卡券名来获取
	 * */
	@RequestMapping("/getStatisByName")
	public String getStatisByName(VoucherPage voucherPage,Model model){
		List<UserVoucher> userVouchers = uService.getStatisByName(voucherPage);
		Long totalRows = uService.getTotalRowsByNmae(voucherPage);
		voucherPage.setCount(totalRows);
		voucherPage.calculate();
		model.addAttribute("userVouchers", userVouchers);
		model.addAttribute("voucherPage", voucherPage);
		return "pages/manage/detailStatis";

	}

	/**
	 * 得到卡券图表统计的数据
	 * */
	@RequestMapping("/getChartStatis")
	public String getChartStatis(VoucherPage voucherPage){
		// TODO 此方法暂时没有完成
		return "pages/manage/uVoucherChartStatis";

	}

	/**
	 * 向主程序查询指定的商户的所有门店
	 * */
	@ResponseBody
	@RequestMapping("/findStoresByShop")
	public List<Store> findBusinessName(VoucherPage voucherPage, Model model){
		String data = getHttpData("http://114.215.138.165/api/"+voucherPage.getBusinessId()+"/stores");
		List<Store> stores = null;
		try{
			stores = JSON.parseArray(data, Store.class);
			model.addAttribute("stores", stores);
		}catch(Exception e){
			return null;
		}
		return stores;

	}

	/**
	 * 查询所有商户
	 * */
	@ResponseBody
	@RequestMapping("/findShops")
	public List<Shop> findShops(Model model){
		String data = getHttpData("http://114.215.138.165/api/shops");
		List<Shop> shops = null;
		try{
			shops = JSON.parseArray(data, Shop.class);
			model.addAttribute("shops", shops);
		}catch(Exception e){
			return null;
		}
		return shops;
	}

}
