package Bogu.Test;

import Bogu.Dao.SubjectDao;
import Bogu.Entity.SubjectEntity;

public class SubjectAddTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SubjectEntity sub = new SubjectEntity() ;
		sub.setPid(0);
		sub.setSubjectName("HelloWorld");
		
		SubjectDao subjectDao = new SubjectDao() ;
		boolean json = subjectDao.insert(sub) ;
		System.out.println(json);
	}

}
