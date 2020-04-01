<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.oracle_jdbc.*,java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updatePwd6.jsp' starting page</title>
    
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
    <form action="ModifyPwd6?www=${map.get('WWW')}&userName=${map.get('USERNAME')}" method="post">
    	<table>
    		<tr><td>网站：</td><td>${map.get('WWW')}</td></tr>
    		<tr><td>用户名：</td><td>${map.get('USERNAME')}</td></tr>
    		<tr><td>密码：</td><td><input type="text" name="pwd10" id="pwd10" value='${map.get("PWD10")}' required></td></tr>
    		<tr><td>密码6：</td><td><input type="text" name="pwd6" id="pwd6" value='${map.get("PWD6")}' required></td></tr>
    		<tr><td>日期：</td><td><input type="text" name="registerdate" id="registerdate" value='${map.get("REGISTERDATE")}'></td></tr>
    		<tr><td>备注：</td><td><input type="text" name="note" id="note" value='${map.get("NOTE")}'></td></tr>
    		<tr><td>图片链接：</td><td><input type="text" name="cardImage" id="cardImage" value='${map.get("CARDIMAGE")}'></td></tr>
    		<tr><td><input type="submit" name="submit" id="submit" value='提交' required></td><td></td></tr>
    	</table>
    </form>
  </body>
</html>
