/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.27
 * Generated at: 2015-12-04 20:01:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/C:/ISA681_Poker2/LinusWorkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/StrutsAjax/WEB-INF/lib/struts2-core-2.3.1.2.jar!/META-INF/struts-tags.tld", Long.valueOf(1327118112000L));
    _jspx_dependants.put("/WEB-INF/lib/struts2-core-2.3.1.2.jar", Long.valueOf(1448677189417L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Ajax in Struts</title>\n");
      out.write("\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\n");
      out.write("\n");
      out.write("/*\n");
      out.write(" * Author: Linus Freeman\n");
      out.write(" * \n");
      out.write(" */\n");
      out.write("\n");
      out.write("\n");
      out.write("//Browser Support Code\n");
      out.write("function ajaxFunctionbk(){\n");
      out.write("\t\n");
      out.write("\talert('executing ajaxFunction()');\n");
      out.write("\tvar ajaxRequest;  // The variable that makes Ajax possible!\n");
      out.write("\t\n");
      out.write("\ttry{\n");
      out.write("\t\t// Opera 8.0+, Firefox, Safari\n");
      out.write("\t\tajaxRequest = new XMLHttpRequest();\n");
      out.write("\t\talert('executing ajaxFunction() #10');\n");
      out.write("\n");
      out.write("\t} catch (e){\n");
      out.write("\t\t// Internet Explorer Browsers\n");
      out.write("\t\t\n");
      out.write("\t\talert('executing ajaxFunction() #20');\n");
      out.write("\t\t\n");
      out.write("\t\ttry{\n");
      out.write("\t\t\tajaxRequest = new ActiveXObject(\"Msxml2.XMLHTTP\");\n");
      out.write("\t\t} catch (e) {\n");
      out.write("\t\t\ttry{\n");
      out.write("\t\t\t\tajaxRequest = new ActiveXObject(\"Microsoft.XMLHTTP\");\n");
      out.write("\t\t\t} catch (e){\n");
      out.write("\t\t\t\t// Something went wrong\n");
      out.write("\t\t\t\talert(\"Your browser broke!\");\n");
      out.write("\t\t\t\treturn false;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t}\n");
      out.write("\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\talert('executing ajaxFunction() #30');\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t// Create a function that will receive data sent from the server\n");
      out.write("\tajaxRequest.onreadystatechange = function(){\n");
      out.write("\t\tif(ajaxRequest.readyState == 4){\n");
      out.write("\t\t\tvar ajaxDisplay = document.getElementById('ajaxDiv');\n");
      out.write("\t\t\tajaxDisplay.innerHTML = ajaxRequest.responseText;\n");
      out.write("\t\t}\n");
      out.write("\t}\n");
      out.write("//\tvar age = document.getElementById('age').value;\n");
      out.write("//\tvar wpm = document.getElementById('wpm').value;\n");
      out.write("//\tvar sex = document.getElementById('sex').value;\n");
      out.write("//\tvar queryString = \"?age=\" + age + \"&wpm=\" + wpm + \"&sex=\" + sex;\n");
      out.write("\n");
      out.write("\talert('executing ajaxFunction() #40');\n");
      out.write("\n");
      out.write("\n");
      out.write("var queryString = '?playerContent=yes';\n");
      out.write("\tajaxRequest.open(\"GET\", 'ajaxLogicAction' + queryString, true);\n");
      out.write("\tajaxRequest.send(null);\n");
      out.write("\t\n");
      out.write("\talert('executing ajaxFunction() #50');\n");
      out.write("\n");
      out.write("}\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"js/jquery-1.6.2.js\" type=\"text/javascript\"></script>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("function joinGameNow(gamePlayer1UserName) {\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\talert('#30 exuting javaScript Function joinGameNow(gamePlayer1UserName)');\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t$.ajax({\n");
      out.write("\t\ttype : \"POST\",\n");
      out.write("\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/joinGameNow.html?gamePlayer1UserName=' + gamePlayer1UserName ,\n");
      out.write("\t\tsuccess : function(response) {\n");
      out.write("\t\t\t$('.result').html(response);\n");
      out.write("\t\t}\n");
      out.write("\t});\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\n");
      out.write("function playerContent() {\n");
      out.write("\t\n");
      out.write("\talert('#10');\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t$.ajax({\n");
      out.write("\t\ttype : \"POST\",\n");
      out.write("\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/getPlayerTurn.html',\n");
      out.write("\t\tsuccess : function(response) {\n");
      out.write("\t\t\t$('.result').html(response);\n");
      out.write("\t\t}\n");
      out.write("\t});\n");
      out.write("}\n");
      out.write("\n");
      out.write("function raise() {\n");
      out.write("\n");
      out.write("\talert('In method raise()');\n");
      out.write("\t\n");
      out.write("\tvar betAmount_ = document.getElementById('betAmount');\n");
      out.write("\t\n");
      out.write("\talert('betAmount = ' + betAmount_.value);\n");
      out.write("\t\n");
      out.write("\tvar betAmountFloat_ = parseFloat(betAmount.value);\n");
      out.write("\t\n");
      out.write("\tif(betAmountFloat_ <= 0 || betAmountFloat_ > 1.00  ) {\n");
      out.write("\t\t\n");
      out.write("\t\talert('Bet Amount muset be greater than 0 and less than equal to 1.00 dollar.');\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t\treturn;\n");
      out.write("\t\t\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t$.ajax({\n");
      out.write("\t\ttype : \"POST\",\n");
      out.write("\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/raise.html?betAmount=' + betAmountFloat_,\n");
      out.write("\t\tsuccess : function(response) {\n");
      out.write("\t\t\t$('.result').html(response);\n");
      out.write("\t\t}\n");
      out.write("\t});\n");
      out.write("\t\n");
      out.write("\tsetTimeout(waitForTurnOrGameToStart,5000)\n");
      out.write("\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\n");
      out.write("function waitForTurnOrGameToStart() {\n");
      out.write("\n");
      out.write("\talert('In method waitForTurnOrGameToStart()');\n");
      out.write("\t\n");
      out.write("\talert('waitForTurnOrGameToStart JavaScript function');\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t$.ajax({\n");
      out.write("\t\ttype : \"POST\",\n");
      out.write("\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/waitForTurnOrGameToStart.html',\n");
      out.write("\t\tsuccess : function(response) {\n");
      out.write("\t\t\t$('.result').html(response);\n");
      out.write("\t\t}\n");
      out.write("\t});\n");
      out.write("\t\n");
      out.write("\tsetTimeout(waitForTurnOrGameToStart,5000)\n");
      out.write("\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write("$(document).ready(function() {\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t/*)\n");
      out.write("\t\t$('#joinGameNow1').click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t\ttype : \"POST\",\n");
      out.write("\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/joinGameNow1.html',\n");
      out.write("\t\t\t\tsuccess : function(response) {\n");
      out.write("\t\t\t\t\t$('.result').html(response);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\t*/\n");
      out.write("\n");
      out.write("\t\t$('#joinGameNow1').click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t\ttype : \"POST\",\n");
      out.write("\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/joinGameNow1.html',\n");
      out.write("\t\t\t\tsuccess : function(response) {\n");
      out.write("\t\t\t\t\t$('.result').html(response);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t\n");
      out.write("\t\t$('#buttonJoinGame2').click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t\ttype : \"POST\",\n");
      out.write("\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/joinGame2.html',\n");
      out.write("\t\t\t\tsuccess : function(response) {\n");
      out.write("\t\t\t\t\t$('.result').html(response);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\t\n");
      out.write("\t\t$('#buttonNewGame').click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t\ttype : \"POST\",\n");
      out.write("\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/newGame.html',\n");
      out.write("\t\t\t\tsuccess : function(response) {\n");
      out.write("\t\t\t\t\t$('.result').html(response);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\t\n");
      out.write("/*\t\t\n");
      out.write("\t\t$('#buttonJoinGame').click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t\ttype : \"POST\",\n");
      out.write("\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/joinGame.html',\n");
      out.write("\t\t\t\tsuccess : function(response) {\n");
      out.write("\t\t\t\t\t$('.result').html(response);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("*/\n");
      out.write("\t\t\n");
      out.write("\t\t$('#buttonHello').click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t\ttype : \"POST\",\n");
      out.write("\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/hello.html',\n");
      out.write("\t\t\t\tsuccess : function(response) {\n");
      out.write("\t\t\t\t\t$('.result').html(response);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\t$('#buttonSum').click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t\ttype : \"GET\",\n");
      out.write("\t\t\t\tdata : {\n");
      out.write("\t\t\t\t\ta : 1,\n");
      out.write("\t\t\t\t\tb : 2\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/sum.html',\n");
      out.write("\t\t\t\tsuccess : function(response) {\n");
      out.write("\t\t\t\t\t$('.result').html(response);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\t$('#buttonFind').click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t\ttype : \"GET\",\n");
      out.write("\t\t\t\theaders : {\n");
      out.write("\t\t\t\t\tAccept : \"application/json; charset=utf-8\",\n");
      out.write("\t\t\t\t\t\"Content-Type\" : \"application/json; charset=utf-8\"\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/find.html',\n");
      out.write("\t\t\t\tsuccess : function(response) {\n");
      out.write("\t\t\t\t\tvar result = 'Id: ' + response.sp.id + '<br>Name: ' + response.sp.name + '<br>Price: ' + response.sp.price;\n");
      out.write("\t\t\t\t\t$('.result').html(result);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\t//buttonJoinGame\n");
      out.write("\t\t$('#buttonJoinGame').click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t\ttype : \"GET\",\n");
      out.write("\t\t\t\theaders : {\n");
      out.write("\t\t\t\t\tAccept : \"application/json; charset=utf-8\",\n");
      out.write("\t\t\t\t\t\"Content-Type\" : \"application/json; charset=utf-8\"\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/joinGame.html',\n");
      out.write("\t\t\t\tsuccess : function(response) {\n");
      out.write("\t\t\t\t\tvar n = response.lu.length;\n");
      out.write("\t\t\t\t\tvar result = '';\n");
      out.write("\t\t\t\t\tfor(var i=0; i<n; i++)\n");
      out.write("\t\t\t\t\t\t//result += '<br><a href = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/joinGameNow.html?gameUserName=\"' + response.lu[i].userName + '\" value = \"' + response.lu[i].userName + '\">';\n");
      out.write("\t\t\t\t\t\tresult += '<br>' + response.lu[i].userName + '<br>';\n");
      out.write("\t\t\t\t\t$('.result').html(result);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t\t$('#buttonFindAll').click(function() {\n");
      out.write("\t\t\t$.ajax({\n");
      out.write("\t\t\t\ttype : \"GET\",\n");
      out.write("\t\t\t\theaders : {\n");
      out.write("\t\t\t\t\tAccept : \"application/json; charset=utf-8\",\n");
      out.write("\t\t\t\t\t\"Content-Type\" : \"application/json; charset=utf-8\"\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/findall.html',\n");
      out.write("\t\t\t\tsuccess : function(response) {\n");
      out.write("\t\t\t\t\tvar n = response.lp.length;\n");
      out.write("\t\t\t\t\tvar result = '';\n");
      out.write("\t\t\t\t\tfor(var i=0; i<n; i++)\n");
      out.write("\t\t\t\t\t\tresult += '<br>Id: ' + response.lp[i].id + ' - Name: ' + response.lp[i].name + ' - Price: ' + response.lp[i].price;\n");
      out.write("\t\t\t\t\t$('.result').html(result);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t});\n");
      out.write("</script>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("\n");
      out.write("<body onload \"setTimeout(playActiveGame,5000)\">\n");
      out.write("\n");
      out.write("\t<input type=\"button\" value=\"Start a new Game\" id=\"buttonNewGame\">\n");
      out.write("\t<!-- input type=\"button\" value=\"Join an existing Game\" id=\"buttonJoinGame\" -->\n");
      out.write("\t<input type=\"button\" value=\"Join an existing Game\" id=\"buttonJoinGame2\">\n");
      out.write("\t\n");
      out.write("\t<BR><BR><input type=\"button\" name = \"raise\" value = \"Raise\" id = \"raise\"  onclick=\"raise()\">\n");
      out.write("\tPlease only bet between 0 dollars and up to 1 dollar with cents: <input type = \"text\" name = \"betAmount\"  value = \"0\" id = \"betAmount\"><BR>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<!--   input type=\"button\" value=\"Hello\" id=\"buttonHello\">\n");
      out.write("\t<input type=\"button\" value=\"Sum\" id=\"buttonSum\">\n");
      out.write("\t<input type=\"button\" value=\"Find\" id=\"buttonFind\">\n");
      out.write("\t<input type=\"button\" value=\"Find All\" id=\"buttonFindAll\" -->\n");
      out.write("\t<br>\n");
      out.write("\t<div class=\"result\"></div><BR><BR>\n");
      out.write("\t<div class=\"result2\"></div>\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
