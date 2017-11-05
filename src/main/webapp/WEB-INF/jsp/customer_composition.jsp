<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript" src="${ctx}/resources/thirdlib/echarts.js"></script>
		<style>
			* {
				margin: 0;
				padding: 0;
			}
		</style>
		<script type="text/javascript">
			$(function() {
				 // 基于准备好的dom，初始化echarts实例
		        var myChart = echarts.init(document.getElementById('main'));
	
		        // 异步加载数据
		        $.post('${ctx}/customer/findCustomerComposition.action',
		        		function(result){
		        			if(result.status == Util.SUCCESS) {
		        				var xAxisData=new Array();
		    					var seriesData=new Array();
		    					var data = result.data;
		    					for(var i=0;i<data.length;i++){
		    						xAxisData.push(data[i].customerLevel);
		    						seriesData.push(data[i].num);
		    					}
		    					console.log(xAxisData);
		    		        	// 填入数据
		    		            myChart.setOption({
		    		                title: {
		    		                    text: '客户构成分析'
		    		                },
		    		                tooltip: {},
		    		                legend: {
		    		                    data:['销量']
		    		                },
		    		                xAxis: {
		    		                    data: xAxisData
		    		                },
		    		                yAxis: {},
		    		                series: [{
		    		                    // 根据名字对应到相应的系列
		    		                    name: '销量',
		    		                    type: 'bar',
		    		                    data: seriesData
		    		                }]
		    		            });
		        			} else {
		        				alert("查询失败");
		        			}
				        	
		        		},
		        		'json'
		        	);
		        
			});
		</script>
	</head>
	
	<body>
		 <div id="main" style="width: 600px;height:400px;"></div>
	</body>
</html>