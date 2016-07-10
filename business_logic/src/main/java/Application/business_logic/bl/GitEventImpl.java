package Application.business_logic.bl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import Application.common.DynamicInfo;
import Application.common.blService.GitEventService;
import Application.common.data_service.GitEventInfoService;


@Component
public class GitEventImpl implements GitEventService {

	@Autowired
	CacheManager cacheManager;

	@Autowired
	GitEventInfoService gitService;

	@Cacheable(value="getUserDynamics",key="#account")
	@Override
	public Map<String, List<DynamicInfo>> getUserDynamics(String account) {
		// TODO Auto-generated method stub

		Map<String, List<DynamicInfo>> resultMap = new TreeMap<String, List<DynamicInfo>>();

		List<DynamicInfo> allDynamics = gitService.getWatcherEventList(account);
		for(DynamicInfo dynamicInfo: allDynamics){
			String dateString = dynamicInfo.getDateString();
			String[] tempList = dateString.split(" ");
			String yearString = tempList[0];

			if(resultMap.containsKey(yearString)){
				List<DynamicInfo> tempDynamics = resultMap.get(yearString);
				tempDynamics.add(dynamicInfo);
				resultMap.put(yearString, tempDynamics);
			}else{
				List<DynamicInfo> initDynamics = new ArrayList<DynamicInfo>();
				initDynamics.add(dynamicInfo);
				resultMap.put(yearString, initDynamics);
			}
		}

		TreeMap<String, List<DynamicInfo>> reversed
						= new TreeMap<String, List<DynamicInfo>>(Collections.reverseOrder());// 初始化为反转排序
		reversed.putAll(resultMap);

		return reversed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean getUserIsHit(String account) {
		// TODO Auto-generated method stub
		Cache cache = cacheManager.getCache("getUserDynamics");


        if(cache==null) {
            return false;
        }

        if(cache.get(account) == null)
        	return false;


        Map<String, List<DynamicInfo>> result = (Map<String, List<DynamicInfo>>) cache.get(account).get();
        if(result==null) {
            return false;
        }

        return true;
	}

	@Override
	public void evictCache(String account) {
		// TODO Auto-generated method stub
		Cache cache = cacheManager.getCache("getUserDynamics");
		cache.evict(account);
	}


}
