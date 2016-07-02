package Application.business_logic.bl.stat;

import java.text.DecimalFormat;
import java.util.List;

public class ListScoring {

	static ListComparator comparator = new ListComparator();

	/**
	 * 根据目标大小在list排序后的位置的百分比 开根号乘10 得到分数
	 * @param list int类型的list用于存放所有的数据
	 * @param target目标参数
	 * @return 根据target的值所给出的相应评分
	 * @author Echo
	 */
	public static double scoreByList(List<Integer> list, int target){

		list.sort(comparator);
		int position = list.indexOf(target);
		int total_num = list.size();

		
		double percent =((double)position) / ((double) total_num) ;
		percent = Math.sqrt(percent);
		percent *= 10;
		DecimalFormat df = new DecimalFormat("0.0");
		percent = Double.parseDouble(df.format(percent));

		return percent;

	}
}
