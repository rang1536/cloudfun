<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<t:layout>
	 <!-- Page info section -->
	<section class="page-info-section" >
	
		<div class="pi-content">
			<div class="container">
				<div class="row">
					<div class="col-xl-5 col-lg-6 text-white">
						<h2>Post Edit</h2>
						<p>Register content to receive sponsorship</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Page info section -->


	<!-- Page section -->
	<section class="page-section single-blog-page spad">
		<div class="container">
			<form class="comment-form" id="postFrm" onSubmit="return false;">
			
				<!-- <div class="row mb-3" >
					<div class="col-lg-6">
						<div class="file-preview-container" id="divImageMediaPreview">
						</div>
					</div>
					<div class="col-lg-6">
						<div class="file-drop-area mb-3 ">
							<span class="file-message">Choose Files or drag and drop files here</span>
							<input type="file" class="file-input" id="inputPreview" accept=".jfif,.jpg,.jpeg,.png,.gif" multiple>
						</div>
					</div>
					
				</div> -->
				
				<div class="row mb-3" >
					<div class="col-lg-1">
						<p class="edit-title mb-0" >MAIN IMAGE</p>
					</div>
					<div class="col-lg-4">
						<div class="file-preview-container" id="divImageMediaPreview">
						</div>
					</div>
					<div class="col-lg-7">
						<div class="file-drop-area mb-3 ">
							<span class="file-message span-upload">Choose the main picture</span>
							<input type="file" class="file-input" id="inputPreview" accept=".jfif,.jpg,.jpeg,.png,.gif" multiple>
						</div>
					</div>
				</div>
				
				
				<!-- upload file -->
				<div class="row mb-1" >
					<div class="col-lg-1">
						<p class="edit-title mb-0" >TEXT FILE</p>
					</div>
					<div class="col-lg-11">
						<div class="file-drop-area mb-4">
						  <span class="file-message span-upload">Upload your creation</span>
						  <input class="file-input" name="uploadFile" type="file" >
						</div>
					</div>
				</div>
				
				
				
				<!-- title contents -->
				
				<div class="row mb-3" >
					<div class="col-lg-1">
						<p class="mb-0 edit-title">TITLE</p>
					</div>
					
					<div class="col-lg-11">
						<input type="text" name="title" placeholder="TITLE">
					</div>
				</div>
				
				
				<div class="row mb-3" >
					
					<div class="col-lg-1">
						<p class="mb-0 edit-title">CONTENTS</p>
					</div>
					
					<div class="col-lg-11">
						<textarea class="mb-0" name="contents" placeholder="CONTENTS"></textarea>
					</div>
				</div>
				
				
				<div class="row mb-3" >
					<div class="col-lg-1">
						<p class="mb-0 edit-title">Tags</p>
					</div>
					
					<div class="col-lg-11">
						<div class="form-group">
							<input type="text" class="form-control" name="tags"/>
						</div>
					</div>
				</div>
				
								
				
				<div class="row mb-3" >
					<div class="col-lg-4">
						<p class="mb-0 edit-title">AI-generated work</p>
					</div>
					
					<div class="col-lg-8">
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="aiYn" id="inlineRadio1" value="Y">
						  <label class="form-check-label" for="inlineRadio1">YES</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="aiYn" id="inlineRadio2" value="N">
						  <label class="form-check-label" for="inlineRadio2">NO</label>
						</div>
					</div>
					
					
				</div>
				
				
				<div class="row mb-3 " >
					<div class="col-lg-4 ">
						<p class="mb-0 edit-title">Sponsorship period</p>
					</div>
					<div class="col-lg-6   d-flex">
								<input class="datetimepicker" name="funStartDt" id="fromDt" type="text"  placeholder="start date" autocomplete="off">
								<span class="ml-3 mr-3">~</span>
								<input class="datetimepicker" name="funEndDt" id="toDt" type="text"  placeholder="end date" autocomplete="off">
					</div>
				</div>
				
				
				<div class="row mb-3 " >
					<div class="col-lg-4">
						<p class="mb-0 edit-title">target amount</p>
					</div>
					<div class="col-lg-4">
						<input type="text" placeholder="target amount" id="tagetAmount" name ="tgtAmt">
					</div>
				</div>
				
				<div class="row mb-3 " >
					<div class="col-lg-4  ">
						<p class="mb-0 edit-title">Amount provided for viewing</p>
					</div>
					<div class="col-lg-4  ">
						<input type="text" placeholder="Amount provided for viewing" id="openAmt" name ="openAmt">
					</div>
				</div>
				
				<div class="row mb-3 " >
						
					<div class="col-lg-4 ">
						<p class="mb-0 edit-title">Anonymous Sponsorship Received</p>
					</div>
					
					<div class="col-lg-8">
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="anoFunYn" id="AnonymousYN1" value="Y">
						  <label class="form-check-label" for="AnonymousYN1">YES</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="anoFunYn" id="AnonymousYN2" value="N">
						  <label class="form-check-label" for="AnonymousYN2">NO</label>
						</div>
					</div>
				</div>
				
				<div class="row mb-3 " >
						
					<div class="col-lg-4 ">
						<p class="mb-0 edit-title">Premium Mode(Open later)</p>
						<p class="explain-text">   Provides high-definition/high-capacity file uploads</p>
						<p class="explain-text">   Contents over 18 years of age or older</p>
						<p class="explain-text">   Publish default page (unspecified majority)</p>
					</div>
					
					<div class="col-lg-8">
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="anoFunYn" id="AnonymousYN1" value="Y" disabled>
						  <label class="form-check-label" for="AnonymousYN1">YES</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="anoFunYn" id="AnonymousYN2" value="N" disabled>
						  <label class="form-check-label" for="AnonymousYN2">NO</label>
						</div>
					</div>
					<div class="col-lg-12 ">
						
					</div>
				</div>
				
				
			</form>
			<button class="site-btn btn-sm mt-3" id="save-btn">Save</button>
		</div>
	</section>
	<!-- Page section end -->
	
	
	
</t:layout>


<link rel="stylesheet" href='<c:url value="/css/amsify.suggestags.css"/>'>
<link rel="stylesheet" href='<c:url value="/css/jquery.datetimepicker.min.css"/>'>
<script src="${path}/resources/js/jquery.amsify.suggestags.js"></script>
<script src="${path}/resources/js/jquery.datetimepicker.full.min.js"></script>




<script>


$(document).ready(function() {

	 $(".file-drop-area").on('change', '.file-input', function() {
	    
	
	  var filesCount = $(this)[0].files.length;
	  
	  var textbox = $(this).prev();

	  if (filesCount === 1) {
	    var fileName = $(this).val().split('\\').pop();
	    textbox.text(fileName);
	  } else {
	    textbox.text(filesCount + ' files selected');
	  }
	});
	
	

	//tags
	$('input[name="tags"]').amsifySuggestags();
	
	
	// datetimepicker
	$(".datetimepicker").datetimepicker({ 
		format: "d-m-Y"
		,timepicker:false
	});	
	
	$( "#tagetAmount" ).keyup(function(event ) {
		removeChar(event)
		inputNumberFormat(this)
	});
	$( "#tagetAmount" ).keydown(function(event ) {
		inputNumberFormat(this)
	});
	 
	
	 // save btn
	$("#save-btn").on("click",function(e){
		var form = $('#postFrm');
		
		
		var formData = new FormData();
		
		formData.append("jsonStr",JSON.stringify(Utils.getFormValue($("#postFrm"))));
		
		
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
		for(var i =0; i< files.length ; i++){
			formData.append("uploadFile",files[i]);	
		} 
		
		 $.ajax({
	    	  url: "${path}" + '/api/post/save',
	          data : formData,
	          type: 'post',
	          enctype: 'multipart/form-data',
	          contentType: false,
	          processData:false
	          
	    }).done(function (res) {
	        if(res.error){
	        	alert(error);
	        	return ; 
	        }else{
	        	alert(res)
	        }
	    });
		
		
	}) 
	
	
	// main img preview
	$(".file-drop-area").on('change', '#inputPreview', function() {

		
		var filesCount = $(this)[0].files.length;

		var textbox = $(this).prev();

		if (filesCount === 1) {
		var fileName = $(this).val().split('\\').pop();
			textbox.text(fileName);
		} else {
			textbox.text(filesCount + ' files selected');
		}
	
		if (typeof (FileReader) != "undefined") {
		    var dvPreview = $("#divImageMediaPreview");
		    dvPreview.html("");            
		    $($(this)[0].files).each(function () {
		        var file = $(this);                
		            var reader = new FileReader();
		            reader.onload = function (e) {
		                var img = $("<img />");
		                img.attr("style", "width: 100%; height:100%; padding: 10px");
		                img.attr("src", e.target.result);
		                dvPreview.append(img);
		            }
		            reader.readAsDataURL(file[0]);                
		    });
		} else {
		    alert("This browser does not support HTML5 FileReader.");
		}
	
	
	});
	
})



</script>

