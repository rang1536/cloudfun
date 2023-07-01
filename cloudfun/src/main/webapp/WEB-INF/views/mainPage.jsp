<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<t:layout>
   
<!-- Hero section -->
	<section class="hero-section">
		<div class="hero-slider owl-carousel">
			<!-- <div class="hs-item " style="background : black;"> -->
			<div class="hs-item set-bg set-bg-main" data-setbg="${path}/img/new/main-banner.png" > <!--style="background : black;"  -->
			<!-- <div class="hs-item set-bg" data-setbg="img/slider-1.jpg" style="background-image: url(&quot;img/slider-1.jpg&quot;);"> -->
				<div class="hs-text">
					<div class="container">
						<h2>The Best <span>Games</span> Out There</h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada <br> lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. <br>Suspendisse cursus faucibus finibus.</p>
						<a href="#" class="site-btn">Read More</a>
					</div>
				</div>
			</div>
			
		</div>
			<%-- <div class="hs-item set-bg" data-setbg="${path}/img/slider-2.jpg"> --%>
			<!-- <div class="hs-item set-bg" style="background : black;">
				<div class="hs-text">
					<div class="container">
						<h2>The Best <span>Games</span> Out There</h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada <br> lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. <br>Suspendisse cursus faucibus finibus.</p>
						<a href="#" class="site-btn">Read More</a>
					</div>
				</div>
			</div> -->
		</div>
	</section>
	<!-- Hero section end -->


	<!-- Latest news section -->
	<!-- <div class="latest-news-section">
		<div class="ln-title">Latest News</div>
		<div class="news-ticker">
			<div class="news-ticker-contant">
				<div class="nt-item"><span class="new">new</span>Lorem ipsum dolor sit amet, consectetur adipiscing elit. </div>
				<div class="nt-item"><span class="strategy">strategy</span>Isum dolor sit amet, consectetur adipiscing elit. </div>
				<div class="nt-item"><span class="racing">racing</span>Isum dolor sit amet, consectetur adipiscing elit. </div>
			</div>
		</div>
	</div> -->
	<!-- Latest news section end -->


	<!-- Feature section -->
	<section class="feature-section spad " >
		<div class="container ">
						
						
			<div class="row">
				<div class="col-lg-5 align-self-center">
					<h2>Get sponsorship even <span class="main-span">before it's finished</span></h2>
					<p>You can proceed with the sponsorship from the beginning and communicate with the sponsor according to the progress.</p>
				
					
				</div>
				<div class="col-lg-2"></div>
				<div class="col-lg-5">
					<img class="set-bg-right" src="${path}/img/new/cloud.png">
				</div> 
			
			</div>
			
			
			
		</div>
	</section>
	<!-- Feature section end -->


	<!-- Recent game section  -->
	<section class="recent-game-section spad set-bg" data-setbg="">
		<div class="container">
			<div class="row">
				<div class="col-lg-5 ">
					<img class="set-bg-right" src="${path}/img/new/ranbow.png">
					
					
				</div>
				<div class="col-lg-2"></div>
				<div class="col-lg-5 align-self-center">
					
					<h2>Pay at least<br/><span class="main-span"> 3% </span>commission.</h2>
				</div> 
			
			</div>
			
			
			
			<!-- <div class="section-title">
				<div class="cata new">new</div>
				<h2>Recent Games</h2>
			</div> -->
			<%-- <div class="row">
				<div class="col-lg-4 col-md-6">
					<div class="recent-game-item">
						<div class="rgi-thumb set-bg" data-setbg="${path}/img/new/menu.png">
							<div class="cata new">new</div>
						</div>
						<div class="rgi-content">
							<h5>Suspendisse ut justo tem por, rutrum</h5>
							<p>Lorem ipsum dolor sit amet, consectetur adipisc ing ipsum dolor sit amet, consectetur elit. </p>
							<a href="#" class="comment">3 Comments</a>
							<div class="rgi-extra">
								<div class="rgi-star"><img src="${path}/img/icons/star.png" alt=""></div>
								<div class="rgi-heart"><img src="${path}/img/icons/heart.png" alt=""></div>
							</div>
						</div>
					</div>	
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="recent-game-item">
						<div class="rgi-thumb set-bg" data-setbg="${path}/img/new/menu.png">
							<div class="cata racing">racing</div>
						</div>
						<div class="rgi-content">
							<h5>Susce pulvinar metus nulla, vel  facilisis sem </h5>
							<p>Lorem ipsum dolor sit amet, consectetur adipisc ing ipsum dolor sit amet, consectetur elit. </p>
							<a href="#" class="comment">3 Comments</a>
							<div class="rgi-extra">
								<div class="rgi-star"><img src="${path}/img/icons/star.png" alt=""></div>
								<div class="rgi-heart"><img src="${path}/img/icons/heart.png" alt=""></div>
							</div>
						</div>
					</div>	
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="recent-game-item">
						<div class="rgi-thumb set-bg" data-setbg="${path}/img/new/menu.png">
							<div class="cata adventure">Adventure</div>
						</div>
						<div class="rgi-content">
							<h5>Suspendisse ut justo tem por, rutrum</h5>
							<p>Lorem ipsum dolor sit amet, consectetur adipisc ing ipsum dolor sit amet, consectetur elit. </p>
							<a href="#" class="comment">3 Comments</a>
							<div class="rgi-extra">
								<div class="rgi-star"><img src="${path}/img/icons/star.png" alt=""></div>
								<div class="rgi-heart"><img src="${path}/img/icons/heart.png" alt=""></div>
							</div>
						</div>
					</div>	
				</div>
			</div> --%>
		</div>
	</section>
	<!-- Recent game section end -->


	<!-- Tournaments section -->
	<%-- <section class="tournaments-section spad">
		<div class="container">
			<div class="tournament-title">Tournaments</div>
			<div class="row">
				<div class="col-md-6">
					<div class="tournament-item mb-4 mb-lg-0">
						<div class="ti-notic">Premium Tournament</div>
						<div class="ti-content">
							<div class="ti-thumb set-bg" data-setbg="${path}/img/new/menu.png"></div>
							<div class="ti-text">
								<h4>World Of WarCraft</h4>
								<ul>
									<li><span>Tournament Beggins:</span> June 20, 2018</li>
									<li><span>Tounament Ends:</span> July 01, 2018</li>
									<li><span>Participants:</span> 10 teams</li>
									<li><span>Tournament Author:</span> Admin</li>
								</ul>
								<p><span>Prizes:</span> 1st place $2000, 2nd place: $1000, 3rd place: $500</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="tournament-item">
						<div class="ti-notic">Premium Tournament</div>
						<div class="ti-content">
							<div class="ti-thumb set-bg" data-setbg="${path}/img/new/menu.png"></div>
							<div class="ti-text">
								<h4>DOOM</h4>
								<ul>
									<li><span>Tournament Beggins:</span> June 20, 2018</li>
									<li><span>Tounament Ends:</span> July 01, 2018</li>
									<li><span>Participants:</span> 10 teams</li>
									<li><span>Tournament Author:</span> Admin</li>
								</ul>
								<p><span>Prizes:</span> 1st place $2000, 2nd place: $1000, 3rd place: $500</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section> --%>
	<!-- Tournaments section bg -->


	<!-- Review section -->
<!-- 	<section class="review-section spad set-bg" data-setbg="">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 align-self-center">
					<h2>Start now.</span></h2>
					<div class="user-panel mt-3" onclick="loginWithGoogle();">
						<a href="#">Login</a>  /  <a href="#">Register</a>
					</div>
					
				</div>
				 
			
			</div>
		</div>
	</section> -->
	<!-- Review section end -->
	
	
	
</t:layout>
