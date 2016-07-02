package APITest.SpeedAndSroreTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import APITest.ConnectTest.ProjectRelatedFactory;

public class StoreForkTable {

	public static void main(String[] args) {
		LocalFactory factory = new LocalFactory();
		List<String> reposName = factory.getAllRepo();
		//ObjectMapper mapper = new ObjectMapper();
		File forkFile = new File("localRepos/forks.txt");
		File errorForkFile = new File("localRepos/errorForks.txt");
		PrintWriter writer = null;
		PrintWriter errorWriter = null;
		try {
			writer = new PrintWriter(forkFile);
			errorWriter = new PrintWriter(errorForkFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<10;i++){
		//for(int i=0;i<reposName.size();i++){
			String name = reposName.get(i);
			System.out.println(name);
			List<String> forkNames = factory.getForkNames(name);
		
			if(forkNames == null){
				errorWriter.println(name);
				System.out.println(name+"出现错误");
				continue;
			}
			
			
			for(String fork:forkNames){
				writer.println(name+":"+fork);
			}
			System.out.println("完成"+i+"个");
		}
		

		
	}
	
	
	public static class LocalFactory extends ProjectRelatedFactory {
		public List<String> getForkNames(String fullName){
			String url = prefix + "/api/repository/"+fullName+"/forks/names";
			String result = execURL(url);
			System.out.println(result);
			
			if(result==null){
				return null;
			}
			
			try {
				List<String> forks = mapper.readValue(result,new TypeReference<List<String>>() {});
				return forks;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
}
