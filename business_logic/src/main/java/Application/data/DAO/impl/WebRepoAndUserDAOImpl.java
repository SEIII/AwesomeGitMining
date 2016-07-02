package Application.data.DAO.impl;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Application.common.DAOService.RepoAndUserDAOService;
import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;



/**
 * @author lenovo
 *
 */
//@Component
public class WebRepoAndUserDAOImpl implements RepoAndUserDAOService {

	/*GitMining API 的网页名前缀*/
	private String prefix = "http://gitmining.net";

	/*转换对象的工具*/
	@Autowired
	ObjectMapper mapper = new ObjectMapper();




	/**
	 * 获取所有仓库的全名称
	 * @return 仓库全名称的列表  List<String>  格式为 "rubinius/rubinius"
	 */
	public List<String> getRepoFullNameList()
	{

        String cmd = execURL(prefix+"/api/repository/names");
		List<String> repoFullNameList = new ArrayList<String>();


		if(mapper!=null){
			try {
				repoFullNameList = mapper.readValue(cmd, new TypeReference<List<String>>(){});
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		return repoFullNameList;
	}




	/**
	 * 获取用户列表
	 * 读取文件
	 * @return List<String>  用户登录名
	 */
	public List<String> getUserList()
	{

		List<String> userList = new ArrayList<String>();
		File file = new File("localRepos/users.txt");

		String content = "";

		try {
			BufferedReader br = new BufferedReader((new FileReader(file)));

			while((content = br.readLine()) != null)
			{
				userList.add(content);
			}

			br.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}






	/**
	 * 获取贡献者数量
	 * 从网页api获取
	 * @param username  用户名
	 * @param reponame  仓库名
	 * @return  贡献者数量
	 */
	public int getContributorCount(String username, String reponame)
	{
		String url = prefix + "/api/repository/" + username + "/" +
	                       reponame +"/contributors/login";

		String cmd = execURL(url);


		List<String> contributorList = new ArrayList<String>();

		try {
		       contributorList = mapper.readValue(cmd,
		    		             new TypeReference<List<String>>() {});


		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return contributorList.size();

	}


	/**
	 * 获得单个项目的基本信息
	 * @param username 项目所有者登录名
	 * @param reponame 项目名称
	 * @return BasicRepository 基本项目信息
	 */
	public BasicRepositoryInfo getSingleBasicRepoInfo(String username, String reponame)
	{
		String url = prefix + "/api/repository/" + username + "/" + reponame;
		String cmd = execURL(url);

		BasicRepositoryInfo repositoryInfo = null;

		try {
			  repositoryInfo = mapper.readValue(cmd, BasicRepositoryInfo.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return repositoryInfo;

	}






	/**
	 * 获得项目的所有fork信息
	 * @param username
	 * @param reponame
	 * @return List<BasicRepository>
	 */
	public List<BasicRepositoryInfo> getRepositoryForkList(String username, String reponame)
	{
		String url = prefix + "/api/repository/" + username + "/" + reponame + "/forks";
		String cmd = execURL(url);

		List<BasicRepositoryInfo> fork_list = new ArrayList<BasicRepositoryInfo>();

		try {
			fork_list = mapper.readValue(cmd, new TypeReference<List<BasicRepositoryInfo>>() {});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fork_list;
	}



	/**
	 * 获得某项目的贡献者列表
	 * @param username
	 * @param reponame
	 * @return
	 */
	public List<BasicUserInfo> getRepositoryContributorList(String username, String reponame)
	{
		String url = prefix + "/api/repository/" + username + "/" + reponame + "/contributors";
		String cmd = execURL(url);

		List<BasicUserInfo> contributor_list = new ArrayList<BasicUserInfo>();

		try {
			contributor_list = mapper.readValue(cmd, new TypeReference<List<BasicUserInfo>>() {});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contributor_list;
	}






	/**
	 * 获得项目所有合作者的列表
	 * @param username
	 * @param reponame
	 * @return
	 */
	public List<BasicUserInfo> getRepositoryCollaboratorList(String username, String reponame)
	{
		String url = prefix + "/api/repository/" + username + "/" + reponame + "/collaborators";
		String cmd = execURL(url);

		List<BasicUserInfo> collaborator_list = new ArrayList<BasicUserInfo>();

		try {
			collaborator_list = mapper.readValue(cmd, new TypeReference<List<BasicUserInfo>>() {});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return collaborator_list;
	}



	/**
	 * 获得用户所有的创建项目
	 * @param username
	 * @return
	 */
	public List<BasicRepositoryInfo> getUserCreatedRepoList(String username)
	{
		List<BasicRepositoryInfo> repositoryList = new ArrayList<BasicRepositoryInfo>();

		String url = prefix + "/api/repository/names";
		String cmd = execURL(url);


		try {
			List<String> repository_fullname = mapper.readValue(cmd,
					new TypeReference<List<String>>() {});


			for(String s: repository_fullname)
			{

				String repo_username = s.split("/")[0];
			    String repo_reponame = s.split("/")[1];

				if(repo_username.equals(username))
				{
					BasicRepositoryInfo repositoryInfo =
							getSingleBasicRepoInfo(repo_username, repo_reponame);

					repositoryList.add(repositoryInfo);
				}
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return repositoryList;

	}





	public List<BasicRepositoryInfo> getUserContributedRepoList(String username)
	{
		File file = new File("localRepos/contributors.txt");
//		List<String> contributedRepositoryFullName = new ArrayList<String>();
		List<BasicRepositoryInfo> contributedRepositoryList = new ArrayList<BasicRepositoryInfo>();
		String line = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			long before = System.currentTimeMillis();
			while((line = br.readLine()) != null)
			{
				String[] contributor_info = line.split(":");
				String repoFullname = contributor_info[0];
				String contributed_username = contributor_info[1];
				if(contributed_username.equals(username))
				{

					String [] repo_info = repoFullname.split("/");
					String repo_username = repo_info[0];
					String reponame = repo_info[1];
					contributedRepositoryList.add(getSingleBasicRepoInfo(repo_username, reponame));

			    }
			}

			long after = System.currentTimeMillis();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}






		return contributedRepositoryList;
	}





	/**
	 * 内部方法 用于执行url指令
	 *
	 * @param urlstring
	 * @return json格式的String
	 *
	 *
	 */
	private String execURL(String urlstring)
	{
		String string = "";


		try {

			URL url = new URL(urlstring);
            BufferedReader reader = new BufferedReader(
            		new InputStreamReader(url.openStream()));
			string = reader.readLine();

		} catch (IOException e) {
			Exception exception = new Exception("网络连接错误");
			exception.printStackTrace();
		}


		/*正则表达式 用于处理Web API返回类型的校验*/
		if(!string.matches("\\{.*\\}")&&!string.matches("\\[.*\\]")){
			string = "\"" + string + "\"";
		}


		return string;
	}




	@Override
	public List<BasicRepositoryInfo> getSomeBasicRepoInfo(List<String> fullNameList) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Image getUserIcon(String username) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public int getCollaboratorCount(String username, String reponame) {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public List<BasicRepositoryInfo> getAllRepoInfo() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<BasicUserInfo> getAllUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Map<String, Integer> getAllLanguageLine() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public int getContributedRepoNum(String login) {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int getCreatedRepoNum(String login) {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public String getUserMainLanguage(String login) {
		// TODO Auto-generated method stub
		return "";
	}




	@Override
	public List<Integer[]> getUserEvent(String login) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public BasicUserInfo getSingleBasicUserInfo(String login) {
        // TODO Auto-generated method stub
        return null;
    }




	@Override
	public List<BasicRepositoryInfo> getRepoListByKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<BasicRepositoryInfo> getCreatedRepoFromGit(String login) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<BasicRepositoryInfo> getSearchRepoFromGit(List<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}



//	public void test()
//	{
//
//		List<BasicRepositoryInfo> list = getRepositoryForkList("rubinius", "rubinius");
//		System.out.println(list.size());
//
//	}
//
//
//	public static void main(String [] args)
//	{
//		new RepoAndUserDAO().test();
//	}
}
