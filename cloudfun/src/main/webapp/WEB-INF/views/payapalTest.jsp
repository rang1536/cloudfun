<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<t:layout>

	<h2>페이팔 결제 테스트</h2>
	<table style="border:1px solid #ddd">
	<tr>
	    <th>상품 명</th>
	    <th>가격</th>
	</tr>
	<tr>
	    <td>
	        <span>그림후원</span>
	        <input type="hidden" id="pd_id" value="testOrd1">
	    </td>
	    <td>
	        <span>0.01 / USD</span>
	    </td>
	</tr>
	</table>
	<br/><br/>
	<div id="paypal-button-container"></div>
</t:layout>

<!-- <script src="https://www.paypal.com/sdk/js?client-id=AbUFDyK5nz2G7Pkgmuq3N6yPBhwTXv3ETCtmSoMXrQ6CFtDnPDKDb43uUuqo8wggVWf8QSS-xtUQV0R8"></script> -->
<script src="https://www.paypal.com/sdk/js?client-id=AbUFDyK5nz2G7Pkgmuq3N6yPBhwTXv3ETCtmSoMXrQ6CFtDnPDKDb43uUuqo8wggVWf8QSS-xtUQV0R8"></script>

 <script>
     //paypal.Buttons().render('#paypal-button-container');

   console.log($('#pd_id').val())
   console.log(JSON.stringify({
       pd_id: $("#pd_id").val()
   }))
   //Custom Exception Func
     function PayPalException(name, message){
         this.name = name;
         this.message = message;
     }

     paypal.Buttons({
         createOrder: function(data, actions){ //결제 요청
             return fetch('/www/verified-product', {
                 method:'POST',
                 headers:{
                     "Content-Type": "application/json;charset=utf-8",
                     //"X-CSRF-TOKEN": document.getElementById("csrf-token").getAttribute("content")
                     "X-CSRF-TOKEN":$("meta[name='_csrf']").attr("content")
                 },
                 body: JSON.stringify({
                     pd_id: $("#pd_id").val()
                 })
             }).then(function (response){
                 return response.json();
             }).then(function (result){
            	 console.log(result);
                 if(result.valid == 'true'){
                     return actions.order.create({
                         purchase_units: [{
                             description: result.purchase_units.description,
                             amount: {
                                 value: result.purchase_units.amount.value,
                                 currency: result.purchase_units.amount.currency
                             }
                         }]
                     });
                 } else {
                     throw PayPalException(result.name, result.message);
                 }
             });
         },
         onApprove: function(data, actions){
        	 console.log('onApprove'+data)
             let res = fetch('/paypal/order/capture', {
                 method: 'POST',
                 headers: {
                     "Content-Type": "application/json;charset=utf-8",
                     //"X-CSRF-TOKEN": document.getElementById("csrf-token").getAttribute("content")
                     "X-CSRF-TOKEN":$("meta[name='_csrf']").attr("content")
                 },
                 body: JSON.stringfy({ //결제 정보를 DB에 등록하는 경우, 이곳에서 데이터를 전달합니다.
                     order_id: data.orderID
                 })
             }).then(function (response){
                 return response.json();
             }).then( function (result){
                 if(result.valid == true){
                     alert('결제가 완료되었습니다.');
                 } else {
                     throw new PayPalException(result.name, result.message);
                 }
             }).catch(function(err){
                 alert('[' + err.name + ']\n' + err.message);
             });
         },
         onError: function(err){
             alert('[' + err.name + ']\n' + err.message);
         },
         onCancel: function(data){
             console.log('거래 취소');
         }
     }).render('#paypal-button-container');
 </script>