<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<t:layout>
   <!-- edit Component -->
   <section class="page-section spad contact-page">
		<div class="container">
			
			<!-- 1:1 -->
			<form class="comment-form">
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
						
						<div class="search-widget mb-4">
							<input type="text" placeholder="Search">
							<button><i class="fa fa-search"></i></button>
						</div>
						
						<div class="file-drop-area mb-4">
						  <span class="file-message">Choose files or drag and drop files here</span>
						  <input class="file-input" type="file" multiple="">
						</div>
						
						
											
						
						
						            
					</div>
				</div>
				
				<!-- img preview  -->
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
				</div>
				
				
				
			
				
				
				
				
			</form>
			
		</div>
		
	</section>
   

	
</t:layout>

<script>
	$(".file-drop-area").on('change', '.file-input', function() {
    
		/*파일업로드  */
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
	

</script>

<script src="${path}/resources/js/bootstrap.min.js"></script>

