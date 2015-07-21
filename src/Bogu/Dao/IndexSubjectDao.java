package Bogu.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Bogu.Entity.IndexSubjectEntity;
import Bogu.Factory.GavinHibernateSessionFactory;

public class IndexSubjectDao extends DaoAbstract{
	String hql = "" ;
	
	/**
	* @Title:       showIndex
	* @Description: 遍历首页的学科
	* @param:       @param indexNum
	* @param:       @return
	* @return:      List<IndexSubjectEntity>
	* @throws
	*/ 
	public List<IndexSubjectEntity> showIndex() {

		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from IndexSubjectEntity" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			List<IndexSubjectEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if( lists == null || lists.size() == 0 ) {
				return null ;
			} else {
				return lists ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}
	}
	
	/**
	* @Title:       update
	* @Description: 更换首页显示的学科
	* @param:       @param is
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean update ( IndexSubjectEntity is ) {

		Session session = GavinHibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.update( is ) ; 
			session.flush() ;
			session.getTransaction().commit() ;

			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return false ;
		}
	}
}
