<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String contextPath = request.getContextPath();
String id=request.getParameter("bottomPageId");
if(id==null){
	id="";
}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" src="<%=contextPath%>/jslib/plupload-2.0.0/js/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/jslib/plupload-2.0.0/js/i18n/zh_CN.js"></script>
 <link rel="stylesheet" href="<%=contextPath %>/jslib/kindeditor-4.1.7/themes/default/default.css" />
		<script charset="utf-8" src="<%=contextPath %>/jslib/kindeditor-4.1.7/kindeditor-min.js"></script>
		<script charset="utf-8" src="<%=contextPath %>/jslib/kindeditor-4.1.7/lang/zh_CN.js"></script>
		<script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="pageContent"]', {
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
	if($('#content').val()==null || $('#content').val()==''){
		$pjq.messager.alert('提示', '内容不能为空', 'info');
		return;
	}
	var url;
	 if ($(':input[name="bottomPageId"]').val().length > 0) {
		url = '<%=contextPath%>/mdmy/bottomPage/updateBottomPage';
	} else {
		url = '<%=contextPath%>/mdmy/bottomPage/saveBottomPage';
	} 
	$.post(url, sy.serializeObject($('form')), function(result) {
		parent.sy.progressBar('close');//关闭上传进度条
        if(url=='<%=contextPath%>/mdmy/bottomPage/saveBottomPage'){
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

</script>
</head>
<body>
	<form method="post" class="form" >
		<input name="bottomPageId" value="<%=id %>" type="hidden"/>
		<fieldset>
			<legend>页脚详情</legend>
			<table class="table" style="width: 100%;">
					<tr>
					<th>页脚标题</th>
					<td><input name="pageTitle" class="easyui-validatebox" data-options="required:true" style="width: 155px;" value="${bottomPage.pageTitle }"/></td>
					</tr>
					<tr>
					<th>横向位置</th>
					<td><input name="horizontalPosition" class="easyui-numberspinner" data-options="required:true,min:1,max:100000,editable:true" style="width: 155px;" value="${bottomPage.horizontalPosition}" /></td>
					</tr>
					<tr>
					<th>竖向位置</th>
					<td><input name="verticalPosition" class="easyui-numberspinner" data-options="required:true,min:1,max:100000,editable:true" style="width: 155px;" value="${bottomPage.verticalPosition}" /></td>
			        </tr>
			        <tr>
					<th>页脚内容</th>
					<td><textarea id="content" name="pageContent"  style="width:580px;height:280px;visibility:hidden;">${bottomPage.pageContent }</textarea></td>
			        </tr>
			</table>
		</fieldset>
	</form>
</body>
</html>