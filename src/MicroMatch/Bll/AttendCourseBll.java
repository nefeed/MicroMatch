package MicroMatch.Bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import MicroMatch.Dao.AttendCourseDao;
import MicroMatch.Dao.ChapterDao;
import MicroMatch.Dao.CourseDao;
import MicroMatch.Dao.SubjectDao;
import MicroMatch.Dao.UserDao;
import MicroMatch.Entity.AttendCourseEntity;
import MicroMatch.Entity.CourseEntity;
import MicroMatch.Entity.SubjectEntity;
import MicroMatch.Entity.UserEntity;
import MicroMatch.Factory.DaoFactory;
import MicroMatch.Tools.SampleDateFormat;

public class AttendCourseBll extends BllAbstract {
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private AttendCourseDao attendCourseDao = daoFactory.getInstance( AttendCourseDao.class ) ;
	private CourseDao courseDao = daoFactory.getInstance( CourseDao.class ) ;
	private UserDao userDao = daoFactory.getInstance( UserDao.class ) ;
	private SubjectDao subjectDao = daoFactory.getInstance( SubjectDao.class ) ;
	private ChapterDao chapterDao = daoFactory.getInstance( ChapterDao.class ) ;
	
	/**
	* @Title:       insert
	* @Description: 新建订阅
	* @param:       @param attendCourse
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean insert(AttendCourseEntity attendCourse) {
		// TODO Auto-generated method stub
		return attendCourseDao.insert( attendCourse ) ;
	}
	
	/**
	* @Title:       delete
	* @Description: 删除订阅
	* @param:       @param attendCourse
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean delete(AttendCourseEntity attendCourse) {
		return attendCourseDao.delete( attendCourse ) ;
	}

	/**
	* @Title:       IsRegister
	* @Description: 判断用户是否订阅
	* @param:       @param attendCourse
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean IsAttend(AttendCourseEntity attendCourse) {
		// TODO Auto-generated method stub
		return attendCourseDao.IsAttend( attendCourse ) ;
	}

	/** 
	* 方法： ShowMyAttends
	* 描述： 显示我的订阅
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年5月10日 下午11:20:02
	* 修改人：Gavin
	* 修改时间：2015年5月10日 下午11:20:02
	* 修改备注：
	* @throws 
	*/ 
	public JSONArray ShowMyAttends(String userNum) {
		// TODO Auto-generated method stub
		List<AttendCourseEntity> lists = attendCourseDao.showMyAttends( userNum ) ;
		if( lists == null ) {
			return null ;
		} else {
			JSONArray json = new JSONArray() ;
			for ( AttendCourseEntity ac : lists ) {
				Map<String, Object> map = new HashMap<String, Object>() ;
				CourseEntity c = courseDao.QueryByCourseNum( ac.getCourseNum() ) ;
				UserEntity user = userDao.QueryByUserNum( c.getUserNum() ) ;
				if( user != null ) {
					map.put( "UserNum" , user.getUserNum() ) ;
					map.put( "NickName", user.getNickName() ) ;				
				}
				map.put( "CourseName", c.getCourseName() ) ;
				map.put( "CoverPicture", c.getCoverPicture() ) ;
				map.put( "AudienceNum", c.getAudienceNum() ) ;
				map.put( "SubscriptionNum" , c.getSubscriptionNum() ) ;
				map.put( "CourseContent" , c.getCourseContent() ) ;
				map.put( "CourseNum", c.getCourseNum() ) ;
				String createtime = SampleDateFormat.toDIYDateString( c.getCreatetime() ) ;
				map.put( "Createtime", createtime ) ;
				map.put( "AudienceNum" , c.getAudienceNum() ) ;
				SubjectEntity sub = subjectDao.QuerySubById( c.getSubId() ) ;
				if( sub != null ){
					map.put( "SubId" , sub.getId() ) ;
					map.put( "SubjectName", sub.getSubjectName() ) ;				
				}
				int period = chapterDao.showPeriod( c.getCourseNum() ) ;
				map.put( "Period" , period ) ;
				json.add( map );	
			}
			return json ;
		}
	}
}
