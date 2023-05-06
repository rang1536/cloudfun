<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<t:layout>

	<section class="page-info-section">
		<div class="pi-content">
			<div class="container">
				<div class="row">
					<div class="col-xl-5 col-lg-6 text-white">
						<h2>CREATION LIST</h2>
						<p>...</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Page info section -->


	<!-- Page section -->
	<section class="page-section recent-game-page spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div class="row">
					
						<c:forEach var="item" items="${resultList}" >
							<div class="col-md-6" ">
								<input type="hidden" value="${item.POST_ID}"/>
								<div class="recent-game-item">
									<div class="rgi-thumb set-bg" data-setbg="${path}/display?filename=${item.THUMBNAIL_NM}">
										<div class="cata new">${item.DOMAIN_TYPE}</div>
									</div>
									<div class="rgi-content">
										<h5>${item.TITLE}</h5>
										<p>${item.CONTENTS2} </p>
										
										<div class="comment mb-2">PERIOD : ${item.FUN_START_DT} ~ ${item.FUN_END_DT}</div>
										<a href="#" class="comment">${item.NAME}</a>
										
										<%-- <div class="rgi-extra">
											<div class="rgi-star"><img src="${path}/img/icons/star.png" alt=""></div>
											<div class="rgi-heart"><img src="${path}/img/icons/heart.png" alt=""></div>
										</div> --%>
										<div class="row mt-3 ml-0">
											<div class="col-md-6 text-left">
												<button class="btn-info btn-w-auto "  onclick="postSend('${item.POST_ID}')">View</button>
											</div>
											<div class="col-md-6 text-right">
												<button class=" btn-info btn-w-auto"  onclick="postEdit('${item.POST_ID}')">Edit</button>
											</div>
										</div>	
									</div>
									
									
								</div>
								
							</div>
						  
						</c:forEach>
						
						
					</div>
					<div class="site-pagination">
						
						
						<c:if test="${arrowYN eq 'Y'}">
							<span onclick="postPage(${arrowBeforePage})">◁</span>
						</c:if>
						<c:forEach var="item" items="${pageList}" >
							<span class="<c:if test="${pageNo eq item}">active</c:if>"  onclick="postPage(${item})">${item}.</span>
						</c:forEach>
						<c:if test="${arrowYN eq 'Y'}">
							<span onclick="postPage(${arrowNextPage})">▷</span>
						</c:if>
<!-- 
					
						<span class="active">01.</span>
						<a href="#">02.</a>
						<a href="#">03.</a> -->
					</div>
				</div>
				<!-- sidebar -->
				<div class="col-lg-4 col-md-7 sidebar pt-5 pt-lg-0">
					<!-- widget -->
					<div class="widget-item">
						<form class="search-widget">
							<input type="text" placeholder="Search">
							<button><i class="fa fa-search"></i></button>
						</form>
					</div>
					<%-- <!-- widget -->
					<div class="widget-item">
						<h4 class="widget-title">Latest Posts</h4>
						<div class="latest-blog">
							
						<c:forEach var="item" items="${resentList}" >
							<div class="lb-item" onclick="postSend(this)">	
								<input type="hidden" value="${item.POST_ID}"/>		
								<div class="lb-thumb set-bg" data-setbg="${path}/display?filename=${item.THUMBNAIL_NM}"></div>
								<div class="lb-content">
									<div class="lb-date">${item.FUN_START_DT} ~ ${item.FUN_END_DT}</div>
									<p>${item.TITLE}</p>
									<a href="#" class="lb-author">${item.NAME}</a>
								</div>
							</div>
						</c:forEach>
							
						</div>
					</div> --%>
					<!-- widget -->
					
					
				</div>
			</div>
		</div>
	</section>
	<!-- Page section end -->
	
	
	
</t:layout>



<script>
function postSend(param){
	//console.log($(param).children("input").val())
	location.href="${path}/post/viewText?postId=" + param;
}

function postEdit(param){
	//console.log($(param).children("input").val())
	location.href="${path}/post/postEdit?postId=" + param;
	
	
	
	
}



function postPage(param){
	location.href="${path}/post/myPostList?pageNo="+ param
}

$(document).ready(function() {
	
})


</script>

