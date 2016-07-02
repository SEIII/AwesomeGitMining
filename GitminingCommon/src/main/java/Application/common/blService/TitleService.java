package Application.common.blService;

import java.util.List;

import Application.common.TitleKey;
import Application.common.SearchPage.SearchPage;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;

public interface TitleService {

	public SearchPage<ReposInfo> getRepoListByKey(TitleKey key);

	public SearchPage<UserInfo> getUserListBykey(TitleKey key);
}
