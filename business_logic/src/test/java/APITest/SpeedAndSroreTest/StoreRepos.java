package APITest.SpeedAndSroreTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import APITest.ConnectTest.ProjectRelatedFactory;

public class StoreRepos {
	public static void main(String[] args) {
		LocalFactory factory = new LocalFactory();

		String allReposString = factory.getAllReposString();
		File file = new File("localRepos/allRepos.txt");
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
			writer.write(allReposString);
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

			StringBuilder builder = new StringBuilder("[");

			for (int page = 1; page <= 65; page++) {
				String url = prefix + "/api/repository?page=" + page;
				String cmd = execURL(url);
				cmd = cmd.substring(1, cmd.length()-1);
				
				if(page>1){
					builder.append(",");
				}
				builder.append(cmd);
				
				if (page % 5 == 0) {
					System.out.println("网页已下载"+(page * 100.0 / 65) + "%");
				}
				
			}
			builder.append("]");
			System.out.println(builder.length());
			long end_time = System.currentTimeMillis();
			System.out.println(end_time - start_time);
			return builder.toString();
		}
	}
}
