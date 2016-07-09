package Application.data.DAO.sql;

import static Application.data.DAO.sql.DBConfig.ALL_USER_INFO;
import static Application.data.DAO.sql.DBConfig.COLLABORATORS;
import static Application.data.DAO.sql.DBConfig.CONTRIBUTORS;
import static Application.data.DAO.sql.DBConfig.GITMININGUSERS;
import static Application.data.DAO.sql.DBConfig.LANGUELINE;
import static Application.data.DAO.sql.DBConfig.REPOFULLNAME;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.egit.github.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.common.DTO.FollowRepoInfo;
import Application.common.DTO.FollowUserInfo;
import Application.common.DTO.WatchUserInfo;
import Application.common.info.GitMiningUserInfo;


@Component
public class SQLTemplate {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	JdbcTemplate jdbcTemplate;


	public List<WatchUserInfo> getUserWatcherListInfo(String account){
		List<WatchUserInfo> result = new ArrayList<WatchUserInfo>();
		Map<String, List<String>> totalUserWatchRelation = getUserWatching();
		List<String> accountList = totalUserWatchRelation.get(account);

		if(accountList == null || accountList.size() == 0)
			return result;


		for(String s: accountList){

			WatchUserInfo watchUserInfo = getSingleWatchInfo(s);
			result.add(watchUserInfo);
		}

		return result;
	}

	public WatchUserInfo getSingleWatchInfo(String account){

		GitMiningUserInfo userInfo = getUserPassword(account);
		WatchUserInfo watchUserInfo = new WatchUserInfo();
		watchUserInfo.setAccount(userInfo.getAccount());
		watchUserInfo.setAvatarUrl(userInfo.getAvatar_url());
		watchUserInfo.setGitid(userInfo.getGitid());

		List<FollowRepoInfo> followRepoList = getRepoCollection(account);
		if(followRepoList!=null)
			watchUserInfo.setRepoCollection(followRepoList.size());
		else
			watchUserInfo.setRepoCollection(0);

		List<FollowUserInfo> followUserList = getUserCollection(account);
		if(followUserList!=null)
			watchUserInfo.setUserCollection(followUserList.size());
		else
			watchUserInfo.setUserCollection(0);

		return watchUserInfo;
	}

	public Map<String, List<String>> getUserWatching(){
		Map<String, List<String>> userWatchingMap = new TreeMap<String, List<String>>();

		String sql = "select * from watch";
		List<Map<String, Object>> tempMap = jdbcTemplate.queryForList(sql);
		for(Map<String, Object> entry: tempMap){
			String watcherAccount = (String) entry.get("watcherAccount");
			String watchedAccount = (String) entry.get("watchedAccount");

			if(userWatchingMap.containsKey(watcherAccount)){
				List<String> tempValue = userWatchingMap.get(watcherAccount);
				tempValue.add(watchedAccount);
				userWatchingMap.put(watcherAccount, tempValue);
			}else{
				List<String> tempValue = new ArrayList<String>();
				tempValue.add(watchedAccount);
				userWatchingMap.put(watcherAccount, tempValue);
			}
		}

		return userWatchingMap;
	}

	public Map<String, List<String>> getUserCollection(){
		Map<String, List<String>> userCollectionMap = new TreeMap<String, List<String>>();

		String sql = "select * from usercollection";
		List<Map<String, Object>> tempMap = jdbcTemplate.queryForList(sql);
		for(Map<String, Object> entry: tempMap){
			String account = (String) entry.get("account");
			String userLogin = (String) entry.get("userLogin");

			if(userCollectionMap.containsKey(account)){
				List<String> tempValue = userCollectionMap.get(account);
				tempValue.add(userLogin);
				userCollectionMap.put(account, tempValue);
			}else{
				List<String> tempValue = new ArrayList<String>();
				tempValue.add(userLogin);
				userCollectionMap.put(account, tempValue);
			}
		}

		return userCollectionMap;
	}


	public Map<String, List<String>> getRepoCollection(){
		Map<String, List<String>> repoCollectionMap = new TreeMap<String, List<String>>();

		String sql = "select * from repocollection";
		List<Map<String, Object>> tempMap = jdbcTemplate.queryForList(sql);
		for(Map<String, Object> entry: tempMap){
			String account = (String) entry.get("account");
			String userLogin = (String) entry.get("repofullname");

			if(repoCollectionMap.containsKey(account)){
				List<String> tempValue = repoCollectionMap.get(account);
				tempValue.add(userLogin);
				repoCollectionMap.put(account, tempValue);
			}else{
				List<String> tempValue = new ArrayList<String>();
				tempValue.add(userLogin);
				repoCollectionMap.put(account, tempValue);
			}
		}

		return repoCollectionMap;
	}


	public List<String> getAllFisherLogin(){
		List<String> fisherLogin = new ArrayList<String>();
		String sql = "select g.account from "+GITMININGUSERS+" g";
		fisherLogin = jdbcTemplate.query(sql, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet arg0, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				return arg0.getString("account");
			}

		});

		return fisherLogin;
	}


	public List<String> getRepoFullNameList() throws Exception{

		long start_time = System.currentTimeMillis();

		List<String> result = new ArrayList<String>();
		String sql = "select content from "+REPOFULLNAME;

		result = jdbcTemplate.query(sql, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return rs.getString("content");
			}

		});


		long end_time = System.currentTimeMillis();
		System.out.println("This get repo full name sql excution takes "+ (end_time-start_time) +" ms");

		return result;
	}

	public List<String> getUserLoginList() throws Exception{
		long start_time = System.currentTimeMillis();

		List<String> result = new ArrayList<String>();
		String sql = "select content from "+ALL_USER_INFO;

		result = jdbcTemplate.query(sql, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return rs.getString("content");
			}

		});

		long end_time = System.currentTimeMillis();
		System.out.println("This get user login sql excution takes "+ (end_time-start_time) +" ms");

		return result;
	}

	public Map<String, Integer> getLanguageLineNumber() throws Exception{

		Map<String, Integer> result = new LinkedHashMap<String, Integer>();
		String sql = "select l.language, l.lines from "+LANGUELINE+" l";

		List<Map<String, Object>> tempList = jdbcTemplate.queryForList(sql);
		for(Map<String, Object> map: tempList){
			String language = (String) map.get("language");
			Integer lines = (Integer) map.get("lines");
			result.put(language, lines);
		}

		return result;
	}

	public Map<String, List<String>> getContributorRelation() throws Exception{
		Map<String, List<String>> result = new LinkedHashMap<String, List<String>>();

		String sql = "select c.repoFullName,c.userLogin from "+CONTRIBUTORS+" c";

		List<Map<String, Object>> tempList = jdbcTemplate.queryForList(sql);
		for(Map<String, Object> map: tempList){
			String repoFullName = (String) map.get("repoFullName");
			String userLogin = (String) map.get("userLogin");

			if(result.containsKey(repoFullName))
			  result.get(repoFullName).add(userLogin);
			else{
				List<String> temp_list = new ArrayList<String>();
				temp_list.add(userLogin);
				result.put(repoFullName, temp_list);
			}

		}

		return result;
	}

	public Map<String, List<String>> getCollaboratorRelation() throws Exception{
		Map<String, List<String>> result = new LinkedHashMap<String, List<String>>();

		String sql = "select c.repoFullName,c.userLogin from "+ COLLABORATORS+" c";

		List<Map<String, Object>> tempList = jdbcTemplate.queryForList(sql);
		for(Map<String, Object> map: tempList){
			String repoFullName = (String) map.get("repoFullName");
			String userLogin = (String) map.get("userLogin");

			if(result.containsKey(repoFullName))
			  result.get(repoFullName).add(userLogin);
			else{
				List<String> temp_list = new ArrayList<String>();
				temp_list.add(userLogin);
				result.put(repoFullName, temp_list);
			}

		}

		return result;
	}



	public Map<String, List<String>> getReversedContributorRelation() throws Exception{
		Map<String, List<String>> result = new LinkedHashMap<String, List<String>>();

		String sql = "select c.repoFullName,c.userLogin from "+ CONTRIBUTORS+" c";

		List<Map<String, Object>> tempList = jdbcTemplate.queryForList(sql);
		for(Map<String, Object> map: tempList){
			String repoFullName = (String) map.get("repoFullName");
			String userLogin = (String) map.get("userLogin");

			if(result.containsKey(userLogin))
			  result.get(userLogin).add(repoFullName);
			else{
				List<String> temp_list = new ArrayList<String>();
				temp_list.add(repoFullName);
				result.put(userLogin, temp_list);
			}

		}

		return result;
	}


	public List<BasicRepositoryInfo> getAllRepoInfo() throws Exception{
		List<BasicRepositoryInfo> repos = new ArrayList<BasicRepositoryInfo>();
		String sql = "select r.info from "+REPOFULLNAME+" r";

		repos = jdbcTemplate.query(sql, new RowMapper<BasicRepositoryInfo>(){

			@Override
			public BasicRepositoryInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				String json = rs.getString("info");

				try {
					return mapper.readValue(json, BasicRepositoryInfo.class);
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
				return null;
			}

		});
		System.out.println("Get the all Repo Info");

		return repos;
	}


	public List<BasicUserInfo> getAllUserInfo() throws Exception{
		List<BasicUserInfo> users = new ArrayList<BasicUserInfo>();
		String sql = "select u.info from "+ ALL_USER_INFO+" u";

		users = jdbcTemplate.query(sql, new RowMapper<BasicUserInfo>(){

			@Override
			public BasicUserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				String json = rs.getString("info");


				try {
					return mapper.readValue(json, BasicUserInfo.class);
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
				return null;
			}

		});

		System.out.println("Get the all User Info");
		return users;
	}

	public Map<String, Map<String, Integer>> getRepoLanguages() throws Exception{
		Map<String, Map<String, Integer>> result = new LinkedHashMap<String, Map<String, Integer>>();
		String sql = "select r.content, r.languages from "+ REPOFULLNAME+" r";

		List<Map<String, Object>> tempList = jdbcTemplate.queryForList(sql);
		for(Map<String, Object> map: tempList){
			String key = (String) map.get("content");
			String json = (String) map.get("languages");
			Map<String, Integer> value = new LinkedHashMap<String, Integer>();
			if(json!=null && json!= ""){
				try {
					value = mapper.readValue(json, new TypeReference<Map<String, Integer>>(){});
				} catch (Exception e) {
					value = new LinkedHashMap<String, Integer>();
					}
			}
			result.put(key, value);

		}

		return result;
	}

	public GitMiningUserInfo getUserPassword(String account){
		String sql = "select * from "+GITMININGUSERS+" g where g.account = "+"\'"+account + "\'";
		System.out.println(sql);

		GitMiningUserInfo info = null;

		try {
			info = jdbcTemplate.queryForObject(sql, new RowMapper<GitMiningUserInfo>(){

				@Override
				public GitMiningUserInfo mapRow(ResultSet arg0, int arg1) throws SQLException {
					// TODO Auto-generated method stub

					GitMiningUserInfo info = new GitMiningUserInfo();
					info.setAccount(arg0.getString("account"));
					info.setGitid(arg0.getString("gitid"));
					info.setPassword(arg0.getString("password"));
					info.setAvatar_url(arg0.getString("avatarUrl"));

					return info;
				}

			});
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

		return info;

	}

	public void insertOneFisher(String account, String password, String gitID, User userInfo){

		String url = userInfo.getAvatarUrl();
		String sql = "insert into "+GITMININGUSERS
		        +" values(" + "\'"+account+"\'," + "\'"+password + "\'," + "\'" + gitID+"\',"+"\'"+url+"\')";
		System.out.println(jdbcTemplate.update(sql));

	}

	public int insertOneRepoCollection(String account, String repoFullName){

		String query = "select repofullname from repocollection where account = "+"\'"+account +"\'";
		List<String> exsitedRepoCollection = jdbcTemplate.query(query, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return rs.getString("repofullname");
			}

		});

		for(String s: exsitedRepoCollection){
			System.out.println(s);
			if(s.equals(repoFullName))
				return 0;
		}


		String sql = "insert into repocollection values("+"\'"+account+"\',"+"\'"+repoFullName+"\')";
		return jdbcTemplate.update(sql);
	}

	public List<FollowUserInfo> getUserCollection(String account){
		String sql = "select * from usercollection where account = "+"\'"+account+"\'";
		List<FollowUserInfo> resultList = jdbcTemplate.query(sql, new RowMapper<FollowUserInfo>(){

			@Override
			public FollowUserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				String userLogin = rs.getString("userLogin");
				String userName = rs.getString("userName");
				String userType = rs.getString("userType");
				int followers = rs.getInt("followers");
				int publicRepos = rs.getInt("publicRepos");
				String avatarUrl = rs.getString("avatarUrl");
				String createString = rs.getString("create");

				FollowUserInfo info = new FollowUserInfo();
				info.setAvatarUrl(avatarUrl);
				info.setFollowers(followers);
				info.setLogin(userLogin);
				info.setName(userName);
				info.setPublicRepos(publicRepos);
				info.setType(userType);
				info.setCreated(createString);

				return info;
			}

		});

		return resultList;
	}

	public List<FollowRepoInfo> getRepoCollection(String account){
		String sql = "select * from repocollection where account = "+"\'"+account+"\'";
		List<FollowRepoInfo> resultList = jdbcTemplate.query(sql, new RowMapper<FollowRepoInfo>(){

			@Override
			public FollowRepoInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				String repofullname = rs.getString("repofullname");
				String description = rs.getString("description");
				int star = rs.getInt("star");
				int fork = rs.getInt("fork");
				int issue = rs.getInt("issue");
				String mainLanguage = rs.getString("mainLanguage");
				double size = rs.getDouble("size");

				FollowRepoInfo info = new FollowRepoInfo();
				info.setDescription(description);
				info.setFork(fork);
				info.setIssue(issue);
				info.setMainLanguage(mainLanguage);
				info.setRepofullname(repofullname);
				info.setSize(size);
				info.setStar(star);

				return info;
			}

		});

		return resultList;
	}

	public int insertOneUserCollection(String account, FollowUserInfo userInfo){

		String login = userInfo.getLogin();
		String name = userInfo.getName();
		String type = userInfo.getType();
		String avatarUrl = userInfo.getAvatarUrl();
		String createString = userInfo.getCreated();

		int followers = userInfo.getFollowers();
		int publicRepos = userInfo.getPublicRepos();


		String query = "select userLogin from usercollection where account = "+"\'"+account +"\'";
		List<String> exsitedUserCollection = jdbcTemplate.query(query, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return rs.getString("userLogin");
			}
		});


		for(String s: exsitedUserCollection){
			System.out.println(s);
			if(s.equals(login))
				return 0;
		}


		String sql = "insert into usercollection "
				+ "values("+"\'"+account+"\',"+"\'"+login+"\',"+"\'"
				+name+"\',"+"\'"+type+"\',"+"\'"+followers+"\',"+"\'"
				+publicRepos+"\',"+"\'"+avatarUrl+"\',"+"\'"+createString+"\')";

		System.out.println(sql);
		return jdbcTemplate.update(sql);
	}

	public int insertOneRepoCollection(String account, FollowRepoInfo repoInfo){

		String repofullname = repoInfo.getRepofullname();
		String description = repoInfo.getDescription();
		String mainLanguage = repoInfo.getMainLanguage();
		int star = repoInfo.getStar();
		int fork = repoInfo.getFork();
		int issue = repoInfo.getIssue();
		double size = repoInfo.getSize();



		String query = "select repofullname from repocollection where account = "+"\'"+account +"\'";
		List<String> exsitedRepoCollection = jdbcTemplate.query(query, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return rs.getString("repofullname");
			}
		});


		for(String s: exsitedRepoCollection){
			System.out.println(s);
			if(s.equals(repofullname))
				return 0;
		}


		String sql = "insert into repocollection "
				+ "values("+"\'"+account+"\',"+"\'"+repofullname+"\',"+"\'"
				+description+"\',"+"\'"+star+"\',"+"\'"+mainLanguage+"\',"+"\'"
				+fork+"\',"+"\'"+issue+"\',"+"\'"+size+"\')";

		System.out.println(sql);
		return jdbcTemplate.update(sql);
	}


	public int insertOneUserWatching(String watcherAccount, String watchedAccount){

		String query = "select watcherAccount from watch where watcherAccount = " + "\'"+watcherAccount+"\'"
				+" and watchedAccount = " + "\'"+ watchedAccount + "\'";

		List<String> judgement = jdbcTemplate.query(query, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return rs.getString("watcherAccount");
			}

		});

		if(judgement != null && judgement.size()!=0){
			return 0;
		}else{
			String sql = "insert into watch values("+"\'"+watcherAccount+"\',"+"\'"+watchedAccount+"\')";
			return jdbcTemplate.update(sql);
		}


	}

	public String[] getGithubTokens(){
		String query = "select * from tokens";
		List<String> tokens = jdbcTemplate.query(query, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return rs.getString("token");
			}});

		String[] result = new String[tokens.size()];
		for(int i=0; i<tokens.size(); i++)
			result[i] = tokens.get(i);

		return result;

	}

}
