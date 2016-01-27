<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("videoId");
	if (id == null) {
		id = "";
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
					url = '<%=contextPath%>/mdmy/videoController/addApproval';
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
			if ($(':input[name="videoId"]').val().length > 0) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
				$.post('<%=contextPath%>/mdmy/videoController/findVideoById', {
					videoId : $(':input[name="videoId"]').val()
				}, function(result) {
					if (result.videoId != undefined) {
						//alert(result.videoState);
						if(result.videoState==null||result.videoState=='result.videoState'||result.videoState==0){
							result.videoState=1;
						} else if(result.videoState==3){
							result.videoState=1;
						 					}
						if(result.videoState==2){
							$("#notPassReasonId").show();
							if(result.notPassReasonState=='zzz'){
								$("#hidden").show();
							}else{
								$("#hidden").hide();
							}
						}else{
							$("#otherNotPassReason").attr("class","");
							$("#notPassReasonId").hide();
							$("#hidden").hide();
						}
						$('form').form('load', {
							'videoState' : result.videoState,
							'notPassReasonState': result.notPassReasonState,
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
					if($('#select').combobox('getValue')=='zzz'){
					$("#otherNotPassReason").attr("class","easyui-validatebox");
						$("#hidden").show();
					$("#otherNotPassReason").focus();
					} 
				}else{
					$("#otherNotPassReason").attr("class","");
					$("#notPassReasonId").hide();
					$("#hidden").hide();
				}
				}
			});
			$('#select').combobox({
				onChange:function(n,o){
					if(n=='zzz'){
						$('#hidden').show();
						$("#otherNotPassReason").attr("class","easyui-validatebox");
						$("#otherNotPassReason").focus();
					}else{
						$("#hidden").hide();
						$("#otherNotPassReason").attr("class","");
					}
				}
			});
		});
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="videoId" value="<%=id%>" type="hidden"/>
		<fieldset>
			<legend>审核结果信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>审核结果</th>
					<td><select id="selected" class="easyui-combobox" name="videoState" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1" >通过</option>
							<option value="2">不通过</option>
					</select></td>
				</tr>
				<tr id="notPassReasonId">
					<th>不通过原因</th>
					<td><select id="select" url="<%=contextPath%>/mdmy/videoController/findNotPassReason" valueField="key" textField="value" class="easyui-combobox" name="notPassReasonState" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
					<option value="zzz">其他</option>
					</select></td>
				</tr>
				<tr id="hidden">
				<td></td>
				<td><textarea id="otherNotPassReason" name="notPassReason" class="easyui-validatebox" data-options="required:true"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>