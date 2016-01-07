<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<%@ include file="/pages/public/phone.jspf"%>
<link rel="stylesheet" href="${pro}/css/adcss.css">

<script src="${pro}/js/jquery-1.9.1.js"></script>
<script src="${pro}/js/bootstrap.min.js"></script>

</head>
<body>

	<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#myModal">Launch demo modal
	</button>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content"
				style="background: #303030; background: url(${pro}/img/bgl.jpg); background-size: cover; margin: 10px auto; width: 80%; min-width: 300px; height: 420px;">
				<div class="modal-body">
					<div
						style="margin: 10px auto; height: 150px; width: 230px; text-align: center;">

						<div class="curved_box" style="width: 230px;">
							<div class="yezi-bg">
								<img src="${pro}/images/oflogo.png" class="yezi-img-l" alt="" /> <span
									class="left yezi-logoname" style="line-height: 85px;">微信手机话费充值</span>
							</div>
							<div class="clearfix"></div>
							<div style="position: relative">
								<div class="yezi-deco"></div>
							</div>
							<div class="clearfix"></div>
							<div class="yezi-footer">
								<div class="left text-left ">
									<span style="font-size: 14px;">潍坊学院</span>
								</div>
							</div>
						</div>
					</div>
					<div
						style="margin: 10px auto; height: 150px; width: 260px; text-align: center;">
						<div style="margin: auto; width: 260px;">
							<div class="thumbnail"
								style="max-height: 125px; max-width: 125px; float: left; margin: 0px 2px;">
								<img src="${pro}/img/erweima.jpg" width="125" height="125" alt="...">
							</div>
							<div class="thumbnail"
								style="max-height: 125px; max-width: 125px; float: left; margin: 0px 2px;">
								<img src="${pro}/img/zhiwen.jpg" width="125" height="125" alt="...">
							</div>
						</div>
					</div>
					<div style="margin-top: 5px;">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary">保存改变</button>						
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>