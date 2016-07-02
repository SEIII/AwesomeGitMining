package Application.data.data_impl.git;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import Application.AppContextSupport;
import Application.common.DTO.GitRepoTotalInfo;
import Application.common.data_service.GitRepoInfoService;

@Component
public class GitRepoInfoImpl implements GitRepoInfoService{

	@Override
	@Cacheable("getGitRepoInfo")
	public GitRepoTotalInfo getGitRepoInfo(String repoFullName) {
		// TODO Auto-generated method stub

		StatRepoInfoObj statRepoInfoObj = AppContextSupport.getApplicationContext()
                .getBean(StatRepoInfoObj.class);

		statRepoInfoObj.setSelfService(statRepoInfoObj);

		return statRepoInfoObj.getGitRepoInfo(repoFullName);
	}

}
