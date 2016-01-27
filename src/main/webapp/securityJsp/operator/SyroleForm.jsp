<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
%>
<%
     
	String id = request.getParameter("roleId");
    String lookInfo = request.getParameter("lookInfo");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		$('#addButton').linkbutton('disable');
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="roleId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/operatorController/update';
			} else {
				url = '<%=contextPath%>/mdmy/operatorController/saveRole';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/operatorController/saveRole'){
					if (result.success) {
						$pjq.messager.alert('提示', result.msg, 'info');
						$grid.datagrid('load');
						$dialog.dialog('destroy');
					} else {
						$pjq.messager.alert('提示', result.msg, 'error');
					}
				}else{
				if (result.success) {
					$pjq.messager.alert('提示', result.msg, 'info');
					$grid.datagrid('reload');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
				}
			}, 'json');
		}
	};
	$(function() {
		if($(':input[name="lookInfo"]').val()=="true"){
			$(':input[name="roleName"]').attr("readonly","readonly");
			$(':input[name="roleDesc"]').attr("readonly","readonly");
		}
		if ($(':input[name="roleId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/operatorController/findById', {
				roleId : $(':input[name="roleId"]').val()
			}, function(result) {
				if (result.roleId != undefined) {
					$('form').form('load', {
						'roleName' : result.roleName,
						'roleDesc' : result.roleDesc,
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="roleId" value="<%=id%>" type="hidden"/>
	<input name="lookInfo" value="<%=lookInfo%>" type="hidden" />
		<fieldset>
			<legend>角色基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>角色名称</th>
					<td><input name="roleName"  class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
				   
					<th>角色描述</th>
					<td><textarea name="roleDesc"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>