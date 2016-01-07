<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/pages/public/header.jspf"%>
<!-- 颜色选择器 -->
<%@ include file="/pages/public/colorselcet.jspf"%>
<script type="text/javascript" src="${pro}/js/bootstrap-select.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pro}/css/bootstrap-select.min.css">
<title>创建卡券</title>
</head>
<body>
	<div class="cardmanage">
		<div class="minfo">
			<div class="left">
				<h4>卡券设置</h4>
			</div>
			<div class="right">
				<button class="btn btn-default" onclick='history.go(-1)'
					type="button">返回</button>
			</div>
			<u class="clr"></u>
		</div>
		<div>
			<div class="left media_preview_area"
				style="height: 515px; width: 320px;">
				<div class="msg_card">
					<div class="msg_card_inner">
						<p id="typevalue" class="msg_title"></p>
						<div class="js_preview msg_card_section shop disabled"
							id="img_preview" style="padding: 0px;">
							<div id="imglogo" style="height: 150px; background-color: green;">
								<div class="m10">
									<img src="${pro}/images/oflogo.png" class="img-logo" alt="" />
									<span class="left logoname name" style="font-size: 15px;">卡券名称示例</span>
								</div>
								<div class="clearfix"></div>
								<div class="text-center">
									<span
										style="font-size: 20px; color: #fff; letter-spacing: 3px;"
										class="title">卡券标题示例</span>
								</div>
								<div class="clearfix"></div>
								<div class="text-center " style="margin-top: 3px;">
									<span style="font-size: 14px; color: #fff; font-style: italic;"
										class="subtitle">卡券副标题示例</span>
								</div>
								<div class="clearfix"></div>
								<div class="text-center">
									<span style="font-size: 14px; color: #fff;">有效期：</span> <span
										style="color: #fff;" class="startTime">2015.10.10</span> <span
										style="color: #fff;" class="">-</span> <span
										style="color: #fff;" class="endTime">2015.11.11</span>
								</div>
								<div class="deco"></div>
							</div>

						</div>
						<div>
							<ul id="myTab" class="nav nav-tabs cardactive">

								<li class="active "
									style="background-color: #fff; width: 317px;"><a
									href="#home" data-toggle="tab"
									style="width: 317px; background-color: #fff !important; border: none; text-align: center">
										基本设置 </a></li>
								<li class="clearfix li"><a href="#checkset"
									data-toggle="tab" id="checksets"
									style="width: 320px; text-align: center; color: #000;">核销设置</a>
								</li>
								<li class="clearfix li "><a href="#details"
									data-toggle="tab" id="voucherdetail"
									style="width: 320px; text-align: center; color: #000;">卡券详情</a>
								</li>
								<li class="clearfix li"><a href="#usestore"
									data-toggle="tab" id="adaptedstore"
									style="width: 320px; text-align: center; color: #000;">适用门店</a>
								</li>
								<li class="clearfix li"><a href="#see" data-toggle="tab"
									id="picsee"
									style="width: 320px; text-align: center; color: #000;">卡片预览</a>
								</li>
							</ul>
							<div class="m10 text-center">
								<form action="${prourl}/activeVoucher" method="post">
									<input type="hidden" value="${businessId}" name="businessId">
									<input type="hidden" value="${voucherId}" name="voucherId">
									<button class="btn btn-default" id="savevoucherfull">保存激活卡券</button>
								</form>
							</div>

						</div>

					</div>
				</div>




			</div>
			<div class="right setright"
				style="height: 515px; width: 660px; position: relative;">
				<div style="margin: 10px;">

					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade in active" id="home">

							<!-- Start 顶部信息-->
							<div class="minfo">
								<div class="left">
									<h5 style="color: #AAAAAA;">基本信息</h5>
									<h5 style="color: red">注意:(卡券基本信息一旦保存便不能修改)</h5>
								</div>
								<u class="clr"></u>
							</div>
							<!-- End 顶部信息-->
							<!--Start 基本信息 form 表单-->
							<form name="base" id="createVoucherform" class="form-horizontal">
								<input type="hidden" value="${voucherId}" name="voucherId">
								<input type="hidden" name="token" value="${sessionScope.token}" />
								<div class="form-group">
									<label for="name" class="col-sm-3 control-label textleft">卡券名称</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="name"
											placeholder="" name="name">
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<label for="inputPassword3"
										class="col-sm-3 control-label textleft">选择发行卡券的商户</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" readonly="readonly"
											id="inputPassword3" value="${shop.name}">
										<!-- 此隐藏字段用于提交商户id -->
										<input type="hidden" name="businessId" value="${businessId}">
									</div>
									<div class="col-sm-3">
										<input type="button" class="left btn btn-default" value="确定"
											id="findBusinessName" />

									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<label for="inputPassword3"
										class="col-sm-3 control-label textleft">卡券背景色</label>
									<div class="col-sm-6">
										<input type="text" class="pick-a-color form-control"
											value="green" id="inputPassword4" placeholder="颜色示意框"
											name="background">
									</div>
									<div class="col-sm-3">
										<input type="button" class="left btn btn-default" value="确定"
											name="choosecolor" />
									</div>
								</div>
								<div class="clearfix"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label textleft">卡券标题</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="inputEmail5"
											placeholder="" name="title">
									</div>
								</div>
								<div class="clearfix"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label textleft">卡券副标题</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="inputEmail6"
											placeholder="" name="subtitle">
									</div>
								</div>
								<div class="clearfix"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label textleft">卡券使用开始日期</label>
									<div class="col-sm-6">
										<input type="date" class="form-control" id="inputEmail7"
											placeholder="" name="startTime">
									</div>
								</div>
								<div class="clearfix"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label textleft">卡券发放结束日期</label>
									<div class="col-sm-6">
										<input type="date" class="form-control" id="inputEmail8"
											placeholder="" name="endTime">
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label textleft">礼品名称</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="inputEmail9"
											placeholder="" name="giftName">
									</div>
								</div>
								<div class="clearfix"></div>
								<div class=" text-right" style="margin-top: -32px;">
									<input type="button" id="createvoucher" class="btn btn-default"
										value="保存" style="background-color: #44b549; color: #fff;" />
								</div>
							</form>
							<!-- End 基本信息 form-->
						</div>
						<!-- Start 核销设置-->
						<div class="tab-pane fade" id="checkset">
							<div class="minfo">
								<div class="left">
									<h5 style="color: #AAAAAA;">核销设置</h5>
								</div>
								<u class="clr"></u>
							</div>
							<div class="form-horizontal">
								<form id="checksetform">
									<input type="hidden" value="${voucherId}" name="voucherId">
									<div class="form-group">
										<label class="col-sm-3 control-label textleft">库存</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="in2"
												placeholder="" name="stock">
										</div>
										<span style="height: 30px; line-height: 30px;">份</span>
									</div>
									<div class="clearfix"></div>
									<div class="form-group">
										<label class="col-sm-3 control-label textleft">领券限制 <br />
											<span class="fc_aaa">（选填）</span>
										</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="in3"
												placeholder="" name="ledVoucherLimit">
										</div>
										<span style="height: 30px; line-height: 30px;">张</span>
									</div>
									<div class="clearfix"></div>
									<div style="text-align: center;">
										<p class="fc_aaa" style="margin-top: -12px;">每个用户领券上线，如不填，则默认为1</p>
									</div>
									<div class="form-group" style="margin-top: 30px;">
										<div class=" col-sm-10">
											<div class="checkbox">
												<label> <input type="checkbox" checked="checked"
													name="voucherUseRole" value="1">用户可以分享领券连接
												</label>
											</div>
											<div class="checkbox">
												<label> <input type="checkbox" checked="checked"
													name="voucherUseRole" value="2">用户领券后可以转赠其他好友
												</label>
											</div>
										</div>
									</div>
								</form>
							</div>
							<div class=" text-right"
								style="position: absolute; bottom: 2px; right: 10px;">
								<input type="button" class="btn btn-default" value="保存"
									style="background-color: #44b549; color: #fff;" id="checksetbu" />
							</div>
						</div>
						<!-- End 核销设置-->

						<div class="tab-pane fade" id="details">
							<div class="minfo">
								<div class="left">
									<h5 style="color: #AAAAAA;">卡券详情</h5>
								</div>
								<u class="clr"></u>
							</div>
							<div class="form-horizontal">
								<form id="veriform">
									<input type="hidden" value="${voucherId}" name="voucherId">
									<div class="form-group">
										<label class="col-sm-3 control-label textleft">优惠详情</label>
										<div class="col-sm-6">
											<textarea class="form-control" rows="3" name="offerDetail"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label textleft">使用须知</label>
										<div class="col-sm-6">
											<textarea class="form-control" rows="3" name="usageNotes"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label textleft">客服电话</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="in4"
												placeholder="" name="phone">
										</div>
									</div>
									<div style="text-align: center;">
										<p class="fc_aaa" style="margin-top: -12px;">手机或固话</p>
									</div>
									<div class="clearfix"></div>
								</form>
							</div>
							<div class=" text-right"
								style="position: absolute; bottom: 2px; right: 10px;">
								<input type="button" class="btn btn-default" value="保存"
									style="background-color: #44b549; color: #fff;" id="verifbu" />
							</div>
						</div>
						<!-- Start 适用门店-->
						<div class="tab-pane fade" id="usestore">
							<div class="minfo">
								<div class="left">
									<h5 style="color: #AAAAAA;">适用门店</h5>
								</div>
								<u class="clr"></u>
							</div>
							<div class="form-group">
								<div class="col-sm-3" style="height: 200px;">适用门店</div>
								<div class="col-sm=9">
									<form id="adaptform">
										<input type="hidden" value="${voucherId}" name="voucherId">
										<input type="hidden" value="${businessId}" name="businessId">
										<label class="radio-inline"> <input type="radio"
											name="radioStore" id="inlineRadio3" class="radio_Store"
											checked="checked"> 指定门店适用 <br />
											<!--  <select  class="selectpicker" name="adaptedStore" id="adaptedStore" multiple data-done-button="true">
											 	<option value='' selected='selected'>请选择门店</option>
											 </select>  -->
											<select name="adaptedStore" multiple="multiple" size="2" style="height: 50px;width:153px"></select> 
										</label> <br /> <label class="radio-inline"> <input
											type="radio" name="radioStore" id="inlineRadio4">无指定门店
										</label>
									</form>
								</div>
							</div>
							<div class=" text-right"
								style="position: absolute; bottom: 2px; right: 10px;">
								<input type="button" class="btn btn-default" value="保存"
									style="background-color: #44b549; color: #fff;" id="adaptstore" />
							</div>
						</div>
						<!-- End 适用门店-->
						<!-- Start 预览-->
						<div class="tab-pane fade" id="see">
							<div class="minfo">
								<div class="left">
									<h5 style="color: #AAAAAA;">卡片预览</h5>
								</div>
								<u class="clr"></u>
							</div>
							<div id="imglogosee"
								style="height: 250px; background-color: green;">
								<div class="m10">
									<img src="${pro}/images/oflogo.png" class="img-logo" alt="" />
									<span class="left logonamesee name" style="font-size: 18px;">卡券名称示例</span>
								</div>
								<div class="clearfix"></div>
								<div class="text-center" style="margin-top: 8px;">
									<span
										style="font-size: 25px; color: #fff; letter-spacing: 13px;"
										class="title">卡券标题示例</span>
								</div>
								<div class="clearfix"></div>
								<div class="text-center" style="margin-top: 18px;">
									<span
										style="font-size: 18px; color: #fff; font-style: italic; letter-spacing: 3px;"
										class="subtitle">卡券副标题示例</span>
								</div>
								<div class=""></div>
								<div class="text-center" style="margin-top: 10px;">
									<span
										style="font-size: 16px; color: #fff; letter-spacing: 3px;">有效期：</span>
									<span style="color: #fff; font-size: 16px;" class="startTime">2015.10.10</span>
									<span style="color: #fff; font-size: 16px;" class="">-</span> <span
										style="color: #fff; font-size: 16px;" class="endTime">2015.11.11</span>
								</div>
								<div class="deco"></div>
							</div>
						</div>
						<!-- End 预览-->


					</div>

				</div>

			</div>
		</div>

	</div>
	<script type="text/javascript">
    $(document).ready(function() {
        $('#example-includeSelectAllOption-onChange').multiselect({
            includeSelectAllOption: true,
            onChange: function(option, checked) {
                alert('Not triggered when clicking the select all!');
            },
        });
    });
    
  
</script>
</body>
</html>