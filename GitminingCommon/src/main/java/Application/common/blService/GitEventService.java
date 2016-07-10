package Application.common.blService;

import java.util.List;
import java.util.Map;

import Application.common.DynamicInfo;

public interface GitEventService {

	public Map<String, List<DynamicInfo>> getUserDynamics(String account);

	public boolean getUserIsHit(String account);

	public void evictCache(String account);
}
