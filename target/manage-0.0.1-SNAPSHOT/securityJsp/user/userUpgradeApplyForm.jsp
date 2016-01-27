<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("userUpgradeApplyId");
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
			if ($(':input[name="userUpgradeApplyId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/userUpgradeApply/update';
			} 
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					$pjq.messager.alert('提示', result.msg, 'info');
					$grid.datagrid('reload');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	$(function() {
		if ($(':input[name="userUpgradeApplyId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/userUpgradeApply/findUserUpgradeApplyById', {
				userUpgradeApplyId : $(':input[name="userUpgradeApplyId"]').val(),
			}, function(result) {
				if(result.auditState==1){
					result.auditState=2;
				}
				if(result.auditState==3){
						$("#hidden").show();
				}else{
					$("#otherNotPassReason").attr("class","");
					$("#hidden").hide();
				}
				if (result.userUpgradeApplyId != undefined) {
					$('form').form('load', {
						'auditState':result.auditState,
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		$('#selected').combobox({
			onChange: function (n,o) {
			if(n==3){
				$("#otherNotPassReason").attr("class","easyui-validatebox");
					$("#hidden").show();
				$("#otherNotPassReason").focus();
			}else{
				$("#otherNotPassReason").attr("class","");
				$("#hidden").hide();
			}
			}
		});
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>特长人申请</legend>
			<input name="userUpgradeApplyId" value="<%=id%>" type="hidden" />
			<table class="table" style="width: 100%;">
				<tr>
				    <th>审核状态</th>
					<td><select id="selected" name="auditState" class="easyui-combobox" data-options="required:true,panelHeight:'auto'" style="width: 155px;">
					<option value="2">通过</option>
					<option value="3">不通过</option>
					</select></td>
				</tr>
				<tr id="hidden">
				<th>不通过原因</th>
				<td><textarea id="otherNotPassReason" name="applyNotPassReason" class="easyui-validatebox" data-options="required:true"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>