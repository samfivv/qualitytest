<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("subjectInterestId");
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
	var submitForm = function($dialog, $grid, $pjq, $mainMenu) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="subjectInterestId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/subject/updateSubjectInterest';
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
		if ($(':input[name="subjectInterestId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/subject/findSubjectInterestById', {
				subjectInterestId : $(':input[name="subjectInterestId"]').val(),
			}, function(result) {
				if(result.subjectInterestState==2){
					$('#paixu').hide();
				}
				if (result.subjectInterestId != undefined) {
					$('form').form('load', {
						'subjectInterestState':result.subjectInterestState,
						'subjectInterestSort' : result.subjectInterestSort
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		$('#select').combobox({
			onChange:function (n,o) {
				if(n==2){
					$('#paixu').hide();
				}else{
					$('#paixu').show();
				}
			}
		})
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>活动管理</legend>
			<input name="subjectInterestId" value="<%=id%>" type="hidden" />
			<table class="table" style="width: 100%;">
				<tr>
				    <th>状态</th>
					<td><select id="select" name="subjectInterestState" class="easyui-combobox" data-options="required:true,panelHeight:'auto'" style="width: 155px;">
					<option value="1" selected="selected">通过</option>
					<option value="2">不通过</option>
					</select></td>
				</tr>
				<tr id="paixu">
					<th>排序</th>
					<td colspan="3"><input name="subjectInterestSort" class="easyui-numberspinner" data-options="required:true,min:0,max:100000,editable:true" style="width: 155px;" value="1" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>