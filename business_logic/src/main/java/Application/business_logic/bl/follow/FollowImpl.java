package Application.business_logic.bl.follow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.common.DTO.FollowRepoInfo;
import Application.common.DTO.FollowUserInfo;
import Application.common.DTO.WatchUserInfo;
import Application.common.blService.followService.FollowService;
import Application.common.data_service.FollowDataService;
import Application.common.info.GitMiningUserInfo;

@Component
public class FollowImpl implements FollowService{


	@Autowired
	FollowDataService followService;

	@Override
	public int insertOneRepo(String account, FollowRepoInfo info) {
		// TODO Auto-generated method stub
		return followService.insertOneRepoCollection(account, info);
	}

	@Override
	public int insertOneUser(String account, FollowUserInfo userInfo) {
		// TODO Auto-generated method stub
		return followService.insertOneUserCollection(account, userInfo);
	}

	@Override
	public List<FollowUserInfo> getUserInfoList(String account) {
		// TODO Auto-generated method stub
		return followService.getUserInfoList(account);
	}

	@Override
	public List<FollowRepoInfo> getRepoInfoList(String account) {
		// TODO Auto-generated method stub
		return followService.getRepoInfoList(account);
	}

	@Override
	public List<WatchUserInfo> getWatchingUserList(String account) {
		// TODO Auto-generated method stub
		return followService.getWatchingUserList(account);
	}

	@Override
	public int insertOneWatch(String watcherAccount, String watchedAccount) {
		// TODO Auto-generated method stub
		return followService.insertOneUserWatch(watcherAccount, watchedAccount);
	}

	@Override
	public List<WatchUserInfo> getSearchWatchUserList(String key) {
		// TODO Auto-generated method stub
		return followService.getSearchWatchUserList(key);
	}

	@Override
	public GitMiningUserInfo getSingleUserInfo(String account) {
		// TODO Auto-generated method stub
		return followService.getSingleUserInfo(account);
	}


}
