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
	var logSort="createTime";
	var logOrder="desc";
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

	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加达人专访信息',
			width:700,
			height:580,
			url : sy.contextPath + '/securityJsp/application/userInterviewForm.jsp',
			buttons : [ {
				text : '草稿',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$,1);
				}
			} ,{
				text : '发布',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$,2);
				}
			} 
			]
		});
	};
	var showFun = function(coverImgUrl) {
		var dialog = parent.sy.modalDialog({
			title : '查看达人专访信息',
			url :sy.contextPath + '/securityJsp/application/checkUserInterviewImgUrl.jsp?coverImgUrl=' + coverImgUrl
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑达人专访信息',
			width:700,
			height:580,
			url : sy.contextPath + '/mdmy/userInterview/findUserInterviewById?userInterviewId=' + id,
			buttons : [ {
				text : '草稿',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$,1);
				}
			},{
				text : '发布',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$,2);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post('<%=contextPath%>/mdmy/userInterview/deleteUserInterview', {
					userInterviewId : id
				}, function(result) {
					if(result.success){
						parent.$.messager.alert('提示', result.msg, 'info');
					}else{
						parent.$.messager.alert('提示', result.msg, 'error');
					}
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
		f.action = sy.contextPath + '/mdmy/sysConfig/exportSysLog?'+$form.serialize()+"&sort="+logSort+"&order="+logOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : "<%=contextPath%>/mdmy/userInterview/findAllUserInterview",
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ 
			{
				width : '130',
				title : '标题',
				field : 'userInterviewTitle'
			} ,
			{
				width : '130',
				title : '简介',
				field : 'userInterviewAbstract'
			} ,
			/* {
				width : '130',
				title : '详情内容',
				field : 'userInterviewContent'
			} , */
			{
				width : '130',
				title : '创建人昵称',
				field : 'createOperatorName'
			} ,
			{
				width : '130',
				title : '修改人昵称',
				field : 'updateOperatorName'
			} ,
			{
				width : '130',
				title : '排序',
				field : 'userInterviewSort',
				sortable : true,
			} ,
			{
				width : '100',
				title : '状态',
				field : 'userInterviewState',
				formatter : function(value, row) {
					var str = '';
						if(row.userInterviewState==1){
							str = "草稿";	
						}else if(row.userInterviewState==2){
							str = "发布";
						}else if(row.userInterviewState==3){
							str = "删除";
						}
					return str;
				}
			}, 
			{
				width : '130',
				title : '创建时间',
				field : 'createTime',
				sortable : true,
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        },
			} ,
			{
				width : '130',
				title : '修改时间',
				field : 'updateTime',
				sortable : true,
				formatter:function(value,row,index){
					if(value==null||value==''){
						return "";
					}
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        },
			} ,
			{
				title : '操作',
				field : 'action',
				width : '193',
				formatter : function(value, row) {
					var str = '';
					    //str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.coverImgUrl); 
					 
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.userInterviewId);
					 
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.userInterviewId);
					 
					return str;
				}
			} 
				 ] ],
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
				logSort=sort;
				logOrder=order;
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
		<table >
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<td>标题</td>
								<td><input name="userInterviewTitle" style="width: 80px;" /></td>
							    <td>简介</td>
								<td><input name="userInterviewAbstract"  style="width: 80px;" /></td>
								<!-- <td>创建人昵称</td>
								<td><input name="createOperatorName"  style="width: 80px;" /></td>
								<td>修改人昵称</td>
								<td><input name="updateOperatorName"  style="width: 80px;" /></td> -->
								<td>状态</td>
								<td><select id="select" class="easyui-combobox" name="userInterviewState" data-options="panelHeight:'auto',editable:false" style="width: 90px;">
									<option value="">请选择</option>
									<option value="1">草稿</option>
									<option value="2">发布</option>
									<option value="3">删除</option>
							       </select></td>
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
						<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
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