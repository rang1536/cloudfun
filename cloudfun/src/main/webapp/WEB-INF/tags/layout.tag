<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="script" fragment="true" required="false" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

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
</head>
<body>

    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <jsp:include page="/WEB-INF/views/common/sidebar.jsp"/>
    <jsp:doBody/>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    
    
    <script src="${path}/resources/js/common.js"></script>
    
    <script src="${path}/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${path}/resources/js/jquery.marquee.min.js"></script>
    <script src="${path}/resources/js/owl.carousel.min.js"></script>
    
    <jsp:invoke fragment="script"/>
</body>
</html>