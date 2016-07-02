package Application.data.data_impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.common.DTO.FollowRepoInfo;
import Application.common.DTO.FollowUserInfo;
import Application.common.DTO.WatchUserInfo;
import Application.common.data_service.FollowDataService;
import Application.common.fuzzyQuery.FuzzyQuery;
import Application.common.fuzzyQuery.SearchResult;
import Application.common.info.GitMiningUserInfo;
import Application.data.DAO.sql.SQLTemplate;

@Component
public class FollowDataImpl implements FollowDataService{


	@Autowired
	SQLTemplate sqlTemplate;

	@Override
	public int insertOneRepoCollection(String account, FollowRepoInfo repoInfo) {
		// TODO Auto-generated method stub
		return sqlTemplate.insertOneRepoCollection(account, repoInfo);
	}

	@Override
	public int insertOneUserCollection(String account, FollowUserInfo userInfo) {
		// TODO Auto-generated method stub
		return sqlTemplate.insertOneUserCollection(account, userInfo);
	}

	@Override
	public List<FollowUserInfo> getUserInfoList(String account) {
		// TODO Auto-generated method stub
		return sqlTemplate.getUserCollection(account);
	}

	@Override
	public List<FollowRepoInfo> getRepoInfoList(String account) {
		// TODO Auto-generated method stub
		return sqlTemplate.getRepoCollection(account);
	}

	@Override
	public List<WatchUserInfo> getWatchingUserList(String account) {
		// TODO Auto-generated method stub
		return sqlTemplate.getUserWatcherListInfo(account);
	}

	@Override
	public int insertOneUserWatch(String watcherAccount, String watchedAccount) {
		// TODO Auto-generated method stub
		return sqlTemplate.insertOneUserWatching(watcherAccount, watchedAccount);
	}

	@Override
	public List<WatchUserInfo> getSearchWatchUserList(String key) {
		// TODO Auto-generated method stub

		List<WatchUserInfo> result = new ArrayList<WatchUserInfo>();
		List<String> accountList = sqlTemplate.getAllFisherLogin();

		List<SearchResult> searchResult = FuzzyQuery.fuzzyQuery((ArrayList<String>)accountList, key);
		for(SearchResult tempResult: searchResult){
			String userAccount = tempResult.getString();
			WatchUserInfo info = sqlTemplate.getSingleWatchInfo(userAccount);
			result.add(info);
		}

		return result;
	}

	@Override
	public GitMiningUserInfo getSingleUserInfo(String account) {
		// TODO Auto-generated method stub
		return sqlTemplate.getUserPassword(account);
	}



}
