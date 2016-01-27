<%@page import="com.midai.miya.sys.model.Operator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>

<%
	String id = request.getParameter("operatorId");
	String lookInfo = request.getParameter("lookInfo");
	String see = request.getParameter("see");
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
	var uploader;//上传对象
	var submitNow = function($dialog, $grid, $pjq) {};
	var submitForm = function($dialog, $grid, $pjq) {
		var $form = $("#operatorForm");
		if (!$form.form('validate')) {
			return;
		}
		$('#grantButton').linkbutton('disable');
		var url;
		if ($(':input[name="operatorId"]').val().length > 0) {
			url = '<%=contextPath%>/mdmy/operatorController/updateOperator';
		} else {
			url = '<%=contextPath%>/mdmy/operatorController/save';
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条
			if(url=='<%=contextPath%>/mdmy/operatorController/save'){
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

	

	$(function() {
		if($(':input[name="see"]').val()=="true"){
			$(':input[name="operatorName"]').attr("readonly","readonly");
		}
		if($(':input[name="lookInfo"]').val()=="true"){
			$(':input[name="operatorRealName"]').attr("readonly","readonly");
			$(':input[name="operatorName"]').attr("readonly","readonly");
			$(':input[name="operatorState"]').attr("readonly","readonly");
			$(':input[name="operatorMail"]').attr("readonly","readonly");
			$(':input[name="operatorMobile"]').attr("readonly","readonly");
			$(':input[name="operatorPosition"]').attr("readonly","readonly");
			$(':input[name="operatorDept"]').attr("readonly","readonly");
			$("#operatorState").combobox({ readonly: true })
		}
		if ($(':input[name="operatorId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/operatorController/findOperatorById',
					{
						operatorId : $(':input[name="operatorId"]').val()
					}, function(result) {
						if (result.operatorId != undefined) {
							$('form').form('load', {
								'operatorRealName' : result.operatorRealName,
								'operatorName' : result.operatorName,
								'operatorState' : result.operatorState,
								'operatorMail' : result.operatorMail,
								'operatorMobile' : result.operatorMobile,
								'operatorPosition' : result.operatorPosition,
								'operatorDept' : result.operatorDept
							});
							if (result.photo) {
								$('#photo').attr('src',
										sy.contextPath + result.photo);
							}
						}
						parent.$.messager.progress('close');
					}, 'json');
		}

	});
</script>
</head>
<body>
	<form method="post" class="form" id="operatorForm">
		<input name="operatorId" value="<%=id%>" type="hidden" /> <input
			name="lookInfo" value="<%=lookInfo%>" type="hidden" /> <input
			name="see" value="<%=see%>" type="hidden" />
		<fieldset>
			<legend>用户信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>登录名</th>
					<td><input name="operatorName" class="easyui-validatebox"
						data-options="required:true,validType:'eqName'" /></td>
					<th>状态</th>
					<td><select name="operatorState"
						class="easyui-combobox easyui-validatebox"
						data-options="panelHeight:'auto',editable:false,required:true"
						id="operatorState">
							<option value="1">正常</option>
							<option value="0">禁用</option>
					</select></td>
				</tr>
				<tr>
					<th>姓名</th>
					<td><input name="operatorRealName" /></td>
					<th>手机</th>
					<td><input name="operatorMobile" class="easyui-validatebox"
						data-options="validType:'phoneRex'"></td>
				<tr>
					<th>职位</th>
					<td><input name="operatorPosition" /></td>
					<th>部门</th>
					<td><input name="operatorDept"></td>
				</tr>
				<tr>
					<th>邮箱</th>
					<td><input name="operatorMail" class="easyui-validatebox" 
					data-options="validType:'eqEmail'"/></td>
				</tr>

			</table>
		</fieldset>
	</form>
</body>
</html>