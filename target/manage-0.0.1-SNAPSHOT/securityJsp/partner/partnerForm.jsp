<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
%>
<%
     
	String id = request.getParameter("partnerId");
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
	var submitForm = function($dialog, $grid, $pjq) {
		$('#addButton').linkbutton('disable');
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="partnerId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/partner/updatePartner';
			} else {
				url = '<%=contextPath%>/mdmy/partner/savePartner';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/partner/savePartner'){
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
		}
	};
	$(function() {
		if($(':input[name="lookInfo"]').val()=="true"){
			$(':input[name="partnerName"]').attr("readonly","readonly");
			$(':input[name="partnerUrl"]').attr("readonly","readonly");
			$('#selected').combobox({
				required:true,
				//multiple:true,
				disabled:true
			})
		}
		if ($(':input[name="partnerId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/partner/findById', {
				partnerId : $(':input[name="partnerId"]').val()
			}, function(result) {
				if(result.partnerType==2){
					$('#hidden').show();
					$("#partnerImgUrl").attr("class","easyui-validatebox");
					$("#partnerImgUrl").focus();
				}else{
					$('#hidden').hide();
				}
				if (result.partnerId != undefined) {
					$('form').form('load', {
						'partnerName' : result.partnerName,
						'partnerUrl' : result.partnerUrl,
						'partnerImgUrl' : result.partnerImgUrl,
						'partnerType' : result.partnerType,
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}else{
			$("#hidden").hide();
			$("#partnerImgUrl").attr("class","");
		}
		$('#selected').combobox({
			onChange:function(n,o){
				if(n=='2'){
					$('#hidden').show();
					$("#partnerImgUrl").attr("class","easyui-validatebox");
					$("#partnerImgUrl").focus();
				}else{
					$("#hidden").hide();
					$("#partnerImgUrl").attr("class","");
				}
			}
		});
	});
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="partnerId" value="<%=id%>" type="hidden"/>
	<input name="lookInfo" value="<%=lookInfo%>" type="hidden" />
		<fieldset>
			<legend>合作伙伴基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>合作伙伴名称</th>
					<td><input name="partnerName"  class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>合作伙伴链接</th>
					<td><input name="partnerUrl"  class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>合作伙伴类型</th>
					<td><select id="selected" class="easyui-combobox" name="partnerType" data-options="panelHeight:'auto',editable:false" style="width: 120px;">
											<option value="1">文字链接</option>
											<option value="2">图片链接</option>
									</select></td>
				</tr>
				<tr id="hidden">
				   <th>合作伙伴图片地址</th>
					<td><input name="partnerImgUrl" id="partnerImgUrl" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>