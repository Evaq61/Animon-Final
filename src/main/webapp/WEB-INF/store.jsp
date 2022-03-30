<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<style>

</style>
<body class="body_store">

	<!-- SOUND EFFECT -->
	<script>
      function play() {
        var audio = document.getElementById("audio");
        audio.play();
      }
    </script>
    
    <audio id="audio" src="/sounds/chaching.mp3"></audio>

	
	

	
	<div class = "items_container">
		<a class=" button_home btn btn-warning" href="/home">Go back home</a>
		<div class="items">
			<!-- GOLD -->
			<h3 class="store_gold m-3 text-warning">Gold: <c:out value="${thisUser.getGold().getGold()}"/></h3>
			<c:choose>
				<c:when test="${thisUser.getGold().getGold() >= 50 }">
					<a href="/buypetfood" onclick="play()" class="button_item btn"><button class="button_test">Pet Food - 50 Gold</button></a>
					<p>Food Count: <c:out value="${thisUser.foodCount(thisUser.getItems())}" /></p>
				</c:when>
				<c:otherwise>
					<button type="button" class="button_item btn" disabled>Pet Food (Not enough gold)</button>
					<p>Food Count: <c:out value="${thisUser.foodCount(thisUser.getItems())}" /></p>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${thisUser.getGold().getGold() >= 100 }">
					<a href="/buyrope" onclick="play()" class="button_item btn"><button class="button_test">Toy Rope - 100 Gold</button></a>
					<p>Rope count: <c:out value="${thisUser.ropeCount(thisUser.getItems())}" /></p>
					
				</c:when>	
				<c:otherwise>
					<button type="button" class="button_item btn" disabled>Toy Rope (Not enough gold)</button>
					<p>Rope count: <c:out value="${thisUser.ropeCount(thisUser.getItems())}" /></p>
					
				</c:otherwise>
			</c:choose>
	
			<c:choose>
				<c:when test="${thisUser.getGold().getGold() >= 300 }">
					<a href="/buyball" onclick="play()" class="button_item btn"><button class="button_test">Toy Ball 300 Gold</button></a>
					<p>Ball count: <c:out value="${thisUser.ballCount(thisUser.getItems())}" /></p>
				</c:when>
				<c:otherwise>
					<button type="button" class="button_item btn" disabled> Toy Ball (Not enough gold)</button>
					<p>Ball count: <c:out value="${thisUser.ballCount(thisUser.getItems())}" /></p>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${thisUser.getGold().getGold() >= 400 }">
					<a href="/buysoap" onclick="play()" class="button_item btn"><button class="button_test" >Pet Soap - 400 Gold</button></a>
					<p>Soap Count: <c:out value="${thisUser.soapCount(thisUser.getItems())}" /></p>
				</c:when>
				<c:otherwise>	
					<button type="button" class="button_item btn" disabled>Pet Soap (Not enough gold)</button>
					<p>Soap Count: <c:out value="${thisUser.soapCount(thisUser.getItems())}" />	</p>
				</c:otherwise>
			</c:choose>
		</div>	
		
	</div>

</body>
</html>