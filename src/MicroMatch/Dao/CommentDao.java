package MicroMatch.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import MicroMatch.Entity.CommentEntity;
import MicroMatch.Factory.HibernateSessionFactory;

public class CommentDao extends DaoAbstract {
	String hql = "" ;
	
	/** 
	* 方法： insert
	* 描述： 创建评论
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月16日 下午7:21:25
	* 修改人：Administrator
	* 修改时间：2015年4月16日 下午7:21:25
	* 修改备注：
	* @throws 
	*/ 
	public boolean insert ( CommentEntity comment ) {
		Session session = HibernateSessionFactory.getSession() ;
		try {
			System.out.println("本次comment保存的内容为：\n"+comment.getCommentContent() );
			session.clear() ;
			session.beginTransaction() ;
			session.save( comment ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			return false ;
		}
	}
	
	/** 
	* 方法： update
	* 描述： 更新评论
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月16日 下午7:21:25
	* 修改人：Administrator
	* 修改时间：2015年4月16日 下午7:21:25
	* 修改备注：
	* @throws 
	*/ 
	public boolean update ( CommentEntity comment ) {
		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.update( comment ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			return false ;
		}
	}
	
	/** 
	* 方法： queryByObjectId
	* 描述： 遍历本课程或本章节的评论
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年4月17日 上午11:32:32
	* 修改人：Administrator
	* 修改时间：2015年4月17日 上午11:32:32
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public List<CommentEntity> QueryByObjectTypeAndNum ( CommentEntity comment ) {
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from CommentEntity c where c.objectNum=? and c.objectType=? and c.pid=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setString( 0 , comment.getObjectNum() ) ;
			query.setInteger( 1 , comment.getObjectType() ) ;
			query.setInteger( 2 , comment.getPid() ) ;
			List<CommentEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if( lists == null || lists.size() == 0){
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
	* 方法： showReplay
	* 描述： 查找回复
	* 返回： List<CommentEntity>
	* 创建人：章华隽
	* 创建时间：2015年5月25日 下午10:16:03
	* 修改人：Gavin
	* 修改时间：2015年5月25日 下午10:16:03
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public List<CommentEntity> showReply(Integer pid) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from CommentEntity where pid=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setInteger( 0 , pid ) ;	
			List<CommentEntity> lists = query.list() ;
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
}
