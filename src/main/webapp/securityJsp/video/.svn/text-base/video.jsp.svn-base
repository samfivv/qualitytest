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
	var videoSort="videoCreatetime";
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
	var editFun = function(id) {
		 $.post('<%=contextPath%>/mdmy/videoController/findById',{'videoId' : id},
				 function(result) {
				 if (result.success) {
					 $("#lock_"+id).attr("class","iconImg ext-icon-lock");
					 var dialog = parent.sy.modalDialog({
							title : '视频审核',
							url : sy.contextPath + '/securityJsp/video/videoForm.jsp?videoId=' + id,
							buttons : [ {
								text : '审核',
								handler : function() {
									dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
								}
							} ]
						});
				} else {
					$.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
	};
	var openInterestPage=function(videoId){
		var url="${sysMap['sys_url']}/mdmy/video/play/"+videoId+".html";
		window.open(url);
	};
  var clearFun = function(id) {
				$.post(sy.contextPath + '/mdmy/videoController/update', {
					'videoId' : id
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			};
	var playVideoFun = function(vId,id) {
		 $.post('<%=contextPath%>/mdmy/videoController/findById',{'videoId' : id},
				 function(result) {
				 if (result.success) {
					$("#lock_"+id).attr("class","iconImg ext-icon-lock");
					var dialog = parent.sy.playDialog({
						title : '查看视频',
						url : sy.contextPath + '/securityJsp/video/playVideo.jsp?vId=' + vId,
						buttons : [ {
							text : '关闭',
							handler : function() {
								dialog.dialog('destroy');
							}
						} ]
					});
				 } else {
						$.messager.alert('提示', result.msg, 'error');
					}
				}, 'json');
	};
	var exportFun = function(){
		var $form = $("#searchForm");
		//alert(JSON.stringify($form.serialize()));
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/videoController/exportVideo?'+$form.serialize()+"&sort="+videoSort+"&order="+videoOrder;
		f.submit();
	};
	function myrefresh()
	{
	       $('#search_id').trigger("click");
	}
	setInterval('myrefresh()',300000); //指定5分钟刷新一次 
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/videoController/findAllVideo',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'videoCreatetime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '250',
				title : '视频ID',
				field : 'videoId',
			},  {
				width : '150',
				title : '视频名称',
				field : 'videoName',
				formatter : function(value, row) {
					var str = '';
					str += sy.formatString('<a class="noimg" title="'+row.videoName+'" href="javaScript:;" onclick="openInterestPage(\'{0}\');">',row.videoId);
					return str+row.videoName+'</a>';
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
				width : '120',
				title : '创建人',
				field : 'userName'
			},  
			{
				width : '150',
				title : '创建时间',
				field : 'videoCreatetime',
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
				title : '审核状态',
				field : 'videoState',
					formatter : function(value, row) {
						var str = '';
						  if(row.videoState==0){
							 str='未审核';
						 }else if(row.videoState==1||row.videoState==3){
							 str='审核通过';
						 }else{
							 str='审核不通过';
						 }
						 return str;	
					}
			},  
			{
				width : '150',
				title : '审核时间',
				field : 'videoAudittime',
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
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '&nbsp;&nbsp;';
					//var selected=$("#selected").combobox('getValues');
					// if(selected==0){
						    str += sy.formatString('<img class="iconImg ext-icon-television" title="查看视频" onclick="playVideoFun(\'{0}\',\'{1}\');"/>', row.polyVid,row.videoId);
						    str += "&nbsp;";
							str += sy.formatString('<img class="iconImg ext-icon-joystick" title="审核" onclick="editFun(\'{0}\');"/>', row.videoId);
							str += "&nbsp;";
							if(row.videoLock==1){
								str += sy.formatString('<img class="iconImg ext-icon-lock_go" title="解锁" id="lock_'+row.videoId+'" onclick="clearFun(\'{0}\');"/>', row.videoId);
							}else{
								str += sy.formatString('<img class="iconImg ext-icon-lock_open" title="加锁" id="lock_'+row.videoId+'" onclick="clearFun(\'{0}\');"/>', row.videoId);
							}
					 //}else{
						 //str += "&nbsp;&nbsp;";
						 //str += sy.formatString('<img class="iconImg ext-icon-television" title="查看" onclick="showFun(\'{0}\');"/>', row.polyVid);
					 
					
					 //}
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
			 <td>&nbsp;&nbsp;&nbsp;视频&nbsp;ID</td>
				<td><input id="searchBox" name="videoId" style="width: 60px" ></input></td>
			    <td>视频名称</td>
				<td><input id="searchBox" name="videoName" style="width: 60px" ></input></td>
				<td>创建人</td>
				<td><input id="searchBox" name="userName" style="width: 60px" ></input></td>
				<td>创建时间</td>
				<td><input id="startTime" name="queryTimeBegin" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryTimeEnd" class="Wdate easyui-validatebox" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
						        style="width: 150px;" /></td>
				<td>状态</td>
					<td><select id="selected" class="easyui-combobox" name="videoState" data-options="panelHeight:'auto',editable:false" style="width: 90px;">
							<option value="">请选择</option>
							<option value="0">未审核</option>
							<option value="1">审核通过</option>
							<option value="2">审核不通过</option>
							<option value="3">已分享</option>
					</select></td>
			<td><a id="search_id" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
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