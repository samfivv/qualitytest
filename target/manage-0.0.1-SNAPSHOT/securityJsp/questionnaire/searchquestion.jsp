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
	var questionnaireSort="questionnaireCreateTime";
	var questionnaireOrder="desc";
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
	var surveyFun = function(id) {
		window.open(sy.contextPath + '/mdmy/questionnaireController/questionnaireAnalysis/'+id);
	};
	var showFun = function(id) {
		window.open(sy.contextPath + '/securityJsp/questionnaire/questionnaireChart.jsp?questionnaireId='+id);
	};
	var addFun = function() {
		window.open(sy.contextPath + '/securityJsp/questionnaire/question.jsp');
	};
	var editFun = function(id) {
		window.open(sy.contextPath + '/mdmy/questionnaireController/findQuestionnaire/' + id);
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此问卷吗？', function(r) {
			if (r) {
				$.post('<%=contextPath%>/mdmy/questionnaireController/deleted', {
					questionnaireId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var exportFun = function(){
		var $form = $("#searchForm");
		//alert(JSON.stringify($form.serialize()));
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/operatorController/exportRole?'+$form.serialize()+"&sort="+questionnaireSort+"&order="+questionnaireOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/questionnaireController/findAll',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'questionnaireCreateTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '150',
				title : '问卷标题',
				field : 'questionnaireTitle',
			} ,
			{
				width : '150',
				title : '问卷描述',
				field : 'questionnaireDesc',
			} ,{
				width : '180',
				title : '创建时间',
				field : 'questionnaireCreateTime',
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        },
				sortable : true
			},  
			 {
				width : '200',
				title : '问卷类型',
				field : 'questionnaireType',
					formatter : function(value, row) {
						var str = '';
						  if(row.questionnaireType==1){
							 str='深度调查';
						 }else if(row.questionnaireType==2){
							 str='社会生活';
						 }else if(row.questionnaireType==3){
							 str='文体娱乐';
						 }else if(row.questionnaireType==4){
							 str='科技财经';
						 }else if(row.questionnaireType==5){
							 str='情感话题';
						 }else if(row.questionnaireType==6){
							 str='千奇百怪';
						 }
						 return str;	
					}
			},  
			{
				width : '150',
				title : '终止时间',
				field : 'questionnaireEndTime',
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd");
		        },
				sortable : true
			},  
			 {
				title : '操作',
				field : 'action',
				width : '150',
				formatter : function(value, row) {
					var str = '';
					 if(row.roleId==1){
						return "系统管理员不允许修改"; 
					 }
					   str += sy.formatString('<img class="iconImg ext-icon-folder_table" title="分析页" onclick="surveyFun(\'{0}\');"/>', row.questionnaireId);

					    str += sy.formatString('<img class="iconImg ext-icon-note" title="统计结果" onclick="showFun(\'{0}\');"/>', row.questionnaireId);

						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.questionnaireId);
					 
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.questionnaireId);
					 
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
			onSortColumn:function(sort,order){
				questionnaireSort=sort;
				questionnaireOrder=order;
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
				<td>问卷标题</td>
				<td><input  name="questionnaireTitle" style="width: 150px" ></input></td>
			    <td>问卷类型</td>
				<td><select name="questionnaireType" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">深度调查</option>
										<option value="2">社会生活</option>
										<option value="3">文体娱乐</option>
										<option value="4">科技财经</option>
										<option value="5">情感话题</option>
										<option value="6">千奇百怪</option>
					</select></td>
				<td>创建时间</td>
								<td><input id="startTime" name="queryTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
			</tr>
			<tr>
			<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
			<!-- <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td> -->
			</tr>
		</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>