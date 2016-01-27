<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("recommendUserId");
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
			if ($(':input[name="recommendUserId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/recommendUser/updateOrganization';
			} else {
				url = '<%=contextPath%>/mdmy/recommendUser/saveOrganization';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/recommendUser/saveOrganization'){
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
		if ($(':input[name="recommendUserId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/recommendUser/findByTalentId', {
				recommendUserId : $(':input[name="recommendUserId"]').val()
			}, function(result) {
				if (result.recommendUserId != undefined) {
					$('form').form('load', {
						'userNicknamea' : result.userNickname,
						'userMaila' : result.userMail,
						'recommendSorta' : result.recommendSort,
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
var choose = function() {
	    $('#win').window('open');
		/* 
		var dialog = parent.sy.modalDialog({
			title : '编辑角色信息',
			url : sy.contextPath + '/securityJsp/operator/test.jsp',
			buttons : []
		}); */
	};
	var editFun=function(id,userNickname,userMail){
		$("#userNickname").val(userNickname);
		$("#userId").val(id);
		$("#userMail").val(userMail);
		 $('#win').window('close');
	}
	$(function() {
		$('#win').window('close');
		grid = $('#grida').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/recommendUser/findAllOrganizationUser',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [{
				width : '150',
				title : '邮箱',
				field : 'userMail'
			},{
				width : '150',
				title : '昵称',
				field : 'userNickname',
			},
			 {
				width : '190',
				title : '个性签名',
				field : 'userSignature'
			},  
			 {
				title : '操作',
				field : 'action',
				width : '50',
				formatter : function(value, row) {
					var str = '';
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="选择" onclick="editFun(\'{0}\',\'{1}\',\'{2}\');"/>', row.userId,row.userNickname,row.userMail);
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
	<input name="recommendUserId" value="<%=id%>" type="hidden"/>
	<input name="userId" id="userId" value="" type="hidden"/>
		<fieldset>
			<legend>机构基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>机构昵称</th>
					<td><input name="userNicknamea" id="userNickname" class="easyui-validatebox" readonly="readonly" data-options="required:true" /></td>
					<td><img class="iconImg ext-icon-note_edit" title="选择" onclick="choose()"/></td>
				</tr>
				<tr>
					<th>邮箱</th>
				   <td><input  name="userMaila" id="userMail" readonly="readonly" style="width: 150px" ></input></td>
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
	 <div id="win" class="easyui-window" title="选择机构"   style="width: 600px;height: 400px"
        data-options="iconCls:'icon-save',modal:true">
	<div id="toolbar">
	<form id="searchForm">
		<table>
		    <tr>
		        <td>邮箱</td>
				<td><input  name="userMail" style="width: 60px" ></input></td>
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