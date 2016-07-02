package Application.data.data_impl.git;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import Application.AppContextSupport;
import Application.common.DTO.GitUserTotalInfo;
import Application.common.blService.statService.callback.StatTotalCallback;
import Application.common.data_service.GitUserTotalInfoService;

@Component
public class GitUserTotalInfoImpl implements GitUserTotalInfoService {


	@Autowired
	SourceDataAuxiliary auxiliary;

	@Autowired
	CacheManager cacheManager;

    @Override
    public GitUserTotalInfo getGitUserTotalInfo(String login) {
        Cache cache = cacheManager.getCache("gitUserTotalInfo");
        
        if(cache==null) {
            throw new NullPointerException("未缓存情况下调用getGitTotalInfo");
        }
        GitUserTotalInfo totalInfo = (GitUserTotalInfo) cache.get(login).get();
        if(totalInfo==null) {
            throw new NullPointerException("未缓存情况下调用getGitTotalInfo");
        }
        
        return totalInfo;
    }

    @Cacheable(value = "gitUserTotalInfo",key="#login")
    public GitUserTotalInfo getGitUserTotalInfo(String login,StatTotalCallback callback) {
//    	StatInfoObj statObj = new StatInfoObj(callback);
        
        StatUserInfoObj statObj = 
                AppContextSupport.getApplicationContext()
                    .getBean(StatUserInfoObj.class,callback);
        
        statObj.setSelfService(statObj);
        
    	return statObj.getGitUserTotalInfo(login);
    }


	@Override
	public boolean isHit(String login) {

		Cache cache = cacheManager.getCache("gitUserTotalInfo");

		if(cache!=null && cache.get(login)!=null){
			return true;
		}else{
			return false;
		}

	}

}
