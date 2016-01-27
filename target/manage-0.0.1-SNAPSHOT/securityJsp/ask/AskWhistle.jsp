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
.noimg img{
display: none
}
</style>
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
	var disableFun = function(whistleBlowing) {
		parent.$.messager.confirm('询问', '您确定要修改为未读吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/askWhidtleBlowing/update', {
					'whistleBlowing' : whistleBlowing
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var OpenFun = function(whistleBlowing) {
		parent.$.messager.confirm('询问', '您确定要修改为已读吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/askWhidtleBlowing/update', {
					'whistleBlowing' : whistleBlowing
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var showFun = function(id,whistleBlowingType) {
		var dialog = parent.sy.modalDialog({
			title : '查看',
			width:750,
			height:580,
			url : sy.contextPath + '/securityJsp/ask/AskWhistleForm.jsp?whistleBlowing=' + id+'&whistleBlowingType='+whistleBlowingType,
			buttons : [ {
				text : '查看',
				  handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}  
			} ]
		});
	};
	var pingbiFun = function(id,whistleBlowingType) {
		if(whistleBlowingType==1){
			parent.$.messager.confirm('询问', '您确定要屏蔽此问题吗？', function(r) {
				if (r) {
					$.post(sy.contextPath + '/mdmy/ask/disableQuestion', {
						'questionId' : id
					}, function() {
							grid.datagrid('reload');
					}, 'json');
				}
			});
		}else{
			parent.$.messager.confirm('询问', '您确定要屏蔽此问题吗？', function(r) {
				if (r) {
					$.post(sy.contextPath + '/mdmy/ask/disableReply', {
						'replyId' : id
					}, function() {
							grid.datagrid('reload');
					}, 'json');
				}
			});
		} 
	};
	var quxiaoFun = function(id,whistleBlowingType) {
		if(whistleBlowingType==1){
		parent.$.messager.confirm('询问', '您确定要取消屏蔽吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/ask/openQuestion', {
					'questionId' : id
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
		}else{
			parent.$.messager.confirm('询问', '您确定要取消屏蔽吗？', function(r) {
				if (r) {
					$.post(sy.contextPath + '/mdmy/ask/openReply', {
						'replyId' : id
					}, function() {
							grid.datagrid('reload');
					}, 'json');
				}
			});
		}
	};
	var openInterestPage=function(questionId){
		var url="${sysMap['sys_url']}/mdmy/askquestion/showonequestion/"+questionId+"/1.html";
		window.open(url);
	};
	var exportFun = function(){
		var $form = $("#searchForm");
		//alert(JSON.stringify($form.serialize()));
		var f = document.createElement("form");
		document.body.appendChild(f);
		f.method="post";
		f.action = sy.contextPath + '/mdmy/askWhidtleBlowing/exportAskWhistleBlowing?'+$form.serialize()+'&sort='+userSort+'&order='+userOrder;
		f.submit();
	};

	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/mdmy/askWhidtleBlowing/findAskWhidtleBlowing',
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
				title : '举报人昵称',
				field : 'userNickname',
			},
			 {
				width : '100',
				title : '被举报内容',
				field : 'questionTitle',
				formatter:function(value,row){
					var str = '';
					str += sy.formatString('<a class="noimg" href="javaScript:;" onclick="openInterestPage(\'{0}\');">',row.questionId);
					return str+row.questionTitle+'</a>';
				}
			},
			  ] ],
			columns : [ [ {
				width : '100',
				title : '举报原因',
				field : 'whistleBlowingContent',
			} ,
			{
				width : '150',
				title : '举报时间',
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
				title : '状态',
				field : 'questionState',
				formatter : function(value, row) {
					var str = '';
						if(row.questionState==1){
							str = "通过";	
						}else if(row.questionState==2){
							str = "不通过";
						} 
					return str;
				}
			} ,
			{
				title : '操作',
				field : 'action',
				width : '80',
				formatter : function(value, row) {
					var str = '';
						if(row.whistleState==2){
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="已读" onclick="disableFun(\'{0}\');"/>', row.whistleBlowing);	
						}else{
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="未读" onclick="OpenFun(\'{0}\');"/>', row.whistleBlowing);
						}
						str += '&nbsp;&nbsp;',
						str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\',\'{1}\');"/>',row.whistleBlowing,row.whistleBlowingType);
						str += '&nbsp;&nbsp;';
						if(row.questionState==1){
							str += sy.formatString('<img class="iconImg ext-icon-joystick" title="屏蔽" onclick="pingbiFun(\'{0}\',\'{1}\');"/>',row.questionOrReplyId,row.whistleBlowingType);
						}else{
							str += sy.formatString('<img class="iconImg ext-icon-joystick" title="取消" onclick="quxiaoFun(\'{0}\',\'{1}\');"/>',row.questionOrReplyId,row.whistleBlowingType);
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
							<td>是否已读</td>
								<td><select name="whistleState" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
										<option value="1">未读</option>
										<option value="2">已读</option>
										</select></td>
								<td>举报类型</td>
								<td><select name="whistleBlowingType" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
										<option value="1">问题</option>
										<option value="2">回复</option></select></td>
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