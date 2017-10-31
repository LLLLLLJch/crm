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

		//格式化时间
		formatDateTime : function(val, row) {
			var now = new Date(val);
			return now.format("yyyy-MM-dd hh:mm:ss");
		},
		// 格式化连接
		formatUrl : function(val, row) {
			if (val) {
				return "<a href='" + val + "' target='_blank'>查看</a>";
			}
			return "";
		},
}