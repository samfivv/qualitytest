<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style type="text/css">
img{
width:180px;
height:180px;
}
#imgs{
width:400px;
height:300px;
}
.aa{
position: absolute;
right:0px;
bottom:0px;
width: 12px
}
</style>
<script type="text/javascript">
var grid;
 var openwin = function(src,state,photoId,photoName,i) {
	 $('#win').window('open');
	 $('#photoNames').html(photoName);
	 $("#imgs").attr('src',src);
	 if(state==1){
		 var div="<input class='aa' type='button' id='photoBtn' value='删除' onclick='edit(\""+photoId+"\",\""+i+"\")'/>";
	 }else{
		 var div="<input class='aa' type='button' id='photoBtn'  value='恢复' onclick='edit(\""+photoId+"\",\""+i+"\")'/>";
	 }
	 $('#edit').append(div);
}; 
var edit=function(photoId,i){
	parent.$.messager.confirm('询问', '您确定要修改吗？', function(r) {
		if (r) {
			$.post(sy.contextPath + '/mdmy/photo/update', {
				'photoId' :photoId
			}, function() {
				$('#win').window('close'); 
				var navigatorName = "Microsoft Internet Explorer"; 
				if(navigator.appName == navigatorName){
					$('#img'+i+'').removeNode(true);
				}else{
					$('#img'+i+'').remove();
				}
			}, 'json');
		}
	});
}
$(function(){
	var page=0;
	  $('body').data('page',page);
	  var row=36;
	  $('body').data('row',row);
	search(page,row);
})
  function search(page,row){
	  $('#win').window('close');
	  var photoName=$('#photoName').val();
	  var photoState=document.getElementById("photoState").value;
	  var userNickname=$('#userNickname').val();
				    $.ajax({
						  type:"post",
						  url:'<%=contextPath %>/mdmy/photo/findAllPhoto',
						  data:{"photoName":photoName,"photoState":photoState,"userNickname":userNickname,"page":$('body').data('page'),"row":$('body').data('row')},
						  dataType:'json',
						  success:function(result){
							  if(result.success){
								  var photos=result.data.photos;
								  var count=result.data.count;
								  $('#photo').html("");
								  if(photos!=null){
									  for(var i=0;i<photos.length;i++){
										  var src=photos[i].photoPath;
										  var state=photos[i].photoState;
										  var photoId=photos[i].photoId;
										  var photoName=photos[i].photoName;
										  var div='<div  style="float: left; width:180px">'+
										  "<img src='"+src+"' id='img"+i+"' onclick='openwin(\""+src+"\",\""+state+"\",\""+photoId+"\",\""+photoName+"\",\""+i+"\")'/>";
										  '</div>';
										  $('#photo').append(div);
									  }
								  }
								  $('#pp').pagination({
								        total:count,
								        pageSize:36,
								        pageList: [36],
								        onSelectPage: function(pageNumber, pageSize){
								        	var page=app(pageNumber-1,pageSize);
								        	$('body').data('page',page);
								        	 page=$('body').data('page');
								            $('#grid').panel('refresh', search(page,row));
								            }
								    });
							  }
						  }
					  });
  }
function app(a,b){
    sumc=a*b;
    return sumc;
    }
    function changePage(){
    	 $('body').data('page',0);
    }
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<form action="" method="post"  id="grid" class="easyui-panel" data-options="region:'center',fit:true,border:false">
相片名称<input type="text" id="photoName" name="photoName" value="" style="width:100px;"/>
用户昵称<input type="text" id="userNickname" name="userNickname" value="" style="width:100px;"/>
相片状态<select id="photoState" name="photoState"  onchange="changePage()"style="width: 155px;">
<option value="1">正常</option>
<option value="2">后台删除</option>
</select>
<input type="button" id="photoBtn"  value="查询" onclick="search()" style="width:100px;"/>
<br>
<br/>
<div id="photo" class="photo" data-options="fit:true,border:false"></div>
</form>
<div id="pp" class="easyui-pagination"  data-options="region:'center',fit:true" style="border:1px solid #ddd;"></div>
  <div id="win" class="easyui-window" title="图片详情"   style="width: 600px;height: 400px"
        data-options="iconCls:'icon-save',modal:true">
        <form method="post" class="form">
	<input name="photoState" type="hidden" value=""/>
	<input name="photoId" type="hidden" value=""/>
		<fieldset>
			<legend>图片详情</legend>
			<div align="center" id="photoNames"></div>
			<div align="center">
				 <img alt="" id="imgs" src=""/>
				 </div>
				 <div id="edit" >
				</div> 
		</fieldset>
	</form>
	</div>  
</body>
</html>