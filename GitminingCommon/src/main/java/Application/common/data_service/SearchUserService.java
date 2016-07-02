package Application.common.data_service;

import java.awt.Image;
import java.util.List;
import java.util.Map;

import Application.common.TitleKey;
import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;



/**
 * @author lenovo
 * 数据接口 与用户基本信息以及创建项目列表、参与项目列表有关
 *
 *
 *
 */
public interface SearchUserService {

	/**
	 * 根据关键字key来获得用户简略信息列表
	 * @return List<BasicUserInfo>
	 */
	public List<BasicUserInfo> searchUsers(String key);

	/**
	 * 根据用户的登陆名称来获得其创建过的项目列表
	 * @returns List<BasicRepositoryInfo>
	 */
	public List<BasicRepositoryInfo> searchCreatedRepos(String username);


	/**
	 * 根据用户的登陆名称来获得其参与过的项目列表
	 * @return List<BasicRepositoryInfo>
	 */
	public List<BasicRepositoryInfo> searchContributedRepos(String username);



	/**
	 * 根据用户的登陆名来获取git头像
	 * @param username
	 * @return
	 */
	public Image getUserIcon(String username);



	/**
	 * 获得所有的用户的基本信息
	 * @return List<BasicUserInfo> 所有人员基本信息的列表
	 */
	public List<BasicUserInfo> getAllUserInfo();


	/**
	 * 获得用户最近7天的提交等event次数 按时间先后依次放在List中
	 * @param login
	 * @return
	 */
	public List<Integer[]> getUserEvent(String login);


	public List<BasicUserInfo> getUserByTitleKey(TitleKey key);

	public List<BasicRepositoryInfo> getCreatedRepoFromGit(String login);

	public Map<Integer, Integer> getCreateWithUser();

	public Map<Integer, Integer> getYearWithUser();
}
