package Bogu.Bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import Bogu.Dao.CourseDao;
import Bogu.Dao.RegistrationDao;
import Bogu.Dao.SubjectDao;
import Bogu.Dao.UserDao;
import Bogu.Entity.CourseEntity;
import Bogu.Entity.MatchEntity;
import Bogu.Entity.RegistrationEntity;
import Bogu.Entity.SubjectEntity;
import Bogu.Entity.UserEntity;
import Bogu.Factory.DaoFactory;

public class RegistrationBll extends BllAbstract {
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private RegistrationDao registrationDao = daoFactory.getInstance( RegistrationDao.class ) ;
	private CourseDao courseDao = daoFactory.getInstance( CourseDao.class ) ;
	private UserDao userDao = daoFactory.getInstance( UserDao.class ) ;
	private SubjectDao subjectDao = daoFactory.getInstance( SubjectDao.class ) ;
	
	/**
	* @Title:       RegisterMatch
	* @Description: 增加比赛报名记录
	* @param:       @param registration
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean RegisterMatch(RegistrationEntity registration) {
		// TODO Auto-generated method stub
		return registrationDao.insert(registration) ;
	}

	/**
	* @Title:       AddPollNum
	* @Description: 投票数+1
	* @param:       @param registration
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean AddPollNum(RegistrationEntity registration) {
		// TODO Auto-generated method stub
		return registrationDao.AddPollNum( registration ) ;
	}

	/**
	* @Title:       CountPollCourseNum
	* @Description: 结束比赛开始统计得票，找出参赛课程
	* @param:       @param matchNum
	* @param:       @return
	* @return:      List<RegistrationEntity>
	* @throws
	*/ 
	public JSONArray QueryCourseByMatchNum(MatchEntity m) {
		// TODO Auto-generated method stub
		List<RegistrationEntity> lists = registrationDao.QueryCourseByMatchNum( m ) ;
		return putIntoJsonArray(lists) ;
		
	}

	/**
	* @Title:       StopPollUpdate
	* @Description: 结束比赛，统计最终得票
	* @param:       @param courseNum
	* @param:       @param finalPoll
	* @return:      void
	* @throws
	*/ 
	public boolean StopPollUpdate( RegistrationEntity r ) {
		// TODO Auto-generated method stub
		return registrationDao.StopPollUpdate( r ) ;
	}

	/**
	* @Title:       QueryMatchByCourseNum
	* @Description: 查找本课程报名的所有比赛
	* @param:       @param courseNum
	* @param:       @return
	* @return:      List<RegistrationEntity>
	* @throws
	*/ 
	public List<RegistrationEntity> QueryMatchByCourseNum(String courseNum) {
		// TODO Auto-generated method stub
		return registrationDao.QueryMatchByCourseNum( courseNum ) ;
	}

	/**
	* @Title:       isRegister
	* @Description: 判断课程时候已经报名了比赛
	* @param:       @param registration
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean isRegister(RegistrationEntity registration) {
		// TODO Auto-generated method stub
		return registrationDao.isRegister( registration ) ;
	}

	/** 
	* 方法： finishPreliminary
	* 描述： 结束初赛，将比赛成绩前8名置入复赛
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年6月23日 下午12:14:56
	* 修改人：Gavin
	* 修改时间：2015年6月23日 下午12:14:56
	* 修改备注：
	* @throws 
	*/ 
	public boolean finishPreliminary(String matchNum) {
		// TODO Auto-generated method stub
		return registrationDao.finishPreliminary( matchNum );
	}

	/** 
	* 方法： finishQuarter
	* 描述： 结束复赛，将比赛成绩前4名置入绝赛
	* 返回： void
	* 创建人：章华隽
	* 创建时间：2015年6月23日 下午12:26:26
	* 修改人：Gavin
	* 修改时间：2015年6月23日 下午12:26:26
	* 修改备注：
	* @throws 
	*/ 
	public boolean finishQuarter(String matchNum) {
		// TODO Auto-generated method stub
		return registrationDao.finishQuarter( matchNum );
	}
	
	/**
	* @Title:       putIntoJsonArray
	* @Description: 打包Lists的参赛者置于JsonArray中
	* @param:       @param lists
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray putIntoJsonArray( List<RegistrationEntity> lists ) {
		JSONArray jsonArray = new JSONArray() ;
		if( lists.size() != 0 ) {
			for( RegistrationEntity r : lists ) {
				CourseEntity c = courseDao.QueryByCourseNum( r.getCourseNum() ) ;
				Map<String, Object> map = new HashMap<String, Object>() ;
				if ( c != null ){
					UserEntity u = userDao.QueryByUserNum( c.getUserNum() ) ;
					if ( u != null ) {
						map.put( "NickName", u.getNickName() ) ;						
					}
					map.put( "Poll", r.getPoll() ) ;
					map.put( "PollNum", r.getPollNum() ) ;
					map.put( "CourseNum", c.getCourseNum() ) ;
					map.put( "CourseName" , c.getCourseName() ) ;
					map.put( "CourseContent", c.getCourseContent() ) ;
					map.put( "CoverPicture", c.getCoverPicture() ) ;
					map.put( "AudienceNum", c.getAudienceNum() ) ;
					SubjectEntity sub = subjectDao.QuerySubById( c.getSubId() ) ;
					if( sub != null ){
						map.put( "SubjectName", sub.getSubjectName() ) ;						
					}
					map.put( "RegisterTime", r.getRegisterTime() ) ;
					jsonArray.add(map) ;					
				} else {
					jsonArray = null ;
				}
			}			
		} else {
			jsonArray = null ;
		}
		return jsonArray ;
	}
	
}
