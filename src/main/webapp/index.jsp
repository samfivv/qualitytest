<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.midai.miya.sys.model.Operator"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>质检平台管理系统</title>
<jsp:include page="inc.jsp"></jsp:include>
<%
	Operator operator = (Operator) session.getAttribute("_loginOperator");
	if (operator != null) {
		request.getRequestDispatcher("/securityJsp/main.jsp").forward(request, response);
	} else {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
%>
</head>
<body>
</body>
</html>