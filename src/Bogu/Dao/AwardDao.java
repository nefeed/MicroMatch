package Bogu.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Bogu.Entity.AwardEntity;
import Bogu.Factory.GavinHibernateSessionFactory;

public class AwardDao extends DaoAbstract {
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
	public boolean insert( AwardEntity award ) {

		Session session = GavinHibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.save( award ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
	public boolean update( AwardEntity award ) {

		Session session = GavinHibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.update( award ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false ;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean GivenAward( AwardEntity award ) {

		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from AwardEntity where id=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql).setInteger( 0 , award.getId() ) ;
			List<AwardEntity> lists = query.list() ;
			AwardEntity a = lists.get(0) ;
			a.setAwardTime( award.getAwardTime() ) ;
			a.setCourseNum( award.getCourseNum() ) ;
			session.flush() ;
			session.update( a ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false ;
		}
	}

	/** 
	* 方法： QueryBymatchNum
	* 描述： 查看本比赛下的全部获奖
	* 返回： List<AwardEntity>
	* 创建人：章华隽
	* 创建时间：2015年4月26日 上午10:41:12
	* 修改人：华隽
	* 修改时间：2015年4月26日 上午10:41:12
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public List<AwardEntity> QueryBymatchNum(String matchNum) {
		// TODO Auto-generated method stub
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from AwardEntity where matchNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , matchNum ) ;
			List<AwardEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if( lists == null || lists.size() == 0 ) {
				return null ;
			}else {
				return lists ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}
	}
	
	/** 
	* 方法： queryById
	* 描述： 根据Id查找奖项
	* 返回： AwardEntity
	* 创建人：章华隽
	* 创建时间：2015年5月31日 上午11:35:19
	* 修改人：Gavin
	* 修改时间：2015年5月31日 上午11:35:19
	* 修改备注：
	* @throws 
	*/ 
	public AwardEntity queryById( int id ) {
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from AwardEntity where id=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setInteger( 0 , id ) ;
			List<AwardEntity> lists = query.list() ;
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
	* @Title:       queryHistoryAward
	* @Description: 查询课程历史获奖记录
	* @param:       @param courseNum
	* @param:       @return
	* @return:      List<CourseEntity>
	* @throws
	*/ 
	public List<AwardEntity> queryHistoryAward(String courseNum) {
		// TODO Auto-generated method stub
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from AwardEntity where courseNum=:coursenum" ;
		session.clear() ;
		Query query = session.createQuery( hql ) ;
		query.setParameter("coursenum", courseNum) ;
		List<AwardEntity> lists = query.list() ;
		session.flush() ;
		if ( lists == null || lists.size() == 0 ) {
			return null;			
		} else {
			return lists ;
		}
	}
}
