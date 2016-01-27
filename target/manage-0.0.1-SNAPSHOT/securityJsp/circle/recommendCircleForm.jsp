<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("recommendCircleId");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		//$('#addButton').linkbutton('disable');
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="recommendCircleId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/recommendCircle/updateRecommendCircle';
			} else {
				url = '<%=contextPath%>/mdmy/recommendCircle/saveRecommendCircle';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/recommendCircle/saveRecommendCircle'){
					if (result.success) {
						$pjq.messager.alert('提示', result.msg, 'info');
						$grid.datagrid('load');
						$dialog.dialog('destroy');
					} else {
						$pjq.messager.alert('提示', result.msg, 'error');
					}
				}else{
				if (result.success) {
					$pjq.messager.alert('提示', result.msg, 'info');
					$grid.datagrid('reload');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
				}
			}, 'json');
		}
	};
	$(function() {
		if ($(':input[name="recommendCircleId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/recommendCircle/findRecommendCircleById', {
				recommendCircleId : $(':input[name="recommendCircleId"]').val()
			}, function(result) {
				if (result.recommendCircleId != undefined) {
					$('form').form('load', {
						'circleName_a' : result.circleName,
						'recommendCircleSort' : result.recommendCircleSort,
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
var choose = function() {
	    $('#win').window('open');
	};
	var editFun=function(circleName,circleId){
		$("#circleName").val(circleName);
		$('#circleId').val(circleId);
		 $('#win').window('close');
	}
	$(function() {
		$('#win').window('close');
		grid = $('#grida').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/circle/findCircleByRecommend',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			pageSize : 10,
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
				title : '操作',
				field : 'action',
				width : '50',
				formatter : function(value, row) {
					var str = '';
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="选择" onclick="editFun(\'{0}\',\'{1}\');"/>', row.circleName,row.circleId);
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
		});
		$('form :input').keyup(function(event) {
			if (event.keyCode == 13) {
				grid.datagrid('load',sy.serializeObject($('#searchForm')));
			}
		});
	});
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="recommendCircleId" value="<%=id%>" type="hidden"/>
	<input name="circleId" id="circleId" value="" type="hidden"/>
		<fieldset>
			<legend>圈子基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>圈子名称</th>
					<td><input name="circleName_a" id="circleName" class="easyui-validatebox" readonly="readonly" data-options="required:true" /></td>
					<td><img class="iconImg ext-icon-note_edit" title="选择" onclick="choose()"/></td>
				</tr>
				<tr>
					<th>推荐排序</th>
					<td>
					<input name="recommendCircleSort" class="easyui-numberspinner" data-options="required:true,min:1,max:100000,editable:true" style="width: 155px;" value="1" /></td>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
	 <div id="win" class="easyui-window" title="选择圈子"   style="width: 600px;height: 400px"
        data-options="iconCls:'icon-save',modal:true">
	<div id="toolbar">
	<form id="searchForm">
		<table>
							<tr>
								<td>圈子Id</td>
								<td><input name="circleId" style="width: 80px;" /></td>
							    <td>圈子名称</td>
								<td><input name="circleName"  style="width: 80px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grida" data-options="border:false" style="height: 360px"></table>
	</div>
	</div>
</body>
</html>