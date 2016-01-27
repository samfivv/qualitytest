<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
%>
<%
     
	String id = request.getParameter("replyId");
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
			$(':input[name="replyContent"]').attr("readonly","readonly");
		}
		if ($(':input[name="replyId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/ask/findAskReplyById', {
				replyId : $(':input[name="replyId"]').val()
			}, function(result) {
				if (result.replyId != undefined) {
					$('#questionTitle').html(result.questionTitle);
					$('#replyContent').html(result.replyContent);
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="replyId" value="<%=id%>" type="hidden"/>
	<input name="lookInfo" value="<%=lookInfo%>" type="hidden" />
		<fieldset>
			<legend>回复内容</legend>
			<table class="table" style="width: 100%;">
			    <tr>
			    <td width="80px">问题标题：</td>
			    <td><div id="questionTitle"></div></td>
			    </tr>
				<tr>
				<td width="80px">回复内容：</td>
					<td><div id="replyContent"></div></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>