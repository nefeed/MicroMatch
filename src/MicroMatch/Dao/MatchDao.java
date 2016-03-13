package MicroMatch.Dao;

import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import MicroMatch.Entity.MatchEntity;
import MicroMatch.Factory.HibernateSessionFactory;

public class MatchDao extends DaoAbstract {
	String hql = "" ;
	
	public JSONObject insert ( MatchEntity match ) {
		JSONObject json = new JSONObject() ;
		Session session = HibernateSessionFactory.getSession() ;
		String ranString = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890" ;
		String theRan = "" ;
		boolean clash = true ;
		Random random = new Random() ;
		char[] ranArray = new char[20] ;
		session.clear();
		session.beginTransaction() ;
		
		while ( clash ) {
			for (int i = 0; i < ranArray.length; i++) {
				ranArray[i] = ranString.charAt( random.nextInt( 36 ) ) ;
				theRan = theRan + ranArray[i] ;
			}
			try {
				session.flush() ;
				hql = "from MatchEntity m where m.matchNum=?" ;
				Query query = session.createQuery( hql ) ;
				query.setString( 0 , theRan ) ;
				@SuppressWarnings("unchecked")
				List<MatchEntity> matchs = query.list() ;
				if( matchs.size() == 0 ) {
					clash = false ;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
			}
		}
		Short check = 0 ;
		match.setIsChecked( check ) ;
		match.setMatchNum( theRan ) ;
		try {
			session.flush();
			session.save( match ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			json.put( "result" , 0 ) ;
			json.put( "MatchNum", theRan ) ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			json.put( "result" , 1 ) ;
		}
		return json ;
	}
	
	/** 
	* ������ update
	* ������ �޸ı���
	* ���أ� boolean
	* �����ˣ��»���
	* ����ʱ�䣺2015��4��13�� ����5:18:26
	* �޸��ˣ�����
	* �޸�ʱ�䣺2015��4��13�� ����5:18:26
	* �޸ı�ע��
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public boolean update ( MatchEntity match ) {

		Session session = HibernateSessionFactory.getSession() ;
		MatchEntity nm = new MatchEntity() ;
		hql = "from MatchEntity m where m.matchNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , match.getMatchNum() ) ;
			List<MatchEntity> lists = query.list() ;
			nm = lists.get(0) ;
			nm.setMatchName( match.getMatchName() ) ;
			nm.setMatchContent( match.getMatchContent() ) ;
			nm.setMatchStartTime( match.getMatchStartTime() ) ;
			nm.setMatchEndTime( match.getMatchEndTime() ) ;
			session.flush() ;
			session.update( nm ) ;
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
	* @Title:       QueryByMatchNum
	* @Description: 根据比赛Num查找比赛
	* @param:       @param matchNum
	* @param:       @return
	* @return:      MatchEntity
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public MatchEntity QueryByMatchNum( String matchNum ) {

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from MatchEntity m where m.matchNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , matchNum ) ;
			List<MatchEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if( lists.size() != 0 ) {
				return lists.get(0) ;				
			}else {
				return null ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}
	}
	
	/**
	* @Title:       QueryByUserNum
	* @Description: 根据创建者Num遍历比赛
	* @param:       @param userNum
	* @param:       @return
	* @return:      List<MatchEntity>
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public List<MatchEntity> QueryByUserNum( String userNum ) {

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from MatchEntity m where m.userNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0, userNum ) ;
			List<MatchEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			return lists ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}
	}

	/**
	* @Title:       ANewRegistrant
	* @Description: 增加一名报名参赛数
	* @param:       @param matchNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public boolean ANewRegistrant(String matchNum) {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getSession() ;
		MatchEntity nm = new MatchEntity() ;
		hql = "from MatchEntity m where m.matchNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , matchNum ) ;
			List<MatchEntity> lists = query.list() ;
			nm = lists.get(0) ;
			nm.setRegistrationNum( nm.getRegistrationNum() + 1 ) ;
			session.flush() ;
			session.update( nm ) ;
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
	* @Title:       StopMatch
	* @Description: 结束当前比赛状态
	* @param:       @param matchNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public boolean StopMatch(String matchNum) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from MatchEntity m where m.matchNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , matchNum ) ;
			List<MatchEntity> lists = query.list() ;
			session.flush() ;
			if( lists == null || lists.size() == 0 ) {
				session.getTransaction().commit() ;
				return false ;
			}else {
				MatchEntity m = lists.get(0) ;
				// MatchTemp 0 初赛 1 复赛 2 决赛 3 比赛所有阶段已经结束
				int temp = m.getMatchTemp() + 1 ;
				// 比赛所有阶段已经结束
				if ( temp == 4 ) {
					session.getTransaction().commit() ;
					return false ;
				} else {
					m.setMatchTemp( temp ) ;
					session.update( m ) ;
					session.flush() ;
					session.getTransaction().commit() ;
					return true ;					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			session.getTransaction().commit() ;
			return false ;
		}
	}

	/**
	* @Title:       QueryAll
	* @Description: 遍历所有比赛
	* @param:       @return
	* @return:      List<MatchEntity>
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public List<MatchEntity> QueryAll() {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from MatchEntity m ORDER BY m.publishTime DESC " ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			List<MatchEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if ( lists == null || lists.size() == 0) {
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
	* @Title:       check
	* @Description: 审核比赛通过
	* @param:       @param matchNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public boolean check ( String matchNum ) {

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from MatchEntity m where m.matchNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 ,  matchNum ) ;
			List<MatchEntity> lists = query.list() ;
			if( lists == null || lists.size() == 0 ) {
				return false ;
			} else {
				MatchEntity m = lists.get(0) ;
				Short check = 1 ;
				if( m.getIsChecked() == 1 ) {
					check = 0 ;
				} else if( m.getIsChecked() == 0 ) {
					check = 1 ;
				}
				m.setIsChecked( check ) ;
				session.flush() ;
				session.update( m ) ;
				session.flush() ;
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
	* @Title:       QueryUnChecked
	* @Description: 搜索未审核比赛
	* @param:       @return
	* @return:      List<MatchEntity>
	* @throws
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public List<MatchEntity> QueryUnChecked( Short check ) {

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from MatchEntity m where m.isChecked=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setShort( 0 , check ) ;
			List<MatchEntity> lists = query.list() ;
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
	
	@SuppressWarnings({ "unchecked", "null" })
	public List<MatchEntity> QuerySimilar( String matchName ) {

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from MatchEntity m where m.matchName like :Mname" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( "Mname" , "%"+matchName+"%") ;
			List<MatchEntity> lists = query.list() ;
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
	* 方法： isMine
	* 描述： 
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年5月27日 下午11:44:06
	* 修改人：Gavin
	* 修改时间：2015年5月27日 下午11:44:06
	* 修改备注：
	* @throws 
	*/ 
	public boolean isMine(MatchEntity m) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from MatchEntity m where m.userNum=? and m.matchNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , m.getUserNum() ) ;
			query.setString( 1 , m.getMatchNum() ) ;
			List<MatchEntity> lists = query.list() ;
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
	* @Title:       queryByMatchTemp
	* @Description: 按照比赛阶段搜索比赛
	* @param:       @param matchTemp
	* @param:       @return
	* @return:      List<MatchEntity>
	* @throws
	*/ 
	public List<MatchEntity> queryByMatchTemp( Integer matchTemp ) {
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from MatchEntity m where m.matchTemp=:matchTemp" ;
		session.clear() ;
		session.beginTransaction() ;
		Query query =session.createQuery( hql ) ;
		query.setParameter( "matchTemp" , matchTemp ) ;
		List<MatchEntity> lists = query.list() ;
		session.flush() ;
		session.getTransaction().commit() ;
		if ( lists == null || lists.size() == 0 ) {
			return null ;
		} else {
			return lists ;
		}
	}
}
