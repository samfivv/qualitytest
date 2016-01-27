<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String contextPath = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
String id = request.getParameter("questionnaireId");
if (id == null) {
	id = "";
}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style type="text/css">
#tab1{
width:60%;
}
</style>
  <script src="<%=contextPath%>/jslib/echarts/build/dist/echarts.js"></script>
  <script type="text/javascript">
function search(id){
	   $.post( sy.contextPath + '/mdmy/questionnaireController/findQuestion',{'questionnaireId':id}, function(result) {
		   
			if(result!=null){
					var end ='';
					var questionnaireQuestionList=result.questionnaireQuestionList;
					var html1='<tr><td colspan="3">截止到&nbsp;<font color="red">'+result.newDate+'</font></td></tr><tr><td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共收到&nbsp;<font color="red">'+result.count+'</font>&nbsp;份问卷</td></tr>';
					for(var i=0;i<questionnaireQuestionList.length;i++){
						var data1=new Array();
						var data2=new Array();
						var time=new Array();
					    var html='<tr><td>'+'<table border="0" cellspacing="0" cellpadding="0" id='+"t"+(i+1)+'><tr>'+(i+1)+"、"+questionnaireQuestionList[i].questionnaireQuestionTitle+'</tr>';
						var questionnaireOptionList=questionnaireQuestionList[i].questionnaireOptionList;
						var optiontitle='';
						for(var j=0;j<questionnaireOptionList.length;j++){
							if(j==0){
								optiontitle='A';
							}else if(j==1){
								optiontitle='B';
							}else if(j==2){
								optiontitle='C';
							}else if(j==3){
								optiontitle='D';
							}else if(j==4){
								optiontitle='E';
							}else if(j==5){
								optiontitle='F';
							}else if(j==6){
								optiontitle='G';
							}else if(j==7){
								optiontitle='H';
							}else if(j==8){
								optiontitle='I';
							}else if(j==9){
								optiontitle='J';
							}else if(j==10){
								optiontitle='K';
							}else if(j==11){
								optiontitle='L';
							}
							
							html+='<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+optiontitle+'&nbsp;:&nbsp;</td><td>'+questionnaireOptionList[j].questionnaireOptionDesc+'</td></tr>';
							data1.push(questionnaireOptionList[j].person);
							data2.push(questionnaireOptionList[j].percent);
							time.push(questionnaireOptionList[j].questionnaireOptionDesc); 
						}
						html+='</table></td><td><div style="width: 600px;height: 250px;"  id='+questionnaireQuestionList[i].questionnaireQuestionId+'>';
						loadChart(data1,data2,time,questionnaireQuestionList[i].questionnaireQuestionId);
							html+='</div>';
					 
						html+='</td><td><table border="0" cellspacing="0" cellpadding="0">';
						for(var k=0;k<questionnaireOptionList.length;k++){
						  html+='<tr><td align="right">&nbsp;&nbsp;&nbsp;&nbsp;'+questionnaireOptionList[k].person+'人</td><td>占&nbsp;'+questionnaireOptionList[k].percent+"%"+'</td></tr>';
						}
						html+='</table></td></tr>';
						end+=html;
						}
						$('#tab1').append(html1+end);
				}
			//loadChart(data1,data2,time);		 
			 
		}, 'json');
   }
/* var data=[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3];//myadata的形式
var time=['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];//myTime是形式
 */
	 function loadChart(mydata1,mydata2,myTime,divId){
		// 按需加载 
	    // Step:3 conifg ECharts's path, link to echarts.js from current page. 
	    // Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径 
	    require.config({ 
	        paths: { 
	            echarts: '<%=contextPath%>/jslib/echarts/build/dist' //echarts.js的路径 
	        } 
	    }); 
    // Step:4 require echarts and use it in the callback. 
    // Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径 
    require( 
    [ 
        'echarts', 
        'echarts/chart/bar' ,
    ], 
    //回调函数 
     //渲染ECharts图表 
    function DrawEChart(ec) { 
        //图表渲染的容器对象 
        var chartContainer = document.getElementById(divId); 
        //加载图表 
        var myChart = ec.init(chartContainer); 
        myChart.setOption({ 
            //图表标题 
            title: { 
               // text: "浏览统计报表", //正标题 
                //link: "http://www.xxx.com", //正标题链接 点击可在新窗口中打开 
                x: "center", //标题水平方向位置 
                //subtext: "From:http://www.xxx.com", //副标题 
                //sublink: "http://www.xxx.com", //副标题链接 
                //正标题样式 
               /*  textStyle: { 
                    fontSize:24 
                }, 
                //副标题样式 
                subtextStyle: { 
                    fontSize:12, 
                    color:"red" 
                }  */
        }, 
        //数据提示框配置 
        tooltip: { 
            trigger: 'axis', //触发类型，默认数据触发，见下图，可选为：'item' | 'axis' 其实就是是否共享提示框 
        }, 
        //图例配置 
        legend: { 
            data: ['人数','百分比'], //这里需要与series内的每一组数据的name值保持一致 
            y:"bottom" 
        }, 
        //工具箱配置 
         toolbox: { 
            show : true, 
            feature : { 
               // mark : {show: true}, // 辅助线标志，上图icon左数1/2/3，分别是启用，删除上一条，删除全部 
                //dataView : {show: true, readOnly: false},// 数据视图，上图icon左数8，打开数据视图 
                //magicType : {show: true, type: ['line']},// 图表类型切换，当前仅支持直角系下的折线图、柱状图转换，上图icon左数6/7，分别是切换折线图，切换柱形图 
                //restore : {show: true}, // 还原，复位原始图表，上图icon左数9，还原 
                saveAsImage : {show: false} // 保存为图片，上图icon左数10，保存 
            } 
        }, 
        calculable: true, 
        //轴配置 
        yAxis: [ 
                { 
                    type: 'category', 
                   // boundaryGap : false,
                    data: myTime, 
                    name: '选项'
                } 
            ], 
        //Y轴配置 
        xAxis: [ 
                { 
                    type: 'value', 
                    splitArea: { show: true }, 
                    name:"个数" 
                } 
            ], 
        //图表Series数据序列配置 
        series: [ 
                { 
                    name: '人数', 
                    type: 'bar', 
                    data:  mydata1,
                } ,
                { 
                    name: '百分比', 
                    type: 'bar', 
                    data:  mydata2,
                } 
            ] 
    }); 
    }
    ); 
}
 </script>
 </head> 
<body onload="search('<%=id%>')" > 
<form id="queryForm" action="" method="post" style="margin: 10;">
   <table  border="1" cellspacing="0" cellpadding="0" id="tab1" align="left" >
   <tr><td><font color="red"></font></td></tr>
   </table>
   
   </form>
</body> 
</html>