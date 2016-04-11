<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>检测结果录入</title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var orderSort="resultDate";
	var orderOrder="desc";
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
			title : '添加检测报告',
			width:750,
			height:580,
			url : sy.contextPath + '/securityJsp/order/orderresultForm.jsp',
			buttons : [ {
				text : '添加',
				    handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);  
				}    
			} ]  
		});
	};	
	var editFun = function(id,orderId) {
					 var dialog = parent.sy.modalDialog({
							title : '委托单审核',
							url : sy.contextPath + '/securityJsp/order/approvalorderForm.jsp?approvalOrderId=' + id+'&orderId='+orderId,
							buttons : [ {
								text : '审核',
								handler : function() {
									dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
								}
							} ]
						});
				}  
	 var playFun = function(id,front,back) {
		 var dialog = parent.sy.modalDialog({
				title : '证件照片',
				width:800,
				height:500,
			    url:sy.contextPath + '/securityJsp/approvaluser/approvaluserIdCard.jsp?applicationHandIdentityPhoto='+id
			    		+'&applicationFrontIdentityPhoto='+front+'&applicationBackIdentityPhoto='+back,
			});
		};
	var exportFun = function(){
		var $form = $("#searchForm");
		//alert(JSON.stringify($form.serialize()));
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/approvaluser/exportApprovalUser?'+$form.serialize()+"&sort="+videoSort+"&order="+videoOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/orderresult/findAll',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'resultDate',
			sortOrder : 'DESC',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [{
					title : '操作',
					field : 'action',
					width : '100',
					formatter : function(value, row) {
					var str = '&nbsp;&nbsp;';
						str += sy.formatString('<img class="iconImg ext-icon-television" title="查看委托书明细" onclick="playFun(\'{0}\',\'{1}\',\'{2}\');"/>',
						                    row.applicationHandIdentityPhoto,row.applicationFrontIdentityPhoto,row.applicationBackIdentityPhoto);
						str += "&nbsp;&nbsp;&nbsp;&nbsp;";
						if(row.orderState==0){
							str += sy.formatString('<img class="iconImg ext-icon-joystick" title="审核" onclick="editFun(\'{0}\',\'{1}\');"/>', row.approvalOrderId,row.orderId);
							str += "&nbsp;";
						}
					return str;
					}
				} ,{
					width : '60',
					title : '报告编号',
					field : 'orderNo'
			   },{
					width : '60',
					title : '条码',
					field : 'materialBarcode'
			   },{
					width : '100',
					title : '样品名称',
					field : 'materialName'
				},{
					width : '150',
					title : '委托单位',
					field : 'userDesc'
				},{
					width : '100',
					title : '完成日期',
					field : 'resultDate',
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
					width : '150',
					title : '录入人',
					field : 'operatorId'
				},{
					width : '150',
					title : '附件',
					field : 'resultAttachment'
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
		<table>
			<tr>
			    <td>报告编号</td>
				<td><input id="searchBox" name="orderNo" style="width: 100px" ></input></td>
			    <td>会员电话</td>
				<td><input id="searchBox" name="memberTel" style="width: 100px" ></input></td>
			    <td>样品名称</td>
				<td><input id="searchBox" name="materialName" style="width: 100px" ></input></td>
				<td>提交时间</td>
				<td><input id="startTime" name="createTimeBeginStr" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="createTimeEndStr" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
				<td>审批时间</td>
				<td><input id="startTime1" name="approvalTimeBeginStr" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="approvalTimeEndStr" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime1\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
				<td>审批状态</td>
					<td><select id="selected" class="easyui-combobox" name="approvalState" data-options="panelHeight:'auto',editable:false" style="width: 90px;">
							<option value="">请选择</option>
							<option value="0">审批中</option>
							<option value="1">审批通过</option>
							<option value="2">审批不通过</option>
					</select></td>
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
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>