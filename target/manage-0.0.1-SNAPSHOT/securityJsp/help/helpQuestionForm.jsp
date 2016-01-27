<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("helpQuestionId");
    if(id==null){
    	id="";
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
			if ($(':input[name="helpQuestionId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/help/update';
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
		if ($(':input[name="helpQuestionId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/help/findById', {
				helpQuestionId : $(':input[name="helpQuestionId"]').val()
			}, function(result) {
				if (result.helpQuestionId != undefined) {
					$('form').form('load', {
						'helpSuggestion' : result.helpSuggestion,
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
	<input name="helpQuestionId" value="<%=id%>" readonly="readonly" type="hidden"/>
		<fieldset>
			<legend>处理信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>处理意见</th>
					<td><textarea name="helpSuggestion" class="easyui-validatebox" data-options="required:true"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>