<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("interestId");
	if (id == null) {
		id = "";
	}
	String interestCreator = request.getParameter("interestCreator");
	if (interestCreator == null) {
		interestCreator = "";
	} 
	
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
					url = '<%=contextPath%>/mdmy/interestApproval/update';
					console.log(sy.serializeObject($('form')))
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
			if ($(':input[name="interestId"]').val().length > 0) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
				$.post('<%=contextPath%>/mdmy/interestApproval/findInterestByInterestId', {
					interestId : $(':input[name="interestId"]').val()
				}, function(result) {
					if (result.interestId != undefined) {
						//alert(result.videoState);
						if(result.interestState==null||result.interestState!=2){
							result.interestState=1;
						}  
						
						if(result.interestState==2){
							$("#notPassReasonId").show();
								$("#hidden").show();
						}else{
							$("#otherNotPassReason").attr("class","");
							$("#notPassReasonId").hide();
							$("#hidden").hide();
						}
						$('form').form('load', {
							'interestState' : result.interestState,
							'notPassReason' : result.notPassReason,
						});
					}
					parent.$.messager.progress('close');
				}, 'json');
				
			}
			$('#selected').combobox({
				onChange: function (n,o) {
				if(n==2){
					$("#notPassReasonId").show();
					$("#otherNotPassReason").attr("class","easyui-validatebox");
						$("#hidden").show();
					$("#otherNotPassReason").focus();
				}else{
					$("#otherNotPassReason").attr("class","");
					$("#notPassReasonId").hide();
					$("#hidden").hide();
				}
				}
			});
		});
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="interestId" value="<%=id%>" type="hidden"/>
	<input name="interestCreator" value="<%=interestCreator%>" type="hidden"/>
		<fieldset>
			<legend>状态管理</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>状态</th>
					<td><select id="selected" class="easyui-combobox" name="interestState" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1" >通过</option>
							<option value="2">不通过</option>
					</select></td>
				</tr>
				<tr id="notPassReasonId">
					<th>不通过原因</th>
					<td id="hidden"><textarea id="otherNotPassReason" name="notPassReason" class="easyui-validatebox" data-options="required:true"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>