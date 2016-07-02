package Application.business_logic.bl.resultPool;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 申彬
 *
 * @param <T>
 * 
 * 搜索结果缓存池
 */
public class SearchResultPool<T> {
	
	Map<String, List<T>> resultMap = new ResultMap<>();
	
	
	//最大size为500的map
	private static class ResultMap<K, V> extends LinkedHashMap<K, V>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		protected static int maxNum = 30;
		@Override
		protected boolean removeEldestEntry(java.util.Map.Entry<K, V> arg0) {
			if(size()>maxNum){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public void addResult(String keyword,List<T> resultList){
		resultMap.put(keyword, resultList);
	}
	
	public boolean containsResult(String keyword){
		return resultMap.containsKey(keyword);
	}
	
	public List<T> getResult(String keyword){
		return resultMap.get(keyword);
	}
	
	public void removeResult(String keyword){
		resultMap.remove(keyword);
	}

	public Map<String, List<T>> getResultMap() {
		return resultMap;
	}
}
