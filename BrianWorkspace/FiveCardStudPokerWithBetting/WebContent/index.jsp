<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Untitled Document</title>
<link href="CSS/oneColFixCtrHdr.css" rel="stylesheet" type="text/css" />


</head>

<body>

<div class="container">
  <div class="header"><a href="#"><img src="" alt="Insert Logo Here" name="Insert_logo" width="180" height="90" id="Insert_logo" style="background-color: #C6D580; display:block;" /></a> 
    <!-- end .header --></div>
    <div class="content" align="center">
        <s:form action="doLogin" method="POST">


  <tr>
   <td colspan="2">
   <s:actionerror />
   </td>
  </tr>

   <s:textfield name="username" label="Login name"/>
   <s:password name="Password" label="Password"/>
  
<s:submit value="Login" align="center"/>

</s:form>
 
   <!-- end .content --></div>
  <div class="footer">
    <p>&nbsp;</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>
</body>
</html>