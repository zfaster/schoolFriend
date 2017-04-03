package com.sys.service.base;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sys.system.PagerModel;
import com.sys.system.SystemContext;
import com.sys.utils.GenericsUtils;

public abstract class DaoSupport<T> extends HibernateDaoSupport implements
		DAO<T> {

	@SuppressWarnings("unchecked")
	private Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this
			.getClass());

	// 使用LinkedHashMap保持输入参数排序先后顺序
	public PagerModel<T> findScrollData(
			LinkedHashMap<String, String> orderBy, String whereSql,
			Object[] queryParams) {
		PagerModel<T> pm = null;
		try {
			String entityName = getEntityName(entityClass);
			pm = new PagerModel<T>();

			Query result = this.getSession().createQuery(
					"from " + entityName + " o "
							+ (whereSql == null ? "" : "where " + whereSql)
							+ buildOrderBy(orderBy));
			result = setQueryParams(result, queryParams);
			result.setFirstResult(SystemContext.getOffset()).setMaxResults(
					SystemContext.getPageSize());

			pm.setDatas(result.list());

			Query count = this.getSession().createQuery(
					"select count(*) from " + entityName + " o "
							+ (whereSql == null ? "" : "where " + whereSql));
			count = setQueryParams(count, queryParams);
			pm.setTotal((Long) count.uniqueResult());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return pm;
	}

	public PagerModel<T> findScrollData(
			String whereSql, Object[] queryParams) {
		return findScrollData(null, whereSql, queryParams);
	}

	public PagerModel<T> findScrollData(
			LinkedHashMap<String, String> orderBy) {
		return findScrollData(orderBy, null, null);
	}

	public PagerModel<T> findScrollData() {
		return findScrollData( null, null, null);
	}

	public List<T> findScrollDataNoPager(){
		String entityName = getEntityName(entityClass);
		Query result = this.getSession().createQuery(
				"from " + entityName + " o ");
		return result.list();
	}
	
	public List<T> findScrollDataNoPager(
			LinkedHashMap<String, String> orderBy, String whereSql,
			Object[] queryParams) {
		String entityName = getEntityName(entityClass);

		Query result = this.getSession().createQuery(
				"from " + entityName + " o "
						+ (whereSql == null ? "" : "where " + whereSql)
						+ buildOrderBy(orderBy));
		result = setQueryParams(result, queryParams);
		

		return result.list();
	}
	
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	public void delete(Serializable... entityId) {
		for(Serializable id : entityId){
			this.getHibernateTemplate().delete(
					this.getHibernateTemplate().load(entityClass, id));
		}
	}

	public T find(Serializable entityId) {
		return this.getHibernateTemplate().load(entityClass, entityId);
	}

	public long getCount() {
		return (Long) this
				.getSession()
				.createQuery(
						"select count(*) from " + getEntityName(entityClass)
								).uniqueResult();

	}

	/**
	 * 获得实体类名称
	 * @param entityClass
	 * @return
	 */
	public String getEntityName(Class<T> entityClass) {
		String name = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if (entity != null && entity.name() != null && !"".equals(entity.name())) {
			name = entity.name();
		}
		return name;
	}

	/**
	 * 构建查询排序语句
	 * @param orderBy
	 * @return
	 */
	protected String buildOrderBy(LinkedHashMap<String, String> orderBy) {
		if (orderBy == null || orderBy.isEmpty()) {
			return "";
		}
		StringBuffer orderSql = new StringBuffer(" order by ");
		for (String key : orderBy.keySet()) {
			orderSql.append("o.").append(key).append(" ")
					.append(orderBy.get(key)).append(",");
		}
		orderSql.deleteCharAt(orderSql.length() - 1);
		return orderSql.toString();
	}

	/**
	 * 为查询语句增加参数
	 * @param query
	 * @param queryParams
	 * @return
	 */
	protected Query setQueryParams(Query query, Object[] queryParams) {
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i, queryParams[i]);
			}
		}
		return query;
	}
	
	public T get(Serializable entityId) {
		
		return this.getHibernateTemplate().get(entityClass, entityId);
	}

	@Resource(name = "hibernateTemplate")
	public void setSuperHibernateTemplate(HibernateTemplate hibernateTemplate) {
		super.setHibernateTemplate(hibernateTemplate);
	}
}
