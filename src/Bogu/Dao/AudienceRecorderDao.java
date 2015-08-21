package Bogu.Dao;

import java.sql.Timestamp;
import java.util.List;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;

import Bogu.Entity.AudienceRecorderEntity;
import Bogu.Factory.GavinHibernateSessionFactory;

public class AudienceRecorderDao extends DaoAbstract {
	String hql = "" ;
	
	/** 
	* 方法： insert
	* 描述： 新建观看记录,若已经观看过，则更新观看时间
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午4:31:52
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午4:31:52
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public JSONObject insert( AudienceRecorderEntity audienceRecorder ) {

		Session session = GavinHibernateSessionFactory.getSession() ;
		hql = "from AudienceRecorderEntity where userNum=? and courseNum=?" ;
		JSONObject json = new JSONObject() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0, audienceRecorder.getUserNum() ) ;
			query.setString( 1, audienceRecorder.getCourseNum() ) ;
			List<AudienceRecorderEntity> lists = query.list() ;
			session.flush() ;
			if( lists == null || lists.size() == 0 ){
				session.save( audienceRecorder ) ;
				// result的值为0:新建观看记录成功
				json.put( "result" , 0 ) ;
			}else {
				audienceRecorder = lists.get(0) ;
				audienceRecorder.setRecorderTime( new Timestamp( System.currentTimeMillis() ) ) ;
				session.update( audienceRecorder ) ;
				// result的值为1:已存在观看记录,修改成功
				json.put( "result" , 1 ) ;
			}
			session.flush() ;
			session.getTransaction().commit() ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			json.put( "result" , 2 ) ;
		}
		return json ;
	}
	
	/** 
	* 方法： update
	* 描述： 修改观看记录
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午4:32:43
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午4:32:43
	* 修改备注：
	* @throws 
	*/ 
	public boolean update( AudienceRecorderEntity audienceRecorder ) {

		Session session = GavinHibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.update( audienceRecorder ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return false ;
		}
	}
}
