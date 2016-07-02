package Application.common.blService.verifyService;

import Application.common.RegisterState;
import Application.common.info.GitMiningUserInfo;

public interface LoginService {

	public GitMiningUserInfo verifyLogin(String account, String password);

	public RegisterState verifyRegister(String account, String gitID, String password);

}
