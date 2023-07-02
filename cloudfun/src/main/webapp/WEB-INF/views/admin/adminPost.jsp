<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<t:layout>


	<section class="page-section m-3">
	
		<div class="row" >
			
			<div class="col-lg-4 ">
				 <div class="input-group mb-3">
					  <div class="input-group-prepend">
					    <label class="input-group-text" for="inputGroupSelect01">TYPE</label>
					  </div>
					  <select class="custom-select" id="typeSelect" onchange="changeType(this)">
					    <option value="" selected>ALL</option>
					    <option value="text">text</option>
					    <option value="picture">picture</option>
					    <option value="comic">comic</option>
					    <option value="music">music</option>
					    <option value="model">3D model</option>
					  </select>
					</div>
	            
	          </div>
			</div>				
		</div>
		<div class="row" >
			<div class="col-lg-12 ">
				<div id="wrapper"></div>
			
			</div>
			
		</div>
	</section>
	
	
<div class="modal fade" id="alertPost" tabindex="-1" role="dialog" aria-labelledby="alertModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">USER INFO</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form>
	          	<div class="row mb-1 " >
					<div class="col-lg-6">
						<p class="mb-0 edit-title">* POST ID</p>
					</div>
					<div class="col-lg-6">
						<input type="hidden" id="POST_ID" name ="POST_ID" readonly>
						<p id="POST_ID2" ></p>
					</div>
				</div>
				
				<div class="row mb-1 " >
					<div class="col-lg-6">
						<p class="mb-0 edit-title">* TITLE</p>
					</div>
					<div class="col-lg-6">
						<!-- <input type="text" id="NAME" name ="NAME" readonly> -->
						<p id="TITLE" name ="TITLE"  ></p>
					</div>
				</div>
				
				<div class="row mb-1 " >
					<div class="col-lg-6">
						<p class="mb-0 edit-title">* TAGS</p>
					</div>
					<div class="col-lg-6">
						<!-- <input type="text" id="EMAIL" name ="EMAIL" readonly> -->
						<p id="TAGS" name ="TAGS"  ></p>
					</div>
				</div>
				
				
				<div class="row mb-1 " >
					<div class="col-lg-6">
						<p class="mb-0 edit-title">* FUN_START_DT</p>
					</div>
					<div class="col-lg-6">
						<!-- <input type="text" id="EMAIL" name ="EMAIL" readonly> -->
						<p id="FUN_START_DT" name ="FUN_START_DT"  ></p>
					</div>
				</div>
				
				
				<div class="row mb-1 " >
					<div class="col-lg-6">
						<p class="mb-0 edit-title">* FUN_END_DT</p>
					</div>
					<div class="col-lg-6">
						<!-- <input type="text" id="EMAIL" name ="EMAIL" readonly> -->
						<p id="FUN_END_DT" name ="FUN_END_DT"  ></p>
					</div>
				</div>
				
				<div class="row mb-1 " >
					<div class="col-lg-6">
						<p class="mb-0 edit-title">* TGT_AMT</p>
					</div>
					<div class="col-lg-6">
						<!-- <input type="text" id="EMAIL" name ="EMAIL" readonly> -->
						<p id="TGT_AMT" name ="TGT_AMT"  ></p>
					</div>
				</div>
				
				
				<div class="row mb-1 " >
					<div class="col-lg-6">
						<p class="mb-0 edit-title">* OPEN_AMT</p>
					</div>
					<div class="col-lg-6">
						<!-- <input type="text" id="EMAIL" name ="EMAIL" readonly> -->
						<p id="OPEN_AMT" name ="OPEN_AMT"  ></p>
					</div>
				</div>
				<div class="row mb-1 " >
					<div class="col-lg-6">
						<p class="mb-0 edit-title">* TYPE</p>
					</div>
					<div class="col-lg-6">
						<!-- <input type="text" id="EMAIL" name ="EMAIL" readonly> -->
						<p id="DOMAIN_TYPE" name ="DOMAIN_TYPE"  ></p>
					</div>
				</div>
				
				
				<div class="row mb-1 " >
					<div class="col-lg-6">
						<p class="mb-0 edit-title">* MEMBER_ID</p>
					</div>
					<div class="col-lg-6">
						<!-- <input type="text" id="EMAIL" name ="EMAIL" readonly> -->
						<p id="MEMBER_ID" name ="MEMBER_ID"  ></p>
					</div>
				</div>
				<div class="row mb-1 " >
					<div class="col-lg-6">
						<p class="mb-0 edit-title">* NAME</p>
					</div>
					<div class="col-lg-6">
						<!-- <input type="text" id="EMAIL" name ="EMAIL" readonly> -->
						<p id="NAME" name ="NAME"  ></p>
					</div>
				</div>
				
				<div class="row mb-1 " >
					<div class="col-lg-6 ">
						<p class="mb-0 edit-title">* AI_YN</p>
					</div>
					
					<div class="col-lg-6">
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="AI_YN" id="AI_Y" value="Y" onclick="return false;">
						  <label class="form-check-label" for="AI_Y">YES</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="AI_YN" id="AI_N" value="N" onclick="return false;">
						  <label class="form-check-label" for="AI_N">NO</label>
						</div>
					</div>
				</div>
				
				<hr>
				<div class="row mb-1 " >
					<div class="col-lg-6 ">
						<p class="mb-0 edit-title">* USE</p>
					</div>
					
					<div class="col-lg-6">
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="USE_YN" id="USE_Y" value="Y">
						  <label class="form-check-label" for="USE_Y">YES</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="USE_YN" id="USE_N" value="N">
						  <label class="form-check-label" for="USE_N">NO</label>
						</div>
					</div>
				</div>
				
				
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="sendMemberInfo()">Save</button>
	      </div>
	    </div>
	    
	  </div>
	</div>
	

</t:layout>
<link rel="stylesheet" href='<c:url value="/css/jquery.datetimepicker.min.css"/>'>
<script src="${path}/resources/js/jquery.datetimepicker.full.min.js"></script>

<script>
var grid ="";

$(document).ready(function () {

	getPostList()
	 /* $("div#wrapper").Grid({
        search: true,
        sort: true,
        columns: ["Name", "Age", "Email"],
        data: [
          ["John", 25, "john@k.com"],
          ["Mark", 59, "mark@e.com"]
        ]
      }); */
	 
	 
});


function getPostList(){
	var jsonData = {
			adminType : $('#typeSelect').find(":selected").val()
	}
	
	$.ajax({
		url: "${path}" + '/api/admin/getPostList',
         dataType:'json',
         data : JSON.stringify(jsonData),
         type: 'post',
         contentType: "application/json"
   }).done(function (res) {
       if(res.error){
       	alert(error);
       	return ; 
       }else{
    	   
       	console.log(res);
       	setGrid(res);
       	
       }
   });
	
	
}


function setGrid(param){
	// nameList paramList
	var listMemberObj = param.memberList;
    /*
     * 글 : text
     * 그림 : picture
     * 만화 : comic
     * 음악 : music
     * 3D Model : model
     * */ 
     
     
	var columns = ["POST_ID", "TITLE","CONTENTS","TAGS", "AI_YN", "FUN_START_DT", "FUN_END_DT","TGT_AMT","OPEN_AMT", "DOMAIN_TYPE", "MEMBER_ID", "NAME","USE_YN"]
	var dbColumns = ["POST_ID", "TITLE","CONTENTS2","TAGS", "AI_YN", "FUN_START_DT", "FUN_END_DT","TGT_AMT","OPEN_AMT", "DOMAIN_TYPE","MEMBER_ID", "NAME","USE_YN"]
	var divId = "div#wrapper"
	
	
	$(divId).text("");
	
	var gridData = [];
	
	for(var i = 0 ; i < listMemberObj.length; i++){
		var tmpRow = [];
		for(var j = 0 ; j < dbColumns.length ; j++){
			
			tmpRow[j] = listMemberObj[i][dbColumns[j]] ;
			
		}
		gridData[i] = tmpRow; 
		
		
		
	}
	
	console.log(gridData)
	
	
	grid = $(divId).Grid({
        search: true,
        sort: true,
        columns: columns,
        data: gridData
        , style: {
            td: {
              border: '1px solid #ccc'
            },
            table: {
              'font-size': '12px'
            }
          }
    });
	
	
	
	grid.on('rowClick', (...args) => modalOpen(args));
	grid.forceRender();
	
}

function modalOpen(param){
	$('#alertPost').modal('toggle')	
	console.log(param[1])
	
	var POST_ID          = param[1].cells[0].data;
	var TITLE            = param[1].cells[1].data;
	var CONTENTS2        = param[1].cells[2].data;
	var TAGS             = param[1].cells[3].data;
	var AI_YN            = param[1].cells[4].data;
	var FUN_START_DT     = param[1].cells[5].data;
	var FUN_END_DT       = param[1].cells[6].data;
	var TGT_AMT          = param[1].cells[7].data;
	var OPEN_AMT         = param[1].cells[8].data;
	var DOMAIN_TYPE      = param[1].cells[9].data;
	var MEMBER_ID        = param[1].cells[10].data;
	var NAME             = param[1].cells[11].data;
	var USE_YN             = param[1].cells[12].data;
	

	
	$("#POST_ID").val(POST_ID)
	$("#POST_ID2").text(POST_ID)
	
	$("#TITLE").text(TITLE)
	$("#TAGS").text(TAGS)
	$("#FUN_START_DT").text(FUN_START_DT)
	$("#FUN_END_DT").text(FUN_END_DT)
	$("#TGT_AMT").text(TGT_AMT)
	$("#OPEN_AMT").text(OPEN_AMT)
	$("#DOMAIN_TYPE").text(DOMAIN_TYPE)
	$("#MEMBER_ID").text(MEMBER_ID)
	$("#NAME").text(NAME)
	
	
	$("input:radio[name='USE_YN']:radio[value="+USE_YN+"]").prop('checked', true);
	$("input:radio[name='AI_YN']:radio[value="+AI_YN+"]").prop('checked', true);

}


function sendMemberInfo(){
	var jsonData = {
			postId : $("#POST_ID").val()
			, useYn : $("input:radio[name='USE_YN']:checked").val()
	}
	
	
	if (confirm("Would you like to save??") == true) {
		$.ajax({
	    	  url: preUrl + '/api/admin/setPostInfo',
	          dataType:'json',
	          data : JSON.stringify(jsonData),
	          type: 'post',
	          contentType: "application/json"
	    }).done(function (res) {
	        if(res.error){
	        	alert(error);
	        	return ; 
	        }else{
	        	location.reload();
	        }
	    });
		
	} 
	
}

// type change
function changeType(){
	getPostList();
}	

</script>

