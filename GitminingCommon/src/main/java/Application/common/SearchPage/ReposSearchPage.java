package Application.common.SearchPage;

import java.util.List;

import Application.common.ReposSort;
import Application.common.info.ReposInfo;

public class ReposSearchPage extends SearchPage<ReposInfo>{

	ReposSort sort;
	public ReposSearchPage(List<ReposInfo> list, String key, int totalListSize,ReposSort sort) {
		super(list, key, totalListSize);
		this.sort = sort;
	}

	@Override
	protected List<ReposInfo> invokeServer() {
		return searchService.searchRepos(searchKey, sort, currentPageNum);
	}

}
