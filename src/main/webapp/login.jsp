<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>质检平台管理系统登录</title>
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
if(window !=top){  
    top.location.href=location.href;  
}  
	$(function() {

		var loginFun = function() {
			var $form = $("#loginForm");
			if ($form.length == 1 && $form.form('validate')) {
				$('#loginBtn').linkbutton('disable');
				$.post(sy.contextPath + '/mdmy/operatorController/login', $form.serialize(), function(result) {
					if (result.success) {
						location.replace(sy.contextPath + '/index.jsp');
					} else {
						$.messager.alert('提示', result.msg, 'error', function() {
							$('#loginBtn').linkbutton('enable');
						});
					}
				}, 'json');
			}
		};

		$('#loginDialog').show().dialog({
			modal : false,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [{
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('form :input:first').focus();
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		});

		$('#userLoginCombobox').combobox({
			url : sy.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_loginNameComboBox.sy',
			valueField : 'loginname',
			textField : 'loginname',
			required : true,
			panelHeight : 'auto',
			mode : 'remote',
			delay : 500
		});


	});
	var isOpenCal=false;
	function cheackCal(e){
		var isie = (document.all) ? true : false;//判断是IE内核还是Mozilla 
		var event; 
		if (isie) 
			event = window.event.keyCode;//IE使用windows.event事件 
		else 
		{ 
			event = e.which;//3个按键函数有一个默认的隐藏变量，这里用e来传递。e.which给出一个索引值给Mo内核（注释1） 
		} 
		var capsLockKey = event.keyCode ? event.keyCode : event.which;
		alert(event)
		var shifKey = event.shiftKey ? event.shiftKey:((capsLockKey == 16) ? true : false);
		if(((capsLockKey >= 65 && capsLockKey <= 90) && !shifKey)||((capsLockKey >= 97 && capsLockKey <= 122) && shifKey)){
			isOpenCal=true;
		}else{
			isOpenCal = false;
		}
	}
	document.getElementById("passWordId").onkeypress = cheackCal; 
	$.extend($.fn.validatebox.defaults.rules, {
		uppercaseOpen : {/**大写锁定*/
			validator : function(e) {
				return isOpenCal;
			},
			message : '大写锁定已打开！'
		}
	});
	
	


</script>
</head>
<body>
	<div id="loginDialog" title="系统登录" style="display: none; width: 320px; height: 150px; overflow: hidden;">
			<div style="overflow: hidden; padding: 10px;">
				<form method="post" class="form" id="loginForm">
					<table class="table" style="width: 100%; height: 100%;">
						<tr>
							<th width="50">登录名</th>
							<td><input name="operatorName" class="easyui-validatebox" data-options="required:true" style="width: 210px;" /></td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input id="passWordId" name="operatorPassword" type="password" class="easyui-validatebox" data-options="required:true,validType:'uppercaseOpen'" style="width: 210px;" /></td>
						</tr>
					</table>
				</form>
			</div>
	</div>
</body>
</html>