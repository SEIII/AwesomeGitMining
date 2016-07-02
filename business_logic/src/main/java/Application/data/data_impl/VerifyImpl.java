package Application.data.data_impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.common.RegisterState;
import Application.common.data_service.VerifyService;
import Application.common.info.GitMiningUserInfo;
import Application.data.DAO.common.GithubServiceFactory;
import Application.data.DAO.sql.SQLTemplate;

@Component
public class VerifyImpl implements VerifyService{


	@Autowired
	SQLTemplate sqlTemplate;

	@Override
	public GitMiningUserInfo verifyLogin(String account, String password) {
		// TODO Auto-generated method stub
		GitMiningUserInfo info = sqlTemplate.getUserPassword(account);
		if(info != null){
			if(info.getPassword().equals(password)){
				return info;
			}
		}
		return null;
	}

	@Override
	public RegisterState verifyRegister(String account, String gitID, String password) {
		// TODO Auto-generated method stub
		List<String> fisherLogin = sqlTemplate.getAllFisherLogin();
		for(String login : fisherLogin){
			if(login.equals(account))
				return RegisterState.ACCOUNT;
		}

		 UserService userService = GithubServiceFactory.getUserService();
		 User userInfo = null;
		try {
			userInfo = userService.getUser(gitID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


			if(userInfo == null){
				return RegisterState.GITID;
			}

			sqlTemplate.insertOneFisher(account, password, gitID,userInfo);
			System.out.println("after register");
			return RegisterState.GOOD;



	}


//	private String execURL(String urlstring)
//	{
//		String string = "";
//		try {
//
//			URL url = new URL(urlstring);
//            BufferedReader reader = new BufferedReader(
//            		new InputStreamReader(url.openStream()));
//			string = reader.readLine();
//
//		} catch (IOException e) {
//
//		}
//
//		return string;
//	}

}
