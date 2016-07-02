package Application.business_logic.bl.infoFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.common.DTO.BasicUserInfo;
import Application.common.data_service.SearchUserService;
import Application.common.info.UserInfo;

@Component
public class UserInfoFactory extends InfoFactory<UserInfo>{

	@Autowired
	SearchUserService userService;
	
	@Override
	public UserInfo createOne(Object obj) {
		// TODO Auto-generated method stub
		BasicUserInfo userInfo = (BasicUserInfo)obj;
		return new UserInfo(userInfo);
	}

}
