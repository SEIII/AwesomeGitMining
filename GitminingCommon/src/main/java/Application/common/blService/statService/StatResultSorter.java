package Application.common.blService.statService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Application.common.blService.statService.StatResult.Turple;


/**
 * @author 申彬
 * 为了让网络传输的对象少一点，所以写一个静态类处理排序
 */
public class StatResultSorter {
	
	private static KeyComparator keyComparator;
	private static ValueComparator valueComparator;
	
	static{
		keyComparator = new KeyComparator();
		valueComparator = new ValueComparator();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sortByKey(StatResult result){
		List resultList = result.getTrupleList();
		Collections.sort(resultList, keyComparator);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sortByValue(StatResult result){
		List resultList = result.getTrupleList();
		Collections.sort(resultList, valueComparator);
	}
	
	@SuppressWarnings("rawtypes")
	private static class KeyComparator implements Comparator<StatResult.Turple>{

		@SuppressWarnings("unchecked")
		@Override
		public int compare(Turple o1, Turple o2) {
			return  o1.getKey().compareTo(o2.getKey()) * (-1);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static class ValueComparator implements Comparator<StatResult.Turple>{

		@SuppressWarnings("unchecked")
		@Override
		public int compare(Turple o1, Turple o2) {
			return o1.getValue().compareTo(o2.getValue()) * (-1);
		}

	}
}
