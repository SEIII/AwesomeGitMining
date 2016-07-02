package Application.common.data_service;

import java.util.List;

import Application.common.DTO.FollowRepoInfo;
import Application.common.DTO.FollowUserInfo;
import Application.common.DTO.WatchUserInfo;
import Application.common.info.GitMiningUserInfo;

public interface FollowDataService {
	int insertOneRepoCollection(String account, FollowRepoInfo repoInfo);

	int insertOneUserCollection(String account, FollowUserInfo userInfo);

	int insertOneUserWatch(String watcherAccount, String watchedAccount);

	GitMiningUserInfo getSingleUserInfo(String account);

	List<FollowUserInfo> getUserInfoList(String account);

	List<FollowRepoInfo> getRepoInfoList(String account);

	List<WatchUserInfo> getWatchingUserList(String account);

	List<WatchUserInfo> getSearchWatchUserList(String key);
}
