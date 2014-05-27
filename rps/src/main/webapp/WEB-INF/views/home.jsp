<!DOCTYPE html>

<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>Rock-Paper-Scissors Game</title>
	<link rel="stylesheet" type="text/css" href="resources/style.css">
</head>

<body>

	<header class="banner">
		<h1>Rock-Paper-Scissors Game</h1>
		<p>Try it now!</p>
	</header>

	<nav>
		<ul>
			<li><a href="${pageContext.request.contextPath}/">New Game</a></li>
		</ul>
	</nav>
	
	<main>
		<section>
			<h2>Make a throw</h2>
			<p>Pick a number from this dropdown list</p>
			<input id="uInput" list="items" name="sign">
			<datalist id="items">
			</datalist>
			<button type="button" onclick="gameResults()">Submit</button>
			<br>
		</section>
		<section>
			<h2 id="resultTitle"></h2>
			<p id="userThrew"></p>
			<p id="programThrew"></p>
			<p id="resultText"></p>
		</section>
	</main>
	
	<aside>
		<h2>Rules</h2>
		<p>Rock beats scissors</p>
		<p>Scissors beats paper</p>
		<p>Paper beats rock</p>
		<p>Identical throws tie</p>
	</aside>
	
	<footer>
		<p>Rps Game</p>
	</footer>
	
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"> </script>
	
	<script>
		
		$(document).ready(function() {
			// Ajax call
			$.get("${pageContext.request.contextPath}/getThrowSet", function(throwSet) {					
				for (var i = 0; i < throwSet.n; i++) {
					$("#items").append("<option value='" + i + "'>" + throwSet.items[i].name + "</option>");
				}
			});
		});
		
		function validateForm() {
			var userInput = $("#uInput").val();
			if (isNaN(userInput) || userInput==null || userInput=="" || userInput==undefined) {
		  		//alert("Invalid input!");
		  		return false;
			} else if (userInput!=0 && userInput!=1 && userInput!=2) {
				//alert("Invalid input!");
		  		return false;
			} else {
				return true;
			}
		}
		
		function gameResults() {
			$("#resultTitle").text("Game Outcome");
			if (validateForm()) {
				// Ajax call
				$.get("${pageContext.request.contextPath}/throw", { sign: $("#uInput").val() } , function(gameResult) {
					$("#userThrew").text("You threw " + gameResult.userItem.name);
					$("#programThrew").text("This program threw " + gameResult.machineItem.name);
					switch (gameResult.resultCode) {
					case 1: document.getElementById("resultText").innerHTML="You won! ";
							break;
					case 2: document.getElementById("resultText").innerHTML="The program won!";
							break;
					case 3: document.getElementById("resultText").innerHTML="You and the program tied!";
							break;
					default: document.getElementById("resultText").innerHTML="Invalid input. Please enter a valid number.";
							break;
					}
				});
			} else {
				$("#userThrew").text("");
				$("#programThrew").text("");
				document.getElementById("resultText").innerHTML="Invalid input. Please enter a valid number.";
			}
		}
		
	</script>
	
</body>

</html>