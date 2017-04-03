package com.sys.service.base;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import com.sys.system.PagerModel;

public interface DAO<T> {
	public void save(T entity);
	public void update(T entity);
	public void delete(Serializable... entityId);
	public T find(Serializable entityId);

	public T get(Serializable entityId);
	
	public long getCount();
	
	public PagerModel<T> findScrollData(
			LinkedHashMap<String, String> orderBy,String whereSql,Object[] queryParams);

	public PagerModel<T> findScrollData(
			String whereSql,Object[] queryParams);
	
	public PagerModel<T> findScrollData(
			LinkedHashMap<String, String> orderBy);
	
	public PagerModel<T> findScrollData();
	public List<T> findScrollDataNoPager();
	
	public List<T> findScrollDataNoPager(
			LinkedHashMap<String, String> orderBy, String whereSql,
			Object[] queryParams);
}
