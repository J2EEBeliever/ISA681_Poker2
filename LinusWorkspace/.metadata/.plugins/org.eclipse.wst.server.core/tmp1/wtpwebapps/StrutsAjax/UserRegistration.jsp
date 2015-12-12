<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration to Five Card Stud Poker</title>
</head>



<body>
    <table>
 <tr>
   <td colspan="2">
   <s:actionerror />
   </td>
  </tr>
    </table>
User Registration to Five Card Stud Poker<BR>
 <s:form action="RegisterForFiveCardStudPoker.html" method="POST">

   <s:textfield name="username" label="Login name"/>
   <s:password name="password" label="Password"/>
   <s:password name="password2" label="Password (twice)"/>
  <s:submit value="Register" align="center"/>
</s:form>

</body>
</html>