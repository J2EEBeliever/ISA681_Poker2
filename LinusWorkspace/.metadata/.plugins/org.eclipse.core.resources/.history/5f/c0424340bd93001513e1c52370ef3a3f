<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Hello World</title>
<script language="javascript" type="text/javascript">
<!-- 
//Browser Support Code
function ajaxFunction(){
	
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

//-->
</script>

</head>
<body onload="setTimeout(ajaxFunction, 5000)">
   Hello World, <s:property value="name"/>
   <DIV id="ajaxDiv">
   </DIV>
</body>
</html>