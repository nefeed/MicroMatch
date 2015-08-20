package MicroMatch.Bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import MicroMatch.Dao.ChapterDao;
import MicroMatch.Dao.CourseDao;
import MicroMatch.Dao.SubjectDao;
import MicroMatch.Dao.UserDao;
import MicroMatch.Entity.CourseEntity;
import MicroMatch.Entity.SubjectEntity;
import MicroMatch.Entity.UserEntity;
import MicroMatch.Factory.DaoFactory;
import MicroMatch.Tools.SampleDateFormat;

public class CourseBll extends BllAbstract {

	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private CourseDao courseDao = daoFactory.getInstance( CourseDao.class ) ;
	private SubjectDao subjectDao = daoFactory.getInstance( SubjectDao.class ) ;
	private UserDao userDao = daoFactory.getInstance( UserDao.class ) ;
	private ChapterDao chapterDao = daoFactory.getInstance( ChapterDao.class ) ;
	
	/** 
	* 方法： insert
	* 描述： 创建课程
	* 返回： JSONObject
	* 创建人：章华隽
	* 创建时间：2015年4月16日 下午6:38:30
	* 修改人：Administrator
	* 修改时间：2015年4月16日 下午6:38:30
	* 修改备注：
	* @throws 
	*/ 
	public JSONObject insert(CourseEntity course) {
		// TODO Auto-generated method stub
		return courseDao.insert( course ) ;
	}


	/** 
	* 方法： QueryQueryCourseByCourseNum
	* 描述：通过Num 查找课程信息
	* 返回： CourseEntity
	* 创建人：章华隽
	* 创建时间：2015年4月16日 下午6:46:13
	* 修改人：Administrator
	* 修改时间：2015年4月16日 下午6:46:13
	* 修改备注：
	* @throws 
	*/ 
	public CourseEntity QueryCourseByCourseNum(String courseNum) {
		// TODO Auto-generated method stub
		return courseDao.QueryByCourseNum( courseNum );
	}

	/** 
	* 方法： QueryByUserId
	* 描述： 通过创建者ID查找发布的课程
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:44:25
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:44:25
	* 修改备注：
	* @throws 
	*/ 
	public JSONArray QueryByUserNum( String userNum ) {
		List<CourseEntity> lists = courseDao.queryByUserNum( userNum ) ;
		return putIntoJsonArray( lists ) ;
	}


	/** 
	* 方法： AddAudienceNum
	* 描述： 增加观看记录
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午5:30:35
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午5:30:35
	* 修改备注：
	* @throws 
	*/ 
	public boolean AddAudienceNum( int courseId ) {
		// TODO Auto-generated method stub
		return courseDao.AddAudienceNum( courseId );
	}


	/** 
	* 方法： UpdateCourse
	* 描述： 修改课程
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月20日 下午1:30:14
	* 修改人：Administrator
	* 修改时间：2015年4月20日 下午1:30:14
	* 修改备注：
	* @throws 
	*/ 
	public boolean UpdateCourse(CourseEntity course) {
		// TODO Auto-generated method stub
		return courseDao.update(course) ;
	}

	/**
	* @Title:       OpenComment
	* @Description: 开放聊天功能
	* @param:       @param courseNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean OpenComment( String courseNum ) {
		return courseDao.OpenComment( courseNum ) ;
	}

	/**
	* @Title:       QueryAllCourses
	* @Description: 遍历所有课程
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray QueryAllCourses() {
		// TODO Auto-generated method stub
		List<CourseEntity> lists = courseDao.QueryAllCourses() ;
		return putIntoJsonArray( lists ) ;
	}


	/**
	* @Title:       check
	* @Description: 审核课程
	* @param:       @param courseNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean check(String courseNum) {
		// TODO Auto-generated method stub
		return courseDao.check( courseNum ) ;
	}
	
	/**
	* @Title:       QueryUnChecked
	* @Description: 遍历未审核的课程
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray QueryUnChecked( Short check) {
		List<CourseEntity> lists = courseDao.QueryUnChecked( check ) ;
		return putIntoJsonArray( lists ) ;
	}
	
	/**
	* @Title:       QuerySimilar
	* @Description: 模糊搜索
	* @param:       @param input
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray QuerySimilar( String inputName ) {
		List<CourseEntity> lists = courseDao.QuerySimilar( inputName ) ;
		return putIntoJsonArray( lists ) ;
	}
	
	/** 
	* 方法： putIntoJsonArray
	* 描述： 将获得的lists打包成JsonArray
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年5月10日 上午10:35:29
	* 修改人：GavinVictory
	* 修改时间：2015年5月10日 上午10:35:29
	* 修改备注：
	* @throws 
	*/ 
	@SuppressWarnings("null")
	public JSONArray putIntoJsonArray( List<CourseEntity> lists ) {
		if( lists == null || lists.size() == 0 ){
			return null ;
		} else {
			JSONArray jsonArray = new JSONArray() ;
			for ( CourseEntity course : lists ) {
				Map<String, Object> map = new HashMap<String, Object>() ;
				UserEntity user = userDao.QueryByUserNum( course.getUserNum() ) ;
				if( user != null ) {
					map.put( "UserNum" , user.getUserNum() ) ;
					map.put( "NickName", user.getNickName() ) ;				
				}
				map.put( "CourseName", course.getCourseName() ) ;
				map.put( "CoverPicture", course.getCoverPicture() ) ;
				map.put( "AudienceNum", course.getAudienceNum() ) ;
				map.put( "SubscriptionNum" , course.getSubscriptionNum() ) ;
				map.put( "CourseContent" , course.getCourseContent() ) ;
				map.put( "CourseNum", course.getCourseNum() ) ;
				String createtime = SampleDateFormat.toDIYDateString( course.getCreatetime() ) ;
				map.put( "Createtime", createtime ) ;
				map.put( "AudienceNum" , course.getAudienceNum() ) ;
				int subID = course.getSubId() ;
				SubjectEntity sub = subjectDao.QuerySubById( subID ) ;
				if( sub != null ){
					map.put( "SubId" , sub.getId() ) ;
					map.put( "SubjectName", sub.getSubjectName() ) ;				
				}
				int period = chapterDao.showPeriod( course.getCourseNum() ) ;
				map.put( "Period" , period ) ;
				jsonArray.add( map );	
			}
			return jsonArray ;	
		}
	}
	
	/**
	* @Title:       isMyCourse
	* @Description: 判断用户是否为课程的创建者
	* @param:       @param c
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean isMyCourse(CourseEntity c) {
		// TODO Auto-generated method stub
		return courseDao.isMyCourse( c );
	}

	/**
	* @Title:       QueryCourseBySubId
	* @Description: 根据学科查找课程
	* @param:       @param subId
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray QueryBySubId(Integer subId) {
		// TODO Auto-generated method stub
		List<CourseEntity> lists = courseDao.QueryBySubId( subId ) ;
		lists = addChildSubCourse( lists , subId ) ;
		return putIntoJsonArray( lists ) ;
	}
	
	/**
	* @Title:       addChildSubCourse
	* @Description: 将所有子学科分类的课程加入本队列
	* @param:       @param ancientList
	* @param:       @param subId
	* @param:       @return
	* @return:      List<CourseEntity>
	* @throws
	*/ 
	@SuppressWarnings("null")
	public List<CourseEntity> addChildSubCourse( List<CourseEntity> lists , int subId ) {
		List<SubjectEntity> childs = subjectDao.showChild( subId ) ;
		if ( childs != null ) {
			for ( int i = 0 ; i < childs.size() ; i ++ ) {
				int cid = childs.get(i).getId() ;
				List<CourseEntity> lists2 = courseDao.QueryBySubId( cid ) ;
				if ( lists2 != null ) {
					lists.addAll( lists2 ) ;
				}
				lists = addChildSubCourse( lists , cid ) ;					
			}			
		}
		return lists ;
	}


	/**
	* @Title:       showHotCourse
	* @Description: 显示首页课程
	* @param:       @param listNum
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray showHotCourse(Integer listNum) {
		// TODO Auto-generated method stub
		List<CourseEntity> lists = courseDao.showHotCourse( listNum ) ;
		return putIntoJsonArray( lists ) ;
	}


	/**
	* @Title:       queryPopularCourses
	* @Description: 显示热门比赛
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray queryPopularCourses( Integer indexNum ) {
		// TODO Auto-generated method stub
		List<CourseEntity> lists = courseDao.queryPopularCourse( indexNum ) ;
		return putIntoJsonArray( lists ) ;
	}
	
	/**
	* @Title:       queryPopularCourses
	* @Description: 显示最新的比赛
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	public JSONArray queryNewestCourses( Integer indexNum ) {
		// TODO Auto-generated method stub
		List<CourseEntity> lists = courseDao.queryNewestCourse( indexNum ) ;
		return putIntoJsonArray( lists ) ;
	}
	
}
