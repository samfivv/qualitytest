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
	var recommendSort="recommendCircleSort";
	var recommendOrder="asc";
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
			title : '添加推荐作品',
			width:700,
			height:580,
			url : sy.contextPath + '/securityJsp/production/productionBoutiqueForm.jsp',
			buttons : [ {
				id:'add',
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑推荐作品',
			width:700,
			height:580,
			url : sy.contextPath + '/securityJsp/production/productionBoutiqueForm.jsp?productionBoutiqueId=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此作品吗？', function(r) {
			if (r) {
				$.post('<%=contextPath%>/mdmy/production/deleteProductionBoutique', {
					productionBoutiqueId : id
				}, function(result) {
					if(result.success){
						parent.$.messager.alert('提示', result.msg, 'info');
						grid.datagrid('reload');
					}
				}, 'json');
			}
		});
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/production/findAllProductionBoutique',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'productionBoutiqueSort',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '150',
				title : '作品标题',
				field : 'productionTitle'
			},
			{
				width : '110',
				title : '排序',
				field : 'productionBoutiqueSort',
				sortable : true
			}, 
			{
				width : '100',
				title : '类型',
				field : 'productionBoutiqueType',
				formatter : function(value, row) {
					var str = '';
					if(row.productionBoutiqueType==1){
						str="精品作品";
					}else if(row.productionBoutiqueType==2){
						str="人气作品";
					}else if(row.productionBoutiqueType==3){
						str="新品展示";
					}
					return str;
				}
			} ,
			 {
				title : '操作',
				field : 'action',
				width : '150',
				formatter : function(value, row) {
					var str = '';
					 if(row.roleId==1){
						return "系统管理员不允许修改"; 
					 }
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.productionBoutiqueId);
					 
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.productionBoutiqueId);
					 
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
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
	<form id="searchForm">
		<table>
			<tr>
			<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>