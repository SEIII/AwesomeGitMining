package Application.common.blService.statService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 申彬 统计结果对象
 * @param <K>
 * @param <V>
 */
public class StatResult<K extends Comparable<K>, V extends Comparable<V>> implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<Turple> turpleList = new ArrayList<Turple>();

	public List<Turple> getTrupleList() {
		return turpleList;
	}

	public void put(K key,V value){
		turpleList.add(new Turple(key, value));
	}

	public List<K> getKeys(){
		List<K> keyList = new ArrayList<>(turpleList.size());
		for(Turple turple:turpleList){
			keyList.add(turple.getKey());
		}
		return keyList;
	}

	public V getValue(K key){
		for(Turple turple:turpleList){
			if(turple.getKey().equals(key)){
				return turple.getValue();
			}
		}
		return null;
	}

	/**
	 * 由大到小排列key
	 */
	public void sortByKey(){
		StatResultSorter.sortByKey(this);
	}

	/**
	 * 由大到小排列value
	 */
	public void sortByValue(){
		StatResultSorter.sortByValue(this);
	}

	/**
     * 由小到大排列key
     */
    public void sortByKeyReverse(){
        StatResultSorter.sortByKey(this);
        Collections.reverse(getTrupleList());
    }

	/**
	 * 由小到大排列value
	 */
	public void sortByValueReverse(){
        StatResultSorter.sortByValue(this);
        Collections.reverse(getTrupleList());
    }

	public Map<K, V> getMap(){
	    LinkedHashMap<K, V> resultMap = new LinkedHashMap<>();
	    for(Turple turple:turpleList){
	        resultMap.put(turple.getKey(),turple.getValue());
        }
        return resultMap;
	}

	public class Turple implements Serializable{
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private K key;
		private V value;

		public Turple(K key, V value) {
			this.setKey(key);
			this.setValue(value);
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}
}
