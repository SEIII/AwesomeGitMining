//package com.example;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import Application.business_logic.BlPackageInfo;
//import Application.business_logic.bl.resultPool.ReposResultPool;
//import Application.business_logic.bl.resultPool.UserResultPool;
//import Application.business_logic.bl.stat.ListGrouping;
//import Application.business_logic.bl.stat.StatInfoFactory;
//import Application.common.ReposSort;
//import Application.common.UserSort;
//import Application.common.DTO.BasicRepositoryInfo;
//import Application.common.DTO.BasicUserInfo;
//import Application.common.blService.statService.StatResult;
//import Application.common.info.ReposInfo;
//import Application.common.info.UserInfo;
//import Application.data.DataPackageInfo;
//import Application.data.DAO.common.MyObjectMapper;
//import Application.data.DAO.impl.FileRepoAndUserDAOImpl;
//import Application.data.DAO.sql.SQLTemplate;
//import KeyPhrase.Document;
//import KeyPhrase.DocumentItem;
//import KeyPhrase.KeyPhrase;
//import KeyPhrase.ResultItem;
//import KeyPhrase.ResultList;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {DataPackageInfo.class, BlPackageInfo.class})
//public class DAOTest {
//
//	@Autowired
//	SQLTemplate template;
//
//	@Autowired
//	MyObjectMapper mapper;
//
//	@Autowired
//	FileRepoAndUserDAOImpl file_dao;
//
//	@Autowired
//	ReposResultPool reposPool;
//
//	@Autowired
//	UserResultPool userPool;
//
//	@Autowired
//	StatInfoFactory factory;
//
//
//
//	@Test
//	public void statRepoLanguageExp()throws Exception{
//
//		int languageCount = 0;
//		List<BasicRepositoryInfo> repos = file_dao.getAllRepoInfo();
//		for(BasicRepositoryInfo repo: repos)
//			languageCount += repo.getLanguageNum();
//		System.out.println((double)languageCount/(double)repos.size());
//	}
//
//	@Test
//	public void statUserExpFollowers() throws Exception{
//		int followers = 0;
//		List<BasicUserInfo> users = template.getAllUserInfo();
//		for(BasicUserInfo info: users)
//			followers += info.getFollowers();
//
//		System.out.println(followers/users.size());
//	}
//
//
//	@Test
//	public void analyzeRepoValue(){
//		List<Integer> open_issue = new ArrayList<Integer>();
//		try {
//			List<BasicRepositoryInfo> repos = template.getAllRepoInfo();
//			for(BasicRepositoryInfo info: repos)
//				open_issue.add(info.getOpen_issues_count());
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		StatResult<Integer, Integer> result = ListGrouping.groupList(open_issue, 5);
//		Map<Integer, Integer> map = result.getMap();
//		for(Map.Entry<Integer, Integer> entry: map.entrySet())
//			System.out.println(entry.getKey()+":"+entry.getValue());
//	}
//
//
//	@Test
//	public void testSearchRepoFromGit(){
//
//		List<String> keys = new ArrayList<String>();
//		keys.add("javascript");
//		keys.add("tool");
//
//		List<BasicRepositoryInfo> repos = file_dao.getSearchRepoFromGit(keys);
//		for(BasicRepositoryInfo info : repos)
//			System.out.println(info.toString());
//	}
//
//
//	@Test
//	public void testCreatedRepoFromGit(){
//		List<BasicRepositoryInfo> repos = file_dao.getCreatedRepoFromGit("8");
//		for(BasicRepositoryInfo info : repos)
//			System.out.println(info.toString());
//	}
//
//
//	@Test
//	public void testAddLines() throws Exception{
//		File f = new File("localRepos/allKeys.txt");
//		File converted = new File("localRepos/convertedKeys.txt");
//
//
//		PrintWriter writer = null;
//		try {
//			writer = new PrintWriter(converted);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//
//		BufferedReader br = new BufferedReader(new FileReader(f));
//		String content = br.readLine();
//		String temp = "";
//		for(int index=0; index<content.length(); index++){
//			if(content.charAt(index) != '\'')
//				temp += content.charAt(index);
//
//		}
//
//
//		String[] list = temp.split(", ");
//		for(String s : list){
//			writer.println(s);
//		}
//
//		writer.flush();
//	}
//
//	@Test
//	public void testGetAllKeyPhrases() throws Exception{
//		File file = new File("localRepos/keyphraseTest.txt");
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		String line = "";
//		String result = "";
//		List<String> allKeyPhrases = new ArrayList<String>();
//		while((line = br.readLine()) != null){
//			String [] tempList = line.split(":");
//			String tempKeyPhrase = tempList[1];
//			result += tempKeyPhrase.toLowerCase() + " ";
//		}
//
//		File allKeys = new File("localRepos/allKeys.txt");
//		PrintWriter writer = null;
//		try {
//			writer = new PrintWriter(allKeys);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		writer.println(result);
//		writer.flush();
//
//
//	}
//
//	@Test
//	public void testKeyPhrase() throws Exception{
//
//		List<BasicRepositoryInfo> repos = template.getAllRepoInfo();
//		int index = 0;
//
//
//		File forkFile = new File("localRepos/keyphraseTest.txt");
//		PrintWriter writer = null;
//		try {
//			writer = new PrintWriter(forkFile);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		for(BasicRepositoryInfo info : repos){
//
//
//
//			String description = info.getDescription();
//			String fullName = info.getFull_name();
//
//
//
//
//			List<DocumentItem> documentItems = new ArrayList<DocumentItem>();
//			if(description != null){
//				DocumentItem item = new DocumentItem(description, index);
//				documentItems.add(item);
//
//				Document document = new Document(documentItems);
//				String text = mapper.writeValueAsString(document);
//
//				String result = new KeyPhrase(text).getJSON();
//				System.out.println("process " + index);
//
//
//				ResultList resultList = mapper.readValue(result, ResultList.class);
//				if(resultList != null){
//
//					List<ResultItem> items = resultList.getDocuments();
//
//					if(items != null && items.size()>0){
//						ResultItem resultItem = items.get(0);
//						List<String> keys = resultItem.getKeyPhrases();
//						for(String key : keys)
//							writer.println(fullName+":"+key);
//					}
//
//				}
//
//
//
//			}
//			index++;
//
//		}
//		writer.flush();
//		}
//
//
//
//
////		int index=0;
////
////
////		List<DocumentItem> documentItems = new ArrayList<DocumentItem>();
////
////		for(;index<50; index++){
////
////			BasicRepositoryInfo info = repos.get(index);
////
////			String description = info.getDescription();
////			if(description != null){
////				DocumentItem item = new DocumentItem(description, index);
////				documentItems.add(item);
////
////			}
////		}
////
////		Document document = new Document(documentItems);
////		String text = mapper.writeValueAsString(document);
////
////		String result = new KeyPhrase(text).getJSON();
////		System.out.println(result);
////		ResultList resultList = mapper.readValue(result, ResultList.class);
////
////		File forkFile = new File("localRepos/keyphraseTest.txt");
////		PrintWriter writer = null;
////		try {
////			writer = new PrintWriter(forkFile);
////		} catch (FileNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		writer.println(result);
////		writer.flush();
////
////	}
//
//
//	@Test
//	public void testRepoListByKey(){
//		List<BasicRepositoryInfo> list = file_dao.getRepoListByKey("api");
//		for(BasicRepositoryInfo info : list)
//			System.out.println(info.toString());
//	}
//
//
////	@Test
////	public void testRepoKeyPhrase() throws Exception{
////
////
////		String containList = "";
////
////		String[] key_phrases = {"activerecord", "api",
////				"app", "cms", "django", "emacs", "framework","interface", "irc",
////				"json", "library", "linux", "mac", "management", "os",
////				"plugin", "rails", "redis", "server", "source", "template",
////				"textmate", "tool", "web", "website", "database"
////				,"javascript","ruby","perl","module","python","git",
////				"html","bindings","client"};
////
////		List<BasicRepositoryInfo> repos = file_dao.getAllRepoInfo();
////		int index = 0;
////		int matched = 0;
////		for(BasicRepositoryInfo info : repos){
////			index++;
////			System.out.print("Process "+index + " repo    ");
////			String description = info.getDescription();
////
////
////			if(description!=null){
////
////				int i=0;
////				for(; i<key_phrases.length; i++){
////					if(description.toLowerCase().contains(key_phrases[i])){
////						template.updateRepoKeyPhrase(info.getFull_name(), key_phrases[i]);
////						System.out.println(info.getFull_name()+" Match" + " and it's key is " + key_phrases[i]);
////						matched++;
////						break;
////					}
////				}
////				if(i == key_phrases.length){
////					template.updateRepoKeyPhrase(info.getFull_name(), "other");
////					System.out.println("NO match");
////					containList += info.getDescription();
////
////				}
////
////			}else{
////				System.out.println("Null Description");
////			}
////
////
////		}
////		System.out.println("Total Mathched : " + matched);
////	}
//
//
//	@Test
//	public void testSQLRepoLanguages() throws Exception{
//
//		Map<String, Map<String, Integer>> result = template.getRepoLanguages();
//		for(Map.Entry<String, Map<String, Integer>> entry: result.entrySet()){
//			System.out.println(entry.getKey()+" "+entry.getValue().toString());
//		}
//	}
//
//	@Test
//	public void testSQLUserInfo(){
//		try {
//
//
//				long start = System.currentTimeMillis();
//				List<BasicUserInfo> list = template.getAllUserInfo();
//				long end = System.currentTimeMillis();
//				System.out.println(end-start);
//
////
////			for(BasicUserInfo info: list)
////				System.out.println(info.toString());
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void testSQLTemplate(){
//		try {
//
//			long start = System.currentTimeMillis();
//			List<BasicRepositoryInfo> list = template.getAllRepoInfo();
//			long end = System.currentTimeMillis();
//			System.out.println(end-start);
//
//
//			for(BasicRepositoryInfo info: list)
//				System.out.println(info.toString());
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void testUserEvent() {
//
//		for(int i=0; i<1 ;i++){
//			long start_time = System.currentTimeMillis();
//			List<Integer[]> list = file_dao.getUserEvent("rubinius");
//
//
//			long end_time = System.currentTimeMillis();
//			System.out.println();
//			System.out.println(end_time - start_time);
//	}
//	}
//	@Test
//	public void testUserMainLanguage() {
//		for (int i = 0; i < 10; i++) {
//			long start_time = System.currentTimeMillis();
//			String language = file_dao.getUserMainLanguage("b");
//			System.out.println(language);
//			long end_time = System.currentTimeMillis();
//			System.out.println(end_time - start_time);
//		}
//	}
//
//	@Test
//	public void testGetSingleRepo() {
//
//		long start_time = System.currentTimeMillis();
//		List<String> list = file_dao.getRepoFullNameList();
//		long end_time = System.currentTimeMillis();
//		System.out.println(end_time - start_time);
//
//	}
//
//	@Test
//	public void testGetRepo() {
//
//		for (int i = 0; i < 10; i++) {
//			long start_time = System.currentTimeMillis();
//			BasicRepositoryInfo repositoryInfo = file_dao.getSingleBasicRepoInfo("rubinius", "rubinius");
//			long end_time = System.currentTimeMillis();
//			System.out.println(end_time - start_time);
//		}
//	}
//
//	@Test
//	public void testUserCreatedRepo() {
//		for (int i = 0; i < 10; i++) {
//			long start_time = System.currentTimeMillis();
//			file_dao.getUserCreatedRepoList("rubinius");
//			long end_time = System.currentTimeMillis();
//			System.out.println(end_time - start_time);
//		}
//	}
//
//	@Test
//	public void testSomeRepoInfo() {
//		List<String> fullNameList = new ArrayList<String>();
//		fullNameList.add("mojombo/grit");
//		fullNameList.add("mojombo/god");
//		fullNameList.add("rubinius/rubinius");
//		for (int i = 0; i < 10; i++) {
//			long start_time = System.currentTimeMillis();
//			file_dao.getSomeBasicRepoInfo(fullNameList);
//			long end_time = System.currentTimeMillis();
//			System.out.println(end_time - start_time);
//
//		}
//
//	}
//
//	@Test
//	public void testGetContributorCount() {
//		int num = 0;
//		for (int i = 0; i < 10; i++) {
//			long start_time = System.currentTimeMillis();
//			num = file_dao.getContributorCount("rubinius", "rubinius");
//			long end_time = System.currentTimeMillis();
//			System.out.println(end_time - start_time);
//		}
//		System.out.println(num);
//	}
//
//	@Test
//	public void testContributedRepo() {
//		List<BasicRepositoryInfo> list = new ArrayList<BasicRepositoryInfo>();
//		for (int i = 0; i < 10; i++) {
//			long start_time = System.currentTimeMillis();
//			list = file_dao.getUserContributedRepoList("rubinius");
//			long end_time = System.currentTimeMillis();
//			System.out.println(end_time - start_time);
//		}
//
//		for (BasicRepositoryInfo info : list)
//			System.out.println(info.toString());
//	}
//
//	@Test
//	public void testGetUserNameList() {
//		long start_time = System.currentTimeMillis();
//		List<String> userNameList = new ArrayList<String>();
//		userNameList = file_dao.getUserList();
//		long end_time = System.currentTimeMillis();
//		System.out.println(end_time - start_time);
//		System.out.println(userNameList.size());
//	}
//
//	@Test
//	public void testGetRepoContributors() {
//
//		for (int i = 0; i < 10; i++) {
//			long start_time = System.currentTimeMillis();
//
//			file_dao.getRepositoryContributorList("rubinius", "rubinius");
//			long end_time = System.currentTimeMillis();
//			System.out.println(end_time - start_time);
//		}
//	}
//
//	@Test
//	public void testGetRepoCollaborators() {
//		for (int i = 0; i < 10; i++) {
//			long start_time = System.currentTimeMillis();
//
//			file_dao.getRepositoryCollaboratorList("rubinius", "rubinius");
//			long end_time = System.currentTimeMillis();
//			System.out.println(end_time - start_time);
//		}
//
//	}
//
//	@Test
//	public void testGetSomeUserInfo() {
//		List<String> userNameList = new ArrayList<String>();
//		userNameList.add("0");
//		userNameList.add("8");
//
//		for (int i = 0; i < 10; i++) {
//			long start = System.currentTimeMillis();
//			List<BasicUserInfo> list = file_dao.getSomeUserInfo(userNameList);
//
//			long end = System.currentTimeMillis();
//			System.out.println(end - start);
//		}
//	}
//
//	@Test
//	public void testGetIcon() {
//
//		for (int i = 0; i < 10; i++) {
//			long start_time = System.currentTimeMillis();
//			file_dao.getUserIcon("smt116");
//			long end_time = System.currentTimeMillis();
//			System.out.println("delt: " + (end_time - start_time));
//		}
//	}
//
//	@Test
//	public void testGetAllRepoInfo() {
//
//		List<BasicRepositoryInfo> repoList = file_dao.getAllRepoInfo();
//		for (BasicRepositoryInfo info : repoList)
//			System.out.println(info.getLanguageMap().size());
//	}
//
//	@Test
//	public void testGetAllUserInfo() {
//
//		for (int i = 0; i < 3; i++) {
//			long start = System.currentTimeMillis();
//			List<BasicUserInfo> userList = file_dao.getAllUserInfo();
//			long end = System.currentTimeMillis();
//			System.out.println(end - start);
//		}
//	}
//
//	@Test
//	public void testGetAllLanguageLineNumber() {
//
//		Map<String, Integer> languageLineNumber = file_dao.getAllLanguageLine();
//		for (Map.Entry<String, Integer> entry : languageLineNumber.entrySet())
//			System.out.println(entry.getKey() + ":" + entry.getValue());
//	}
//
//
//	@Test
//	public void caculateMostLanguageRepository(){
//		List<BasicRepositoryInfo> all_repo = file_dao.getAllRepoInfo();
//		List<ReposInfo> info_list = factory.createReposList(all_repo);
//
//		reposPool.addResult("all", info_list);
//		List<ReposInfo> sorted_list = reposPool.sort(ReposSort.language_num, info_list);
//
//		List<String> fullNameList = new ArrayList<String>();
//
//		for(int i=0; i<10; i++){
//
//			ReposInfo info = sorted_list.get(i);
//
//			System.out.println(info.getFull_name()+":"+info.getLanguageNum());
//
//			fullNameList.add(info.getFull_name());
//		}
//
//
//		url_to_file(fullNameList, "repository", "language");
//
//
//		File file = new File("localRepos/most_language_repository.txt");
//		List<BasicRepositoryInfo> temp_list = new ArrayList<BasicRepositoryInfo>();
//
//		try {
//			temp_list = mapper.readValue(file, new TypeReference<List<BasicRepositoryInfo>>() {});
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(BasicRepositoryInfo info: temp_list){
//			System.out.println(info.toString());
//		}
//
//	}
//
//	@Test
//	public void caculateMostContributedUsers(){
//		List<BasicUserInfo> all_user = file_dao.getAllUserInfo();
//		List<UserInfo> info_list = factory.createUserList(all_user);
//
//		userPool.addResult("all", info_list);
//		List<UserInfo> sorted_list = userPool.sort(UserSort.contirbuted, info_list);
//
//		List<String> login_list = new ArrayList<String>();
//
//		for(int i=0; i<10; i++){
//
//			UserInfo info = sorted_list.get(i);
//			login_list.add(info.getLogin());
//
//		}
//
//
//
//		url_to_file(login_list, "user", "contributed");
//
//
//		File file = new File("localRepos/most_contributed_user.txt");
//		List<BasicUserInfo> temp_list = new ArrayList<BasicUserInfo>();
//
//		try {
//			temp_list = mapper.readValue(file, new TypeReference<List<BasicUserInfo>>() {});
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(BasicUserInfo info: temp_list){
//			System.out.println(info.toString());
//		}
//
//	}
//
//	@Test
//	public void caculateMostPopularRepos(){
//
//		List<BasicRepositoryInfo> all_repos = file_dao.getAllRepoInfo();
//		List<ReposInfo> info_list = factory.createReposList(all_repos);
//
//		reposPool.addResult("all", info_list);
//		List<ReposInfo> sorted_list = reposPool.sort(ReposSort.star, info_list);
//
//		List<String> fullname_list = new ArrayList<String>();
//
//		for(int i=0; i<10; i++){
//
//			ReposInfo info = sorted_list.get(i);
//			fullname_list.add(info.getFull_name());
//			System.out.println(info.getFull_name() + ":" + info.getStargazers_count());
//		}
//
//
//		url_to_file(fullname_list, "repository", "popular");
//
//
//		File file = new File("localRepos/most_popular_repository.txt");
//		List<BasicRepositoryInfo> temp_list = new ArrayList<BasicRepositoryInfo>();
//
//		try {
//			temp_list = mapper.readValue(file, new TypeReference<List<BasicRepositoryInfo>>() {});
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(BasicRepositoryInfo info: temp_list){
//			System.out.println(info.toString());
//		}
//
//	}
//
//
//	private String execURL(String urlstring)
//	{
//		String string = "";
//
//
//		try {
//
//			URL url = new URL(urlstring);
//            BufferedReader reader = new BufferedReader(
//            		new InputStreamReader(url.openStream()));
//			string = reader.readLine();
//
//		} catch (IOException e) {
//			Exception exception = new Exception("网络连接错误");
//			exception.printStackTrace();
//		}
//
//
//		/*正则表达式 用于处理Web API返回类型的校验*/
//		if(!string.matches("\\{.*\\}")&&!string.matches("\\[.*\\]")){
//			string = "\"" + string + "\"";
//		}
//
//
//		return string;
//	}
//
//
//
//	public void url_to_file(List<String> list, String type, String key){
//
//
//		StringBuilder builder = new StringBuilder("[");
//
//		for (int i = 0; i<10; i++) {
//			String url = "http://gitmining.net/api/"+type+"/" + list.get(i);
//			String cmd = execURL(url);
//
//			if(i>0)
//				builder.append(",");
//
//			builder.append(cmd);
//
//
//			System.out.println("has completed "+ i + " th");
//		}
//		builder.append("]");
//
//
//
//
//		File file = new File("localRepos/most_"+key+"_"+type+".txt");
//		if(!file.exists()){
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//
//
//		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//			writer.write(builder.toString());
//			writer.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//
//
////	@Test
////	public void testGetMostPopularRepo(){
////		List<BasicRepositoryInfo> repos = file_dao.getMostPopularRepos();
////
////		for(BasicRepositoryInfo info: repos)
////			System.out.println(info.toString());
////	}
////
////	@Test
////	public void testGetMostLanguageRepo(){
////		List<BasicRepositoryInfo> repos = file_dao.getMostLanguageRepos();
////
////		for(BasicRepositoryInfo info: repos)
////			System.out.println(info.toString());
////	}
////
////	@Test
////	public void testGetMostContributedUser(){
////		List<BasicUserInfo> users = file_dao.getMostContributedUsers();
////
////		for(BasicUserInfo info: users)
////			System.out.println(info.toString());
////	}
//}
