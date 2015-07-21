package Bogu.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Bogu.Entity.CourseEntity;
import Bogu.Factory.BllFacadeFactory;
import Bogu.Interface.BllInterface;

public class CreateCourseServlet extends HttpServlet {
	private BllFacadeFactory bllFacadeFactory = BllFacadeFactory.getbBllFactoryInstance() ;
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
		String coverPicture = request.getParameter( "CoverPicture" ) ;
		if( "".equals(coverPicture) || coverPicture == null ) {
			coverPicture = "img/bogu-default.png" ;
		}
		course.setCoverPicture(coverPicture);
		course.setCourseName( new String( request.getParameter("CourseName") ) )  ;
		course.setCourseContent( new String( request.getParameter("CourseContent") ) )   ;
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
		System.out.println( "result结果表是：\n 0：创建通过，1：创建失败\n"+json ) ;
		out.print(json) ;
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
