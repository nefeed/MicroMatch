package MicroMatch.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import MicroMatch.Entity.MatchEntity;
import MicroMatch.Entity.RegistrationEntity;
import MicroMatch.Factory.BllFacadeFactory;
import MicroMatch.Interface.BllInterface;

public class QueryAlreadyRegisterMatchesServlet extends HttpServlet {
	private BllFacadeFactory bllFacadeFactory = BllFacadeFactory.getBllFactoryInstance() ;
	private BllInterface bllInterface = bllFacadeFactory.getInterface() ;
	/**
	 * Constructor of the object.
	 */
	public QueryAlreadyRegisterMatchesServlet() {
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

		response.setCharacterEncoding("utf-8") ;
		response.setContentType("text/html;charset=utf-8") ;
		PrintWriter out = response.getWriter() ;
		
		String courseNum = request.getParameter( "CourseNum" ) ;
		System.out.println( "\n本次查询得到的课程Num："+ courseNum + "\n" ) ;
		List<RegistrationEntity> lists = bllInterface.QueryAlreadyRegisterMatches( courseNum ) ;
		JSONArray jsonArray = new JSONArray() ;
		if (lists == null || lists.size() == 0 ){
			jsonArray = null ;
		}else {
			for( RegistrationEntity r : lists ) {
				Map<String, Object> map = new HashMap<String, Object>() ;
				MatchEntity m = bllInterface.QueryMatchByMatchNum( r.getMatchNum() ) ;
				if ( m != null ){
					map.put( "MatchName" , m.getMatchName() ) ;
					map.put( "MatchNum" , m.getMatchNum() ) ;
					map.put( "MatchContent" , m.getMatchContent() ) ;
					map.put( "StartTime" , m.getMatchStartTime() ) ;
					map.put( "EndTime" , m.getMatchEndTime() ) ;
					map.put( "RegistrationNum" , m.getRegistrationNum() ) ;
				} else {
					map.put( "result" , 1 ) ;
				}
				jsonArray.add( map ) ;
			}
		} 
		System.out.println( jsonArray ) ;
		out.print( jsonArray ) ;
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
