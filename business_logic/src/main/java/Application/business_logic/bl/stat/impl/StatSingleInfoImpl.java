//package Application.business_logic.bl.stat.impl;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import Application.business_logic.bl.stat.ListScoring;
//import Application.business_logic.bl.stat.StatInfoFactory;
//import Application.common.DTO.BasicRepositoryInfo;
//import Application.common.DTO.BasicUserInfo;
//import Application.common.blService.statService.StatResult;
//import Application.common.blService.statService.StatSingleInfoService;
//import Application.common.data_service.SearchRepositoryService;
//import Application.common.data_service.SearchUserService;
//import Application.common.info.ReposInfo;
//import Application.common.info.UserInfo;
//
//@Component
//public class StatSingleInfoImpl implements StatSingleInfoService{
//
//	@Autowired
//	StatInfoFactory statInfoFactory;
//
//	@Autowired
//	SearchRepositoryService searchRepositoryService;
//
//	@Autowired
//	SearchUserService searchUserService;
//
//	List<ReposInfo> reposList;
//	List<UserInfo> userList;
//	List<List<Integer>> repoStatInfoList;
//	List<List<Integer>> userStatInfoList;
//	List<String> repoStatTypeList;
//	List<String> userStatTypeList;
//
//
//	public void initRepo(){
//
//		reposList = new ArrayList<ReposInfo>();
//		repoStatInfoList = new ArrayList<List<Integer>>();
//		repoStatTypeList = new ArrayList<String>();
//
//		List<BasicRepositoryInfo> basicInfoList = searchRepositoryService.getAllRepoInfo();
//		reposList = statInfoFactory.createReposList(basicInfoList);
//
//		List<Integer> forkList = new ArrayList<Integer>();
//		List<Integer> contributorList = new ArrayList<Integer>();
//		List<Integer> collaboratorList =  new ArrayList<Integer>();
//		List<Integer> open_issueList = new ArrayList<Integer>();
//		List<Integer> starList = new ArrayList<Integer>();
//		List<Integer> sizeList = new ArrayList<Integer>();
//
//		for(ReposInfo info : reposList){
//			forkList.add(info.getForks_count());
//			contributorList.add(info.getContributorCount());
//			collaboratorList.add(info.getCollaboratorCount());
//			open_issueList.add(info.getOpen_issues_count());
//			starList.add(info.getStargazers_count());
//			sizeList.add(info.getSize());
//		}
//
//		repoStatInfoList.add(forkList);
//		repoStatInfoList.add(contributorList);
//		repoStatInfoList.add(collaboratorList);
//		repoStatInfoList.add(open_issueList);
//		repoStatInfoList.add(starList);
//		repoStatInfoList.add(sizeList);
//
//		repoStatTypeList.add("Fork");
//		repoStatTypeList.add("Contributor");
//		repoStatTypeList.add("Collaborator");
//		repoStatTypeList.add("Open_issue");
//		repoStatTypeList.add("Star");
//		repoStatTypeList.add("Size");
//	}
//
//
//	public void initRepoJudgement(){
//		if(reposList == null)
//			initRepo();
//	}
//
//	@SuppressWarnings("deprecation")
//	public void initUser(){
//		userList = new ArrayList<UserInfo>();
//		userStatInfoList = new ArrayList<List<Integer>>();
//		userStatTypeList = new ArrayList<String>();
//
//		List<BasicUserInfo> basicInfoList = searchUserService.getAllUserInfo();
//		userList = statInfoFactory.createUserList(basicInfoList);
//
//
//		List<Integer> followerList = new ArrayList<Integer>();
//		List<Integer> followingList = new ArrayList<Integer>();
//		List<Integer> contributedList = new ArrayList<Integer>();
//		List<Integer> yearAgeList = new ArrayList<Integer>();
//
//
//		for(UserInfo info : userList){
//			followerList.add(info.getFollowers());
//			contributedList.add(info.getContributedRepoNum());
//			followingList.add(info.getFollowing());
//			yearAgeList.add(116-(info.getCreated_at().getYear()));
//		}
//
//
//		userStatInfoList.add(followerList);
//		userStatInfoList.add(contributedList);
//		userStatInfoList.add(followingList);
//		userStatInfoList.add(yearAgeList);
//
//		userStatTypeList.add("Follower");
//		userStatTypeList.add("Con.");
//		userStatTypeList.add("Following");
//		userStatTypeList.add("Age");
//		userStatTypeList.add("Acti.");
//		userStatTypeList.add("Famous");
//
//	}
//
//	public void initUserJudgement(){
//		if(userList == null)
//			initUser();
//	}
//
//
//
//
//
//	@Override
//	public StatResult<String, Double> statSingleRepos(ReposInfo reposInfo) {
//
//		initRepoJudgement();
//		StatResult<String, Double> resultStat = new StatResult<String, Double>();
//
//		ReposInfo info = statInfoFactory.createSingleRepos(reposInfo);
//		List<Integer> singleInfoList = new ArrayList<Integer>();
//		List<Double> resultList = new ArrayList<Double>();
//
//		singleInfoList.add(info.getForks_count());
//		singleInfoList.add(info.getContributorCount());
//		singleInfoList.add(info.getCollaboratorCount());
//		singleInfoList.add(info.getOpen_issues_count());
//		singleInfoList.add(info.getStargazers_count());
//		singleInfoList.add(info.getSize());
//
//		for(int i=0;i<6;i++){
//
//			List<Integer> list = repoStatInfoList.get(i);
//			Integer target = singleInfoList.get(i);
//			double result = ListScoring.scoreByList(list, target);
//
//			resultList.add(result);
//		}
//
//
//		for(int i=0;i<6;i++){
//			resultStat.put(repoStatTypeList.get(i), resultList.get(i));
//		}
//
//
//		return resultStat;
//	}
//
//	@SuppressWarnings("deprecation")
//	@Override
//	public StatResult<String, Double> statSingleUser(UserInfo userInfo) {
//
//		initUserJudgement();
//		StatResult<String, Double> resultStat = new StatResult<String, Double>();
//
//		UserInfo info = statInfoFactory.createSingleRepos(userInfo);
//		List<Double> resultList = new ArrayList<Double>();
//		List<Integer> singleInfoList = new ArrayList<Integer>();
//
//		singleInfoList.add(info.getFollowers());
//		singleInfoList.add(info.getContributedRepoNum());
//		singleInfoList.add(info.getFollowing());
//		singleInfoList.add(116-info.getCreated_at().getYear());
//
//
//		for(int i=0;i<4;i++){
//			resultList.add(ListScoring.scoreByList(userStatInfoList.get(i), singleInfoList.get(i)));
//		}
//
//		DecimalFormat df = new DecimalFormat("0.0");
//
//
//		double activity = 0.8*resultList.get(2) + 0.2*resultList.get(3);
//		double famous = 0.2*resultList.get(0) + 0.6*resultList.get(1) + 0.2*resultList.get(3);
//
//		activity = Double.parseDouble(df.format(activity));
//		famous = Double.parseDouble(df.format(famous));
//
//		resultList.add(activity);
//		resultList.add(famous);
//
//
//
//		for(int i=0;i<6;i++){
//			resultStat.put(userStatTypeList.get(i), resultList.get(i));
//		}
//
//
//		return resultStat;
//	}
//
//
//	@Override
//	public List<Integer[]> statSingleUserWeekEvent(String login) {
//		return searchUserService.getUserEvent(login);
//	}
//
//}
