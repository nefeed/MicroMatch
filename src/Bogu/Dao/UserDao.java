package Bogu.Dao;

import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;

import Bogu.Entity.UserEntity;
import Bogu.Factory.BoguHibernateSessionFactory;
import Bogu.Tools.MD5Tool;


 
/**
* 项目名称：BoguCloudCourse
* 类名称：UserDao
* 类描述：用户数据层
* 创建人：章华隽
* 创建时间：2015年4月13日 下午7:19:19
* 修改人：Administrator
* 修改时间：2015年4月15日 下午7:19:19
* 修改备注：
*
*/
public class UserDao extends DaoAbstract { 
	String hql = "" ;
	
	/** 
	* 方法： insert
	* 描述： 注册账号并保存
	* 返回： JSONObject
	* 创建人：章华隽
	* 创建时间：2015年4月13日 下午7:19:29
	* 修改人：Administrator
	* 修改时间：2015年4月15日 下午7:19:29
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public JSONObject insert ( UserEntity user ) {
		Session session = BoguHibernateSessionFactory.getSession();
		String ranString = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890" ;
		String theRan = "" ;
		JSONObject json = new JSONObject() ;
		int result = 0 ;
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
			session.flush();
			hql = "from UserEntity where userNum=?" ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , theRan ) ;
			List<UserEntity> listUser = query.list() ;
			if( listUser.size() == 0 ) {
				clash = false ;
			}
		}
		user.setUserNum( theRan ) ;
		if ( "".equals( user.getNickName() ) ) {
			user.setNickName( user.getUserName() ) ;
		}
		user.setUserPassword( MD5Tool.GetMD5Code( user.getUserPassword() ) ) ;
		try {
			session.flush();
			hql = "from UserEntity where userName=?" ;
			Query query = session.createQuery(hql).setString( 0 , user.getUserName() ) ;
			List<UserEntity> listUser = query.list() ;
			if( listUser.size() == 1 ) {
				result = 1 ;
			}else {
				try {
					session.flush() ;
					session.save(user) ;
					json.put( "ID" , user.getId() ) ;
					json.put( "UserNum" , user.getUserNum() ) ;
					json.put( "UserName" , user.getUserName() ) ;
					json.put( "NickName" , user.getNickName() ) ;
					json.put( "UserType" , user.getUserType() ) ;
					result = 0 ;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace() ;
					System.out.println("账号注册出错！");
				}
			}
			session.flush() ;
			session.getTransaction().commit() ;
			json.put( "result" , result ) ;
			return json ;
		}catch (Exception e) {
			e.printStackTrace();
			json.put( "result" , -1 ) ;
			return json ;
		}
	}
	
	public boolean update ( UserEntity user ) {
		Session session = BoguHibernateSessionFactory.getSession();
		try {
			session.clear() ;
			session.beginTransaction() ;
			System.out.println("update方法获得的用户头像数据：" + user.getUserPicture() ) ;
			session.update( user ) ;
			session.flush() ;
			session.getTransaction().commit() ;
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			System.out.println( user.getUserName() + "用户修改失败！");
			return false ;
		}
	}

	/** 
	* 方法： updatePassword
	* 描述： 修改密码
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月13日 下午7:19:51
	* 修改人：Administrator
	* 修改时间：2015年4月15日 下午7:19:51
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings({ "unchecked" })
	public boolean UpdatePassword ( UserEntity user ) {
		Session session = BoguHibernateSessionFactory.getSession();
		hql = "from UserEntity where userNum=?" ;
		session.clear() ;
		session.beginTransaction() ;
		Query query = session.createQuery(hql).setString( 0, user.getUserNum() ) ;
		List<UserEntity> listuser = query.list() ;
		session.flush() ;
		session.getTransaction().commit();
		listuser.get(0).setUserPassword( MD5Tool.GetMD5Code(user.getUserPassword()) ) ;
		try {
			user.setUserPassword( MD5Tool.GetMD5Code( user.getUserPassword() ) ) ;
			return update( listuser.get(0) );
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	/** 
	* 方法： Login
	* 描述： 
	* 返回： JSONObject
	* 创建人：章华隽
	* 创建时间：2015年4月13日 下午7:19:58
	* 修改人：Administrator
	* 修改时间：2015年4月15日 下午7:19:58
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public JSONObject Login ( UserEntity user ) {
		Session session = BoguHibernateSessionFactory.getSession();
		int result = 2 ;
		JSONObject json = new JSONObject() ;
		hql = "from UserEntity where userName=?";
		try{
			session.clear();
			session.beginTransaction();
			Query query = session.createQuery(hql).setString(0, user.getUserName() ) ;
			List<UserEntity> listUser = query.list() ;
			session.flush() ;
			session.getTransaction().commit();
			if( listUser.size() == 0 ) {
				result = 2 ;  // 没有这个账号
			}else {
				if( MD5Tool.GetMD5Code(user.getUserPassword()).equals( listUser.get(0).getUserPassword() ) ){
					result = 0 ;  // 验证成功
					json.put( "NickName" , listUser.get(0).getNickName() ) ;
					json.put( "UserNum" , listUser.get(0).getUserNum() ) ;
					json.put( "UserName" , listUser.get(0).getUserName() ) ;
					json.put( "UserType" , listUser.get(0).getUserType() ) ;
					System.out.println(json);
				}else {
					result = 1 ;  // 密码错误
				}
			}
			json.put( "result" , result ) ;
			return json ;
				 
		}catch (Exception e) {
			e.printStackTrace();
			json.put( "result" , -1 ) ;
			return json ;
		}
	}
	
	/** 
	* 方法： QueryByUserNum
	* 描述： 根据用户Num查找用户（所有用户操作需要用到这个功能）
	* 返回： UserEntity
	* 创建人：章华隽
	* 创建时间：2015年4月16日 下午5:07:31
	* 修改人：Administrator
	* 修改时间：2015年4月16日 下午5:07:31
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public UserEntity QueryByUserNum( String userNum ) {
		Session session = BoguHibernateSessionFactory.getSession();
		hql = "from UserEntity where userNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql) ;
			query.setString( 0 , userNum ) ;
			List<UserEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if( lists == null || lists.size() == 0 ) {
				return null ;				
			} else {
				return lists.get(0) ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}
	}

	/** 
	* 方法： QueryById
	* 描述： 根据用户Id返回用户
	* 返回： UserEntity
	* 创建人：章华隽
	* 创建时间：2015年4月20日 下午5:47:25
	* 修改人：Administrator
	* 修改时间：2015年4月20日 下午5:47:25
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("unchecked")
	public UserEntity QueryById(Integer userId) {
		Session session = BoguHibernateSessionFactory.getSession();
		// TODO Auto-generated method stub
		hql = "from UserEntity where id=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery(hql).setInteger(0, userId) ;
			List<UserEntity> listUser = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			return listUser.get(0) ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return null ;
		}
	}

	/**
	* @Title:       ChangeUserType
	* @Description: 修改用户类别
	* @param:       @param user
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public boolean ChangeUserType(UserEntity user) {
		Session session = BoguHibernateSessionFactory.getSession();
		// TODO Auto-generated method stub
		hql = "from UserEntity where userNum=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , user.getUserNum() ) ;
			List<UserEntity> lists = query.list() ;
			if( lists != null || lists.size() != 0 ) {
				UserEntity u = lists.get( 0 ) ;
				u.setUserType( user.getUserType() ) ;
				session.flush() ;
				session.update( u ) ;
			}
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
	* @Title:       QueryAll
	* @Description: 查找所有用户
	* @param:       @return
	* @return:      List<UserEntity>
	* @throws
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public List<UserEntity> QueryAll( Short isLock ) {
		Session session = BoguHibernateSessionFactory.getSession();
		// TODO Auto-generated method stub
		hql = "from UserEntity where isLock=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			
			Query query = session.createQuery( hql ) ;
			query.setShort( 0 , isLock ) ;
			List<UserEntity> lists = query.list() ;
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
	
	/**
	* @Title:       QueryByUserType
	* @Description: 根据用户类别查找用户
	* @param:       @param userType
	* @param:       @return
	* @return:      List<UserEntity>
	* @throws
	*/ 
	@SuppressWarnings({ "unchecked", "null" })
	public List<UserEntity> QueryByUserType( int userType ) {
		Session session = BoguHibernateSessionFactory.getSession();
		hql = "from UserEntity where userType=?" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setInteger( 0 , userType ) ;
			List<UserEntity> lists = query.list() ;
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
	
	@SuppressWarnings({ "unchecked", "null" })
	public List<UserEntity> QueryBySimilarNick( String nickName ) {
		Session session = BoguHibernateSessionFactory.getSession();
		hql = "from UserEntity where nickName like :name" ;
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( "name" , "%"+nickName+"%" ) ;
			List<UserEntity> lists = query.list() ;
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

	/**
	* @Title:       updatePic
	* @Description: 更新用户头像
	* @param:       @param u
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean updatePic(UserEntity u) {
		// TODO Auto-generated method stub
		Session session = BoguHibernateSessionFactory.getSession() ;
		hql = "from UserEntity where userNum=?" ;
		System.out.println( "传值得到的pic:"+u.getUserPicture() );
		try {
			session.clear() ;
			session.beginTransaction() ;
			Query query = session.createQuery( hql ) ;
			query.setString( 0 , u.getUserNum() ) ;
			List<UserEntity> lists = query.list() ;
			session.flush() ;
			session.getTransaction().commit() ;
			if( lists == null || lists.size() == 0 ) {
				return false ;
			} else {
				UserEntity newU = lists.get(0) ;
				newU.setUserPicture( u.getUserPicture() ) ;
				return this.update( newU ) ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			return false ;
		}
	}
}

