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
				url = '<%=contextPath%>/mdmy/userController/sendMessageToUser';
				var state=$('#checkState').val();
				if(state==3){
					var reg=/(0?1[3|4|5|8][0-9]\d{8}\s*,\s*)*(0?1[3|4|5|8][0-9]\d{8})$/;
					var phone=$('#userPhone').val();
					if(phone!=''||phone==null){
						if(!reg.test(phone)){
							alert('手机号码格式错误，请按照正确的格式填写')
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
		 $("#userPhone").attr("class","");
		 $('#selected').combobox({
				onChange:function(n,o){
					if(n==3){
						$("#hidden").show();
						$('#userPhone').attr("class","easyui-validatebox");
						$("#userPhone").focus();
					}else{
						$("#hidden").hide();
						$("#userPhone").attr("class","");
					}
				}
			})
	}); 
	function checkphone(){
		var reg=/(0?1[3|4|5|8][0-9]\d{8}\s*,\s*)*(0?1[3|4|5|8][0-9]\d{8})$/;
		var phone=$('#userPhone').val();
		if(reg.test(phone)){
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
	<input type="hidden" name="userPhoneLists" value="${userPhone}">
		<fieldset>
			<legend>发送短信</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>接收人</th>
					<td><select id="selected" class="easyui-combobox" name="checkState" data-options="panelHeight:'auto',editable:false" style="width: 100px;">
							<option value="1">选中用户</option>
							<option value="2">全部用户</option>
							<option value="3">自己填写</option>
					</select></td>
				</tr>
				<tr id="hidden" style="display: none">
				<th>电话号码</th>
				<td><input id="userPhone" name="userPhone" onblur="checkphone()" class="easyui-validatebox" data-options="required:true" style="width: 500px;"/></td>
				<td>请用逗号","隔开</td>
				</tr>
				<tr>
					<th>发送内容</th>
				    <td>
				   <textarea id="content" name="content"  style="width:500px;height:400px;visibility:hidden;" style="width: 500px;"></textarea>
				    </td>
				   </tr>
			</table>
		</fieldset>
	</form>
</body>
</html>