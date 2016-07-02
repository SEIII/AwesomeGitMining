package APITest.SpeedAndSroreTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import APITest.AxeContent.RepositoryInfo_AXE;

public class FileToRepos {
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("localRepos/allRepos.txt");
		long beginTime = System.currentTimeMillis();
		try {
			List<RepositoryInfo_AXE> reposList = 
					mapper.readValue(file, new TypeReference<List<RepositoryInfo_AXE>>() {});
			System.out.println(reposList.size());
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
		
		long endTime = System.currentTimeMillis();
		System.out.println("delta time: " + (endTime - beginTime));
		
	}
}
