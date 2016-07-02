package APITest.SpeedAndSroreTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Application.common.DTO.BasicUserInfo;


public class StoreRepoOwner {

	
	
	ObjectMapper mapper = new ObjectMapper();
	
	
	public static void main(String[] args){
		new StoreRepoOwner().test2();
	}
	
	
	
	public void test(){
		
		
		File ownerListFile = new File("localRepos/repoOwnerInfo.txt");
		Set<String> a = new HashSet<String>();
		
		List<BasicUserInfo> ownerList = new ArrayList<BasicUserInfo>();
		
		try {
			 ownerList = mapper.readValue(ownerListFile, new TypeReference<List<BasicUserInfo>>() {});
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
	
		
		
		for(BasicUserInfo info: ownerList)
			a.add(info.getLogin());
	
		StringBuilder builder = new StringBuilder();
		for(String s: a)
			builder.append(s + "\r\n");
		
		System.out.println(a.size());
		System.out.println(a.contains("rubinius"));
		System.out.println(builder.toString());
		
	}
	
	
	public void test2(){
		File file = new File("localRepos/allUserInfo.txt");
		try {
			List<BasicUserInfo> list = mapper.readValue(file, new TypeReference<List<BasicUserInfo>>() {});
		   	for(BasicUserInfo info:list)
		   		System.out.println(info.toString());
			System.out.println(list.size());
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
	
}
