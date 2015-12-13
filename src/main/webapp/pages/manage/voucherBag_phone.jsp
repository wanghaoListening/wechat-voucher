<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="format-detection" content="telephone=no" />

<%@ include file="/pages/public/header.jspf"%>
<script type="text/javascript" src="${pro}/js/phone.js"></script>
<title>你的卡包</title>
</head>
<body style="background-color: #f4f5f9;">
	<div class="container">
		<c:if test="${not empty vouchers}">
			<c:forEach items="${vouchers}" var="voucher">
				<div class="curved_box">
					<div class="yezi-bg"
						style="background-color: #${voucher.background}">
						<img src="${pro}/images/oflogo.png" class="yezi-img-l" alt="" />
						<span class="left yezi-logoname">${voucher.name}</span> <img
							src="${pro}/images/iconfont-youhuiquan.png" class="yezi-img-r"
							alt="" />
					</div>
					<div class="clearfix"></div>
					<div style="position: relative">
						<div class="yezi-deco"></div>
					</div>
					<div class="clearfix"></div>
					<div class="yezi-footer">
						<div class="left text-left ">
							<getname style="font-size: 14px;display:none;">${voucher.businessId}</getname>
							<shopname></shopname>
						</div>

						<div class="right text-center" style="margin-right: 5px;">
							<span> <fmt:formatDate value="${voucher.startTime}"
									pattern="yyyy.MM.dd" /> - <fmt:formatDate
									value="${voucher.endTime}" pattern="yyyy.MM.dd" />
							</span>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${empty vouchers}">

			<span id="cher" style="font-size: 20px;"> 主人暂时没领取过卡券 </span>
		</c:if>
	</div>


</body>
</html>