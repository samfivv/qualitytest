<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String addtitionalId = request.getParameter("addtitionalId");
	if (addtitionalId == null) {
		addtitionalId = "";
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
			if ($('#addtitionalId').val().length > 0) {
				url = '<%=contextPath%>/mdmy/itemaddtitional/updateItemAddtitional';
			} else {
				url ='<%=contextPath%>/mdmy/itemaddtitional/saveItemAddtitional';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/itemaddtitional/saveItemAddtitional'){
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
		if ($(':input[name="addtitionalId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/itemaddtitional/findItemAddtitionalById', {
				addtitionalId : $(':input[name="addtitionalId"]').val(),
			}, function(result) {
				if (result.addtitionalId != undefined) {
					$('form').form('load', {
						'addtitionalId':result.addtitionalId,
						'itemId' : result.itemId,
						'addtitionalName' : result.addtitionalName,
						'addtitionalPrice'   : result.addtitionalPrice,
						'createTime' : result.createTime,
						'itemName' : result.itemName
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
		<fieldset>
			<legend>附加项目基本信息</legend>
			<input id="addtitionalId" value="<%=addtitionalId%>" type="hidden" />
			<input name="addtitionalId" value="<%=addtitionalId%>" type="hidden" />
			<table class="table" style="width: 100%;">
				<tr>
					<th>上级项目</th>
					<td><select  name="itemId" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/mdmy/item/findAllItemTree'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#syresource_id').combotree('clear');" title="清空" /></td>
				</tr>
				<tr>
					<th>描述</th>
					<td><input name="addtitionalName" class="easyui-validatebox" width="250" data-options="required:true"/></td>
				</tr>
				<tr>
				<th>费用</th>
					<td><input name="addtitionalPrice" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>