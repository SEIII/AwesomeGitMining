package APITest.ConnectTest;


import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AnyQuant {
	String prefix = "http://121.41.106.89:8010/";
	String method = "";
	String openCode = "b38c8d358121f83a8029fa83c4c5feff";
	
	public void go(){
		try {
			URL url = new URL("http://121.41.106.89:8010/");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			if (connection.getResponseCode() >= 300) {
//	            throw new Exception("HTTP Request is not success, Response code is " + connection.getResponseCode());
//	        }
			//增加一个header
			connection.setRequestProperty("X-Auth-Code",openCode);
			

			//这里不能写成url.openStream()，因为它是打开一个新连接，之前设置的参数无效
//			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//			String string=reader.readLine();
			
			
			//ObjectMapper 666666
			ObjectMapper mapper = new ObjectMapper();
			InfoObj infoObj = mapper.readValue(connection.getInputStream(), InfoObj.class);
			System.out.println(infoObj.getStatus()+","+infoObj.getData());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new AnyQuant().go();
	}
}
