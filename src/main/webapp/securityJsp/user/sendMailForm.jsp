<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.user.model.User"%>

<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<link rel="stylesheet" href="<%=contextPath %>/jslib/kindeditor-4.1.7/themes/default/default.css" />
		<script charset="utf-8" src="<%=contextPath %>/jslib/kindeditor-4.1.7/kindeditor-min.js"></script>
		<script charset="utf-8" src="<%=contextPath %>/jslib/kindeditor-4.1.7/lang/zh_CN.js"></script>
		<script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					 uploadJson : '<%=contextPath%>/mdmy/subject/plupload',
					allowFileManager : true,
					afterBlur: function(){this.sync();},
					minWidth : '580px',
					 items:[
					        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
					        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
					        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
					        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
					        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					        'italic', 'underline', 'strikethrough', 'lineheight', '|', 'image',
					         'table', 'hr', 'emoticons', 'pagebreak',
					        'anchor', 'link', 'unlink', '|', 'about']
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
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
				url = '<%=contextPath%>/mdmy/userController/sendMailtoUser';
				var state=$('#checkState').val();
				if(state==3){
					 var reg=/^(([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})\s*;\s*)*(([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2}))$/;
						var mail=$('#mail').val();
						if(mail!=''||mail==null){
							if(!reg.test(mail)){
								alert('邮箱格式错误，请按照正确的格式填写')
								return false;
							}
						}
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
		 $("#mail").attr("class","");
		 $('#selected').combobox({
				onChange:function(n,o){
					if(n==3){
						$("#hidden").show();
						$('#mail').attr("class","easyui-validatebox");
						$("#mail").focus();
					}else{
						$("#hidden").hide();
						$("#mail").attr("class","");
					}
				}
			})
	}); 
	 function checkmail(){
		 var reg=/^(([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})\s*;\s*)*(([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2}))$/;
			var mail=$('#mail').val();
			if(reg.test(mail)){
				return true;
			}else{
				alert('请按照正确的格式填写')
				return false;
			}
	 }
</script>
</head>
<body>
	<form method="post" class="form">
	<input type="hidden" name="userLists" value="${userLists}">
		<fieldset>
			<legend>发送邮件</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>接收人</th>
					<td><select id="selected" class="easyui-combobox" name="checkState" data-options="panelHeight:'auto',editable:false" style="width: 100px;">
							<option value="1">选中用户</option>
							<option value="2">全部用户</option>
							<option value="3">自己填写</option>
					</select></td>
				</tr>
				<tr id="hidden" style="display: none;">
				<th>邮箱</th>
				<td><input id="mail" name="userMail" class="easyui-validatebox" onblur="checkmail()" data-options="required:true" style="width: 500px;"/></td>
				<td>请用分号";"隔开</td>
				</tr>
				<tr>
				<th>邮箱标题</th>
				<td><input id="mailTitle" name="userMailTitle" class="easyui-validatebox" data-options="required:true" style="width: 500px;"/></td>
				</tr>
				<tr>
					<th>发送内容</th>
				    <td>
				   <textarea id="content" name="content"  style="width:500px;height:350px;visibility:hidden;"></textarea>
				    </td>
				   </tr>
			</table>
		</fieldset>
	</form>
</body>
</html>