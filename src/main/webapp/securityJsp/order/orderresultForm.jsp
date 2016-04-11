<%@page import="com.midai.miya.sys.model.Operator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>

<%
	String id = request.getParameter("resultId");
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
</script>
</head>
<body>
	<form method="post" class="form" >
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
	</form>
</body>
</html>