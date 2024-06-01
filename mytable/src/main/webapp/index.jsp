<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--oracle.jdbc.OracleTypes--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="com.oracle_jdbc.*,oracle.jdbc.*,com.mysql.jdbc.Driver,java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<%--<%@ page contentType="text/html; charset=UTF-8"%> --%>
<%-- <%@ include file="loginControl.jsp" %> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<%pageContext.setAttribute("value",123); %>
  	<input type="text" name="inputPwd6" id="inputPwd6" value="<%=pageContext.getAttribute("value") %>" required>
 <%-- <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
					 url="jdbc:mysql://localhost:3306/mytable"
					 user="root"  password="root"/>

  <sql:query dataSource="${snapshot}" sql="select * from userdata" var="result" />--%>
	<table border="1" width="100%">
	<tr>
	<th>www</th>
	<th>username</th>
	<th>pwd10</th>
	<th>pwd6</th>
	</tr>
	<%--<c:forEach var="row" items="${result.rows}">
	<tr>
		<td><c:out value="${row.www}"/></td>
		<td><c:out value="${row.username}"/></td>
		<td><c:out value="${row.pwd10}"/></td>
		<td><c:out value="${row.pwd6}"/></td>
	</tr>
	</c:forEach>--%>
	</table>
  <h2>Hello World!${indexPageShow}23</h2>
  </body>
</html>
