<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<t:layout>
	<!-- Page info section -->
	<%-- <section class="page-info-section set-bg" data-setbg="${path}/img/page-top-bg/2.jpg">
		<div class="pi-content">
			<div class="container">
				<div class="row">
					<div class="col-xl-5 col-lg-6 text-white">
						<h2>Genji - The Game</h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem maximus mauris scelerisque, at rutrum nulla dictum.</p>
					</div>
				</div>
			</div>
		</div>
	</section> --%>
	<!-- Page info section -->

<!--  
fileList
result

-->
	<!-- Page section -->
	<section class="page-section single-blog-page spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div class="blog-thumb set-bg" data-setbg="${path}/display?filename=${result.FILE_ID}">
						<div class="cata new">${result.DOMAIN_TYPE}</div>
						<div class="rgi-extra">
							<%-- <div class="rgi-star"><img src="${path}/img/icons/star.png" alt=""></div>
							<div class="rgi-heart"><img src="${path}/img/icons/heart.png" alt=""></div> --%>
						</div>
					</div>
					
					
					
					
					
					<div class="blog-content">
						<h3>${result.TITLE}</h3>
						<a href="" class="meta-comment">3comment</a>
						<p style="white-space: pre-line;">${result.CONTENTS}</p>
					</div>
					
					<c:if test="${result.DOMAIN_TYPE == 'text'}">
					<div class="blog-content">
						<h3>PREVIEW</h3>
						<c:set var="loop_flag" value="false" />
						<c:forEach var="item" items="${fileList}" varStatus="status">
							<c:if test="${not empty  item.TXT_PREVIEW }">
							
								<c:if test="${not loop_flag}">
								
									<p style="white-space: pre-line;">${item.TXT_PREVIEW}</p>
									<p style="white-space: pre-line;">................</p>
									<c:set var="loop_flag" value="true" />
									
								</c:if>
							
							</c:if>
						
						
						</c:forEach>
					</div>
					</c:if>
					
					
					<c:if test="${result.DOMAIN_TYPE == 'model'}">
					<div class="comment-warp">
						<h4 class="mb-3">Description Image</h4>
						
						<div id="demo" class="carousel slide" data-ride="carousel">
						
							 <!-- Indicators -->
							 <ul class="carousel-indicators">
							<c:set var="loop_flag" value="false" />
							<c:forEach var="item" items="${fileList3}" varStatus="status">
								<c:if test="${not empty  item.THUMBNAIL_NM }">
									
									<c:choose>
										<c:when test="${not loop_flag}">
											<li data-target="#demo" data-slide-to="0" class="active"></li>
											<c:set var="loop_flag" value="true" />
										</c:when>
										<c:otherwise>
											<li data-target="#demo" data-slide-to="${status.index}" ></li>
										</c:otherwise>
									</c:choose>
							        
						        </c:if>
							</c:forEach>
							</ul>
						
							<!-- The slideshow -->
							  <div class="carousel-inner">
								<c:set var="loop_flag" value="false" />
								<c:forEach var="item" items="${fileList3}" varStatus="status">
									<c:if test="${not empty  item.THUMBNAIL_NM }">
										
										<c:choose>
											<c:when test="${not loop_flag}">
												<div class="carousel-item active">
											      <img src="${path}/display?filename=${item.THUMBNAIL_NM}" width="1100" height="500">
											    </div>
												<c:set var="loop_flag" value="true" />
											</c:when>
											<c:otherwise>
												<div class="carousel-item">
													<img src="${path}/display?filename=${item.THUMBNAIL_NM}" width="1100" height="500">
											    </div>
											</c:otherwise>
										</c:choose>
								        
							        </c:if>
								</c:forEach>
							</div>
						
							  <!-- Left and right controls -->
							  <a class="carousel-control-prev" href="#demo" data-slide="prev">
							    <span class="carousel-control-prev-icon"></span>
							  </a>
							  <a class="carousel-control-next" href="#demo" data-slide="next">
							    <span class="carousel-control-next-icon"></span>
							  </a>
							  
						</div>
						
					</div>
					</c:if>
					
					<div class="comment-warp">
						<h4 class="">Creations</h4>
						<p class="mb-3">If you sponsor more than the minimum donation, you can download the creation.</p>
						
						
						<c:set var="loop_flag" value="false" />
						<c:forEach var="item" items="${fileList}" varStatus="status">
						    <c:if test="${not loop_flag }">
						        <c:if test="${not empty  item.THUMBNAIL_NM }">
						        <div class="blog-thumb rgi-thumb set-bg blur p-0" data-setbg="${path}/display?filename=${item.THUMBNAIL_NM}">
						        	<div class="blur-container">
						        	</div>
									
								</div>
						            <%-- <div class="rgi-thumb set-bg" data-setbg="${path}/display?filename=${item.THUMBNAIL_NM}">
						            </div> 
						            <p>${item.THUMBNAIL_NM}</p>
						            --%>
						            <c:set var="loop_flag" value="true" />
						        </c:if>
						    </c:if>
						</c:forEach>

						
						<c:forEach var="item" items="${fileList}" >
							<div class="ml-3">
								<input type="hidden" value="${item.FILE_ID}"/>
								<p class="font-weight-bold">${item.FILE_NM}</p>
							</div>
						</c:forEach>
						
					</div>
						
					<div class="comment-warp">
						<h4 class="comment-title">Top Coments</h4>
						<ul class="comment-list">
							<li>
								<div class="comment">
									<div class="comment-avator set-bg" data-setbg="${path}/img/authors/1.jpg"></div>
									<div class="comment-content">
										<h5>James Smith <span>June 21, 2018</span></h5>
										<p>Donec venenatis at eros sit amet aliquam. Donec vel orci efficitur, dictum nisl vitae, scelerisque nibh. Curabitur eget ipsum pulvinar nunc gravida interdum. </p>
										<a href="" class="reply">Reply</a>
									</div>
								</div>
							</li>
							<li>
								<div class="comment">
									<div class="comment-avator set-bg" data-setbg="${path}/img/authors/2.jpg"></div>
									<div class="comment-content">
										<h5>James Smith <span>June 21, 2018</span></h5>
										<p>Donec venenatis at eros sit amet aliquam. Donec vel orci efficitur, dictum nisl vitae, scelerisque nibh. Curabitur eget ipsum pulvinar nunc gravida interdum. </p>
										<a href="" class="reply">Reply</a>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<div class="comment-form-warp" disabled>
						<h4 class="comment-title mb-0">Leave Your Comment</h4>
						<p class="mb-3">If you sponsor more than the minimum amount, you can leave a message to the creator.</p>
						<form class="comment-form" onSubmit="return false;">
							<div class="row">
								<div class="col-md-6">
									<input type="text" placeholder="Name" disabled>
								</div>
								<div class="col-md-6">
									<input type="email" placeholder="Email" disabled>
								</div>
								
								<div class="col-lg-12">
									<input type="text" placeholder="Subject" disabled>
									<textarea placeholder="Message"  disabled></textarea>
									
								</div>
								<div class="col-lg-6">
									<button class="site-btn btn-sm bg-light">Send</button>
								</div>
								<div class="col-lg-6">
									<button class="site-btn btn-sm " >Donation</button>
								</div>
							</div>
						</form>
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
					<!-- widget -->
					<div class="widget-item">
						<h4 class="widget-title">Latest Posts</h4>
						<div class="latest-blog">
						
							<c:forEach var="item" items="${resentList}" >
								<div class="lb-item" onclick="postSend(this)">	
									<input type="hidden" value="${item.POST_ID}"/>				
									<div class="lb-thumb set-bg" data-setbg="${path}/display?filename=${item.THUMBNAIL_NM}"></div>
									<div class="lb-content">
										<div class="lb-date">~ ${item.FUN_END_DT}</div>
										<p>${item.TITLE}</p>
										<a href="#" class="lb-author">${item.NAME}</a>
									</div>
								</div>
							</c:forEach>
							
							
						</div>
					</div>
					<!-- widget -->
					<div class="widget-item">
						<h4 class="widget-title">Top Comments</h4>
						<div class="top-comment">
							<div class="tc-item">
								<div class="tc-thumb set-bg" data-setbg="${path}/img/authors/1.jpg"></div>
								<div class="tc-content">
									<p><a href="#">James Smith</a> <span>on</span> Lorem consec ipsum dolor sit amet, co</p>
									<div class="tc-date">June 21, 2018</div>
								</div>
							</div>
							<div class="tc-item">
								<div class="tc-thumb set-bg" data-setbg="${path}/img/authors/2.jpg"></div>
								<div class="tc-content">
									<p><a href="#">Michael James</a> <span>on</span>Cras sit amet sapien aliquam</p>
									<div class="tc-date">June 21, 2018</div>
								</div>
							</div>
							<div class="tc-item">
								<div class="tc-thumb set-bg" data-setbg="${path}/img/authors/3.jpg"></div>
								<div class="tc-content">
									<p><a href="#">Justin More</a> <span>on</span> Lorem ipsum dolor consecsit amet, co</p>
									<div class="tc-date">June 21, 2018</div>
								</div>
							</div>
						</div>
					</div>
					<!-- widget -->
					<div class="widget-item">
						<div class="feature-item set-bg" data-setbg="${path}/img/features/1.jpg">
							<span class="cata new">new</span>
							<div class="fi-content text-white">
								<h5><a href="#">Suspendisse ut justo tem por, rutrum</a></h5>
								<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. </p>
								<a href="#" class="fi-comment">3 Comments</a>
							</div>
						</div>
					</div>
					<!-- widget -->
					<div class="widget-item">
						<div class="review-item">
							<div class="review-cover set-bg" data-setbg="${path}/img/review/1.jpg">
								<div class="score yellow">9.3</div>
							</div>
							<div class="review-text">
								<h5>Assasin’’s Creed</h5>
								<p>Lorem ipsum dolor sit amet, consectetur adipisc ing ipsum dolor sit ame.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Page section end -->
	
	
	
</t:layout>


<link rel="stylesheet" href='<c:url value="/css/amsify.suggestags.css"/>'>
<link rel="stylesheet" href='<c:url value="/css/jquery.datetimepicker.min.css"/>'>
<script src="${path}/resources/js/jquery.amsify.suggestags.js"></script>
<script src="${path}/resources/js/jquery.datetimepicker.full.min.js"></script>




<script>
function postSend(param){
	console.log($(param).children("input").val())
	location.href="${path}/post/viewText?postId=" + $(param).children("input").val();
}



$(document).ready(function() {

	
	
})



</script>

