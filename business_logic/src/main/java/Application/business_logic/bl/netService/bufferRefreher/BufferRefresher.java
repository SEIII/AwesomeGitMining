package Application.business_logic.bl.netService.bufferRefreher;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import Application.business_logic.bl.resultPool.SearchResultPool;

public class BufferRefresher {

	//public final static long refreshPeriod = 3*60*60*1000;
    public final static long refreshPeriod = 30*1000;

	@SuppressWarnings("rawtypes")
	SearchResultPool resultPool;

	HashSet<String> readyToMoveKeySet;

	@SuppressWarnings("rawtypes")
	public BufferRefresher(SearchResultPool onePool){
		this.resultPool = onePool;
		readyToMoveKeySet = new HashSet<>();

		Timer timer = new Timer();
		DeleteRunnabel deleteRunnabel = new DeleteRunnabel();

		timer.scheduleAtFixedRate(deleteRunnabel, 2*refreshPeriod, 2*refreshPeriod);
	}

	public void refresh(List<String> keywords){
		readyToMoveKeySet.removeAll(keywords);
	}

	class DeleteRunnabel extends TimerTask{

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void run() {

			for(String keyToRemove:readyToMoveKeySet){
				if(resultPool.containsResult(keyToRemove)){
					resultPool.removeResult(keyToRemove);
				}
			}

			readyToMoveKeySet.clear();

			Map<String, List> bufferMap = resultPool.getResultMap();
			readyToMoveKeySet.addAll(bufferMap.keySet());

		}
	}
}
