<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contest</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<style>

</style>
<body class="body_contest">

<!-- 	NAV BAR -->
	<div class="nav_container">
		<div class="title_nav">
			<h1 class="winner">
				<!-- TITLE -->
				Winner
				<c:choose>
					<c:when test="${medal eq 1 }">First Place!!!</c:when>
					<c:when test="${medal eq 2 }">Second Place!!</c:when>
					<c:otherwise>Third Place!</c:otherwise>
				</c:choose>
			</h1>
			<!-- HOME -->
			<a href="/home" class="btn btn-success m-5">Job Well Done</a>
		</div>
	</div>
	

	
	<!-- MEDAL/PET IMG -->
	<div class="contest_imgs">
		<div >
			<c:choose>
				<c:when test="${medal eq 1}">
					<img class="medal" src="/images/contest/goldmedal.png"
						style="background-color: #ffffff00; border: none"
						alt="gold meadal img" />
				</c:when>
				<c:when test="${medal eq 2 }">
					<img class="medal" src="/images/contest/silvermedal.png"
						style="background-color: #ffffff00; border: none"
						alt="silver medal img" />
				</c:when>
				<c:otherwise>
					<img class="medal" src="/images/contest/bronzemedal.png"
						style="background-color: #ffffff00; border: none"
						alt="bronze meadal img" />
				</c:otherwise>
			</c:choose>
		</div>
		<div class="container">
			<div class="row mb-4 c">
				<div class="col-md-2 p-5 gray">
					<img style="background-color: #ffffff00; border: none"
						class="img_contest" src="/images/${myPet.get(0).getType()}pic.png"
						alt="Failed image: ${myPet.get(0).getType()}" 
						/>
				</div>
			</div>
		</div>
	</div>
	

</body>
</html>