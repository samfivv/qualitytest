<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
%>
<%
     
	String id = request.getParameter("hobbyId");
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
			if ($(':input[name="hobbyId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/hobbyController/updateHobby';
			} else {
				url = '<%=contextPath%>/mdmy/hobbyController/addHobby';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/hobbyController/addHobby'){
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
			$(':input[name="hobbyName"]').attr("readonly","readonly");
			  $('#selected').combobox({    
	                 required:true,    
	                 //multiple:true,
	                 disabled:true  
	           });  

		}
		if ($(':input[name="hobbyId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/hobbyController/findHobbyById', {
				hobbyId : $(':input[name="hobbyId"]').val()
			}, function(result) {
				if (result.hobbyId != undefined) {
					$('form').form('load', {
						'hobbyName' : result.hobbyName,
						'hobbyState' : result.hobbyState,
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
	<input name="hobbyId" value="<%=id%>" type="hidden"/>
	<input name="lookInfo" value="<%=lookInfo%>" type="hidden" />
		<fieldset>
			<legend>爱好基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>爱好名称</th>
					<td><input name="hobbyName"  class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
				   
					<th>爱好状态</th>
					<td><select id="selected" class="easyui-combobox" name="hobbyState" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">正常</option>
							<option value="2">删除</option>
					</select></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>