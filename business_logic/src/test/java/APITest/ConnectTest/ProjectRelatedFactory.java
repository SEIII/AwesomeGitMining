package APITest.ConnectTest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import APITest.AxeContent.Branch;
import APITest.AxeContent.Contributor;
import APITest.AxeContent.RepositoryInfo_AXE;


/*
 * 处理有关Repository的方法啊
 * 方法中的参数多为  (String owner, String reponame)
 * 分别代表了  项目所有者的名字 和 项目的名称
 */
public class ProjectRelatedFactory {

	/*GitMining API前缀*/
	protected String prefix = "http://gitmining.net";
	int a = 0;
	int b=0;
	/*GitHub API前缀*/
	String prefix_2 = "http://api.github.com";

	/*类似于适配的工具*/
	protected ObjectMapper mapper = new ObjectMapper();


	/*
	 * 获得所有仓库(登录名/项目名)
	 */
	public List<String> getAllRepo(){
		String cmd = execURL(prefix+"/api/repository/names");


		List<String> repository_name_owner = null;


		try {
			 repository_name_owner = mapper.readValue(cmd, new TypeReference<List<String>>(){});
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

		return repository_name_owner;
	}



	/*
	 * 获得项目所使用的所有语言
	 *
	 * @return  Map<String, String>  前者代表语言名称  后者代表行数
	 */
	public Map<String, String> getRepositoryLanguage(String owner, String repository) throws JsonParseException, JsonMappingException, IOException
	{
		String url = prefix + "/api/repository/"+owner+"/"+repository+"/languages";
		String cmd = execURL(url);


		Map<String, String> repository_language_list =
				mapper.readValue(cmd, new TypeReference<Map<String,String>>() {});

		return repository_language_list;
	}





	/*
	 * 获得单个项目的项目所有者登录ID
	 *
	 * @return  string
	 */
	public String getRepoOwnerId(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "owner_id");
	}






	/*
	 * 获得单个项目的所有者用户类型
	 *
	 * @return  string
	 */
	public String getRepoOwnerType(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "owner_type");
	}





	/*
	 * 获得单个项目的项目主页URL
	 *
	 * @return  string
	 */
	public String getRepoHomeURL(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "html_url");
	}






	/*
	 * 获得单个项目的项目描述
	 *
	 * @return  string
	 */
	public String getRepoDescription(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "description");
	}




	/*
	 *
	 * 获得项目创建时间
	 *
	 * @return  string
	 */
	public String getRepoCreatedAt(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "created_at");
	}





	/*
	 * 获得项目更新时间
	 *
	 * @return  string
	 */
	public String getRepoUpdatedAt(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "updated_at");
	}





	/*
	 * 获得项目最后一次push时间
	 *
	 * @return  string
	 */
	public String getRepoPushedAt(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "pushed_at");
	}





	/*
	 * 获得项目大小
	 *
	 * @return  string
	 */
	public String getRepoSize(String owner, String reponame) throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "size");
	}




	/*
	 * 获得项目点赞人数
	 *
	 * @return  string
	 */
	public String getRepoStarGazersCount(String owner, String reponame) throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "stargazers_count");
	}






	/*
	 * 获得项目主语言
	 *
	 * @return  string
	 */
	public String getRepoMainLanguage(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "language");
	}





	/*
	 * 获得项目被fork次数
	 *
	 * @return  string
	 */
	public String getRepoByFork(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "forks");
	}






	/*
	 * 获得项目被open的issue数
	 *
	 * @return  string
	 */
	public String getRepoOpenIssue(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		return getRepositoryInfo(owner, reponame, "open_issues");
	}






	/*
	 * 获得项目的关注数
	 *
	 * @return  string
	 */
	public String getRepoSubscribedCount(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{
		return getRepositoryInfo(owner, reponame, "subscribes_count");
	}






	/*
	 * 获得参与特定项目的贡献者的登录名列表
	 *
	 * @return List<String> 返回一个String类型的列表 储存该个项目的所有贡献者登录名
	 *
	 *
	 */
	public List<String> getContributorsLogin(String owner, String reponame) throws JsonParseException, JsonMappingException, IOException{

		String url = prefix + "/api/repository/"+owner+"/"+reponame+"/contributors/login";
		String cmd = execURL(url);

		List<String> contributors = mapper.readValue(cmd, new TypeReference<List<String>>() {});

		return contributors;


	}



	/*
	 * 获得参与特定项目的贡献者的信息
	 *
	 * @return List<Contributor>   Contributor类具体请参照Contributor类
	 */
	public List<Contributor> getRepoContributors(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		String url = prefix + "/api/repository/"+owner+"/"+reponame+"/contributors";
		String cmd = execURL(url);

		List<Contributor> contributors = mapper.readValue(cmd, new TypeReference<List<Contributor>>() {});

		return contributors;
	}


	/*
	 * 获取仓库对象
	 *
	 * 仓库对象的具体信息请参见RepositoryInfo 包括了仓库的基本信息
	 */
	public RepositoryInfo_AXE getRepoInfo(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		String url = prefix + "/api/repository/"+owner+"/"+reponame;
		String cmd = execURL(url);

		RepositoryInfo_AXE repositoryInfo = new RepositoryInfo_AXE();
		try {
			 repositoryInfo = mapper.readValue(cmd, RepositoryInfo_AXE.class);

		} catch (Exception e) {
			//System.err.println("Error");
		}
		return repositoryInfo;
	}



	/*
	 * 获得项目的所有版本
	 *
	 * @return List<Branch> 返回Branch类型   Branch具体属性参见Branch类
	 *
	 */
	public List<Branch> getRepoBranches(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException{

		String url = prefix + "/api/repository/"+owner+"/"+reponame+"/branches";
		String cmd = execURL(url);

		List<Branch> branchs = mapper.readValue(cmd, new TypeReference<List<Branch>>() {});
		return branchs;

	}


	/*
	 * 获得某一页的仓库列表
	 */
	public List<RepositoryInfo_AXE> getRepoByPage(int page)throws JsonParseException, JsonMappingException, IOException{

		String url = prefix +"/api/repository?page="+page;
		String cmd = execURL(url);

		List<RepositoryInfo_AXE> repositoryInfos = mapper.readValue(cmd, new TypeReference<List<RepositoryInfo_AXE>>() {});
		return repositoryInfos;

	}


	/*
	 * 获取某项目fork信息
	 *
	 * @return  返回fork过该项目的项目全称的List<String>
	 */
	public List<String> getRepoForkNames(String owner, String reponame) throws JsonParseException, JsonMappingException, IOException{

		String url = prefix +"/api/repository/"+owner+"/"+reponame+"/forks/names";
		String cmd = execURL(url);

		List<String> fork_names = mapper.readValue(cmd, new TypeReference<List<String>>() {});
		return fork_names;

	}




	/*
	 * 获取某项目的Collaborators的信息
	 *
	 * @return 返回该项目所有Collaborators的登陆名的List<String>
	 *
	 */
	public List<String> getRepoCollaboratorsLogin(String owner, String reponame)throws JsonParseException, JsonMappingException, IOException {

		String url = prefix +"/api/repository/"+owner+"/"+reponame+"/collaborators/login";
		String cmd = execURL(url);

		List<String> collaborators_names = mapper.readValue(cmd, new TypeReference<List<String>>() {});
		return collaborators_names;




	}


	/*
	 * 获取单个项目的某项内容
	 *
	 * 辅助方法
	 */
	private String getRepositoryInfo(String owner, String reponame, String item) throws JsonParseException, JsonMappingException, IOException {

		String url = prefix + "/api/repository/" + owner + "/"+ reponame +"/item/"+item;
		String cmd = execURL(url);

		String item_component = mapper.readValue(cmd, String.class);

	    return item_component;
	}







	/*
	 * 执行URL 返回调用api中所输出的内容
	 *
	 * 辅助方法
	 */
	protected String execURL(String urlstring)
	{

		String string = "";

		try {

			URL url = new URL(urlstring);
			URLConnection connection = url.openConnection();
//			connection.setReadTimeout(100);
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			string = reader.readLine();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}


		/*正则表达式 用于处理Web API返回类型的校验*/
		if(!string.matches("\\{.*\\}")&&!string.matches("\\[.*\\]")){
			string = "\"" + string + "\"";
		}


		return string;
	}








	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		new ProjectRelatedFactory().test();
	}



    /*
     * 测试驱动
     */
	private void test() throws JsonParseException, JsonMappingException, IOException {

		execURL(prefix + "/api/repository/");

//		ArrayList<RepositoryInfo> all_repos = new ArrayList<RepositoryInfo>();
//
//		long start_time = System.currentTimeMillis();
//
//
//		for(int i=1;i<=65;i++)
//		{
//			all_repos.addAll(getRepoByPage(i));
//		}
//
//
//		long end_time = System.currentTimeMillis();
//
//
//
//		System.out.println(end_time-start_time);
	}


}
