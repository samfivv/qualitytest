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
	var userSort="createTime";
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
			url : sy.contextPath + '/mdmy/user/findAllUsers',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : false,
			idField : 'userId',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			frozenColumns : [ [
                {
                	field:'checkbox',
                	checkbox: true },               
			    {
                		width : '100',
        				title : '手机号',
        				field : 'userPhone',
			},
            {
				width : '100',
				title : '名称',
				field : 'userDesc',
			},
			 {
				width : '100',
				title : '简称',
				field : 'userSort',
			},
			{
				width : '200',
				title : '邮箱',
				field : 'userMail',				
			},
			  ] ],
			columns : [ [ {
				width : '100',
				title : '会员编号',
				field : 'userNo',
			},  {
				width : '100',
				title : '状态',
				field : 'userState',
				formatter : function(value, row) {
					var str = '';
						if(row.userState==1){
							str = "未认证";	
						}else if(row.userState==2){
							str = "已认证";
						}
					return str;
				}
			},  {
				width : '80',
				title : '会员类型',
				field : 'userType',
				formatter : function(value, row) {
				var str = '';
    			  if(row.userType==1){
					 str='企业';
				 }else if(row.userType==2){
					 str='个人';
			 	}else {
					 str='其它';
			 	}
			 	return str;	
		      }
			},
			 {
				width : '80',
				title : '是否VIP',
				field : 'userVip',
				formatter : function(value, row) {
				var str = '';
			  if(row.userVip==false){
				 str='否';
			 }else if(row.userVip==true){
				 str='是';
			 } 
			 return str;	
		      }
			},{
				width : '150',
				title : '注册时间',
				field : 'createTime',
				sortable : true,
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        }
			},{
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
								<td>手机号</td>
								<td><input name="userPhone" style="width: 80px;" /></td>
								<td>ID号</td>
								<td><input name="userNo" style="width: 80px;" /></td>
								<td>邮箱</td>
								<td><input name="userMail" style="width: 80px;" /></td>
								<td>简称</td>
								<td><input name="userSort" style="width: 80px;" /></td>
				                <td>用户状态</td>
								<td><select name="userState" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">未认证</option>
										<option value="2">已认证</option></select></td>
								<td>类型</td>
								<td><select name="userType" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">企业</option>
										<option value="2">个人</option>
										<option value="0">其它</option></select></td>
								
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