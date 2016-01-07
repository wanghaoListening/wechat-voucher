<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/pages/public/phone.jspf"%>
<script type="text/javascript" src="${pro}/js/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="${pro}/js/phone.js"></script>
<!-- 微信关注js -->
<link rel="stylesheet" href="${pro}/css/adcss.css">
<script src="${pro}/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f4f5f9;">

	<div class="msg_card"
		style="background-color: #fff; margin-top: -10px;">
		<div class="msg_card_inner">

			<div class="js_preview msg_card_section shop disabled">
				<div class="shop_panel" id="img_preview" style="padding: 0px;">
					<div id="imglogo"
						style="height: 170px; background-color: #${voucher.background}">
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
							<span style="font-size: 14px; color: #fff;">有效期： </span><span
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
				<!--默认隐藏二维码的显示 -->
				<ul class="cardactive" style="display: none;" id="qcode">
					<li class="active " style="background-color: #fff;">
						<div id="qrcode" style="text-align: center;"></div>
						<p
							style="text-align: center; letter-spacing: 3px; color: #C5C5C5; font-size: 16px;"
							id="code"></p>
						<p style="text-align: center; color: #C5C5C5; font-size: 18px;">将本页面展示给店员看即可</p>
					</li>

				</ul>

				<div style="height: 20px; background-color: #f4f5f9;"></div>

				<div class="list-group ul-li-a" style="border-radius: 0px;">
					<a href="${phone}/getDiscDetail?voucherId=${voucher.voucherId}"
						class="list-group-item " style="border-radius: 0px;"> 优惠券详情 <span
						class=" right ul-li"></span>
					</a> <a href="#" class="list-group-item"> 查看公众号 <span
						class=" right ul-li"></span>
					</a> <a href="${phone}/getAdaptedStore?voucherId=${voucher.voucherId}"
						class="list-group-item" style="border-radius: 0px;"
						id="adaptStore_phone">适用门店 <span class=" right ul-li"></span>
					</a>

				</div>

			</div>

		</div>
	</div>
	<c:if test="${empty userVoucher}">
		<div class="m10 text-center" style="margin: 10px 10px 10px;">
			<form id="phoneQCode">
				<input type="hidden" name="voucherId" value="${voucher.voucherId}" />
				<input type="hidden" name="userId" value="${userId}" />
			</form>
			<button class="btn btn-block" style="background-color: #44b549;"
			id="acquireQCode" data-toggle="modal" data-target="#myModal">领取卡券</button>
		</div>
	</c:if>
	<c:if test="${not empty userVoucher}">
		<div class="m10 text-center" style="margin: 10px 10px 10px;">
			<button class="btn btn-block" style="background-color: #d9d6c3;"
				id="hasQCode">领取卡券</button>
		</div>
	</c:if>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content"
				style="background: #303030; background: url(${pro}/img/bgl.jpg); background-size: cover; margin: 10px auto; width: 80%; min-width: 300px; height: 420px;">
				<div class="modal-body">
					<div
						style="margin: 10px auto; height: 150px; width: 230px; text-align: center;">

						<div class="curved_box" style="width: 230px;">
							<div class="yezi-bg">
								<img src="${pro}/images/oflogo.png" class="yezi-img-l" alt="" /> <span
									class="left yezi-logoname" style="line-height: 85px;">${voucher.name}</span>
							</div>
							<div class="clearfix"></div>
							<div style="position: relative">
								<div class="yezi-deco"></div>
							</div>
							<div class="clearfix"></div>
							<div class="yezi-footer">
								<div class="left text-left ">
									<span style="font-size: 14px;" id="isf">
									</span>
								</div>
							</div>
						</div>
					</div>
					<div
						style="margin: 10px auto; height: 150px; width: 260px; text-align: center;">
						<div style="margin: auto; width: 260px;">
							<div class="thumbnail"
								style="max-height: 125px; max-width: 125px; float: left; margin: 0px 2px;">
								<img src="${pro}/img/erweima.jpg" width="125" height="125" alt="...">
							</div>
							<div class="thumbnail"
								style="max-height: 125px; max-width: 125px; float: left; margin: 0px 2px;">
								<img src="${pro}/img/zhiwen.jpg" width="125" height="125" alt="...">
							</div>
						</div>
					</div>
					<div style="margin-top: 5px;margin-left: 145px;">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>