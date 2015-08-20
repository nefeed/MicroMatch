package MicroMatch.Bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import MicroMatch.Dao.ChapterDao;
import MicroMatch.Entity.ChapterEntity;
import MicroMatch.Factory.DaoFactory;
import MicroMatch.Tools.SampleDateFormat;

public class ChapterBll extends BllAbstract {
	
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private ChapterDao chapterDao = daoFactory.getInstance( ChapterDao.class ) ;
	
	

	/** 
	* 方法： QueryByNum
	* 描述： 通过Num查找实体
	* 返回： ChapterEntity
	* 创建人：章华隽
	* 创建时间：2015年4月16日 下午7:24:10
	* 修改人：Administrator
	* 修改时间：2015年4月16日 下午7:24:10
	* 修改备注：
	* @throws 
	*/ 
	public ChapterEntity QueryByNum(String chapterNum) {
		// TODO Auto-generated method stub
		return chapterDao.QueryByNum( chapterNum ) ;
	}

	/** 
	* 方法： QueryByCourseNum
	* 描述： 通过课程Num查找课程下属所有章节
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:45:59
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:45:59
	* 修改备注：
	* @throws 
	*/ 
	public JSONArray QueryByCourseNum ( String courseNum ) {
		List<ChapterEntity> lists = chapterDao.QueryByCourseNum( courseNum ) ;
		if ( lists != null ) {
			JSONArray jsonArray = new JSONArray() ;
			for( ChapterEntity cha : lists) {
				Map<String, Object> map = new HashMap<String, Object>() ;
				map.put( "ChapterName" , cha.getChapterName() ) ;
				map.put( "ChapterVideo", cha.getChapterVideo() ) ;
				map.put( "ChapterContent" , cha.getChapterContent() ) ;
				map.put( "ChapterNum", cha.getChapterNum() ) ;
				map.put( "isComment" , cha.getIsComment() ) ;
				map.put( "ListID" , cha.getListId() ) ;
				map.put( "PID" , cha.getPid() ) ;
				jsonArray.add( map ) ;
			} 
			return jsonArray ;
		} else {
			return null ;		
		}
	}


	/** 
	* 方法： Upload
	* 描述： 上传文件
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月20日 下午2:46:21
	* 修改人：Administrator
	* 修改时间：2015年4月20日 下午2:46:21
	* 修改备注：
	* @throws 
	*/ 
	public boolean Upload(ChapterEntity chapter) {
		// TODO Auto-generated method stub
		return chapterDao.Upload( chapter ) ;
	}


	/** 
	* 方法： AddChapter
	* 描述： 新增章节
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月20日 下午3:03:14
	* 修改人：Administrator
	* 修改时间：2015年4月20日 下午3:03:14
	* 修改备注：
	* @throws 
	*/ 
	public JSONObject AddChapter(ChapterEntity chapter) {
		// TODO Auto-generated method stub
		ChapterEntity cha = chapterDao.insert(chapter) ;
		JSONObject json = new JSONObject() ;
		if( cha != null ){
			json.put( "result" , 0 ) ;
			json.put( "ChapterName" , cha.getChapterName() ) ;
			json.put( "ChapterVideo", cha.getChapterVideo() ) ;
			json.put( "ChapterContent", cha.getChapterContent() ) ;
			json.put( "CourseNum", cha.getCourseNum() ) ;
			json.put( "ListID" , cha.getListId() ) ;
			json.put( "ChapterNum", cha.getChapterNum() ) ;
			json.put( "Createtime" , SampleDateFormat.toDIYDateString( cha.getCreateTime() ) ) ;			
		}else {
			json.put( "result" , 1 ) ;
		}
		return json ;
	}

	/**
	* @Title:       check
	* @Description: 审核章节
	* @param:       @param chapterNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean check(String chapterNum) {
		// TODO Auto-generated method stub
		return chapterDao.check( chapterNum ) ;
	}
	
	/**
	* @Title:       QueryUnChecked
	* @Description: 遍历所有未过审的章节视频
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	@SuppressWarnings("null")
	public JSONArray QueryUnChecked( Short check ) {
		List<ChapterEntity> lists = chapterDao.QueryUnChecked( check ) ;
		if ( lists != null || lists.size() != 0 ) {
			JSONArray jsonArray = new JSONArray() ;
			for( ChapterEntity cha : lists) {
				Map<String, Object> map = new HashMap<String, Object>() ;
				map.put( "ChapterName" , cha.getChapterName() ) ;
				map.put( "ChapterVideo" , cha.getChapterVideo() ) ;
				map.put( "ChapterContent" , cha.getChapterContent() ) ;
				map.put( "ChapterNum", cha.getChapterNum() ) ;
				map.put( "isComment" , cha.getIsComment() ) ;
				map.put( "isChecked" , cha.getIsChecked() ) ;
				map.put( "ListID" , cha.getListId() ) ;
				map.put( "PID" , cha.getPid() ) ;
				jsonArray.add( map ) ;
			} 
			return jsonArray ;
		} else {
			return null ;		
		}
	}
	
	/**
	* @Title:       OpenComment
	* @Description: 开放评论
	* @param:       @param chapterNum
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean OpenComment( String chapterNum ) {
		return chapterDao.OpenComment( chapterNum ) ;
	}

	/**
	* @Title:       showPeriod
	* @Description: 显示课程学时
	* @param:       @param courseNum
	* @param:       @return
	* @return:      int
	* @throws
	*/ 
	public int showPeriod(String courseNum) {
		// TODO Auto-generated method stub
		return chapterDao.showPeriod(courseNum);
	}
	
	/**
	* @Title:       showChoose
	* @Description: 返回被选中的章节
	* @param:       @param chapter
	* @param:       @return
	* @return:      JSONArray
	* @throws
	*/ 
	@SuppressWarnings("null")
	public JSONObject showChoose( ChapterEntity chapter ) {
		List<ChapterEntity> lists = chapterDao.showChoose( chapter ) ;
		if ( lists != null || lists.size() != 0 ) {
				JSONObject json = new JSONObject() ;
				ChapterEntity cha = lists.get(0) ;
				json.put( "ChapterName" , cha.getChapterName() ) ;
				json.put( "ChapterVideo" , cha.getChapterVideo() ) ;
				json.put( "ListId", cha.getListId() ) ;
				json.put( "ChapterContent" , cha.getChapterContent() ) ;
				json.put( "ChapterNum", cha.getChapterNum() ) ;
				json.put( "isComment" , cha.getIsComment() ) ;
				json.put( "isChecked" , cha.getIsChecked() ) ;
				json.put( "PID" , cha.getPid() ) ;
				return json ;
		} else {
			return null ;		
		}
	}
}
