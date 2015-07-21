package Bogu.Test;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONObject;
import Bogu.Dao.UserDao;
import Bogu.Entity.UserEntity;

public class UserTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UserEntity user = new UserEntity() ;
		user.setUserName( "a" ) ;
		user.setNickName( "TestAdminUser" ) ;
		user.setUserPassword("1") ;
		short s = 0 ;
		user.setIsAdmin( s ) ;
		user.setUserType( 0 ) ;
		
		Date date = new Date() ;
		Timestamp timestamp = new Timestamp( System.currentTimeMillis() ) ;
		user.setRegTime( timestamp ) ;
		UserDao userDao = new UserDao() ;
		userDao.update(user) ;
		JSONObject json = userDao.insert(user) ;
		
		System.out.println("我就试试Json："+json.getInt("result"));
	}

}
