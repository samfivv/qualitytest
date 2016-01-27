<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("recommendId");
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
		$('#addButton').linkbutton('disable');
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="recommendId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/RecommendController/update';
			} else {
				url = '<%=contextPath%>/mdmy/RecommendController/save';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/RecommendController/save'){
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
		if ($(':input[name="recommendId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/RecommendController/findRecommendById', {
				recommendId : $(':input[name="recommendId"]').val()
			}, function(result) {
				if (result.recommendId != undefined) {
					$('form').form('load', {
						'interestTitlea' : result.interestTitle,
						'interestDesca' : result.interestDesc,
						'recommendSorta' : result.recommendSort,
						'categoryId':result.categoryId,
						'categoryName':result.categoryName
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
var choose = function(id) {
	    $('#win').window('open');
		/* 
		var dialog = parent.sy.modalDialog({
			title : '编辑角色信息',
			url : sy.contextPath + '/securityJsp/operator/test.jsp',
			buttons : []
		}); */
	};
	var editFun=function(interestTitle,id,interestDesc,categoryParentId){
		$("#interestTitle").val(interestTitle);
		$("#interestId").val(id);
		$("#interestDesc").val(interestDesc);
		$.post('<%=contextPath%>/mdmy/RecommendController/findCategoryNameById',
				{'categoryId':categoryParentId},function(result){
			        if(result.success){
			        	$('#categoryName').val(result.data);
			        }
				},"json");
		 
		 //$("#categoryId").combobox('select', categoryParentId);
		 $('#categoryId').val(categoryParentId);
		 $('#win').window('close');
	}
	$(function() {
		$('#win').window('close');
		grid = $('#grida').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/RecommendController/findInterestAll',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '150',
				title : '专辑名称',
				field : 'interestTitle',
			},
			 {
				width : '190',
				title : '专辑描述',
				field : 'interestDesc'
			},  
			{
				width : '150',
				title : '上传人昵称',
				field : 'userNickname'
			},  
			 {
				title : '操作',
				field : 'action',
				width : '50',
				formatter : function(value, row) {
					var str = '';
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="选择" onclick="editFun(\'{0}\',\'{1}\',\'{2}\',\'{3}\');"/>', my_nl2br(row.interestTitle),row.interestId,my_nl2br(row.interestDesc),row.categoryParentId);
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
	//去掉换行符
	function my_nl2br($s)  
	  {  
		var str= $s.replace(/[\r\n]/g,"");  
	  return  str;
	  }
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="recommendId" value="<%=id%>" type="hidden"/>
	<input name="interestId" id="interestId" value="" type="hidden"/>
	<input name="categoryId" id="categoryId" value="" type="hidden"/>
		<fieldset>
			<legend>专辑基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>专辑名称</th>
					<td><input name="interestTitlea" id="interestTitle" class="easyui-validatebox" readonly="readonly" data-options="required:true" /></td>
					<td><img class="iconImg ext-icon-note_edit" title="选择" onclick="choose()"/></td>
				</tr>
				<tr>
					<th>专辑描述</th>
					<td><textarea id="interestDesc" name="interestDesca" readonly="readonly"></textarea></td>
				</tr>
				<tr>
				    <th>所属类别</th>
				   <%--  <td>
					<select id="categoryId" name="categoryId" class="easyui-combobox" data-options="valueField:'categoryId',textField:'categoryName',url:'<%=contextPath%>/mdmy/category/findCategoryByParentId'" readonly="readonly" style="width: 155px" >
					</select>
					</td> --%>
					<td><input name="categoryName" id="categoryName" class="easyui-validatebox" readonly="readonly" data-options="required:true" /></td>
			    </tr>
				<tr>
					<th>推荐排序</th>
					<td>
					<select id="selected" class="easyui-combobox" name="recommendSorta" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1" >1</option>
							<option value="2">2</option>
							<option value="3" >3</option>
							<option value="4">4</option>
							<option value="5" >5</option>
							<option value="6">6</option>
							<option value="7" >7</option>
							<option value="8">8</option>
							<option value="9">9</option>
					</select>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
	 <div id="win" class="easyui-window" title="选择专辑"   style="width: 600px;height: 400px"
        data-options="iconCls:'icon-save',modal:true">
	<div id="toolbar">
	<form id="searchForm">
		<table>
		    <tr>
				<td>专辑名称</td>
				<td><input  name="interestTitle" style="width: 60px" ></input></td>
			    <td>专辑描述</td>
				<td><input  name="interestDesc" style="width: 90px" ></input></td>
				<td>上传人昵称</td>
				<td><input  name="userNickname" style="width: 60px" ></input></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
			</tr>
			<tr>
			<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
			<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grida" data-options="border:false"></table>
	</div>
	</div>
</body>
</html>