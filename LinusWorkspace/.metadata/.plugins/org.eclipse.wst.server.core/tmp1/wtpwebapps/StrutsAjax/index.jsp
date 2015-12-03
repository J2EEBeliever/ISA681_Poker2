<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Ajax in Struts</title>

<script language="javascript" type="text/javascript">

/*
 * Author: Linus Freeman
 * 
 */


//Browser Support Code
function ajaxFunctionbk(){
	
	alert('executing ajaxFunction()');
	var ajaxRequest;  // The variable that makes Ajax possible!
	
	try{
		// Opera 8.0+, Firefox, Safari
		ajaxRequest = new XMLHttpRequest();
		alert('executing ajaxFunction() #10');

	} catch (e){
		// Internet Explorer Browsers
		
		alert('executing ajaxFunction() #20');
		
		try{
			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try{
				ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e){
				// Something went wrong
				alert("Your browser broke!");
				return false;
			}
		}

	}
	
	alert('executing ajaxFunction() #30');

	
	// Create a function that will receive data sent from the server
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4){
			var ajaxDisplay = document.getElementById('ajaxDiv');
			ajaxDisplay.innerHTML = ajaxRequest.responseText;
		}
	}
//	var age = document.getElementById('age').value;
//	var wpm = document.getElementById('wpm').value;
//	var sex = document.getElementById('sex').value;
//	var queryString = "?age=" + age + "&wpm=" + wpm + "&sex=" + sex;

	alert('executing ajaxFunction() #40');


var queryString = '?playerContent=yes';
	ajaxRequest.open("GET", 'ajaxLogicAction' + queryString, true);
	ajaxRequest.send(null);
	
	alert('executing ajaxFunction() #50');

}

</script>


<script src="js/jquery-1.6.2.js" type="text/javascript"></script>
<script type="text/javascript">

function joinGameNow(gamePlayer1UserName) {
	
	
	alert('#30 exuting javaScript Function joinGameNow(gamePlayer1UserName)');

	
	$.ajax({
		type : "POST",
		url : '${pageContext.request.contextPath}/joinGameNow.html?gamePlayer1UserName=' + gamePlayer1UserName ,
		success : function(response) {
			$('.result').html(response);
		}
	});
	
}

function playerContent() {
	
	alert('#10');
	
	
	$.ajax({
		type : "POST",
		url : '${pageContext.request.contextPath}/getPlayerTurn.html',
		success : function(response) {
			$('.result').html(response);
		}
	});
}


$(document).ready(function() {

	
	/*)
		$('#joinGameNow1').click(function() {
			$.ajax({
				type : "POST",
				url : '${pageContext.request.contextPath}/joinGameNow1.html',
				success : function(response) {
					$('.result').html(response);
				}
			});
		});
	*/

		$('#joinGameNow1').click(function() {
			$.ajax({
				type : "POST",
				url : '${pageContext.request.contextPath}/joinGameNow1.html',
				success : function(response) {
					$('.result').html(response);
				}
			});
		});
		
		
	
		$('#buttonJoinGame2').click(function() {
			$.ajax({
				type : "POST",
				url : '${pageContext.request.contextPath}/joinGame2.html',
				success : function(response) {
					$('.result').html(response);
				}
			});
		});

		
		$('#buttonNewGame').click(function() {
			$.ajax({
				type : "POST",
				url : '${pageContext.request.contextPath}/newGame.html',
				success : function(response) {
					$('.result').html(response);
				}
			});
		});

		
/*		
		$('#buttonJoinGame').click(function() {
			$.ajax({
				type : "POST",
				url : '${pageContext.request.contextPath}/joinGame.html',
				success : function(response) {
					$('.result').html(response);
				}
			});
		});
*/
		
		$('#buttonHello').click(function() {
			$.ajax({
				type : "POST",
				url : '${pageContext.request.contextPath}/hello.html',
				success : function(response) {
					$('.result').html(response);
				}
			});
		});

		$('#buttonSum').click(function() {
			$.ajax({
				type : "GET",
				data : {
					a : 1,
					b : 2
				},
				url : '${pageContext.request.contextPath}/sum.html',
				success : function(response) {
					$('.result').html(response);
				}
			});
		});

		$('#buttonFind').click(function() {
			$.ajax({
				type : "GET",
				headers : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				url : '${pageContext.request.contextPath}/find.html',
				success : function(response) {
					var result = 'Id: ' + response.sp.id + '<br>Name: ' + response.sp.name + '<br>Price: ' + response.sp.price;
					$('.result').html(result);
				}
			});
		});

		//buttonJoinGame
		$('#buttonJoinGame').click(function() {
			$.ajax({
				type : "GET",
				headers : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				url : '${pageContext.request.contextPath}/joinGame.html',
				success : function(response) {
					var n = response.lu.length;
					var result = '';
					for(var i=0; i<n; i++)
						//result += '<br><a href = "${pageContext.request.contextPath}/joinGameNow.html?gameUserName="' + response.lu[i].userName + '" value = "' + response.lu[i].userName + '">';
						result += '<br>' + response.lu[i].userName + '<br>';
					$('.result').html(result);
				}
			});
		});
		
		
		
		$('#buttonFindAll').click(function() {
			$.ajax({
				type : "GET",
				headers : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				url : '${pageContext.request.contextPath}/findall.html',
				success : function(response) {
					var n = response.lp.length;
					var result = '';
					for(var i=0; i<n; i++)
						result += '<br>Id: ' + response.lp[i].id + ' - Name: ' + response.lp[i].name + ' - Price: ' + response.lp[i].price;
					$('.result').html(result);
				}
			});
		});

	});
</script>
</head>


<body onload_bk "setTimeout(playerContent,5000)">

	<input type="button" value="Start a new Game" id="buttonNewGame">
	<!-- input type="button" value="Join an existing Game" id="buttonJoinGame" -->
	<input type="button" value="Join an existing Game" id="buttonJoinGame2">


	<input type="button" value="Hello" id="buttonHello">
	<input type="button" value="Sum" id="buttonSum">
	<input type="button" value="Find" id="buttonFind">
	<input type="button" value="Find All" id="buttonFindAll">
	<br>
	<div class="result"></div>

</body>
</html>