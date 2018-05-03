<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matrix Calculator</title>
<link rel="stylesheet" type="text/css" href="resources/style.css">
<!-- jquery -->

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

<script type="text/javascript" src="CalcMatrix.js"></script>

</head>
<body>
	<div id="page-wrapper">
		<header>
			<div class = "logo">
				<h1>Matrix Calculator</h1>
			</div>
			<div class= "auth">
				
			</div>
			<hr class="hr1">
		</header>

		<aside>
			<div class = "p1"><h1>Operations</h1></div>
			<nav class="menu">
				<ul>
					<c:forEach var="entry" items = "${taskData}">
						<li><a href="#" class = "menuItem" id = "${entry.key}">${entry.value.operationtag}</a></li>
					</c:forEach>

				</ul>
			</nav>	
			<div class = "dark1">
				
			</div>
		</aside>
		
		<section>
			<article class = "operation-def">
				<div class = "operation-header"><h1>Addition and Substarction</h1>

				</div>
				
			</article>
			<article class = "input-data">
				<h1>Input data</h1>
				<div id='userinput'>
					
				</div>
				<input type="button" class="populate" value="Calculate!" id="GR">
			</article> 
			<article class = "output-data">
				<h1>Result</h1>
				<div id='result'>
						
				</div>

			</article>
		</section>
		
		<footer>
			<hr class="hr1">
			<div class = "copyright">
				Copyright @ 2017 All Rights Reserved
			</div>
		</footer>
	</div>
</body>
</html>