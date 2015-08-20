package MicroMatch.Test;

import net.sf.json.JSONObject;
import MicroMatch.Dao.UserDao;
import MicroMatch.Entity.UserEntity;
import MicroMatch.Tools.MD5Tool;

public class UserLoginTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserEntity user = new UserEntity() ;
		user.setId(212);
		user.setUserNum("MXPFPRQT214NE5APIL0F");
		user.setUserName("a");
		user.setPassword("1");
		UserDao userDao = new UserDao() ;
		System.out.println( MD5Tool.GetMD5Code(user.getPassword()));
		JSONObject json = userDao.Login(user) ;
		System.out.println(json);
	}

}
