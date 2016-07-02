package Application.data.data_impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.common.TitleKey;
import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.common.data_service.SearchRepositoryService;
import Application.common.fuzzyQuery.FuzzyQuery;
import Application.common.fuzzyQuery.SearchResult;
import Application.data.DAO.impl.GitHubAPIRepoAndUserDAO;



@Component
public class SearchRepositoryImpl implements SearchRepositoryService {


	@Autowired
	GitHubAPIRepoAndUserDAO dao;

	@Autowired
	StatDataFactory statDataFactory;

	@Override
	public List<BasicRepositoryInfo> searchRepositoryBasicInfo(String key) {

//		List<String> fullNameList = dao.getRepoFullNameList();
//		ArrayList<SearchResult> resultList = FuzzyQuery.fuzzyQuery((ArrayList<String>)fullNameList, key);
//		List<String> sortedFullName = new ArrayList<String>();
//		for(SearchResult result : resultList){
//			int position = result.getFormerIndex();
//			sortedFullName.add(fullNameList.get(position));
//		}
//
//		List<BasicRepositoryInfo> resultRepoInfoList = dao.getSomeBasicRepoInfo(sortedFullName);
//
//		return resultRepoInfoList;
	    return dao.searchRepos(key);
	}



	@Override
	public List<BasicUserInfo> searchRepositoryContributorsInfo(
			String username, String reponame) {
		return dao.getRepositoryContributorList(username, reponame);
	}

	@Override
	public List<BasicUserInfo> searchRepositoryCollaboratorsInfo(
			String username, String reponame) {
		return dao.getRepositoryCollaboratorList(username, reponame);
	}



	@Override
	public List<BasicRepositoryInfo> getAllRepoInfo() {
		return dao.getAllRepoInfo();
	}



	@Override
	public Map<String, Integer> getLanguageLine() {
		return dao.getAllLanguageLine();
	}



	@Override
	public List<BasicRepositoryInfo> getRepoByTitleKey(TitleKey key) {

//		switch(key){
//
//		case languages: return file_dao.getMostLanguageRepos();
//		case stars: return file_dao.getMostPopularRepos();
//
//
//		}
//
//		return new ArrayList<BasicRepositoryInfo>();
	    return null;
	
	}



	@Override
	public List<BasicRepositoryInfo> getRepoByKeyPhrase(String key) {
		return dao.getRepoListByKey(key);
	}



	@Override
	public List<BasicRepositoryInfo> getSearchRepoFromGit(List<String> keys) {
		return dao.getSearchRepoFromGit(keys);
	}



	@Override
	public Map<String, Integer> getLanagugeWithByte() {
		File f = new File("statData/language-byte.txt");
		Map<String, Integer> map = new TreeMap<String, Integer>();

		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));

			while((line = br.readLine()) != null){
				String[] tempList = line.split(":");
				String key = tempList[0];
				Integer value = Integer.parseInt(tempList[1]);
				map.put(key, value);

			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}



	@Override
	public Map<Integer, Integer> getStarWithRepo() {
		return statDataFactory.getStatData("repo-star");
	}



	@Override
	public Map<Integer, Integer> getForkWithRepo() {
		return statDataFactory.getStatData("repo-fork");
	}



	@Override
	public Map<Integer, Integer> getContributorWithRepo() {
		return statDataFactory.getStatData("repo-contributors");
	}



	@Override
	public Map<Integer, Integer> getYearWithRepo() {
		return statDataFactory.getStatData("repo-year");
	}



	@Override
	public Map<Integer, Integer> getStarWithFork() {
		return statDataFactory.getStatData("star-fork");
	}



	@Override
	public Map<Integer, Integer> getStarWithContributor() {
		return statDataFactory.getStatData("star-contribution");
	}

}
