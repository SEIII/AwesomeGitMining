//package APITest.SpeedAndSroreTest;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
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
//import Application.data.DataPackageInfo;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DataPackageInfo.class)
//public class StorUserLanguage {
//
//	@Autowired
//	FileRepoAndUserDAOImpl file_dao;
//
//
//	public static void main(String[] args){
//		new StorUserLanguage().start();
//	}
//
//	@Test
//	public void start() {
//		// TODO Auto-generated method stub
//		File f = new File("localRepos/users.txt");
//		List<String> userList = new ArrayList<String>();
//		StringBuilder sb = new StringBuilder();
//		String line = "";
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(f));
//
//			while((line = br.readLine()) != null)
//				userList.add(line);
//
//			br.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		int i = 0;
//		for(;i<userList.size(); i++){
//			String login = userList.get(i);
//			List<BasicRepositoryInfo> contributedRepoList = file_dao.getUserContributedRepoList(login);
//			List<BasicRepositoryInfo> createdRepoList = file_dao.getUserCreatedRepoList(login);
//
//			String language = "Unknown";
//
//			if(contributedRepoList.size() != 0)
//				language = contributedRepoList.get(0).getLanguage();
//			if(createdRepoList.size() != 0)
//				language = createdRepoList.get(0).getLanguage();
//
//			sb.append(login + ":" + language +"\r\n");
//			System.out.println("have completed " + i);
//		}
//
//		File f2 = new File("localRepos/userLanguage.txt");
//
//		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(f2));
//			writer.write(sb.toString());
//			writer.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//
//	}
//}
