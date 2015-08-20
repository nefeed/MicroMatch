package MicroMatch.Test;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONObject;
import MicroMatch.Dao.UserDao;
import MicroMatch.Entity.UserEntity;

public class UserTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UserEntity user = new UserEntity() ;
		user.setUserName( "a" ) ;
		user.setNickName( "TestAdminUser" ) ;
		user.setPassword("1") ;
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
