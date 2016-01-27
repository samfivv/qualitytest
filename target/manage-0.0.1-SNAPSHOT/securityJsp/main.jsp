<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>米芽网后台管理系统</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
	var mainMenu;
	var mainTabs;

	$(function() {

		var loginFun = function() {
			if ($('#loginDialog form').form('validate')) {
				$('#loginBtn').linkbutton('disable');
				$.post(sy.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_login.sy', $('#loginDialog form').serialize(), function(result) {
					if (result.success) {
						$('#loginDialog').dialog('close');
					} else {
						$.messager.alert('提示', result.msg, 'error', function() {
							$('#loginDialog form :input:eq(1)').focus();
						});
					}
					$('#loginBtn').linkbutton('enable');
				}, 'json');
			}
		};
		$('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('#loginDialog form :input[name="data.pwd"]').val('');
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		}).dialog('close');

		$('#passwordDialog').show().dialog({
			modal : true,
			closable : true,
			iconCls : 'ext-icon-lock_edit',
			buttons : [ {
				text : '修改',
				handler : function() {
					if ($('#passwordDialog form').form('validate')) {
						$.post(sy.contextPath + '/mdmy/operatorController/updatePassword', {
							'oldPwd' : $('#pwd1').val(),
							'newPwd' : $('#pwd').val()
						}, function(result) {
							if (result.success) {
								$.messager.alert('提示', '密码修改成功！', 'info');
								$('#passwordDialog').dialog('close');
							}else{
								$.messager.alert('提示', '旧密码错误！', 'info');
								$('#passwordDialog').dialog('close');
							}
						}, 'json');
					}
				}
			} ],
			onOpen : function() {
				$('#passwordDialog form :input').val('');
			}
		}).dialog('close');

		mainMenu = $('#mainMenu').tree({
			url : sy.contextPath + '/mdmy/operatorController/findPermissionByOperatorId',
			parentField : 'pid',
			onClick : function(node) {
				if (node.attributes.url) {
					var src = sy.contextPath + node.attributes.url;
					if (!sy.startWith(node.attributes.url, '/')) {
						src = node.attributes.url;
					}
					if (node.attributes.target && node.attributes.target.length > 0) {
						window.open(src, node.attributes.target);
					} else {
						var tabs = $('#mainTabs');
						var opts = {
							title : node.text,
							closable : true,
							iconCls : node.iconCls,
							content : sy.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', src),
							border : false,
							fit : true
						};
						if (tabs.tabs('exists', opts.title)) {
							tabs.tabs('select', opts.title);
						} else {
							tabs.tabs('add', opts);
						}
					}
				}
			}
		});

		$('#mainLayout').layout('panel', 'center').panel({
			onResize : function(width, height) {
				sy.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
			}
		});

		mainTabs = $('#mainTabs').tabs({
			fit : true,
			border : false,
		});

	});
</script>
</head>
<body id="mainLayout" class="easyui-layout">
	<div data-options="region:'north',href:'<%=basePath%>/securityJsp/north.jsp'"  style="height: 70px; overflow: hidden;" class="logo"></div>
	<div data-options="region:'west',href:'',split:true" title="导航" style="width: 200px; padding: 10px;overflow: hidden;overflow-y:auto;">
		<ul id="mainMenu"></ul>
	</div>
	<div data-options="region:'center'" style="overflow: hidden;">
		<div id="mainTabs" style="overflow: hidden;">
			<div title="欢迎" data-options="iconCls:'ext-icon-heart'" style="overflow: hidden;">
				<iframe src="<%=basePath%>/welcome.jsp" allowTransparency="true" style="border: 0; width: 100%; height: 99%;" frameBorder="0"></iframe>
			</div>
		</div>
	</div>
	<div data-options="region:'south',href:'<%=basePath%>/securityJsp/south.jsp',border:false" style="height: 30px; overflow: hidden;"></div>

	<div id="loginDialog" title="解锁登录" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th width="50">登录名</th>
					<td>${_loginOperator.operatorName}<input name="operatorName" readonly="readonly" type="hidden" value="${_loginOperator.operatorName}" /></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input name="data.pwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="passwordDialog" title="修改密码" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
			    <tr>
					<th>旧密码</th>
					<td><input id="pwd1" name="oldPwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>新密码</th>
					<td><input id="pwd" name="newPwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>重复密码</th>
					<td><input  type="password" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#pwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>