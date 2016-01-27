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
	var userSort="circleTopicCreateTime";
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
				$.post(sy.contextPath + '/mdmy/circleTopic/disableCircleTopic', {
					'circleTopicId' : circleTopicId
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var OpenFun = function(circleTopicId) {
		parent.$.messager.confirm('询问', '您确定要取消屏蔽吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/circleTopic/openCircleTopic', {
					'circleTopicId' : circleTopicId
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var topFun = function(circleTopicId,isTop) {
		if(isTop=='null'){
			isTop=1;
		}
		if(isTop==1){
			parent.$.messager.confirm('询问', '您确定要置顶吗？', function(r) {
				if (r) {
					$.post(sy.contextPath + '/mdmy/circleTopic/topTopic', {
						'circleTopicId' : circleTopicId,'isTop':isTop
					}, function() {
							grid.datagrid('reload');
					}, 'json');
				}
			});
		}else if(isTop==2){
			parent.$.messager.confirm('询问', '您确定要取消置顶吗？', function(r) {
				if (r) {
					$.post(sy.contextPath + '/mdmy/circleTopic/topTopic', {
						'circleTopicId' : circleTopicId,'isTop':isTop
					}, function() {
							grid.datagrid('reload');
					}, 'json');
				}
			});
		}
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看话题图片',
			url : sy.contextPath + '/mdmy/circleTopic/findCircleTopicById?circleTopicId=' + id
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
			url : sy.contextPath + '/mdmy/circleTopic/findAllCircleTopic',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			//idField : 'userId',
			sortName : 'circleTopicCreateTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			frozenColumns : [ [ {
				width : '120',
				title : '话题标题',
				field : 'circleTopicTitle',
			},
			 {
				width : '100',
				title : '话题内容',
				field : 'circleTopicContent',
			},
			  ] ],
			columns : [ [  {
				width : '100',
				title : '圈子名称',
				field : 'circleName',
			},{
				width : '100',
				title : '创建人',
				field : 'userNickname',
			},{
				width : '150',
				title : '创建时间',
				field : 'circleTopicCreateTime',
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
				width : '100',
				title : '顶的数量',
				field : 'topCount',
			},{
				width : '100',
				title : '回复数量',
				field : 'replyCount',
			},{
				width : '100',
				title : '是否置顶',
				field : 'isTop',
				formatter : function(value, row) {
					var str = '';
						if(row.isTop==1){
							str = "否";	
						}else if(row.isTop==2){
							str = "是";
						} 
					return str;
				}
			}, {
				width : '150',
				title : '置顶时间',
				field : 'toTopTime',
				sortable : true,
				formatter:function(value,row,index){
					if(value!=null&&value!=''){
					var unixTimestamp = new Date(value);
		            return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
		        }else{
		            return "";	
		        }
		        }
			} ,{
				width : '80',
				title : '加精高亮 ',
				field : 'isHighlight',
				formatter : function(value, row) {
				var str = '';
			 if(row.isHighlight==1){
				 str='否';
			 }else if(row.isHighlight==2){
				 str='加精高亮';
			 } 
			 return str;	
		       }
			},{
				width : '80',
				title : '是否显示 ',
				field : 'isShow',
				formatter : function(value, row) {
				var str = '';
			 if(row.isShow==1){
				 str='是';
			 }else if(row.isShow==2){
				 str='否';
			 } 
			 return str;	
		       }
			}, 
			{
				title : '操作',
				field : 'action',
				width : '80',
				formatter : function(value, row) {
					var str = '';
					    str += sy.formatString('<img class="iconImg ext-icon-note" title="查看话题图片" onclick="showFun(\'{0}\');"/>', row.circleTopicId);
						if(row.isShow==1){
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="屏蔽" onclick="disableFun(\'{0}\');"/>', row.circleTopicId);	
						}else{
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="取消屏蔽" onclick="OpenFun(\'{0}\');"/>', row.circleTopicId);
						}
						if(row.isTop==1||row.isTop==null){
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="置顶" onclick="topFun(\'{0}\',\'{1}\');"/>', row.circleTopicId,row.isTop);	
						}else if(row.isTop==2){
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="取消置顶" onclick="topFun(\'{0}\',\'{1}\');"/>', row.circleTopicId,row.isTop);
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
								<td>话题标题</td>
								<td><input name="circleTopicTitle" style="width: 80px;" /></td>
								<td>话题内容</td>
								<td><input name="circleTopicContent" style="width: 80px;" /></td>
								<td>圈子名称</td>
								<td><input name="circleName" style="width: 80px;" /></td>
								<td>是否显示</td>
								<td><select name="isShow" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
										<option value="1">是</option>
										<option value="2">否</option>
										</select></td>
							    <td>是否置顶</td>
								<td><select name="isTop" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
								        <option value="">请选择</option>
										<option value="1">否</option>
										<option value="2">是</option>
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
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>