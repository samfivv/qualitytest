<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=" <%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>米芽网</title>
</head>
<body>

<!--main-->
<div class="cc">
	<div style="color:red">发布成功</div><br>
	<%-- <a href="javascript:void(0)" onclick="window.location.href='<%=basePath %>/mdmy/questionnaireController/findQuestionnaireResult/${questionnaireId}'">查看刚刚的发布结果</a>
 --%></div> 
</body>
</html>