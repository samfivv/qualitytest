<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style type="text/css">
.datagrid-mask{  
  opacity:0; 
   filter:alpha(opacity=0);}
   .datagrid-mask-msg{  
    opacity:0; 
     filter:alpha(opacity=0);
     }
</style>
<script type="text/javascript">
	var grid;
	var approvalSort="approvalTime";
	var approvalOrder="desc";
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
	var exportFun = function(){
		var $form = $("#searchForm");
		//alert(JSON.stringify($form.serialize()));
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/videoController/exportApproval?'+$form.serialize()+"&sort="+approvalSort+"&order="+approvalOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/videoController/findApproval',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'approvalTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '250',
				title : '视频ID',
				field : 'videoId',
			},{
				width : '150',
				title : '视频名称',
				field : 'videoName',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			 {
				width : '150',
				title : '视频简介',
				field : 'videoDesc',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '150',
				title : '审核人',
				field : 'operatorName'
			},  
			{
				width : '150',
				title : '审核时间',
				field : 'approvalTime',
				sortable : true,
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        }

			},
			{
				width : '150',
				title : '审核结果',
				field : 'approvalState',
					formatter : function(value, row) {
						var str = '';
						 if(row.approvalState==1){
							 str='审核通过';
						 }else if(row.approvalState==2){
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
			  ] ],
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
				approvalSort=sort;
				approvalOrder=order;
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
			   <td>&nbsp;&nbsp;&nbsp;视频&nbsp;ID</td>
				<td><input id="searchBox" name="videoId"  style="width: 80px" ></input></td>
			    <td>视频名称</td>
				<td><input id="searchBox" name="videoName"  style="width: 60px" ></input></td>
				<td>审核人</td>
				<td><input id="searchBox" name="operatorName"  style="width: 60px" ></input></td>
				<td>审核时间</td>
		        <td><input id="startTime" name="queryTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
		        <td>审核结果</td>
					<td><select  class="easyui-combobox" name="approvalState" data-options="panelHeight:'auto',editable:false" style="width: 90px;">
					        <option value="">请选择</option>
							<option value="1">审核通过</option>
							<option value="2">审核不通过</option>
					</select></td>
		      <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
		   </tr>
		    <tr>
		        <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td>
		    </tr>
		</table>
		</form>
	</div>
	<div  data-options="region:'center',fit:true,border:false">
		<table id="grid"  data-options="fit:true,border:false"></table>
	</div>
</body>
</html>