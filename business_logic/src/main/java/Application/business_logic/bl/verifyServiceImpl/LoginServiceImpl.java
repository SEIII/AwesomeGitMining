package Application.business_logic.bl.verifyServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.common.RegisterState;
import Application.common.blService.verifyService.LoginService;
import Application.common.data_service.VerifyService;
import Application.common.info.GitMiningUserInfo;

@Component
public class LoginServiceImpl implements LoginService{



	@Autowired
	VerifyService verifyService;

	@Override
	public GitMiningUserInfo verifyLogin(String account, String password) {
		// TODO Auto-generated method stub
		return verifyService.verifyLogin(account, password);
	}

	@Override
	public RegisterState verifyRegister(String account, String gitID, String password) {
		// TODO Auto-generated method stub
		return verifyService.verifyRegister(account, gitID, password);
	}

}
