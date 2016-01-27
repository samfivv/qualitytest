<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	var disableFun = function(userName) {
		parent.$.messager.confirm('询问', '您确定将此用户加入黑名单吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/userController/disableUser', {
					'userName' : userName
				}, function(res) {
					if(res.success){
						grid.datagrid('reload');
					}
				}, 'json');
			}
		});
	};
	var OpenFun = function(userName) {
		parent.$.messager.confirm('询问', '您确定要开启此用户吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/userController/openUser', {
					'userName' : userName
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	//导出
	var exportFun = function(){
		var $form = $("#searchForm");
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/userController/exportSyserrorUser?'+$form.serialize();
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/mdmy/userController/findErrorUser',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'userRegisterTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			frozenColumns : [ [
			    {
				width : '180',
				title : '邮箱',
				field : 'userMail',
			},{
				width : '100',
				title : '姓名',
				field : 'userName',
			}, 
			 {
				width : '100',
				title : '昵称',
				field : 'userNickname',
			},] ],
			columns : [ [ {
				width : '100',
				title : '用户QQ',
				field : 'userQq',
			},  {
				width : '100',
				title : '用户状态',
				field : 'userState',
				formatter : function(value, row) {
					var str = '';
						if(row.userState==1){
							str = "正常";	
						}else{
							str = "黑名单";
						}
					return str;
				}
			},
			 {
				width : '100',
				title : '邮箱状态',
				field : 'userMailState',
				formatter : function(value, row) {
					var str = '';
						if(row.userMailState==1){
							str = "未验证";	
						}else{
							str = "已验证";
						}
					return str;
				}
			}, {
				width : '100',
				title : '性别',
				field : 'userSex',
				formatter : function(value, row) {
					var str = '';
						if(row.userSex==0){
							str = "未填写";	
						}else if(row.userSex==1){
							str = "男";
						}else if(row.userSex==2){
							str = "女";
						}
					return str;
				}
			
			},{
				width : '130',
				title : '上次登录时间',
				field : 'userLastLoginTime',
				sortable : true,
				formatter:function(value,row,index){
					var unixTimestamp = new Date(value);
		            return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        }
			}, 
			{
				width : '130',
				title : '用户注册时间',
				field : 'userRegisterTime',
				sortable : true,
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        }
			}  ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td>
							
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>