package Bogu.Test;

import java.util.List;




import net.sf.json.JSONArray;
import Bogu.Bll.MatchBll;
import Bogu.Dao.MatchDao;
import Bogu.Entity.MatchEntity;


public class MatchTest {
	public static void main(String[] args) {
		MatchDao matchDao = new MatchDao() ;
		List<MatchEntity> lists = matchDao.queryByMatchTemp(0) ;
		System.out.println(lists);
		
		MatchBll matchBll = new MatchBll() ;
		JSONArray json = matchBll.queryNewestMatches(4) ;
		System.out.println(json);
	}

}
