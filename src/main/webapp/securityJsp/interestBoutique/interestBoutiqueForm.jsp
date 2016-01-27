<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String id = request.getParameter("interestBoutiqueId");
	String type = request.getParameter("type");
	if (id == null) {
		id = "";
	}
	if(type == null){
		type="";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style type="text/css">
#can3{
    position:fixed;
	height:500px;
	width:700px;
	top:32%;
	left: 44%;
	margin: -160px 0 0 -297px;
	z-index:1056;
	background:white;
   }
.opacity_bg{
	width:100%;
	height:100%;
	position:fixed;
	top:0;
	left:0;
	background-color:#000;
	opacity:0.6;
	filter:alpha(opacity=60);
	margin:auto;
	z-index:1049;
	display:none;
    }
</style>
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=contextPath%>/jslib/json2.js" charset="utf-8"></script>
<script type="text/javascript">
var grid;
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
$('#addButton').linkbutton('disable');
	var submitForm = function($dialog, $grid, $pjq) {
		alert(1)
		$('#addButton').linkbutton('disable');
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="interestBoutiqueId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/interestBoutique/updateInterestBoutique';
			} else {
				url = '<%=contextPath%>/mdmy/interestBoutique/saveInterestBoutique?interesttype='+$("#interesttype").val();
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if(url=='<%=contextPath%>/mdmy/interestBoutique/saveInterestBoutique'){
					if (result.success) {
						$pjq.messager.alert('提示', result.msg, 'info');
						$grid.datagrid('load');
						$dialog.dialog('destroy');
					} else {
						$pjq.messager.alert('提示', result.msg, 'error');
					}
				}else{
				if (result.success) {
					$pjq.messager.alert('提示', result.msg, 'info');
					$grid.datagrid('reload');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
				}
			}, 'json');
		}
	};
	$(function() {
		if ($(':input[name="interestBoutiqueId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/interestBoutique/findInterestBoutiqueById', {
				interestBoutiqueId : $(':input[name="interestBoutiqueId"]').val()
			}, function(result) {
				if (result.interestBoutiqueId != undefined) {
					$('form').form('load', {
						'interestTitle_a' : result.interestTitle,
						'productionInterestType':result.productionInterestType,
						'interestBoutiqueSort' : result.interestBoutiqueSort,
						'interestBigLongImgUrl' : result.interestBigLongImgUrl
					});
					$('#interestBigLongImgUrl').attr('src',result.interestBigLongImgUrl);
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		$('#win').window('close');
	});
var choose = function() {
	    $('#win').window('open');
	    var url;
		if ($(':input[name="interestBoutiqueId"]').val().length > 0) {
			url='<%=contextPath%>/mdmy/interestApproval/findAllInterestByUser?interesttype='+$('#productionInterestType').val();
		}else{
			url='<%=contextPath%>/mdmy/interestApproval/findAllInterestByUser?interesttype='+$("#interesttype").val();
		}
		grid = $('#grida').datagrid({
			title : '',
			url : url,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [ 
			 			{
				width : '250',
				title : '专辑ID',
				field : 'interestId'
			},  {
				width : '150',
				title : '专辑标题',
				field : 'interestTitle'
			},  
			 {
				width : '150',
				title : '专辑简介',
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
			}, {
				width : '120',
				title : '推荐数量',
				field : 'interestPraiseCount',
				sortable : true
			}, 
			 {
				title : '操作',
				field : 'action',
				width : '50',
				formatter : function(value, row) {
					var str = '';
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="选择" onclick="editFun(\'{0}\',\'{1}\');"/>', row.interestTitle,row.interestId);
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
			},
		});
		$('form :input').keyup(function(event) {
			if (event.keyCode == 13) {
				grid.datagrid('load',sy.serializeObject($('#searchForm')));
			}
		});
	};
	var editFun=function(interestTitle,interestId){
		$("#interestTitle").val(interestTitle);
		$('#interestIdd').val(interestId);
		 $('#win').window('close');
	}
	$(document).on('click','div.opacity_bg',function(){
		$('#can3').hide();
		$('.opacity_bg').hide();
	});

	function loadMeiTu(interestBigLongImgUrl){
		$('#can3').show();
		$('.opacity_bg').show();
		xiuxiu.setLaunchVars("cropPresets", "9:16");
		xiuxiu.setLaunchVars("cameraEnabled", 0); 
		xiuxiu.setLaunchVars("customMenu", []);   
		xiuxiu.setLaunchVars("avatarPreview", {visible:true,large:{width:140,height:140, label:"预览"}}); 
	       /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
		xiuxiu.embedSWF("altContent",5,"100%","100%","exitId");
		  
	       //修改为您自己的图片上传接口
		xiuxiu.setUploadURL('<%=basePath%>/mdmy/interestBoutique/load');
	        xiuxiu.setUploadType(2);
	        xiuxiu.setUploadDataFieldName("upload_file");
	        
		xiuxiu.onInit = function ()
		{
			xiuxiu.loadPhoto("http://open.web.meitu.com/sources/images/1.jpg");
		}	
		xiuxiu.onUploadResponse = function (result)
		{   
			var obj=JSON.parse(result);
			if(obj.success){
				 var id='#'+interestBigLongImgUrl;
					$(id).attr('src',obj.data);
					$('#interestBigLong').val(obj.data);
					$('#can3').hide();
					$('.opacity_bg').hide();
			}else{
				$('#can3').hide();
				$('.opacity_bg').hide();
				myw_alert(obj.message);
				return;
			}
		}
		 
	}
	$(document).on('click','#btn',function(){
		loadMeiTu('interestBigLongImgUrl');
	});	
</script>
</head>
<body>
<div class="opacity_bg" style='display:none'></div>
	<input name="interesttype" id="interesttype" value="<%=type %>" type="hidden"/>
	<form method="post" class="form">
	<input name="interestBoutiqueId" value="<%=id%>" type="hidden"/>
	<input name="interestId" id="interestIdd" type="hidden"/>
    <input name="productionInterestType" id="productionInterestType" type="hidden"/>
		<fieldset>
			<legend>攻略基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>攻略标题</th>
					<td><input name="interestTitle_a" id="interestTitle" class="easyui-validatebox" readonly="readonly" data-options="required:true" /></td>
					<td><img class="iconImg ext-icon-note_edit" title="选择" onclick="choose()"/></td>
				</tr>
				<tr>
					<th>推荐排序</th>
					<td>
					<input name="interestBoutiqueSort" class="easyui-numberspinner" data-options="required:true,min:1,max:100000,editable:false" style="width: 155px;" value="1" />
					</td>
				</tr>
				<tr>
					<th>图片</th>
					<td>
					<input id="interestBigLong" name="interestBigLongImgUrl" readonly="readonly" style="display: none;" />
					<img id="interestBigLongImgUrl"  style="width: 180px; height: 320px;cursor:default"/>
					<input id="btn" type="button" style="cursor: pointer;" value="上传图片"/>
					<div id="can3" style='display:none'>
						<div id="altContent">
						</div>
					</div></td>
				</tr>
			</table>
		</fieldset>
	</form>
	 <div id="win" class="easyui-window" title="选择攻略"   style="width: 600px;height: 400px"
        data-options="iconCls:'icon-save',modal:true">
	<div id="toolbar">
	<form id="searchForm">
		<table>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;专辑&nbsp;ID</td>
								<td><input id="searchBox" name="interestId" style="width: 60px" ></input></td>
							    <td>专辑标题</td>
								<td><input id="searchBox" name="interestTitle" style="width: 60px" ></input></td>
								<td>创建人</td>
								<td><input id="searchBox" name="userNickname" style="width: 60px" ></input></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grida" data-options="border:false" style="height: 360px"></table>
	</div>
	</div>
</body>
</html>