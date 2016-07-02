package Application.business_logic.bl.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Application.business_logic.bl.infoFactory.InfoFactory;
import Application.business_logic.bl.infoFactory.ReposInfoFactory;
import Application.business_logic.bl.infoFactory.UserInfoFactory;
import Application.business_logic.bl.resultPool.ReposResultPool;
import Application.business_logic.bl.resultPool.SearchResultPool;
import Application.business_logic.bl.resultPool.UserResultPool;
import Application.common.ReposSort;
import Application.common.SearchPage.LocalPageJudge;
import Application.common.SearchPage.ReposSearchPage;
import Application.common.SearchPage.SearchPage;
import Application.common.SearchPage.UserSearchPage;
import Application.common.blService.SearchService;
import Application.common.data_service.SearchRepositoryService;
import Application.common.data_service.SearchUserService;
import Application.common.info.AbstractInfo;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;




/**
 * @author 申彬
 *
 */
@Component
public class SearchServiceImpl implements SearchService{

	@Autowired
	SearchRepositoryService reposDataService;

	@Autowired
	SearchUserService userDataService;

	@Autowired
	ReposResultPool reposResultPool;

	@Autowired
	UserResultPool userResultPool;

	@Autowired
	ReposInfoFactory reposFactory;
	@Autowired
	UserInfoFactory userFacotry;

	/**
	 * 因为关联查询没有key，所以给关联查询一个特殊的的key
	 */

	@Override
	public SearchPage<ReposInfo> searchRepos(String key) {
		List<ReposInfo> infos = search(key, reposResultPool, reposFactory, reposDataService);
		int totalSize = infos.size();
		List<ReposInfo> aPageList = makeList(infos, SearchPage.size, 0);
		return configSearchPage(
		        new ReposSearchPage(aPageList, key, totalSize, ReposSort.general));
	}

	@Override
	public SearchPage<ReposInfo> searchRepos(String key, ReposSort sort) {
		//先执行一遍搜索，若缓存区中原来没有结果，则现在有了
		searchRepos(key);
		List<ReposInfo> resultList = reposResultPool.getResult(key, sort);
		int totalSize = resultList.size();
		List<ReposInfo> aPageList = makeList(resultList, SearchPage.size, 0);
		return configSearchPage(
		        new ReposSearchPage(aPageList, key, totalSize, sort));
	}

	@Override
	public SearchPage<UserInfo> searchUser(String key) {
		// TODO Auto-generated method stub
		List<UserInfo> resultList = search(key, userResultPool, userFacotry, userDataService);
		int totalSize = resultList.size();
		List<UserInfo> aPageList = makeList(resultList, SearchPage.size, 0);
		return configSearchPage(
		        new UserSearchPage(aPageList, key, totalSize));
	}

	/**
	 * 这个方法有点难懂，目的是为了抽象出一次搜索操作<br/>
	 * 先看缓存池中是否有这个key，有的话直接做成page返回<br/>
	 * 若没有，则调用dataservice返回list，然后用factory包装成逻辑层dto，加入缓存区，然后返回
	 * @param key
	 * @param resultPool
	 * @param factory
	 * @param dataService
	 * @return
	 */
	protected <T> List<T> search
		(String key,SearchResultPool<T> resultPool,InfoFactory<T> factory,Object dataService){


		List<T> infos = null;

		if (resultPool.containsResult(key)) {
			infos = resultPool.getResult(key);
		} else {

			@SuppressWarnings("rawtypes")
			List basicInfoList = null;

			if(dataService instanceof SearchUserService){
				SearchUserService userService = (SearchUserService) dataService;
				basicInfoList = userService.searchUsers(key);
			}else if(dataService instanceof SearchRepositoryService){
				SearchRepositoryService repositoryService =
						(SearchRepositoryService)dataService;
				basicInfoList = repositoryService.searchRepositoryBasicInfo(key);
			}

			infos = factory.createList(basicInfoList);
			resultPool.addResult(key, infos);
		}
		return infos;

	}

	protected <T> List<T> makeList(List<T> formerList,int pageSize,int pageIndex){
		List<T> resultList = new ArrayList<T>(pageSize);

		int offset = pageIndex*pageSize;
		//&&是为了处理最后一页的情况
		for(int i = 0; i<pageSize&& (i+offset)<formerList.size(); i++){
			resultList.add(formerList.get(i+offset));
		}
		return resultList;
	}


	@Override
	public SearchPage<ReposInfo> searchUserCreateRepos(String login) {
		return relatedSearch(SearchType.UserCreateRepos, reposFactory, login);
	}

	@Override
	public SearchPage<ReposInfo> searchUserContributeRepos(String login) {
		return relatedSearch(SearchType.UserContributeRepos, reposFactory, login);
	}

	@Override
	public SearchPage<UserInfo> searchReposContributors(String owner, String reposName) {
		return relatedSearch(SearchType.ReposContributor, userFacotry, owner,reposName);
	}

	@Override
	public SearchPage<UserInfo> searchReposCollaborators(String owner, String reposName) {
		return relatedSearch(SearchType.ReposColla, userFacotry, owner,reposName);
	}



	/**
	 * 这个方法有点难懂，目的是为了抽象一次相关查找，包括通过人找项目和通过项目找人<br/>
	 * 获得basicInfo的list，通过factory创建逻辑层Info，包装成page返回
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public <T extends AbstractInfo> SearchPage<T> relatedSearch
		(SearchType type,InfoFactory<T> factory,String...args){

		List basicInfoList = null;
		switch(type){
			case UserCreateRepos:
				basicInfoList = userDataService.searchCreatedRepos(args[0]);
				break;
			case UserContributeRepos:
				basicInfoList = userDataService.searchContributedRepos(args[0]);
				break;
			case ReposContributor:
				basicInfoList = reposDataService.searchRepositoryContributorsInfo(args[0], args[1]);
				break;
			case ReposColla:
				basicInfoList = reposDataService.searchRepositoryCollaboratorsInfo(args[0], args[1]);
				break;
		}

		List<T> infoList = factory.createList(basicInfoList);
		//return new SearchPage<T>(infoList,LocalPageJudge.relatedSearchKey,infoList.size());
		SearchPage<T> onePage = new SearchPage<T>(infoList,LocalPageJudge.relatedSearchKey,infoList.size());
		return configSearchPage(onePage);
	}


	enum SearchType{
		UserCreateRepos,UserContributeRepos,ReposContributor,ReposColla
	}


	@Override
	public List<ReposInfo> searchRepos
		(String key, ReposSort sort, int pageIndex) {

		List<ReposInfo> resultList = reposResultPool.getResult(key, sort);
		List<ReposInfo> aPageList = makeList(resultList, SearchPage.size, pageIndex);
		return aPageList;

	}


	@Override
	public List<UserInfo> searchUser(String key, int pageIndex) {
		List<UserInfo> resultList = userResultPool.getResult(key);
		List<UserInfo> aPageList = makeList(resultList, SearchPage.size, pageIndex);
		return aPageList;
	}

	protected <T extends AbstractInfo> SearchPage<T> configSearchPage(SearchPage<T> onePage) {
        onePage.setSearchService(this);
        return onePage;
    }

}
