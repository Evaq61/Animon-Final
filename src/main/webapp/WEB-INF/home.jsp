<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Home</title>
</head>
<style>
</style>
<body class="body_home">

		<!-- SOUND EFFECT -->
	<script>
      function play() {
        var audio = document.getElementById("audio");
        audio.play();
      }
    </script>
    
    <audio id="audio" src="/sounds/crunch.mp3"></audio>
	<c:forEach items="${ thisUser.getPets()}" var="onePet">

		<!------------------------------------- PET BARS/INVENTORY ---------------------------------->
		<div class="hud">
			<div class="status">
				<div class="bars">
					<!-- GOLD -->
					<h2 class="mb-3 text-warning">
						Gold:
						<c:out value="${thisUser.getGold().getGold()}" />
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
					<h2>
						Food Count:
						<c:out value="${thisUser.foodCount(thisUser.getItems())}" />
					</h2>
					<h2>
						Soap Count:
						<c:out value="${thisUser.soapCount(thisUser.getItems())}" />
					</h2>
				</div>
			</div>


			<!-------------------------------- 	NAV BAR -------------------------->
			<div class="body_nav_container">
				<!------------------------------------- PET NAME ---------------------------------->
				<h1 class="pet_name">
					<c:out value="${onePet.getPetName()}" />
				</h1>
				<div class="body_nav">
					<a class="btn btn-primary p-2" href="/play">Play with Me</a> 
					<a class="btn btn-secondary p-2" onclick="play()" href="/feed/${onePet.getId() }">Feed Me</a> 
						<a class="btn btn-light p-2" href="/clean/${onePet.getId() }">Clean Me </a> 
						<a class="btn btn-info p-2" href="/sleep/${onePet.getId() }">Sleep</a>
					<c:choose>
						<c:when test="${contestDiff >= 420}">
							<a class="btn btn-success p-2" href="/contest/${onePet.getId() }">Contest</a>
						</c:when>
						<c:otherwise>
							<button class="btn btn-danger p-2">Contest not available</button>
						</c:otherwise>
					</c:choose>

					<a class="btn btn-warning" href="/store">Store</a> <a
						class="btn btn-danger text-dark" href="/logout">logout</a>


				</div>
			</div>
		</div>
		
		

		

		
		<!------------------------------------------ PET IMG ----------------------------------------->
		<div class="container">
			<div class="row mb-4 c">
				<div class="col-md-2 p-5 gray">
					<img style="background-color: #ffffff00; border: none"
						class="img_home" src="/images/${onePet.getType()}pic.png"
						alt="Failed image: ${onePet.getType()}" />
				</div>
			</div>
		</div>


	</c:forEach>

</body>
</html>