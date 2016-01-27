<%@page import="com.midai.miya.sys.model.Operator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>

<%
	String id = request.getParameter("schoolNum");
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
var submitForm = function($dialog, $grid, $pjq, $mainMenu) {
	if ($('form').form('validate')) {
		var url;
		if ($('#schoolNum').val().length > 0) {
			url = '<%=contextPath%>/mdmy/school/updateSchool';
		} else {
			url ='<%=contextPath%>/mdmy/school/saveSchool';
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			if(url=='<%=contextPath%>/mdmy/school/saveSchool'){
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
	
	};
}
	$(function() {
		if($(':input[name="lookInfo"]').val()=="true"){
			$(':input[name="schoolNum"]').attr("readonly","readonly");
		}
		if ($('#schoolNum').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/school/findSchoolByNum',
					{
				schoolNum : $('#schoolNum').val()
					}, function(result) {
						if (result.schoolNum != undefined) {
							$('form').form('load', {
								'schoolNum' : result.schoolNum,
								'schoolSort' : result.schoolSort,
								'schoolName' : result.schoolName
							});
						}
						parent.$.messager.progress('close');
					}, 'json');
		}

	});
</script>
</head>
<body>
	<form method="post" class="form" >
	<input id="schoolNum"  value="<%=id%>" type="hidden" />
		<input name="lookInfo" value="<%=lookInfo%>" type="hidden" />
		<fieldset>
			<legend>用户信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th >学校编号</th>
					<td><input name="schoolNum" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<th>学校名称</th>
					<td><input name="schoolName" class="easyui-validatebox" data-options="required:true"/></td>
				<tr>
					<th>排序</th>
					<td><input name="schoolSort" class="easyui-numberspinner" data-options="required:true,min:1,max:100000,editable:false" style="width: 155px;" value="1" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>