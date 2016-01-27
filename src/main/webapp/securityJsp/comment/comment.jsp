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
	var commentSort="commentCreateTime";
	var commentOrder="desc";
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
	var disableFun = function(commentId) {
		parent.$.messager.confirm('询问', '您确定要屏蔽此评论吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/comment/disableComment', {
					'commentId' : commentId
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var OpenFun = function(commentId) {
		parent.$.messager.confirm('询问', '您确定要取消屏蔽此评论吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/comment/openComment', {
					'commentId' : commentId
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
		f.action = sy.contextPath + '/mdmy/comment/exportComment?'+$form.serialize()+"&sort="+commentSort+"&order="+commentOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/comment/findAll',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'commentCreateTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '250',
				title : '专辑ID',
				field : 'interestId',
			},  {
				width : '150',
				title : '专辑标题',
				field : 'interestTitle',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			 {
				width : '150',
				title : '评论内容',
				field : 'commentContent',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '150',
				title : '评论人',
				field : 'commentUserNickname'
			},  
			
			{
				width : '120',
				title : '评论状态',
				field : 'commentState',
					formatter : function(value, row) {
						var str = '';
						   if(row.commentState==1){
							 str='通过';
						 }else if(row.commentState==2){
							 str='违规评论，不显示';
						 }
						 return str;	
					}
			}, 
			{
				width : '150',
				title : '评论时间',
				field : 'commentCreateTime',
				sortable : true,
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        }
			},
			 {
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '&nbsp;&nbsp;';
					if(row.commentState==1){
						str += sy.formatString('<img class="iconImg ext-icon-note" title="屏蔽" onclick="disableFun(\'{0}\');"/>', row.commentId);	
					}else{
						str += sy.formatString('<img class="iconImg ext-icon-note" title="取消屏蔽" onclick="OpenFun(\'{0}\');"/>', row.commentId);
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
				commentSort=sort;
				commentOrder=order;
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
			 <td>&nbsp;&nbsp;&nbsp;专辑&nbsp;ID</td>
				<td><input id="searchBox" name="interestId" style="width: 60px" ></input></td>
			    <td>专辑标题</td>
				<td><input id="searchBox" name="interestTitle" style="width: 60px" ></input></td>
				<td>评论内容</td>
				<td><input id="searchBox" name="commentContent" style="width: 60px" ></input></td>
				<td>评论人</td>
				<td><input id="searchBox" name="commentUserNickname" style="width: 60px" ></input></td>
				<td>状态</td>
					<td><select id="selected" class="easyui-combobox" name="commentState" data-options="panelHeight:'auto',editable:false" style="width: 120px;">
							<option value="">请选择</option>
							<option value="1">通过</option>
							<option value="2">违规评论，不显示</option>
					</select></td>
				<td>评论时间</td>
				<td><input id="startTime" name="queryTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
			<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
			</tr>
			<tr>
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