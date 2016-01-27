<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style type="text/css">
.table td{
  text-align:center;
}
</style>
  <script src="<%=contextPath%>/jslib/echarts/build/dist/echarts.js"></script>
  <script type="text/javascript">
  
function search(flag){
	    $('#thead tr').html("");
	    $('#tbody tr').html("");
		var begin=$("#begin").val();
		var end=$("#end").val();
		var type=$('#selected option:selected').val();
		if(flag=='reset'){
			begin='';
			end='';
		}
	   $.post( sy.contextPath + '/mdmy/playRecord/findCount',{'queryBeginTimeStr':begin,'queryEndTimeStr':end,'playFrom':type}, function(result) {
			if(result!=null&&result.length>0){
				var data1=new Array();
				var data2=new Array();
				var time=new Array();
					var headHtml="<table class=\"table\" style=\"font-size:12px;\"><tr><td>日期</td>";
					var bodyHtml1="<tr><td>日浏览量</td>";
					var bodyHtml2="<tr><td>IP数</td>";
					var tableHtml1="";
					var tableHtml2="";
					for(var i=0;i<result.length;i++){
						data1.push(result[i].countAll);
						data2.push(result[i].countIp);
						time.push(result[i].days);  
						if(i!=0&&i%15==0){
							tableHtml1+=headHtml+bodyHtml1+"</table>";
							tableHtml2+=headHtml+bodyHtml2+"</table>";
							headHtml="<table class=\"table\" style=\"font-size:12px;\"><tr><td>日期</td><th>"+result[i].days+"</th>";
							bodyHtml1="<tr><td>日浏览量</td><td>"+result[i].countAll+"</td>";
							bodyHtml2="<tr><td>IP数</td><td>"+result[i].countIp+"</td>";
						}else{
							headHtml+="<th>"+result[i].days+"</th>";
							bodyHtml1+="<td>"+result[i].countAll+"</td>";
							bodyHtml2+="<td>"+result[i].countIp+"</td>";
						}
					}
					headHtml+="</tr>";
					bodyHtml1+="</tr></table>";
					bodyHtml2+="</tr></table>";
					tableHtml1+=headHtml+bodyHtml1;
					tableHtml2+=headHtml+bodyHtml2;
					$('#tab1 td').html(tableHtml1);
					$('#tab2 td').html(tableHtml2);
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
                saveAsImage : {show: true} // 保存为图片，上图icon左数10，保存 
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
<body onload="search()" > 
<form id="queryForm" style="margin: 10;">
<br/>
   按类型：<select name="playFrom" id="selected"  style="width: 90px;">
                                        <option value="">请选择</option>
										<option value="1">网页</option>
										<option value="2">安卓</option>
										<option value="3">苹果</option></select>
   开始日期：<input type="text" name="queryBeginTimeStr" class="Wdate" id="begin" autocomplete="off" readonly="readonly"
 onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end\')||\'%y-%M-%d\'}'})" />
    结束日期：<input name="queryEndTimeStr" class="Wdate " id="end" autocomplete="off" readonly="readonly"
  onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'begin\')}'})" />
		<input id="but" type="button" value="过滤" onclick="search()"/>
		<input id="reset" type="reset" value="重置过滤" onclick="search('reset')" />
    </form>
    <br/>
     <div id="main" style="height: 400px; border: 1px solid #ccc; padding: 10px;"> 
    </div>
    <br/>
   <table id="tab1" align="center">
   <caption align="top">浏览量统计</caption>  
       <tr>
       <td></td>
       </tr>
   </table>
   <br/>
    <table id="tab2" align="center">
   <caption align="top">IP数统计</caption>  
       <tr>
       <td></td>
       </tr>
   </table>
</body> 
</html>