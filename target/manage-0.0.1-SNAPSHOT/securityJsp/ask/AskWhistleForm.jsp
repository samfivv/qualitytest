<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String contextPath = request.getContextPath();
%>
<%
     
	String id = request.getParameter("whistleBlowing");
    String whistleBlowingType=request.getParameter("whistleBlowingType");
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
		if ($(':input[name="whistleBlowing"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/askWhidtleBlowing/findAskWhistleBlowingById', {
				whistleBlowing : $(':input[name="whistleBlowing"]').val(),
				whistleBlowingType: $(':input[name="whistleBlowingType"]').val()
			}, function(result) {
				if (result.whistleBlowing != undefined){
					 $('#questionTitle').html(result.questionTitle);
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="whistleBlowing" value="<%=id%>" type="hidden"/>
	<input name="whistleBlowingType" value="<%=whistleBlowingType%>" type="hidden"/>
	<input name="lookInfo" value="<%=lookInfo%>" type="hidden" />
		<fieldset>
			<legend>被举报内容</legend>
			<table class="table" style="width: 100%;">
			    <tr>
			    <th>被举报内容</th>
						<td><div id="questionTitle" name="questionTitle" style="width: 200px"  IsReadOnly="True"></div></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>