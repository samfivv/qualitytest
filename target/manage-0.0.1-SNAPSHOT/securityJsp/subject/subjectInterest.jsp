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
	var categorySort="subjectInterestSort";
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
			title : '状态管理',
			width:750,
			height:580,
			url : sy.contextPath + '/securityJsp/subject/subjectInterestForm.jsp?subjectInterestId=' + id,
			buttons : [ {
				text : '修改',
				  handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}  
			} ]
		});
	};
	var openInterestPage=function(interestId){
		var url="${sysMap['sys_url']}/mdmy/interest/detail/"+interestId+"/1.html";
		window.open(url);
	};
	var exportFun = function(){
		var $form = $("#searchForm");
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/subject/exportSubjectInterest?'+$form.serialize()+"&sort="+categorySort+"&order="+categoryOrder;
		f.submit();
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/mdmy/subject/findSubjectInterest',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'subjectInterestSort',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '120',
				title : '活动标题',
				field : 'subjectTitle',
			},{
				width : '120',
				title : '状态',
				field : 'subjectInterestState',
				formatter:function(value,row){
					var str='';
					if(row.subjectInterestState==1){
						str='通过';
					}else if(row.subjectInterestState==2){
						str='不通过';
					}
					return str;
				}
			},{
				width : '120',
				title : '排序',
				field : 'subjectInterestSort',
				sortable : true
			} ,{
				width : '120',
				title : '兴趣标题',
				field : 'interestTitle',
				formatter:function(value,row){
					var str = '';
					str += sy.formatString('<a href="javaScript:;" onclick="openInterestPage(\'{0}\');">',row.interestId);
					return str+row.interestTitle+'</a>';
				}
			} ,{
				width : '120',
				title : '兴趣简介',
				field : 'interestDesc',
			} ,{
				width : '120',
				title : '上传人昵称',
				field : 'userNickname',
			},
			{
				width : '120',
				title : '上传人邮箱',
				field : 'userMail',
			},
			{
				width : '200',
				title : '更新时间',
				field : 'interestUpdateTime',
				sortable : true,
				formatter:function(value,row,index){
					if(value!=null&&value!=''){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd");
		        }else{
		        	return "";
	        	}
				}
			} , {
				width : '200',
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
			},{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '';
					if(row.subjectInterestState==1){
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="不通过" onclick="editFun(\'{0}\');"/>',row.subjectInterestId);
					}else if(row.subjectInterestState==2){
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="通过" onclick="editFun(\'{0}\');"/>',row.subjectInterestId);
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
					    <td>活动标题</td>
						<td><input id="searchBox" name="subjectTitle" style="width: 60px" ></input></td>
						<td>兴趣标题</td>
						<td><input id="searchBox" name="interestTitle" style="width: 60px" ></input></td>
						<td>邮箱</td>
						<td><input id="searchBox" name="userMail" style="width: 60px" ></input></td>
					   <td>状态</td>
						<td><select id="select" class="easyui-combobox" name="subjectInterestState" data-options="panelHeight:'auto',editable:false" style="width: 90px;">
							<option value="">请选择</option>
							<option value="1">通过</option>
							<option value="2">不通过</option>
					       </select></td>
					    <td>创建时间</td>
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