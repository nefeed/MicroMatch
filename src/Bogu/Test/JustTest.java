package Bogu.Test;

import net.sf.json.JSONArray;
import Bogu.Bll.MatchBll;

public class JustTest {

	public static void main(String[] args) {
		System.out.println("\\");
		System.out.println("//");
		System.out.println("\n");
		System.out.println(".");
		String s = "abc.txt" ;
		System.out.println( s.substring(s.lastIndexOf(".")) );
		MatchBll matchBll = new MatchBll() ;
		JSONArray json = matchBll.queryNewestMatches( 4 ) ;
		System.out.println( json ) ;
	}
}
