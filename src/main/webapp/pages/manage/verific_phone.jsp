<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/pages/public/phone.jspf"%>
<script type="text/javascript" src="${pro}/js/phone.js"></script>
</head>
<body style="background-color: #f4f5f9;">
	<div class="Search">
		<div class="inp">
				<input style="font-size: 18px; color: #000;" class="Search_Inp"
					type="text" id="searchvalue" name="code" /> <input
					class="Search_Btn" type="button" value="" /><input type="hidden"
					value="${userId}" name="userId"> <span
					onclick="clearvalue()"></span>
		</div>
	</div>
	<div class="msg_card" style="background-color: #fff;">
		<div class="msg_card_inner">
			<div class="js_preview msg_card_section shop disabled">
				<div class="shop_panel" id="img_preview" style="padding: 0px;">
					<div id="imglogo" style="height: 170px; background-color: green;">
						<div class="m10">
							<img src="${pro}/images/oflogo.png" class="img-logo" alt="" /> <span
								class="left logoname" style="font-size: 15px;">${voucher.name}</span>
						</div>
						<div class="clearfix"></div>
						<div class="text-center">
							<span style="font-size: 20px; color: #fff; letter-spacing: 3px;">${voucher.title}</span>
						</div>
						<div class="clearfix"></div>
						<div class="text-center " style="margin-top: 5px;">
							<span style="font-size: 14px; color: #fff; font-style: italic;">${voucher.subtitle}</span>
						</div>
						<div class="clearfix"></div>
						<div class="text-center" style="margin-top: 5px;">
							<span style="font-size: 14px; color: #fff;"> </span><span
								style="color: #fff;"> <fmt:formatDate
									value="${voucher.startTime}" pattern="yyyy.MM.dd" />-<fmt:formatDate
									value="${voucher.endTime}" pattern="yyyy.MM.dd" />
							</span>
						</div>
					</div>
				</div>
				<div class="deco"></div>
			</div>
			<div>
				<div style="height: 20px; background-color: #f4f5f9;"></div>

				<div class="list-group ul-li-a" style="border-radius: 0px;">
					<a href="${prourl}/getDiscDetail?voucherId=${voucher.voucherId}"
						class="list-group-item " style="border-radius: 0px;">查看详情 <span
						class=" right ul-li"></span>
					</a>
				</div>
			</div>

		</div>
	</div>
	<div class="m10 text-center" style="margin: 10px 10px 10px;">
		<input type="hidden" name="vericcode" value="${code}" />
		<button class="btn btn-block" style="background-color: #44b549;"
			id="cancelVoucher">确认核销</button>
	</div>

</body>
</html>