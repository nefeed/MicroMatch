package MicroMatch.Test;

import MicroMatch.Dao.CourseDao;

public class CourseQueryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CourseDao cd = new CourseDao() ;
		
		System.out.println(cd.showHotCourse(5)) ;

	}

}
