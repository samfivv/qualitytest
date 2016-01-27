<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("circleId");
	if (id == null) {
		id = "";
	}
	String userId = request.getParameter("userId");
	if (userId == null) {
		userId = "";
	}
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
<script type="text/javascript" src="<%=contextPath%>/jslib/plupload-2.0.0/js/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/jslib/plupload-2.0.0/js/i18n/zh_CN.js"></script>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($('#circleId').val().length > 0) {
				url = '<%=contextPath%>/mdmy/circle/updateCircle';
			}  
			$.post(url, sy.serializeObject($('form')), function(result) {
				parent.sy.progressBar('close');//关闭上传进度条
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
		if ($('#circleId').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/circle/findCircleById', {
				circleId : $('#circleId').val()
			}, function(result) {
				if (result.circleId != undefined) {
					if(result.circleState==3 || result.circleState==5){
						result.circleState=1;
						$('#unpass').hide();
						$("#unpassDesc").attr("class","");
 					}
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		$('#selected').combobox({
			onChange: function (n,o) {
				if(n==2){
					$('#unpass').show();
					$('#unpassDesc').focus();
				}else{
					$('#unpass').hide();
					$("#unpassDesc").attr("class","");
				}
			}
		})
	});

</script>
</head>
<body>
	<form method="post" class="form">
	<input id="circleId" name="circleId" value="<%=id%>" type="hidden"/>
	<input id="userId" name="userId" value="<%=id%>" type="hidden"/>
		<fieldset>
			<legend>圈子基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>圈子状态</th>
					<td><select id="selected" class="easyui-combobox" name="circleState" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							    <option value="1" selected="selected">通过</option>
								<option value="2">不通过</option>
					</select></td>
				</tr>
				<tr id="unpass">
					<th>不通过原因</th>
					<td><textarea rows="3" cols="3" name="unpassDesc" id="unpassDesc" class="easyui-validatebox"  data-options="required:true"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>