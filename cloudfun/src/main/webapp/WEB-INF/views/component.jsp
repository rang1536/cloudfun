<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<t:layout>
   <!-- edit Component -->
   <section class="page-section spad contact-page">
		<div class="container">
			
			<!-- 1:1 -->
			<form class="comment-form" onSubmit="return false;">
				<div class="row">
					<div class="col-lg-12 mb-lg-0">
						<h4 class="comment-title">1:1 </h4>
					</div>
					
					<div class="col-lg-6 mb-lg-0">
						<div class="row">
							<div class="col-md-6">
								<input type="text" placeholder="Name">
							</div>
							<div class="col-md-6">
								<input type="email" placeholder="Email">
							</div>
							<div class="col-lg-12">
								<input type="text" placeholder="Subject">
								<textarea placeholder="Message"></textarea>
								<button class="site-btn btn-sm">Send</button>
							</div>
						</div>
					</div>
					<div class="col-lg-6 mb-lg-0">
					
						<input type="text" placeholder="Subject">
						
						<!-- 검색 input box & button -->
						<div class="search-widget mb-4">
							<input type="text" placeholder="Search">
							<button><i class="fa fa-search"></i></button>
						</div>
						
						<!-- 멀티 파일 업로드 -->
						<div class="file-drop-area mb-4">
						  <span class="file-message">Choose files or drag and drop files here</span>
						  <input class="file-input" name="uploadFile" type="file" multiple>
						</div>
						<button type="button" class="site-btn btn-sm" id="sendFile">file Send</button>
						 <a href="<c:url value='/fileDownload/111'/> ">111 파일 다운로드</a><br>
						
						            
					</div>
				</div>
				
				<!-- img preview 업로드  -->
				<div class="row mt-5">
					<div class="col-lg-4">
						<div class="file-preview-container" id="divImageMediaPreview">
						</div>
					</div>
					<div class="col-lg-8">
						<div class="file-drop-area mb-3 ">
						
							<span class="file-message">Choose Files or drag and drop files here</span>
							<input type="file" class="file-input" id="inputPreview" accept=".jfif,.jpg,.jpeg,.png,.gif" multiple>
						</div>
					</div>
					
					
					<div class="col-lg-8">
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
						  <label class="form-check-label" for="inlineRadio1">1</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">
						  <label class="form-check-label" for="inlineRadio2">2</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3" disabled>
						  <label class="form-check-label" for="inlineRadio3">3 (disabled)</label>
						</div>
					</div>
					
					<div class="col-md-4">
          
			         		<div class="form-group">
								<input type="text" class="form-control" name="country"/>
							</div>
			        </div>
			        
			        
			        <div class="col-md-12">
			            <button type="button" class="btn btn-warning btn-block" onclick="document.getElementById('inputFile').click()">Add Image</button>
			            <div class="form-group inputDnD">
			                <label class="sr-only" for="inputFile">File Upload</label>
			                <input type="file" class="form-control-file text-warning font-weight-bold" id="inputFile" accept="image/*" onchange="readUrl(this)" data-title="Drag and drop a file">
			            </div>
			        
			        </div>
								
				</div>
				
				
				
			</form>
			
			
			
		</div>
		
		
	</section>
   

	
</t:layout>


<link rel="stylesheet" href='<c:url value="/css/amsify.suggestags.css"/>'>
<script src="${path}/resources/js/jquery.amsify.suggestags.js"></script>




<script>
$(document).ready(function(){
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
	
	
	// 파일업로드 이벤트 걸기.
	$("#sendFile").on("click",function(e){
		
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
		console.log(files);
		
		
		formData.append("test","1234");
		
		for(var i =0; i< files.length ; i++){
			formData.append("uploadFile",files[i]);	
		}
		
		 $.ajax({
	    	  url: preUrl + '/api/post',
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
	
	
	//tags
	$('input[name="tags"]').amsifySuggestags();

	
	
	
})
	


</script>

<script src="${path}/resources/js/bootstrap.min.js"></script>

