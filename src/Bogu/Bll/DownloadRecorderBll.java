package Bogu.Bll;

import net.sf.json.JSONObject;
import Bogu.Dao.DownloadRecorderDao;
import Bogu.Entity.DownloadRecorderEntity;
import Bogu.Factory.DaoFactory;

public class DownloadRecorderBll extends BllAbstract {
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private DownloadRecorderDao downloadRecorderDao = daoFactory.getInstance( DownloadRecorderDao.class ) ;
	
	
	/** 
	* 方法： insert
	* 描述： 创建文件下载记录
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午4:54:32
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午4:54:32
	* 修改备注：
	* @throws 
	*/ 
	public JSONObject insert(DownloadRecorderEntity downloadRecorder) {
		// TODO Auto-generated method stub
		return downloadRecorderDao.insert( downloadRecorder ) ;
	}
}
