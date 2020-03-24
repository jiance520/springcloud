<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <%=session.getId()%>
    
    <sql:setDataSource var="snapshot" driver="oracle.jdbc.driver.OracleDriver" url="jdbc:oracle:thin:@localhost:1521/orcl" user="mytable"  password="mytable"/>
    <sql:query dataSource="${snapshot}" var="result">select * from userdata</sql:query>
	<table border="1" width="100%">
	<tr>
	<th>www</th>
	<th>username</th>
	<th>pwd10</th>
	<th>pwd6</th>
	</tr>
	<c:forEach var="row" items="${result.rows}">
	<tr>
		<td><c:out value="${row.www}"/></td>
		<td><c:out value="${row.username}"/></td>
		<td><c:out value="${row.pwd10}"/></td>
		<td><c:out value="${row.pwd6}"/></td>
	</tr>
	</c:forEach>
	</table>
  </body>
</html>
