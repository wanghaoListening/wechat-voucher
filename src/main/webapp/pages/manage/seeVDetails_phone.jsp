<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看核销码</title>
</head>
<%@ include file="/pages/public/phone.jspf"%>
<script type="text/javascript" src="${pro}/js/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="${pro}/js/phone.js"></script>
<script type="text/javascript">
$(function(){
	$('#qrcode').qrcode({width: 135,height: 135,text: '${code}'});
	$("li p[id='code']").text(data);
});
</script>
</head>
<body style="background-color: #f4f5f9;">

	<div class="msg_card" style="background-color: #fff;margin-top:-10px;">
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
					<!--默认隐藏二维码的显示 -->
					<ul class="cardactive" id="qcode">
						<li class="active " style="background-color: #fff;">
							<div id="qrcode" style="text-align: center;"></div>
							<p style="text-align: center;letter-spacing:3px; color: #C5C5C5; font-size: 16px;">${code}</p>
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


</body>
</html>