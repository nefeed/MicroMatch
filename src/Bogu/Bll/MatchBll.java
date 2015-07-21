package Bogu.Bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import Bogu.Dao.MatchDao;
import Bogu.Dao.UserDao;
import Bogu.Entity.MatchEntity;
import Bogu.Entity.UserEntity;
import Bogu.Factory.DaoFactory;
import Bogu.Tools.SampleDateFormat;

public class MatchBll extends BllAbstract {
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private MatchDao matchDao = daoFactory.getInstance( MatchDao.class ) ;
	private UserDao userDao = daoFactory.getInstance( UserDao.class ) ;
	/**
	* @Title:       insert
	* @Description: 发布比赛
	* @param:       @param match
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public JSONObject insert(MatchEntity match) {
		// TODO Auto-generated method stub
		return matchDao.insert( match ) ;
	}
	
	/**
	* @Title:       update
	* @Description: 修改比赛
	* @param:       @param match
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean update( MatchEntity match ) {
		return matchDao.update( match ) ;
	}
	
	/**
	* @Title:       QueryByMatchNum
	* @Description: 根据比赛Num查找比赛详情
	* @param:       @param matchNum
	* @param:       @return
	* @return:      MatchEntity
	* @throws
	*/ 
	public MatchEntity QueryByMatchNum( String matchNum ) {
		return matchDao.QueryByMatchNum( matchNum ) ;
	}
	
	/**
	* @Title:       QueryByUserNum
	* @Description: 根据创建者ID查找比赛
	* @param:       @param UserNum
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray QueryByUserNum( String userNum ) {
		List<MatchEntity> lists = matchDao.QueryByUserNum( userNum ) ;
		return putIntoJsonArray(lists) ;
	}

	/**
	* @Title:       ANewRegistrant
	* @Description: 新增报名参赛数量
	* @param:       @param matchNum
	* @return:      void
	* @throws
	*/ 
	public boolean ANewRegistrant(String matchNum) {
		// TODO Auto-generated method stub
		return matchDao.ANewRegistrant( matchNum ) ;
	}

	/**
	* @Title:       StopMatch
	* @Description: 停止比赛
	* @param:       @param matchNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean StopMatch(String matchNum) {
		// TODO Auto-generated method stub
		return matchDao.StopMatch( matchNum ) ;
	}

	/**
	* @Title:       QueryAllMatches
	* @Description: 遍历所有比赛
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray QueryAllMatches() {
		// TODO Auto-generated method stub
		List<MatchEntity> lists = matchDao.QueryAll() ;
		return putIntoJsonArray(lists) ;
	}

	/**
	* @Title:       check
	* @Description: 审核比赛
	* @param:       @param matchNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean check(String matchNum) {
		// TODO Auto-generated method stub
		return matchDao.check(matchNum) ;
	}
	
	/**
	* @Title:       QueryUnChecked
	* @Description: 遍历未审核的比赛
	* @param:       @param check
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	@SuppressWarnings("null")
	public JSONArray QueryUnChecked( Short check ) {
		List<MatchEntity> lists = matchDao.QueryUnChecked(check) ;
		return putIntoJsonArray( lists ) ;
	}

	/**
	* @Title:       QuerySimilar
	* @Description: 模糊搜索比赛
	* @param:       @param inputName
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	@SuppressWarnings("null")
	public JSONArray QuerySimilar(String inputName) {
		// TODO Auto-generated method stub
		List<MatchEntity> lists = matchDao.QuerySimilar(inputName) ;
		return putIntoJsonArray(lists) ;
	}

	/**
	* @Title:       queryNewestMatches
	* @Description: 按指定数量遍历最新的比赛
	* @param:       @param indexNum
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray queryNewestMatches( Integer indexNum ) {
		List<MatchEntity> lists = matchDao.QueryAll() ;
		List<MatchEntity> newlists = new ArrayList<MatchEntity>() ;
		for ( int i = 0 ; i < indexNum ; i ++ ) {
			newlists.add( lists.get(i) ) ;
		}
		return putIntoJsonArray(newlists) ;
	}
	
	/**
	* @Title:       queryByMatchTemp
	* @Description: 根据学科阶段查看比赛
	* @param:       @param matchTemp
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray queryByMatchTemp( Integer matchTemp ) {
		List<MatchEntity> lists = matchDao.queryByMatchTemp(matchTemp) ;
		return putIntoJsonArray( lists ) ;
	}
	
	/**
	* @Title:       putIntoJsonArray
	* @Description: 将获取的lists封装为jsonArray格式
	* @param:       @param lists
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray putIntoJsonArray( List<MatchEntity> lists ) {
		if( lists != null || lists.size() != 0 ){
			JSONArray jsonArray = new JSONArray() ;
			for( MatchEntity nm : lists ){
				Map<String, Object> map = new HashMap<String, Object>() ;
				UserEntity u = userDao.QueryByUserNum( nm.getUserNum() ) ;
				if( u != null ) {
					String NickName = u.getNickName() ;
					map.put( "UserNum" , nm.getUserNum() ) ;
					map.put( "NickName" , NickName ) ;									
				}
				map.put( "ID" , nm.getId() ) ;
				map.put( "MatchNum" , nm.getMatchNum() ) ;
				map.put( "MatchName" , nm.getMatchName() ) ;
				map.put( "MatchContent" , nm.getMatchContent() ) ;
				map.put( "StartTime" , nm.getMatchStartTime() ) ;
				map.put( "EndTime" , nm.getMatchEndTime() ) ;
				map.put( "PublishTime" , SampleDateFormat.toDateString(nm.getPublishTime()) ) ;
				map.put( "PID" , nm.getPid() ) ;
				map.put( "MatchPicture" , nm.getMatchPicture() ) ;
				map.put( "isStop" , nm.getIsStop() ) ;
				map.put( "isChecked", nm.getIsChecked() ) ;
				map.put( "RegistrationNum" , nm.getRegistrationNum() ) ;
				map.put( "MatchTemp", nm.getMatchTemp() ) ;
				jsonArray.add( map ) ;
			}
			return jsonArray ;	
		} else {
			return null ;
		}
	}

	/** 
	* 方法： isMine
	* 描述： 判断是否是本人创建的比赛
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年5月27日 下午11:43:49
	* 修改人：Gavin
	* 修改时间：2015年5月27日 下午11:43:49
	* 修改备注：
	* @throws 
	*/ 
	public boolean isMine(MatchEntity m) {
		// TODO Auto-generated method stub
		return matchDao.isMine(m);
	}
}
