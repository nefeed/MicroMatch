package MicroMatch.Dao;


import MicroMatch.Entity.AttendCourseEntity;
import MicroMatch.Factory.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class AttendCourseDao extends DaoAbstract {
	String hql = "" ;
	/**
	* @Title:       insert
	* @Description: 新建订阅
	* @param:       @param attendCourse
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean insert(AttendCourseEntity attendCourse) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.save( attendCourse ) ;
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
	* @Title:       delete
	* @Description: 删除订阅
	* @param:       @param attendCourse
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean delete( AttendCourseEntity attendCourse ) {
		Session session = HibernateSessionFactory.getSession() ;
		hql = "delete AttendCourseEntity where studentNum=? and courseNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.createQuery( hql ).setString( 0, attendCourse.getStudentNum() ).setString( 1 , attendCourse.getCourseNum() ).executeUpdate() ;
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
	* @Title:       isAttend
	* @Description: 判断用户时候订阅
	* @param:       @param attendCourse
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public boolean IsAttend(AttendCourseEntity attendCourse) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from AttendCourseEntity ac where ac.studentNum=? and ac.courseNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , attendCourse.getStudentNum() ) ;
			query.setString( 1 , attendCourse.getCourseNum() ) ;
			List<AttendCourseEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			return !(lists == null || lists.isEmpty());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return false ;
		}
	}
	
	/** 
	* 方法： showMyAttends
	* 描述： 显示我的订阅课程
	* 返回： List<AttendCourseEntity>
	* 创建人：章华隽
	* 创建时间：2015年5月10日 下午10:58:33
	* 修改人：Gavin
	* 修改时间：2015年5月10日 下午10:58:33
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public List<AttendCourseEntity> showMyAttends ( String userNum ) {
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from AttendCourseEntity where studentNum=?";
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , userNum ) ;
			List<AttendCourseEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if( lists == null || lists.size() == 0 ) {
				return null ;
			}else {	
				return lists ;
			}
		} catch ( Exception e ) {
			e.printStackTrace() ;
			return null ;
		}
 	}
	
}
