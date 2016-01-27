<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String photo1 = request
			.getParameter("coverImgUrl");
	if (photo1 == null) {
		photo1 = "";
	}
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
			<legend>身份证照片</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<td><img alt="" src="<%=photo1%>"></td>

				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>