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
		$(function(){
			var arr = [];
			var myChart = echarts.init(document.getElementById('main'));
            option = {
            	     title : {
            	         text: '客户服务分析',
            	         //subtext: '纯属虚构',
            	         x:'center'
            	     },
            	     tooltip : {
            	         trigger: 'item',
            	         formatter: "{a} <br/>{b} : {c} ({d}%)"
            	     },
            	     legend: {
            	         orient : 'vertical',
            	         x : 'left',
            	         data:['0-10000','10000-20000','20000-30000','30000-40000','40000以上']
            	     },
            	     toolbox: {
            	         show : true,
            	         feature : {
            	             mark : {show: true},
            	             dataView : {show: true, readOnly: false},
            	             magicType : {
            	                 show: true, 
            	                 type: ['pie', 'funnel'],
            	                 option: {
            	                     funnel: {
            	                         x: '25%',
            	                         width: '50%',
            	                         funnelAlign: 'left',
            	                         max: 1548
            	                     }
            	                 }
            	             },
            	             restore : {show: true},
            	             saveAsImage : {show: true}
            	         }
            	     },
            	     calculable : true,
            	     series : [
            	          {
            	             name:'访问来源',
            	             type:'pie',
            	             radius : '55%',
            	             center: ['50%', '60%'],
            	             data : (function(){
            	             var arr=[];
            	             $.ajax({
            	                  type : "post",
            	                   async : false, //同步执行
            	                  url : "${ctx}/customer/customerServiceAnalysis.action",
            	                  data : {},
            	                 dataType : "json", //返回数据形式为json
            	                   success : function(result) {
            	                		var data = result.data;
            	                		console.log(data);
							    		if(result.status == Util.SUCCESS){
							    			for(var i=0;i<data.length;i++){
							    				arr.push({value:data[i].num,
							    				name:data[i].serviceType});
											}
							    			console.log(arr);
							    		}else{
							    			$.messager.alert('系统提示','查询失败');
							    		}	
            	                                          
            	                  },
            	                 })
            	                   return arr;
            	                })() 
            	             
            	          }
            	      ]
            	  };
			myChart.setOption(option);
		});
		</script>
	</head>
	
	<body>
		 <div id="main" style="width: 600px;height:400px;"></div>
	</body>
</html>