<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
%>
<%
     
	String id = request.getParameter("questionId");
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
	$(function() {
		if($(':input[name="lookInfo"]').val()=="true"){
			$(':input[name="questionDesc"]').attr("readonly","readonly");
		}
		if ($(':input[name="questionId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/ask/findQuestionById', {
				questionId : $(':input[name="questionId"]').val()
			}, function(result) {
				if (result.questionId != undefined) {
					$("#questionDesc").html(result.questionDesc)
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="questionId" value="<%=id%>" type="hidden"/>
	<input name="lookInfo" value="<%=lookInfo%>" type="hidden" />
		<fieldset>
			<legend>问题内容</legend>
			<table class="table" style="width: 100%;">
				<tr>
				<td><DIV id="questionDesc"></DIV></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>