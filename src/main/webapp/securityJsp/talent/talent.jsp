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
	var recommendSort="recommendSort";
	var recommendOrder="desc";
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
			title : '添加达人',
			width:700,
			height:580,
			url : sy.contextPath + '/securityJsp/talent/talentForm.jsp',
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
			title : '编辑达人',
			width:700,
			height:580,
			url : sy.contextPath + '/securityJsp/talent/talentForm.jsp?recommendUserId=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此达人吗？', function(r) {
			if (r) {
				$.post('<%=contextPath%>/mdmy/recommendUser/deleteTalent', {
					recommendUserId : id
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
		f.action = sy.contextPath + '/mdmy/recommendUser/exportRecommendTalent?'+$form.serialize()+"&sort="+recommendSort+"&order="+recommendOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/recommendUser/findAllTalent',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'recommendSort',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '150',
				title : '达人昵称',
				field : 'userNickname'
			},
			 {
				width : '200',
				title : '达人邮箱',
				field : 'userMail'
			},  
			 {
				width : '200',
				title : '个性签名',
				field : 'userSignature'
			},  
			{
				width : '110',
				title : '排序',
				field : 'recommendSort',
				sortable : true
			},  
			{
				width : '110',
				title : '推荐理由',
				field : 'recommendDesc',
			},
			 {
				title : '推荐位置',
				field : 'recommendLocation',
				width : '150',
				formatter : function(value, row) {
					var str = '';
					if(value==1){
						str="首页";
					}else{
						str="达人首页";
					}
					return str;
				}
			},  
			 {
				title : '操作',
				field : 'action',
				width : '150',
				formatter : function(value, row) {
					var str = '';
					 if(row.roleId==1){
						return "系统管理员不允许修改"; 
					 }
					 
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.recommendUserId);
					 
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.recommendUserId);
					 
					return str;
				}
			}
			] ],
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
			<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>