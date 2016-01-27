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
	var videoSort="interestCreateTime";
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
	var editFun = function(id,interestCreator,interestTitle) {
					 var dialog = parent.sy.modalDialog({
							title : '状态管理',
							url : sy.contextPath + '/securityJsp/operations/approvalRecommendForm.jsp?interestId=' + id+'&interestCreator='+interestCreator+'&interestTitle='+interestTitle,
							buttons : [ {
								text : '修改',
								handler : function() {
									dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
								}
							} ]
						});
	};
	var lookFun = function(id) {
		 var dialog = parent.sy.modalDialog({
				title : '查看专辑封面',
				width:800,
				height:500,
				url : sy.contextPath + '/securityJsp/operations/lookImg.jsp?interestBigImgUrl=' + id,
				buttons : [ {
					text : '查看',
					handler : function() {
						dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
					}
				} ]
			});
};
var openInterestPage=function(interestId){
	var url="${sysMap['sys_url']}/interest/videostrategydetail/"+interestId+".html?source=preview";
	window.open(url);
};

var openInterestImgPage=function(interestId){
	var url="${sysMap['sys_url']}/interest/imagetextdetail/"+interestId+".html?source=preview";
	window.open(url);
};

  var clearFun = function(id) {
				$.post(sy.contextPath + '/mdmy/videoController/update', {
					'videoId' : id
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/interestApproval/findAllInterest',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'interestCreatetime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '250',
				title : '攻略ID',
				field : 'interestId',
			},  {
				width : '150',
				title : '攻略标题',
				field : 'interestTitle',
				formatter : function(value, row) {
						var str = '';
						if(row.interestType==1){
							str += sy.formatString('<a class="noimg" title="'+row.interestTitle+'" href="javaScript:;" onclick="openInterestPage(\'{0}\');">',row.interestId);
							return str+row.interestTitle+'</a>';
						}else{
							str += sy.formatString('<a class="noimg" title="'+row.interestTitle+'" href="javaScript:;" onclick="openInterestImgPage(\'{0}\');">',row.interestId);
							return str+row.interestTitle+'</a>';
						}
						
				}
			},  
			 {
				width : '150',
				title : '攻略简介',
				field : 'interestDesc',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},  
			{
				width : '120',
				title : '创建人',
				field : 'userNickname'
			}, 
			{
				width : '120',
				title : '创建人邮箱',
				field : 'userMail'
			},  
			{
				width : '120',
				title : '社团编号',
				field : 'clubNum',
			} , {
				width : '120',
				title : '社团名称',
				field : 'clubName',
			} , {
				width : '120',
				title : '学校名称',
				field : 'schoolName',
			} ,{
				width : '120',
				title : '推荐数量',
				field : 'interestPraiseCount',
				sortable : true
			}, 
			{
				width : '150',
				title : '创建时间',
				field : 'interestCreatetime',
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
				title : '状态',
				field : 'interestState',
					formatter : function(value, row) {
						var str = '';
						  if(row.interestState==1){
							 str='通过';
						 }else if(row.interestState==2){
							 str='不通过';
						 }else if(row.interestState==3){
							 str='草稿';
						 }else if(row.interestState==4){
							 str='审核中';
						 }else if(row.interestState==5){
							 str='删除';
						 }
						 return str;	
					}
			}, 
			/* {
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
				width : '120',
				title : '不通过原因',
				field : 'notPassReason'
			},   */
			 {
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '&nbsp;&nbsp;';
						    str += sy.formatString('<img class="iconImg ext-icon-note" title="查看专辑封面" onclick="lookFun(\'{0}\');"/>',row.interestBigImgUrl);
						    if(row.interestState!=3&&row.interestState!=5){
						    	str += "&nbsp;";
								str += sy.formatString('<img class="iconImg ext-icon-joystick" title="审核" onclick="editFun(\'{0}\',\'{1}\',\'{2}\');"/>', row.interestId,row.interestCreator,row.interestTitle);
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
			 <td>&nbsp;&nbsp;&nbsp;专辑&nbsp;ID</td>
				<td><input id="searchBox" name="interestId" style="width: 60px" ></input></td>
			    <td>专辑标题</td>
				<td><input id="searchBox" name="interestTitle" style="width: 60px" ></input></td>
				<td>创建人</td>
				<td><input id="searchBox" name="userNickname" style="width: 60px" ></input></td>
				<td>邮箱</td>
				<td><input id="searchBox" name="userMail" style="width: 60px" ></input></td>
				<td>社团编号</td>
				<td><input id="searchBox" name="clubNum" style="width: 60px" ></input></td>
				<td>创建时间</td>
				<td><input id="startTime" name="queryTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
				<td>状态</td>
					<td><select id="selected" class="easyui-combobox" name="interestState" data-options="panelHeight:'auto',editable:false" style="width: 90px;">
							<option value="">请选择</option>
							<option value="4">审核中</option>
							<option value="1">通过</option>
							<option value="2">不通过</option>
					</select></td>
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