<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("withdrawDepositId");
	if (id == null) {
		id = "";
	}
	String userId = request.getParameter("userId");
	if (userId == null) {
		userId = "";
	}
	String phoneNum=request.getParameter("phoneNum");
	if (phoneNum == null) {
		phoneNum = "";
	}
	String userMail=request.getParameter("userMail");
	if (userMail == null) {
		userMail = "";
	}
	String money=request.getParameter("money");
	if (money == null) {
		money = "";
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
				url = '<%=contextPath%>/mdmy/withdrawDeposit/updateWithdrawDeposit';
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
	<%-- $(function() {
		if ($(':input[name="withdrawDepositId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/withdrawDeposit/findWithdrawDepositById', {
				withdrawDepositId : $(':input[name="withdrawDepositId"]').val()
			}, function(result) {
				if (result.withdrawDepositId != undefined) {
					$('form').form('load', {
						'dealState' : result.dealState,
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	}); --%>
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="withdrawDepositId" value="<%=id%>" type="hidden"/>
	<input name="phoneNum" value="<%=phoneNum%>" type="hidden"/>
	<input name="userMail" value="<%=userMail%>" type="hidden"/>
	<input name="userId" value="<%=userId%>" type="hidden"/>
	<input name="money" value="<%=money%>" type="hidden"/>
		<fieldset>
			<legend>提现基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>处理状态</th>
					<td><select id="selected" class="easyui-combobox" name="dealState" data-options="panelHeight:'auto',editable:false" style="width: 100px;">
							<option value="2">已处理</option>
							<option value="3">驳回申请</option>
					</select></td>
				</tr>
				<tr>
				<th>备注</th>
				<td><textarea name="remak" class="easyui-validatebox" data-options="required:true" ></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>