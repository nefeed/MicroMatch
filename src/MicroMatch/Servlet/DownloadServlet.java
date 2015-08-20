package MicroMatch.Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import MicroMatch.Entity.AccessoryEntity;
import MicroMatch.Entity.DownloadRecorderEntity;
import MicroMatch.Factory.BllFacadeFactory;
import MicroMatch.Interface.BllInterface;

@SuppressWarnings("serial")
public class DownloadServlet extends HttpServlet {
	private BllFacadeFactory bllFacadeFactory = BllFacadeFactory.getBllFactoryInstance() ;
	private BllInterface bllInterface = bllFacadeFactory.getInterface() ;


	/**
	 * Constructor of the object.
	 */
	public DownloadServlet() {
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

		
		String userNum = request.getParameter( "UserNum" );
		int aid = Integer.parseInt( request.getParameter("ai") ) ;
		AccessoryEntity accessory = new AccessoryEntity() ;
 		accessory.setId( aid ) ;
 		DownloadRecorderEntity downloadRecorder = new DownloadRecorderEntity() ;
 		downloadRecorder.setUserNum( userNum ) ;
 		downloadRecorder.setAccessoryId( aid ) ;
 		downloadRecorder.setDownloadTime( new Timestamp( System.currentTimeMillis() ) ) ;
		String savePath = new String() ;

		// 要下载的附件的文件名
		String fileName = request.getParameter( "AccessoryName" ) ;
		String fileAddress = request.getParameter( "AccessoryAddress" ) ;
		// 得到要下载的文件
		String path = request.getSession().getServletContext().getRealPath("/") + fileAddress ;
		File file = new File( path ) ;
		// 如果文件不存在
		if ( !file.exists() ) {
			request.setAttribute( "message", "您要下载的文件资源因为法律原因已经被删除！") ;
			request.getRequestDispatcher("/message.jsp").forward(request, response) ;
			return ;
		}
		
		// 设置响应头
		response.setHeader( "content-disposition", "attachment;filename="
				+ URLEncoder.encode( fileName, "UTF-8" ) ) ;
		// 读取要下载的文件，保存到文件输入流
		FileInputStream in = new FileInputStream( path );
		// 创建输出流
		OutputStream out = response.getOutputStream() ;
		// 创建缓冲区
		byte buffer[] = new byte[1024];
		int len = 0;
		// 循环将输入流中的内容读取到缓冲区当中
		while((len=in.read(buffer))>0){
			// 输出缓冲区的内容到浏览器，实现文件下载
			out.write(buffer, 0, len);
		}
		JSONObject json = bllInterface.CreateDownloadRecorder( downloadRecorder ) ;
		// result：0：创建下载记录成功，1：已经存在下载记录，所以不增加下载数
		if( json.getInt("result") == 0 ){
			bllInterface.AddAccessoryDownloadNum( accessory ) ;			
		}
		// 关闭文件输入流
		in.close();
		// 关闭输出流
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
