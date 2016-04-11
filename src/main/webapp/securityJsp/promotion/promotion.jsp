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
	var sortName="promotionSort";
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
			title : '添加促销信息',
			width:750,
			height:580,
			url : sy.contextPath + '/securityJsp/promotion/promotionForm.jsp',
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
			title : '编辑促销信息',
			width:750,
			height:580,
			url : sy.contextPath + '/mdmy/promotion/findById?promotionId=' + id,
			buttons : [ {
				text : '编辑',
				  handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}  
			} ]
		});
	};
	var closeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要取消此促销吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/promotion/closePromotion', {
					'promotionId' : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此促销吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/promotion/deletePromotion', {
					'promotionId' : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	
	var openFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要恢复此促销吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/promotion/openPromotion', {
					'promotionId' : id
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
		f.action = sy.contextPath + '/mdmy/promotion/exportPromotion?'+$form.serialize()+"&sort="+sortName+"&order="+sortOption;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/mdmy/promotion/findAll',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'promotionSort',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '120',
				title : '促销标题',
				field : 'promotionTitle',
			},{
				width : '120',
				title : '状态',
				field : 'promotionState',
				formatter:function(value,row){
					var str='';
					if(row.promotionState==1){
						str='正常';
					}else if(row.promotionState==2){
						str='取消';
					}
					return str;
				}
			},{
				width : '120',
				title : '促销排序',
				field : 'promotionSort',
				sortable : true
			} ,{
				width : '120',
				title : '封面图片',
				field : 'promotionImgUrl',
			} ,{
				width : '150',
				title : '开始时间',
				field : 'promotionStartTime',
				sortable : true,
				formatter:function(value,row,index){
					if(value!=null&&value!=''){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd");
		        }else{
		        	return "";
	        	}
				}
			} ,{
				width : '150',
				title : '结束时间',
				field : 'promotionEffTime',
				sortable : true,
				formatter:function(value,row,index){
					if(value!=null&&value!=''){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd");
		        }else{
		        	return "";
	        	}
				}
			} ,{
				width : '150',
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
					str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>',row.promotionId);
					if(row.promotionState==1){
						str += sy.formatString('<img class="iconImg ext-icon-table_gear" title="取消促销" onclick="closeFun(\'{0}\');"/>', row.promotionId);
					}else{
						str += sy.formatString('<img class="iconImg ext-icon-table_gear" title="恢复促销" onclick="openFun(\'{0}\');"/>', row.promotionId);
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除促销" onclick="removeFun(\'{0}\');"/>', row.promotionId);
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
					    <td>促销标题</td>
						<td><input id="searchBox" name="promotionTitle" style="width: 60px" ></input></td>
					   <td>促销状态</td>
						<td><select id="select" class="easyui-combobox" name="promotionState" data-options="panelHeight:'auto',editable:false" style="width: 90px;">
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