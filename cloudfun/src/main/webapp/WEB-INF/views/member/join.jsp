<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<t:layout>
	 <section class="page-section spad join-page">
		<div class="container"  >
			<form class="comment-form" id="frm-join" name="frm-join" >
			
			<h4 id="join-title">Sign Up</h4>
				<br>
				<div class="row justify-content-md-center">
					<div class="col-lg-12 mb-3" >
						<p class="text-left mb-0">Email</p>
						<input type="text" name="email" placeholder="Email" value ="${email}" readonly>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-12 mb-3">
						<p class="text-left mb-0">Name</p>
						<input type="text" name="name" placeholder="Name" value = "${name}" readonly>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12 mb-3">
						<p class="text-left mb-0">Password</p>
						<input type="password" name="password" placeholder="Password"  autocomplete="off">
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-12 mb-3">
						<p class="text-left mb-0">Date of brith</p>
						<input class="datetimepicker" name="birth" id="birth" type="text"  placeholder="Date of brith" autocomplete="off">
					</div>
				</div>

				<p class="text-center mb-0">By creating an account,</p>
				<p class="text-center mb-2"> you are agreeing to 00's</p>
				<a href="${path}/terms">Terms of Service</a>
				
				
				
			</form>
			<button class="site-btn btn-sm mt-3" id="join-btn">Join</button>
		</div>
	</section>


	

</t:layout>
<link rel="stylesheet" href='<c:url value="/css/jquery.datetimepicker.min.css"/>'>
<script src="${path}/resources/js/jquery.datetimepicker.full.min.js"></script>

<script>


$(document).ready(function () {

	// datetimepicker
	$(".datetimepicker").datetimepicker({ 
		format: "d-m-Y"
		,timepicker:false
	});	
	

	// btn
	$("button[id='join-btn']").click(function () {
		join();
	}); 
});
	



function join() {
	var jsonData = Utils.getFormValue($("#frm-join"));
		
	var datatimeRegexp = /([0-9]{2}-[0-9]{2}-[0-9]{4})/;

    if ( !datatimeRegexp.test($('#birth').val()) ) {
        alert("'Date of birth' is wrong");
        return false;
    }	
	
	
    $.ajax({
    	  url: preUrl + '/api/join',
          dataType:'json',
          data : JSON.stringify(jsonData),
          type: 'post',
          contentType: "application/json"
    }).done(function (res) {
        if(res.error){
        	alert(error);
        	return ; 
        }else{
        	if(res.url == "/"){
        		// 메인페이지로 이동.
        		location.href = "${path}";
        	}	
        }
    });
}
</script>

