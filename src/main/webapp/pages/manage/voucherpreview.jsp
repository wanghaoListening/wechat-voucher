<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/pages/public/header.jspf"%>
</head>
<body style="background-color: #f4f5f9;">
	<nav class="navbar navbar-inverse"
		style="margin-bottom:0px;width:auto;">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">

						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#" style="font-size: 13px;">关闭</a>
					<div class="phonehead" style="font-size: 13px;">
						<c:choose>
							<c:when test="${sessionScope.previewVoucher.type eq 1}">优惠券</c:when>
							<c:when test="${sessionScope.previewVoucher.type eq 2}">代金券 </c:when>
							<c:when test="${sessionScope.previewVoucher.type eq 3}">折扣券</c:when>
							<c:when test="${sessionScope.previewVoucher.type eq 4}">礼品券</c:when>
						</c:choose>
					</div>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Link </a></li>
						<li><a href="#">Link</a></li>

					</ul>
				</div>
			</div>

		</div>

	</div>
	</nav>
	<div class="msg_card" style="background-color: #fff;">
		<div class="msg_card_inner" id="previewVoucher">

			<div class="js_preview msg_card_section shop disabled">
				<div class="shop_panel" id="img_preview" style="padding: 0px;">
					<div id="imglogo" style="height: 170px; background-color: green;">
						<div class="m10">
							<img src="${pro}/images/oflogo.png" class="img-logo" alt="" /> <span
								class="left logoname" style="font-size: 15px;">${sessionScope.previewVoucher.name}</span>
						</div>
						<div class="clearfix"></div>
						<div class="text-center">
							<span style="font-size: 20px; color: #fff; letter-spacing: 3px;">
								${sessionScope.previewVoucher.title}
							</span>
						</div>
						<div class="clearfix"></div>
						<div class="text-center " style="margin-top: 5px;">
							<span style="font-size: 14px; color: #fff; font-style: italic;">
								${sessionScope.previewVoucher.subtitle}
							</span>
						</div>
						<div class="clearfix"></div>
						<div class="text-center" style="margin-top: 5px;">
							<span style="font-size: 14px; color: #fff;"> 
								有效期：
							</span>
							<span style="font-size: 14px; color: #fff;"> 
								<fmt:formatDate value="${sessionScope.previewVoucher.startTime}"
									pattern="yyyy.MM.dd"/>
							</span>
						    <span style="font-size: 14px; color: #fff;">-</span>
							<span style="font-size: 14px; color: #fff;"> 
								<fmt:formatDate value="${sessionScope.previewVoucher.endTime}"
									pattern="yyyy.MM.dd"/>
							</span>
								
						</div>
					</div>
				</div>
				<div class="deco"></div>
			</div>
			<div>
				<div style="height: 20px; background-color: #f4f5f9;"></div>

				<div class="list-group ul-li-a" style="border-radius: 0px;">
					<a href="#" class="list-group-item " style="border-radius: 0px;">
						优惠券详情 <span class=" right ul-li"></span>
					</a> <a href="#" class="list-group-item"> 查看公众号 <span
						class=" right ul-li"></span>
					</a> <a href="#" class="list-group-item" style="border-radius: 0px;"
						id="adaptStore_phone">适用门店 <span class=" right ul-li"></span>
					</a>

				</div>


			</div>

		</div>
	</div>
	<div class="m10 text-center" style="margin: 10px 10px 10px;">
		<button class="btn btn-block" style="background-color: #44b549;"
			id="acquireVoucher">领取卡券</button>
	</div>
</body>
</html>