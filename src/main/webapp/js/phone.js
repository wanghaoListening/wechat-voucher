/**
 * 关于手机端的一些js脚本
 */
$(document).ready(function(){ 

	function clearvalue(){
		$('#searchvalue')[0].value="";
	};

	$("button[id='cancelVoucher']").click(function(){
		var code = $("input[name='vericcode']").attr('value');
		$.ajax({
			type:'post',
			url:'cancelVoucher',
			data:{"code":code},
			success:function(data){
				if(data == '"success"'){
					//隐藏按钮
					$("button[id='cancelVoucher']").hide();
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
			url:'acquireVoucher',
			async:false,
			data:{'voucherId':voucherId,'userId':userId},
			success:function(data){
				if(data != null){
					if(data==1){
						$("div span[id='isf']").text("请到公众号卡包查看卡券");
					}else{
						$("div span[id='isf']").text("请长按二维码或手印关注公众号并到卡包查看领取的卡券");
					}
					$("#acquireQCode").hide();
				}
			}

		});
		
		
	});
	$("#hasQCode").click(function(){
		alert("此卡券不能重复领取");
	});
	/**
	 * 点击事件获取code
	 * */	
	/*	$("#acquireCode").click(function(){
		var voucherId = $("#phoneCode input[name='voucherId']").val();
		var userId = $("#phoneCode input[name='userId']").val();
		$.ajax({
			type:'post',
			url:'acquireVoucher',
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
	 */
	$(".Search input[class='Search_Btn']").click(function(){
		var code = $(".Search #searchvalue").val();
		var userId = $(".Search input[name='userId']").val();
		window.location.href='verificVoucher?code='+code+"&userId="+userId;
	});

})
/**
 * 在卡包查看卡券详细
 * **/
function seeVDetails(voucherId){
	var userId = $("body input[id='bagBody']").val();
	window.location.href='seeVDetails?voucherId='+voucherId+"&userId="+userId;
}

/**
 * 用于核销时清除文本框的内容
 * */
function clearvalue(){
	document.getElementById('searchvalue').value="";
}
