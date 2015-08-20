package MicroMatch.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import MicroMatch.Entity.PollVoteEntity;
import MicroMatch.Factory.HibernateSessionFactory;

public class PollVoteDao extends DaoAbstract {
String hql = "" ;
	
	/**
	* @Title:       insert
	* @Description: 新建投票
	* @param:       @param pollVote
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean insert(PollVoteEntity pollVote) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.save( pollVote ) ;
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
	* @Title:       IsPollVote
	* @Description: 判断是否已经投票
	* @param:       @param pollVote
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public boolean IsPollVote(PollVoteEntity pollVote) {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from PollVoteEntity where studentNum=? and courseNum=? and matchNum=? and matchTemp=?" ;
		try {
			session.clear() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , pollVote.getStudentNum() ) ;
			query.setString( 1 , pollVote.getCourseNum() ) ;
			query.setString( 2 , pollVote.getMatchNum() ) ;
			query.setInteger( 3 , pollVote.getMatchTemp() ) ;
			List<PollVoteEntity> lists = query.list() ;
			session.flush() ;
			return !(lists == null || lists.size() == 0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return false ;
		}
	}


	/**
	* @Title:       CountPoll
	* @Description: 统计这门课程的最终得票情况
	* @param:       @param courseNum
	* @param:       @return
	* @return:      float
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public float CountPoll(PollVoteEntity pv) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from PollVoteEntity where courseNum=? and matchNum=? and matchTemp=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setString( 0 , pv.getCourseNum() ) ;
			query.setString( 1 , pv.getMatchNum() ) ;
			query.setInteger( 2 , pv.getMatchTemp() ) ;
			List<PollVoteEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			float PollCount = 0 ;
			for (int i = 0; i < lists.size(); i++) {
				PollCount += lists.get(i).getPoll() ;
			}
			float finalPoll = PollCount / lists.size() ;
			return finalPoll ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return 0 ;
		}
	}
}
