<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>核销记录</title>
<%@ include file="/pages/public/header.jspf"%>
<script type="text/javascript"
	src="${pro}/js/voucher.js"></script>
</head>
<body>
	<div class="cardmanage">
		<div class="minfo">
			<div class="left">
				<h4>领券记录</h4>
			</div>
			<div class="right">
				<button class="btn btn-default" onclick="history.go(-1)">返回</button>
			</div>
			<u class="clr"></u>
		</div>

		<div style="height: 50px;">
			<div class="left">
				<button type="button" class="btn btn-default btn-lg"
					data-toggle="modal" data-target="#normal">
					<span class="madd">&nbsp;&nbsp;&nbsp;&nbsp;卡券核销</span>
				</button>
				<!-- Modal -->
				<div class="modal fade" id="normal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content" style="width: 780px;">
							<div class="modal-header" style="background-color: #f4f5f9;">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">卡券核销</h4>
							</div>
							<div class="modal-body">
								<div style="margin: 20px 30px;">
									<div class="row">

										<div class="col-sm-12">
											<div class="input-group">
												<input type="text" class="form-control" placeholder="请输入卡券号" id="verifictext">
												<span class="input-group-btn">
													<button class="btn btn-default" type="button" id="verificbutton">搜索</button>
												</span>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="left media_preview_area"
												style="height: 380px; width: 320px; margin-top: 20px;">
												<div class="msg_card">
													<div class="msg_card_inner">
														<p class="msg_title">
															
														</p>
														<div class="js_preview msg_card_section shop disabled" id="js_preview_verific">
															<div id="imglogo"
																style="height: 150px; background-color: green;">
																<div class="m10">
																	<img src="${pro}/images/oflogo.png" class="img-logo"
																		alt="" /> <span class="left logoname" id="verific_name" style="font-size: 15px;"></span>
																</div>
																<div class="clearfix"></div>
																<div class="text-center">
																	<span
																		style="font-size: 20px; color: #fff; font-style: italic;letter-spacing: 3px;" class="verific_title"></span>
																</div>
																<div class="clearfix"></div>
																<div class="text-center " style="margin-top: 5px;">
																	<span style="font-size: 14px; color: #fff; font-style: italic;" class="verific_subtitle"></span>
																</div>
																<div class="clearfix"></div>
																<div class="text-center">
																	<span style="font-size: 14px; color: #fff;" class="validDate">
																	</span>
																	<span style="color: #fff;" class="verific_startTime"></span>
																	<span style="color: #fff;" class="spaceMark"></span>		
																	<span style="color: #fff;" class="verific_endTime"></span>
																</div>
															</div>
															<div class="deco"></div>
														</div>
														<div style="font-size: 18px;">
															<ul class="nav nav-tabs cardactive">
																<li class="active "
																	style="background-color: #fff; width: 317px;">
																	<h3 style="width: 317px; background-color: #fff !important; border: none; text-align: center" id="h3code">
																	</h3>
																	<p style="text-align: center; color: #C5C5C5;"></p>
																</li>
															</ul>
														</div>
													</div>
												</div>


											</div>
										</div>

										<div class="col-sm-6">
											<div class="left" style="margin-top: 20px; width: 380px;">
												<div class="step_list_wrp">
													<ul class="step_list">
														<li class="step_item step_box no_extra_up"><div
																class="step_inner">
																<div class="step_content">
																	<h4>搜索卡券</h4>
																	<div class="step_desc">请顾客出示卡券，输入卡券号</div>
																</div>
															</div> <span class="icon_step_line icon_step_line_up"></span><span
															class="icon_step_line icon_step_line_dcoden"></span><span
															class="icon_step_wrapper"><i
																class="icon_step_list finish">1</i></span></li>
														<li class="step_item step_box"><div
																class="step_inner">
																<div class="step_content">
																	<h4>校检</h4>
																	<div class="step_desc">微信提供有效期验证，其他信息请自行核对</div>
																</div>
															</div> <span class="icon_step_line icon_step_line_up"></span><span
															class="icon_step_line icon_step_line_dcoden"></span><span
															class="icon_step_wrapper"><i
																class="icon_step_list finish">2</i></span></li>
														<li class="step_item step_box no_extra_dcoden"><div
																class="step_inner">
																<div class="step_content">
																	<h4>销券完成</h4>
																	<div class="step_desc">查看销券结果，顾客将同时收到销券成功通知</div>
																</div>
															</div> <span class="icon_step_line icon_step_line_up"></span><span
															class="icon_step_line icon_step_line_dcoden"></span><span
															class="icon_step_wrapper"><i
																class="icon_step_list step">3</i></span></li>
													</ul>
												</div>
												<div style="text-align: center;">
													<button class="button" data-dismiss="modal" id="finishVerific">点击完成核销</button>
												</div>
											</div>
										</div>


									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="right" style="line-height: 30px; margin: 10px 0;">
				<form action="${prourl}/getVerificRecords" method="POST">

					<input type="text" name="condition" style="height: 30px;" value=""
						placeholder="请填写卡券号或卡券名称" class="px"> <input
						type="submit" value="查询" style="font-size: 14px;" class="button">

				</form>
			</div>
		</div>


		<table
			class="table table-striped table-bordered table-hover recordtable">
			<thead>
				<tr>
					<th>卡券号</th>
					<th>核销时间</th>
					<th>卡券名称</th>
					<th>卡券类型</th>
					<th>门店</th>
					<th>核销员</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty userVouchers}">
					<c:forEach items="${userVouchers}" var="userVoucher"
						varStatus="status">
						<tr>
							<td>${userVoucher.code}</td>
							<td>
							<c:if test="${empty userVoucher.useDate}">暂未核销</c:if>
							<fmt:formatDate value="${userVoucher.useDate}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${userVoucher.cardvoucher.name}</td>
							<td><c:choose>
									<c:when test="${userVoucher.cardvoucher.type eq 1}">优惠券</c:when>
									<c:when test="${userVoucher.cardvoucher.type eq 2}">代金券 </c:when>
									<c:when test="${userVoucher.cardvoucher.type eq 3}">折扣券</c:when>
									<c:when test="${userVoucher.cardvoucher.type eq 4}">礼品券</c:when>
								</c:choose>
							</td>
							<td>
								${userVoucher.cardvoucher.adaptedStore}
							</td>
							<td>管理员</td>
							<td><a href="#" class="btn btn-default">删除</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>

	<%@ include file="/pages/public/pageView.jspf"%>

	</div>

</body>
</html>