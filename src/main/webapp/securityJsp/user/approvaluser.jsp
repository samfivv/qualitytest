<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>会员认证审核</title>
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
	var editFun = function(id,userId) {
					 var dialog = parent.sy.modalDialog({
							title : '会员认证审核',
							url : sy.contextPath + '/securityJsp/user/approvaluserForm.jsp?approvalUserId=' + id+'&userId='+userId,
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
			url : '<%=contextPath%>/mdmy/approvaluser/findAllApprovalUser',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'createTime',
			sortOrder : 'DESC',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [{
					title : '操作',
					field : 'action',
					width : '100',
					formatter : function(value, row) {
					var str = '&nbsp;&nbsp;';
						str += sy.formatString('<img class="iconImg ext-icon-television" title="查看身份证照片" onclick="playFun(\'{0}\',\'{1}\',\'{2}\');"/>',
						                    row.applicationHandIdentityPhoto,row.applicationFrontIdentityPhoto,row.applicationBackIdentityPhoto);
						str += "&nbsp;&nbsp;&nbsp;&nbsp;";
						if(row.approvalState==0){
							str += sy.formatString('<img class="iconImg ext-icon-joystick" title="审核" onclick="editFun(\'{0}\',\'{1}\');"/>', row.approvalUserId,row.userId);
							str += "&nbsp;";
						}
					return str;
					}
				} ,{
					width : '60',
					title : '会员编号',
					field : 'userNo',
					formatter : function(value, row) {
						if(value){
							return sy.formatString('<span title="{0}">{1}</span>', value, value);
						}
					}
			   },  {
				width : '60',
				title : '会员简称',
				field : 'userSort',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},{
				width : '100',
				title : '会员全称',
				field : 'userDesc',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},{
				width : '100',
				title : '电话号码',
				field : 'userPhone'
			},{
				width : '60',
			    title : '会员类型',
			    field : 'userType',
			    formatter : function(value, row) {
				  var str = '';
				    if(row.userType==1){
					 str='企业用户';
				 }else if(row.userType==2){
					 str='个人用户';
				 }else {
					 str='其它';
				 }
				 return str;	
			}
		},{
				width : '130',
				title : '证件号码',
				field : 'userCertNo',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},{
				width : '100',
				title : '详细地址',
				field : 'userAddr',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '100',
				title : '邮箱',
				field : 'userEmail',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},{
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
				width : '100',
				title : '审批状态',
				field : 'approvalState',
					formatter : function(value, row) {
						var str = '';
						  if(row.approvalState==1){
							 str='审核通过';
						 }else if(row.approvalState==2){
							 str='审核不通过';
						 }else{
							 str='未审核';
						 }
						 return str;	
					}
			},{
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
			},{
				width : '120',
				title : '不通过原因',
				field : 'notPassReason',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			}] ],
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
			 <td>&nbsp;&nbsp;&nbsp;会员电话</td>
				<td><input id="searchBox" name="userPhone" style="width: 100px" ></input></td>
			    <td>会员昵称</td>
				<td><input id="searchBox" name="userSort" style="width: 100px" ></input></td>
			    <td>会员全称</td>
				<td><input id="searchBox" name="userDesc" style="width: 100px" ></input></td>
				<td>创建时间</td>
				<td><input id="startTime" name="approvalCreateTimeBeginStr" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="approvalCreateTimeEndStr" class="Wdate easyui-validatebox" 
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