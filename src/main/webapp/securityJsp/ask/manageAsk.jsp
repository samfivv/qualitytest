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
	var userSort="questionCreateTime";
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
	var disableFun = function(questionId) {
		parent.$.messager.confirm('询问', '您确定要屏蔽此问题吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/ask/disableQuestion', {
					'questionId' : questionId
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var OpenFun = function(questionId) {
		parent.$.messager.confirm('询问', '您确定要取消屏蔽吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/mdmy/ask/openQuestion', {
					'questionId' : questionId
				}, function() {
						grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看问题内容',
			url : sy.contextPath + '/securityJsp/ask/askForm.jsp?questionId=' + id+"&&lookInfo=true"
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
			url : sy.contextPath + '/mdmy/ask/findAll',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			//idField : 'userId',
			sortName : 'questionCreateTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			frozenColumns : [ [ {
				width : '120',
				title : '问题标题',
				field : 'questionTitle',
			},
			 {
				width : '100',
				title : '昵称',
				field : 'userNickname',
			},
			  ] ],
			columns : [ [ {
				width : '100',
				title : '类别',
				field : 'categoryName',
			},{
				width : '100',
				title : '标签',
				field : 'questionTag',
			},{
				width : '100',
				title : '状态',
				field : 'questionState',
				formatter : function(value, row) {
					var str = '';
						if(row.questionState==1){
							str = "通过";	
						}else if(row.questionState==2){
							str = "不通过";
						}else if(row.questionState==3){
							str = "删除";
						}
					return str;
				}
			},  {
				width : '80',
				title : '是否已解决',
				field : 'questionIsSettle',
				formatter : function(value, row) {
				var str = '';
			  if(row.questionIsSettle==1){
				 str='已解决';
			 }else if(row.questionIsSettle==2){
				 str='未解决';
			 } 
			 return str;	
		     }
			},{
				width : '80',
				title : '是否悬赏 ',
				field : 'questionIsOffer',
				formatter : function(value, row) {
				var str = '';
			 if(row.questionIsOffer==1){
				 str='悬赏';
			 }else if(row.questionIsOffer==2){
				 str='不悬赏';
			 } 
			 return str;	
		       }
			},{
				width : '100',
				title : '悬赏数额',
				field : 'questionOfferCount',
			},{
				width : '100',
				title : '来源',
				field : 'questionFrom',
				formatter : function(value, row) {
					var str = '';
						if(row.questionFrom==1){
							str = "网页";	
						}else if(row.questionFrom==2){
							str = "安卓";
						}else if(row.questionFrom==3){
							str = "苹果";
						}
					return str;
				}
			},{
				width : '100',
				title : '回复数量',
				field : 'replyCount',
			},{
				width : '100',
				title : '浏览次数',
				field : 'questionWacthCount',
			},{
				width : '100',
				title : '推荐数量',
				field : 'questionRecommendCount',
			},
			{
				width : '150',
				title : '提问时间',
				field : 'questionCreateTime',
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
				title : '问题更新时间',
				field : 'questionUpdateTime',
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
				width : '80',
				formatter : function(value, row) {
					var str = '';
					    str += sy.formatString('<img class="iconImg ext-icon-note" title="查看问题内容" onclick="showFun(\'{0}\');"/>', row.questionId);
						if(row.questionState==1){
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="屏蔽" onclick="disableFun(\'{0}\');"/>', row.questionId);	
						}else{
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="取消屏蔽" onclick="OpenFun(\'{0}\');"/>', row.questionId);
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
								<td>问题标题</td>
								<td><input name="questionTitle" style="width: 80px;" /></td>
								<td>标签</td>
								<td><input name="questionTag" style="width: 80px;" /></td>
								<td>状态</td>
								<td><select name="questionState" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
										<option value="">请选择</option>
										<option value="1">通过</option>
										<option value="2">不通过</option>
										<option value="3">删除</option>
										</select></td>
								<td>是否悬赏</td>
								<td><select name="questionIsOffer" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
										<option value="">请选择</option>
										<option value="1">悬赏</option>
										<option value="2">不悬赏</option></select></td>
								<td>问题更新时间</td>
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