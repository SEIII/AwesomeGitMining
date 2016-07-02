package Application.business_logic.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.business_logic.bl.stat.StatInfoFactory;
import Application.common.ReposSort;
import Application.common.TitleKey;
import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.common.SearchPage.LocalPageJudge;
import Application.common.SearchPage.ReposSearchPage;
import Application.common.SearchPage.SearchPage;
import Application.common.SearchPage.UserSearchPage;
import Application.common.blService.TitleService;
import Application.common.data_service.SearchRepositoryService;
import Application.common.data_service.SearchUserService;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;

@Component
public class TitleServiceImpl implements TitleService {



	@Autowired
	SearchRepositoryService repositoryService;

	@Autowired
	SearchUserService userService;

	@Autowired
	StatInfoFactory factory;


	@Override
	public SearchPage<ReposInfo> getRepoListByKey(TitleKey key) {
		// TODO Auto-generated method stub

		List<BasicRepositoryInfo> basicRepoList = repositoryService.getRepoByTitleKey(key);
		List<ReposInfo> repoList = factory.createReposList(basicRepoList);
		ReposSearchPage page =
					new ReposSearchPage(repoList, LocalPageJudge.sheetSearchKey,repoList.size(),ReposSort.language_num);
		return page;

	}

	@Override
	public SearchPage<UserInfo> getUserListBykey(TitleKey key) {
		// TODO Auto-generated method stub

		List<BasicUserInfo> basicUserList = userService.getUserByTitleKey(key);
		List<UserInfo> userList = factory.createUserList(basicUserList);

		UserSearchPage page =
				new UserSearchPage(userList, LocalPageJudge.sheetSearchKey,userList.size());

		System.out.println(page == null);
		return page;
	}

}
