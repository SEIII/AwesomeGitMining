//package com.example;
//
//
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import Application.business_logic.BlPackageInfo;
//import Application.business_logic.bl.stat.StatInfoFactory;
//import Application.common.StarRelatedItem;
//import Application.common.DTO.BasicRepositoryInfo;
//import Application.common.DTO.BasicUserInfo;
//import Application.common.blService.statService.StatResult;
//import Application.common.blService.statService.StatSingleInfoService;
//import Application.common.blService.statService.StatTotalReposService;
//import Application.common.blService.statService.StatTotalUserService;
//import Application.common.info.ReposInfo;
//import Application.common.info.UserInfo;
//
//import Application.data.DataPackageInfo;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = { BlPackageInfo.class, DataPackageInfo.class })
//public class StatTest {
//
//
//	@Autowired
//	StatTotalReposService totalRepoService;
//
//	@Autowired
//	StatTotalUserService totalUserService;
//
//
//	@Autowired
//	StatSingleInfoService singleInfoService;
//
//	@Autowired
//	StatInfoFactory statInfoFactory;
//
//	@Test
//	public void testStatLanguages(){
//		StatResult<String, Integer> resultStat = totalRepoService.statLanguages();
//		for(StatResult<String, Integer>.Turple turple : resultStat.getTrupleList()){
//			System.out.println(turple.getKey() + ":" + turple.getValue());
//		}
//	}
//
//
//	@Test
//	public void testStatForkDistribution(){
//		StatResult<Integer, Integer> resultStat = totalRepoService.statForkDistri(5);
//		resultStat.sortByKey();
//		for(StatResult<Integer, Integer>.Turple turple : resultStat.getTrupleList()){
//			System.out.println(turple.getKey() + ":" + turple.getValue());
//		}
//	}
//
//	@Test
//	public void testStatRepoCreateTime(){
//		StatResult<Integer, Integer> resultStat = totalRepoService.statCreateTime();
//		resultStat.sortByValue();
//		for(StatResult<Integer, Integer>.Turple turple : resultStat.getTrupleList()){
//			System.out.println(turple.getKey() + ":" + turple.getValue());
//		}
//	}
//
//
//	@Test
//	public void testStatStarDistribution(){
//		StatResult<Integer, Integer> resultStat = totalRepoService.statStarDistri(25);
//		resultStat.sortByKey();
//		for(StatResult<Integer, Integer>.Turple turple : resultStat.getTrupleList()){
//			System.out.println(turple.getKey() + ":" + turple.getValue());
//		}
//	}
//
//
//	@Test
//	public void testStatContributorDistribution(){
//		StatResult<Integer, Integer> resultStat = totalRepoService.statContributorDistri(25);
//		resultStat.sortByKey();
//		for(StatResult<Integer, Integer>.Turple turple : resultStat.getTrupleList()){
//			System.out.println(turple.getKey() + ":" + turple.getValue());
//		}
//	}
//
//
//
//	@Test
//	public void testStatUserCreated(){
//		long start = System.currentTimeMillis();
//		StatResult<Integer, Integer> resultStat = totalUserService.statCreateTime();
//		resultStat.sortByKey();
//		for(StatResult<Integer, Integer>.Turple turple : resultStat.getTrupleList()){
//			System.out.println(turple.getKey() + ":" + turple.getValue());
//		}
//
//		long end = System.currentTimeMillis();
//		System.out.println("delt: " +(end-start));
//	}
//
//
//	@Test
//	public void testStatCreatedRepoDistribution(){
//		StatResult<Integer, Integer> resultStat = totalUserService.statCreateReposDistri(2);
//		resultStat.sortByKey();
//		for(StatResult<Integer, Integer>.Turple turple : resultStat.getTrupleList()){
//			System.out.println(turple.getKey() + ":" + turple.getValue());
//		}
//	}
//
//	@Test
//	public void testStatContributedRepoDistribution(){
//		StatResult<Integer, Integer> resultStat = totalUserService.statContributeReposDistri(1);
//		resultStat.sortByKey();
//		for(StatResult<Integer, Integer>.Turple turple : resultStat.getTrupleList()){
//			System.out.println(turple.getKey() + ":" + turple.getValue());
//		}
//	}
//
//
//
//
//	@Test
//	public void testStatSingleRepo(){
//		BasicRepositoryInfo repoInfo = new BasicRepositoryInfo();
//		repoInfo.setForks_count(31);
//		repoInfo.setCollaboratorCount(10);
//		repoInfo.setContributorCount(20);
//		repoInfo.setOpen_issues_count(26);
//		repoInfo.setSize(2411);
//		repoInfo.setStargazers_count(89);
//
//
//		ReposInfo info = new ReposInfo(repoInfo);
//		info = statInfoFactory.createSingleRepos(info);
//
//		StatResult<String, Double> resultStat = singleInfoService.statSingleRepos(info);
//		List<StatResult<String, Double>.Turple> list = resultStat.getTrupleList();
//		for(StatResult<String, Double>.Turple turple : list){
//			System.out.println(turple.getKey() +":" + turple.getValue());
//		}
//
//	}
//
//
//
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testStatSingleUser(){
//		Date date = new Date(110, 0, 1);
//
//		BasicUserInfo userInfo = new BasicUserInfo();
//		userInfo.setFollowers(13);
//		userInfo.setFollowing(10);
//		userInfo.setContributedRepoNumber(1);
//		userInfo.setCreated_at(date);
//
//		UserInfo info = new UserInfo(userInfo);
//		info = statInfoFactory.createSingleRepos(info);
//
//		StatResult<String, Double> resultStat = singleInfoService.statSingleUser(info);
//		List<StatResult<String, Double>.Turple> list = resultStat.getTrupleList();
//		for(StatResult<String, Double>.Turple turple : list){
//			System.out.println(turple.getKey() +":" + turple.getValue());
//		}
//
//	}
//
//
//	@Test
//	public void testStarRelatedDistri(){
//		StatResult<Integer, Integer> result = totalRepoService.statStarRelatedDistri
//				(StarRelatedItem.fork, 50, 0.01);
//
//		for(StatResult<Integer, Integer>.Turple turple : result.getTrupleList()){
//			System.out.println(turple.getKey() + ":" + turple.getValue());
//		}
//
//	}
//
//
//
//}
