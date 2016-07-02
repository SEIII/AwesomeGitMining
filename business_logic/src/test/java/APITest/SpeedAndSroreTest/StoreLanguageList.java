package APITest.SpeedAndSroreTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import APITest.ConnectTest.ProjectRelatedFactory;

public class StoreLanguageList {
	public static void main(String[] args) {
		LocalFactory factory = new LocalFactory();

		ObjectMapper mapper = new ObjectMapper();
		String repoFullName = factory.getAllReposString();
		File file = new File("localRepos/repoLanguage.txt");
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



		try {
			Map<String, Map<String, Integer>> heh = mapper.readValue(file, new TypeReference<Map<String, Map<String, Integer>>>(){});
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

	}



	public static class LocalFactory extends ProjectRelatedFactory {
		public String getAllReposString() {

			File repoNameListFile = new File("localRepos/repoFullName.txt");

			List<String> repoNameList = new ArrayList<String>();
			try {
				repoNameList	= mapper.readValue(repoNameListFile, new TypeReference<List<String>>(){});
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

			System.out.println(repoNameList.size());


			StringBuilder sb = new StringBuilder();
			sb.append("{");

			for(int i=0; i<repoNameList.size(); i++){





				String url = prefix + "/api/repository/" + repoNameList.get(i) + "/languages";
				String cmd = execURL(url);


				if(cmd != null){
				String[] temp = cmd.split(",\"fn\":");
				cmd = temp[1].split("}")[0] + ":"+temp[0] + "}";

				if(i>0){
					sb.append(",");
				}
				sb.append(cmd);
				System.out.println("Have completed "+(i+1));
			}

			}
			sb.append("}");

			return sb.toString();
		}
	}
}
