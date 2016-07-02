//package com.example;
//
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import Application.business_logic.BlPackageInfo;
//import Application.business_logic.bl.resultPool.ReposResultPool;
//import Application.business_logic.bl.resultPool.UserResultPool;
//import Application.common.ReposSort;
//import Application.common.TitleKey;
//import Application.common.SearchPage.SearchPage;
//import Application.common.blService.SearchService;
//import Application.common.blService.TitleService;
//import Application.common.info.ReposInfo;
//import Application.common.info.UserInfo;
//import Application.data.DataPackageInfo;
//import Application.data.DAO.sql.SQLTemplate;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = { BlPackageInfo.class, DataPackageInfo.class })
//public class LogicTest {
//
//	@Autowired
//	SearchService blService;
//
//	@Autowired
//	ReposResultPool reposResultPool;
//
//	@Autowired
//	UserResultPool userResultPool;
//
//	@Autowired
//	TitleService titleService;
//
//	@Autowired
//	SQLTemplate template;
//
//
//
//
//	@Test
//	public void testRepos() {
//
//		SearchPage<ReposInfo> reposInfoPage = blService.searchRepos("rubinius");
//		System.out.println("page num:" + reposInfoPage.totalPageNum());
//
//		for (ReposInfo info : reposInfoPage.getCurrentContent()) {
//			System.out.println(info.getFull_name());
//		}
//
//		reposInfoPage.turnPageTo(0);
//		ReposInfo info = reposInfoPage.getCurrentContent().get(0);
//
//		System.out.println("----------Collaborators----------");
//		SearchPage<UserInfo> userpage = info.getCollaborators();
//		for (UserInfo userInfo : userpage.getCurrentContent()) {
//			System.out.println(userInfo.getName());
//		}
//
//		System.out.println(reposResultPool.containsResult("rubinius"));
//
//		reposInfoPage = blService.searchRepos("rubinius", ReposSort.contributors);
//
//		for (ReposInfo aInfo : reposInfoPage.getCurrentContent()) {
//			System.out.println(aInfo.getFull_name() + "  contributors:  " + aInfo.getContributorCount());
//		}
//
//		reposInfoPage = blService.searchRepos("rubinius", ReposSort.star);
//
//		for (ReposInfo aInfo : reposInfoPage.getCurrentContent()) {
//			System.out.println(aInfo.getFull_name() + "  stars:  " + aInfo.getStargazers_count());
//		}
//
//		reposInfoPage = blService.searchRepos("rubinius", ReposSort.fork);
//
//		for (ReposInfo aInfo : reposInfoPage.getCurrentContent()) {
//			System.out.println(aInfo.getFull_name() + "  forks:  " + aInfo.getForks_count());
//		}
//
//	}
//
//	@Test
//	public void testUser() {
//		SearchPage<UserInfo> userInfoPage = blService.searchUser("rubinius");
//		System.out.println(userInfoPage.totalPageNum());
//
//		//userInfoPage.turnPageTo(2);
//		for (UserInfo info : userInfoPage.getCurrentContent()) {
//			System.out.println(info.getLogin());
//		}
//
//		UserInfo info = userInfoPage.getCurrentContent().get(2);
//		System.out.println("--------ContributedReposList----------");
//
//		SearchPage<ReposInfo> reposPage = info.getContributedReposList();
//		for (ReposInfo reposInfo : reposPage.getCurrentContent()) {
//			System.out.println(reposInfo.getFull_name());
//		}
//
//		System.out.println(userResultPool.containsResult("rubinius"));
//
//		System.out.println(info.getLogin());
//	}
//
//	@Test
//	public void testShortKeySearch(){
//		long beginTime = System.currentTimeMillis();
//
//		blService.searchUser("a");
//
//
//		long endTime = System.currentTimeMillis();
//		System.out.println("delta time: " + (endTime - beginTime));
//
//	}
//
////	@Test
////	public void testGetRepoByTitleKey(){
////		List<ReposInfo> repos_language = titleService.getRepoListByKey(TitleKey.languages);
////		List<ReposInfo> repos_popular = titleService.getRepoListByKey(TitleKey.stars);
////		List<UserInfo> user_contributed = titleService.getUserListBykey(TitleKey.contributed);
////
////		for(ReposInfo info: repos_language)
////			System.out.println(info.getFull_name());
////
////		System.out.println();
////
////		for(ReposInfo info: repos_popular)
////			System.out.println(info.getFull_name());
////
////		System.out.println();
////
////		for(UserInfo info: user_contributed)
////			System.out.println(info.getLogin());
////	}
//}
