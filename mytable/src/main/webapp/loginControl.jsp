<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:if test="${empty sessionScope.cardId }">
	    <!-- sessionID失效或者没有登陆成功，则不能访问个人主页。不为null，不需要再加else，停留在当前页面 -->
		<c:redirect url="load.jsp"></c:redirect>
	</c:if>
  	
