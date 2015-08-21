package Bogu.Bll;

import Bogu.Dao.PollVoteDao;
import Bogu.Entity.PollVoteEntity;
import Bogu.Factory.DaoFactory;

public class PollVoteBll extends BllAbstract{
	private DaoFactory daoFactory = DaoFactory.getDaoFactory() ;
	private PollVoteDao pollVoteDao = daoFactory.getInstance( PollVoteDao.class ) ;
	
	/**
	* @Title:       insert
	* @Description: 新建投票
	* @param:       @param pollVote
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean insert(PollVoteEntity pollVote) {
		// TODO Auto-generated method stub
		return pollVoteDao.insert(pollVote) ;
	}
	
	/**
	* @Title:       IsPollVote
	* @Description: 判断是否已经投票
	* @param:       @param pollVote
	* @param:       @return
	* @return:      boolean
	* @throws
	*/ 
	public boolean IsPollVote( PollVoteEntity pollVote ) {
		return pollVoteDao.IsPollVote(pollVote) ;
	}

	/**
	* @Title:       CountPoll
	* @Description: 统计这门课程的最终得票情况
	* @param:       @param matchNum
	* @param:       @return
	* @return:      float
	* @throws
	*/ 
	public float CountPoll(PollVoteEntity pv) {
		// TODO Auto-generated method stub
		return pollVoteDao.CountPoll( pv ) ;
	}
}
