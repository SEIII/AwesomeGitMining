package APITest.APITest;


import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import APITest.AxeContent.RepositoryInfo_AXE;
import APITest.ConnectTest.ProjectRelatedFactory;

public class ReposSpeedTest{

	ProjectRelatedFactory factory;
	
	@Before
	public void SetupContext(){
		factory = new ProjectRelatedFactory();
	}
	
	//@Test
	public void testGetRepos(){
		
		ArrayList<RepositoryInfo_AXE> all_repos = new ArrayList<RepositoryInfo_AXE>();
		
		long start_time = System.currentTimeMillis();
		
		
		for(int i=1;i<=65;i++)
		{
			try {
				all_repos.addAll(factory.getRepoByPage(i));
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
		
		
		long end_time = System.currentTimeMillis();
		
		System.out.println(end_time-start_time);
	}
	
	
	

}
