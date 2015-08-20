package MicroMatch.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.print.attribute.standard.MediaSize.Other;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import MicroMatch.Entity.CourseEntity;
import MicroMatch.Factory.BllFacadeFactory;
import MicroMatch.Interface.BllInterface;
import MicroMatch.Tools.ConvertCharSet;

public class CreateCourseServlet extends HttpServlet {
	private BllFacadeFactory bllFacadeFactory = BllFacadeFactory.getBllFactoryInstance() ;
	private BllInterface bllInterface = bllFacadeFactory.getInterface() ;
	/**
	 * Constructor of the object.
	 */
	public CreateCourseServlet() {
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

		this.doPost( request , response ) ;
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
		
		CourseEntity course = new CourseEntity() ;
		
		String userNum = new String ( request.getParameter("UserNum") ) ;
		String coverPicture = ConvertCharSet.toUTF8( request.getParameter( "CoverPicture" ) ) ;
		if( "".equals(coverPicture) || coverPicture == null ) {
			coverPicture = "img/bogu-default.png" ;
		}
		course.setCoverPicture(coverPicture);
		course.setCourseName( ConvertCharSet.toUTF8( new String( request.getParameter("CourseName") ) ) )  ;
		course.setCourseContent( ConvertCharSet.toUTF8( new String( request.getParameter("CourseContent") ) ) )   ;
		course.setUserNum( userNum ) ;
		course.setSubId( Integer.parseInt( new String(request.getParameter("SubId") ) ) )  ;
		Timestamp timestamp = new Timestamp( System.currentTimeMillis() ) ;
		course.setCreatetime(timestamp) ;
		Short check = 0 ;
		course.setIsChecked( check ) ;
		course.setIsExam(0);
		check = 1 ;
		course.setIsComment( check ) ;
		JSONObject json = bllInterface.CreateCourse( course ) ;
		out.print(json) ;
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
