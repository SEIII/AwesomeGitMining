package APITest.SpeedAndSroreTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StatsLanguageLines {

	public static void main(String [] args){
		new StatsLanguageLines().start();
	}

	private void start() {
		// TODO Auto-generated method stub
		File file = new File("localRepos/repoLanguage.txt");
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, String>> languageList = new ArrayList<Map<String, String>>();

		try {
			languageList = mapper.readValue(file, new TypeReference<List<Map<String, String>>>(){});
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


		Map<String, Integer> resultMap = new LinkedHashMap<String, Integer>();

		int count =1;
		for(Map<String, String> languageItem : languageList){




			if(languageItem != null){


			for (Map.Entry<String, String> entry : languageItem.entrySet()) {
				if(entry.getKey()!="fn"){
				   if(resultMap.containsKey(entry.getKey())){
					   int temp = Integer.parseInt(entry.getValue());
					   int result = resultMap.get(entry.getKey());
					   result += temp;
					   resultMap.put(entry.getKey(), result);
				   }else{
					   int temp = Integer.parseInt(entry.getValue());
					   resultMap.put(entry.getKey(), temp);
				   }

				  }

			}

			}

			System.out.println("Finish"+" "+count);
			count++;
		}


		for(Map.Entry<String, Integer> entry : resultMap.entrySet()){
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}


	}




}
