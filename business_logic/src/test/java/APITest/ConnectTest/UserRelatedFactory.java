package APITest.ConnectTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import APITest.AxeContent.User;

public class UserRelatedFactory {

	
	/*GitMining API前缀*/
	String prefix = "http://gitmining.net";
	
	/*GitHub API前缀*/
	String prefix_2 = "http://api.github.com";

	/*类似于适配的工具*/
	ObjectMapper mapper = new ObjectMapper();
	
	
	/*
	 * 返回某个User的详细信息
	 * 
	 * @return User类  具体信息请参照User类
	 */
	public User getUserInfo(String user) throws JsonParseException, JsonMappingException, IOException
	{
		String url = prefix + "/api/user/" + user ;
		String cmd = execURL(url);
		
		User userInfo = mapper.readValue(cmd, User.class);
		return userInfo;
	}
	
	
	
	
	/*
	 * 执行URL 返回调用api中所输出的内容
	 * 
	 * 辅助方法
	 */
	private String execURL(String urlstring)
	{
		
		String string = "";
		
		try {

			URL url = new URL(urlstring);
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			string = reader.readLine();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*正则表达式 用于处理Web API返回类型的校验*/
		if(!string.matches("\\{.*\\}")&&!string.matches("\\[.*\\]")){
			string = "\"" + string + "\"";
		}
		
		
		return string;
	}
	
	
	
	
	public void testMethod() throws JsonParseException, JsonMappingException, IOException
	{
		User userInfo = getUserInfo("kubo");
		System.out.println(userInfo.toString());
	}
	
	
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException
	{
		new UserRelatedFactory().testMethod();
	}
}
