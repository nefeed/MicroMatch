<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
 
<%

    File uploadPath = new File( this.getServletContext().getRealPath("/") + "coursePic/");//上传文件目录
    
    if (!uploadPath.exists()) {
       uploadPath.mkdirs();
    }
    // 临时文件目录
    File tempPathFile = new File( this.getServletContext().getRealPath("/") + "temporary/");
    if (!tempPathFile.exists()) {
       tempPathFile.mkdirs();
    }
    try {
       // Create a factory for disk-based file items
       DiskFileItemFactory factory = new DiskFileItemFactory();
 
       // Set factory constraints
       factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
       factory.setRepository(tempPathFile);//设置缓冲区目录
 
       // Create a new file upload handler
       ServletFileUpload upload = new ServletFileUpload(factory);
 
       // Set overall request size constraint
       upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
 
       List<FileItem> items = upload.parseRequest(request);//得到所有的文件
       Iterator<FileItem> i = items.iterator();
       String alFileName = "" ;
       while (i.hasNext()) {
           FileItem fi = i.next();
           String fileName = fi.getName();
           if (fileName != null) {
		       File fullFile = new File(fi.getName());
		       long currentTime=System.currentTimeMillis() ;
 			   String newFileName = Long.toString(currentTime) + ".jpg";
		       File savedFile = new File(uploadPath, newFileName);
			   alFileName = "coursePic/" + newFileName ;
		       fi.write(savedFile);
           }
       }
       out.print( alFileName ) ;
    } catch (Exception e) {
       e.printStackTrace();
    }
%>
