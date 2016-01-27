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
	var editFun = function(id,userId) {
					 var dialog = parent.sy.modalDialog({
							title : '机构审核',
							url : sy.contextPath + '/securityJsp/application/organizationForm.jsp?organizationApplicationId=' + id+'&userId='+userId,
							buttons : [ {
								text : '审核',
								handler : function() {
									dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
								}
							} ]
						});
				}  
	 var playFun = function(log,businessLicense) {
		 var dialog = parent.sy.modalDialog({
				title : '身份证照片',
				width:800,
				height:500,
			    url:sy.contextPath + '/securityJsp/application/organizationCard.jsp?organizationLog='+log
			    		+'&organizationBusinessLicense='+businessLicense,
			});
		};
	var exportFun = function(){
		var $form = $("#searchForm");
		//alert(JSON.stringify($form.serialize()));
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/userApplication/exportorganizationApplication?'+$form.serialize()+"&sort="+videoSort+"&order="+videoOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/userApplication/findOrganization',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '80',
				title : '用户邮箱',
				field : 'userMail',
			},  {
				width : '100',
				title : '机构全称',
				field : 'organizationName',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},{
				width : '100',
				title : '机构简介',
				field : 'organizationDesc'
			},{
				width : '60',
				title : '认证领域',
				field : 'categoryId',
				formatter : function(value, row) {
					var str = '';
					if(row.categoryId==1){
						 str='时间兴趣';
					 }else if(row.categoryId==2){
						 str='空间兴趣';
					 }else if(row.categoryId==3){
						 str='视觉兴趣';
					 }else if(row.categoryId==4){
						 str='民间兴趣';
					 }else {
						 str='使用兴趣';
					 } 
					 return str;	
				}
			},
			{
				width : '60',
				title : '联系人',
				field : 'organizationLinkman'
			},  
			{
				width : '100',
				title : '联系电话',
				field : 'linkmanPhone'
			}, 
			/* {
				width : '120',
				title : '联系邮箱',
				field : 'linkmanMail',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			}, */
			{
				width : '130',
				title : '身份证号码',
				field : 'applicationIdentityCard',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '60',
				title : '省',
				field : 'userAddrProvince'
			},  
			{
				width : '60',
				title : '市',
				field : 'userAddrCity'
			},  
			{
				width : '60',
				title : '区',
				field : 'userAddrDistrict'
			},  
			{
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
				title : '攻略链接',
				field : 'interestUrl',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '80',
				title : '微信号',
				field : 'wechatNum',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '80',
				title : '微博号',
				field : 'weiboNum',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '100',
				title : '媒体报道链接',
				field : 'medaiReportUrl',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '100',
				title : '代表作链接',
				field : 'masterWorkUrl',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '100',
				title : '审批状态',
				field : 'organizationState',
					formatter : function(value, row) {
						var str = '';
						  if(row.organizationState==1){
							 str='审核通过';
						 }else if(row.organizationState==2){
							 str='审核不通过';
						 }else{
							 str='审核中';
						 }
						 return str;	
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
				width : '150',
				title : '审批时间',
				field : 'applicationApproveTime',
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
				title : '不通过原因',
				field : 'applicationNotPassReason'
			},  
			 {
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '&nbsp;&nbsp;';
							 str += sy.formatString('<img class="iconImg ext-icon-television" title="查看身份证照片" onclick="playFun(\'{0}\',\'{1}\');"/>',
							 row.organizationLog,row.organizationBusinessLicense);
							 if(row.organizationState==0){
								 str += sy.formatString('<img class="iconImg ext-icon-joystick" title="审核" onclick="editFun(\'{0}\',\'{1}\');"/>', row.organizationApplicationId,row.userId);
									  str += "&nbsp;";
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
			 <td>&nbsp;&nbsp;&nbsp;用户邮箱</td>
				<td><input id="searchBox" name="userMail" style="width: 60px" ></input></td>
			    <td>机构全称</td>
				<td><input id="searchBox" name="organizationName" style="width: 60px" ></input></td>
				<td>创建时间</td>
				<td><input id="startTime" name="queryTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
				<td>审批时间</td>
				<td><input id="startTime1" name="queryTimeBeginStr" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryTimeEndStr" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime1\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
				<td>审批状态</td>
					<td><select id="selected" class="easyui-combobox" name="organizationState" data-options="panelHeight:'auto',editable:false" style="width: 90px;">
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