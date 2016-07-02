package Application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
@EnableCaching
public class GitMiningServer {



    public static void main(String[] args) {



	    setHostIP();


		SpringApplication.run(GitMiningServer.class);
		System.out.println("---------------- Start ----------------");
	}


	private static void setHostIP() {		Properties pro = new Properties();
		FileInputStream in;
		try {


			in = new FileInputStream("config/hostIP.properties");
			pro.load(in);
			in.close();
			String hostIP = pro.getProperty("hostIP");
			System.out.println(hostIP);
			if(!hostIP.equals("")){
				System.setProperty("java.rmi.server.hostname", hostIP);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
