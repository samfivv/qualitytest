<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style type="text/css">
  #t1{
  background-color: #EEEEEE;
  }
 #t2 input{
 width:400px
 }
  
</style>
<script type="text/javascript">
var index=4;
var index2=1;
function createQuestionOption(){
	$('#t2').append('<tr><td><input type="text" name="questionnaireOptionList['+index+'].questionnaireOptionDesc"/></td><td><a href="javascript:void(0)" onclick="del(this);">删除</a></td></tr>');
	index++;
}
function del(obj)
{
	 var tr=obj.parentNode.parentNode;//得到按钮[obj]的父元素[td]的父元素[tr]
	 $(obj).innerHTML = "";
	 tr.style.display = "none";
   //tr.parentNode.removeChild(tr);//从tr的父元素[tbody]移除tr
}
function GetQueryString(name) 
{ 
var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
var r = window.location.search.substr(1).match(reg); 
if(r!=null)return unescape(r[2]); return null; 
}
 function validateQuestion(){
	 var questionnaireQuestionTitle=$('#txtTitle').val();
	 var length=document.getElementsByName("questionnaireQuestionType");
	 var questionnaireId=GetQueryString("questionnaireId");
	 var questionnaireQuestionType;
	 for(var i=0;i<length.length;i++){
		 if(length[i].checked){
			 questionnaireQuestionType=length[i].value; 
		 }
	 }
	 if(questionnaireQuestionTitle==''){
		 alert('问题标题不能为空');
		 return;
	 }
	 if($('#t2 input').length==0){
		 alert('问题至少有一个答案选项');
		 return;
	 }
	 var count=0;
	  for(var i=0;i<$('#t2 input').length;i++){
		  if(!$('#t2 input')[i].value==''){
			  count++;
		  }
	  }
	  if(count<2){
		  alert('问题选项不能少于两个');
		  return;
	  }
	  $("#questionnaireQuestionForm").submit();
 }
 function validatePubish(id){
	 var title= $('#t1 tr:first td').val();
	 if(title==null){
		 alert('发布调查前，至少设置一个问题');
	 }else{
		 window.location.href='<%=basePath %>/mdmy/questionnaireController/publishSearch/'+id;
	 }
 }
</script>
</head>
<body>
<form action="<%=basePath %>/mdmy/questionnaireController/saveQuestion/${questionnaireId}" method="post" id="questionnaireQuestionForm">
<table id="t1" cellspacing="0" cellpadding="0" width="550px" align="left" border="0">
  <c:forEach items="${lists }" var="question" varStatus="status">
                  <tr align="left"><td height="25"><font color="red">${ status.index + 1}、${question.questionnaireQuestionTitle }</font></td></tr> 
                  <c:forEach items="${question.questionnaireOptionList}" var="questionnaireOption" varStatus="qto">
                  <tr align="left"><td height="25">
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   <c:if test="${qto.index==0 }">A:</c:if>
                   <c:if test="${qto.index==1 }">B:</c:if>
                   <c:if test="${qto.index==2 }">C:</c:if>
                   <c:if test="${qto.index==3 }">D:</c:if>
                   <c:if test="${qto.index==4 }">E:</c:if>
                   <c:if test="${qto.index==5 }">F:</c:if>
                   <c:if test="${qto.index==6 }">G:</c:if>
                   <c:if test="${qto.index==7 }">H:</c:if>
                   <c:if test="${qto.index==8 }">I:</c:if>
                   <c:if test="${qto.index==9 }">J:</c:if>
                   <c:if test="${qto.index==10}">K:</c:if>
                   <c:if test="${qto.index==11}">L:</c:if>
                   <c:if test="${qto.index==12}">M:</c:if>
                   <c:if test="${qto.index==13}">N:</c:if>
                   <c:if test="${qto.index==14}">O:</c:if>
                   <c:if test="${qto.index==15}">P:</c:if>
                   <c:if test="${question.questionnaireQuestionType==1}">
                    <input type="radio" name="${ status.index + 1}">
                   </c:if>
                    <c:if test="${question.questionnaireQuestionType==2}">
                    <input type="checkbox" name="${ status.index + 1}"/>
                   </c:if>
				      ${questionnaireOption.questionnaireOptionDesc}<br> 
				      </td></tr>
                  </c:forEach>
  </c:forEach>
</table>
<div class="detail" style="float: left; width: 97%">
   <table  class="link_t4" width="550px" cellspacing="0" cellpadding="0" border="0" style="background-color:#ffffff;border:1px solid #D5E2F2">
    <tbody>
	<caption align="top" style="background-color:#D5E2F2;">设置问题</caption>  
	<tr>
	<td align="right" height="30">问题标题：</td>
	<td align="left">
	<input id="txtTitle" class="n_list" type="text" style="width:450px;" maxlength="400" name="questionnaireQuestionTitle">
	</td>
	</tr>
	<tr>
	<td align="right" height="30">
	<p class="yinput_l">问题类别：</p>
	</td>
	<td align="left">
	<label>
	<input id="radtype1" type="radio" checked="checked" value="1" name="questionnaireQuestionType">
	单选
	</label>
	<label>
	<input id="radtype2" type="radio" value="2" name="questionnaireQuestionType">
	复 选
	</label>
	</td>
	</tr>
	<tr>
	<td align="right" style="vertical-align:top;">
	<p class="yinput_l">问题选项：</p>
	</td>
	<td>
	<p>
	<a id="addanswer" onclick="createQuestionOption();" href="javascript:void(0)">添加选项</a>
	</p>
	</td>
	</tr>
	<tr>
	<td></td>
	<td>
	<table id="t2" class="t2" width="450px" cellspacing="0" cellpadding="0" border="0" style="background-color:#ffffff;border:1px solid #D5E2F2">
	<tr><td><input type="text" name="questionnaireOptionList[0].questionnaireOptionDesc"/></td><td><a style="cursor:hand" href="javascript:void(0)" onclick="del(this);">删除</a></td></tr>
	<tr><td><input type="text" name="questionnaireOptionList[1].questionnaireOptionDesc"/></td><td><a style="cursor:hand" href="javascript:void(0)" onclick="del(this);">删除</a></td></tr>
	<tr><td><input type="text" name="questionnaireOptionList[2].questionnaireOptionDesc"/></td><td><a style="cursor:hand" href="javascript:void(0)" onclick="del(this);">删除</a></td></tr>
	<tr><td><input type="text" name="questionnaireOptionList[3].questionnaireOptionDesc"/></td><td><a style="cursor:hand" href="javascript:void(0)" onclick="del(this);">删除</a></td></tr>
	</table>
	</td>
	</tr>
	<tr>
	<td></td>
	<td><input type="button" value="确定" onclick="validateQuestion();" name="que"/></td>
	</tr>
  </tbody>
  </table>
  <div style="text-align:center;width:550px;background-color:#D5E2F2;height:30px;">
	<input id="publish" type="button"  onclick="validatePubish('${questionnaireId}')" value="发布调查"  name="publish">
	<input id="update" type="button" onclick="window.location.href='<%=basePath %>/mdmy/questionnaireController/findQuestionnaire/${questionnaireId}';" value="修改调查"  name="Button1">
  </div>
 </div>
</form>
</body>
</html>