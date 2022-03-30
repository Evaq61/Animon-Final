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
<title>Play with me</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<style>

</style>
<body class="body_play">
	
	<c:forEach items="${ thisUser.getPets()}" var="onePet">
	
	
		<!------------------------------------- PET BARS/INVENTORY ---------------------------------->
	<div class="hud">
		<div class="status">
			<div class="bars">
				<!-- GOLD -->	
				<h2 class="mb-3 text-warning">
					Gold: <c:out value="${thisUser.getGold().getGold()}" />
				</h2>
				<!-- ENERGY -->
				<h2>Energy: ${onePet.getEnergy()}</h2>
				<progress class="progress-bar" value="${onePet.getEnergy()}"
					max="100"></progress>
				<!-- HAPPINESS -->
				<h2>Happiness: ${onePet.getHappiness()}</h2>
				<progress class="progress-bar" value="${onePet.getHappiness()}"
					max="100"></progress>
				<!-- HUNGER -->
				<h2>Hunger: ${onePet.getHealth()}</h2>
				<progress class="progress-bar" value="${onePet.getHealth()}"
					max="100"></progress>
				<!-- CLEAN -->
				<h2>Clean: ${onePet.getCleanliness()}</h2>
				<progress class="progress-bar" value="${onePet.getCleanliness()}"
					max="10"></progress>
			</div>
			
				<!---------------------------------------- INVENTORY ------------------------------------------->
			<div class="inventory">
				<h2>Ball count: <c:out value="${thisUser.ballCount(thisUser.getItems())}" /> </h2>
				<h2>Rope count: <c:out value="${thisUser.ropeCount(thisUser.getItems())}" /></h2>
			</div>
		</div>

	
			<!-------------------------------- 	NAV BAR -------------------------->
		<div class="body_nav_container">
		<!------------------------------------- PET NAME ---------------------------------->		
		<h1 class="pet_name"><c:out value="${onePet.getPetName()}" /></h1>
			<div class="body_nav_play" >
				<a href="/home" class="btn btn-warning">Home</a>

				<a class="btn btn-primary" href="/rope/${onePet.getId() }">Play Tug of War</a>
				
				<a class="btn btn-primary" href="/ball/${onePet.getId() }">Play ball</a>
				
				<a class="btn btn-primary" href="/tag/${onePet.getId() }">Play Tag</a>
				
			</div>
		</div>
	</div>
		
		<!-- PET IMG -->
		<div class="container">
			<div class="row mb-4 c">
				<div class="col-md-2 p-5 gray">
					<img style="background-color: #ffffff00; border: none"
						class="img_play" src="/images/${onePet.getType()}pic.png"
						alt="Failed image: ${onePet.getType()}" />
				</div>
			</div>
		</div>

	</c:forEach>

	

</body>
</html>