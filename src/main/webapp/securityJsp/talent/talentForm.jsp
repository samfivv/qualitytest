<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("recommendUserId");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
 <script type="text/javascript" src="<%=contextPath%>/jslib/plupload-2.0.0/js/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/jslib/plupload-2.0.0/js/i18n/zh_CN.js"></script>
<script type="text/javascript">
var uploader;//上传对象
	var submitNow = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="recommendUserId"]').val().length > 0) {
				url = '<%=contextPath%>/mdmy/recommendUser/updateTalent';
			} else {
				url = '<%=contextPath%>/mdmy/recommendUser/saveTalent';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				parent.sy.progressBar('close');//关闭上传进度条
				if(url=='<%=contextPath%>/mdmy/recommendUser/saveTalent'){
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
		if ($(':input[name="recommendUserId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/recommendUser/findByTalentId', {
				recommendUserId : $(':input[name="recommendUserId"]').val()
			}, function(result) {
				if (result.recommendUserId != undefined) {
					$('form').form('load', {
						'userNicknamea' : result.userNickname,
						'userMaila' : result.userMail,
						'recommendSorta' : result.recommendSort,
						'recommendImgUrl':result.recommendImgUrl,
						'recommendDesc':result.recommendDesc,
						'recommendLocation':result.recommendLocation
					});
					if(result.recommendImgUrl){
						$('#photo').attr('src',result.recommendImgUrl);
					}
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		uploader = new plupload.Uploader({//上传插件定义
			browse_button : 'pickfiles',//选择文件的按钮
			container : 'container',//文件上传容器
			runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
			//flash_swf_url : sy.contextPath + '/jslib/plupload_1_5_7/plupload/js/plupload.flash.swf',// Flash环境路径设置
			url : '<%=contextPath%>/mdmy/copyWriter/plupload',//上传文件路径
			max_file_size : '5mb',//100b, 10kb, 10mb, 1gb
			chunk_size : '10mb',//分块大小，小于这个大小的不分块
			unique_names : true,//生成唯一文件名
			// 如果可能的话，压缩图片大小
			/*resize : {
				width : 320,
				height : 240,
				quality : 90
			},*/
			// 指定要浏览的文件类型
			filters : [ {
				title : '图片文件',
				extensions : 'jpg,gif,png'
			} ]
		});
		uploader.bind('Init', function(up, params) {//初始化时
			//$('#filelist').html("<div>当前运行环境: " + params.runtime + "</div>");
			$('#filelist').html("");
		});
		uploader.bind('BeforeUpload', function(uploader, file) {//上传之前
			if (uploader.files.length > 1) {
				parent.$.messager.alert('提示', '只允许选择一张照片！', 'error');
				uploader.stop();
				return;
			}
			$('.ext-icon-cross').hide();
		});
		uploader.bind('FilesAdded', function(up, files) {//选择文件后
			$.each(files, function(i, file) {
				$('#filelist').append('<div id="' + file.id + '">' + file.name + '(' + plupload.formatSize(file.size) + ')<strong></strong>' + '<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor:pointer;" class="ext-icon-cross" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>');
			});
			up.refresh();
		});
		uploader.bind('UploadProgress', function(up, file) {//上传进度改变
			var msg;
			if (file.percent == 100) {
				msg = '99';//因为某些大文件上传到服务器需要合并的过程，所以强制客户看到99%，等后台合并完成...
			} else {
				msg = file.percent;
			}
			$('#' + file.id + '>strong').html(msg + '%');

			parent.sy.progressBar({//显示文件上传滚动条
				title : '文件上传中...',
				value : msg
			});
		});
		uploader.bind('Error', function(up, err) {//出现错误
			$('#filelist').append("<div>错误代码: " + err.code + ", 描述信息: " + err.message + (err.file ? ", 文件名称: " + err.file.name : "") + "</div>");
			up.refresh();
		});
		uploader.bind('FileUploaded', function(up, file, info) {//上传完毕
			var response = $.parseJSON(info.response);
			if (response.status) {
				$('#' + file.id + '>strong').html("100%");
				//console.info(response.fileUrl);
				//console.info(file.name);
				//$('#f1').append('<input type="hidden" name="fileUrl" value="'+response.fileUrl+'"/>');
				//$('#f1').append('<input type="hidden" name="fileName" value="'+file.name+'"/><br/>');
				$(':input[name="recommendImgUrl"]').val(response.fileUrl);
			}
		});
		uploader.init();

	});

	var submitForm = function($dialog, $grid, $pjq) {
			if ($('form').form('validate')) {
				var recommendDesc=$('#recommendDesc').val().replace(/(^\s+)|(\s+$)/g, "");
				if(recommendDesc==''){
					parent.$.messager.alert('提示', '推荐理由不能为空！', 'error');
					return;
				}else{
					$('#recommendDesc').val($('#recommendDesc').val().trim());
				}
				if (uploader.files.length > 0) {
					uploader.start();
					uploader.bind('StateChanged', function(uploader) {// 在所有的文件上传完毕时，提交表单
						if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
							uploader.unbind('StateChanged');
							submitNow($dialog, $grid, $pjq);
						}
					});
				} else {
					if ($(':input[name="recommendUserId"]').val().length > 0 || $(':input[name="recommendLocation"]').val()==2){
						submitNow($dialog, $grid, $pjq);
					}else{
					parent.$.messager.alert('提示', '请选择一张照片！', 'error');
					uploader.stop();
					return;
					}
				}
			}
	};
var choose = function() {
	    $('#win').window('open');
		/* 
		var dialog = parent.sy.modalDialog({
			title : '编辑角色信息',
			url : sy.contextPath + '/securityJsp/operator/test.jsp',
			buttons : []
		}); */
	};
	var editFun=function(id,userNickname,userMail){
		$("#userNickname").val(userNickname);
		$("#userId").val(id);
		$("#userMail").val(userMail);
		 $('#win').window('close');
	}
	$(function() {
		$('#win').window('close');
		grid = $('#grida').datagrid({
			title : '',
			url : '<%=contextPath%>/mdmy/recommendUser/findAllTalentUser',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50],
			columns : [ [{
				width : '150',
				title : '邮箱',
				field : 'userMail'
			},{
				width : '150',
				title : '昵称',
				field : 'userNickname',
			},
			 {
				width : '190',
				title : '个性签名',
				field : 'userSignature'
			},  
			 {
				title : '操作',
				field : 'action',
				width : '50',
				formatter : function(value, row) {
					var str = '';
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="选择" onclick="editFun(\'{0}\',\'{1}\',\'{2}\');"/>', row.userId,row.userNickname,row.userMail);
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
	});
</script>
</head>
<body>
	<form method="post" class="form">
	<input name="recommendUserId" value="<%=id%>" type="hidden"/>
	<input name="userId" id="userId" value="" type="hidden"/>
		<fieldset>
			<legend>达人基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>达人昵称</th>
					<td><input name="userNicknamea" id="userNickname" class="easyui-validatebox" readonly="readonly" data-options="required:true" /></td>
					<td><img class="iconImg ext-icon-note_edit" title="选择" onclick="choose()"/></td>
				</tr>
				<tr>
					<th>邮箱</th>
				   <td><input  name="userMaila" id="userMail" style="width: 150px" readonly="readonly"></input></td>
				</tr>
				
				<tr>
					<th>推荐位置</th>
					<td>
					<select id="selected" class="easyui-combobox" name="recommendLocation" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1" >首页</option>
							<option value="2">达人首页</option>
					</select>
					</td>
				</tr>
				
				<tr>
					<th>推荐排序</th>
					<td>
					<select id="selected" class="easyui-combobox" name="recommendSorta" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1" >1</option>
							<option value="2">2</option>
							<option value="3" >3</option>
							<option value="4">4</option>
							<option value="5" >5</option>
							<option value="6">6</option>
							<option value="7" >7</option>
							<option value="8">8</option>
							<option value="9">9</option>
					</select>
					</td>
				</tr>
				<tr>
				<th>推荐图片</th>
					<td><div id="container">
							<a id="pickfiles" href="javascript:void(0);" class="easyui-linkbutton"   data-options="iconCls:'ext-icon-zoom'">上传图片</a>
							<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
						</div></td>  
				</tr>
				<tr>
				<th></th>
					<td><input name="recommendImgUrl" readonly="readonly" style="display: none;" /> <img id="photo" src="" style="width: 200px; height: 200px;cursor:default"></td>                        
				</tr>
				<tr>
				<th>推荐理由</th>
				<td> <textarea rows="3" cols="5" id="recommendDesc" name="recommendDesc" class="easyui-validatebox" data-options="required:true" style="width: 480px"></textarea> </td>
				</tr>
			</table>
		</fieldset>
	</form>
	 <div id="win" class="easyui-window" title="选择达人"   style="width: 600px;height: 400px"
        data-options="iconCls:'icon-save',modal:true">
	<div id="toolbar">
	<form id="searchForm">
		<table>
		    <tr>
		        <td>邮箱</td>
				<td><input  name="userMail" style="width: 60px" ></input></td>
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