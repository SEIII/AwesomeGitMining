package Application.data.DAO.ResultPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import Application.data.DAO.sql.SQLTemplate;

@Component
@Scope("prototype")
public class LineFileResult {

	Map<String, List<String>> relationTable;
	Map<String, List<String>> relationTable_reverse;

	@Autowired
	SQLTemplate sqltemplate;

	public void init(String filePath)
	{
		if(relationTable == null)
			relationTable = new HashMap<String, List<String>>();



		if(filePath.equals("contributors")){
			try {
				relationTable = sqltemplate.getContributorRelation();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(filePath.equals("collaborators")){
			try {
				relationTable = sqltemplate.getCollaboratorRelation();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.gc();
	}



	public void init_2(String filePath){
		if(relationTable_reverse == null)
			relationTable_reverse = new TreeMap<String, List<String>>();

		if(filePath.equals("contributors")){
			try {
				relationTable_reverse = sqltemplate.getReversedContributorRelation();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}


	public int getRelatedNum(String key, String pathName){
		if(relationTable == null)
			init(pathName);

		List<String> valueList = relationTable.get(key);
		if(valueList != null)
		   return valueList.size();
		else
			return 0;
	}

	public int getRelatedNum_2(String key, String pathName){
		if(relationTable_reverse == null || relationTable_reverse.isEmpty()) {
		    init_2(pathName);
		}


		List<String> valueList = relationTable_reverse.get(key);
		if(valueList != null) {
		    return valueList.size();
		}else {
			return 0;
		}

	}


	public List<String> getValue(String key, String pathName){
		if(relationTable == null)
			init(pathName);


			if(relationTable.get(key) != null)
			  return relationTable.get(key);
			else{
				List<String> temp = new ArrayList<String>();
				return temp;
			}


	}


	public List<String> getKeyList(String value, String pathName){
		if(relationTable == null)
			init(pathName);
		List<String> keyList = new ArrayList<String>();
		for(Map.Entry<String, List<String>> entry: relationTable.entrySet())
		{
			if(entry.getValue().contains(value))
				keyList.add(entry.getKey());
		}


		return keyList;
	}
}
