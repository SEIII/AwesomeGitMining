package Application.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;




public class ClassifyItem implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Integer> classifyItem;


	public void init(){
		classifyItem = new LinkedHashMap<String, Integer>();
	}


	public boolean isContains(String key){
		return classifyItem.containsKey(key);
	}


	public void addItem(String key){

	    if(key==null) {
	        key="unknow";
	    }

		if(classifyItem == null)
			init();

		if(classifyItem.containsKey(key)){
			int value  = (int) classifyItem.get(key);
			value++;
			classifyItem.put(key, value);
		}else {
			classifyItem.put(key, 1);
		}
	}


	public Map<String, Integer> getClassifyResult(){

		return sortMap(classifyItem);
	}



	private Map<String, Integer> sortMap(Map<String, Integer> oldMap) {

		ArrayList<Map.Entry<String, Integer>> list;
		if(oldMap != null){
		 list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());
		}else {
			list = new ArrayList<Map.Entry<String,Integer>>();
		}



        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Entry<String, Integer> arg0,
                    Entry<String, Integer> arg1) {
                return arg1.getValue() - arg0.getValue();
            }
        });



        Map<String, Integer> newMap = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }
}
