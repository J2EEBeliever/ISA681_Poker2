<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logon to Five Card Stud Poker</title>
</head>



<body>
<form action="${pageContext.request.contextPath}/logonToFiveCardStudPoker.html"  name="logonFiveCardStudPokerForm">
<BR><BR>User Name: <input type="text" name="userName" value="">
<BR><BR>Password: <input type="password" name="password" value="">
<BR><BR><input type="submit" name="submit">
</form>
</body>
</html>