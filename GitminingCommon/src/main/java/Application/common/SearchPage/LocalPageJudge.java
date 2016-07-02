package Application.common.SearchPage;

import java.util.Arrays;

/**
 *
 * 负责判断翻页时不需要访问服务器的页
 * @author lenovo
 *
 */
public class LocalPageJudge {

	public final static String relatedSearchKey;
	public final static String sheetSearchKey;
	private static String[] keyList;

	static{
		relatedSearchKey = "related_search";
		sheetSearchKey = "sheet_search";
		keyList = (String[]) Arrays.asList(relatedSearchKey,sheetSearchKey).toArray();
	}

	public static boolean isLocalPage(String key){
		for(String specialKey:keyList){
			if(specialKey.equals(key)){
				return true;
			}
		}
		return false;
	}


}
