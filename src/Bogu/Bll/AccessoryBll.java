package Bogu.Bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import Bogu.Dao.AccessoryDao;
import Bogu.Entity.AccessoryEntity;
import Bogu.Factory.DaoFactory;
import Bogu.Tools.SampleDateFormat;

public class AccessoryBll extends BllAbstract {
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private AccessoryDao accessoryDao = daoFactory.getInstance( AccessoryDao.class ) ;
	
	/** 
	* 方法： insert
	* 描述： 上传附件
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:52:11
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:52:11
	* 修改备注：
	* @throws 
	*/ 
	public boolean insert( AccessoryEntity accessory ) {
		return accessoryDao.insert( accessory ) ;
	}
	
	/** 
	* 方法： delete
	* 描述： 删除附件
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:54:38
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:54:38
	* 修改备注：
	* @throws 
	*/ 
	public boolean delete( int accessoryId ) {
		return accessoryDao.delete( accessoryId ) ;
	}
	
	/** 
	* 方法： QueryByObjectTypeAndId
	* 描述： 通过对象类型和Id查找下属附件
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:56:03
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:56:03
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("null")
	public JSONArray QueryByObjectTypeAndNum( AccessoryEntity accessory ) {
		List<AccessoryEntity> lists = accessoryDao.QueryByObjectTypeAndNum( accessory ) ;
		if( lists == null || lists.size() == 0 ){
			return null ;
		}else {
			JSONArray jsonArray = new JSONArray() ;
			for ( AccessoryEntity acc:lists ) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put( "AccessoryID", acc.getId() ) ;
				map.put( "AccessoryName" , acc.getAccessoryName() ) ;
				map.put( "AccessoryAddress" , acc.getAccessoryAddress() ) ;
				map.put( "CreateTime" , SampleDateFormat.toDIYDateString( acc.getCreateTime() ) ) ;
				map.put( "DownloadNum" , acc.getDownloadNum() ) ;
				jsonArray.add( map ) ;
			}
			return jsonArray ;
		}
	}

	/** 
	* 方法： AddAccessoryDownloadNum
	* 描述： 使附件的下载记录增加1
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午4:56:26
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午4:56:26
	* 修改备注：
	* @throws 
	*/ 
	public boolean AddAccessoryDownloadNum(AccessoryEntity accessory) {
		// TODO Auto-generated method stub
		return accessoryDao.AddDownloadNum( accessory ) ;
	}

}
