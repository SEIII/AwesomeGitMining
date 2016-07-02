package Application.common.blService.netService;

import java.util.List;

public interface RefreshBufferService {
	public void refreshBuffer(List<String> ReposKeys,List<String> userKeys);
	
	public long getRefreshPeriod();
}
