package Bogu.Tools;
import java.sql.*;

/**   
 *    
 * 项目名称：Experiment   
 * 类名称  ：JDBCConnection   
 * 类描述  ：
 * 创建人  ：章华隽   
 * 创建时间：2015年1月10日 下午8:51:36       
 *    
 */
public class GavinConnection {
	
	public static Connection getConnection(){
	
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类，加载驱动失败！");
			e.printStackTrace();
		}
	
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=BG_CloudCourse";
		String username = "sa";
		String password = "bogusoft2011";
	
		Connection conn = null;	
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException se) {
			se.printStackTrace();
		}
	
		return conn;
	}
}
