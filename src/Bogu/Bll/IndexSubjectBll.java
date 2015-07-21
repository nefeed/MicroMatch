package Bogu.Bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import Bogu.Dao.IndexSubjectDao;
import Bogu.Dao.SubjectDao;
import Bogu.Entity.IndexSubjectEntity;
import Bogu.Entity.SubjectEntity;
import Bogu.Factory.DaoFactory;

public class IndexSubjectBll extends BllAbstract {
	
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private IndexSubjectDao indexSubjectDao = daoFactory.getInstance( IndexSubjectDao.class ) ;
	private SubjectDao subjectDao = daoFactory.getInstance( SubjectDao.class ) ;
	
	
	/**
	* @Title:       showIndex
	* @Description: 显示首页学科
	* @param:       @param indexNum
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray showIndex( int indexNum ) {
		List<IndexSubjectEntity> lists = indexSubjectDao.showIndex() ;
		JSONArray json = new JSONArray() ;
		if( lists == null || lists.size() == 0 ) {
			return null ;
		} else {
			for ( int i = 0 ; i < indexNum ; i ++ ) {
				IndexSubjectEntity is = lists.get(i) ;
				Map<String, Object> map = new HashMap<String, Object>() ;
				map.put( "ID" , is.getId() ) ;
				int sid = is.getSid() ;
				SubjectEntity s = subjectDao.QuerySubById( sid ) ;
				map.put( "SID" , s.getId() ) ;
				map.put( "SubjectName" , s.getSubjectName() ) ;
				map.put( "Remark" , s.getRemark() ) ;
				map.put( "PID" , s.getPid() ) ;
				json.add( map ) ;
			}
			return json ;
		}
	}
	
	/**
	* @Title:       update
	* @Description: 修改首页学科表
	* @param:       @param is
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean update( IndexSubjectEntity is ) {
		return indexSubjectDao.update( is ) ;
	}
}
