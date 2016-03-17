<%@page import="com.midai.miya.sys.model.Operator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>

<%
	String id = request.getParameter("typeId");
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
var submitForm = function($dialog, $grid, $pjq, $mainMenu) {
	if ($('form').form('validate')) {
		var url;
		if ($('#typeId').val().length > 0) {
			url = '<%=contextPath%>/mdmy/itemtype/updateItemType';
		} else {
			url ='<%=contextPath%>/mdmy/itemtype/saveItemType';
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			if(url=='<%=contextPath%>/mdmy/itemtype/saveItemType'){
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
	
	};
}
	$(function() {
		if($(':input[name="lookInfo"]').val()=="true"){
			$(':input[name="typeId"]').attr("readonly","readonly");
		}
		if ($('#typeId').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/itemtype/findItemTypeById',
					{
				typeId : $('#typeId').val()
					}, function(result) {
						if (result.typeId != undefined) {
							$('form').form('load', {
								'typeId' : result.typeId,
								'typeName' : result.typeName
							});
						}
						parent.$.messager.progress('close');
					}, 'json');
		}

	});
</script>
</head>
<body>
	<form method="post" class="form" >
	<input id="typeId"  value="<%=id%>" type="hidden" />
		<input name="lookInfo" value="<%=lookInfo%>" type="hidden" />
		<fieldset>
			<legend>项目类型信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th >类型编号</th>
					<td><input name="typeId" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<th>类型名称</th>
					<td><input name="typeName" class="easyui-validatebox" width="250" data-options="required:true"/></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>