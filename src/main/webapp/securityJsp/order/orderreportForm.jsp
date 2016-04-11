<%@page import="com.midai.miya.order.model.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>

<%
	String id = request.getParameter("orderItemId");
    String orderId = request.getParameter("orderId");
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
var submitForm = function($dialog, $grid, $pjq, $mainMenu) {
	if ($('form').form('validate')) {
		var url;
		if ($('#resultId').val().length > 0) {
			url = '<%=contextPath%>/mdmy/orderresult/updateOrderResult';
		} else {
			url ='<%=contextPath%>/mdmy/orderresult/addOrderResult';
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			if(url=='<%=contextPath%>/mdmy/orderresult/addOrderResult'){
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
	
	};
}

  /*
	$(function() {
		if($(':input[name="lookInfo"]').val()=="true"){
			$(':input[name="resultId"]').attr("readonly","readonly");
		}
		if ($('#resultId').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/orderresult/findById',
					{resultId : $('#resultId').val()}, 
					function(result) {
						if (result.resultId != undefined) {
							$('form').form('load', {
								'resultId' : result.resultId,
								'orderId' : result.orderId,
								'resultDate' : result.resultDate,
								'resultAttachment' : result.resultAttachment,
								'orderNo' : result.orderNo,
								'materialBarcode' : result.materialBarcode,
							});
						}
						parent.$.messager.progress('close');
					}, 'json');
		}

	});
*/
	
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/order/findItemByOrderId',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'orderId',
			sortOrder : 'ASC',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [{
					title : '操作',
					field : 'action',
					width : '70',
					formatter : function(value, row) {
					var str = '&nbsp;&nbsp;';
						str += sy.formatString('<img class="iconImg ext-icon-television" title="录入检测结果" onclick="playFun(\'{0}\',\'{1}\',\'{2}\');"/>',
						                    row.applicationHandIdentityPhoto,row.applicationFrontIdentityPhoto,row.applicationBackIdentityPhoto);
						str += "&nbsp;&nbsp;&nbsp;&nbsp;";
						if(row.orderState==0){
							str += sy.formatString('<img class="iconImg ext-icon-joystick" title="审核" onclick="editFun(\'{0}\',\'{1}\');"/>', row.approvalOrderId,row.orderId);
							str += "&nbsp;";
						}
					return str;
					}
				} ,{
					width : '150',
					title : '检验项目',
					field : 'itemParentName'
			   },{
					width : '100',
					title : '子项目',
					field : 'itemName'
			   },{
					width : '100',
					title : '单位',
					field : 'itemUnit'
				},{
					width : '150',
					title : '标准要求',
					field : 'itemStandard'
				},{
					width : '150',
					title : '实测结果',
					field : 'itemResult'
				},{
					width : '150',
					title : '单项结论',
					field : 'itemConclusion'
				},{
					width : '200',
					title : '备注',
					field : 'itemNote'
				},] ],
		
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
				videoSort=sort;
				videoOrder=order;
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
    	<input name="orderItemId" value="<%=id %>"/>
    </form>
	<form method="post" class="form" >
	   <table id="grid" data-options="fit:true,border:false"></table>
	     <!-- 
	    <input id="resultId"  value="<%=id%>" type="hidden" />
	    <input name="orderId"  type="hidden" />
		<input name="lookInfo" value="<%=lookInfo%>" type="hidden" />
		<fieldset>
			<legend>检测报告信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th >报告编号或条码</th>
					<td><input name="orderNo" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
				<th>开始时间</th>
				<td><input id="resultDate" name="resultDateStr" class="Wdate easyui-validatebox" 
						         onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
						        readonly="readonly" style="width: 150px;" data-options="required:true" 
						        value="<fmt:formatDate value='${resultDate}' type='both' pattern='yyyy-MM-dd'/>"/></td>
				</tr>
				<tr>
					<th >附件</th>
					<td><input name="resultAttachment" class="easyui-validatebox" data-options="required:false"/></td>
				</tr>
			</table>
		</fieldset>
		-->
	</form>
	
</body>
</html>