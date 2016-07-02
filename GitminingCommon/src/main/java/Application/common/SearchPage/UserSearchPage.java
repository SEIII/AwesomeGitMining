package Application.common.SearchPage;

import java.util.List;

import Application.common.info.UserInfo;

public class UserSearchPage extends SearchPage<UserInfo>{

	public UserSearchPage(List<UserInfo> list, String key, int totalListSize) {
		super(list, key, totalListSize);
	}

	protected List<UserInfo> invokeServer() {
		return searchService.searchUser(searchKey, currentPageNum);
	}
}
