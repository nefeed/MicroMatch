package MicroMatch.Test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

public class JustTest {

	private static final int SUN = 1 ;
	public enum weekday {
		Sun, Mon, Tues, Thur, Wed, Fri, Sat
	}
	public static int trycatchflnal() {
		int i = 0 ;
		try {
			i++ ;
			return i ;
		} catch (Exception e) {
			// TODO: handle exception
			i-- ;
			return i;
		} finally {
			return ++i ;
		}
	}
	
	public static int binary( int[] arr, int key ) {
		int i = 0 ;
		int midpoint = 0 ;
		int lowerbound = 0 ;
		int upperbound = arr.length - 1 ;
		System.out.println("字符上限：" + upperbound);
		while ( lowerbound <= upperbound ) {
			midpoint = (lowerbound+upperbound)/2 ;
			System.out.println("第" + ++i + "次查询！" ) ;
			System.out.println("这次查询的中间值：" + midpoint ) ;
			if ( key == arr[midpoint] ) {
				return midpoint ;
			} else if ( key < arr[midpoint] ) {
				upperbound = midpoint ;
			} else {
				lowerbound = midpoint ;
			}
		}
		return -1 ;
	}
	public static void main(String[] args) {
		System.out.println(weekday.Sun.ordinal());
		System.out.println(trycatchflnal());
		int[] arr = {15,63,88,66,5,10,55,99,66,22,78,30,90} ;
		System.out.println( "二分查找结果：" + binary( arr , 55)) ;
	}
}
