package Application.data.data_impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

@Component
public class StatDataFactory {

	Map<String, Map<Integer, Integer>> totalStatData;

	public Map<Integer, Integer> getStatData(String type){

		if(totalStatData == null){
			totalStatData = new TreeMap<String, Map<Integer, Integer>>();
		}


		if(totalStatData.containsKey(type)){
			return totalStatData.get(type);
		}

		else{
			File f = new File("statData/"+type+".txt");
			Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

			String line = "";
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));

				while((line = br.readLine()) != null){
					String[] tempList = line.split(":");
					Integer key = Integer.parseInt(tempList[0]);
					Integer value = Integer.parseInt(tempList[1]);
					map.put(key, value);

				}

				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			totalStatData.put(type, map);

			return map;
		}





	}
}
