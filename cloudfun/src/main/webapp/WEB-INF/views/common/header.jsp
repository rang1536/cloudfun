<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page  language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>


<!-- Page Preloder -->
<!-- <div id="preloder">
	<div class="loader"></div>
</div> -->
<%
	session = request.getSession();

	String paramLocal = "en";
	
	if(session.getAttribute("localCountry")==null){
		if(request.getLocale().getCountry().equals("KR")) {
			paramLocal = "kr";
			session.setAttribute("localCountry", "kr"); // KR
		}else{
			session.setAttribute("localCountry", "en"); // KR
		}
	}
	
	paramLocal = (String)session.getAttribute("localCountry");
	System.out.println(paramLocal);
	pageContext.setAttribute("paramLocal",paramLocal);
	
	
	// type
	if(session.getAttribute("type")==null){
		session.setAttribute("type","text");
	}
	
	pageContext.setAttribute("type",session.getAttribute("type"));
	
	
%>
<fmt:setLocale value="${paramLocal}"/>

<fmt:bundle basename="message.local">

<!-- Header section -->
<header class="header-section">
	<div class="container">
		<!-- logo -->
		<div class="text-right">
		<select class="" id="changeType" onchange="changesType(this)" style="display:none">
		    <option value="picture" <c:if test="${type == 'picture'}">selected </c:if> >picture</option>
		    <option value="text"    <c:if test="${type == 'text'}">selected </c:if> >text</option>
		    <option value="comic"   <c:if test="${type == 'comic'}">selected </c:if> >comic</option>
		    <option value="music"   <c:if test="${type == 'music'}">selected </c:if> >music</option>
		    <option value="model"   <c:if test="${type == 'model'}">selected </c:if> >model</option>
		</select>
		</div>
		<div class="text-right" style="color:white;">
				<a style="color:white;" href="${path}/local/en">English</a>  /  <a style="color:white;" href="${path}/local/ko">한국어</a>
				
		</div>
		
		<a class="site-logo" href="${path}/">
			<img src="${path}/img/new/logo2.png" alt="">
		</a>
		
		
		
		   <% if(session.getAttribute("memberId")!= null){ %>
		    <div class="user-panel mt-3" onclick="logout();">
				<a href="#"><fmt:message key="header.Logout" /></a>
			</div>
		   <%}else{%>
		    <div class="user-panel mt-3" onclick="loginWithGoogle();">
				<a href="#"><fmt:message key="header.Login" /></a>  /  <a href="#"><fmt:message key="header.Register" /></a>
			</div>
			
				
			
			
				
		    <%} %>
		 	
		
		
		
		
		
		<!-- responsive -->
		<div class="nav-switch">
			<i class="fa fa-bars"></i>
		</div>
		<!-- site menu -->
		<nav class="main-menu">
			<ul>
				<!-- HOME -->
				<li><a href="${path}/"><fmt:message key="header.HOME" /></a></li>
				
				<!-- CREATION LIST -->
				<li><a href="${path}/post/postList"><fmt:message key="header.CREATION.LIST" /></a></li>
				
				<!-- CREATION LIST -->
				<li><a href="${path}/post/myPostList"><fmt:message key="header.MY.CREATION.LIST" /></a></li>
				
				<%-- 
				<!-- EDIT-TEXT -->
				<li><a href="${path}/post/editText"><fmt:message key="header.EDIT-TEXT" /></a></li> 
				--%>
				<!-- EDIT-PICTURES -->
				<li><a href="${path}/post/editPicture"><fmt:message key="header.EDIT-PICTURES" /></a></li>
				<%-- 
				<!-- EDIT-3D MODEL -->
				<li><a href="${path}/post/edit3D"><fmt:message key="header.EDIT-3D.MODEL" /></a></li>
				<!-- EDIT-MUSIC -->
				<li><a href="${path}/post/editMusic"><fmt:message key="header.EDIT-MUSIC" /></a></li>
				<!-- EDIT-COMIC -->
				<li><a href="${path}/post/editComic"><fmt:message key="header.EDIT-COMIC" /></a></li>
				 --%>
				
				<%-- 
				<li><a href="${path}/post/viewText?postId=1">viewText</a></li> --%>
				
				<!-- JOIN -->
				<li><a href="${path}/join"><fmt:message key="header.JOIN" /></a></li>
				<!-- editMember -->
				<li><a href="${path}/editMember"><fmt:message key="header.EDIT-MEMBER" /></a></li>
				<!-- TERMS -->
				<li><a href="${path}/terms"><fmt:message key="header.TERMS" /></a></li>
				
				
				<li><a href="${path}/local/en">English</a></li>
				<li><a href="${path}/local/ko">한국어</a></li>
				
				
				<li><a href="${path}/admin/member">관리자-member</a></li>
				<li><a href="${path}/admin/adminPost">관리자-post</a></li>
				<li><a href="${path}/admin/adminReport">관리자-report</a></li>
				
				
				<%-- 
				<li><a href="${path}/component"><fmt:message key="header.COMPONENTS" /></a></li> 
				--%>
			</ul>
		</nav>
	</div>
</header>
<!-- Header section end -->


</fmt:bundle>