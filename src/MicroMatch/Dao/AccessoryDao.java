package MicroMatch.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import MicroMatch.Entity.AccessoryEntity;
import MicroMatch.Factory.HibernateSessionFactory;

public class AccessoryDao extends DaoAbstract {
	String hql = "" ;

	/** 
	* 方法： insert
	* 描述： 上传附件时生成数据库数据
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:50:40
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:50:40
	* 修改备注：
	* @throws 
	*/ 
	public boolean insert ( AccessoryEntity accessory ) {
		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.save( accessory ) ;
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
	public boolean update ( AccessoryEntity accessory ) {
		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.update( accessory ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			return false ;
		}
	}
	
	/** 
	* 方法： delete
	* 描述： 删除附件
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:56:25
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:56:25
	* 修改备注：
	* @throws 
	*/ 
	public boolean delete ( int accessoryId ) {
		hql = "delete AccessoryEntity where accessoryId=?" ;
		Session session = HibernateSessionFactory.getSession() ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			session.createQuery( hql ).setInteger( 0, accessoryId ).executeUpdate() ;
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
	* 方法： QueryByObjectTypeAndNum
	* 描述： 通过对象类型和Id查找下属附件
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:56:35
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:56:35
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public List<AccessoryEntity> QueryByObjectTypeAndNum(AccessoryEntity accessory) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from AccessoryEntity a where a.objectType=? and a.objectNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setInteger( 0 , accessory.getObjectType() ) ;
			query.setString( 1 , accessory.getObjectNum() ) ;
			List<AccessoryEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if( lists == null || lists.size() == 0 ){
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
	* 方法： AddDownloadNum
	* 描述： 增加文件下载记录
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午4:57:02
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午4:57:02
	* 修改备注：
	* @throws 
	*/ 
	public boolean AddDownloadNum(AccessoryEntity accessory) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession() ;
		hql = "from AccessoryEntity where id=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setInteger( 0, accessory.getId() ) ;
			List<AccessoryEntity> lists = query.list() ;
			accessory = lists.get( 0 ) ;
			accessory.setDownloadNum( accessory.getDownloadNum() + 1 );
			session.flush() ;
			session.update( accessory ) ;
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
