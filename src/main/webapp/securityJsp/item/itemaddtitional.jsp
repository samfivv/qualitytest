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
	var logSort="addtitionalName";
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
			title : '添加附加项目',
			url : sy.contextPath + '/securityJsp/item/itemaddtitionalForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑附加项目',
			url : sy.contextPath + '/securityJsp/item/itemaddtitionalForm.jsp?addtitionalId=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此附加项目？', function(r) {
			if (r) {
				$.post('<%=contextPath%>/mdmy/itemaddtitional/deleteItemAddtitional', {
					addtitionalId : id
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
		f.action = sy.contextPath + '/mdmy/sysConfig/exportSysLog?'+$form.serialize()+"&sort="+logSort+"&order="+logOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : "<%=contextPath%>/mdmy/itemaddtitional/findItemAddtitionalAll",
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'itemName',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ 
			{
				width : '250',
				title : '检测项目',
				field : 'itemName'
			} ,
			{
				width : '250',
				title : '附加项目',
				field : 'addtitionalName',
				sortable : true
			} ,
			{
				width : '50',
				title : '价格',
				field : 'addtitionalPrice',
				sortable : true
			} ,
			{
				width : '130',
				title : '创建时间',
				field : 'createTime',
				sortable : true,
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        },
			} , {
				title : '操作',
				field : 'action',
				width : '193',
				formatter : function(value, row) {
					var str = '';
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.addtitionalId);
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.addtitionalId);
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
								<td>检测项目</td>
								<td><input name="itemName"  style="width: 80px;sortable : true" /></td>
								<td>附加项目</td>
									<td><input name="addtitionalName" style="width: 80px;" />
										</td>
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
							<!-- <td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td>
 -->						</tr>
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