<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商户卡券统计页</title>
<%@ include file="/pages/public/header.jspf"%>

</head>
<body>
<div class="cardmanage">
    <div class="minfo" >
        <div class="left">
            <h4>领券记录</h4>
        </div>
        <div class="right">
            <button class="btn btn-default" onclick='history.go(-1)'>返回</button>
        </div>
        <u class="clr"></u></div>

    <div style="height: 50px;">
    	<form action="${pageContext.request.contextPath}/cardManagement/getBusinsStatis" method="post">
    	<input type="hidden" name="businessId" value="${voucherPage.businessId}">
        <div class="form-inline">
            <div class="form-group">
                <label for="exampleInputName2">请输入商户名称</label>
                <input type="text" class="form-control" id="exampleInputName2" placeholder=" " name="businessName">
            </div>
            <div class="form-group" style="margin-left: 200px;">
                <label for="exampleInputEmail2">请输入卡券名称</label>
                <input type="text" class="form-control" id="exampleInputEmail2" placeholder="" name="name"> 
            </div>
            <button type="submit" class="btn btn-default " style="margin-left: 100px;">查询</button>
        </div>
        </form>
    </div>

    <div>
        <p>领取/使用记录</p>
        <table class="table table-striped table-bordered table-hover recordtable">
            <thead>
            <tr>
                <th>卡券名称</th>
                <th>卡券发行商户</th>
                <th>使用开始日期</th>
                <th>使用截止日期</th>
                <th>领取次数</th>
                <th>使用次数</th>
                <th>详情设置</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty vouchers}">
            <c:forEach items="${vouchers}" var="voucher" varStatus="status">
            <tr>
                <td>${voucher.name}</td>
                <td>${shop.name}</td>
                <td>
                	 <fmt:formatDate value="${voucher.startTime}" pattern="yyyy年MM月dd日"/>
                </td>
                <td>
                	<fmt:formatDate value="${voucher.endTime}" pattern="yyyy年MM月dd日"/>
                </td>
                <td>${voucher.receiveTimes}</td>
                <td>${voucher.useTimes}</td>
                <td>
                    <a href="${prourl}/getDetailStatis?voucherId=${voucher.voucherId}&businessId=${voucher.businessId}" class="btn btn-default">详细统计</a>
                    <a href="${prourl}/getVerificRecords?voucherId=${voucher.voucherId}" class="btn btn-default">核销记录</a>
                </td>
            </tr> 
            </c:forEach>          
            </c:if>
            </tbody>
        </table>
        <form action="${prourl}/businStatis" id="pageForm" method="post">
			<input type="hidden" name="businessId" value="${voucherPage.businessId}">
		</form>
		<%@ include file="/pages/public/pageView.jspf"%>
    </div>


</div>

</body>
</html>

   