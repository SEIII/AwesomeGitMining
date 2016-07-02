package Application.business_logic.bl.netService;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.business_logic.bl.netService.bufferRefreher.BufferRefresher;
import Application.business_logic.bl.resultPool.ReposResultPool;
import Application.business_logic.bl.resultPool.UserResultPool;
import Application.common.blService.netService.RefreshBufferService;

@Component
public class RefreshBufferImpl implements RefreshBufferService{

	@Autowired
	ReposResultPool reposResultPool;
	
	@Autowired
	UserResultPool userResultPool;
	
	BufferRefresher reposRefresher;
	BufferRefresher userRefresher;
	
	@PostConstruct
	public void init(){
		reposRefresher = new BufferRefresher(reposResultPool);
		userRefresher = new BufferRefresher(userResultPool);
	}
	
	@Override
	public void refreshBuffer(List<String> reposKeys,List<String> userKeys) {
		reposRefresher.refresh(reposKeys);
		userRefresher.refresh(userKeys);
	}

	@Override
	public long getRefreshPeriod() {
		return BufferRefresher.refreshPeriod;
	}
	
}
