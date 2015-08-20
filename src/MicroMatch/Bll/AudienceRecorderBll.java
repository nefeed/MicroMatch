package MicroMatch.Bll;

import net.sf.json.JSONObject;
import MicroMatch.Dao.AudienceRecorderDao;
import MicroMatch.Entity.AudienceRecorderEntity;
import MicroMatch.Factory.DaoFactory;

public class AudienceRecorderBll extends BllAbstract {
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private AudienceRecorderDao audienceRecorderDao = daoFactory.getInstance( AudienceRecorderDao.class ) ;
	
	
	/** 
	* 方法： insert
	* 描述： 新增观看记录
	* 返回： JSONObject
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午5:29:27
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午5:29:27
	* 修改备注：
	* @throws 
	*/ 
	public JSONObject insert(AudienceRecorderEntity ar) {
		// TODO Auto-generated method stub
		return audienceRecorderDao.insert( ar ) ;
	}
}
