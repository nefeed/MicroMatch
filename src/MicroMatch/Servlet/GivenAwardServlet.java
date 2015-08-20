package MicroMatch.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import MicroMatch.Entity.AwardEntity;
import MicroMatch.Entity.MatchEntity;
import MicroMatch.Factory.BllFacadeFactory;
import MicroMatch.Interface.BllInterface;

public class GivenAwardServlet extends HttpServlet {
	private BllFacadeFactory bllFacadeFactory = BllFacadeFactory.getBllFactoryInstance() ;
	private BllInterface bllInterface = bllFacadeFactory.getInterface() ;
	
	/**
	 * Constructor of the object.
	 */
	public GivenAwardServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response) ;
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8") ;
		response.setContentType("text/html;charset=utf-8") ;
		PrintWriter out = response.getWriter() ;
		String userNum = request.getParameter( "UserNum" ) ;
		String matchNum = request.getParameter( "MatchNum" ) ;
		MatchEntity m = new MatchEntity() ;
		m.setUserNum(userNum) ;
		m.setMatchContent(matchNum) ;
		int awardId =  Integer.parseInt( request.getParameter( "AwardId" ) ) ;
		String courseNum = request.getParameter( "CourseNum" ) ;
		AwardEntity a = new AwardEntity() ;
		a.setAwardTime( new Timestamp( System.currentTimeMillis() ) ) ;
		a.setCourseNum(courseNum);
		a.setId(awardId) ;
		JSONObject json = new JSONObject() ;
		if( bllInterface.isMyMatch(m) ) {
			if( bllInterface.GivenAward( a ) ) {
				json.put( "result" , 0 ) ;
			}else {
				json.put( "result" , 1 ) ;
			}			
		}else {
			json.put( "reuslt", 2 ) ;
		}
		out.print(json) ;
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
