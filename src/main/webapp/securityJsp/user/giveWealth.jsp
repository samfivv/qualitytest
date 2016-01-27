<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("userId");
    if(id==null){
    	id="";
    }
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
				url = '<%=contextPath%>/mdmy/userController/giveWealth';
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/userController/giveWealth'){
					if (result.success) {
						$pjq.messager.alert('提示', result.msg, 'info');
						$grid.datagrid('load');
						$dialog.dialog('destroy');
					} else {
						$pjq.messager.alert('提示', result.msg, 'error');
					}
				} 
			}, 'json');
		}
	};
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="userId" value="<%=id%>" readonly="readonly" type="hidden"/>
		<fieldset>
			<legend>赠送财富信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>赠送金额</th>
					<td><input name="logMoney"  class="easyui-validatebox" data-options="required:true"  /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><textarea name="logDesc"  class="easyui-validatebox" data-options="required:true"  /></textarea>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>