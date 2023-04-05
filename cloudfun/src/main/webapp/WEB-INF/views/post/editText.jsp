<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<t:layout>
	 <!-- Page info section -->
	<%-- <section class="page-info-section set-bg" data-setbg="${path}/img/page-top-bg/background.jpg"> --%>
	<section class="page-info-section set-bg" >
	
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
			<form class="comment-form" onSubmit="return false;">
			
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
						<input type="text" placeholder="TITLE">
					</div>
				</div>
				
				
				<div class="row mb-3" >
					
					<div class="col-lg-1">
						<p class="mb-0 edit-title">CONTENTS</p>
					</div>
					
					<div class="col-lg-11">
						<textarea class="mb-0" placeholder="CONTENTS"></textarea>
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
						  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
						  <label class="form-check-label" for="inlineRadio1">YES</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">
						  <label class="form-check-label" for="inlineRadio2">NO</label>
						</div>
					</div>
					
					
				</div>
				
				
				<div class="row mb-3 " >
					<div class="col-lg-4 ">
						<p class="mb-0 edit-title">Sponsorship period</p>
					</div>
					<div class="col-lg-6   d-flex">
								<input class="datetimepicker" name="fromDt" id="fromDt" type="text"  placeholder="start date" autocomplete="off">
								<span class="ml-3 mr-3">~</span>
								<input class="datetimepicker" name="toDt" id="toDt" type="text"  placeholder="end date" autocomplete="off">
					</div>
				</div>
				
				
				<div class="row mb-3 " >
					<div class="col-lg-4">
						<p class="mb-0 edit-title">target amount</p>
					</div>
					<div class="col-lg-4">
						<input type="text" placeholder="target amount" id="tagetAmount" name ="tagetAmount">
					</div>
				</div>
				
				<div class="row mb-3 " >
					<div class="col-lg-4  ">
						<p class="mb-0 edit-title">Amount provided for viewing</p>
					</div>
					<div class="col-lg-4  ">
						<input type="text" placeholder="Amount provided for viewing" id="viewingAmount" name ="viewingAmount">
					</div>
				</div>
				
				<div class="row mb-3 " >
						
					<div class="col-lg-4 ">
						<p class="mb-0 edit-title">Anonymous Sponsorship Received</p>
					</div>
					
					<div class="col-lg-8">
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="AnonymousYN" id="AnonymousYN1" value="Y">
						  <label class="form-check-label" for="AnonymousYN1">YES</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="AnonymousYN" id="AnonymousYN2" value="N">
						  <label class="form-check-label" for="AnonymousYN2">NO</label>
						</div>
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


$(document).ready(function () {

	$(".file-drop-area").on('change', '.file-input', function() {
	    
		/*file upload  */
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
		
	
});


	

</script>

