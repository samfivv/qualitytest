<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style type="text/css">
textarea {
				display: block;
			}
</style>
 <link rel="stylesheet" href="<%=basePath %>/jslib/kindeditor-4.1.7/themes/default/default.css" />
		<script charset="utf-8" src="<%=basePath %>/jslib/kindeditor-4.1.7/kindeditor-min.js"></script>
		<script charset="utf-8" src="<%=basePath %>/jslib/kindeditor-4.1.7/lang/zh_CN.js"></script>
		<script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="questionnaireDesc"]', {
					allowFileManager : true,
					afterBlur: function(){this.sync();}
				});
				K('input[name=getHtml]').click(function(e) {
					alert(editor.html());
				});
				K('input[name=isEmpty]').click(function(e) {
					alert(editor.isEmpty());
				});
				K('input[name=getText]').click(function(e) {
					alert(editor.text());
				});
				K('input[name=selectedHtml]').click(function(e) {
					alert(editor.selectedHtml());
				});
				K('input[name=setHtml]').click(function(e) {
					editor.html('<h3>Hello KindEditor</h3>');
				});
				K('input[name=setText]').click(function(e) {
					editor.text('<h3>Hello KindEditor</h3>');
				});
				K('input[name=insertHtml]').click(function(e) {
					editor.insertHtml('<strong>插入HTML</strong>');
				});
				K('input[name=appendHtml]').click(function(e) {
					editor.appendHtml('<strong>添加HTML</strong>');
				});
				K('input[name=clear]').click(function(e) {
					editor.html('');
				});
			});
			
		</script>
		<script type="text/javascript">
		 
		function CheckQuestionNaire(){
			if($('#txtTitle').val()==''){
				alert('调查标题不能为空');
				return;
			}
			var questionnaireTitle=$('#txtTitle').val();
			var questionnaireType=$('#type option:selected').val();
			var questionnaireDesc=$('#content').val();
			var questionnaireEndTime=$('#questionnaireEndTime').val();
			var questionnaireId=$('#questionnaireId').val();
		    questionnaireEndTime=new Date(questionnaireEndTime);
			var questionnaire={
					"questionnaireId":questionnaireId,
					"questionnaireTitle":questionnaireTitle,
					"questionnaireType":questionnaireType,
					"questionnaireDesc":questionnaireDesc,
					"questionnaireEndTime":questionnaireEndTime
			}
			$.ajax({
				type:"post",
				url:'<%=contextPath%>/mdmy/questionnaireController/save',
				data:questionnaire,
				dataType:'json',
			    success:function(result){
			    	if(result.success==true){
			    		location.href='<%=contextPath%>/mdmy/questionnaireController/findQuestionnaireQuestion/'+result.data.questionnaireId;
			    	}
			    }
			})
		}
		</script>
</head>
<body>
<form action="nextquestion.jsp" method="post">
<input type="hidden" id="questionnaireId" name="questionnaireId" value="${questionnaire.questionnaireId}"/>
<div class="detail" style="float: left; width: 97%">
<table class="link_t4" width="800px" cellspacing="0" cellpadding="0" border="0" style="background-color:#ffffff;border:1px solid #D5E2F2">
<tbody>
<caption align="top">创建调查</caption>  
<tr>
<td colspan="2">
<p class="line_x" align="left">
   以下带
<font color="#FF0000">*</font>
为必填项
</p>
</td>
</tr>
<tr>
<td align="center" width="100px" height="30">
<font color="#FF0000">*</font>
调查标题
</td>
<td align="left">
<input id="txtTitle" type="text" style="width:350px;" maxlength="60" name="questionnaireTitle" value="${questionnaire.questionnaireTitle}">
(标题请控制在30个汉字以内)
</td>
</tr>
<tr>
<td align="center" height="30">
<font color="#FF0000">*</font>
调查类别
</td>
<td align="left">
<select id="type" name="questionnaireType">
<option <c:if test="${questionnaire.questionnaireType==1}">selected="selected"</c:if> value="1">深度调查</option>
<option <c:if test="${questionnaire.questionnaireType==2}">selected="selected"</c:if>  value="2">社会生活</option>
<option <c:if test="${questionnaire.questionnaireType==3}">selected="selected"</c:if> value="3">文体娱乐</option>
<option <c:if test="${questionnaire.questionnaireType==4}">selected="selected"</c:if>  value="4">科技财经</option>
<option <c:if test="${questionnaire.questionnaireType==5}">selected="selected"</c:if>  value="5">情感话题</option>
<option <c:if test="${questionnaire.questionnaireType==6}">selected="selected"</c:if>  value="6">千奇百怪</option>
</select>
</td>
</tr>
<tr>
<td align="center" valign="top" height="30"><font>&nbsp;&nbsp;</font>调查说明</td>
<td>
<textarea id="content" name="questionnaireDesc"  style="width:500px;height:400px;visibility:hidden;">
${questionnaire.questionnaireDesc}
</textarea>
	</td>
</tr>
 <tr>
<td align="center" height="30"><font>&nbsp;&nbsp;</font>终止时间</td>
<td align="left">
<input id="questionnaireEndTime"  value="<fmt:formatDate value='${questionnaire.questionnaireEndTime}' type='both' pattern='yyyy-MM-dd'/>" class="Wdate easyui-validatebox" type="text" style="width:160px;" 
onfocus="WdatePicker({isShowClear:true,readOnly:true,})" data-options="required:true" name="questionnaireEndTime">
</td>
</tr>
<tr>
<td align="center" height="60" colspan="2">
<input id="submit" type="button" name="next" onclick="CheckQuestionNaire()" value="下一步"/>
</td>
</tr>
</tbody>
</table>
</div>
</form>
</body>
</html>