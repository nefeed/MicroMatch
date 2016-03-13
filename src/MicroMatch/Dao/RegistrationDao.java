package MicroMatch.Dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import MicroMatch.Entity.MatchEntity;
import MicroMatch.Entity.RegistrationEntity;
import MicroMatch.Factory.HibernateSessionFactory;

public class RegistrationDao extends DaoAbstract {
	String hql = "" ;
	
	/** 
	* ������ insert
	* ������ ���ӿγ��½�
	* ���أ� boolean
	* �����ˣ��»���
	* ����ʱ�䣺2015��4��13�� ����5:13:55
	* �޸��ˣ�����
	* �޸�ʱ�䣺2015��4��13�� ����5:13:55
	* �޸ı�ע��
	* @throws 
	*/ 
	public boolean insert ( RegistrationEntity registration ) {

		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Float poll = Float.parseFloat("0") ;
			registration.setPoll( poll ) ;
			registration.setPollNum( 0 ) ;
			session.save( registration ) ;
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
	* ������ �޸Ŀγ��½���Ϣ
	* ���أ� boolean
	* �����ˣ��»���
	* ����ʱ�䣺2015��4��13�� ����5:18:26
	* �޸��ˣ�����
	* �޸�ʱ�䣺2015��4��13�� ����5:18:26
	* �޸ı�ע��
	* @throws 
	*/ 
	public boolean update ( RegistrationEntity registration ) {

		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.update( registration ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			return false ;
		}
	}

	/**
	* @Title:       AddPollNum
	* @Description: 投票数+1
	* @param:       @param registration
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public boolean AddPollNum(RegistrationEntity registration) {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from RegistrationEntity where matchNum=? and courseNum=?" ;
		RegistrationEntity r = new RegistrationEntity() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setString( 0 , registration.getMatchNum() ) ;
			query.setString( 1 , registration.getCourseNum() ) ;
			List<RegistrationEntity> lists = query.list() ;
			if( lists.size() != 0 ) {
				r = lists.get(0) ;
				r.setPollNum( r.getPollNum()+1 ) ;				
				session.flush() ;
				session.update( r ) ;
			}
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return false ;
		}
	}

	/**
	* @Title:       QueryCourseByMatchNum
	* @Description: 根据比赛Num找出所有参赛课程
	* @param:       @param matchNum
	* @param:       @return
	* @return:      List<RegistrationEntity>
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public List<RegistrationEntity> QueryCourseByMatchNum(MatchEntity m) {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from RegistrationEntity r where r.matchNum=? and r.matchTemp=? order by r.pollNum DESC" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setString( 0, m.getMatchNum() ) ;
			query.setInteger( 1 , m.getMatchTemp() ) ;
			List<RegistrationEntity> lists =  query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if( lists.size() == 0 || lists == null ) {
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
	* @Title:       StopPollUpdate
	* @Description: 统计最终得票数
	* @param:       @param courseNum
	* @param:       @param finalPoll
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean StopPollUpdate( RegistrationEntity registration ) {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from RegistrationEntity where courseNum=? and matchNum=? and matchTemp=?" ;
		RegistrationEntity r = new RegistrationEntity() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setString( 0 , registration.getCourseNum() ) ;
			query.setString( 1 , registration.getMatchNum() ) ;
			query.setInteger( 2 , registration.getMatchTemp() ) ;
			List<RegistrationEntity> lists = query.list() ;
			r = lists.get(0) ;
			r.setPoll( registration.getPoll() ) ;
			session.flush() ;
			session.update( r ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<RegistrationEntity> QueryMatchByCourseNum(String courseNum) {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from RegistrationEntity where courseNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , courseNum ) ;
			List<RegistrationEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if( lists == null || lists.size() == 0 ){
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
	* @Title:       isRegister
	* @Description: 判断课程时候已经报名了比赛
	* @param:       @param registration
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public boolean isRegister(RegistrationEntity registration) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from RegistrationEntity where courseNum=? and matchNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , registration.getCourseNum() ) ;
			query.setString( 1 , registration.getMatchNum() ) ;
			List<RegistrationEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			return !(lists == null || lists.size() == 0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return false ;
		}
	}

	/** 
	* 方法： finishPreliminary
	* 描述： 结束初赛，将比赛成绩前8名置入复赛
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年6月23日 下午12:15:21
	* 修改人：Gavin
	* 修改时间：2015年6月23日 下午12:15:21
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public boolean finishPreliminary(String matchNum) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from RegistrationEntity where matchNum=? and matchTemp=0 order by poll desc" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , matchNum ) ;
			List<RegistrationEntity> lists = query.list() ;
			session.flush() ;
			if( lists == null || lists.size() == 0 ){
				session.getTransaction().commit() ;
				return false ;
			}else {
				RegistrationEntity r ;
				// 将投票得分前8名置入复赛
				for ( int i = 0 ; i < 8 ; i ++ ) {
					r = new RegistrationEntity() ;
					r.setCourseNum( lists.get(i).getCourseNum() ) ;
					r.setMatchNum( lists.get(i).getMatchNum() );
					r.setMatchTemp( 1 ) ;
					Float poll = Float.parseFloat("0") ;
					r.setPoll( poll ) ;
					r.setPollNum( 0 ) ;
					r.setRegisterTime( new Timestamp( System.currentTimeMillis() ) ) ;
					session.save( r ) ;
					session.flush() ;
				}
				session.getTransaction().commit() ;
				return true ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return false ;
		}
	}

	/** 
	* 方法： finishQuarter
	* 描述： 结束复赛
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年6月23日 下午12:28:05
	* 修改人：Gavin
	* 修改时间：2015年6月23日 下午12:28:05
	* 修改备注：
	* @throws 
	*/ 
	public boolean finishQuarter(String matchNum) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from RegistrationEntity where matchNum=? and matchTemp=1 order by poll desc" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , matchNum ) ;
			List<RegistrationEntity> lists = query.list() ;
			session.flush() ;
			if( lists == null || lists.size() == 0 ){
				session.getTransaction().commit() ;
				return false ;
			}else {
				RegistrationEntity r ;
				// 将复赛投票得分前4名置入决赛
				for ( int i = 0 ; i < 4 ; i ++ ) {
					r = new RegistrationEntity() ;
					r.setCourseNum( lists.get(i).getCourseNum() ) ;
					r.setMatchNum( lists.get(i).getMatchNum() );
					r.setMatchTemp( 2 ) ;
					Float poll = Float.parseFloat("0") ;
					r.setPoll( poll ) ;
					r.setPollNum( 0 ) ;
					r.setRegisterTime( new Timestamp( System.currentTimeMillis() ) ) ;
					session.save( r ) ;
					session.flush() ;
				}
				session.getTransaction().commit() ;
				return true ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			session.getTransaction().rollback() ;
			return false ;
		}
	}
}
