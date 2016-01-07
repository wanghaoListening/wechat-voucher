/**
 * Copyright 2015 wanghao
 */
/***************************创建卡券start************************************/
/**
 * 获取服务端最新的token用于解决表单提交重复
 * */
function getToken(formId){
	$.post("getToken", function(data) {
		if(data.length>0){
			if(data.charAt(0)=='\"')
			{
				//字符串以"开头，去掉"
				data=data.substr(1,data.length-1)
			}
			if(data.charAt(data.length-1)=='\"')
			{
				//字符串以"结尾，去掉"
				data=data.substr(0,data.length-1)
			}
		}

		$("#"+formId+" input[name='token']").val(data);
	});
}
/**
 * 
 * 此函数用于创建卡券模板的基本信息
 * */
$(document).ready(function(){ 
	$("#createvoucher").click(function(){
		var crevoudata = $('#createVoucherform').serialize();
		$.ajax({
			type:'post',
			url:'createVoucher',
			data:crevoudata,
			success:function(data){
				if(data == '"success"'){
					confirm("卡券基本信息保存成功");
					$("ul a[id='checksets']").click();
				}
			}

		})
	});

	/**
	 * 
	 * */
	/**
	 * 卡券的核销设置
	 * */
	$("#checksetbu").click(function(){
		var crevoudata = $('#checksetform').serialize();
		$.ajax({
			type:'post',
			url:'verificationSet',
			data:crevoudata,
			success:function(data){
				if(data == '"success"'){
					confirm("卡券核销设置保存成功");
					$("ul a[id='voucherdetail']").click();
				}
			}

		})
	});
	/**
	 * 设置卡圈的详情细节
	 * */
	$("#verifbu").click(function(){
		var crevoudata = $('#veriform').serialize();
		$.ajax({
			type:'post',
			url:'createVoucDetls',
			data:crevoudata,
			success:function(data){
				if(data == '"success"'){
					confirm("卡券详情设置保存成功");
					$("ul a[id='adaptedstore']").click();
				}else if(data == '"error"'){
					alert("系统异常请稍后再试");
				}
			}

		})
	});



	/**
	 * 
	 * */
	$("a[id='adaptedstore']").click(function(){
		var businessId = $("#adaptform input[name='businessId']").val();
		$.ajax({
			type:'post',
			url:'findStoresByShop',
			data:{"businessId":businessId},
			success:function(data){
				var jsonarray= $.parseJSON(data);
				//先清空optition
				$("#adaptform select[name='adaptedStore']").empty();
				$("#adaptform select[name='adaptedStore']").append("<option value='' selected='selected'>请选择门店</option>");
				$.each(jsonarray, function (i, n)
						{	//获取option的个数
					$("#adaptform select[name='adaptedStore']").append("<option value='"+(n.id)+"'>"+(n.name)+"</option>");
						})
			}

		})
	});

	/**
	 * 
	 * 
	 * 设置卡券的适用门店
	 * */
	$("#adaptstore").click(function(){
		getToken("adaptform");
		var crevoudata = $('#adaptform').serialize();
		$.ajax({
			type:'post',
			url:'setAdaptedStore',
			data:crevoudata,
			success:function(data){
				if(data == '"success"'){
					confirm("卡券适用门店保存成功");
					$("#picsee").click();
				}
			}

		})
	});
	//通过url的type获取卡券的类型并显示
	$.extend({
		getUrlVars: function () {
			var vars = [], hash;
			var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			for (var i = 0; i < hashes.length; i++) {
				hash = hashes[i].split('=');
				vars.push(hash[0]);
				vars[hash[0]] = hash[1];
			}
			return vars;
		},
		getUrlVar: function (name) {
			return $.getUrlVars()[name];
		}
	});

	var   arr   =   $('#typevalue');
	var type = $.getUrlVar('type');
	if(type == "1")
		arr[0].innerHTML   =   "优惠券 ";
	if(type == "2")
		arr[0].innerHTML   =   "代金券 ";
	if(type == "3")
		arr[0].innerHTML   =   "折扣券";
	if(type == "4")
		arr[0].innerHTML   =   "礼品券";

	$(function(){
		var   arr   =   document.getElementById('typevalue');
		var type = $.getUrlVar('type');
		if(type == 1)
			arr[0].innerHTML   =   "优惠券 ";
		if(type == 2)
			arr[0].innerHTML   =   "代金券 ";
		if(type == 3)
			arr[0].innerHTML   =   "折扣券";
		if(type == 4)
			arr[0].innerHTML   =   "礼品券";

	});


	/* *************************创建卡券，动态生成卡券的函数***************************/

	/***
	 * 
	 * 卡券名称失去焦点的动态改变
	 * */
	$("#createVoucherform input[name='name']").blur(function(){
		var name = $(this).val();
		$("#imglogo span[class='left logoname name']").text(name);
		$("#imglogosee span[class='left logonamesee name']").text(name);
	});
	/**
	 * 改变卡券的背景色
	 * */
	$("#createVoucherform input[name='choosecolor']").click(function(){
		var background = $("#createVoucherform input[name='background']").val();
		$("#imglogo").css({'background-color': "#"+background});
		$("#imglogosee").css({'background-color': "#"+background});
	});
	/**
	 * 改变卡券的标题
	 * */
	$("#createVoucherform input[name='title']").blur(function(){
		var title = $(this).val();
		$("#imglogo span[class='title']").text(title);
		$("#imglogosee span[class='title']").text(title);
	});
	/**
	 * 改变卡券的副标题
	 * */
	$("#createVoucherform input[name='subtitle']").blur(function(){
		var subtitle = $(this).val();
		$("#imglogo span[class='subtitle']").text(subtitle);
		$("#imglogosee span[class='subtitle']").text(subtitle);
	});
	/**
	 * 改变卡券的有效日期
	 * */
	$("#createVoucherform input[name='startTime']").blur(function(){
		//jquery字符的全局替换
		var startTime = $(this).val().replace(/-/gm,'.');
		$("#imglogo span[class='startTime']").text(startTime);
		$("#imglogosee span[class='startTime']").text(startTime);
	});
	$("#createVoucherform input[name='endTime']").blur(function(){
		//jquery字符的全局替换
		var endTime = $(this).val().replace(/-/gm,'.');
		$("#imglogo span[class='endTime']").text(endTime);
		$("#imglogosee span[class='endTime']").text(endTime);
	});


	/***************************创建卡券end*******************************/
	/**********************************************************/

	/**
	 * 禁用某卡券模板的状态
	 * */
	$(".disableState").click(function(){
		var voucherId = $(this).attr("name");
		$.ajax({
			type:'post',
			url:'disableState',
			data:{"voucherId":voucherId},
			success:function(data){
				if(data == '"success"'){
					$("#"+voucherId+" td[class='state']").text("禁用");
				}
				if(date == '"error"'){
					alert('禁用失败');
				}
			}

		})
	});
	/**
	 * 更改库存
	 * 注意当元素是被遍历出来的时获取此元素应该使用能够唯一标示此元素的东西
	 * **/
	$("tbody input[class='btn btn-default']").click(function(){
		if(voucherId==null&&voucherId=='')
			alert("系统出现异常暂时无法更改库存");
		var voucherId = $(this).attr("name");
		var stock = $("tr[id="+voucherId+"] input[name='stock']").val();
		$.ajax({
			type:'post',
			url:'changeStock',
			data:{"voucherId":voucherId,"stock":stock},
			success:function(data){
				if(data == '"success"'){
					$("tr[id="+voucherId+"] span[id='stockspan']").text(stock);
					$("tr[id="+voucherId+"] input[name='stock']").val("");
				}
				else{
					alert("更改库存失败");
				}
			}

		})
	});

	
	/**
	 * 对卡券模板的预览preview
	 * */

	$("tr a[id='previewVoucher']").click(function(){
		var projectName = $("input[id='projectName']").val();
		//根据卡券的id获取卡券模板
		var voucherId = $(this).attr('name');
		if(voucherId==null || ""==voucherId){
			alert("系统出现异常请联系管理员！！！");
		}else{

			$.ajax({
				type:'post',
				url:'previewVoucher',
				data:{"voucherId":voucherId},
				success:function(data){
					if(data == '"error"'){
						alert("无法预览此卡券");
					}else if(data == '"success"'){
						//开启卡券模板预览子窗体
						var iWidth=300;                          //弹出窗口的宽度;
						var iHeight=450;                        //弹出窗口的高度;
						var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
						var iLeft = (window.screen.availWidth-10-iWidth)/2;        //获得窗口的水平位置;
						var win = window.open(projectName+"/pages/manage/voucherpreview.jsp","newwindow",'top='+iTop+',left='+(iLeft-90)+
								'menubar=no,' +
								'width='+iWidth+',' +
								'height='+iHeight+','+
								'resizable=no,' +
								'toolbar=no,' +
								'scrollbars=no,' +
								'resizable=no,' +
								'location=no, ' +
								'status=no,' +
								'alwaysRaised=yes,' +
								'titlebar=no,' +
								'hotkeys=no,' +
								'directories=no,'+
						'status=no');

					}

				}

			})
			

		}
	});
	/**************************卡券核销相关的设置start**********************************/

	/*
	 * js中的date对象
	 * 
	 */

	function getDate(time){
		var objTime = new Date(time);
		var year = objTime.getFullYear();
		var month = objTime.getMonth();
		var day = objTime.getDate();

		return year+"."+month+"."+day;
	}
	/**
	 * 查询卡券是否含有code
	 * */
	$("#verificbutton").click(function(){
		var code = $("#verifictext").val();
		//正则表达式匹配12个数字字符
		var re = new RegExp("^[0-9]{12}$");
		if(!re.test(code)){
			alert("不能为空");
			return false;
		}else{
			$.ajax({
				type:'post',
				url:'searchCode',
				data:{"code":code},
				success:function(data){
					if(data == null||data == '')
					{
						$("#h3code").text("无此用户");
					}else{

						var parsedJson = $.parseJSON(data);
						if(parsedJson.verificated == true){
							$("#h3code").text("未能核销,此用户已经核销");
						}else{
							//若核销成功
							$("#h3code").text(parsedJson.code);
							$("#js_preview_verific span[id='verific_name']").text(parsedJson.cardvoucher.name);
							$("#js_preview_verific span[class='verific_title']").text(parsedJson.cardvoucher.title);
							$("#js_preview_verific span[class='verific_subtitle']").text(parsedJson.cardvoucher.subtitle);
							$("#js_preview_verific span[class='validDate']").text('有效期:');
							var startTime = getDate(parsedJson.cardvoucher.startTime);
							$("#js_preview_verific span[class='verific_startTime']").text(startTime.toLocaleString());
							$("#js_preview_verific span[class='spaceMark']").text('-');
							var endTime = getDate(parsedJson.cardvoucher.endTime);
							$("#js_preview_verific span[class='verific_endTime']").text(endTime.toLocaleString());
							$("#js_preview_verific div[id='imglogo']").css({'background-color': "#"+parsedJson.cardvoucher.background})
						}
					}
				}

			})
		}
	});



	/**
	 * 完成核销设置
	 * */

	$("div #finishVerific").click(function(){
		var code = $("#verifictext").val();	
		$.ajax({
			type:'post',
			url:'setVerific',
			data:{"code":code},
			success:function(data){
				if(data == '"success"'){
					confirm("审核卡券"+code+"成功")
				}
				else if(data == '"error"'){
					alert("审核失败");
				}
			}

		})
	});
	/*************************卡券统计饼形图。。***********************************/
	var pieData = [
	               {
	            	   value: 300,
	            	   color:"#F7464A",
	            	   highlight: "#FF5A5E",
	            	   label: "Red"
	               },
	               {
	            	   value: 50,
	            	   color: "#46BFBD",
	            	   highlight: "#5AD3D1",
	            	   label: "Green"
	               },
	               {
	            	   value: 100,
	            	   color: "#FDB45C",
	            	   highlight: "#FFC870",
	            	   label: "Yellow"
	               },
	               {
	            	   value: 40,
	            	   color: "#949FB1",
	            	   highlight: "#A8B3C5",
	            	   label: "Grey"
	               },
	               {
	            	   value: 120,
	            	   color: "#4D5360",
	            	   highlight: "#616774",
	            	   label: "Dark Grey"
	               }

	               ];

	/*window.onload = function(){
				var ctx = document.getElementById("chart-area").getContext("2d");
				window.myPie = new Chart(ctx).Pie(pieData);
			};*/



}); 