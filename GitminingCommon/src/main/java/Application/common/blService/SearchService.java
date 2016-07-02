package Application.common.blService;


import java.util.List;

import Application.common.ReposSort;
import Application.common.SearchPage.SearchPage;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;

public interface SearchService {

	public SearchPage<ReposInfo> searchRepos(String key);

	public SearchPage<ReposInfo> searchRepos(String key, ReposSort sort);
	
	public List<ReposInfo> searchRepos(String key, ReposSort sort,int pageIndex);
	
	public List<UserInfo> searchUser(String key,int pageIndex);

	public SearchPage<UserInfo> searchUser(String key);

	public SearchPage<ReposInfo> searchUserCreateRepos(String login);

	public SearchPage<ReposInfo> searchUserContributeRepos(String login);

	public SearchPage<UserInfo> searchReposContributors(String owner, String reposName);

	public SearchPage<UserInfo> searchReposCollaborators(String owner, String reposName);
}
