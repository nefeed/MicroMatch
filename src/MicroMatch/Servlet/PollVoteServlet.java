package MicroMatch.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import MicroMatch.Entity.PollVoteEntity;
import MicroMatch.Entity.RegistrationEntity;
import MicroMatch.Factory.BllFacadeFactory;
import MicroMatch.Interface.BllInterface;

@SuppressWarnings("serial")
public class PollVoteServlet extends HttpServlet {
	private BllFacadeFactory bllFacadeFactory = BllFacadeFactory.getBllFactoryInstance() ;
	private BllInterface bllInterface = bllFacadeFactory.getInterface() ;
	/**
	 * Constructor of the object.
	 */
	public PollVoteServlet() {
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

		this.doPost(request, response);
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

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String userNum = request.getParameter("UserNum") ;
		String courseNum = request.getParameter("CourseNum") ;
		String matchNum = request.getParameter("MatchNum") ;
		String pollString = request.getParameter("Poll") ;
		float poll = Float.parseFloat( pollString ) ;
		Integer matchTemp = Integer.parseInt( request.getParameter( "MatchTemp" ) ) ;
		RegistrationEntity r = new RegistrationEntity() ;
		r.setCourseNum(courseNum) ;
		r.setMatchNum(matchNum) ;
		r.setMatchTemp(matchTemp) ;
		JSONObject json = new JSONObject() ;
		PollVoteEntity pv = new PollVoteEntity() ;
		pv.setPoll( poll ) ;
		pv.setCourseNum(courseNum) ;
		pv.setMatchNum(matchNum);
		pv.setStudentNum(userNum);
		pv.setMatchTemp(matchTemp);
		int check = -1 ;
		// json.result 0-投票成功 1-投票失败，result 2-已经投过票, 3-还未订阅 4-课程没有报名这个比赛
		if( bllInterface.IsRegister( r ) ){
				if ( !bllInterface.IsPollVote( pv ) ){
					pv.setVoteTime( new Timestamp( System.currentTimeMillis() ) ) ;
					if( bllInterface.AddPollVote( pv ) ) {
						bllInterface.AddPollNum( r ) ;
						check = 0 ;
					}else {
						check = 1 ;
					}
				} else {
					check = 2 ;
				}	
		} else {
			check = 4 ;
		}
		json.put( "result" , check ) ;
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
