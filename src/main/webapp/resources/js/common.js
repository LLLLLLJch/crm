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
		}
}