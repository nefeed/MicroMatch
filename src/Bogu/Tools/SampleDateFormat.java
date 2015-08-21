/**
 * 
 */
package Bogu.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
* 项目名称：BoguCloudCourse
* 类名称：SampleDateFormat
* 类描述：时间格式转换为String类型自动以格式
* 创建人：章华隽
* 创建时间：2015年4月21日 上午9:38:05
* 修改人：Administrator
* 修改时间：2015年4月21日 上午9:38:05
* 修改备注：
*
*/
public class SampleDateFormat {


	/** 
	* 方法： toDateString
	* 描述： 以时间全部信息输出
	* 返回： String
	* 创建人：章华隽
	* 创建时间：2015年4月21日 上午9:38:30
	* 修改人：Administrator
	* 修改时间：2015年4月21日 上午9:38:30
	* 修改备注：
	* @throws 
	*/ 
	public static String toDateString(Date date) {
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return myFmt.format(date);
	}
	
	/** 
	* 方法： toDIYDateString
	* 描述： 不显示年和秒
	* 返回： String
	* 创建人：章华隽
	* 创建时间：2015年4月21日 上午9:38:49
	* 修改人：Administrator
	* 修改时间：2015年4月21日 上午9:38:49
	* 修改备注：
	* @throws 
	*/ 
	public static String toDIYDateString(Date date) {
		SimpleDateFormat myFmt = new SimpleDateFormat("MM月dd日 HH:mm");
		return myFmt.format(date);
	}
	
	/** 
	* 方法： toYearMonthDayString
	* 描述： 显示年月日
	* 返回： String
	* 创建人：章华隽
	* 创建时间：2015年4月21日 上午9:38:58
	* 修改人：Administrator
	* 修改时间：2015年4月21日 上午9:38:58
	* 修改备注：
	* @throws 
	*/ 
	public static String toYearMonthDayString(Date date) {
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日");
		return myFmt.format(date);
	}
	
}
