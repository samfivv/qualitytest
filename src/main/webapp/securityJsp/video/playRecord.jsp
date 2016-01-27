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
	var playSort="playBeginTime";
	var playOrder="desc";
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
		f.action = sy.contextPath + '/mdmy/playRecord/exportPlayRecord?'+$form.serialize()+'&sort='+playSort+'&order='+playOrder;
		f.submit();
	};

	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/mdmy/playRecord/findPlayRecord',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			//idField : 'userId',
			sortName : 'playBeginTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			frozenColumns : [ [  
			{
				width : '200',
				title : '播放IP',
				field : 'playIp',
				
			},
			{
				width : '100',
				title : '兴趣标题',
				field : 'interesetTitle',
				
			},
            {
				width : '250',
				title : '视频ID',
				field : 'videoId',
				
			},
			 {
				width : '200',
				title : '视频名称',
				field : 'videoName',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},
			  ] ],
			 columns : [ [ 
			{
				width : '100',
				title : '用户昵称',
				field : 'userNickname',
				formatter : function(value) {
					var str = '';
				  if(value==null||value==''){
					  str='游客';
				  }else{
					  return value;
				  }
				 return str;	
			     }
			}, 
			{
				width : '80',
				title : '来源',
				field : 'playFrom',
				formatter : function(value, row) {
				var str = '';
			  if(row.playFrom==1){
				 str='网页';
			 }else if(row.playFrom==2){
				 str='安卓';
			 }else if(row.playFrom==3){
				 str='苹果';
			 }else{
				 str='网页';
			 } 
			 return str;	
		     }
			},
			{
				width : '150',
				title : '播放时间',
				field : 'playBeginTime',
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
				title : '结束时间',
				field : 'playEndTime',
				sortable : true,
				formatter:function(value,row,index){
					if(value!=null&&value!=''){
						 var unixTimestamp = new Date(value);
			             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
					}else{
						return "";
					}
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
				playSort=sort;
				playOrder=order;
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
								<td>兴趣标题</td>
								<td><input name="interesetTitle" style="width: 80px;" /></td>
								<td>视频ID</td>
								<td><input name="videoId" style="width: 80px;" /></td>
								<td>视频名称</td>
								<td><input name="videoName" style="width: 80px;" /></td>
								<td>用户昵称</td>
								<td><input name="userNickname" style="width: 80px;" /></td>
								<td>来源</td>
								<td><select name="playFrom" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">网页</option>
										<option value="2">安卓</option>
										<option value="3">苹果</option></select></td>
								<td>播放时间</td>
								<td><input id="startTime1" name="playTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="playTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime1\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
								<td>结束时间</td>
								<td><input id="startTime2" name="endTimeStart" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="endTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime2\']'" readonly="readonly" 
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