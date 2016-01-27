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
.t2{
border:1px solid #ccc;
text-align:center;
}
.t2 tr td{
border:1px solid #ccc;
}
div{
 width:auto; 
 display:inline-block !important; 
 display:inline;
 border:0;
}
</style>
 <script src="<%=contextPath%>/jslib/echarts/build/dist/echarts.js"></script>
<script type="text/javascript">
function search(questionnaireId){
	$.post( sy.contextPath + '/mdmy/questionnaireController/getChar',{'questionnaireId':questionnaireId}, function(result) {
	var data1=new Array();
	var data2=new Array();
	var time=new Array();
		if(result!=null){
			var questionSurveyList=result.data;
				for(var i=0;i<questionSurveyList.length;i++){
					data1.push(questionSurveyList[i].viewCount);
					data2.push(questionSurveyList[i].ipCount);
					time.push(questionSurveyList[i].days);  
				}
			}
		 loadChart(data1,data2,time);
	}, 'json');
}
/* var data=[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3];//myadata的形式
var time=['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];//myTime是形式
*/
 function loadChart(mydata1,mydata2,myTime){
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
    'echarts/chart/line' ,
    'echarts/chart/bar' ,
], 
//回调函数 
 //渲染ECharts图表 
function DrawEChart(ec) { 
    //图表渲染的容器对象 
    var chartContainer = document.getElementById("main"); 
    //加载图表 
    var myChart = ec.init(chartContainer); 
    myChart.setOption({ 
        //图表标题 
        title: { 
            text: "浏览统计报表", //正标题 
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
        data: ['浏览量','IP数'], //这里需要与series内的每一组数据的name值保持一致 
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
    xAxis: [ 
            { 
                type: 'category', 
                boundaryGap : false,
                data: myTime, 
                name: '日期'
            } 
        ], 
    //Y轴配置 
    yAxis: [ 
            { 
                type: 'value', 
                splitArea: { show: true }, 
                name:"个数" 
            } 
        ], 
    //图表Series数据序列配置 
    series: [ 
            { 
                name: '浏览量', 
                type: 'line', 
                data: mydata1 
            },
            { 
                name: 'IP数', 
                type: 'line', 
                data: mydata2 
            }
        ] 
}); 
}
); 
}
</script>
</head>
<body onload="search('${questionnaireId}')">
<form action="" method="post">
<table style="width:600px;height:50px;" class="t2" cellspacing="0" cellpadding="0">
  <tr><td colspan="5">问卷起止时间</td></tr>
 <tr><td colspan="5"><input type="datetime" name="questionnaireCreateTime" value="<fmt:formatDate value='${questionnaire.questionnaireCreateTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss'/>"/> -
<input type="datetime" name="questionnaireEndTime" value="<fmt:formatDate value='${questionnaire.questionnaireEndTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss'/>"/></td></tr>
 <tr><td colspan="5">目前总计</td></tr>
 <tr>
 <td>浏览次数</td>
 <td>独立IP数量</td>
 <td>填写完成率</td>
 <td>平均用时</td>
 <td>来源</td>
 </tr>
 <tr>
 <td>${questionSurvey.viewCount}</td>
 <td>${questionSurvey.ipCount}</td>
 <td>${questionSurvey.finishingRate}%</td>
 <td>${questionSurvey.avgTime}</td>
 <td>${questionSurvey.viewCount}</td>
 </tr>
<tr><td colspan="5">趋势统计</td></tr>
<tr><td colspan="5"><div id="main" style="width:550px; height: 200px; border: 0px solid #ccc;" align="left"></div></td></tr>
 <tr><td colspan="5">今日数据</td></tr>
 <tr>
 <td>浏览次数</td>
 <td>独立IP数量</td>
 <td>填写完成率</td>
 <td>平均用时</td>
 <td>来源</td>
 </tr>
 <tr>
 <td>${questionSurveys.viewCount}</td>
 <td>${questionSurveys.ipCount}</td>
 <td>${questionSurveys.finishingRate}%</td>
 <td>${questionSurveys.avgTime}</td>
 <td>${questionSurveys.viewCount}</td>
 </tr>
 </table><br>
</form>
</body>
</html>