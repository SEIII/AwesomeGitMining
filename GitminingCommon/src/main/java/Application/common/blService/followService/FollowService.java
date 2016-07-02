package Application.common.blService.followService;

import java.util.List;

import Application.common.DTO.FollowRepoInfo;
import Application.common.DTO.FollowUserInfo;
import Application.common.DTO.WatchUserInfo;
import Application.common.info.GitMiningUserInfo;

public interface FollowService {
	 int insertOneRepo(String account, FollowRepoInfo repoInfo);

	 int insertOneUser(String account, FollowUserInfo userInfo);

	 int insertOneWatch(String watcherAccount, String watchedAccount);

	 GitMiningUserInfo getSingleUserInfo(String account);

	 List<FollowUserInfo> getUserInfoList(String account);

	 List<FollowRepoInfo> getRepoInfoList(String account);

	 List<WatchUserInfo> getWatchingUserList(String account);

	 List<WatchUserInfo> getSearchWatchUserList(String key);
}
