<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("sensitiveWordId");
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
			if ($(':input[name="sensitiveWordId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/sensitive/update';
			} else {
				url = '<%=contextPath%>/mdmy/sensitive/save';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/sensitive/save'){
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
		if ($(':input[name="sensitiveWordId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/sensitive/findById', {
				sensitiveWordId : $(':input[name="sensitiveWordId"]').val()
			}, function(result) {
				if (result.sensitiveWordId != undefined) {
					$('form').form('load', {
						'sensitiveWord' : result.sensitiveWord,
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
	<input name="sensitiveWordId" value="<%=id%>" readonly="readonly" type="hidden"/>
		<fieldset>
			<legend>敏感词信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>敏感词名称</th>
					<td><input name="sensitiveWord"  class="easyui-validatebox" data-options="required:true"  /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>