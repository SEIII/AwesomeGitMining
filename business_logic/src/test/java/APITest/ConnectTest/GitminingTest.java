package APITest.ConnectTest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GitminingTest {
	
	String prefix = "http://gitmining.net";
	String testMethod = "/api/repository/names";
	
	public void go(){
		try {
			String urlString = prefix+testMethod;
			System.out.println(urlString);
			URL url = new URL(urlString);
			
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String string=reader.readLine();
//			System.out.println(string);
			ObjectMapper mapper = new ObjectMapper();
			
			List<String> list = mapper.readValue(string,new TypeReference<List<String>>(){});
			
//			while((string=reader.readLine())!=null){
//				System.out.println(string);
//			}
			
//			string = string.replaceAll("\"|\\[|\\]", "");
//			List<String> list = Arrays.asList(string.split(","));
			for(int i=0;i<10;i++){
				System.out.println(list.get(i));
			}
//			System.out.println(list.size());
//			System.out.println(string);
//			Pattern pattern = Pattern.compile("\".*\"");
//			Matcher matcher = pattern.matcher(string);
//			while (matcher.find()) {
//				System.out.println(matcher.group());
//			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GitminingTest().go();
	}

}
