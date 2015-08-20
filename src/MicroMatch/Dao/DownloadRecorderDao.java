package MicroMatch.Dao;

import java.sql.Timestamp;
import java.util.List;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;

import MicroMatch.Entity.DownloadRecorderEntity;
import MicroMatch.Factory.HibernateSessionFactory;

public class DownloadRecorderDao extends DaoAbstract {
	String hql = "" ;
	
	/** 
	* 方法： insert
	* 描述： 新增下载记录
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午4:35:42
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午4:35:42
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public JSONObject insert( DownloadRecorderEntity downloadRecorder) {

		Session session = HibernateSessionFactory.getSession() ;
		hql = "from DownloadRecorderEntity where userNum=? and accessoryId=?" ;
		JSONObject json = new JSONObject() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0, downloadRecorder.getUserNum() ) ;
			query.setInteger( 1, downloadRecorder.getAccessoryId() ) ;
			List<DownloadRecorderEntity> lists = query.list() ;
			session.flush() ;
			if( lists == null || lists.size() == 0 ){
				session.save( downloadRecorder ) ;
				// result的值为0:新建下载记录成功
				json.put( "result" , 0 ) ;
			}else {
				downloadRecorder = lists.get(0) ;
				downloadRecorder.setDownloadTime( new Timestamp( System.currentTimeMillis() ) ) ;
				session.update( downloadRecorder ) ;
				// result的值为1:已存在下载记录,修改成功
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
	* 描述： 修改记录
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午4:35:59
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午4:35:59
	* 修改备注：
	* @throws 
	*/ 
	public boolean update( DownloadRecorderEntity downloadRecorder ) {

		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.update( downloadRecorder ) ;
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
