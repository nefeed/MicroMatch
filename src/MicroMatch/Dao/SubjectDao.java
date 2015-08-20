package MicroMatch.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import MicroMatch.Entity.SubjectEntity;
import MicroMatch.Factory.HibernateSessionFactory;

public class SubjectDao extends DaoAbstract {
	String hql = "" ;
	
	
	/** 
	* ������ insert
	* ������ ���ӽ���
	* ���أ� boolean
	* �����ˣ��»���
	* ����ʱ�䣺2015��4��13�� ����5:13:55
	* �޸��ˣ�����
	* �޸�ʱ�䣺2015��4��13�� ����5:13:55
	* �޸ı�ע��
	* @throws 
	*/ 
	public boolean insert ( SubjectEntity subject ) {
		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.save( subject ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			return false ;
		}
	}
	
	/** 
	* ������ update
	* ������ �޸Ľ���
	* ���أ� boolean
	* �����ˣ��»���
	* ����ʱ�䣺2015��4��13�� ����5:18:26
	* �޸��ˣ�����
	* �޸�ʱ�䣺2015��4��13�� ����5:18:26
	* �޸ı�ע��
	* @throws 
	*/ 
	public boolean update ( SubjectEntity subject ) {
		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.update( subject ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			return false ;
		}
	}
	
	@SuppressWarnings("unchecked")
	public SubjectEntity QuerySubById( int id ) {
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from SubjectEntity where id=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setInteger( 0 , id ) ;
			List<SubjectEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if ( lists == null || lists.size() == 0 ) {
				return null ;
			} else {
				return lists.get(0) ;						
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}
	}

	/**
	* @Title:       showAll
	* @Description: 展示所有的课程分类
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public List<SubjectEntity> showAll() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from SubjectEntity" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			List<SubjectEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if ( lists == null || lists.size() == 0 ) {
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
	* @Title:       QuerySubByName
	* @Description: 根据名称查找sub
	* @param:       @param subName
	* @param:       @return
	* @return:      SubjectEntity
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public SubjectEntity QuerySubByName(String subName) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from SubjectEntity where subjectName=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setString( 0 , subName ) ;
			List<SubjectEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if ( lists == null || lists.size() == 0 ) {
				return null ;
			} else {
				return lists.get(0) ;						
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}
	}

	/**
	* @Title:       showChild
	* @Description: 展示子学科
	* @param:       @param subId
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public List<SubjectEntity> showChild(int subId) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from SubjectEntity s where s.pid=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setInteger( 0 , subId ) ;
			List<SubjectEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if ( lists == null || lists.size() == 0 ) {
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

}
