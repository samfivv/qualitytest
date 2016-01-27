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
	var userSort="rewardCreateTime";
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
	var openUser=function(rewardUserId){
		var url="${sysMap['sys_url']}/mdmy/interest/userhomepage/"+rewardUserId+".html";
		window.open(url);
	};
	var openInterest=function(rewardInterestId){
		var url="${sysMap['sys_url']}/mdmy/interest/detail/"+rewardInterestId+"/1.html";
		window.open(url);
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/mdmy/reward/findAllReward',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'rewardCreateTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ 
			    {
				width : '200',
				title : '打赏人昵称',
				field : 'rewardUserName',
				formatter:function(value,row){
					var str = '';
					str += sy.formatString('<a href="javaScript:;" onclick="openUser(\'{0}\');">',row.rewardUserId);
					return str+row.rewardUserName+'</a>';
				}
			},
            {
				width : '100',
				title : '被打赏人昵称',
				field : 'rewardToUserName',
				formatter:function(value,row){
					var str = '';
					str += sy.formatString('<a href="javaScript:;" onclick="openUser(\'{0}\');">',row.rewardToUserId);
					return str+row.rewardToUserName+'</a>';
				}
			},
			 {
				width : '100',
				title : '专辑名称',
				field : 'interestTitle',
				formatter:function(value,row){
					var str = '';
					str += sy.formatString('<a href="javaScript:;" onclick="openInterest(\'{0}\');">',row.rewardInterestId);
					return str+row.interestTitle+'</a>';
				}
			},
			{
				width : '100',
				title : '打赏金额',
				field : 'rewardPrice',
			},  {
				width : '100',
				title : '支付方式',
				field : 'rewardPayeeType',
				formatter : function(value, row) {
					var str = '';
						if(row.rewardPayeeType==1){
							str = "账户余额";	
						}else if(row.rewardPayeeType==2){
							str = "支付宝";
						}else if(row.rewardPayeeType==3){
							str = "微信支付";
						}
					return str;
				}
			},
			{
				width : '150',
				title : '打赏时间',
				field : 'rewardCreateTime',
				sortable : true,
				formatter:function(value,row,index){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
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
								<td>打赏人昵称</td>
								<td><input name="rewardUserName" style="width: 80px;" /></td>
								<td>被打赏人昵称</td>
								<td><input name="rewardToUserName" style="width: 80px;" /></td>
				                <td>支付方式</td>
								<td><select name="rewardPayeeType" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">账户余额</option>
										<option value="2">支付宝</option>
										<option value="3">微信支付</option>
										</select>
								</td>
								<td>打赏时间</td>
								<td><input id="startTime" name="queryBeginRegisterTime" class="Wdate" 
						        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						        readonly="readonly" style="width: 150px;" />-
						        <input name="queryEndRegisterTime" class="Wdate easyui-validatebox" 
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