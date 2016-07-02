package Application.business_logic.bl.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.business_logic.bl.infoFactory.ReposInfoFactory;
import Application.business_logic.bl.infoFactory.UserInfoFactory;
import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.common.info.AbstractInfo;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;

@Component
public class StatInfoFactory {

	@Autowired
	ConfigServiceImpl configInfoService;

	@Autowired
	ReposInfoFactory reposInfoFactory;

	@Autowired
	UserInfoFactory userInfoFactory;

	public List<ReposInfo> createReposList(List<BasicRepositoryInfo> basicInfoList){
		List<ReposInfo> infoList = reposInfoFactory.createList(basicInfoList);
		configInfoService.configInfo(infoList);
		return infoList;
	}

	public List<UserInfo> createUserList(List<BasicUserInfo> basicInfoList){
		List<UserInfo> infoList = userInfoFactory.createList(basicInfoList);
		configInfoService.configInfo(infoList);
		return infoList;
	}

	public <T extends AbstractInfo> T createSingleRepos(T info){
		configInfoService.configInfo(info);
		return info;
	}

}
