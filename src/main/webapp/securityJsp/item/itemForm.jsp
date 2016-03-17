<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String itemId = request.getParameter("itemId");
	if (itemId == null) {
		itemId = "";
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
			if ($(':input[name="itemId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/item/updateItem';
			} else {
				url ='<%=contextPath%>/mdmy/item/saveItem';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/item/saveItem'){
					if (result.success) {
						$pjq.messager.alert('提示', result.msg, 'info');
						$grid.treegrid('load');
						$dialog.dialog('destroy');
					} else {
						$pjq.messager.alert('提示', result.msg, 'error');
					}
				}else{
				if (result.success) {
					$pjq.messager.alert('提示', result.msg, 'info');
					$grid.treegrid('reload');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
				}
			}, 'json');
		}
	};
	var showIcons = function() {
		var dialog = parent.sy.modalDialog({
			title : '浏览小图标',
			url : sy.contextPath + '/securityJsp/resource/icons.jsp',
			buttons : [ {
				text : '确定',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.selectIcon(dialog, $('#iconCls'));
				}
			} ]
		});
	};
	$(function() {
		if ($(':input[name="itemId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/item/findItemById', {
				itemId : $(':input[name="itemId"]').val(),
			}, function(result) {
				if (result.itemId != undefined) {
					$('form').form('load', {
						'itemId':result.itemId,
						'itemName' : result.itemName,
						'parentId' : result.parentId,
						'typeId'   : result.typeId,
						'itemPrice' : result.itemPrice,
						'itemSort' : result.itemSort
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
			<legend>项目基本信息</legend>
			<input name="itemId" value="<%=itemId%>" type="hidden" />
			<table class="table" style="width: 100%;">
				<tr>
					<th>项目名称</th>
					<td><input name="itemName" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>项目类型</th>
					<td><select name="typeId" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/mdmy/itemtype/findItemForOption'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#syresource_id').combotree('clear');" title="清空" /></td>
					<th>上级项目</th>
					<td><select  name="parentId" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/mdmy/item/findTopLevelItem'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#syresource_id').combotree('clear');" title="清空" /></td>
				</tr>
				<tr>
				<th>费用</th>
					<td><input name="itemPrice" /></td>
					<th>顺序</th>
					<td colspan="3"><input name="itemSort" class="easyui-numberspinner" data-options="required:true,min:0,max:100000,editable:true" style="width: 155px;" value="1" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>