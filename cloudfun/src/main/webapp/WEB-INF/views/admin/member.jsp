<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<t:layout>


	<section class="page-section recent-game-page spad">
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
	          	<div class="row mb-3 " >
					<div class="col-lg-4">
						<h5 class="mb-0 edit-title">* ID</h5>
					</div>
					<div class="col-lg-4">
						<input type="hidden" id="ID" name ="ID" readonly>
						<p id="pId" ></p>
					</div>
				</div>
				
				<div class="row mb-3 " >
					<div class="col-lg-4">
						<h5 class="mb-0 edit-title">* NAME</h5>
					</div>
					<div class="col-lg-4">
						<!-- <input type="text" id="NAME" name ="NAME" readonly> -->
						<p id="NAME" name ="NAME"  ></p>
					</div>
				</div>
				
				<div class="row mb-3 " >
					<div class="col-lg-4">
						<h5 class="mb-0 edit-title">* EMAIL</h5>
					</div>
					<div class="col-lg-4">
						<!-- <input type="text" id="EMAIL" name ="EMAIL" readonly> -->
						<p id="EMAIL" name ="EMAIL"  ></p>
					</div>
				</div>
				
				<hr>
				<div class="row mb-3 " >
				<div class="col-lg-2 ">
					<h5 class="mb-0 edit-title">* USE</h5>
				</div>
				
				<div class="col-lg-4">
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="USE_YN" id="USE_Y" value="Y">
					  <label class="form-check-label" for="USE_Y">YES</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="USE_YN" id="USE_N" value="N">
					  <label class="form-check-label" for="USE_N">NO</label>
					</div>
				</div>
				
				<div class="col-lg-2 ">
					<h5 class="mb-0 edit-title">* ADMIN</h5>
				</div>
				
				<div class="col-lg-4">
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="ADMIN_YN" id="ADMIN_Y" value="Y">
					  <label class="form-check-label" for="USE_Y">YES</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="ADMIN_YN" id="ADMIN_N" value="N">
					  <label class="form-check-label" for="ADMIN_N">NO</label>
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


$(document).ready(function () {

	getMember()
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


function getMember(){
	var jsonData = {}
	
	$.ajax({
		url: "${path}" + '/api/admin/getMemberList',
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
	var columns = ["ID","NAME","EMAIL","TEXT","PICTURE","COMIC","MUSIC","3D MODEL", "USE_YN","ADMIN_YN"]
	var dbColumns = ["MEMBER_ID", "NAME","EMAIL","TYPE_01","TYPE_02","TYPE_03","TYPE_04","TYPE_05","USE_YN","ADMIN_YN"]
	var divId = "div#wrapper"
	
	/* columns.push({ 
	        name: 'Actions',
	        formatter: (cell, row) => {
	          return h('button', {
	            className: 'py-2 mb-4 px-4 border rounded-md text-white bg-blue-600',
	            onClick: () => alert(`Editing "${row.cells[0].data}" "${row.cells[1].data}"`)
	          }, 'Edit');
	        }
	}) */
	
	var gridData = [];
	
	for(var i = 0 ; i < listMemberObj.length; i++){
		var tmpRow = [];
		for(var j = 0 ; j < dbColumns.length ; j++){
			
			tmpRow[j] = listMemberObj[i][dbColumns[j]] ;
			
		}
		gridData[i] = tmpRow; 
		
		
		
	}
	
	console.log(gridData)
	
	
	var grid = $(divId).Grid({
        search: true,
        sort: true,
        columns: columns,
        data: gridData
    });
	
	grid.on('rowClick', (...args) => modalOpen(args));
//	grid.on('cellClick', (...args) => console.log('cell: ' + JSON.stringify(args), args));
	
}

function modalOpen(param){
	$('#alertPost').modal('toggle')	
	console.log(param[1])
	
	var memberId = param[1].cells[0].data;
	var memberName = param[1].cells[1].data;
	var memberEmail = param[1].cells[2].data;
	
	var memberUseYn= param[1].cells[8].data;
	var memberAdminYn = param[1].cells[9].data;
	
	$("#ID").val(memberId)
	$("#pId").text(memberId)
	$("#NAME").text(memberName)
	$("#EMAIL").text(memberEmail)
	
	$("input:radio[name='USE_YN']:radio[value="+memberUseYn+"]").prop('checked', true);
	$("input:radio[name='ADMIN_YN']:radio[value="+memberAdminYn+"]").prop('checked', true);

}


function sendMemberInfo(){
	var jsonData = {
			memberId : $("#ID").val()
			, useYn : $("input:radio[name='USE_YN']:checked").val()
			, adminYn : $("input:radio[name='ADMIN_YN']:checked").val()
	}
	
	
	if (confirm("Would you like to save??") == true) {
		$.ajax({
	    	  url: preUrl + '/api/admin/setMemberInfo',
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

	
	

</script>

