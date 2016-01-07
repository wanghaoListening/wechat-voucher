<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>优惠详情</title>
<%@ include file="/pages/public/phone.jspf"%>
 <style>
        h4{font-size: 14px;}
    </style>
</head>
<body style="background-color: #f4f5f9;">
         <div class="panel panel-default" style="border-top: none;margin-top: 20px;">
             <div class="panel-heading" style=" background-color: #f4f5f9 !important;">
                 <h4 class="panel-title">优惠详情</h4>
             </div>
             <div class="panel-body">
                 <h4>${voucher.offerDetail}</h4>
             </div>
         </div>
         <div class="panel panel-default" style="border-top: none;">
             <div class="panel-heading" style="background-color: #f4f5f9 !important;">
                 <h4 class="panel-title">使用须知</h4>
             </div>
             <div class="panel-body">
                 <h4>${voucher.usageNotes}</h4>
             </div>
         </div>
         <div style="position:absolute;bottom: 0;width: 100%;;">
             <div class="panel panel-default" style="border-top: none ;margin-bottom: 0px;border-radius: 0px;">

                 <div class="panel-body">
                    <h4 class="left" >客服</h4><h4 class="right">${voucher.phone}</h4>
                 </div>
             </div>
         </div>

</body>
</html>