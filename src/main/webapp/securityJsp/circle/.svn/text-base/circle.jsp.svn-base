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
	var logSort="circleSort";
	var logOrder="asc";
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
			title : '添加圈子信息',
			url : sy.contextPath + '/securityJsp/circle/circleForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看圈子信息',
			url :sy.contextPath + '/securityJsp/circle/circleForm.jsp?circleId=' + id+"&&lookInfo=true"
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑圈子信息',
			url : sy.contextPath + '/securityJsp/circle/circleForm.jsp?circleId=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var auditFun = function(id,userId) {
		var dialog = parent.sy.modalDialog({
			title : '审核信息',
			url : sy.contextPath + '/securityJsp/circle/circleAudit.jsp?circleId=' + id+'&userId='+userId,
			buttons : [ {
				text : '审核',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post('<%=contextPath%>/mdmy/circle/deleteCircleById', {
					circleId : id
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
			url : "<%=contextPath%>/mdmy/circle/findAllCircle",
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'circleSort',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ 
			{
				width : '130',
				title : '圈子Id',
				field : 'circleId'
			} ,
			{
				width : '130',
				title : '排序',
				field : 'circleSort',
				sortable : true
			} ,
			{
				width : '130',
				title : '圈子名称',
				field : 'circleName'
			} ,
			{
				width : '130',
				title : '圈子简介',
				field : 'circleDesc',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			} ,
			{
				width : '130',
				title : '人数',
				field : 'circlePepleCount'
			} ,
			{
				width : '150',
				title : '圈子状态',
				field : 'circleState',
					formatter : function(value, row) {
						var str = '';
						 if(row.circleState==1){
							 str='通过';
						 }else if(row.circleState==2){
							 str='不通过';
						 }else if(row.circleState==3){
							 str='审核中';
						 }else if(row.circleState==4){
							 str='已关闭';
						 }else if(row.circleState==5){
							 str='申诉中';
						 }
						 return str;	
					}
			},  
			{
				width : '130',
				title : '创建时间',
				field : 'circleCreateTime',
				sortable : true,
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        },
			} ,{
				width : '130',
				title : '圈子话题数量',
				field : 'circleTopicCount'
			} ,
			{
				width : '130',
				title : '不通过原因',
				field : 'unpassDesc'
			} ,
			{
				width : '130',
				title : '申述理由',
				field : 'appealReason',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			} ,
			{
				width : '130',
				title : '申述时间',
				field : 'appealTime',
				sortable : true,
				formatter:function(value,row,index){
					if(value==null){
						return "";
					}else{
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
					}
		        },
			} ,
			{
				title : '操作',
				field : 'action',
				width : '193',
				formatter : function(value, row) {
					var str = '';
					    /* str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.clubNum); */
					 
						/* str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.circleId); */
					    if(row.circleState==3||row.circleState==5){
						str += sy.formatString('<img class="iconImg ext-icon-joystick" title="审核" onclick="auditFun(\'{0}\',\'{1}\');"/>', row.circleId,row.userId);
					    }
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.circleId);
					 
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
								<td>圈子Id</td>
								<td><input name="circleId" style="width: 80px;" /></td>
							    <td>圈子名称</td>
								<td><input name="circleName"  style="width: 80px;" /></td>
								<td>圈子名称</td>
								<td>
								<select id="selected" class="easyui-combobox" name="circleState" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
								<option value="1" >通过</option>
								<option value="2">不通过</option>
								<option value="3" selected="selected">审核中</option>
								<option value="4">已关闭</option>
								<option value="5">申诉中</option>
								</select>
								</td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			 <!-- <tr>
				<td>
					<table>
						<tr>
						<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td>
						</tr>
					</table>
				</td>
			</tr>  -->
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>