<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.midai.miya.sys.model.Role"%>

<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("circleId");
	if (id == null) {
		id = "";
	}
	String lookInfo = request.getParameter("lookInfo");
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
	var submitNow = function($dialog, $grid, $pjq) {
		$('#addButton').linkbutton('disable');
		if ($('form').form('validate')) {
			var url;
			if ($('#circleId').val().length > 0) {
				url = '<%=contextPath%>/mdmy/circle/updateCircle';
			} else {
				url = '<%=contextPath%>/mdmy/circle/saveCircle';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				parent.sy.progressBar('close');//关闭上传进度条
				if(url=='<%=contextPath%>/mdmy/circle/saveCircle'){
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
		if ($('#circleId').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post('<%=contextPath%>/mdmy/circle/findCircleById', {
				circleId : $('#circleId').val()
			}, function(result) {
				if (result.circleId != undefined) {
					$('form').form('load', {
						'circleName' : result.circleName,
						'circleDesc' : result.circleDesc,
						'circleState' : result.circleState,
						'circleImgUrl' : result.circleImgUrl,
						'circleSort' : result.circleSort,
						'unpassDesc' : result.unpassDesc
					});
					if(result.circleImgUrl){
						$('#photo').attr('src',result.circleImgUrl);
					}
					if(result.circleState==2){
						$('#unpass').show();
					}else{
						$('#unpass').hide();
						$("#unpassDesc").attr("class","");
					}
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		$('#selected').combobox({
			onChange: function (n,o) {
				if(n==2){
					$('#unpass').show();
					$('#unpassDesc').focus();
				}else{
					$('#unpass').hide();
					$("#unpassDesc").attr("class","");
				}
			}
		})
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
				$(':input[name="circleImgUrl"]').val(response.fileUrl);
			}
		});
		uploader.init();

	});

	var submitForm = function($dialog, $grid, $pjq) {
		$('#addButton').linkbutton('disable');
			if ($('form').form('validate')) {
				if (uploader.files.length > 0) {
					uploader.start();
					uploader.bind('StateChanged', function(uploader) {// 在所有的文件上传完毕时，提交表单
						if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
							uploader.unbind('StateChanged');
							submitNow($dialog, $grid, $pjq);
						}
					});
				} else {
					submitNow($dialog, $grid, $pjq);
				}
			}
	};
	
</script>
</head>
<body>
	<form method="post" class="form">
	<input id="circleId" name="circleId" value="<%=id%>" type="hidden"/>
		<fieldset>
			<legend>圈子基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>圈子名称</th>
					<td><input  name="circleName" class="easyui-validatebox"  data-options="required:true"></input></td>
				</tr>
				<tr>
					<th>圈子简介</th>
					<td><textarea rows="3" cols="3" name="circleDesc" class="easyui-validatebox"  data-options="required:true"></textarea></td>
				</tr>
				<tr>
					<th>圈子状态</th>
					<td><select id="selected" class="easyui-combobox" name="circleState" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							    <option value="1" >通过</option>
								<option value="2">不通过</option>
								<option value="3">审核中</option>
								<option value="4">已关闭</option>
								<option value="5">申诉中</option>
					</select></td>
				</tr>
				<tr id="unpass">
					<th>不通过原因</th>
					<td><textarea rows="3" cols="3" name="unpassDesc" id="unpassDesc" class="easyui-validatebox"  data-options="required:true"></textarea></td>
				</tr>
				<tr>
					<th>排序</th>
					<td><input name="circleSort" class="easyui-numberspinner" data-options="required:true,min:1,max:100000,editable:false" style="width: 155px;" value="1" /></td>
				</tr>
				<tr>
					<th>图片地址</th>
					<td><div id="container">
							<a id="pickfiles" href="javascript:void(0);" class="easyui-linkbutton"   data-options="iconCls:'ext-icon-zoom'">上传图片</a>
							<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
						</div></td>
					</tr>
					<tr>
					<th></th>
					<td><input name="circleImgUrl" readonly="readonly" style="display: none;" /> <img id="photo" src="" style="width: 200px; height: 200px;cursor:default"></td>
					</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>