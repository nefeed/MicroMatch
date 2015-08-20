package MicroMatch.Test;

import java.util.ArrayList;
import java.util.List;

import MicroMatch.Dao.SubjectDao;
import MicroMatch.Entity.SubjectEntity;

public class SubjectAddTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubjectDao subjectDao = new SubjectDao() ;
		SubjectEntity sub = new SubjectEntity() ;
		List<SubjectEntity> list = new ArrayList<SubjectEntity>() ;
		sub.setPid(8);
		sub.setSubjectName("计算机一级");
		list.add(sub) ;
		sub = new SubjectEntity() ;
		sub.setPid(8);
		sub.setSubjectName("计算机二级  C");
		list.add(sub) ;
		sub = new SubjectEntity() ;
		sub.setPid(8);
		sub.setSubjectName("计算机二级  C++");
		list.add(sub) ;
		sub = new SubjectEntity() ;
		sub.setPid(8);
		sub.setSubjectName("计算机二级  JAVA");
		list.add(sub) ;
		sub = new SubjectEntity() ;
		sub.setPid(8);
		sub.setSubjectName("网络工程师");
		list.add(sub) ;
		sub = new SubjectEntity() ;
		sub.setPid(8);
		sub.setSubjectName("数据库工程师");
		list.add(sub) ;
		for ( SubjectEntity s : list ) {
			subjectDao.insert(s) ;			
		}
	}

}
