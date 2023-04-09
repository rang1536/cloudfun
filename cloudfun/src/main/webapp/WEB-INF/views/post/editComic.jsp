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
				
				<!-- upload file -->
				<div class="row mb-3" >
					<div class="col-lg-1">
						<p class="edit-title mb-0" >CREATIVE FILE</p>
					</div>
					<div class="col-lg-11">
						<div class="file-drop-area mb-4">
						  <span class="file-message span-upload">Upload your creation</span>
						  <input class="file-input" name="uploadFile" type="file" >
						</div>
					</div>
				</div>
				
				
				<jsp:include page="/WEB-INF/views/common/post.jsp"/>
				
				
				
				
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
		
		var inputFile2 = $("input[name='mainImg']");
		var files2 = inputFile2[0].files;
		
		for(var i =0; i< files2.length ; i++){
			formData.append("mainImg",files2[i]);	
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

