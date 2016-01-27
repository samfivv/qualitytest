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
	var userSort="createTime";
	var userOrder="desc";
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
	var disableFun = function(circleTopicId) {
		parent.$.messager.confirm('询问', '您确定要屏蔽吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/circleReply/disableCircleReply', {
					'mywCircleReplyId' : circleTopicId
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var OpenFun = function(circleTopicId) {
		parent.$.messager.confirm('询问', '您确定要取消屏蔽吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/circleReply/openCircleReply', {
					'mywCircleReplyId' : circleTopicId
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看回复图片',
			url : sy.contextPath + '/mdmy/circleReply/findCircleReplyImg?mywCircleReplyId=' + id
		});
	};
	var exportFun = function(){
		var $form = $("#searchForm");
		//alert(JSON.stringify($form.serialize()));
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/ask/exportAskQuestion?'+$form.serialize()+'&sort='+userSort+'&order='+userOrder;
		f.submit();
	};

	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/mdmy/circleReply/findAllCircleReply',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			//idField : 'userId',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			frozenColumns : [ [ {
				width : '120',
				title : '回复内容',
				field : 'replyContent',
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
				width : '100',
				title : '是否显示',
				field : 'isShow',
				formatter : function(value, row) {
					var str = '';
						if(row.isShow==1){
							str = "是";	
						}else if(row.isShow==2){
							str = "否";
						} 
					return str;
				}
			},
			{
				width : '100',
				title : '回复的人昵称',
				field : 'replyFromNickname',
			},
			 {
				width : '100',
				title : '被回复人昵称',
				field : 'replyToNickname',
			},
			{
				width : '100',
				title : '所属话题标题',
				field : 'circleTopicTitle',
			},
			{
				width : '100',
				title : '所属圈子名称',
				field : 'circleName',
			},
			  {
				width : '100',
				title : '首层回复数量',
				field : 'replyCount',
			},
			{
				title : '操作',
				field : 'action',
				width : '80',
				formatter : function(value, row) {
					var str = '';
					     str += sy.formatString('<img class="iconImg ext-icon-note" title="查看回复图片" onclick="showFun(\'{0}\');"/>', row.mywCircleReplyId);
						if(row.isShow==1){
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="屏蔽" onclick="disableFun(\'{0}\');"/>', row.mywCircleReplyId);	
						}else{
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="取消屏蔽" onclick="OpenFun(\'{0}\');"/>', row.mywCircleReplyId);
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
				userSort=sort;
				userOrder=order;
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
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<td>回复内容</td>
								<td><input name="replyContent" style="width: 80px;" /></td>
								<td>是否显示</td>
								<td><select name="isShow" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
										<option value="1">是</option>
										<option value="2">否</option>
										</select></td>
								<td>创建时间</td>
								<td><input id="startTime" name="questionBeginTime" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="questionEndTime" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td>
							
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>