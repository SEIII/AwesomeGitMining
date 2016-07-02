package APITest.SpeedAndSroreTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import APITest.ConnectTest.ProjectRelatedFactory;


public class StoreRepoFullName {
	public static void main(String[] args) {
		LocalFactory factory = new LocalFactory();

		String repoFullName = factory.getAllReposString();
		File file = new File("localRepos/repoFullName.txt");
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
			
			long start_time = System.currentTimeMillis();
			
				String url = prefix + "/api/repository/names";
				String cmd = execURL(url);
				
			long end_time = System.currentTimeMillis();
			System.out.println(end_time - start_time);
			return cmd;
		}
	}
}
