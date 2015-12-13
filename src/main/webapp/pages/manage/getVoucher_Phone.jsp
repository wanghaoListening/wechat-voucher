<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/pages/public/header.jspf"%>
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi, user-scalable=no"/>
<meta name="format-detection" content="telephone=no" />
<script type="text/javascript" src="${pro}/js/phone.js"></script>
</head>
<body style="background-color: #f4f5f9;">

	<div class="msg_card" style="background-color: #fff;">
		<div class="msg_card_inner">

			<div class="js_preview msg_card_section shop disabled">
                <div class="shop_panel" id="img_preview" style="padding: 0px;">
                   <div id="imglogo" style="height: 170px;background-color: #${voucher.background};">
                        <div class="m10">
                            <img src="${pro}/images/oflogo.png" class="img-logo" alt=""/>
                            <span class="left logoname" style="font-size: 15px;">${voucher.name}</span>
                        </div>
                       <div class="clearfix"></div>
                       <div class="text-center">
                           <span style="font-size: 20px;color: #fff;letter-spacing: 3px;">${voucher.title}</span>
                       </div>
                       <div class="clearfix"></div>
                       <div class="text-center " style="margin-top: 5px;">
                           <span style="font-size: 14px; color: #fff; font-style: italic;">${voucher.subtitle}</span>
                       </div>
                       <div class="clearfix"></div>
                       <div class="text-center" style="margin-top: 5px;">
                           <span style="font-size: 14px;color: #fff;">有效期：
                           </span><span style="color: #fff;">
                           <fmt:formatDate value="${voucher.startTime}"
									pattern="yyyy.MM.dd"/>-<fmt:formatDate value="${voucher.endTime}"
									pattern="yyyy.MM.dd"/>
                           </span>
                       </div>
                   </div>
                </div>
                <div class="deco"></div>
            </div>
			<div>
					<ul class=" cardactive" style="display: none;" id="vcode">
						<li class="active " style="background-color: #fff;">
							<div
								style="background-color: #fff !important; border: none; text-align: center; font-size: 18px;">
								<h1 style="font-size:36px;" class="code"></h1>
							</div>
							<p style="text-align: center; color: #C5C5C5; font-size: 18px;">将本页面展示给店员看即可</p>
						</li>

					</ul>
				<div style="height: 20px; background-color: #f4f5f9;"></div>

				<div class="list-group ul-li-a" style="border-radius: 0px;">
					<a href="${phone}/getDiscDetail?voucherId=${voucher.voucherId}" class="list-group-item " 
						style="border-radius: 0px;"> 优惠券详情 <span class=" right ul-li"></span>
					</a> <a href="#" class="list-group-item"> 查看公众号 <span
						class=" right ul-li"></span>
					</a> <a href="${phone}/getAdaptedStore?voucherId=${voucher.voucherId}" class="list-group-item" style="border-radius: 0px;" id="adaptStore_phone">适用门店
						<span class=" right ul-li"></span>
					</a>

				</div>


			</div>

		</div>
	</div>
		<div class="m10 text-center" style="margin: 10px 10px 10px;">
			<form id="phoneCode">
				<input type="hidden" name="voucherId" value="${voucher.voucherId}" />
				<input type="hidden" name="userId" value="${userId}"/> 
			</form>
			<button class="btn btn-block" style="background-color: #44b549;"
				id="acquireCode">领取卡券</button>
		</div>
</body>
</html>