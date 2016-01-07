<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="/pages/public/phone.jspf"%>
<link rel="stylesheet" type="text/css" href="${pro}/css/verification.css">
<script type="text/javascript" src="${pro}/js/phone.js"></script>
<title>卡券核销</title>
</head>
<body>
	<%@ include file="/pages/public/search.jspf"%>
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
