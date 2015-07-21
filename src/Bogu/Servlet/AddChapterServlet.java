package Bogu.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bogu.Entity.CourseEntity;
import net.sf.json.JSONObject;
import Bogu.Entity.ChapterEntity;
import Bogu.Factory.BllFacadeFactory;
import Bogu.Interface.BllInterface;

public class AddChapterServlet extends HttpServlet {
	private BllFacadeFactory bllFacadeFactory = BllFacadeFactory.getbBllFactoryInstance() ;
	private BllInterface bllInterface = bllFacadeFactory.getInterface() ;
	/**
	 * Constructor of the object.
	 */
	public AddChapterServlet() {
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
		String userNum = request.getParameter( "UserNum" ) ;
		System.out.println( "获取的用户Num：" + userNum ) ;
		String courseNum = request.getParameter("CourseNum") ;
		CourseEntity c = new CourseEntity() ; 
		c.setUserNum( userNum ) ;
		c.setCourseNum( courseNum ) ;
		// isMyCourse = true ，证明新建章节的用户是课程的创建者，否则不是课程的创建者
		JSONObject json = new JSONObject() ;
		if ( !bllInterface.isMyCourse( c ) ) {
			// json result = 2 ,非课程创建者上传的章节，不予保存
			json.put( "result" , 2 ) ;
		} else {
			ChapterEntity chapter = new ChapterEntity() ;
			chapter.setChapterName( request.getParameter("ChapterName") ) ;
			chapter.setCourseNum( courseNum ) ;
			chapter.setChapterContent( request.getParameter("ChapterContent") ) ;
			chapter.setListId( Integer.parseInt( request.getParameter("ListID").toString() ) ) ;
			String fileAddress = request.getParameter( "VideoAddress" ) ;
			chapter.setChapterVideo( fileAddress ) ;
			chapter.setChapterVideoName( request.getParameter( "VideoName" ) ) ;
			String videoPath = request.getSession().getServletContext().getRealPath("/") + fileAddress ;
			String picPath = request.getSession().getServletContext().getRealPath("/") + "chapterPic/" ;
			String ffmpegPath = request.getSession().getServletContext().getRealPath("/") + "ffmpeg/bin/ffmpeg.exe" ;
			File file = null;
			String picName = "" ;
			do {  
                //生成文件名：  
				picName = UUID.randomUUID().toString();  
                file = new File( picPath + picName + ".jpg" ) ;  
            } while ( file.exists() ) ;
			picPath = picPath + picName + ".jpg" ;
			String coverPath = "chapterPic/" + picName + ".jpg" ;
			List<String> commend = new ArrayList<String>() ;  
            commend.add(ffmpegPath);  
            commend.add("-i");  
            commend.add(videoPath);  
            commend.add("-y");  
            commend.add("-f");  
            commend.add("image2");  
            commend.add("-ss");  
            commend.add("8");  
            commend.add("-t");  
            commend.add("0.001");  
            commend.add("-s");
            commend.add("350*240");
            commend.add(picPath);
            try {      	  
                ProcessBuilder builder = new ProcessBuilder();      
                builder.command(commend);          
                builder.redirectErrorStream(true);         
                System.out.println("视频截图开始...");           
                // builder.start();           
                Process process = builder.start();           
                InputStream in =process.getInputStream();  
                byte[] re = new byte[1024];  
                System.out.print("正在进行截图，请稍候");  
                while (in.read(re) != -1) {  
                	System.out.print(".");          
                }          
                System.out.println("");         
                in.close();
                chapter.setCoverPicture(coverPath) ;
                System.out.println("视频截图完成...");          
            } catch (Exception e) {         
                e.printStackTrace();          
                System.out.println("视频截图失败！");  
            }
			chapter.setCreateTime( new Timestamp( System.currentTimeMillis() ) ) ;
			chapter.setPid( 0 ) ;
			chapter.setCourseNum( courseNum ) ;
			Short isComment = 1 ;
			chapter.setIsComment( isComment ) ;
			short check = 0 ;
			chapter.setIsChecked( check ) ;
			// json result = 0 ,新建章节成功，result = 1 ，新建章节失败
			json = bllInterface.AddChapter( chapter ) ;			
		}
		System.out.println( "result：0-创建章节成功，1-创建章节失败，2-非本人创建的课程，不允许上传视频");
		System.out.println(json);
		out.print( json ) ;
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
