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
	var userSort="userRegisterTime";
	var userOrder="desc";
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
	var disableFun = function(userId) {
		parent.$.messager.confirm('询问', '您确定将此用户加入黑名单吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/userController/disableUser', {
					'userId' : userId
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var editFun = function(userId) {
		 var dialog = parent.sy.modalDialog({
				title : '赠送财富',
				url : sy.contextPath + '/securityJsp/user/giveWealth.jsp?userId='+userId,
				buttons : [ {
					text : '赠送',
					handler : function() {
						dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
					}
				} ]
			});
	}  
	var sendMailFun = function() {
		var rows = $('#grid').datagrid('getSelections');
		var userMails="";
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				userMails=userMails+"&userMail["+i+"]="+rows[i].userMail;
			}
		}
		 var dialog = parent.sy.modalDialog({
				title : '发送邮件',
				url : sy.contextPath + '/mdmy/userController/getUsers?'+userMails,
				width:800,
				height:580,
				buttons : [ {
					text : '发送',
					handler : function() {
						dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
					}
				} ]
			});
	}  
	var sendMessageFun = function() {
		var rows = $('#grid').datagrid('getSelections');
		var userPhone="";
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				userPhone=userPhone+"&userPhone["+i+"]="+rows[i].userPhone;
			}
		}
		 var dialog = parent.sy.modalDialog({
				title : '发送短信',
				url : sy.contextPath + '/mdmy/userController/getUserPhone?'+userPhone,
				width:800,
				height:580,
				buttons : [ {
					text : '发送',
					handler : function() {
						dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
					}
				} ]
			});
	}  
	var setFun = function(userId,isInside) {
		if(isInside==0){
			parent.$.messager.confirm('询问', '您确定要将此用户设为内部员工吗？', function(r) {
				if (r) {
					$.post(sy.contextPath + '/mdmy/userController/setUser', {
						'userId' : userId
					}, function() {
							grid.datagrid('reload');
					}, 'json');
				}
			});
		}else{
			parent.$.messager.confirm('询问', '您确定要将此用户移出内部员工吗？', function(r) {
				if (r) {
					$.post(sy.contextPath + '/mdmy/userController/setUser', {
						'userId' : userId
					}, function() {
							grid.datagrid('reload');
					}, 'json');
				}
			});
		}
		
	};
	var OpenFun = function(userId) {
		parent.$.messager.confirm('询问', '您确定要开启此用户吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/userController/openUser', {
					'userId' : userId
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var exportFun = function(){
		var $form = $("#searchForm");
		//alert(JSON.stringify($form.serialize()));
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/userController/exportUser?'+$form.serialize()+'&sort='+userSort+'&order='+userOrder;
		f.submit();
	};

	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/mdmy/userController/findUsers',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : false,
			idField : 'userId',
			sortName : 'userRegisterTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			frozenColumns : [ [
                {
                	field:'checkbox',
                	checkbox: true },               
			    {
				width : '200',
				title : '邮箱',
				field : 'userMail',
			},
            {
				width : '100',
				title : '姓名',
				field : 'userName',
			},
			 {
				width : '100',
				title : '昵称',
				field : 'userNickname',
			},
			{
				width : '100',
				title : '手机号',
				field : 'userPhone',
			},
			  ] ],
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
						}else if(row.userState==2){
							str = "黑名单";
						}
					return str;
				}
			},  {
				width : '100',
				title : '邮箱状态',
				field : 'userMailState',
				formatter : function(value, row) {
					var str = '';
						if(row.userMailState==1){
							str = "未验证";	
						}else if(row.userMailState==2){
							str = "已验证";
						}
					return str;
				}
			}, {
				width : '80',
				title : '性别',
				field : 'userSex',
				formatter : function(value, row) {
				var str = '';
				 
			  if(row.userSex==0){
				 str='未填写';
			 }else if(row.userSex==1){
				 str='男';
			 }else if(row.userSex==2){
				 str='女';
			 }else{
				 str='未填写';
			 }
			 return str;	
		      }
			},
			 {
				width : '80',
				title : '是否是内部员工',
				field : 'isInside',
				formatter : function(value, row) {
				var str = '';
			  if(row.isInside==0){
				 str='否';
			 }else if(row.isInside==1){
				 str='是';
			 } 
			 return str;	
		      }
			},
			{
				width : '150',
				title : '上次登录时间',
				field : 'userLastLoginTime',
				sortable : true,
				formatter:function(value,row,index){
					if(value!=null&&value!=''){
					var unixTimestamp = new Date(value);
		            return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        }else{
		            return "";	
		        }
		        }
			},
			{
				width : '150',
				title : '用户注册时间',
				field : 'userRegisterTime',
				sortable : true,
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        }
			},
			 {
				width : '100',
				title : '来源',
				field : 'registerFrom',
				formatter : function(value, row) {
					var str = '';
						if(row.registerFrom==1){
							str = "web端";	
						}else if(row.registerFrom==2){
							str = "移动端";
						}else if(row.registerFrom==3){
							str = "微信报名";
						}
					return str;
				}
			},
			{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '';
						if(row.userState==1){
							str += sy.formatString('<img class="iconImg ext-icon-note" title="加入黑名单" onclick="disableFun(\'{0}\');"/>', row.userId);	
						}else{
							str += sy.formatString('<img class="iconImg ext-icon-note" title="启用" onclick="OpenFun(\'{0}\');"/>', row.userId);
						}
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="赠送财富" onclick="editFun(\'{0}\');"/>', row.userId);
						if(row.isInside==0){
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="设为内部员工" onclick="setFun(\'{0}\',\'{1}\');"/>', row.userId,row.isInside);
						}else{
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="移出内部员工" onclick="setFun(\'{0}\',\'{1}\');"/>', row.userId,row.isInside);
						}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				var $form = $("#searchForm");
				if(!$form.form('validate')){
					return false;
				}
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
			},
			onSortColumn:function(sort,order){
				userSort=sort;
				userOrder=order;
			}
		});
		$('form :input').keyup(function(event) {
			if (event.keyCode == 13) {
				grid.datagrid('load',sy.serializeObject($('#searchForm')));
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
								<td>邮箱</td>
								<td><input name="userMail" style="width: 80px;" /></td>
								<td>手机号</td>
								<td><input name="userPhone" style="width: 80px;" /></td>
								<td>昵称</td>
								<td><input name="userNickname" style="width: 80px;" /></td>
				                <td>用户状态</td>
								<td><select name="userState" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">正常</option>
										<option value="2">黑名单</option></select></td>
								<td>邮箱状态</td>
								<td><select name="userMailState" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">未验证</option>
										<option value="2">已验证</option></select></td>
								<td>来源</td>
								<td><select name="registerFrom" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">web端</option>
										<option value="2">移动端</option>
										<option value="3">微信报名</option>
										</select>
								</td>
								<td>性别</td>
								<td><select name="userSex" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="0">未填写</option>
										<option value="1">男</option>
										<option value="2">女</option></select></td>
								<td>上次登录时间</td>
								<td><input id="startTime1" name="lastLoginTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="lastLoginTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime1\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
								<td>注册时间</td>
								<td><input id="startTime2" name="queryBeginRegisterTimeStr" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryEndRegisterTimeStr" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime2\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
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
							<!-- <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td> -->
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="sendMailFun()">发送邮件</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="sendMessageFun()">发送短信</a></td>
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