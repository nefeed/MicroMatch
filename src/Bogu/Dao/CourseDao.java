package Bogu.Dao;

import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;

import Bogu.Entity.CourseEntity;
import Bogu.Factory.GavinHibernateSessionFactory;


/**
* 项目名称：BoguCloudCourse
* 类名称：CourseDao
* 类描述：
* 创建人：章华隽
* 创建时间：2015年4月16日 下午5:07:04
* 修改人：Administrator
* 修改时间：2015年4月16日 下午5:07:04
* 修改备注：
*
*/
public class CourseDao extends DaoAbstract {
	
	String hql = "" ;

	/** 
	* 方法： insert
	* 描述： 
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月16日 上午11:00:15
	* 修改人：Administrator
	* 修改时间：2015年4月16日 上午11:00:15
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public JSONObject insert ( CourseEntity course ) {
		Session session = GavinHibernateSessionFactory.getSession() ;
		JSONObject json = new JSONObject() ;
		String ranString = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890" ;
		String theRan = "" ;
		boolean clash = true ;
		Random random = new Random() ;
		char[] ranArray = new char[20] ;
		session.clear() ;
		session.beginTransaction() ;
		while ( clash ) {
			for (int i = 0; i < ranArray.length; i++) {
				ranArray[i] = ranString.charAt( random.nextInt( 36 ) ) ;
				theRan = theRan + ranArray[i] ;
			}
			try {
				session.flush();
				hql = "from CourseEntity c where c.courseNum=?" ;
				Query query = session.createQuery( hql ).setString( 0 , theRan ) ;
				List<CourseEntity> listCourses = query.list() ;
				if( listCourses.size() == 0 ) {
					clash = false ;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		Short check = 0 ;
		course.setIsChecked( check ) ;
		course.setCourseNum( theRan );
		course.setAudienceNum( 0 ) ;
		course.setSubscriptionNum( 0 ) ;
		if ( "0".equals( course.getCourseType() ) ) {
			course.setCourseType( 0 ) ;
		}else {
			course.setCourseType( 1 ) ;
		}
		try {
			session.flush() ;
			session.save( course ) ;
			hql = "from CourseEntity c where c.courseNum=?" ;
			session.flush() ;
			Query query = session.createQuery(hql).setString( 0, theRan ) ;
			List<CourseEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			json.put( "result" , 0 ) ;
			json.put( "CourseNum", lists.get(0).getCourseNum() ) ;
			return json ;
		} catch (Exception e) {
			// TODO: handle exception
			json.put( "result" , 1 ) ;
			e.printStackTrace() ;
			return json ;
		}
	}
	

	/** 
	* 方法： update
	* 描述： 
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月16日 上午11:00:11
	* 修改人：Administrator
	* 修改时间：2015年4月16日 上午11:00:11
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public boolean update ( CourseEntity course ) {
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c where c.courseNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setString( 0 , course.getCourseNum() ) ;
			List<CourseEntity> lists = query.list() ;
			CourseEntity newCourse = lists.get(0) ;
			newCourse.setCourseName( course.getCourseName() ) ;
			newCourse.setCourseContent( course.getCourseContent() ) ;
			newCourse.setSubId( course.getSubId() ) ;
			session.flush() ;
			session.update( newCourse ) ;
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
	* 方法： QueryByCourseNum
	* 描述： 通过课程Num查找课程
	* 返回： CourseEntity
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:38:29
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:38:29
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public CourseEntity QueryByCourseNum ( String courseNum ) {
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c where c.courseNum=?" ;
		try {
			session.clear() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0, courseNum ) ;
			List<CourseEntity> lists = query.list() ;
			session.flush() ;
			if( lists == null || lists.size() == 0 ){
				return null ;
			}else {
				return lists.get(0) ;				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}
	}
	
	/** 
	* 方法： queryByUserId
	* 描述： 通过创建者ID遍历教师发布的课程
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:39:35
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:39:35
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public List<CourseEntity> queryByUserNum( String userNum ) {
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c where c.userNum=?";
		try {
			session.clear() ;
			Query query = session.createQuery(hql) ;
			query.setString( 0 , userNum ) ;
			List<CourseEntity> lists = query.list() ;
			session.flush() ;
			return lists ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}
	}


	/** 
	* 方法： AddAudienceNum
	* 描述： 使观看数+1
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午5:31:03
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午5:31:03
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public boolean AddAudienceNum( int courseId ) {
		Session session = GavinHibernateSessionFactory.getSession() ;
		// TODO Auto-generated method stub
		hql = "from CourseEntity c where c.id=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql);
			query.setInteger( 0, courseId ) ;
			List<CourseEntity> lists = query.list() ;
			session.flush() ;
			if( lists == null || lists.size() == 0 ){
				session.getTransaction().rollback() ;
				return false ;
			} else {				
				CourseEntity course = lists.get(0) ;
				course.setAudienceNum( course.getAudienceNum() + 1 ) ;
				session.update( course ) ;
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
	* @Title:       QueryAllCourses
	* @Description: 遍历所有课程
	* @param:       @return
	* @return:      List<CourseEntity>
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public List<CourseEntity> QueryAllCourses() {
		Session session = GavinHibernateSessionFactory.getSession() ;
		// TODO Auto-generated method stub
		hql = "from CourseEntity";
		try {
			session.clear() ;
			Query query = session.createQuery(hql) ;
			List<CourseEntity> lists = query.list() ;
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
	* @Title:       check
	* @Description: 审核课程通过
	* @param:       @param courseNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public boolean check( String courseNum ){
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c where c.courseNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , courseNum ) ;
			List<CourseEntity> lists = query.list() ;
			if( lists != null || lists.size() != 0 ) {
				CourseEntity c = lists.get(0) ;
				Short check = 1 ;
				if( c.getIsChecked() == 1 ) {
					check = 0 ;
				} else if( c.getIsChecked() == 0 ) {
					check = 1 ;
				}
				c.setIsChecked( check ) ;
				session.flush() ;
				session.update( c ) ;					
				session.flush() ;
				session.getTransaction().commit() ;
				return true ;
			} else {
				return false ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return false ;
		}
	}
	
	/**
	* @Title:       QueryUnChecked
	* @Description: 查看未通过审核的课程
	* @param:       @return
	* @return:      List<CourseEntity>
	* @throws
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public List<CourseEntity> QueryUnChecked( Short check ) {
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c where c.isChecked=?" ;
		try {
			session.clear() ;
			Query query = session.createQuery( hql ) ;
			query.setShort( 0 , check ) ;
			List<CourseEntity> lists = query.list() ;
			session.flush() ;
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
	* @Title:       OpenComment
	* @Description: 开放聊天功能
	* @param:       @param courseNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public boolean OpenComment(String courseNum) {
		// TODO Auto-generated method stub
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c where c.courseNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , courseNum ) ;
			List<CourseEntity> lists = query.list() ;
			Short checkComment = 1 ;
			lists.get(0).setIsComment( checkComment ) ; 
			session.flush() ;
			session.update( lists.get(0) ) ;
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
	* @Title:       QuerySimilar
	* @Description: 迷糊搜索
	* @param:       @param courseName
	* @param:       @return
	* @return:      List<CourseEntity>
	* @throws
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public List<CourseEntity> QuerySimilar( String courseName ) {
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c where c.courseName like :courseName" ;
		try {
			session.clear() ; 
			Query query = session.createQuery( hql ) ;
			query.setString( "courseName" , "%"+courseName+"%") ;
			List<CourseEntity> lists = query.list() ;
			session.flush() ;
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
	* @Title:       isMyCourse
	* @Description: 判断用户是否为课程的创建者
	* @param:       @param c
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public boolean isMyCourse(CourseEntity c) {
		// TODO Auto-generated method stub
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c where c.userNum=? and c.courseNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query  = session.createQuery( hql ) ;
			query.setString( 0 , c.getUserNum() ) ;
			query.setString( 1 , c.getCourseNum() ) ;
			List<CourseEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if ( lists == null || lists.size() == 0 ) {
				// 用户非本课程的创建人
				return false ;
			} else {
				// 用户确为本课程的创建人
				return true ;
			}
 		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return false ;
		}
	}


	/**
	* @Title:       QueryBySubId
	* @Description: 根据学科查找课程
	* @param:       @param subId
	* @param:       @return
	* @return:      List<CourseEntity>
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public List<CourseEntity> QueryBySubId(Integer subId) {
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c where c.subId=? Order by c.audienceNum DESC" ;
		try {
			session.clear() ;
			Query query  = session.createQuery( hql ) ;
			query.setInteger( 0 , subId ) ;
			List<CourseEntity> lists = query.list() ;
			session.flush() ;
			if( lists == null || lists.size() == 0 ) {
				return null ;
			}else {
				return lists ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			session.getTransaction().rollback() ;
			return null ;
		}
	}


	/**
	* @Title:       showHotCourse
	* @Description: 显示首页课程
	* @param:       @param listNum
	* @param:       @return
	* @return:      List<CourseEntity>
	* @throws
	*/ 
	public List<CourseEntity> showHotCourse(Integer listNum) {
		// TODO Auto-generated method stub
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c Order by c.audienceNum DESC" ;
		try{
			session.clear() ;
			Query query = session.createQuery( hql ) ;
			query.setFirstResult( 0 ) ;
			query.setMaxResults( listNum ) ;
			List<CourseEntity> lists = query.list() ;
			session.flush() ;
			if ( lists == null || lists.size() == 0 ) {
				return null ;
			} else {
				return lists ;
			}
		} catch ( Exception e ) {
			e.printStackTrace() ;
			return null ;
		}
	}
	
	/**
	* @Title:       showHotCourse
	* @Description: 显示首页热门课程
	* @param:       @param listNum
	* @param:       @return
	* @return:      List<CourseEntity>
	* @throws
	*/ 
	public List<CourseEntity> queryPopularCourse( Integer indexNum ) {
		// TODO Auto-generated method stub
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c Order by c.audienceNum DESC" ;
		Integer i = indexNum + 4 ; // 增加4条可选内容，一面内容错误
		try{
			session.clear() ;
			Query query = session.createQuery( hql ) ;
			query.setFirstResult( 0 ) ;
			query.setMaxResults( i ) ;
			List<CourseEntity> lists = query.list() ;
			session.flush() ;
			if ( lists == null || lists.size() == 0 ) {
				return null ;
			} else {
				return lists ;
			}
		} catch ( Exception e ) {
			e.printStackTrace() ;
			return null ;
		}
	}
	
	/**
	* @Title:       showHotCourse
	* @Description: 显示首页热门课程
	* @param:       @param listNum
	* @param:       @return
	* @return:      List<CourseEntity>
	* @throws
	*/ 
	public List<CourseEntity> queryNewestCourse( Integer indexNum ) {
		// TODO Auto-generated method stub
		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from CourseEntity c Order by c.createtime DESC" ;
		Integer i = indexNum + 4 ; // 增加4条可选内容，一面内容错误
		try{
			session.clear() ;
			Query query = session.createQuery( hql ) ;
			query.setFirstResult( 0 ) ;
			query.setMaxResults( i ) ;
			List<CourseEntity> lists = query.list() ;
			session.flush() ;
			if ( lists == null || lists.size() == 0 ) {
				return null ;
			} else {
				return lists ;
			}
		} catch ( Exception e ) {
			e.printStackTrace() ;
			return null ;
		}
	}
	
} 
