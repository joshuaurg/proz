package com.dcx.poz.util;

public class PageUtil {

	public static Integer calcPagNum(Integer total, Integer rows){
		if(total < rows)
			return 1;
		else if(total%rows == 0)
			return total/rows;
		else
			return total/rows + 1;
	}
}
