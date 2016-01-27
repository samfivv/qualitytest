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
		var helpSort="helpCreateTime";
		var helpOrder="desc";
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
				title : '处理意见',
				url : sy.contextPath + '/securityJsp/help/helpQuestionForm.jsp?helpQuestionId=' + id ,
				buttons : [ {
					text : '处理',
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
		f.action = sy.contextPath + '/mdmy/help/exportHelp?'+$form.serialize()+'&sort='+helpSort+'&order='+helpOrder;
		f.submit();
	}; 

	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/help/findAll',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'helpCreateTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '200',
				title : '问题名称',
				field : 'helpQuestion',
			},{
				width : '200',
				title : '页面网址',
				field : 'helpUrl',
			},{ 
				width : '200',
				title : '联系方式',
				field : 'helpContactWay',
			} ,{
				width : '200',
				title : '所在地区',
				field : 'helpRegion',
			} ,{
				width : '100',
				title : '处理人',
				field : 'operatorName',
			} ,{
				width : '100',
				title : '处理状态',
				field : 'helpState',
				formatter : function(value, row) {
					var str = '';
					  if(row.helpState==0){
						 str='未处理';
					 }else if(row.helpState==1){
						 str='已处理';
					 } 
					 return str;	
				}
			},{
				width : '200',
				title : '处理意见',
				field : 'helpSuggestion',
			} ,{
				width : '180',
				title : '创建时间',
				field : 'helpCreateTime',
				formatter:function(value,row,index){
					if(value!=null){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
					}
		        },
				sortable : true
			},{
				width : '180',
				title : '处理时间',
				field : 'helpDealTime',
				formatter:function(value,row,index){
					if(value!=null){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
					}
		        },
				sortable : true
			},  {
				title : '操作',
				field : 'action',
				width : '90',
				formatter : function(value, row) {
					var str = '';
					str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="处理" onclick="editFun(\'{0}\');"/>',row.helpQuestionId);
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
				helpSort=sort;
				helpOrder=order;
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
	<div id="toolbar" >
					<form id="searchForm">
						<table>
							<tr>
							    <td>问题名称</td>
								<td><input id="searchBox" name="helpQuestion" style="width: 60px" ></input></td>
								<td>处理人</td>
								<td><input id="searchBox" name="operatorName" style="width: 60px" ></input></td>
								<td>处理状态</td>
									<td><select id="selected" class="easyui-combobox" name="helpState" data-options="panelHeight:'auto',editable:false" style="width: 120px;">
											<option value="">请选择</option>
											<option value="0">未处理</option>
											<option value="1">已处理</option>
									</select></td>
								<td>创建时间</td>
								<td><input id="startTime" name="queryTimeBegin" class="Wdate" 
										        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										        readonly="readonly" style="width: 150px;" />-
										        <input name="queryTimeEnd" class="Wdate easyui-validatebox" 
										        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
										        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
										        style="width: 150px;" /></td>
								<td>处理时间</td>
								<td><input id="startTime2" name="handleTimeStart" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="handleTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime2\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
					<table>
						<tr>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td>
						</tr>
					</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>