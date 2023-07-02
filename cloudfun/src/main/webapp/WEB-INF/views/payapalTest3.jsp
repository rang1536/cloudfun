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
<script src="https://www.paypal.com/sdk/js?client-id=AbUFDyK5nz2G7Pkgmuq3N6yPBhwTXv3ETCtmSoMXrQ6CFtDnPDKDb43uUuqo8wggVWf8QSS-xtUQV0R8&currency=USD"></script>

 <script>
       paypal.Buttons({
         // Order is created on the server and the order id is returned
         createOrder() {
           return fetch("/www/verified-product", {
             method: "POST",
             headers: {
               "Content-Type": "application/json",
             },
             // use the "body" param to optionally pass additional order information
             // like product skus and quantities
             body: JSON.stringify({
               cart: [
                 {
                   sku: "YOUR_PRODUCT_STOCK_KEEPING_UNIT",
                   quantity: "YOUR_PRODUCT_QUANTITY",
                 },
               ],
             }),
           })
           .then((response) => response.json())
           .then((order) => order.id);
         },
         // Finalize the transaction on the server after payer approval
         onApprove(data) {
           return fetch("/my-server/capture-paypal-order", {
             method: "POST",
             headers: {
               "Content-Type": "application/json",
             },
             body: JSON.stringify({
               orderID: data.orderID
             })
           })
           .then((response) => response.json())
           .then((orderData) => {
             // Successful capture! For dev/demo purposes:
             console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));
             const transaction = orderData.purchase_units[0].payments.captures[0];
             alert(`Transaction ${transaction.status}: ${transaction.id}\n\nSee console for all available details`);
             // When ready to go live, remove the alert and show a success message within this page. For example:
             // const element = document.getElementById('paypal-button-container');
             // element.innerHTML = '<h3>Thank you for your payment!</h3>';
             // Or go to another URL:  window.location.href = 'thank_you.html';
           });
         }
       }).render('#paypal-button-container');
     </script>

 </script>