<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table style="width: 100%;">
			<tr>
				<td><fieldset>
						<legend>用户信息</legend>
						<table class="table" style="width: 100%;">
							<tr>
								<th>登录名</th>
								<td>${_loginOperator.operatorName}</td>
								<th>姓名</th>
								<td>${_loginOperator.operatorRealName}</td>
							</tr>
							<tr>
								<th>Email</th>
								<td>${_loginOperator.operatorMail}</td>
								<th>手机</th>
								<td>${_loginOperator.operatorMobile}</td>
							</tr>
							<tr>
								<th>职位</th>
								<td>${_loginOperator.operatorPosition}</td>
								<th>部门</th>
								<td>${_loginOperator.operatorDept}</td>
							</tr>
							<tr>
								<th>创建时间</th>
								<td colspan="3" >
								<fmt:formatDate pattern="yyyy-MM-dd" value="${_loginOperator.operatorCreateTime}" />
								</td>
							</tr>
						</table>
					</fieldset>
			</tr>
		</table>
	</div>
</body>
</html>