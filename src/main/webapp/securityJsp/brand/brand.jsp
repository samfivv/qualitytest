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
	var sortName="brandSort";
	var sortOption="asc";
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
			title : '添加品牌信息',
			width:750,
			height:580,
			url : sy.contextPath + '/securityJsp/brand/brandForm.jsp',
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
			title : '编辑品牌信息',
			width:750,
			height:580,
			url : sy.contextPath + '/mdmy/brand/findById?brandId=' + id,
			buttons : [ {
				text : '编辑',
				  handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}  
			} ]
		});
	};
	var closeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要取消此品牌吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/brand/closeBrand', {
					'brandId' : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此品牌吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/brand/deleteBrand', {
					'brandId' : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	
	var openFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要恢复此品牌吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/brand/openBrand', {
					'brandId' : id
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
		f.action = sy.contextPath + '/mdmy/brand/exportbBrand?'+$form.serialize()+"&sort="+sortName+"&order="+sortOption;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/mdmy/brand/findAll',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'brandSort',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '120',
				title : '品牌名称',
				field : 'brandTitle',
			},{
				width : '120',
				title : '状态',
				field : 'brandState',
				formatter:function(value,row){
					var str='';
					if(row.brandState==1){
						str='正常';
					}else if(row.brandState==2){
						str='取消';
					}
					return str;
				}
			},{
				width : '120',
				title : '排序',
				field : 'brandSort',
				sortable : true
			} ,{
				width : '120',
				title : '封面图片',
				field : 'brandImgUrl',
			} ,{
				width : '120',
				title : '生产企业',
				field : 'brandEnterprise',
			} ,{
				width : '150',
				title : '会员',
				field : 'userrDesc',
			} ,{
				width : '200',
				title : '创建时间',
				field : 'createTime',
				sortable : true,
				formatter:function(value,row,index){
					if(value!=null&&value!=''){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        }else{
		        	return "";
	        	}
				}
			},{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '';
					str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>',row.brandId);
					if(row.brandState==1){
						str += sy.formatString('<img class="iconImg ext-icon-table_gear" title="取消品牌" onclick="closeFun(\'{0}\');"/>', row.brandId);
					}else{
						str += sy.formatString('<img class="iconImg ext-icon-table_gear" title="恢复品牌" onclick="openFun(\'{0}\');"/>', row.brandId);
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除品牌" onclick="removeFun(\'{0}\');"/>', row.brandId);
					
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
		<form id="searchForm">
					<table>
					<tr>
					    <td>品牌名称</td>
						<td><input id="searchBox" name="brandTitle" style="width: 60px" ></input></td>
					   <td>状态</td>
						<td><select id="select" class="easyui-combobox" name="brandState" data-options="panelHeight:'auto',editable:false" style="width: 90px;">
							<option value="">请选择</option>
							<option value="1">正常</option>
							<option value="2">取消</option>
					       </select></td>
					       <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
						       <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
					    </tr>
						<tr>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
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