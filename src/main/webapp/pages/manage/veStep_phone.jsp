<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<%@ include file="/pages/public/header.jspf"%>

<link rel="stylesheet" type="text/css" href="${pro}/css/verification.css">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,user-scalable=no">
<meta name="format-detection" content="telephone=no" />
<title>卡券核销</title>
<link rel="stylesheet" href="css/verification.css" />
</head>
<body>
	
	<!--   搜索框   -->
	<div class="Search">
		<div class="inp">
			<form action="${phone}/verificVoucher" method="post">
			<input style="font-size:18px;" class="Search_Inp" type="text" placeholder="请输入优惠券号或会员卡号" name="code"/> <input
				class="Search_Btn" type="submit" value="${userId}" />
			</form>
		</div>
	</div>
	<!--   footer   -->
	<footer class="footer">
	<h2>第一步、搜索卡券</h2>
	<p>请顾客出示卡券，输入优惠券号或会员卡号</p>
	<h2>第二步、验证卡券信息</h2>
	<p>验证卡券是否有效</p>
	<h2>第三步、使用完成</h2>
	<p>查看使用结果，顾客将同时收到优惠券核销或会员卡使用通知</p>
	</footer>
</body>
</html>
