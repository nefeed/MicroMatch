package MicroMatch.Bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import MicroMatch.Dao.AwardDao;
import MicroMatch.Dao.CourseDao;
import MicroMatch.Dao.MatchDao;
import MicroMatch.Dao.UserDao;
import MicroMatch.Entity.AwardEntity;
import MicroMatch.Entity.CourseEntity;
import MicroMatch.Entity.MatchEntity;
import MicroMatch.Entity.UserEntity;
import MicroMatch.Factory.DaoFactory;

public class AwardBll extends BllAbstract {
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private AwardDao awardDao = daoFactory.getInstance( AwardDao.class ) ;
	private CourseDao courseDao = daoFactory.getInstance( CourseDao.class ) ;
	private UserDao userDao = daoFactory.getInstance( UserDao.class ) ;
	private MatchDao matchDao = daoFactory.getInstance( MatchDao.class ) ;
	/**
	* @Title:       insert
	* @Description: 新建奖项
	* @param:       @param award
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean insert(AwardEntity award) {
		// TODO Auto-generated method stub
		return awardDao.insert( award ) ;
	}

	/**
	* @Title:       GivenAward
	* @Description: 颁发奖项
	* @param:       @param award
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean GivenAward(AwardEntity award) {
		// TODO Auto-generated method stub
		return awardDao.GivenAward( award ) ;
	}

	/** 
	* 方法： QueryMatchAward
	* 描述： 查看奖项
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年4月26日 上午10:40:13
	* 修改人：华隽
	* 修改时间：2015年4月26日 上午10:40:13
	* 修改备注：
	* @throws 
	*/ 
	public JSONArray QueryMatchAward(String matchNum) {
		// TODO Auto-generated method stub
		List<AwardEntity> lists = awardDao.QueryBymatchNum( matchNum ) ;
		if( lists == null || lists.size() == 0 ){
			return null;			
		} else {
			JSONArray jsonArray = new JSONArray() ;
			for( AwardEntity a : lists ) {
				CourseEntity c = courseDao.QueryByCourseNum( a.getCourseNum() ) ;
				Map<String , Object> map = new HashMap<String, Object>() ;
				map.put( "ID", a.getId() ) ;
				map.put( "AwardName" , a.getAwardName() ) ;
				map.put( "Remark" , a.getRemark() ) ;
				if ( c != null ) {
					map.put( "CourseNum" , a.getCourseNum() ) ;
					map.put( "CourseName" , c.getCourseName() ) ;
					UserEntity u = userDao.QueryByUserNum( c.getUserNum() ) ;
					if ( u != null ) {
						map.put( "UserNum" , c.getUserNum() ) ;
						map.put( "NickName" , u.getNickName() ) ;
						map.put( "RealName", u.getRealName() ) ;
					}
				}
				map.put( "AwardTime" , a.getAwardTime() ) ;
				jsonArray.add(map) ;
			}
			return jsonArray ;
		}
	}
	
	/** 
	* 方法： queryById
	* 描述： 根据id返回award
	* 返回： JSONObject
	* 创建人：章华隽
	* 创建时间：2015年5月31日 上午11:38:15
	* 修改人：Gavin
	* 修改时间：2015年5月31日 上午11:38:15
	* 修改备注：
	* @throws 
	*/ 
	public JSONObject queryById( int id ) {
		JSONObject json = new JSONObject() ;
		AwardEntity a = awardDao.queryById(id) ;
		if( a == null ) {
			return null ;
		} else {
			CourseEntity c = courseDao.QueryByCourseNum( a.getCourseNum() ) ;
			json.put( "ID", a.getId() ) ;
			json.put( "AwardName" , a.getAwardName() ) ;
			json.put( "Remark" , a.getRemark() ) ;
			if ( c != null ) {
				json.put( "CourseNum" , a.getCourseNum() ) ;
				json.put( "CourseName" , c.getCourseName() ) ;
				UserEntity u = userDao.QueryByUserNum( c.getUserNum() ) ;
				if ( u != null ) {
					json.put( "UserNum" , c.getUserNum() ) ;
					json.put( "NickName" , u.getNickName() ) ;
					json.put( "RealName", u.getRealName() ) ;
				}
			}
			json.put( "AwardTime" , a.getAwardTime() ) ;
			return json ;
		}
	}

	/**
	* @Title:       queryHistoryAward
	* @Description: 查看课程的历史获奖记录
	* @param:       @param courseNum
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray queryHistoryAward(String courseNum) {
		// TODO Auto-generated method stub
		List<AwardEntity> lists = awardDao.queryHistoryAward( courseNum ) ;
		if ( lists == null ) {
			return null ;
		} else {
			JSONArray json = new JSONArray() ;
			for( AwardEntity a : lists ) {
				Map<String , Object> map = new HashMap<String, Object>() ;
				map.put( "ID", a.getId() ) ;
				map.put( "AwardName" , a.getAwardName() ) ;
				map.put( "Remark" , a.getRemark() ) ;
				map.put( "MatchNum" , a.getMatchNum() ) ;
				MatchEntity m = matchDao.QueryByMatchNum( a.getMatchNum() ) ;
				map.put( "MatchName", m.getMatchName() ) ;
				map.put( "StartTime", m.getMatchStartTime() ) ;
				map.put( "EndTime", m.getMatchEndTime() ) ;
				map.put( "AwardTime" , a.getAwardTime() ) ;
				json.add(map) ;
			}
			return json ;
		}
	}
}
