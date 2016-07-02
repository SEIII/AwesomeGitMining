package APITest.SpeedAndSroreTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import APITest.AxeContent.RepositoryInfo_AXE;
import APITest.ConnectTest.ProjectRelatedFactory;

public class ReposSpeedTest{

	static ProjectRelatedFactory factory = new ProjectRelatedFactory();
	static Vector<List<RepositoryInfo_AXE>> infosList = new Vector<List<RepositoryInfo_AXE>>(65);
	

	public static void main(String[] args) {
		
		ArrayList<RepositoryInfo_AXE> all_repos = new ArrayList<RepositoryInfo_AXE>();
		
		long start_time = System.currentTimeMillis();
		
		
		for(int i=1;i<=65;i++)
		{
			System.out.println(i+"页");
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
	
	public void multiThreadRead(){
		
		for(int i=1;i<=65;i++){
			//开启一个
			new ReadPageThread(i).start();
		}
		
		synchronized (infosList) {
			while(infosList.size()<65){
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static class ReadPageThread extends Thread{
		int page;
		public ReadPageThread(int page){
			this.page = page;
		}
		
		public void run(){
			try {
				List<RepositoryInfo_AXE> infos = factory.getRepoByPage(page);
				infosList.add(infos);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
