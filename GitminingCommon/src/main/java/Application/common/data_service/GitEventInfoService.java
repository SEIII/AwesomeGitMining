package Application.common.data_service;

import java.util.List;

import Application.common.DynamicInfo;

public interface GitEventInfoService {
	public List<DynamicInfo> getWatcherEventList(String account);
}
