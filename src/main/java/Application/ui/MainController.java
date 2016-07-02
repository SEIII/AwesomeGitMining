//package Application.ui;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import Application.business_logic.bl.ReposInfo;
//import Application.business_logic.bl.UserInfo;
//import Application.business_logic.bl_service.ClassifyService;
//import Application.business_logic.bl_service.SearchService;
//import Application.common.ReposSort;
//import Application.common.SearchPage;
//
//@Component
//public class MainController {
//
//	@Autowired
//	MainPanel mp;
//	@Autowired
//	SearchService searchService;
//	@Autowired
//	ClassifyService classifyService;
//
//	private String currentMessage;
//
//	public void searchRepos(String message) {
//		this.currentMessage = message;
//		SearchPage<ReposInfo> pageInfo = searchService.searchRepos(message);
//		mp.creatReposListPanel(pageInfo);
//		mp.createRepoScanInfoPanel(message);
//	}
//
//	public void searchUsers(String message) {
//
//		this.currentMessage = message;
//		SearchPage<UserInfo> pageInfo = searchService.searchUser(message);
//		mp.creatUserListPanel(pageInfo);
////		mp.createUserScanInfoPanel(message);
//
//	}
//
//	public void showUserDetail(UserInfo userInfo) {
//
//		mp.createUserInfoPanel(userInfo);
//	}
//
//	public void showReposDetail(ReposInfo reposInfo) {
//
//	}
//
//	public SearchPage<ReposInfo> getSortByGeneral(){
//
//		return searchService.searchRepos(currentMessage);
//
//	}
//
//	public SearchPage<ReposInfo> getSortByContributor(){
//
//		return searchService.searchRepos(currentMessage, ReposSort.contributors);
//
//	}
//
//	public SearchPage<ReposInfo> getSortByStar(){
//
//		return searchService.searchRepos(currentMessage, ReposSort.star);
//
//	}
//
//	public SearchPage<ReposInfo> getSortByFork(){
//
//		return searchService.searchRepos(currentMessage, ReposSort.fork);
//
//	}
//
//}
