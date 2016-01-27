<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("userBillChargebackId");
	if (id == null) {
		id = "";
	}
	String userId = request.getParameter("userId");
	if (userId == null) {
		userId = "";
	}
	String billNum = request.getParameter("billNum");
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style type="text/css">
  #text{
    display:none;
  }
</style>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		    if ($('form').form('validate')) {
			var url;
			}else{
				return ;
			}
		    parent.$.messager.confirm('询问', '您确定此审核结果吗？', function(r) {
				if (r) {
					url = '<%=contextPath%>/mdmy/userBillChangeBack/updateUserBillChargeback';
			     $.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					$grid.datagrid('reload');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
				}
				});
		};
		$(function() {
			if ($(':input[name="userBillChargebackId"]').val().length > 0) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
				$.post('<%=contextPath%>/mdmy/userBillChangeBack/findBillChargebackById', {
					userBillChargebackId : $(':input[name="userBillChargebackId"]').val()
				}, function(result) {
					if (result.userBillChargebackId != undefined) {
						if(result.userBillChargebackState==1){
							result.userBillChargebackState=2;
						}
						if(result.userBillChargebackState==3){
								$("#hidden").show();
						}else{
							$("#otherNotPassReason").attr("class","");
							$("#hidden").hide();
						}
						$('form').form('load', {
							'userBillChargebackState' : result.userBillChargebackState,
							'notPassReason' : result.notPassReason,
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
	<input name="userBillChargebackId" value="<%=id%>" type="hidden"/>
	<input name="userId" value="<%=userId%>" type="hidden"/>
	<input name="billNum" value="<%=billNum%>" type="hidden"/>
		<fieldset>
			<legend>审核结果信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>审核结果</th>
					<td><select id="selected" class="easyui-combobox" name="userBillChargebackState" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="2" >通过</option>
							<option value="3">不通过</option>
					</select></td>
				</tr>
				<tr id="hidden">
				<th>不通过原因</th>
				<td><textarea id="otherNotPassReason" name="notPassReason" class="easyui-validatebox" data-options="required:true"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>