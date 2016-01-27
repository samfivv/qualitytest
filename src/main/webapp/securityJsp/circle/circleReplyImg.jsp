<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style type="text/css">
#text {
	display: none;
}
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>图片</legend>
			<table class="table" style="width: 100%;">
			<c:if test="${circleReply.circleTopicImgLists!=null}">
			<c:forEach items="${circleReply.circleTopicImgLists}" var="circleTopicImg">
				<tr>
					<td><img alt="" src="${circleTopicImg.circleImgUrl }"></td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${circleReply.circleTopicImgLists==null}">暂无图片</c:if>
			</table>
		</fieldset>
	</form>
</body>
</html>