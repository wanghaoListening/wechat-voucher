<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>卡券管理</title>
<%@ include file="/pages/public/header.jspf"%>
<script type="text/javascript" src="${pro}/js/manage.js"></script>
</head>
<body>
	<div class="cardmanage">
		<div class="minfo">
			<h4>卡券管理</h4>
			<u class="clr"></u>
		</div>
		<div class="alert boxcolor" role="alert">
			“普通卡券”可被粉丝领取。 “赠送券”无法领取，设置“开卡赠送”时可选择使用 <br>
			“状态”若显示“活动中”则说明此优惠券在微活动优惠券里使用中，会员卡里将不再显示此优惠券，只能在微活动优惠券中抽取。
		</div>
		<div style="height: 50px;">
			<div class="left">
				<button type="button" class="btn btn-default btn-lg"
					data-toggle="modal" data-target="#normal">
					<span class="madd">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;创建卡券</span>
				</button>
				<a href="${prourl}/getBusinsStatis?businessId=${voucherPage.businessId}" class="btn btn-default "
					style="height: 38px; margin-left: 160px;">卡券统计</a>
				
				<!-- Modal -->
				<form id="fmanage"
					action="${prourl}/voucherType"
					method="get">
					<input type="hidden" value="${voucherPage.businessId}"
						name="businessId">
						<input type="hidden" name="token" value="${sessionScope.token}" /> 
					<div class="modal fade" id="normal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content" style="width: 680px;">
								<div class="modal-header" style="background-color: #f4f5f9;">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">选择卡券类型</h4>
								</div>
								<div class="modal-body">

									<div class="modeldiv">
										<div class="">

											<div></div>
											<label for="" class="m10 font-14">选择你要创建的卡券类型</label>
											<div id="check_div">
												<label class="radio-inline"> <input type="radio"
													name="type" id="inlineRadio3" value="1" checked="checked">
													优惠券
													<p class="frm_tips">即“优惠券”，普通优惠券</p>
												</label> <br /> <label class="radio-inline"> <input
													type="radio" name="type" id="inlineRadio5" value="2">
													代金券
													<p class="frm_tips">可为用户提供抵扣现金服务。可设置成为“满*元，减*元”</p>
												</label> <br> <label class="radio-inline"> <input
													type="radio" name="type" id="inlineRadio5" value="3">
													折扣券
													<p class="frm_tips">可为用户商品折扣的 ”</p>
												</label> <br> <label class="radio-inline"> <input
													type="radio" name="type" id="inlineRadio4" value="4">
													礼品券
													<p class="frm_tips">可为用户提供消费送赠品服务</p>
												</label> <br />

											</div>

										</div>
									</div>

								</div>
								<div class="modal-footer " style="text-align: center;">
									<input type="submit" class="btn " value="确定"
										style="background-color: #44b549; color: #fff;"
										id="type_submits">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>

								</div>
							</div>
						</div>
					</div>
				</form>
			</div>

			<div class="right" style="line-height: 30px; margin: 10px 0;">
				<form
					action="${prourl}/filterVoucher"
					method="POST" class="filterVoucher">
					<select name="type">
						<option value="" selected="selected">请选择卡券类型</option>
						<option value="1">优惠卡券</option>
						<option value="2">代金卡券</option>
						<option value="3">折扣卡券</option>
						<option value="4">礼品卡券</option>
					</select> 
					<select name="businessId">
						<option value="" selected="">请选择发券商户</option>
					</select>  
					<input type="text" name="name" value="" placeholder="请填写卡券名称"
						class="px"> <input type="submit" value="查找"
						style="font-size: 14px;" class="button">

				</form>
				
			</div>
		</div>

		<div class="panel panel-default" id="panel-style"
			style="height: 710px; width: 1000px; margin-bottom: 0px;">
			<!-- table header-->
			<div class="panel-heading">
				<table class="table table-striped ">
					<thead>
						<tr class="tenter table-font">
							<th style="width: 100px;">卡券类型</th>
							<th style="width: 150px;">卡券名称</th>
							<th style="width: 120px;"><abbr title="此类卡券模板的总共的创建数量">
									<span class="mque"
									style="font-size: 14px; margin-bottom: 5px; font-weight: bold;">卡券库存</span>
							</abbr></th>
							<th style="width: 100px;">领取次数</th>
							<th style="width: 120px;">使用次数</th>
							<th style="width: 100px;">状态</th>
							<th style="width: 170px; text-align: center;">操作</th>
						</tr>


					</thead>
				</table>
			</div>
			<!-- table context-->
			<div class="panel-body" style="padding: 0 18px;">
				<div class="info-box border-none"
					style="height: 202px; margin-top: 5px;">
					<table class="table table-hover table-condensed tableinfo"
						style="margin-top: -17px;">
						<tbody class="line30">
							<c:if test="${not empty vouchers}">
								<c:forEach items="${vouchers}" var="voucher" varStatus="status">
									<tr id="${voucher.voucherId}">
										<td style="width: 110px;"><c:choose>
												<c:when test="${voucher.type eq 1}">优惠券</c:when>
												<c:when test="${voucher.type eq 2}">代金券 </c:when>
												<c:when test="${voucher.type eq 3}">折扣券</c:when>
												<c:when test="${voucher.type eq 4}">礼品券</c:when>
											</c:choose></td>
										<td style="width: 170px;">${voucher.name}</td>
										<td style="width: 150px; line-height: 30px !important;"><span id="stockspan">${voucher.stock}</span>
											<br /> <input type="text" name="stock"
											style="width: 40px; height: 36px;" class="setinput_stock"/> <input type="button"
											class="btn btn-default" value="确定" name="${voucher.voucherId}"/></td>
										<td style="width: 110px;">${voucher.receiveTimes}</td>
										<td style="width: 125px;">${voucher.useTimes}</td>
										<td style="width: 100px; color: #44b549;" class="state">
											<c:if test="${voucher.state eq true}">启用</c:if> <c:if
												test="${voucher.state eq false}">禁用</c:if>
										</td>
										<td><a
											href="${prourl}/getDetailStatis?voucherId=${voucher.voucherId}"
											class="btn btn-default">统计</a> <a href="#"
											class="btn btn-default" id="previewVoucher" name="${voucher.voucherId}">预览</a> 
											<a name="${voucher.voucherId}"
											class="btn btn-default disableState">禁用</a></td>
									</tr>
								</c:forEach>
							</c:if>

						</tbody>
					</table>

				</div>


			</div>
		
		</div>
		<form action="${prourl}/enterManagement" id="pageForm" method="post">
			<input type="hidden" name="businessId" value="${voucherPage.businessId}">
		</form>
		<%@ include file="/pages/public/pageView.jspf"%>
		
	</div>
</body>
</html>