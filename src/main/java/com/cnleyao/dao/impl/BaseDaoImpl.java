package com.cnleyao.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cnleyao.dao.BaseDaoI;

/**
 * <p>Title:<p>
 * <p>Description:对dao的具体实现以及对hibernate常用方法的封装<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年10月13日
 * */

@Repository
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDaoI<T> {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 获得当前事物的session
	 * 
	 * @return org.hibernate.Session
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}


	public Serializable save(T o) {
		if (o != null) {
			return this.getCurrentSession().save(o);
		}
		return null;
	}


	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}


	public T get(String hql, Map<Integer, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (Integer key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}


	public void delete(T o) {
		if (o != null) {
			this.getCurrentSession().delete(o);
		}
	}


	public void update(T o) {
		if (o != null) {
			this.getCurrentSession().update(o);
		}
	}


	public void saveOrUpdate(T o) {
		if (o != null) {
			this.getCurrentSession().saveOrUpdate(o);
		}
	}


	public List<T> find(String clazz) {
		StringBuilder sb = new StringBuilder("FROM　");
		sb.append(clazz);
		Query q = this.getCurrentSession().createQuery(sb.toString());
		return q.list();
	}


	public List<T> find(String hql, Map<Integer, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (Integer key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}


	public List<T> find(String hql, Map<Integer, Object> params, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (Integer key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}


	public List<T> find(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}


	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}


	public Long count(String hql, Map<Integer, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (Integer key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}


	public int executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}


	public int executeHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}


	public List<Object[]> findBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.list();
	}


	public List<Object[]> findBySql(String sql, int page, int rows) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}


	public List<Object[]> findBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}



	public List<Object[]> findBySql(String sql, Map<String, Object> params, int page, int rows) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}


	public int executeSql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.executeUpdate();
	}


	public int executeSql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}


	public BigInteger countBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return (BigInteger) q.uniqueResult();
	}


	public BigInteger countBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (BigInteger) q.uniqueResult();
	}

	public List<T> find(Class<T> c, Map<String, Object> params, int page, int rows) {
		Session session = this.getCurrentSession();
		DetachedCriteria dc = DetachedCriteria.forClass(c);
		if(params != null && !params.isEmpty()){
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				//***特别注意页面提交过来的空数据，实际都是空字符串
				if (obj instanceof String && "".equals((String)obj)) 
					obj = null;
				if(obj!=null){
					dc.add(Restrictions.eq(key, obj));
				}
			}

		}
		Criteria cr = dc.getExecutableCriteria(session);
		return cr.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}


	@Override
	public void clear() {
		this.getCurrentSession().clear();
		
	}


	@Override
	public void flush() {
		
		this.getCurrentSession().flush();
	}


	@Override
	public List<T> find(String hql, Serializable[] ids) {
		// TODO Auto-generated method stub
		Query q = this.getCurrentSession().createQuery(hql);
		if(ids.length>0&&ids!=null){
			q.setParameterList("ids", ids);
		}
		return q.list();
	}

	

}
