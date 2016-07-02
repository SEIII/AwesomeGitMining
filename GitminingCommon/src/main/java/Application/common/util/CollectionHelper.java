package Application.common.util;

import java.util.Collections;
import java.util.List;

public class CollectionHelper {

    public static <T extends Number> double addAll(List<T> list){
        double total =0;
        
        for(T num:list) {
            total = total + num.doubleValue();
        }
        
        return total;
    }
    
    
    public static <T extends Number> double maxValue(List<T> list) {
        
        double max = list.get(0).doubleValue();
        for(Number oneNumber : list) {
            if(oneNumber.doubleValue()>max) {
                max = oneNumber.doubleValue();
            }
        }
        
        return max;
    } 
    
    public static <T extends Comparable<T>> int maxValueIndex(List<T> list) {
        
        if(list.isEmpty()) {
            return -1;
        }
        
        T max = Collections.max(list);
        int maxValueIndex = list.indexOf(max);
        return maxValueIndex;
    }
    
    public static <T extends Number> double maxValuePercent(List<T> list) {
        double max = maxValue(list);
        double total = addAll(list);
        return max/total;
    }
    
}
