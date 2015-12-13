<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/pages/public/header.jspf"%>
 <title>卡券详细统计</title>
<style>
.d-none{display:none;}
</style>	
</head>
<body>
	<div class="cardmanage">
		<div class="minfo">
			<div class="left">
				<h4>领券记录</h4>
			</div>
			<div class="right">
				<button class="btn btn-default" onclick='history.go(-1)'>返回</button>
			</div>
			<u class="clr"></u>
		</div>

		<div style="height: 50px;">
			<!-- 此宽度的500px是为图表统计按钮更好的在页面展示而设定的 -->
			<div class="form-inline">
				<form
					action="${prourl}/getStatisByName"
					method="post">
					<div class="form-group" style="margin-left: 3px;">
						<label for="exampleInputEmail2">请输入卡券名称</label> <input type="text"
							class="form-control" id="exampleInputEmail2" placeholder=""
							name="name"> <input type="hidden"
							value="${voucherPage.businessId}" name="businessId">
					</div>
					<button type="submit" class="btn btn-default "
						style="margin-left: 100px;">查询</button>
					
					<a href="#" class="btn btn-default " style="margin-left: 300px;background:#44b549;color:#fff"></a>
				</form>
				
			</div>

		</div>

		<div>
			<p>领取/使用记录</p>
			<table
				class="table table-striped table-bordered table-hover recordtable">
				<thead>
					<tr>
						<th>卡券核销码</th>
						<th>领取人</th>
						<th>领取时间</th>
						<th>使用日期</th>
						<th>使用门店</th>
						<th>来源</th>
						<th>领取人信息</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty userVouchers}">
						<c:forEach items="${userVouchers}" var="userVoucher"
							varStatus="status">
							<tr>
								<td>${userVoucher.code}</td>
								<td>${userVoucher.userId}</td>
								<td>
								<fmt:formatDate value="${userVoucher.receiveDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
								<fmt:formatDate value="${userVoucher.useDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>${userVoucher.useStore}</td>
								<td><c:if test="${userVoucher.ownUse eq true}">
						自己领取
					</c:if> <c:if test="${userVoucher.ownUse eq false}">
						转赠他人
					</c:if></td>
								<td><a href="#" class="btn btn-default">查看</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<form action="${prourl}/getDetailStatis" id="pageForm" method="post">
				<input type="hidden" name="voucherId"
					value="${voucherPage.voucherId}">
			</form>
			<%@ include file="/pages/public/pageView.jspf"%>
		</div>


	</div>
	

</body>
</html>