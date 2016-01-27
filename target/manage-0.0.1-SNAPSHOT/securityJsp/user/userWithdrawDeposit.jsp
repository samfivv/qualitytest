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
	var happySort="createTime";
	var happyOrder="desc";
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
	var editFun = function(id,phoneNum,userMail,userId,money) {
		var dialog = parent.sy.modalDialog({
			title : '处理体现申请信息',
			url : sy.contextPath + '/securityJsp/user/userWithdrawDepositForm.jsp?withdrawDepositId=' + id+'&phoneNum='+phoneNum+'&userMail='+userMail+'&userId='+userId+'&money='+money,
			buttons : [ {
				text : '处理',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/withdrawDeposit/findWithdrawDepositAll',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [  
			{
				width : '100',
				title : '用户姓名',
				field : 'userName',
			},
			{
				width : '50',
				title : '金额',
				field : 'money',
			},
			{
				width : '100',
				title : '电话号码',
				field : 'phoneNum',
			},
			{
				width : '100',
				title : '身份证号码',
				field : 'identityCard',
			},
			{
				width : '100',
				title : '银行卡号',
				field : 'bankCardNum',
			},
			{
				width : '100',
				title : '开户行名称',
				field : 'bankName',
			},
			{
				width : '100',
				title : '支付宝帐号',
				field : 'alipayNum',
			},
			{
				width : '130',
				title : '申请时间',
				field : 'createTime',
				sortable : true,
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        },
			} ,
			{
				width : '150',
				title : '用户邮箱',
				field : 'userMail',
				formatter:function(value,row){
		             if(value==null){
		            	return ""; 
		             }
		        },
			},
			{
				width : '200',
				title : '备注',
				field : 'remak',
			},
			 {
				width : '80',
				title : '处理状态',
				field : 'dealState',
				formatter : function(value, row) {
					var str = '';
					 if(row.dealState==1){
						 str='未处理';
					 }else if(row.dealState==2){
						 str='已处理';
					 }else if(row.dealState==3){
						 str='驳回申请';
					 } 
					 return str;	
				}
			}, 
			{
				width : '130',
				title : '处理时间',
				field : 'dealTime',
				sortable : true,
				formatter:function(value,row,index){
					if(value==null){
						return "";
					}else{
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
					}
		        },
			} ,
			{
				width : '100',
				title : '处理人',
				field : 'operatorName',
			},
			 {
				title : '操作',
				field : 'action',
				width : '100	',
				formatter : function(value, row) {
					var str = '';
					if(row.dealState==1){
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="处理" onclick="editFun(\'{0}\',\'{1}\',\'{2}\',\'{3}\',\'{4}\');"/>', row.withdrawDepositId,row.phoneNum,row.userMail,row.userId,row.money);
					}
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
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
	<form id="searchForm">
		<table>
			<tr>
				<td>身份证</td>
				<td><input id="searchBox" name="identityCard" style="width: 150px" ></input></td>
				<td>银行卡号</td>
				<td><input id="searchBox" name="bankCardNum" style="width: 150px" ></input></td>
				<td>支付宝帐号</td>
				<td><input id="searchBox" name="alipayNum" style="width: 150px" ></input></td>
				<td>处理状态</td>
					<td><select id="selected" class="easyui-combobox" name="dealState" data-options="panelHeight:'auto',editable:false" style="width: 80px;">
							<option value="1">未处理</option>
							<option value="2">已处理</option>
							<option value="3">驳回申请</option>
					</select></td>
				 <td>申请时间</td>
				        <td><input id="startTime" name="queryTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
				 <td>处理时间</td>
				        <td><input id="startTime1" name="queryDealTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryDealTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime1\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>