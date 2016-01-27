<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("productionBoutiqueId");
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
	var submitForm = function($dialog, $grid, $pjq) {
		//$('#addButton').linkbutton('disable');
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="productionBoutiqueId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/production/updateProductionBoutique';
			} else {
				url = '<%=contextPath%>/mdmy/production/saveProductionBoutique';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/production/saveProductionBoutique'){
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
		if ($(':input[name="productionBoutiqueId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/production/findProductionBoutiqueById', {
				productionBoutiqueId : $(':input[name="productionBoutiqueId"]').val()
			}, function(result) {
				if (result.productionBoutiqueId != undefined) {
					$('form').form('load', {
						'productionTitle_a' : result.productionTitle,
						'productionBoutiqueSort' : result.productionBoutiqueSort,
						'productionBoutiqueType' : result.productionBoutiqueType,
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
var choose = function() {
	    $('#win').window('open');
	};
	var editFun=function(productionTitle,productionId){
		$("#productionTitle").val(productionTitle);
		$('#productionId').val(productionId);
		 $('#win').window('close');
	}
	$(function() {
		$('#win').window('close');
		grid = $('#grida').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/production/findAllProduction',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ 
			 			{
			 				width : '130',
			 				title : '作品ID',
			 				field : 'productionId'
			 			} ,
			 			{
			 				width : '130',
			 				title : '作品标题',
			 				field : 'productionTitle',
			 			} ,
			 			{
			 				width : '130',
			 				title : '浏览次数',
			 				field : 'browseCount',
			 				sortable : true
			 			} ,
			 			{
							width : '130',
							title : '创建时间',
							field : 'createTime',
							sortable : true,
							formatter:function(value,row,index){
					             var unixTimestamp = new Date(value);
					             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
					        },
						} ,
			 {
				title : '操作',
				field : 'action',
				width : '50',
				formatter : function(value, row) {
					var str = '';
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="选择" onclick="editFun(\'{0}\',\'{1}\');"/>', row.productionTitle,row.productionId);
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
	<input name="productionBoutiqueId" value="<%=id%>" type="hidden"/>
	<input name="productionId" id="productionId" value="" type="hidden"/>
		<fieldset>
			<legend>作品基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>作品标题</th>
					<td><input name="productionTitle_a" id="productionTitle" class="easyui-validatebox" readonly="readonly" data-options="required:true" /></td>
					<td><img class="iconImg ext-icon-note_edit" title="选择" onclick="choose()"/></td>
				</tr>
				<tr>
					<th>推荐排序</th>
					<td>
					<input name="productionBoutiqueSort" class="easyui-numberspinner" data-options="required:true,min:1,max:100000,editable:false" style="width: 155px;" value="1" />
					</td>
				</tr>
				<tr>
					<th>类型</th>
					<td>
					<select id="selected" class="easyui-combobox" name="productionBoutiqueType" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1" >精品作品</option>
							<option value="2">人气作品</option>
							<option value="3" >新品展示</option>
					</select>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
	 <div id="win" class="easyui-window" title="选择作品"   style="width: 600px;height: 400px"
        data-options="iconCls:'icon-save',modal:true">
	<div id="toolbar">
	<form id="searchForm">
		<table>
							<tr>
								<td>作品Id</td>
								<td><input name="productionId" style="width: 80px;" /></td>
							    <td>作品标题</td>
								<td><input name="productionTitle"  style="width: 80px;" /></td>
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