<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String src = request.getParameter("src");
    if(src==null){
    	src="";
    }
    String state = request.getParameter("state");
    if(state==null){
    	state="";
    }
    String photoId = request.getParameter("photoId");
    if(photoId==null){
    	photoId="";
    }
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog,$grid,$pjq) {
		parent.$.messager.confirm('询问', '您确定要删除此机构吗？', function(r) {
		$.post(sy.contextPath + '/mdmy/photo/update', {
			'photoId' :$(':input[name="photoId"]').val(),
		}, function(result) {
			if(result.success){
				$pjq.messager.alert('提示', result.msg, 'info');
				$dialog.dialog('destroy');
				
			}
		}, 'json');
		})
	};
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="photoState" type="hidden" value="<%=state%>"/>
	<input name="photoId" type="hidden" value="<%=photoId%>"/>
		<fieldset>
			<legend>图片详情</legend>
			<table>
			<tr>
			<td>名称</td>
			<td><input name="photoName"  class="easyui-validatebox" data-options="required:true" value="" /></td>
			</tr>
			<tr>
			<td>
			<div align="center">
				 <img alt="" src="<%=src%>" width="400" height="350" />
				 </div></td>
			</tr>
			</table>
       </fieldset>
	</form>
</body>
</html>