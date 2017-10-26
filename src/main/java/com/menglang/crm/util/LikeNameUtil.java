package com.menglang.crm.util;

import org.apache.commons.lang3.StringUtils;

public class LikeNameUtil {

	public static String formartLike(String str) {
		if(StringUtils.isNotBlank(str)){
			return "%"+str+"%";
		}else {
			return null;
		}
	}
}
