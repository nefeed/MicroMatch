package Bogu.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Bogu.Entity.CourseEntity;
import Bogu.Factory.BllFacadeFactory;
import Bogu.Interface.BllInterface;

public class UpdateCourseServlet extends HttpServlet {
	private BllFacadeFactory bllFacadeFactory = BllFacadeFactory.getbBllFactoryInstance() ;
	private BllInterface bllInterface = bllFacadeFactory.getInterface() ;
	/**
	 * Constructor of the object.
	 */
	public UpdateCourseServlet() {
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
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		CourseEntity course = new CourseEntity() ;
		String courseNum = request.getParameter("CourseNum") ;
		String courseName = request.getParameter("CourseName") ;
		String courseContent = request.getParameter("CourseContent") ;
		int subId = Integer.parseInt( new String( request.getParameter( "SubId" ) ) ) ;
		String userNum = request.getParameter( "UserNum" ) ;
		String coverPicture = request.getParameter( "CoverPicture" ) ;
		course.setUserNum(userNum) ;
		course.setCourseNum(courseNum) ;
		course.setCourseName(courseName) ;
		course.setCourseContent(courseContent) ;
		course.setCoverPicture(coverPicture) ;
		course.setSubId(subId) ;
		JSONObject json = new JSONObject() ;
		// 修改课程结果，result-0:修改成功 1:修改失败 2:不是本人创建的课程
		if( bllInterface.isMyCourse(course) ) {
			boolean check = bllInterface.UpdateCourse( course ) ;			
			System.out.println( "修改课程的结果为：" + check ) ;
			if( check ) {
				json.put( "result" , 0 ) ;
			} else {
				json.put( "result" , 1 ) ;
			}
		} else {
			json.put( "result" , 2 ) ;
		}
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
