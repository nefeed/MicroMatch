package MicroMatch.Dao;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;

import MicroMatch.Entity.ChapterEntity;
import MicroMatch.Factory.HibernateSessionFactory;

public class ChapterDao extends DaoAbstract {
	String hql = "" ;
	
	/** 
	* 方法： insert
	* 描述： 创建章节
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月16日 下午7:21:25
	* 修改人：Administrator
	* 修改时间：2015年4月16日 下午7:21:25
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public ChapterEntity insert ( ChapterEntity chapter ) {

		Session session = HibernateSessionFactory.getSession() ;
		session.clear() ;
		session.beginTransaction() ;
		String courseNum = chapter.getCourseNum() ;
		String ranString = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890" ;
		String theRan = "" ;
		
		boolean clash = true ;
		Random random = new Random() ;
		char[] ranArray = new char[20] ;
		while ( clash ) {
			for (int i = 0; i < ranArray.length; i++) {
				ranArray[i] = ranString.charAt( random.nextInt( 36 ) ) ;
				theRan = theRan + ranArray[i] ;
			}
			hql = "from ChapterEntity where chapterNum=?" ;
			session.flush();
			Query query = session.createQuery(hql).setString( 0 , theRan ) ;
			query.setMaxResults(1);
			List<ChapterEntity> listChapter = query.list() ;
			if( listChapter.size() == 0 ) {
				clash = false ;
			}
		}
		Short check = 0 ;
		chapter.setIsChecked( check ) ;
		chapter.setChapterNum( theRan ) ;
		System.out.println("将要保存的Chapter:" + chapter.toString());
		int period = this.showPeriod( courseNum ) ;
		int lid = chapter.getListId() ;
		System.out.println("本课程 " + courseNum + " 的章节数:" + period);
		try {
			if ( lid < period ) {
				hql = "from ChapterEntity where courseNum=? and listId=?" ;
				for ( int i = lid ; i < period ; i++ ) {
					Query query = session.createQuery( hql ) ;
					query.setString( 0 , courseNum ) ;
					query.setInteger( 1 , i ) ;
					query.setMaxResults(1);
					List<ChapterEntity> templ = query.list() ;
					ChapterEntity tempe = templ.get(0) ;
					tempe.setListId( tempe.getListId() + 1 ) ;
					session.update(tempe) ;
					session.flush() ;
				}
			}
			session.save(chapter) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return chapter ;
		} catch ( Exception e ) {
			e.printStackTrace() ;
			return null ;
		}
	}
	
	/** 
	* 方法： insert
	* 描述： 更新章节
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月16日 下午7:21:25
	* 修改人：Administrator
	* 修改时间：2015年4月16日 下午7:21:25
	* 修改备注：
	* @throws 
	*/ 
	public boolean update ( ChapterEntity chapter ) {

		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.update( chapter ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			return false ;
		}
	}

	/** 
	* 方法： QueryByNum
	* 描述： 根据Num查找实体
	* 返回： ChapterEntity
	* 创建人：章华隽
	* 创建时间：2015年4月16日 下午7:29:57
	* 修改人：Administrator
	* 修改时间：2015年4月16日 下午7:29:57
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public ChapterEntity QueryByNum( String chapterNum ) {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from ChapterEntity where chapterNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setString( 0 , chapterNum ) ;
			query.setMaxResults(1);
			List<ChapterEntity> listChapter = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if( listChapter != null || listChapter.size() != 0 ){
				return listChapter.get(0) ;				
			}else {
				return null ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}
	}


	@SuppressWarnings("unchecked")
	public boolean Upload( ChapterEntity chapter ) {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from ChapterEntity where chapterNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql).setString( 0, chapter.getChapterNum() ) ;
			query.setMaxResults(1);
			session.flush() ;
			List<ChapterEntity> lists = query.list() ;
			ChapterEntity c = lists.get(0) ;
			c.setChapterVideo( chapter.getChapterVideo() ) ;
			c.setChapterVideoName( chapter.getChapterVideoName( ) ) ;
			session.update( c ) ;
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
	* @Title:       QueryByCourseNum
	* @Description: 通过课程Num查找地下所有章节
	* @param:       @param courseNum
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public List<ChapterEntity> QueryByCourseNum(String courseNum) {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from ChapterEntity where courseNum=? order by listId asc" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql).setString( 0, courseNum ) ;
			List<ChapterEntity> lists = query.list() ;
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
	* @Title:       check
	* @Description: 审核章节通过
	* @param:       @param chapterNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public boolean check ( String chapterNum ) {

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from ChapterEntity where chapterNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , chapterNum ) ;
			query.setMaxResults(1);
			List<ChapterEntity> lists = query.list() ;
			session.flush() ;
			if( lists == null || lists.size() == 0 ) {
				return false ;
			} else {
				ChapterEntity c = lists.get(0) ;
				Short check = 1 ;
				if( c.getIsChecked() == 1 ) {
					check = 0 ;
				} else if( c.getIsChecked() == 0 ) {
					check = 1 ;
				}
				c.setIsChecked( check ) ;
				session.update( c ) ;
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
	* @Description: 遍历未审核的章节视频
	* @param:       @return
	* @return:      List<ChapterEntity>
	* @throws
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public List<ChapterEntity> QueryUnChecked( Short check ) {

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from ChapterEntity where isChecked=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setShort( 0 , check ) ;
			List<ChapterEntity> lists = query.list() ;
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
	* @Title:       OpenComment
	* @Description: 开放评论
	* @param:       @param chapterNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings("unchecked")
	public boolean OpenComment(String chapterNum) {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from ChapterEntity where chapterNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , chapterNum ) ;
			query.setMaxResults(1);
			List<ChapterEntity> lists = query.list() ;
			session.flush() ;
			Short checkComment = 1 ;
			lists.get(0).setIsComment( checkComment ) ; 
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
	* 方法： showPeriod
	* 描述： 显示学时
	* 返回： int
	* 创建人：章华隽
	* 创建时间：2015年5月10日 上午10:32:45
	* 修改人：GavinVictory
	* 修改时间：2015年5月10日 上午10:32:45
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings({ "null", "unchecked" })
	public int showPeriod( String courseNum ) {

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from ChapterEntity where courseNum=?" ;
		try {
			session.clear() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , courseNum ) ;
			List<ChapterEntity> lists = query.list() ;
			session.flush() ;
			if( lists == null || lists.size() == 0 ) {
				return 0 ;
			}else {
				return lists.size() ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return 0;
		}
	}
	
	/**
	* @Title:       showChoose
	* @Description: 返回选中的章节
	* @param:       @param cha
	* @param:       @return
	* @return:      List<ChapterEntity>
	* @throws
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public List<ChapterEntity> showChoose ( ChapterEntity cha ){

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from ChapterEntity where courseNum=? and listId=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , cha.getCourseNum() ) ;
			query.setInteger( 1 , cha.getListId() ) ;
			query.setMaxResults(1);
			List<ChapterEntity> lists = query.list() ;
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
}
