/**
 * 关于手机端的一些js脚本
 */
$(document).ready(function(){ 
	
	function clearvalue(){
		$('#searchvalue')[0].value="";
	};
	
	$("button[id='cancelVoucher']").click(function(){
		var code = $("input[name='code']").attr('value');
		$.ajax({
			type:'post',
			url:'cancelVoucher',
			data:code,
			success:function(data){
				if(data == '"success"'){
					$(this).hide();
					alert("核销成功");
				}else if(data == '"warn"'){
					alert("不能重复核销");
				}
			}

		})
	});
	
	$(function(){
		var lengthname = $('getname').length;
		var i=0;
		$('getname').each(function() {
		/*	alert(this.innerHTML+"nn"+i);*/
			//在此地发送ajax请求
			$.ajax({
				type:'post',
				url:'getShopName',
				async:false,
				data:{'businessId':this.innerHTML},
				success:function(data){
					if(data != null){
						var parsedJson = $.parseJSON(data);
						$('shopname')[i].innerHTML = parsedJson.name;
					}
				}

			});
			i=i+1;
		})
		/*//数组长度
		var lengthname = $('getname').length;
		var getname = $('getname')[0].innerHTML;*/
	});
	/**
	 * 点击事件获取二维码
	 * */	
	$("#acquireQCode").click(function(){
		var voucherId = $("#phoneQCode input[name='voucherId']").val();
		var userId = $("#phoneQCode input[name='userId']").val();
		$.ajax({
			type:'post',
			url:'../../phone/acquireVoucher',
			data:{"voucherId":voucherId,"userId":userId},
			success:function(data){
				$('#qrcode').qrcode({width: 135,height: 135,text: data});
				//显示二维码
				$("ul[id='qcode']").css("display","inline");
				//隐藏button
				$("button[id='acquireQCode']").css("display","none");
			}

		})
	});
	
	/**
	 * 点击事件获取code
	 * */	
	$("#acquireCode").click(function(){
		var voucherId = $("#phoneCode input[name='voucherId']").val();
		var userId = $("#phoneCode input[name='userId']").val();
		$.ajax({
			type:'post',
			url:'../../phone/acquireVoucher',
			data:{"voucherId":voucherId,"userId":userId},
			success:function(data){
				$("#vcode h1[class='code']").text(data);
				//显示二维码
				$("ul[id='vcode']").css("display","inline");
				//隐藏button
				$("button[id='acquireCode']").css("display","none");
			}

		})
	});
	
	/**
	 * 二维码核销设置
	 * **/
	
})
