<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<t:layout>
	 <section class="page-section spad join-page">
		<div class="container"  >
			<form class="comment-form" id="frm-join" name="frm-join" >
			
			<!-- <h4 id="join-title">MY INFO</h4> -->
				<br>
				
				<div class="row">
					<div class="col-lg-6" >
						<div class="row">
							<div class="col-lg-12 mb-3" >
								<h5>Personal info</h5>
							</div>
						</div>
						<div class="row ">
							
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
								<p class="text-left mb-0">Nation</p>
								<select class="custom-select" id="nation" name="nation" >
								    <option value="" selected>Nation</option>
								    
								</select>
							  </div>
						</div>
					
					</div>
					<div class="col-lg-6"  style="border-left: 1px solid #00000063;">
						<div class="row">
							<div class="col-lg-12 mb-3" >
								<h5>Creator's required info</h5>
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-12 mb-3">
								<p class="text-left mb-0">Account Name</p>
								<input type="text" name="accountNm" placeholder="Account Name" value = "${accountNm}" >
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-12 mb-3">
								<p class="text-left mb-0">Account Number</p>
								<input type="text" name="accountNo" placeholder="Account Number" value = "${accountNo}" >
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-12 mb-3">
								<p class="text-left mb-0">Passport number</p>
								<input type="text" name="passportNo" placeholder="passport number" value = "${passportNo}" >
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-12 mb-3">
							
								<p class="text-left mb-0">Passport scan file</p>
								<%-- <input type="text" name="passportNo" placeholder="passport IMG" value = "${passportNo}" readonly> --%>

								<div class="custom-file">
									<input type="file" class="custom-file-input" id="passPortFile" name="passPortFile">
									<label class="custom-file-label" for="customFile">Choose file</label>
								</div>
								
			            	</div>
						</div>
					
					</div>
				</div>
				
				
				
				
			</form>
			<button class="site-btn btn-sm mt-3" id="join-btn">Save</button>
			
		
		</div>
		
		
		

	</section>


	

</t:layout>
<link rel="stylesheet" href='<c:url value="/css/jquery.datetimepicker.min.css"/>'>
<script src="${path}/resources/js/jquery.datetimepicker.full.min.js"></script>

<script>


$(document).ready(function () {
	
	if("${errorMsg}"!=""){
		alert("${errorMsg}");
	}
	
	if("${fileNm}"!=""){
		$(".custom-file-label").addClass("selected").html("${fileNm}")
	}

	// datetimepicker
	$(".datetimepicker").datetimepicker({ 
		format: "d-m-Y"
		,timepicker:false
	});	
	

	// btn
	$("button[id='join-btn']").click(function () {
		save();
	}); 
	
	$(".custom-file-input").on("change", function() {
	  var fileName = $(this).val().split("\\").pop();
	  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
	  
	  if ( $(this).files &&  $(this).files[0]) {
			let reader = new FileReader();
			reader.onload = (e) => {
				let imgData = e.target.result;
				let imgName = $(this).files[0].name;
				$(this).setAttribute("data-title", imgName);
				//console.log(e.target.result);
			}
			reader.readAsDataURL($(this).files[0]);
		}
	  
	});
	

	var jsonData = {
		
	}
	
	$.ajax({
		url: "${path}" + '/api/selectNation',
          dataType:'json',
          data : JSON.stringify(jsonData),
          type: 'post',
          contentType: "application/json"
    }).done(function (res) {
        if(res.error){
        	alert(error);
        	return ; 
        }else{
        	console.log(res)
        	var nationCdList = res.nationCdList;
        	
        	for(var i = 0 ; i < nationCdList.length; i++){
        		var objNationCd = nationCdList[i];
        		var code = objNationCd.CODE;
        		var codeEn = objNationCd.CODE_EN;
        		var codeId = objNationCd.CODE_ID;
        		var codeKr = objNationCd.CODE_KR;
        		
        		
        	
        		$("#nation").append('<option value="' + code + '">'+ codeEn+' / ' +codeKr  +'</option>');
        		
        		
        	}
        	var nationCd = "${nationCd}";
        	$("#nation").val(nationCd).prop("selected", true);
        }
    });
	
});
	



function save() {
	//var jsonData = Utils.getFormValue($("#frm-join"));
		
	/* var datatimeRegexp = /([0-9]{2}-[0-9]{2}-[0-9]{4})/;

    if ( !datatimeRegexp.test($('#birth').val()) ) {
        alert("'Date of birth' is wrong");
        return false;
    }	 */
    
    
    var form = $('#frm-join');
    
    var formData = new FormData();
    var jsonFormData = Utils.getFormValue($("#frm-join"));
	formData.append("jsonStr",JSON.stringify(jsonFormData));
    
    
    var inputFile = $("input[name='passPortFile']")[0].files[0];
    if(inputFile){
    	formData.append("passPortFile",inputFile);
    }
			
	 
	
	
	
    $.ajax({
    	  url: preUrl + '/api/saveMember',
          //dataType:'json',
          //data : JSON.stringify(jsonData),
          data : formData,
          type: 'post',
          //contentType: "application/json"
          
       	  enctype: 'multipart/form-data',
          contentType: false,
          processData:false
    }).done(function (res) {
        if(res.error){
        	alert(error);
        	return ; 
        }else{
        	if(res.url == "/"){
        		// 메인페이지로 이동.
        		alert("SAVE COMPLETE")
        		location.href = "${path}";
        	}	
        }
    });
}




</script>

