<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String sysConfigId = request.getParameter("sysConfigId");
   
    if(sysConfigId==null){
    	sysConfigId="";
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
			if ($(':input[name="sysConfigId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/sysConfig/update';
			} else {
				url = '<%=contextPath%>/mdmy/sysConfig/save';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/sysConfig/save'){
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
		if ($(':input[name="sysConfigId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/sysConfig/findConfigById', {
				sysConfigId : $(':input[name="sysConfigId"]').val()
			}, function(result) {
				if (result.sysConfigId != undefined) {
					$('form').form('load', {
						'sysConfigKey' : result.sysConfigKey,
						'sysConfigValue' : result.sysConfigValue,
						'sysConfigDesc':result.sysConfigDesc
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
	><input name="sysConfigId" value="<%=sysConfigId%>" readonly="readonly" type="hidden"/>
		<fieldset>
			<legend>参数信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>参数名称</th>
					<td><input name="sysConfigKey"  class="easyui-validatebox" data-options="required:true"  /></td>
					<th>参数值</th>
					<td><input name="sysConfigValue"  class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<th>参数描述</th>
					<td><input name="sysConfigDesc"  class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>