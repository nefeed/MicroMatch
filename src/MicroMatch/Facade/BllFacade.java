package MicroMatch.Facade;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import MicroMatch.Bll.AccessoryBll;
import MicroMatch.Bll.AttendCourseBll;
import MicroMatch.Bll.AudienceRecorderBll;
import MicroMatch.Bll.AwardBll;
import MicroMatch.Bll.ChapterBll;
import MicroMatch.Bll.CommentBll;
import MicroMatch.Bll.CourseBll;
import MicroMatch.Bll.DownloadRecorderBll;
import MicroMatch.Bll.IndexSubjectBll;
import MicroMatch.Bll.MatchBll;
import MicroMatch.Bll.PollVoteBll;
import MicroMatch.Bll.RegistrationBll;
import MicroMatch.Bll.SubjectBll;
import MicroMatch.Bll.UserBll;
import MicroMatch.Entity.AccessoryEntity;
import MicroMatch.Entity.AttendCourseEntity;
import MicroMatch.Entity.AudienceRecorderEntity;
import MicroMatch.Entity.AwardEntity;
import MicroMatch.Entity.ChapterEntity;
import MicroMatch.Entity.CommentEntity;
import MicroMatch.Entity.CourseEntity;
import MicroMatch.Entity.DownloadRecorderEntity;
import MicroMatch.Entity.MatchEntity;
import MicroMatch.Entity.PollVoteEntity;
import MicroMatch.Entity.RegistrationEntity;
import MicroMatch.Entity.SubjectEntity;
import MicroMatch.Entity.UserEntity;
import MicroMatch.Factory.BllFactory;
import MicroMatch.Interface.BllInterface;


public class BllFacade implements BllInterface{

	// 单例
	private static class BllFacadeHolder{
		private static final BllFacade INSTANCE = new BllFacade() ;
	}
	
	// 构造函数
	private BllFacade() {}
	
	public static final BllFacade getInstance() {
		return BllFacadeHolder.INSTANCE ;
	}

	// 调用Bll层，单例模式
	private BllFactory bllFactory = BllFactory.getBllFactory();
	
	// 访问业务层
	private UserBll userBll = bllFactory.getInstance( UserBll.class ) ;
	private CourseBll courseBll = bllFactory.getInstance( CourseBll.class ) ;
	private ChapterBll chapterBll = bllFactory.getInstance( ChapterBll.class ) ;
	private CommentBll commentBll = bllFactory.getInstance( CommentBll.class ) ;
	private MatchBll matchBll = bllFactory.getInstance( MatchBll.class ) ;
	private RegistrationBll registrationBll = bllFactory.getInstance( RegistrationBll.class ) ;
	private SubjectBll subjectBll = bllFactory.getInstance( SubjectBll.class ) ;
	private AccessoryBll accessoryBll = bllFactory.getInstance( AccessoryBll.class ) ;
	private AwardBll awardBll = bllFactory.getInstance( AwardBll.class ) ;
	private AttendCourseBll attendCourseBll = bllFactory.getInstance( AttendCourseBll.class ) ;
	private DownloadRecorderBll downloadRecorderBll = bllFactory.getInstance( DownloadRecorderBll.class ) ;
	private AudienceRecorderBll AudienceRecorderBll = bllFactory.getInstance( AudienceRecorderBll.class ) ;
	private PollVoteBll pollVoteBll = bllFactory.getInstance( PollVoteBll.class ) ;
	private IndexSubjectBll indexSubjectBll = bllFactory.getInstance( IndexSubjectBll.class ) ;
	
	
	@Override
	public JSONObject Login( UserEntity user ) {
		// TODO Auto-generated method stub
		
		JSONObject json = userBll.Login( user ) ;
		return json ;
	}

	@Override
	public JSONObject SignUp ( UserEntity user ) {
	
		JSONObject json = userBll.insert( user ) ;
		return json ;
	}

	@Override
	public UserEntity QueryUserByUserNum( String userNum ) {
		// TODO Auto-generated method stub
		return userBll.queryByUserNum(userNum) ;
	}

	@Override
	public JSONObject CreateCourse(CourseEntity course) {
		// TODO Auto-generated method stub
		return courseBll.insert( course ) ;
	}

	@Override
	public CourseEntity QueryCourseByCourseNum(String courseNum) {
		// TODO Auto-generated method stub
		return courseBll.QueryCourseByCourseNum( courseNum ) ;
	}

	@Override
	public ChapterEntity QueryChapterByNum(String chapterNum) {
		// TODO Auto-generated method stub
		return chapterBll.QueryByNum( chapterNum ) ;
	}

	@Override
	public boolean PublishComment(CommentEntity comment) {
		// TODO Auto-generated method stub
		return commentBll.insert( comment ) ;
	}

	@Override
	public JSONArray QueryCourseByUserNum(String userNum) {
		// TODO Auto-generated method stub
		return courseBll.QueryByUserNum( userNum ) ;
	}

	@Override
	public JSONArray QueryChapterByCourseNum(String courseNum) {
		// TODO Auto-generated method stub
		return chapterBll.QueryByCourseNum( courseNum ) ;
	}

	@Override
	public boolean DeleteAccessory(int accessoryId) {
		// TODO Auto-generated method stub
		return accessoryBll.delete( accessoryId ) ;
	}

	@Override
	public JSONArray QueryCommentByObjectTypeAndId(CommentEntity comment) {
		// TODO Auto-generated method stub
		return commentBll.QueryByObjectTypeAndNum( comment ) ;
	}

	@Override
	public JSONArray QueryAccessoryByObjectTypeAndNum(AccessoryEntity acc) {
		// TODO Auto-generated method stub
		return accessoryBll.QueryByObjectTypeAndNum( acc ) ;
	}

	@Override
	public JSONObject CreateDownloadRecorder(
			DownloadRecorderEntity downloadRecorder) {
		// TODO Auto-generated method stub
		return downloadRecorderBll.insert( downloadRecorder ) ;
	}

	@Override
	public boolean AddAccessoryDownloadNum(AccessoryEntity accessory) {
		// TODO Auto-generated method stub
		return accessoryBll.AddAccessoryDownloadNum( accessory ) ;
	}

	@Override
	public JSONObject CreateAudienceRecorder(AudienceRecorderEntity ar) {
		// TODO Auto-generated method stub
		return AudienceRecorderBll.insert( ar ) ;
	}

	@Override
	public boolean AddCourseAudienceNum( int courseId ) {
		// TODO Auto-generated method stub
		return courseBll.AddAudienceNum( courseId ) ;
	}

	@Override
	public SubjectEntity QuerySubjectById(Integer subId) {
		// TODO Auto-generated method stub
		return subjectBll.QuerySubjectById( subId ) ;
	}

	@Override
	public boolean UpdateCourse(CourseEntity course) {
		// TODO Auto-generated method stub
		return courseBll.UpdateCourse( course ) ;
	}

	@Override
	public boolean UploadChapter(ChapterEntity chapter) {
		// TODO Auto-generated method stub
		return chapterBll.Upload( chapter ) ;
	}

	@Override
	public JSONObject AddChapter(ChapterEntity chapter) {
		// TODO Auto-generated method stub
		return chapterBll.AddChapter( chapter ) ;
	}

	@Override
	public JSONObject PublishMatch(MatchEntity match) {
		// TODO Auto-generated method stub
		return matchBll.insert( match ) ;
	}

	@Override
	public JSONArray QueryMatchByUserNum(String userNum) {
		// TODO Auto-generated method stub
		return matchBll.QueryByUserNum( userNum ) ;
	}

	@Override
	public MatchEntity QueryMatchByMatchNum(String matchNum) {
		// TODO Auto-generated method stub
		return matchBll.QueryByMatchNum( matchNum ) ;
	}

	@Override
	public boolean UpdateMatch(MatchEntity match) {
		// TODO Auto-generated method stub
		return matchBll.update( match ) ;
	}

	@Override
	public boolean RegisterMatch(RegistrationEntity registration) {
		// TODO Auto-generated method stub
		if( registrationBll.RegisterMatch(registration) ){
			System.out.println("注册报名成功！");
			matchBll.ANewRegistrant(registration.getMatchNum()) ;
			return true ;
		}else {
			return false ;
		}
	}

	@Override
	public boolean AddNewAward(AwardEntity award) {
		// TODO Auto-generated method stub
		return awardBll.insert( award ) ;
	}

	@Override
	public boolean GivenAward(AwardEntity award) {
		// TODO Auto-generated method stub
		return awardBll.GivenAward( award ) ;
	}

	@Override
	public boolean StopMatch(String matchNum) {
		// TODO Auto-generated method stub
		return matchBll.StopMatch( matchNum ) ;
	}

	@Override
	public boolean AttendCourse(AttendCourseEntity attendCourse) {
		// TODO Auto-generated method stub
		return attendCourseBll.insert( attendCourse ) ;
	}

	@Override
	public boolean DeleteAttendCourse(AttendCourseEntity attendCourse) {
		// TODO Auto-generated method stub
		return attendCourseBll.delete( attendCourse ) ;
	}

	@Override
	public boolean IsAttend(AttendCourseEntity attendCourse) {
		// TODO Auto-generated method stub
		return attendCourseBll.IsAttend(attendCourse);
	}

	@Override
	public boolean AddPollVote(PollVoteEntity pollVote) {
		// TODO Auto-generated method stub
		return pollVoteBll.insert( pollVote ) ;
	}

	@Override
	public boolean IsPollVote(PollVoteEntity pollVote) {
		// TODO Auto-generated method stub
		return pollVoteBll.IsPollVote( pollVote ) ;
	}

	@Override
	public boolean AddPollNum(RegistrationEntity registration) {
		// TODO Auto-generated method stub
		if( registrationBll.AddPollNum( registration ) ) {
			PollVoteEntity pv = new PollVoteEntity() ;
			pv.setMatchNum( registration.getMatchNum() ) ;
			pv.setMatchTemp( registration.getMatchTemp() ) ;
			pv.setCourseNum( registration.getCourseNum() ) ;
			float pollResult = pollVoteBll.CountPoll( pv ) ;
			registration.setPoll( pollResult ) ;
			registrationBll.StopPollUpdate( registration ) ;
			return true ;
		} else {
			return false ;
		}	
	}

	@Override
	public JSONArray QueryRegisterByMatchNum(MatchEntity m) {
		// TODO Auto-generated method stub
		return registrationBll.QueryCourseByMatchNum(m) ;
	}

	@Override
	public JSONArray QueryAllCourses() {
		// TODO Auto-generated method stub
		return courseBll.QueryAllCourses() ;
	}

	@Override
	public JSONArray QueryAllMatches() {
		// TODO Auto-generated method stub
		return matchBll.QueryAllMatches() ;
	}

	@Override
	public boolean UploadAccessory(AccessoryEntity accessory) {
		// TODO Auto-generated method stub
		return accessoryBll.insert( accessory ) ;
	}

	@Override
	public JSONArray QueryMatchAward(String matchNum) {
		// TODO Auto-generated method stub
		return awardBll.QueryMatchAward( matchNum ) ;
	}

	@Override
	public List<RegistrationEntity> QueryAlreadyRegisterMatches(String courseNum) {
		// TODO Auto-generated method stub
		return registrationBll.QueryMatchByCourseNum( courseNum ) ;
	}

	@Override
	public boolean IsRegister(RegistrationEntity registration ) {
		// TODO Auto-generated method stub
		return registrationBll.isRegister( registration );
	}

	@Override
	public boolean checkCourse(String courseNum) {
		// TODO Auto-generated method stub
		return courseBll.check( courseNum ) ;
	}

	@Override
	public boolean checkChapter(String chapterNum) {
		// TODO Auto-generated method stub
		return chapterBll.check( chapterNum ) ;
	}

	@Override
	public boolean checkMatch(String matchNum) {
		// TODO Auto-generated method stub
		return matchBll.check( matchNum ) ;
	}

	@Override
	public JSONArray QueryUncheckedCourses( Short check ) {
		// TODO Auto-generated method stub
		return courseBll.QueryUnChecked( check );
	}

	@Override
	public JSONArray QueryUncheckedMatches( Short check ) {
		// TODO Auto-generated method stub
		return matchBll.QueryUnChecked(check);
	}

	@Override
	public JSONArray QueryUncheckedChapters( Short check ) {
		// TODO Auto-generated method stub
		return chapterBll.QueryUnChecked( check ) ;
	}

	@Override
	public boolean ChangeUserType(UserEntity u) {
		// TODO Auto-generated method stub
		return userBll.ChangeUserType( u ) ;
	}

	@Override
	public JSONArray QueryAllUser( Short isLock ) {
		// TODO Auto-generated method stub
		return userBll.QueryAll(isLock) ;
	}

	@Override
	public JSONArray QueryUnsureUser(String inputName) {
		// TODO Auto-generated method stub
		return userBll.QueryUnsure(inputName);
	}

	@Override
	public JSONArray QueryUnsureCourse(String inputName) {
		// TODO Auto-generated method stub
		return courseBll.QuerySimilar(inputName) ;
	}

	@Override
	public JSONArray QueryUnsureMatch(String inputName) {
		// TODO Auto-generated method stub
		return matchBll.QuerySimilar(inputName);
	}

	@Override
	public JSONArray ShowMyAttends(String userNum) {
		// TODO Auto-generated method stub
		return attendCourseBll.ShowMyAttends( userNum ) ;
	}

	@Override
	public int showPeriod(String courseNum) {
		// TODO Auto-generated method stub
		return chapterBll.showPeriod( courseNum ) ;
	}

	@Override
	public JSONObject showChooseChapter(ChapterEntity cha) {
		// TODO Auto-generated method stub
		return chapterBll.showChoose( cha ) ;
	}

	@Override
	public boolean isMyCourse(CourseEntity c) {
		// TODO Auto-generated method stub
		return courseBll.isMyCourse( c ) ;
	}

	@Override
	public JSONArray QueryCourseBySubId(Integer subId) {
		// TODO Auto-generated method stub
		return courseBll.QueryBySubId( subId ) ;
	}

	@Override
	public JSONArray showAllSubjects() {
		// TODO Auto-generated method stub
		return subjectBll.showAll() ;
	}

	@Override
	public JSONArray QueryCourseBySubName(String subName) {
		// TODO Auto-generated method stub
		SubjectEntity s = subjectBll.QueryByName( subName ) ;
		return courseBll.QueryBySubId( s.getId() ) ;
	}

	@Override
	public JSONArray showIndexSubject(int indexNum) {
		// TODO Auto-generated method stub
		return indexSubjectBll.showIndex( indexNum ) ;
	}

	@Override
	public JSONArray showChildSubject(int subId) {
		// TODO Auto-generated method stub
		return subjectBll.showChild( subId ) ;
	}

	@Override
	public boolean updateUserPic(UserEntity u) {
		// TODO Auto-generated method stub
		return userBll.updateUserPic( u ) ;
	}

	@Override
	public JSONArray showReply(Integer pid) {
		// TODO Auto-generated method stub
		return commentBll.showReply( pid ) ;
	}

	@Override
	public JSONObject QueryUserInfo(String userNum) {
		// TODO Auto-generated method stub
		return userBll.UserInfo( userNum ) ;
	}

	@Override
	public boolean isMyMatch(MatchEntity m) {
		// TODO Auto-generated method stub
		return matchBll.isMine(m);
	}

	@Override
	public JSONObject queryAwardById(int id) {
		// TODO Auto-generated method stub
		return awardBll.queryById(id) ;
	}

	@Override
	public JSONArray showHotCourse(Integer listNum) {
		// TODO Auto-generated method stub
		return courseBll.showHotCourse( listNum ) ;
	}

	@Override
	public boolean stopPreliminary(String matchNum) {
		// TODO Auto-generated method stub
		if ( matchBll.StopMatch(matchNum) ) {
			registrationBll.finishPreliminary( matchNum ) ;
			return true;
		} else {
			return false;			
		}
	}

	@Override
	public boolean stopQuarter(String matchNum) {
		// TODO Auto-generated method stub
		if ( matchBll.StopMatch(matchNum) ) {
			registrationBll.finishQuarter( matchNum ) ;
			return true;
		} else {
			return false;			
		}
	}

	@Override
	public boolean stopFinal(String matchNum) {
		// TODO Auto-generated method stub
		return matchBll.StopMatch(matchNum);
	}

	@Override
	public JSONArray queryTheNewestMatches(Integer indexNum) {
		// TODO Auto-generated method stub
		return matchBll.queryNewestMatches(indexNum);
	}

	@Override
	public JSONArray queryPopularCourses( Integer indexNum ) {
		// TODO Auto-generated method stub
		return courseBll.queryPopularCourses( indexNum ) ;
	}

	@Override
	public JSONArray queryNewestCourses(Integer indexNum) {
		// TODO Auto-generated method stub
		return courseBll.queryNewestCourses( indexNum ) ;
	}

	@Override
	public JSONArray queryMatchByMatchTemp(Integer matchTemp) {
		// TODO Auto-generated method stub
		return matchBll.queryByMatchTemp(matchTemp);
	}

	@Override
	public JSONArray queryHistoryAward(String courseNum) {
		// TODO Auto-generated method stub
		return awardBll.queryHistoryAward( courseNum ) ;
	}
	
}
