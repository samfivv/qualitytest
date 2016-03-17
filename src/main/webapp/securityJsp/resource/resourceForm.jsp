<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
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
			if ($(':input[name="id"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/operatorController/updatePermission';
			} else {
				url ='<%=contextPath%>/mdmy/operatorController/savePermission';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/operatorController/savePermission'){
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
		if ($(':input[name="id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/operatorController/findPermissionById', {
				permissionId : $(':input[name="id"]').val(),
			}, function(result) {
				if (result.permissionId != undefined) {
					$('form').form('load', {
						'permissionId':result.permissionId,
						'permissionName' : result.permissionName,
						'permissionUrl' : result.permissionUrl,
						'permissionType' : result.permissionType,
						'permissionParentId' : result.permissionParentId,
						'permissionImgUrl' : result.permissionImgUrl,
						'permissionSort' : result.permissionSort,
						'permissionIsshow' : result.permissionIsshow
					});
					$('#iconCls').attr('class', result.permissionImgUrl);//设置背景图标
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
			<legend>资源基本信息</legend>
			<input name="id" value="<%=id%>" type="hidden" />
			<table class="table" style="width: 100%;">
				<tr>
				    <th>资源ID</th>
					<td><input name="permissionId" class="easyui-validatebox" data-options="required:true"/></td>
					<th>资源名称</th>
					<td><input name="permissionName" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
				    <th>是否显示</th>
					<td><select name="permissionIsshow" class="easyui-combobox" data-options="required:true,panelHeight:'auto'" style="width: 155px;">
					<option value="1">显示</option>
					<option value="2">不显示</option>
					</select></td>
					<th>资源类型</th>
					<td><select name="permissionType" class="easyui-combobox" data-options="required:true,panelHeight:'auto'" style="width: 155px;">
					<option value="1">菜单</option>
					<option value="2">按钮</option>
					</select></td>
				</tr>
				<tr>
					<th>上级资源</th>
					<td><select id="pid" name="permissionParentId" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/mdmy/operatorController/findPermissionByOperatorId'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#syresource_id').combotree('clear');" title="清空" /></td>
					<th>资源图标</th>
					<td><input id="iconCls" name="permissionImgUrl" readonly="readonly" style="padding-left: 18px; width: 134px;" /><img class="iconImg ext-icon-zoom" onclick="showIcons();" title="浏览图标" />&nbsp;<img class="iconImg ext-icon-cross" onclick="$('#iconCls').val('');$('#iconCls').attr('class','');" title="清空" /></td>
				</tr>
				<tr>
				<th>资源路径</th>
					<td><input name="permissionUrl" /></td>
					<th>顺序</th>
					<td colspan="3"><input name="permissionSort" class="easyui-numberspinner" data-options="required:true,min:0,max:100000,editable:true" style="width: 155px;" value="1" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>