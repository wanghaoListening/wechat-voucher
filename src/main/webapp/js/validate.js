/**
 * 
 */
$(document).ready(function(){

	$.validator.methods.compareDate = function(value, element, param) {
		//var startDate = jQuery(param).val() + ":00";补全yyyy-MM-dd HH:mm:ss格式
		//value = value + ":00";

		var startDate = jQuery(param).val();

		var date1 = new Date(Date.parse(startDate.replace("-", "/")));
		var date2 = new Date(Date.parse(value.replace("-", "/")));
		return date1 < date2;
	};

	$("#createVoucherform").validate({

		rules:{
			name:{
				required:true,
				minlength:3,
				maxlength:10
			},
			title:{
				required:true,
				minlength:3,
				maxlength:10
			},
			subtitle:{
				minlength:3,
				maxlength:10
			},
			startTime:{
				required:true,
			},
			/**
			 * 卡券发放结束日期
			 */
			deadlineTime:{
				required:true,
			}

		},
		messages:{
			name:{
				required:"名称不能为空",
				minlength:"不能少于三个字符",
				maxlength:"不能多于十个字符"
			},
			title:{
				required:"主题不能为空",
				minlength:"不能少于两个字符",
				maxlength:"不能多于十个字符"
			},
			subtitle:{
				minlength:"不能少于两个字符",
				maxlength:"不能多于十个字符"
			},
			startTime:{
				required:"卡券使用时间必须选择",
			},
			/**
			 * 卡券发放结束日期
			 */
			deadlineTime:{
				required:"卡券发放结束日期必须选择",
			}

		}
	});
	$("#checksetform").validate({
		rules:{
			stock:{
				required:true,
				digits:true,
			},
			ledVoucherLimit:{
				required:true,
				digits:true,
			}
		},
		messages:{
			stock:{
				required:"库存不能为空",
				digits:"数字不合法",
			},
			ledVoucherLimit:{
				digits:"数字不合法",
			}
		}
	});
	
	$("#veriform").validate({
		
		rules:{
			offerDetail:{
				minlength:10,
				maxlength:300
			},
			usageNotes:{
				minlength:10,
				maxlength:300
			},
			
		},
		messages:{
			offerDetail:{
				minlength:"优惠详情不能少于10个字",
				maxlength:"优惠详情不能超过300个字"
			},
			usageNotes:{
				minlength:"使用须知不能少于10个字",
				maxlength:"使用须知不能超过300个字"
			},
			
		}
	});
	
});



