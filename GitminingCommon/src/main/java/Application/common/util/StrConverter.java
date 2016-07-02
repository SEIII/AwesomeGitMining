package Application.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 申彬
 * 将不同类型的对象转化为String，如将日期转化为yyyy-MM-dd格式，将double精确到小数点后两位，<br/>
 * 将string缩进几个空格
 */
public class StrConverter {
	public static String getString(Calendar calendar){
		Date date = calendar.getTime();
		return getString(date);
	}

	public static String getString(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String result = dateFormat.format(date);
		return result;
	}

	public static String getString(double aNum){
		String result = String.format("%.2f", aNum);
		return result;
	}

	public static String indent(int indentNum,String former){
		if(indentNum>0){
			for(int i=0;i<indentNum;i++){
				former = " "+former;
			}
		}
		return former;
	}
}
