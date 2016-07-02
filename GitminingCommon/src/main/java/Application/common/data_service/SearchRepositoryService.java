package Application.common.data_service;

import java.util.List;
import java.util.Map;

import Application.common.TitleKey;
import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;


/**
 * 数据接口
 * 与仓库基础信息以及贡献者、合作者和合作信息有关
 */
public interface SearchRepositoryService {

	/**
	 * 根据关键字key获得仓库基本信息的列表
	 * @return List<Repository>
	 */
	public List<BasicRepositoryInfo> searchRepositoryBasicInfo(String key);


	/**
	 * 根据仓库的全名称获得Contributors的列表
	 * @return List<Contributor> 该项目的Contributors的列表
	 */
	public List<BasicUserInfo> searchRepositoryContributorsInfo(String username, String reponame);


	/**
	 * 根据仓库的全名称获得Collaborators的列表
	 * @return List<Collaborators> 该项目的Collaborators的列表
	 */
	public List<BasicUserInfo> searchRepositoryCollaboratorsInfo(String username, String reponame);



	/**
	 * 获得所有的仓库的基本信息
	 * @return  List<BasicRepositoryInfo> 所有的仓库信息的列表
	 */
	public List<BasicRepositoryInfo> getAllRepoInfo();



	/**
	 * 获得所有的仓库的语言统计情况
	 * @return Map<String, Integer>  String代表的是语言 例如Java,C Integer代表行数
	 */
	public Map<String, Integer> getLanguageLine();


	public List<BasicRepositoryInfo> getRepoByTitleKey(TitleKey key);

	public List<BasicRepositoryInfo> getRepoByKeyPhrase(String key);

	public List<BasicRepositoryInfo> getSearchRepoFromGit(List<String> keys);


	public Map<String, Integer> getLanagugeWithByte();

	public Map<Integer, Integer> getStarWithRepo();

	public Map<Integer, Integer> getForkWithRepo();

	public Map<Integer, Integer> getContributorWithRepo();

	public Map<Integer, Integer> getYearWithRepo();

	public Map<Integer, Integer> getStarWithFork();

	public Map<Integer, Integer> getStarWithContributor();

}
