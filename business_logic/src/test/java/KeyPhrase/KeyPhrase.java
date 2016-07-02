package KeyPhrase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import Application.business_logic.BlPackageInfo;
import Application.common.DTO.BasicRepositoryInfo;
import Application.data.DataPackageInfo;
import Application.data.DAO.sql.SQLTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DataPackageInfo.class, BlPackageInfo.class})
public class KeyPhrase {

	HttpClient httpClient = HttpClients.createDefault();

	String text = "";
	HttpPost request;

	public KeyPhrase(String text) throws Exception {
		// TODO Auto-generated constructor stub

		URIBuilder builder = new URIBuilder(
				"https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/keyPhrases");

		URI uri = builder.build();
		request = new HttpPost(uri);
		request.setHeader("Ocp-Apim-Subscription-Key", "903d248c470e43968138afd96d129f3d");
		request.setHeader("Content-Type", "application/json");
		this.text = text;


	}


	public String getJSON(){

		StringEntity reqEntity = null;
		try {
			reqEntity = new StringEntity(text);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		request.setEntity(reqEntity);
		String answer = "";

		try {
			HttpResponse response = httpClient.execute(request);
			HttpEntity http_entity = response.getEntity();

			if (http_entity != null) {
				answer = EntityUtils.toString(http_entity);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return answer;

	}



	public static void main(String[] args){
		File forkFile = new File("localRepos/keyphraseTest.txt");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(forkFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.println("??");
		writer.flush();
	}
}
