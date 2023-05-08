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
								
				<jsp:include page="/WEB-INF/views/common/post.jsp"/>
				
				<div class="row mb-3" >
					<div class="col-lg-1">
						<p class="edit-title mb-0" >DESCRIPTION IMAGE</p>
					</div>
					<div class="col-lg-11 mb-1">
					
						<button type="button" class="btn btn-info btn-block" onclick="document.getElementById('inputPreview3').click()">Upload description image</button>
					    <div class="form-group inputDnD file-drop-area">
					        <label class="sr-only file-message" for="inputFile">Upload description image</label>
					        <input type="file" class="form-control-file text-info font-weight-bold"  name="desImg" id="inputPreview3" accept=".jfif,.jpg,.jpeg,.png,.gif" onchange="readUrl(this)" data-title="Drag and drop a file">
					    </div>
					
					</div>
					<div class="col-lg-1">
						<p class="explain-text">File List</p>
					</div>
					<div class="col-lg-11 mb-1">
						<div class="file-preview-container " >
							<div class="row m-0" id="divImageMediaPreview3">
							
							
							</div>
						</div>
					</div>
					
				</div>	
				
				
				<div class="row mb-3" >
					<div class="col-lg-2">
						<p class="edit-title mb-0" >CREATIVE FILE</p>
					</div>
					<div class="col-lg-6 mb-1">
						<table class="table table-bordered table-sm">
						  <thead>
						    <tr>
						      <%-- <th scope="col">#</th> --%>
						      <th scope="col" class="text-center">FILE NAME</th>
						      <th scope="col" class="text-center">UPDATED</th>
						      <th scope="col" class="text-center">DELETE</th>
						    </tr>
						  </thead>
						  <tbody id="divImageMediaPreview2">
						  	
						  </tbody>
    						
						</table>
					</div>
					<div class="col-lg-4 mb-1">
						<button type="button" class="btn btn-info btn-block" onclick="document.getElementById('inputPreview2').click()">Upload your creation(3D Model / *.zip)</button>
					    <div class="form-group inputDnD file-drop-area">
					        <!-- <label class="sr-only " for="inputFile">File Upload</label> -->
					        <label class="sr-only file-message" for="inputFile">Upload your creation(*.zip)</label>
					        <input type="file" class="form-control-file text-info font-weight-bold"  name="uploadFile" id="inputPreview2" accept=".zip" onchange="readUrl(this)" data-title="Drag and drop a file">
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

var bolEdit = false;


var fileNo = 0;
var fileNo3 = 0;
var filesArr = new Array();
var filesArr3 = new Array();

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
		
		/* var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files; */
		
		
		// uploadFile
		var files = filesArr
		
		for(var i =0; i< files.length ; i++){
			formData.append("uploadFile",files[i]);	
		} 
		
		// description img
		// uploadFile
		var files3 = filesArr3
		
		for(var i =0; i< files3.length ; i++){
			formData.append("desImg",files3[i]);	
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
	        	location.href = "${path}/post/viewText?postId="+res.postId
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
		                img.attr("style", "width: 100%; height:100%;max-height:350px; padding: 10px");
		                img.attr("src", e.target.result);
		                dvPreview.append(img);
		            }
		            reader.readAsDataURL(file[0]);                
		    });
		} else {
		    alert("This browser does not support HTML5 FileReader.");
		}
	
	
	});
	 
	// picture file upload
	$(".file-drop-area").on('change', '#inputPreview2', function() {
		
	    var maxFileCnt = 6;   // 첨부파일 최대 개수
	    var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
	    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
	    var curFileCnt = this.files.length;  // 현재 선택된 첨부파일 개수
	 // 첨부파일 개수 확인
	    if (curFileCnt > remainFileCnt) {
	        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
	    } else {
		
			var filesCount = $(this)[0].files.length;
			var textbox = $(this).prev();
	
			if (filesCount === 1) {
			var fileName = $(this).val().split('\\').pop();
				textbox.text(fileName);
			} else {
				textbox.text(filesCount + ' files selected');
			}
		
			if (typeof (FileReader) != "undefined") {
			    var dvPreview = $("#divImageMediaPreview2");
			    //dvPreview.html("");            
			    $($(this)[0].files).each(function () {
			        var file = $(this);       
			        if (validation(file[0])) {
			            var reader = new FileReader();
			            reader.onload = function (e) {
			            	
				            	var today = new Date();   
	
				            	var year = today.getFullYear(); // 년도
				            	var month = today.getMonth() + 1;  // 월
				            	var date = today.getDate();  // 날짜
				            	var day = today.getDay();  // 요일
				            	var dt = day +'/'+ month +'/'+ year; 
			            	
			            	
			            		var tr = $("<tr class='filebox' id='file" + fileNo + "'></tr>");
			            		var td1 = $('<td class="align-middle">' + fileName + '</td>')
			            		var td2 = $('<td class="align-middle"> '+    dt    + '</td>')
			            		var td3 = $('<td class="align-middle"><button type="button" class="delete btn btn-info btn-sm" onclick="deleteFile(' + fileNo + ');">DELETE</button></td>')
			            	
			            	 	tr.append(td1);
			            		tr.append(td2);
			            		tr.append(td3);
			            		
			            		dvPreview.append(tr);
				                
				                filesArr.push(file[0]);
				                fileNo++;
			             }
			           
			            reader.readAsDataURL(file[0]);         
			            
			    	} else {
			    		 return true;
	            	}
			    });
			} else {
			    alert("This browser does not support HTML5 FileReader.");
			}
			
			/* document.querySelector("input[type=inputPreview2]").value = ""; */
	    }
	
	});
	
	

	// picture file upload
	$(".file-drop-area").on('change', '#inputPreview3', function() {
		
	    var maxFileCnt = 6;   // 첨부파일 최대 개수
	    var attFileCnt = document.querySelectorAll('.filebox3').length;    // 기존 추가된 첨부파일 개수
	    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
	    var curFileCnt = this.files.length;  // 현재 선택된 첨부파일 개수
	 // 첨부파일 개수 확인
	    if (curFileCnt > remainFileCnt) {
	        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
	    } else {
		
			var filesCount = $(this)[0].files.length;
			var textbox = $(this).prev();
	
			if (filesCount === 1) {
			var fileName = $(this).val().split('\\').pop();
				textbox.text(fileName);
			} else {
				textbox.text(filesCount + ' files selected');
			}
		
			if (typeof (FileReader) != "undefined") {
			    var dvPreview = $("#divImageMediaPreview3");
			    //dvPreview.html("");            
			    $($(this)[0].files).each(function () {
			        var file = $(this);       
			        if (validation(file[0])) {
			            var reader = new FileReader();
			            reader.onload = function (e) {
			            	
				            	var div = $("<div class='col-lg-2 filebox3 row m-0' id='fileImg" + fileNo3 + "' style='border: 1px solid #d6dee7;' > </div>");
				            	var div2 = $("<div class='col-lg-12 text-center mb-3'> </div>");
				            	var div2_2 = $("<div class='col-lg-12'> </div>");
				            	var div3 = $("<div class='text-center'> </div>");
				                var img = $("<img />");
				                img.attr("style","max-height:150px;padding: 0px");
				                img.attr("src", e.target.result);
				                var pTag = $('<p class="name">' + fileName + '</p>')
				                var aTag = $('<button type="button" class="delete btn btn-info btn-sm" onclick="deleteFileImg(' + fileNo3 + ');">DELETE</button>')
				                
				                
				                div3.append(aTag);
				                div3.append(pTag);
				                
				                
				                div2_2.append(div3);
				                div2.append(img);
				                
				                div.append(div2_2);
				                div.append(div2);
				                dvPreview.append(div);
				                
				                filesArr3.push(file[0]);
				                fileNo3++;
			             }
			           
			            reader.readAsDataURL(file[0]);         
			            
			    	} else {
			    		 return true;
	            	}
			    });
			} else {
			    alert("This browser does not support HTML5 FileReader.");
			}
			
			/* document.querySelector("input[type=inputPreview2]").value = ""; */
	    }
	
	});
	

	//tags
	$('input[name="tags"]').amsifySuggestags({
	    tagLimit: 5
	});
	
	<c:if test="${not empty param.postId}">
		bolEdit = true
		$('input[name="tags"]').amsifySuggestags({},'destroy');
	</c:if>
		
			
	if(bolEdit){
		var jsonData = {
				 postId : "${param.postId}"
		}
		
		
		$.ajax({
			url: "${path}" + '/api/post/getPostData',
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
	        	
	        	setPreviewImg(res);
	        	setCommonInfo(res);
	        	//setTextFile(res);
	        	setEdit3DImgPreview(res);
	        	setEdit3DFilePreview(res);
	        	
	        }
	    });
		
		
	}
	
	
	
})


/* 첨부파일 검증 */
function validation(obj){
    const fileTypes = ['application/pdf', 'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'application/haansofthwp', 'application/x-hwp', 'application/x-zip-compressed'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (100 * 1024 * 1024)) {
        alert("최대 파일 용량인 5MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("첨부가 불가능한 파일은 제외되었습니다.");
        return false;
    } else {
        return true;
    }
}

/* 첨부파일 삭제 */
function deleteFile(num,oldFileId) {
    document.querySelector("#file" + num).remove();
    filesArr[num].is_delete = true;
    if(oldFileId){
    	deleteOldFile(oldFileId);	
    }
    
}

/* 첨부파일 삭제 */
function deleteFileImg(num,oldFileId) {
    document.querySelector("#fileImg" + num).remove();
    filesArr[num].is_delete = true;
    if(oldFileId){
    	deleteOldFile(oldFileId);	
    }
    
}

</script>