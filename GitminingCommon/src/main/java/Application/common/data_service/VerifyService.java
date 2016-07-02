package Application.common.data_service;

import Application.common.RegisterState;
import Application.common.info.GitMiningUserInfo;

public interface VerifyService {

	public GitMiningUserInfo verifyLogin(String account, String password);

	public RegisterState verifyRegister(String account, String gitID, String password);

}
