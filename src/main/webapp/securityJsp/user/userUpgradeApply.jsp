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
	var categorySort="createTime";
	var categoryOrder="asc";
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
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '审核',
			width:750,
			height:580,
			url : sy.contextPath + '/securityJsp/user/userUpgradeApplyForm.jsp?userUpgradeApplyId=' + id,
			buttons : [ {
				text : '审核',
				  handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}  
			} ]
		});
	};
	var openInterestPage=function(interestId){
		var url="${sysMap['sys_url']}/mdmy/interest/findSpecialMaterial/"+interestId+".html";
		window.open(url);
	};
	var exportFun = function(){
		var $form = $("#searchForm");
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/userUpgradeApply/exportUserUpgradeApply?'+$form.serialize()+"&sort="+categorySort+"&order="+categoryOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/mdmy/userUpgradeApply/findAllUserUpgradeApply',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'createTime',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '120',
				title : '申请人昵称',
				field : 'userNickname',
			},{
				width : '120',
				title : '申请人手机',
				field : 'userPhone',
			},{
				width : '120',
				title : '申请类型',
				field : 'userUpgradeApplyType',
				formatter:function(value,row){
					var str='';
					if(row.userUpgradeApplyType==2){
						str='签约特长人';
					}else if(row.userUpgradeApplyType==3){
						str='资深特长人';
					}else if(row.userUpgradeApplyType==4){
						str='特约特长人';
					}
					return str;
				}
			},{
				width : '200',
				title : '申请时间',
				field : 'createTime',
				formatter:function(value,row,index){
					if(value!=null&&value!=''){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd");
		        }else{
		        	return "";
	        	}
				}
			} ,{
				width : '120',
				title : '审核人',
				field : 'operatorName',
				sortable : true
			} ,{
				width : '120',
				title : '审核状态',
				field : 'auditState',
				formatter:function(value,row){
					var str='';
					if(row.auditState==1){
						str='未审核';
					}else if(row.auditState==2){
						str='审核通过';
					}else if(row.auditState==3){
						str='审核不通过';
					}
					return str;
				}
			},{
				width : '120',
				title : '不通过原因',
				field : 'applyNotPassReason',
				sortable : true,
			} ,  {
				width : '200',
				title : '审核时间',
				field : 'auditTime',
				formatter:function(value,row,index){
					if(value!=null&&value!=''){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        }else{
		        	return "";
	        	}
				}
			},{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '';
					if(row.auditState==1){
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="审核" onclick="editFun(\'{0}\');"/>',row.userUpgradeApplyId);
					}
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
				categorySort=sort;
				categoryOrder=order;
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
					<td>申请类型</td>
						<td><select id="select" class="easyui-combobox" name="userUpgradeApplyType" data-options="panelHeight:'auto',editable:false" style="width: 90px;">
							<option value="">请选择</option>
							<option value="2">签约特长人</option>
							<option value="3">资深特长人</option>
							<option value="4">特约特长人</option>
					       </select></td>
					   <td>状态</td>
						<td><select id="select" class="easyui-combobox" name="auditState" data-options="panelHeight:'auto',editable:false" style="width: 90px;">
							<option value="1">未审核</option>
							<option value="2">审核通过</option>
							<option value="3">审核不通过</option>
					       </select></td>
					    <td>申请时间</td>
				        <td><input id="startTime" name="queryTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
						        <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
						        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
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