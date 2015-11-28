<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Ajax in Struts</title>
<script src="js/jquery-1.6.2.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {

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

<body>

	<input type="button" value="Hello" id="buttonHello">
	<input type="button" value="Sum" id="buttonSum">
	<input type="button" value="Find" id="buttonFind">
	<input type="button" value="Find All" id="buttonFindAll">
	<br>
	<div class="result"></div>

</body>
</html>