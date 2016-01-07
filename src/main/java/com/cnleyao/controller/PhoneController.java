package com.cnleyao.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnleyao.Message;
import com.cnleyao.dto.Shop;
import com.cnleyao.entity.CardVoucher;
import com.cnleyao.entity.UserVoucher;
import com.cnleyao.service.UserVoucherServiceI;
import com.cnleyao.service.VoucherManageServiceI;

@Controller
@RequestMapping("/phone")
public class PhoneController extends BaseController{

	@Autowired
	private VoucherManageServiceI vService;
	@Autowired
	private UserVoucherServiceI uService;
	//微信的APPID和
	public static final String APPID = "wxa316d1ce30f2c952";
	public static final String SECRET = "0d08df1b5bd02849588b3083ad4ef56d";
	/**
	 * 引导用户授权获取用户的openID，
	 * */
	@RequestMapping(value="/oauthUser/{voucherId}")
	public String oauthUser(@PathVariable Long voucherId,String code,String state,RedirectAttributes rAtt){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		String rUrl = url.replace("APPID", APPID)//
				.replace("SECRET", SECRET)//
				.replace("CODE", code);
		String data = getHttpData(rUrl);
		JSONObject jsonObject = JSONObject.parseObject(data);
		//将领取卡券的openid转换成userId
		String userId = jsonObject.getString("openid");
		rAtt.addAttribute("voucherId", voucherId);
		rAtt.addAttribute("userId", userId);
		return "redirect:/phone/getVoucherTemplate";

	}
	/**
	 * 获取卡券模板(查看卡券模板就提示用户，你是否已经领取，是否有权限领取)
	 * */
	@RequestMapping("/getVoucherTemplate")
	public String getVoucherTemplate(String userId,Long voucherId,Model model){
		CardVoucher voucher = vService.getVoucher(voucherId);
		//根据用户的id和模板id查看此用户是否已经获取了卡券
		UserVoucher userVoucher = uService.findUserVoucher(userId,voucherId);
		if(userVoucher!=null){
			model.addAttribute("userVoucher", userVoucher);
		}
		model.addAttribute("voucher", voucher);
		model.addAttribute("userId", userId);

		return "pages/manage/qCodeVec_Phone";

	}
	/**
	 * 1,先判断用户是否已经关注了公众号
	 * 如果用户没有关注公众号则要引导用户关注
	 * 用户获取卡券的接口(创建卡券并返回卡券的code)
	 * */
	@RequestMapping("/acquireVoucher")
	public @ResponseBody
	int acquireVoucher(String userId,Long voucherId,Model model){
		int isf = focusGZH(userId);
		model.addAttribute("isf", isf);
		uService.acquireVoucher(userId,voucherId);
		return isf;
	}

	/**
	 * 判断用户是否关注公众号
	 * */
	private Integer focusGZH(String userId) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		String rUrl = url.replace("ACCESS_TOKEN", getAccessToken())//
				.replace("OPENID", userId);
		String rText = getHttpData(rUrl);
		JSONObject jsonObject = JSONObject.parseObject(rText);
		return jsonObject.getInteger("subscribe");
	}
	/**
	 * 获取accesstoken
	 * **/
	private String getAccessToken() {
		String tokenDate = getHttpData("http://i.cnleyao.com/api/access_token");
		JSONObject jsonObject = JSONObject.parseObject(tokenDate);

		return jsonObject.getString("access_token");
	}
	/**
	 * 微信端获取卡券的详细使用信息
	 * **/
	@RequestMapping("/getDiscDetail")
	public String getDiscDetail(Long voucherId,Model model){
		CardVoucher voucher = vService.getVoucher(voucherId);
		model.addAttribute("voucher", voucher);
		return "pages/manage/discDetails_phone";
	}

	/**
	 * 获取此卡券所适应的门店
	 * */
	@RequestMapping("/getAdaptedStore")
	public String getAdaptedStore(Long voucherId,Model model){
		//TODO 此卡券适应门店的功能暂时未完成
		return "pages/manage/adaptedStore_phone";
	}
	/**
	 * 用户进入进行核销(未领取卡券的用户依然允许它进入)
	 * */
	@RequestMapping("/enterVeric")
	public String vericVoucher(String userId,Model model){

		model.addAttribute("userId", userId);
		return "pages/manage/veStep_phone";
	}

	/**
	 * 根据卡券的code和userId查询卡券
	 * */
	@RequestMapping("/verificVoucher")
	public String verificVoucher(UserVoucher userVoucher,Model model){
		CardVoucher voucher = uService.getVoucherByCode(userVoucher);
		model.addAttribute("voucher", voucher);
		model.addAttribute("code", userVoucher.getCode());
		return "pages/manage/verific_phone";

	}

	/**
	 * 对卡券进行核销
	 * */

	@RequestMapping("/cancelVoucher")
	public @ResponseBody
	String cancelVoucher(String code){
		boolean b = uService.getStateByCode(code);
		//证明卡券之前已被核销过
		if(b){
			return Message.WARN_MSG;
		}else{
			uService.setVoucherVerific(code);
		}
		return Message.SUCCESS_MSG;
	}

	/**
	 * 用户查看自己的卡包
	 * */
	@RequestMapping("/seeVoucherBag")
	public String seeVoucherBag(String userId,Model model){
		List<CardVoucher> vouchers = uService.getVoucherByUid(userId);
		model.addAttribute("vouchers", vouchers);
		model.addAttribute("userId",userId);
		return "pages/manage/voucherBag_phone";
	}
	/**
	 * 
	 * */
	@RequestMapping("/getShopName")
	public @ResponseBody
	Shop getShopName(Long businessId){
		Shop store = null;
		String data = getHttpData("http://114.215.138.165/api/shops/"+businessId);
		if(data!=null && data.length()>0){
			store = JSON.parseObject(data,Shop.class);
		}
		return store;
	}

	/**
	 * 查看卡包卡券的详细及核销码
	 * **/
	@RequestMapping("/seeVDetails")
	public String seeVDetails(Long voucherId,String userId,Model model){
		CardVoucher voucher = vService.getVoucher(voucherId);
		Long code = uService.acquireVoucher(userId,voucherId);
		model.addAttribute("voucher",voucher);
		model.addAttribute("code", code);
		return "pages/manage/seeVDetails_phone";

	}


}
