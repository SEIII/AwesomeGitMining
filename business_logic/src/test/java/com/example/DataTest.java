//package com.example;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import Application.common.DTO.BasicRepositoryInfo;
//import Application.common.DTO.BasicUserInfo;
//import Application.data.DataPackageInfo;
//import Application.data.DAO.impl.FileRepoAndUserDAOImpl;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DataPackageInfo.class)
//
//public class DataTest {
//	@Autowired
//	FileRepoAndUserDAOImpl dao;
//
//
//
//
//	@Test
//	public void TestGetRepoFullNameList(){
//		long begintime=System.currentTimeMillis();
//		List<String> list=dao.getRepoFullNameList();
//		long endtime=System.currentTimeMillis();
//		System.out.println(endtime-begintime);
//
//	}
//
//	@Test
//	public void TestGetUserList(){
//		long begintime=System.currentTimeMillis();
//		List<String> list=dao.getUserList();
//		long endtime=System.currentTimeMillis();
//		System.out.println(endtime-begintime);
//	}
//
//
//	@Test
//	public void TestGetSingleBasicRepoInfo(){
//		long begintime=System.currentTimeMillis();
//		BasicRepositoryInfo info=dao.getSingleBasicRepoInfo("technoweenie","attachment_fu");
//		System.out.println(info.toString());
//		long endtime=System.currentTimeMillis();
//		System.out.println(endtime-begintime);
//	}
//
//
//
//	@Test
//	public void testUserCreatedRepo(){
//
//			long start_time = System.currentTimeMillis();
//			List<BasicRepositoryInfo> info = dao.getUserCreatedRepoList("rubinius");
//			long end_time = System.currentTimeMillis();
//			System.out.println(end_time - start_time);
//
//
//			for(BasicRepositoryInfo b:info)
//				System.out.println(b.toString());
//	}
//
//
//
//	@Test
//	public void TestGetContributorCount(){
//		int num = 0;
//		for(int i=0;i< 10;i++){
//		long begintime=System.currentTimeMillis();
//		 num=dao.getContributorCount("technoweenie","attachment_fu");
//		System.out.println(num);
//		long endtime=System.currentTimeMillis();
//		System.out.println(endtime-begintime);
//		}
//		System.out.println(num);
//	}
//
//
//
//
//
//
//
//
//
//	@Test
//	public void TestGetSomeBasicRepoInfo() {
//		List<String> list = new ArrayList<String>();
//		list.add("mojombo/god");
//		list.add("mojombo/grit");
//		list.add("rubinius/rubinius");
//	    List<BasicRepositoryInfo> list1 = new ArrayList<>();
//		for(int i=0;i<10;i++)
//		{
//	    long start_time = System.currentTimeMillis();
//	    list1=dao.getSomeBasicRepoInfo(list);
//
//		long end_time = System.currentTimeMillis();
//		System.out.println(end_time - start_time);
//
//		}
//
//		for(BasicRepositoryInfo info:list1)
//			System.out.println(info.toString());
//	}
//
//	@Test
//	public void TestGetRepositoryContributorList(){
//		for(int j=0;j< 10;j++){
//		long begintime=System.currentTimeMillis();
//		List<BasicUserInfo> list=dao.getRepositoryContributorList("technoweenie","attachment_fu");
//		for(int i=0;i<list.size();i++){
//			System.out.print(list.get(i).toString());
//		}
//		long endtime=System.currentTimeMillis();
//		System.out.println(endtime-begintime);
//		}
//
//	}
//
//
//
//	@Test
//	public void TestGetRepositoryCollaboratorList(){
//		for(int j=0;j< 10;j++){
//		long begintime=System.currentTimeMillis();
//		List<BasicUserInfo> list=dao.getRepositoryCollaboratorList("rubinius","rubinius");
//		for(int i=0;i<list.size();i++){
//			System.out.print(list.get(i).toString());
//		}
//		long endtime=System.currentTimeMillis();
//		System.out.println(endtime-begintime);
//		}
//
//	}
//
//
//
//	@Test
//	public void TestGetUserCreatedRepoList(){
//		long begintime=System.currentTimeMillis();
//		List<BasicRepositoryInfo> list=dao.getUserCreatedRepoList("rubinius");
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i).toString());
//		}
//		long endtime=System.currentTimeMillis();
//		System.out.println(endtime-begintime);
//	}
//
//
//	@Test
//	public void TestGetUserContributedRepoList(){
//		List<BasicRepositoryInfo> list = new ArrayList<BasicRepositoryInfo>();
//		for(int i=0;i< 10;i++){
//			long start_time = System.currentTimeMillis();
//			 list =dao.getUserContributedRepoList("0");
//			 long end_time = System.currentTimeMillis();
//			System.out.println(end_time - start_time);
//			}
//
//		for(BasicRepositoryInfo info: list)
//			System.out.println(info.toString());
//	}
//
//
//	@Test
//	public void TestGetUserIcon(){
//		for(int i=0;i< 10;i++){
//		long start_time = System.currentTimeMillis();
//		dao.getUserIcon("technoweenie");
//		long end_time = System.currentTimeMillis();
//		System.out.println(end_time-start_time);
//	}
//	}
//
//
//
//}
