package Application.common.DAOService;

import java.awt.Image;
import java.util.List;
import java.util.Map;

import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;

public interface RepoAndUserDAOService {

	public List<String> getRepoFullNameList();

	public List<String> getUserList();

	public int getContributorCount(String username, String reponame);

	public int getCollaboratorCount(String username, String reponame);

	public int getContributedRepoNum(String login);

	public int getCreatedRepoNum(String login);

	public String getUserMainLanguage(String login);

	public BasicRepositoryInfo getSingleBasicRepoInfo(String username, String reponame);

	public BasicUserInfo getSingleBasicUserInfo(String login);

	public List<BasicRepositoryInfo> getRepositoryForkList(String username, String reponame);

	public List<BasicUserInfo> getRepositoryContributorList(String username, String reponame);

	public List<BasicUserInfo> getRepositoryCollaboratorList(String username, String reponame);

	public List<BasicRepositoryInfo> getUserCreatedRepoList(String username);

	public List<BasicRepositoryInfo> getUserContributedRepoList(String username);

	public List<BasicRepositoryInfo> getSomeBasicRepoInfo(List<String> fullNameList);

	public List<BasicRepositoryInfo> getAllRepoInfo();

	public List<BasicUserInfo> getAllUserInfo();

	public Image getUserIcon(String username);

	public Map<String, Integer> getAllLanguageLine();

	public List<Integer[]> getUserEvent(String login);

//	public List<BasicRepositoryInfo> getMostPopularRepos();
//
//	public List<BasicRepositoryInfo> getMostLanguageRepos();
//
//	public List<BasicUserInfo> getMostContributedUsers();

	public List<BasicRepositoryInfo> getRepoListByKey(String key);

	public List<BasicRepositoryInfo> getCreatedRepoFromGit(String login);

	public List<BasicRepositoryInfo> getSearchRepoFromGit(List<String> keys);

}
