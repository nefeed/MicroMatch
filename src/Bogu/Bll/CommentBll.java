package Bogu.Bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import Bogu.Dao.CommentDao;
import Bogu.Dao.UserDao;
import Bogu.Entity.CommentEntity;
import Bogu.Entity.UserEntity;
import Bogu.Factory.DaoFactory;
import Bogu.Tools.SampleDateFormat;

public class CommentBll extends BllAbstract {
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private CommentDao commentDao = daoFactory.getInstance( CommentDao.class ) ;
	private UserDao userDao = daoFactory.getInstance( UserDao.class ) ;
	
	/** 
	* 方法： insert
	* 描述： 创建评论
	* 返回： boolean
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:36:18
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:36:18
	* 修改备注：
	* @throws 
	*/ 
	public boolean insert(CommentEntity comment) {
		// TODO Auto-generated method stub
		return commentDao.insert( comment ) ;
	}
	
	/** 
	* 方法： QueryByObjectTypeAndId
	* 描述： 通过对象的类别和ID搜索相应的评论并且传值
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年4月17日 下午1:37:24
	* 修改人：Administrator
	* 修改时间：2015年4月17日 下午1:37:24
	* 修改备注：
	* @throws 
	*/ 
	public JSONArray QueryByObjectTypeAndNum( CommentEntity comment ) {
		JSONArray jsonArray = new JSONArray() ;
		List<CommentEntity> lists = commentDao.QueryByObjectTypeAndNum( comment ) ;
		return putIntoJsonArray( lists ) ;
	}
	
	/** 
	* 方法： showReplay
	* 描述：
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年5月25日 下午10:09:28
	* 修改人：Gavin
	* 修改时间：2015年5月25日 下午10:09:28
	* 修改备注：
	* @throws 
	*/ 
	public JSONArray showReply(Integer pid) {
		// TODO Auto-generated method stub
		List<CommentEntity> lists = commentDao.showReply( pid ) ;
		if ( lists == null ) {
			return null ;
		} else {
			return putIntoJsonArray(lists) ;	
		}
	}
	
	/** 
	* 方法： putIntoJsonArray
	* 描述： 封装成JsonArray格式
	* 返回： JSONArray
	* 创建人：章华隽
	* 创建时间：2015年5月25日 下午10:10:11
	* 修改人：Gavin
	* 修改时间：2015年5月25日 下午10:10:11
	* 修改备注：
	* @throws 
	*/ 
	public JSONArray putIntoJsonArray( List<CommentEntity> lists ) {
		JSONArray jsonArray = new JSONArray() ;
		if( lists == null ) {
			return null ;
		} else {
			for( CommentEntity com : lists ) {
				UserEntity user = userDao.QueryByUserNum( com.getUserNum() ) ;
				Map<String, Object> map = new HashMap<String, Object>() ;
				if ( user != null ) {
					map.put( "ID",  com.getId() ) ;
					map.put( "UserName", user.getUserName() ) ;
					map.put( "UserNum", user.getUserNum() ) ;
					map.put( "NickName", user.getNickName() ) ;
					map.put( "UserPicture", user.getUserPicture() ) ;
					map.put( "CommentContent", com.getCommentContent() ) ;
					String samDate = SampleDateFormat.toDIYDateString( com.getCommentTime() ) ;
					map.put( "CommentTime" , samDate ) ;
					map.put( "PID" , com.getPid() ) ;
				}
				jsonArray.add( map ) ;	
			}
			return jsonArray;			
		}
	}
}
