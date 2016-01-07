/**
 * 
 */
$(function(){
	var projectName = $("input[id='projectName']").val();

	/**
	 * mouseenter()
	 * 查询商户所有的商户mouseenter事件每当页面加载完毕就进行数据的填补
	 *  "[{'Id':'144622264597054044','Name':'小掌柜火锅'},{'Id':'144622269107968586','Name':'星美影院'}]";
	 * **/
	/*$(".filterVoucher select[name='businessId']").click(function(){*/
	$.ajax({
		type:'post',
		url:projectName+'/cardManagement/findShops',
		success:function(data){
			var jsonarray= $.parseJSON(data);
			$(".filterVoucher select[name='businessId']").empty();
			$(".filterVoucher select[name='businessId']").append("<option value='' selected='selected'>请选择发券商户</option>");
			$.each(jsonarray, function (i, n)
					{
				$(".filterVoucher select[name='businessId']").append("<option value='"+(n.id)+"'>"+(n.name)+"</option>");
					})
		}

	})

	
})
