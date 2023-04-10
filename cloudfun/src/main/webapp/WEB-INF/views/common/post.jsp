<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<div class="row mb-3" >
	<div class="col-lg-1">
		<p class="edit-title mb-0" >MAIN IMAGE</p>
	</div>
	<div class="col-lg-4 mb-1">
		<div class="file-preview-container" id="divImageMediaPreview">
		</div>
	</div>
	<div class="col-lg-7 mb-1">
		<!-- <div class="file-drop-area">
			<span class="file-message span-upload">Choose the main picture</span>
			<input type="file" class="file-input" name="mainImg" id="inputPreview" accept=".jfif,.jpg,.jpeg,.png,.gif" multiple>
		</div> -->
		
		
		<button type="button" class="btn btn-info btn-block" onclick="document.getElementById('inputPreview').click()">Add Image</button>
	    <div class="form-group inputDnD file-drop-area">
	        <!-- <label class="sr-only " for="inputFile">File Upload</label> -->
	        <label class="sr-only file-message" for="inputFile">File Upload</label>
	        <input type="file" class="form-control-file text-info font-weight-bold"  name="mainImg" id="inputPreview" accept="image/*" onchange="readUrl(this)" data-title="Drag and drop a file">
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
