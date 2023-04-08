<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<t:layout>
   <!-- edit Component -->
   <section class="page-section spad contact-page">
		<div class="container">
			<h3>MAIN</h3>
			<a href="${path}/" >mainPage</a>
			<a href="#" onclick="loginWithGoogle();" >Login</a>
			<a href="${path}/join" >join</a>
			
			<hr>
			<h3>POST PAGE </h3>
			
			
			
			
			<hr>
			<h3>EDIT PAGE</h3>
			<a href="${path}/post/editText" >Edit-Text</a>
			<a href="${path}/post/editPicture" >Edit-Picture</a>
			<a href="${path}/post/edit3D" >Edit-3D</a>
			<a href="${path}/post/editComic" >Edit-Comic</a>
			<a href="${path}/post/editMusic" >Edit-Music</a>
			
			
			<hr>
			<a href="${path}/terms">Terms of Service</a>

			
		
		</div>
		
	</section>
   

	
</t:layout>
