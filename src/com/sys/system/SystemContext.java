package com.sys.system;

public class SystemContext {

	private static ThreadLocal<Integer> offset = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();

	public static int getOffset() {
		Integer _offset = offset.get();
		if (_offset == null) {
			return 0;
		}
		return _offset;
	}

	public static void setOffset(int _offset) {
		offset.set(_offset);
	}

	public static int getPageSize() {
		Integer _pageSize = pageSize.get();
		if (_pageSize == null) {
			return Integer.MAX_VALUE;
		}
		return _pageSize;
	}

	public static void setPageSize(int _pageSize) {
		pageSize.set(_pageSize);
	}

	public static void removeOffset() {
		offset.remove();
	}

	public static void removePageSize() {
		pageSize.remove();
	}

	public static void removePageInfo() {
		pageSize.remove();
		offset.remove();
	}
}
