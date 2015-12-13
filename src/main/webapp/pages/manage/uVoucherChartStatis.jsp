<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户卡券图形统计</title>
<%@ include file="/pages/public/header.jspf"%>
<script type="text/javascript" src="${pro}/js/Chart.js"></script>
<script type="text/javascript" src="${pro}/js/voucher.js"></script>
</head>
<body>
	<div style="margin: auto;">
		<div id="canvas-holder">
			<canvas id="chart-area" width="300" height="300" />
		</div>
	</div>
</body>
</html>