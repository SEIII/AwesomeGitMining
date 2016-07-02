package APITest.SpeedAndSroreTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import APITest.ConnectTest.ProjectRelatedFactory;

public class StoreRepoOwnerInfo {

	public static void main(String[] args) {
		LocalFactory factory = new LocalFactory();

		String repoFullName = factory.getAllReposString();
		
		
		
		File file = new File("localRepos/repoOwnerInfo.txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		long start_time = System.currentTimeMillis();
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(repoFullName);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		long end_time = System.currentTimeMillis();

		System.out.println(end_time - start_time);
	}

	public static class LocalFactory extends ProjectRelatedFactory {
		public String getAllReposString() {
			
			
			File file = new File("localRepos/repoOwnerList.txt");
			List<String> ownerList = new ArrayList<String>();
			String line = "";
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				while((line = br.readLine()) != null)
					ownerList.add(line);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			StringBuilder builder = new StringBuilder("[");	
			for (int i =0; i < ownerList.size(); i++) {

				String url = prefix + "/api/user/" + ownerList.get(i);
				String cmd = execURL(url);
				
				
				if(i>0){
					builder.append(",");
				}
				builder.append(cmd);
				
				System.out.println("已完成第 "+(i+1)+" 个");
			}
			builder.append("]");
			
			
			
			
			
			
			return builder.toString();
			
		}
		
		
		
	}
			
}
