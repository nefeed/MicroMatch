package Bogu.Test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Bogu.Dao.CommentDao;
import Bogu.Entity.CommentEntity;
import Bogu.Factory.GavinHibernateSessionFactory;

public class CommentQueryTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		CommentEntity com = new CommentEntity() ;
		com.setObjectType( 0 ) ;
		com.setObjectNum("FDK8DDFXH6DN940INKOU");
		Session s = GavinHibernateSessionFactory.getSession() ;
		String hql = "from CommentEntity c where c.objectNum=? and c.objectType=?" ;
		s.beginTransaction() ;
		Query query = s.createQuery( hql ).setString(0 , com.getObjectNum() ).setInteger(1, com.getObjectType()) ;
		List<CommentEntity> lists = query.list() ;
		s.getTransaction().commit() ;
		System.out.println( lists.get(0).getCommentContent() );
		CommentDao commentDao = new CommentDao() ;
		lists = commentDao.QueryByObjectTypeAndNum(com) ;
		System.out.println( lists );
		
	}
}
