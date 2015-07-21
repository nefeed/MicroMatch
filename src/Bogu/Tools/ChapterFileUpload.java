package Bogu.Tools;

import java.io.File;

/**
* 项目名称：BoguCloudCourse
* 类名称：FileUpload
* 类描述：
* 创建人：章华隽
* 创建时间：2015年4月16日 上午9:54:08
* 修改人：Administrator
* 修改时间：2015年4月16日 上午9:54:08
* 修改备注：
*
*/
public class ChapterFileUpload {
	
	private static final String CHAPTER_PATH = "D://BoguSoft//BoguChapter" ;
	private static final long SERIALVERSIONUID = 1L ;
	// 上传的文件
	private File uploadFile ;
	// 上传的文件的文件名
	private String uploadFileName ;
	// 上传的文件的文件类型
	private String uploadFileType ;
	
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
		String[] signs = uploadFile.getName().split("\\.") ;
		this.uploadFileName = signs[0] ;
		this.uploadFileType = signs[1] ;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadFileType() {
		return uploadFileType;
	}
	public void setUploadFileType(String uploadFileType) {
		this.uploadFileType = uploadFileType;
	}
	public static long getSerialversionuid() {
		return SERIALVERSIONUID;
	}
	public static String getChapterPath() {
		return CHAPTER_PATH;
	}
	
	
	
}
