<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String contextPath = request.getContextPath();
    String id=request.getParameter("brandId");
    if(id==null){
	id="";
}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" src="<%=contextPath%>/jslib/plupload-2.0.0/js/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/jslib/plupload-2.0.0/js/i18n/zh_CN.js"></script>
 <link rel="stylesheet" href="<%=contextPath %>/jslib/kindeditor-4.1.7/themes/default/default.css" />
		<script charset="utf-8" src="<%=contextPath %>/jslib/kindeditor-4.1.7/kindeditor-min.js"></script>
		<script charset="utf-8" src="<%=contextPath %>/jslib/kindeditor-4.1.7/lang/zh_CN.js"></script>
		<script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="brandText"]', {
					 uploadJson : '<%=contextPath%>/mdmy/brand/plupload',
					allowFileManager : true,
					afterBlur: function(){this.sync();},
					minWidth : '580px',
					 items:[
					        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
					        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
					        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
					        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
					        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					        'italic', 'underline', 'strikethrough', 'lineheight', '|', 'image',
					         'table', 'hr', 'emoticons', 'pagebreak',
					        'anchor', 'link', 'unlink', '|', 'about']
				});
				K('input[name=getHtml]').click(function(e) {
					alert(editor.html());
				});
				K('input[name=isEmpty]').click(function(e) {
					alert(editor.isEmpty());
				});
				K('input[name=getText]').click(function(e) {
					alert(editor.text());
				});
				K('input[name=selectedHtml]').click(function(e) {
					alert(editor.selectedHtml());
				});
				K('input[name=setHtml]').click(function(e) {
					editor.html('<h3>Hello KindEditor</h3>');
				});
				K('input[name=setText]').click(function(e) {
					editor.text('<h3>Hello KindEditor</h3>');
				});
				K('input[name=insertHtml]').click(function(e) {
					editor.insertHtml('<strong>插入HTML</strong>');
				});
				K('input[name=appendHtml]').click(function(e) {
					editor.appendHtml('<strong>添加HTML</strong>');
				});
				K('input[name=clear]').click(function(e) {
					editor.html('');
				});
			});
			
		</script>
<script type="text/javascript">
var uploader;//上传对象
var submitNow = function($dialog, $grid, $pjq) {
	var url;
	 if ($(':input[name="brandId"]').val().length > 0) {
		url = '<%=contextPath%>/mdmy/brand/update';
	} else {
		url = '<%=contextPath%>/mdmy/brand/save';
	} 
	$.post(url, sy.serializeObject($('form')), function(result) {
		parent.sy.progressBar('close');//关闭上传进度条
        if(url=='<%=contextPath%>/mdmy/brand/save'){
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
	if ($(':input[name="brandId"]').val().length > 0) {
		 var brandSort=$('#brandSort').val();
		 $("#selected").combobox("setValue", brandSort);
	}
	uploader = new plupload.Uploader({//上传插件定义
		browse_button : 'pickfiles',//选择文件的按钮
		container : 'container',//文件上传容器
		runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
		//flash_swf_url : sy.contextPath + '/jslib/plupload_1_5_7/plupload/js/plupload.flash.swf',// Flash环境路径设置
		url : '<%=contextPath%>/mdmy/brand/upload',//上传文件路径
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
			parent.$.messager.alert('提示', '只允许选择一张图片！', 'error');
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
			$(':input[name="brandImgUrl"]').val(response.fileUrl);
		}
	});
	uploader.init();
});
     var submitForm = function($dialog, $grid, $pjq) {
    	 var brandContent=$('#brandText').val();
		 if(brandContent==""){
  		 	parent.$.messager.alert('提示', "品牌描述不能为空", 'error');
  		 	return;
  		 }
    	 var img=$('#photo').attr('src');
    	 if (uploader.files.length <1&&img=='') {
 			parent.$.messager.alert('提示', '请选择一张图片！', 'error');
 			uploader.stop();
 			return;
    	 }
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
  	 
 	//*****************会员选择*******************************//
 	var choose = function(id) {
 		$('#win').window('open');
	};
	var editFun=function(userId,userDesc){
			$(':input[name="userId"]').val(userId);
			$(':input[name="userDesc"]').val(userDesc);
			$('#win').window('close');
	}

	$(function() {
		$('#win').window('close');
		grid = $('#grida').datagrid({
				title : '',
				url : '<%=contextPath%>/mdmy/user/findAllCertUser',
				striped : true,
				rownumbers : true,
				pagination : true,
				singleSelect : true,
				sortName : 'userSort',
				sortOrder : 'asc',
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50],
				columns : [ [ {
					width : '130',
					title : '会员编号',
					field : 'userNo'
				} ,
				{
					width : '130',
					title : '会员简称',
					field : 'userSort',
					sortable : true
				} ,
				{
					width : '200',
					title : '会员全称',
					field : 'userDesc'
				} ,
				 {
					title : '操作',
					field : 'action',
					width : '50',
					formatter : function(value, row) {
						var str = '';
							str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="选择" onclick="editFun(\'{0}\',\'{1}\');"/>', row.userId,row.userDesc);
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
	//*****************会员选择*******************************//
</script>
</head>
<body>
	<form method="post" class="form" >
		<input name="brandId" value="<%=id %>" type="hidden"/>
		<input id="brandSort" value="${news.newsSort}" type="hidden"/>
		<fieldset>
			<legend>品牌设置</legend>
			<table class="table" style="width: 100%;">
				    <tr>
					<th>品牌名称</th>
					<td><input name="brandTitle"  class="easyui-validatebox" data-options="required:true" value="${brand.brandTitle }" /></td>
					</tr>
					<tr>
					<th>品牌描述</th>
				    <td>
				   <textarea id="content" name="brandText"  style="width:580px;height:200px;visibility:hidden;">${brand.brandText }</textarea>
				    </td>
				   </tr>
				   <tr>
					  <th>所属会员</th>
					  <td><input name="userId" id="userId" class="easyui-validatebox" readonly="readonly" data-options="required:true" type="hidden" />
					      <input id="userDesc" name="userDesc" class="easyui-validatebox" readonly="readonly"></input>
					      <img class="iconImg ext-icon-note_edit" title="选择" onclick="choose()"/>
					  </td>
				   </tr>
					<tr>
					<th>品牌排序</th>
					<td>
					<select id="selected" class="easyui-combobox" name="brandSort"  data-options="panelHeight:'200',editable:false" style="width: 155px;">
							<c:forEach begin="1" end="100" varStatus="c">
								<option value="${c.count}" >${c.count}</option>
							</c:forEach>
					</select>
					</td>
					</tr>
					<tr>
					<th>封面图片</th>
					<td><div id="container">
							<a id="pickfiles" href="javascript:void(0);" class="easyui-linkbutton"   data-options="iconCls:'ext-icon-zoom'" >上传图片</a>
							<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
						</div>
					<input name="brandImgUrl" readonly="readonly" style="display: none;" /> <img id="photo" src="${brand.brandImgUrl}" style="width: 200px; height: 200px;cursor:default"></td>
					</tr>
				    <tr>
					<th>生产企业</th>
					<td><input name="brandEnterprise"  class="easyui-validatebox" data-options="required:false" value="${brand.brandEnterprise }" /></td>
					</tr>
					
			</table>
		</fieldset>
	</form>
	<!-- 选择会员 -->
	 <div id="win" class="easyui-window" title="选择会员"   style="width: 600px;height: 400px"
        data-options="iconCls:'icon-save',modal:true">
		<div id="toolbar">
			<form id="searchForm">
				<table>
			    <tr>
					<td>会员编号</td>
					<td><input  name="userNO" style="width: 60px" ></input></td>
				    <td>会员简称</td>
					<td><input  name="userSort" style="width: 90px" ></input></td>
				    <td>会员全称</td>
					<td><input  name="userDesc" style="width: 150px" ></input></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
				</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',fit:true,border:false">
			<table id="grida" data-options="border:false"></table>
		</div>
	</div>	
</body>
</html>