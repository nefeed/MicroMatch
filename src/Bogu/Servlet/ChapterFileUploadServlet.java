package Bogu.Servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import Bogu.Entity.ChapterEntity;
import Bogu.Entity.CourseEntity;
import Bogu.Factory.BllFacadeFactory;
import Bogu.Interface.BllInterface;

@SuppressWarnings("serial")
public class ChapterFileUploadServlet extends HttpServlet {
	private BllFacadeFactory bllFacadeFactory = BllFacadeFactory.getbBllFactoryInstance() ;
	private BllInterface bllInterface = bllFacadeFactory.getInterface() ;
	private static final long serialVersionUID = 1L;  
    File tmpDir = null;//初始化上传文件的临时存放目录  
    File saveDir = null;//初始化上传文件后的保存目录 
    String tmpPath = "";  
    String savePath = ""; 
	// 获取的上传的文件的集合
	/**
	 * Constructor of the object.
	 */
	public ChapterFileUploadServlet() {
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
	@SuppressWarnings("unchecked")
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String CourseNum = request.getParameter( "CourseNum" ) ;
		System.out.println(CourseNum);
		CourseEntity course = bllInterface.QueryCourseByCourseNum( CourseNum ) ;
		ChapterEntity chapter = new ChapterEntity() ;
		chapter.setCourseNum( course.getCourseNum() );
		chapter.setChapterName( request.getParameter( "ChapterName" ) ) ;
		chapter.setChapterContent( request.getParameter( "ChapterContent" ) )  ;
		chapter.setPid(0) ;
		Timestamp timestamp = new Timestamp( System.currentTimeMillis() ) ;
		chapter.setCreateTime( timestamp ) ;
		JSONObject json = new JSONObject() ;
		
		try {
			if(ServletFileUpload.isMultipartContent(request)){  
		          DiskFileItemFactory dff = new DiskFileItemFactory();//创建该对象  
		          dff.setRepository(tmpDir);//指定上传文件的临时目录  
		          dff.setSizeThreshold(1024000);//指定在内存中缓存数据大小,单位为byte  
		          ServletFileUpload sfu = new ServletFileUpload(dff);//创建该对象  
		          sfu.setSizeMax(5000000);//指定单个上传文件的最大尺寸  
		          sfu.setSizeMax(10000000);//指定一次上传多个文件的总尺寸  
		          FileItemIterator fii = sfu.getItemIterator(request);//解析request 请求,并返回FileItemIterator集合  
		          while(fii.hasNext()){  
		            FileItemStream fis = fii.next();//从集合中获得一个文件流  
		            if(!fis.isFormField() && fis.getName().length()>0){//过滤掉表单中非文件域  
		                String fileName = fis.getName().substring(fis.getName().lastIndexOf("//"));//获得上传文件的文件名  
		                BufferedInputStream in = new BufferedInputStream(fis.openStream());//获得文件输入流  
		                savePath = savePath+"//"+CourseNum ;
		                saveDir = new File(savePath);  
		        	    if(!tmpDir.isDirectory()){
		        	        tmpDir.mkdir();
		        	    }
		                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(saveDir+fileName)));//获得文件输出流 
		                chapter.setChapterVideo(savePath+fileName);
		                Streams.copy(in, out, true);//开始把文件写到你指定的上传文件夹  
		            }  
		          }  
		          response.getWriter().println("File upload successfully!!!");//终于成功了,还不到你的上传文件中看看,你要的东西都到齐了吗  
		          bllInterface.UploadChapter( chapter ) ;
		          json.put( "result" , 0 ) ;
		        } else {
		        	json.put( "result" , 1 ) ;
		        	System.out.println( "没有获得文件" ) ;
		        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			json.put( "result" , 1 ) ;
		}
		pw.print(json) ;
		pw.close() ;
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
		super.init();  
	    tmpPath = "D://BoguSoft//BoguChapter//tmp";  
	    savePath = "D://BoguSoft//BoguChapter";  
	    tmpDir =new File(tmpPath);  
	    saveDir = new File(savePath);  
	    if(!tmpDir.isDirectory())  
	        tmpDir.mkdir();  
	    if(!saveDir.isDirectory())  
	        saveDir.mkdir();    
	}

}
