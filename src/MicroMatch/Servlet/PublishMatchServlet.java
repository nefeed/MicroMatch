package MicroMatch.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import MicroMatch.Entity.MatchEntity;
import MicroMatch.Factory.BllFacadeFactory;
import MicroMatch.Interface.BllInterface;
import MicroMatch.Tools.ConvertCharSet;

@SuppressWarnings("serial")
public class PublishMatchServlet extends HttpServlet {
	private BllFacadeFactory bllFacadeFactory = BllFacadeFactory.getBllFactoryInstance() ;
	private BllInterface bllInterface = bllFacadeFactory.getInterface() ;
	/**
	 * Constructor of the object.
	 */
	public PublishMatchServlet() {
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
		
		MatchEntity match = new MatchEntity() ;
		String userNum = request.getParameter("UserNum") ;
		String matchName = ConvertCharSet.toUTF8( new String(request.getParameter("MatchName") ) ) ;
		String matchContent = ConvertCharSet.toUTF8( new String(request.getParameter("MatchContent") ) ) ;
		String matchStartTime = request.getParameter("StartTime") ;
		String matchEndTime = request.getParameter("EndTime") ;
		String matchPicture = ConvertCharSet.toUTF8( request.getParameter( "MatchPic" ) ) ;
		if( "".equals(matchPicture) || matchPicture == null ) {
			matchPicture = "img/bogu-default.png" ;
		}
		match.setPublishTime( new Timestamp( System.currentTimeMillis() ) ) ;
		match.setUserNum(userNum) ;
		match.setMatchName(matchName);
		match.setMatchContent(matchContent) ;
		match.setMatchStartTime(matchStartTime) ;
		match.setMatchEndTime(matchEndTime) ;
		match.setMatchPicture(matchPicture) ;
		match.setMatchTemp(0) ;
		short checked = 0 ;
		match.setIsChecked( checked ) ;
		match.setIsStop(0) ;
		match.setPid(0) ;
		match.setRegistrationNum(0) ;
		JSONObject json = bllInterface.PublishMatch( match ) ;
		out.print( json ) ;
		out.flush() ;
		out.close() ;
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
