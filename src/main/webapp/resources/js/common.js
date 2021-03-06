var Util = {
		//成功返回的数据
		SUCCESS : 0,
		//失败返回的数据
		ERROR : 1,
		//获取被选中的数据的id转换成字符串
		getSelected : function(tableId){
			var selectIds = $(tableId).datagrid('getSelections');
			var array = [];
			for(var i in selectIds){
				array.push(selectIds[i].id);
			}
			array=array.join(",");
			return array;
		},

		// 0-9 的日期和时间，在前面补0：9 -> 09
		formatZero: function(n){
		     if(n>=0&&n<=9){
		         return "0"+n;
		     }else{
		         return n;
		     }
		 },
		// 格式化时间
		getCurrentDateTime : function() {
		    var date = new Date();//Mon Oct 30 2017 22:08:37 GMT+0800
		    var year=date.getFullYear();
		    var month=date.getMonth()+1;
		    var day=date.getDate();
		    var hours=date.getHours();
		    var minutes=date.getMinutes();
		    var seconds=date.getSeconds();
		    // 2017-01-01 02:23:06   yyyy-MM-dd hh:mm:ss
		    return year+"-"+this.formatZero(month)+"-"+this.formatZero(day)+" "+this.formatZero(hours)+":"+this.formatZero(minutes)+":"+this.formatZero(seconds);
		},

		// 格式化连接
		formatUrl : function(val, row) {
			if (val) {
				return "<a href='" + val + "' target='_blank'>查看</a>";
			}
			return "";
		},
}