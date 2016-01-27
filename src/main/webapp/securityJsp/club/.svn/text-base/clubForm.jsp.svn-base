<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("clubNum");
	if (id == null) {
		id = "";
	}
	String lookInfo = request.getParameter("lookInfo");
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
			if ($('#clubNum').val().length > 0) {
				url = '<%=contextPath%>/mdmy/club/update';
			} else {
				url = '<%=contextPath%>/mdmy/club/save';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/club/save'){
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
		if($(':input[name="lookInfo"]').val()=="true"){
			$(':input[name="clubNum"]').attr("readonly","readonly");
		}
		if ($('#clubNum').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/club/findClubByNum', {
				clubNum : $('#clubNum').val()
			}, function(result) {
				if (result.clubNum != undefined) {
					$('form').form('load', {
						'clubNum' : result.clubNum,
						'clubName' : result.clubName,
						'clubSort' : result.clubSort,
						'schoolNuma' : result.schoolNum,
						'schoolNamea' : result.schoolName
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
	var editFun=function(schoolNum,schoolName){
		$("#schoolNum").val(schoolNum);
		$("#schoolName").val(schoolName);
		 $('#win').window('close');
	}
	$(function() {
		$('#win').window('close');
		grid = $('#grida').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/school/findSchoolAll',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '130',
				title : '学校编号',
				field : 'schoolNum'
			} ,
			{
				width : '130',
				title : '排序',
				field : 'schoolSort',
				sortable : true
			} ,
			{
				width : '130',
				title : '学校名称',
				field : 'schoolName'
			} ,
			 {
				title : '操作',
				field : 'action',
				width : '50',
				formatter : function(value, row) {
					var str = '';
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="选择" onclick="editFun(\'{0}\',\'{1}\');"/>', row.schoolNum,row.schoolName);
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
	<input id="clubNum" name="mine" value="<%=id%>" type="hidden"/>
	<input name="interestId" id="interestId" value="" type="hidden"/>
	<input name="lookInfo" value="<%=lookInfo%>" type="hidden" />
		<fieldset>
			<legend>社团基本信息</legend>
			<table class="table" style="width: 100%;">
			    <tr>
					<th>社团编号</th>
					<td><input name="clubNum" class="easyui-validatebox" data-options="required:true"></input></td>
				</tr>
				<tr>
					<th>所属学校编号</th>
					<td><input name="schoolNuma" id="schoolNum" class="easyui-validatebox" readonly="readonly" data-options="required:true" /></td>
					<td><img class="iconImg ext-icon-note_edit" title="选择" onclick="choose()"/></td>
				</tr>
				<tr>
					<th>所属学校名称</th>
					<td><input id="schoolName" name="schoolNamea" class="easyui-validatebox" readonly="readonly"></input></td>
				</tr>
				<tr>
					<th>社团名称</th>
					<td><input id="clubName" name="clubName" class="easyui-validatebox"  data-options="required:true"></input></td>
				</tr>
				<tr>
					<th>排序</th>
					<td><input name="clubSort" id="clubSort" class="easyui-numberspinner" data-options="required:true,min:1,max:100000,editable:false" style="width: 155px;" value="1" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
	 <div id="win" class="easyui-window" title="选择学校"   style="width: 600px;height: 400px"
        data-options="iconCls:'icon-save',modal:true">
	<div id="toolbar">
	<form id="searchForm">
		<table>
		    <tr>
				<td>学校编号</td>
				<td><input  name="schoolNum" style="width: 60px" ></input></td>
			    <td>学校名称</td>
				<td><input  name="schoolName" style="width: 90px" ></input></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
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