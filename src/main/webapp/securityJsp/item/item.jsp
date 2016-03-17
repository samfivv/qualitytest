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
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加项目信息',
			url : sy.contextPath + '/securityJsp/item/itemForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu);
				}
			} ]
		});
	};
	var showFun = function(itemId) {
		var dialog = parent.sy.modalDialog({
			title : '查看项目信息',
			url : sy.contextPath + '/securityJsp/item/itemForm.jsp?itemId=' + itemId
		});
	};
	var editFun = function(itemId) {
		var dialog = parent.sy.modalDialog({
			title : '编辑项目信息',
			url : sy.contextPath + '/securityJsp/item/itemForm.jsp?itemId=' + itemId,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu);
				}
			} ]
		});
	};
	var removeFun = function(itemId) {
		parent.$.messager.confirm('询问', '您确定要删除此项目吗？', function(r) {
			if (r) {
				$.post('<%=contextPath%>/mdmy/item/deleteItem', {
					itemId : itemId
				}, function(result) 
				{
					if(!result.success)
						{parent.$.messager.alert('提示', result.msg, 'info');}
					else
						{grid.treegrid('reload');
					     parent.mainMenu.tree('reload');}
				}, 'json');
			}
		});
	};
	
	var redoFun = function() {
		var node = grid.treegrid('getSelected');
		if (node) {
			grid.treegrid('expandAll', node.itemId);
		} else {
			grid.treegrid('expandAll');
		}
	};
	var undoFun = function() {
		var node = grid.treegrid('getSelected');
		if (node) {
			grid.treegrid('collapseAll', node.itemId);
		} else {
			grid.treegrid('collapseAll');
		}
	};
	$(function() {
		grid = $('#grid').treegrid({
			title : '',
			url : '<%=contextPath%>/mdmy/item/findItemAll',
			idField : 'itemId',
			treeField : 'itemName',
			parentField : 'parentId',
			rownumbers : true,
			pagination : false,
			sortName : 'itemSort',
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '300',
				title : '项目名称',
				field : 'itemName'
			} ] ],
			columns : [ [{
				width : '100',
				title : '费用',
				field : 'itemPrice'
			},  {
				width : '50',
				title : '排序',
				field : 'itemSort'
			}, {
				width : '200',
				title : '备注',
				field : 'itemNote',
			},{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '';
						str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.itemId);
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.itemId);
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.itemId);
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(row, param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
			},
			onSortColumn:function(sort,order){
				recommendSort=sort;
				recommendOrder=order;
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_next'">展开</a><a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_previous'">折叠</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="grid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>