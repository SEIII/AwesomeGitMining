package APITest.SpeedAndSroreTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperConfig;

import APITest.ConnectTest.ProjectRelatedFactory;
import Application.common.DTO.BasicUserInfo;


public class StoreUserInfo {
	
	public static void main(String[] args) {
		LocalFactory factory = new LocalFactory();

		String allUserInfo = factory.readUserBasicInfo();
//		File file = new File("localRepos/allUserInfo.txt");
//		if(!file.exists()){
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
		long start_time = System.currentTimeMillis();
//		
//		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//			writer.write(allUserInfo);
//			writer.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		ObjectMapper mapper = new ObjectMapper();
//		
//		try {
//			List<Contributor> userInfoList = mapper.readValue(file, new TypeReference<List<Contributor>>() {});
//		
//			for(Contributor info : userInfoList)
//				System.out.println(info.toString());
//		
//		
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


		long end_time = System.currentTimeMillis();

		System.out.println(end_time - start_time);
	}

	
	
	public static class LocalFactory extends ProjectRelatedFactory {
		public String getAllReposString() {
			
			
			List<String> user_List = new ArrayList<String>();
			File file = new File("localRepos/users.txt");
			String content = "";
			
		
			
			try {
				
				BufferedReader br = new BufferedReader(new FileReader(file));
				while((content = br.readLine()) != null){
					user_List.add(content);
				}
				
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			long start_time = System.currentTimeMillis();
     		StringBuilder builder = new StringBuilder("[");	
			for (int i =46500; i < 47000; i++) {

				String url = prefix + "/api/user/" + user_List.get(i);
				String cmd = execURL(url);
				
				
				if(i>46500){
					builder.append(",");
				}
				builder.append(cmd);
				
				System.out.println("已完成第 "+(i+1)+" 个");
			}
			builder.append("]");

			
			long end_time = System.currentTimeMillis();
			System.out.println(end_time - start_time);
			
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				List<BasicUserInfo> userInfoList = mapper.readValue(builder.toString(), new TypeReference<List<BasicUserInfo>>() {});
			
				for(BasicUserInfo info : userInfoList)
					System.out.println(info.toString());
			
			
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
			
			
			
			
			return builder.toString();
     		
		}
		
		
		
//		public void modify(){
//			File file = new File("localRepos/users.txt");
//			
//		}
		
		
		
		
		public String readUserBasicInfo(){
			
			List<String> user_List = new ArrayList<String>();
			File file = new File("localRepos/users.txt");
			String content = "";
			
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				
				BufferedReader br = new BufferedReader(new FileReader(file));
				while((content = br.readLine()) != null){
					user_List.add(content);
				}
				
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			long start_time = System.currentTimeMillis();

			for (int i =46877; i < 46878; i++) {

				String url = prefix + "/api/user/" + user_List.get(i);
				String cmd = execURL(url);
				
				
				try {
					BasicUserInfo basicUserInfo = mapper.readValue(cmd, BasicUserInfo.class);
					System.out.println(basicUserInfo.toString());
				} catch (IOException e) {
					System.out.println(user_List.get(i));
					System.out.println(cmd);
					e.printStackTrace();
				}
				
				System.out.println("已完成第 "+(i+1)+" 个");
			}


			
			long end_time = System.currentTimeMillis();
			System.out.println(end_time - start_time);
			
			
			return null;
			
			
		}
	}
}
