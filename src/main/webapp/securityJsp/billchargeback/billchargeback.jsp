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
	var videoSort="createTime";
	var videoOrder="desc";
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
	var editFun = function(id,userId,billNum) {
					 var dialog = parent.sy.modalDialog({
							title : '审核',
							url : sy.contextPath + '/securityJsp/billchargeback/billchargebackForm.jsp?userBillChargebackId=' + id+'&userId='+userId+'&billNum='+billNum,
							buttons : [ {
								text : '审核',
								handler : function() {
									dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
								}
							} ]
						});
	};
	var exportFun = function(){
		var $form = $("#searchForm");
		//alert(JSON.stringify($form.serialize()));
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/userBillChangeBack/exportUserBillChargeback?'+$form.serialize()+"&sort="+videoSort+"&order="+videoOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/userBillChangeBack/findAllUserBillChargeback',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '120',
				title : '订单号',
				field : 'billNum',
			},  {
				width : '120',
				title : '申请人',
				field : 'fromCall',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			 {
				width : '120',
				title : '申请人手机',
				field : 'fromPhoneNumber',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},
			{
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
			},
			{
				width : '120',
				title : '被约人',
				field : 'userName'
			}, 
			{
				width : '120',
				title : '被约人手机',
				field : 'userPhone',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '120',
				title : '订单状态',
				field : 'billState',
					formatter : function(value, row) {
						var str = '';
						  if(row.billState==1){
							 str='用户提交账单';
						 }else if(row.billState==2){
							 str='被约人同意见面';
						 }else if(row.billState==3){
							 str='被约人不同意见面';
						 }else if(row.billState==4){
							 str='约定人已付款到平台';
						 }else if(row.billState==5){
							 str='确定付款到被约人';
						 }else if(row.billState==6){
							 str='退款';
						 }else if(row.billState==7){
							 str='订单取消';
						 }
						 return str;	
					}
			},  
			{
				width : '120',
				title : '审核人',
				field : 'operatorName'
			}, 
			{
				width : '100',
				title : '审核状态',
				field : 'userBillChargebackState',
					formatter : function(value, row) {
						var str = '';
						  if(row.userBillChargebackState==1){
							 str='未审核';
						 }else if(row.userBillChargebackState==2){
							 str='审核通过';
						 }else if(row.userBillChargebackState==3){
							 str='审核不通过';
						 }
						 return str;	
					}
			},  
			{
				width : '200',
				title : '不通过的原因',
				field : 'notPassReason',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '150',
				title : '审核时间',
				field : 'approvalTime',
				sortable : true,
				formatter:function(value,row,index){
					if(value!=null&&value!=''){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        }else{
		        	return "";
		        	}
		        }
			},  
			 {
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '&nbsp;&nbsp;';
					//var selected=$("#selected").combobox('getValues');
					// if(selected==0){
						    str += "&nbsp;";
						    if(row.userBillChargebackState==1){
						    	str += sy.formatString('<img class="iconImg ext-icon-joystick" title="审核" onclick="editFun(\'{0}\',\'{1}\',\'{2}\');"/>', row.userBillChargebackId,row.userId,row.billNum);
						    }
							
							/* str += "&nbsp;";
							if(row.videoLock==1){
								str += sy.formatString('<img class="iconImg ext-icon-lock_go" title="解锁" id="lock_'+row.userBillChargebackId+'" onclick="clearFun(\'{0}\');"/>', row.userBillChargebackId);
							}else{
								str += sy.formatString('<img class="iconImg ext-icon-lock_open" title="加锁" id="lock_'+row.userBillChargebackId+'" onclick="clearFun(\'{0}\');"/>', row.userBillChargebackId);
							} */
					 //}else{
						 //str += "&nbsp;&nbsp;";
						 //str += sy.formatString('<img class="iconImg ext-icon-television" title="查看" onclick="showFun(\'{0}\');"/>', row.polyVid);
					 
					
					 //}
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
			    <td>订单号</td>
				<td><input id="searchBox" name="billNum" style="width: 60px" ></input></td>
				<td>申请人手机</td>
				<td><input id="searchBox" name="fromPhoneNumber" style="width: 60px" ></input></td>
				<td>创建时间</td>
				<td><input id="startTime" name="queryTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
				<td>订单状态</td>
					<td><select id="selected" class="easyui-combobox" name="billState" data-options="panelHeight:'auto',editable:false" style="width: 120px;">
							<option value="">请选择</option>
							<option value="1">用户提交账单</option>
							<option value="2">被约人同意见面</option>
							<option value="3">被约人不同意见面</option>
							<option value="4">约定人已付款到平台</option>
							<option value="5">确定付款到被约人</option>
							<option value="6">退款</option>
							<option value="7">订单取消</option>
					</select></td>
					<td>审核状态</td>
					<td><select id="selected" class="easyui-combobox" name="userBillChargebackState" data-options="panelHeight:'auto',editable:false" style="width: 80px;">
							<option value="">请选择</option>
							<option value="1">未审核</option>
							<option value="2">审核通过</option>
							<option value="3">审核不通过</option>
					</select></td>
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