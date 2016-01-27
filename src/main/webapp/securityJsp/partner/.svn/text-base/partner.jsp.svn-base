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
		var helpSort="partnerCreateTime";
		var helpOrder="desc";
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
		var addFun = function() {
			var dialog = parent.sy.modalDialog({
				title : '添加合作伙伴',
				url : sy.contextPath + '/securityJsp/partner/partnerForm.jsp',
				buttons : [ {
					text : '添加',
					id:"addButton",
					handler : function() {
						dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
					}
				} ]
			});
		};
		var showFun = function(id) {
			var dialog = parent.sy.modalDialog({
				title : '查看合作伙伴信息',
				url : sy.contextPath + '/securityJsp/partner/partnerForm.jsp?partnerId=' + id+"&&lookInfo=true"
			});
		};
		var editFun = function(id) {
			var dialog = parent.sy.modalDialog({
				title : '编辑合作伙伴信息',
				url : sy.contextPath + '/securityJsp/partner/partnerForm.jsp?partnerId=' + id,
				buttons : [ {
					text : '编辑',
					handler : function() {
						dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
					}
				} ]
			});
		};
		var removeFun = function(id) {
			parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
				if (r) {
					$.post('<%=contextPath%>/mdmy/partner/deletePartner', {
						partnerId : id
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
		f.action = sy.contextPath + '/mdmy/partner/exportPartner?'+$form.serialize()+'&sort='+helpSort+'&order='+helpOrder;
		f.submit();
	}; 

	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/partner/findByConditions',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			sortName : 'partnerCreateTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ {
				width : '200',
				title : '合作伙伴名称',
				field : 'partnerName',
			},{
				width : '200',
				title : '合作伙伴链接',
				field : 'partnerUrl',
			},{ 
				width : '300',
				title : '合作伙伴图片地址',
				field : 'partnerImgUrl',
			} ,{
				width : '200',
				title : '合作伙伴类型',
				field : 'partnerType',
				formatter : function(value, row) {
					var str = '';
					  if(row.partnerType==1){
						 str='文字链接';
					 }else if(row.partnerType==2){
						 str='图片链接';
					 } 
					 return str;	
				}
			} ,{
				width : '180',
				title : '创建时间',
				field : 'partnerCreateTime',
				formatter:function(value,row,index){
					if(value!=null){
		             var unixTimestamp = new Date(value);
		             return unixTimestamp.Format("yyyy-MM-dd hh:mm:ss");
					}
		        },
				sortable : true
			},{
				title : '操作',
				field : 'action',
				width : '90',
				formatter : function(value, row) {
					var str = '';
					 str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>', row.partnerId);
					str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>',row.partnerId);
					str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.partnerId);
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
				helpSort=sort;
				helpOrder=order;
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
	<div id="toolbar" >
					<form id="searchForm">
						<table>
							<tr>
							    <td>合作伙伴名称</td>
								<td><input id="searchBox" name="partnerName" style="width: 60px" ></input></td>
								<td>合作伙伴类型</td>
									<td><select id="selected" class="easyui-combobox" name="partnerType" data-options="panelHeight:'auto',editable:false" style="width: 120px;">
											<option value="">请选择</option>
											<option value="1">文字链接</option>
											<option value="2">图片链接</option>
									</select></td>
								<td>创建时间</td>
								<td><input id="startTime" name="queryTimeBegin" class="Wdate" 
										        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										        readonly="readonly" style="width: 150px;" /> -
										        <input name="queryTimeEnd" class="Wdate easyui-validatebox" 
										        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
										        data-options="validType:'eqDate[\'#startTime\']'" readonly="readonly" 
										        style="width: 150px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
					<table>
						<tr>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="exportFun()">导出</a></td>
						</tr>
					</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>