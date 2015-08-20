package MicroMatch.Tools;

import java.io.UnsupportedEncodingException;

/**
 * 
 * 
 * 描述：<p>&nbsp;&nbsp;&nbsp;&nbsp;功能描述,该部分必须以中文句号结尾。</p>
 * 创建日期：2012-7-16 下午4:28:16<br>
 * @author：tianyj<br> 
 * @update：$Date$<br>
 * @version：$Revision$<br>
 * @since 版本号，用来指定该类是从整个项目的哪个版本开始加入到项目中的
 */
public class ConvertCharSet {

    private static String changeCharSet(
            String str, String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            // 用默认字符编码解码字符串。
            byte[] bs = str.getBytes("ISO-8859-1");
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return str;
    }

    /**
     * 字符串转化为UTF-8
     * @param str
     * @return
     */
    public static String toUTF8(String str){
        String result = str;
        try {
            result = changeCharSet(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 字节数组转化为UTF-8
     * @param bty
     * @return
     */
    public static String toUTF8(byte[] bty){
        try {
            if (bty.length > 0) {
                return new String(bty, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new String(bty);
    }

    /**
     * 转换为UTF-8编码
     * @param str
     * @return
     */
    public static String beUTF8(String str) {
        String result = "字符编码转换失败！" ;
        try {
            str = str.trim() ;
            result = new String(str.getBytes("ISO-8859-1"), "UTF-8") ;
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace() ;
        }
        return result ;
    }
}
