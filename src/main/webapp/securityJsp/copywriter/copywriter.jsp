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
	var categorySort="copywriterSort";
	var categoryOrder="asc";
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
			title : '添加文案信息',
			width:700,
			height:580,
			url : sy.contextPath + '/securityJsp/copywriter/copywriterForm.jsp',
			buttons : [ {
				text : '添加',
				id:'addButton',
				    handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);  
				}    
			} ]  
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑文案信息',
			width:700,
			height:580,
			url : sy.contextPath + '/securityJsp/copywriter/copywriterForm.jsp?copywriterId=' + id,
			buttons : [ {
				text : '编辑',
				  handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}  
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此文案？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/copyriter/delete', {
					'copywriterId' : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var exportFun = function(){
		var $form = $("#searchForm");
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/copywriter/exportCopywriter?'+$form.serialize()+"&sort="+categorySort+"&order="+categoryOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/mdmy/copywriter/findAll',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'copywriterSort',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '120',
				title : '文案描述',
				field : 'copywriterDesc',
			},{
				width : '180',
				title : '文案跳转链接',
				field : 'copywriterUrl',
			},{
				width : '480',
				title : '文案图片链接',
				field : 'copywriterImgUrl',
			},{
				width : '120',
				title : '文案顺序',
				field : 'copywriterSort',
				formatter : function(value, row) {
					var str = '';
						if(row.copywriterSort==5){
							str = "不显示";	
						}else if(row.copywriterSort==1){
							str="1";
						} else if(row.copywriterSort==2){
							str="2";
						} else if(row.copywriterSort==3){
							str="3";
						} else if(row.copywriterSort==4){
							str="4";
						} 
					return str;
				},
				sortable : true
			} ,{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '';
					str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\',\'{1}\');"/>',row.copywriterId);
					str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.copywriterId);
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
				categorySort=sort;
				categoryOrder=order;
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
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td>
						</tr>
					</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>