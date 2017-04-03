package com.sys.system;

import java.util.List;

public class PagerModel<T> {

	private List<T> datas;
	private long total;
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	
}
