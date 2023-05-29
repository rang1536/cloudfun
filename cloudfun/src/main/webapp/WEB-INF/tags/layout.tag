<%@tag import="org.apache.poi.util.SystemOutLogger"%>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="script" fragment="true" required="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" charset="utf-8">
	sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
</script>
<%
	session = request.getSession();

	String paramLocal = "en";
	
	System.out.println(request.getLocale().getCountry());
	if(session.getAttribute("localCountry")==null){
		if(request.getLocale().getCountry().equals("KR")) {
			session.setAttribute("localCountry", "ko"); // KR
		}
	}
	
	paramLocal = (String)session.getAttribute("localCountry");
	System.out.println(paramLocal);
	
%>
<fmt:setLocale value="${paramLocal}"/>
<%-- <fmt:requestEncoding value="UTF-8"/> --%>
<fmt:bundle basename="message.local">


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    
    <title>CloudFun(가제)</title>
    
    	<!-- Favicon -->   
	<link href="/img/favicon.ico" rel="shortcut icon"/>
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,400i,500,500i,700,700i" rel="stylesheet">
    <!-- http://localhost:8080/resources/css/animate.css -->
  
    <link rel="stylesheet" href='<c:url value="/css/bootstrap.min.css"/>'>
    <link rel="stylesheet" href='<c:url value="/css/font-awesome.min.css"/>'>
    <link rel="stylesheet" href='<c:url value="/css/owl.carousel.css"/>'>
    <link rel="stylesheet" href='<c:url value="/css/style.css"/>'>
    <link rel="stylesheet" href='<c:url value="/css/animate.css"/>'>
    <link rel="stylesheet" href='<c:url value="/css/mermaid.min.css"/>'>
</head>
<body>
	

    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <jsp:include page="/WEB-INF/views/common/sidebar.jsp"/>
    <jsp:doBody/>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    
    
    <script>
    
    	var preUrl = "${path}";
    
    </script>
    
    <script src="${path}/resources/js/common.js"></script>
    
    <script src="${path}/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${path}/resources/js/jquery.marquee.min.js"></script>
    <script src="${path}/resources/js/owl.carousel.min.js"></script>
    
	<script src="${path}/resources/js/main.js"></script>
	<script src="${path}/resources/js/bootstrap.min.js"></script>
	
	<script src="${path}/resources/js/gridjs.production.min.js"></script>
	

    
    
    <jsp:invoke fragment="script"/>
</body>
</html>


</fmt:bundle>