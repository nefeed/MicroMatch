package MicroMatch.Bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import MicroMatch.Dao.SubjectDao;
import MicroMatch.Entity.SubjectEntity;
import MicroMatch.Factory.DaoFactory;

public class SubjectBll extends BllAbstract {
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private SubjectDao subjectDao = daoFactory.getInstance( SubjectDao.class ) ;
	
	/** 
	* 方法： insert
	* 描述： 增加学科
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月20日 上午9:47:32
	* 修改人：Administrator
	* 修改时间：2015年4月20日 上午9:47:32
	* 修改备注：
	* @throws 
	*/ 
	public boolean insert( SubjectEntity sub ) {
		return subjectDao.insert( sub ) ;
	}
	
	/** 
	* 方法： update
	* 描述： 修改学科
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月20日 上午9:49:35
	* 修改人：Administrator
	* 修改时间：2015年4月20日 上午9:49:35
	* 修改备注：
	* @throws 
	*/ 
	public boolean update( SubjectEntity sub ) {
		return subjectDao.update( sub ) ;
	}
	/** 
	* 方法： QuerySubjectById
	* 描述： 通过Id查找Sub
	* 返回： SubjectEntity
	* 创建人：章华隽
	* 创建时间：2015年4月20日 上午9:48:35
	* 修改人：Administrator
	* 修改时间：2015年4月20日 上午9:48:35
	* 修改备注：
	* @throws 
	*/ 
	public SubjectEntity QuerySubjectById(Integer subId) {
		// TODO Auto-generated method stub
		return subjectDao.QuerySubById( subId ) ;
	}

	/**
	* @Title:       showAll
	* @Description: 展示所有学科分类
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray showAll() {
		// TODO Auto-generated method stub
		List<SubjectEntity> lists = subjectDao.showAll( ) ;
		if( lists == null || lists.size() == 0 ) {
			return null ;
		} else {
			return putIntoJsonArray(lists) ;
		}
	}

	/**
	* @Title:       QueryByName
	* @Description: 通过SubName查找课程
	* @param:       @param subName
	* @param:       @return
	* @return:      SubjectEntity
	* @throws
	*/ 
	public SubjectEntity QueryByName(String subName) {
		// TODO Auto-generated method stub
		return subjectDao.QuerySubByName( subName ) ;
	}
	
	/**
	* @Title:       showChild
	* @Description: 显示子学科分类
	* @param:       @param subId
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray showChild(int subId) {
		// TODO Auto-generated method stub
		List<SubjectEntity> lists = subjectDao.showChild( subId ) ;
		if( lists == null || lists.size() == 0  ) {
			return null ;
		} else {
			return putIntoJsonArray(lists) ;
		}
	}
	
	/**
	* @Title:       putIntoJsonArray
	* @Description: 将队列打包成jsonArray
	* @param:       @param lists
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray putIntoJsonArray( List<SubjectEntity> lists ) {
		JSONArray json = new JSONArray() ;
		for ( SubjectEntity s : lists ) {
			Map<String, Object> map = new HashMap<String, Object>() ;
			map.put( "ID", s.getId() ) ;
			map.put( "SubjectName", s.getSubjectName() ) ;
			map.put( "Remark", s.getRemark() ) ;
			map.put( "PID" , s.getPid() ) ;
			
			json.add( map ) ;
		}
		return json ;
	}
}
