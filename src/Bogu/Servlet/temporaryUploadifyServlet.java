package Bogu.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Bogu.Factory.BllFacadeFactory;
import Bogu.Interface.BllInterface;

public class temporaryUploadifyServlet extends HttpServlet {
	private BllFacadeFactory bllFacadeFactory = BllFacadeFactory.getbBllFactoryInstance() ;
	private BllInterface bllInterface = bllFacadeFactory.getInterface() ;
	/**
	 * Constructor of the object.
	 */
	public temporaryUploadifyServlet() {
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

		// 解决服务器端消息返回客户端后中文字符乱码  
	    response.setHeader("Content-Type", "text/html;charset=UTF-8");  
	    HttpSession session = request.getSession();  
	    PrintWriter out = response.getWriter();  
	  
	    System.out.println("-------------------QueryString::::" + request.getQueryString());
		  if(request.getParameter("folder")==null||request.getParameter("folder")==""){
		   System.out.println("-------------------request.getParameter('folder')::::" + request.getParameter("folder") + " then return");
		   return;
		  }
		  response.setContentType("text/html");
		  response.setCharacterEncoding("UTF-8");
		  String path = this.getServletContext().getRealPath("/");
		  String fileD = request.getParameter("folder");
		  String sourcePath = path + "temporary";
		  path = path + fileD + "/";
		  System.out.println("-------------------UpLoadify-path::::" + path);
		  File folder = new File(path);
		  File sourceFolder = new File(sourcePath);
		  if (!folder.exists()) {
		   //文件夹不存在则创建
		   System.out.println("-------------------UpLoadify::::" + "创建文件夹" + fileD);
		   folder.mkdirs();
		  }
		  if (!sourceFolder.exists()) {
		   //文件夹不存在则创建
		   System.out.println("-------------------UpLoadify::::" + "创建文件夹source");
		   sourceFolder.mkdirs();
		  }
		  ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		  sfu.setHeaderEncoding("UFT-8");
		  try {
		   List<?> fileList = sfu.parseRequest(request);
		   String sourceName = "";
		   String extName = "";
		   String name = "";
		   String sfileName = "";
		   for (int i = 0; i < fileList.size(); i++) {
		    System.out.println("-------------------UpLoadify fileList[" + i + "]::::" + fileList.get(i));
		    FileItem fi = (FileItem) fileList.get(i);
		    if (!fi.isFormField()) {
		     sourceName = fi.getName();
		     System.out.println("-------------------UpLoadify name::::" + sourceName);
		     if (sourceName == null || "".equals(sourceName.trim())) {
		      continue;
		     }
		     if (sourceName.lastIndexOf(".") >= 0) {
		      // 扩展名
		      name = sourceName.substring(0,sourceName.lastIndexOf("."));
		      extName = sourceName.substring(sourceName.lastIndexOf("."));
		      System.out.println("-------------------UpLoadify extName::::" + extName);
		     }
		     // 文件名规则 前缀 + 时间 + 两位随机数 + 文件分类(标识图片尺寸) + 扩展名
		     Calendar ca = Calendar.getInstance();
		     DecimalFormat df = new DecimalFormat();
		     df.setMinimumIntegerDigits(2);
		     String dateTime = ca.get(Calendar.YEAR) + ""
		       + df.format(ca.get(Calendar.MONTH)) + ""
		       + df.format(ca.get(Calendar.DATE)) + ""
		       + df.format(ca.get(Calendar.HOUR)) + ""
		       + df.format(ca.get(Calendar.MINUTE)) + ""
		       + df.format(ca.get(Calendar.SECOND));
		     Random rand = new Random();
		     int rand_num = rand.nextInt(89)+10;
		     String fileName = rand_num + extName;
		     sfileName = name + "_" + dateTime + "_" + rand_num + extName;
		     File saveSourceFile = new File(sourcePath + sfileName);
		     File saveFile = new File(path + fileName);
		     fi.write(saveSourceFile);
		     fi.write(saveFile);
		     System.out.println("-------------------UpLoadify fileSourceName::::" + sourceName);
		     System.out.println("-------------------UpLoadify fileName::::" + fileName);
		     String temporaryAddress = fileD+"/"+fileName ;
		     String temporaryName = sourceName ;
		     String _result = "";  
		     _result += temporaryName + "," + temporaryAddress ;  
		     out.print(_result);  
		    }
		   }
		   response.getWriter().println(sfileName);
		  } catch (FileUploadException e) {
		   response.getWriter().println("0");
		   e.printStackTrace();
		  } catch (Exception e) {
		   response.getWriter().println("0");
		   e.printStackTrace();
		  } 
	  
		out.flush();
		out.close();
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

		this.doGet(request, response);
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
