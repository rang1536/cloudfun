<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page  language="java" contentType="text/html;charset=UTF-8" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<!-- Page Preloder -->
<!-- <div id="preloder">
	<div class="loader"></div>
</div> -->

<!-- Header section -->
<header class="header-section">
	<div class="container">
		<!-- logo -->
		<a class="site-logo" href="${path}/">
			<img src="${path}/img/new/logo2.png" alt="">
		</a>
		
		   <% if(session.getAttribute("memberId")!= null){ %>
		    <div class="user-panel" onclick="logout();">
				<a href="#">Logout</a>
			</div>
		   <%}else{%>
		    <div class="user-panel" onclick="loginWithGoogle();">
				<a href="#">Login</a>  /  <a href="#">Register</a>
			</div>
		    <%} %>
		 
		
		
		
		
		
		<!-- responsive -->
		<div class="nav-switch">
			<i class="fa fa-bars"></i>
		</div>
		<!-- site menu -->
		<nav class="main-menu">
			<ul>
				<li><a href="${path}/">HOME</a></li>
				
				
				<li><a href="${path}/post/postList">CREATION LIST</a></li>
				
				
				<li><a href="${path}/post/editText">EDIT-TEXT</a></li>
				<li><a href="${path}/post/editPicture">EDIT-PICTURES</a></li>
				<li><a href="${path}/post/edit3D">EDIT-3D MODEL</a></li>
				<li><a href="${path}/post/editMusic">EDIT-MUSIC</a></li>
				<li><a href="${path}/post/editComic">EDIT-COMIC</a></li>
				
				
				
				
				<%-- 
				<li><a href="${path}/post/viewText?postId=1">viewText</a></li> --%>
				
				<li><a href="${path}/component">COMPONENTS</a></li>
				
				
				<li><a href="${path}/join">JOIN</a></li>
				<li><a href="${path}/terms">TERMS</a></li>
				
				<!-- <li><a href="community.html">Forums</a></li>
				<li><a href="contact.html">Contact</a></li> -->
			</ul>
		</nav>
	</div>
</header>
<!-- Header section end -->