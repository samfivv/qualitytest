<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
String id=request.getParameter("copywriterId");
if(id==null){
	id="";
}
String version=request.getParameter("copywriterVersion");
if(version==null){
   version="";
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
$('#addButton').linkbutton('disable');
var submitNow = function($dialog, $grid, $pjq) {
	var url;
	 if ($(':input[name="copywriterId"]').val().length > 0) {
		url = '<%=contextPath%>/mdmy/copyWriter/update';
	} else {
		url = '<%=contextPath%>/mdmy/copyWriter/save';
	} 
	$.post(url, sy.serializeObject($('form')), function(result) {
		parent.sy.progressBar('close');//关闭上传进度条
        if(url=='<%=contextPath%>/mdmy/copyWriter/save'){
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
};

$(function() {
	if ($(':input[name="copywriterId"]').val().length > 0) {
		 parent.$.messager.progress({
			text : '数据加载中....' 
		});  
		$.post('<%=contextPath%>/mdmy/copyWriter/findById', {
			copywriterId : $(':input[name="copywriterId"]').val()
		}, function(result) {
			if (result.copywriterId != undefined) {
				if(result.copywriterType==null){
					result.copywriterType==1;
				} 
			 if(result.copywriterType==2){
					$('#hidden').show();
				}else{
					$('#hidden').hide();
				}
				$('form').form('load', {
					'copywriterDesc' : result.copywriterDesc,
					'copywriterUrl' : result.copywriterUrl,
				    'copywriterImgUrl' : result.copywriterImgUrl, 
					'copywriterSort' : result.copywriterSort,
					'copywriterType' : result.copywriterType,
					'copywriterInterestId' : result.copywriterInterestId,
				});
				if (result.copywriterImgUrl) {
					$('#photo').attr('src', result.copywriterImgUrl);
				}
			}
			parent.$.messager.progress('close');
		}, 'json');
	}else{
		$('#photo').hide();
	}
	$("#hidden").hide();
	$("#interestId").attr("class","");
	$('#select').combobox({
		onChange:function(n,o){
			if(n=='2'){
				$('#hidden').show();
					$("#interestId").attr("class","easyui-validatebox");
					$("#interestId").focus();
					var copywriterInterestId=$('#copywriterUrl').val();
					var interestId=copywriterInterestId.substring(copywriterInterestId.lastIndexOf("/")+1, copywriterInterestId.lastIndexOf("."));
					$('#interestId').val(interestId);
			}else{
				$("#hidden").hide();
				$("#interestId").attr("class","");
			}
		}
	});
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
			$(':input[name="copywriterImgUrl"]').val(response.fileUrl);
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
function interestIdBlur(){
	var copywriterInterestId=$('#copywriterUrl').val();
	var interestId=copywriterInterestId.substring(copywriterInterestId.lastIndexOf("/")+1, copywriterInterestId.lastIndexOf("."));
	$('#interestId').val(interestId);
}
</script>
</head>
<body>
	<form method="post" class="form" >
		<input name="copywriterId" value="<%=id %>" type="hidden"/>
		<input name="copywriterVersion" value="<%=version%>" type="hidden"/>
		<fieldset>
			<legend>文案设置</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>文案描述</th>
					<td><input name="copywriterDesc"  class="easyui-validatebox" data-options="required:true" /></td>
					<th>文案跳转链接</th>
					<td><input id="copywriterUrl" name="copywriterUrl" class="easyui-validatebox" onblur="interestIdBlur()" data-options="panelHeight:'auto',editable:false,required:true" style="width:150px"/></td>
					</tr>
					<tr>
					<th>文案图片链接</th>
					<td><div id="container">
							<a id="pickfiles" href="javascript:void(0);" class="easyui-linkbutton"   data-options="iconCls:'ext-icon-zoom'">上传图片</a>
							<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
						</div></td>
						<th></th>
					<td><input name="copywriterImgUrl" readonly="readonly" style="display: none;" /> <img id="photo" src="" style="width: 200px; height: 200px;cursor:default"></td>
					</tr>
					<tr>
					<th>文案排序</th>
					<td>
					<select id="selected" class="easyui-combobox" name="copywriterSort" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1" >1</option>
							<option value="2">2</option>
							<option value="3" >3</option>
							<option value="4">4</option>
							<option value="5" >5</option>
							<option value="6">6</option>
							<option value="7" >7</option>
							<option value="8">8</option> 
					</select>
					</td>
					<th>文案类型</th>
					<td>
					<select id="select" class="easyui-combobox" name="copywriterType" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1" >其他链接</option>
							<option value="2">内部专辑</option>
					</select>
					</td>
					</tr>
					<tr id="hidden">
					<th>兴趣Id</th>
				    <td>
				    <input id="interestId" name="copywriterInterestId" class="easyui-validatebox" data-options="required:true" />
				    </td>
				</tr>
				<tr>
				<th>页面</th>
				<td>
				<select id="page" class="easyui-combobox" name="copywriterPage" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1" >网站首页</option>
							<option value="2">攻略首页</option>
							<option value="3">作品首页</option>
				</select>
				</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>