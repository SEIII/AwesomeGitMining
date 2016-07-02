package Application.data.data_impl;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
import Application.common.data_service.SearchUserService;
import Application.common.fuzzyQuery.FuzzyQuery;
import Application.common.fuzzyQuery.SearchResult;
import Application.data.DAO.impl.GitHubAPIRepoAndUserDAO;


@Component
public class SearchUserImpl implements SearchUserService{


	@Autowired
	GitHubAPIRepoAndUserDAO dao;

	@Autowired
	StatDataFactory statDataFactory;

	@Override
	public List<BasicUserInfo> searchUsers(String key) {
		// TODO Auto-generated method stub

	    return dao.searchUser(key);

	}

	@Override
	public List<BasicRepositoryInfo> searchCreatedRepos(String username) {
		// TODO Auto-generated method stub
		return dao.getUserCreatedRepoList(username);
	}

	@Override
	public List<BasicRepositoryInfo> searchContributedRepos(String username) {
		// TODO Auto-generated method stub
		return dao.getUserContributedRepoList(username);
	}



	@Override
	public Image getUserIcon(String username) {
		// TODO Auto-generated method stub
		return dao.getUserIcon(username);
	}

	@Override
	public List<BasicUserInfo> getAllUserInfo() {
		// TODO Auto-generated method stub
		return dao.getAllUserInfo();
	}

	@Override
	public List<Integer[]> getUserEvent(String login) {
		// TODO Auto-generated method stub
		return dao.getUserEvent(login);
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public List<BasicUserInfo> getUserByTitleKey(TitleKey key) {
		// TODO Auto-generated method stub

//		switch(key){
//
//		case contributed: return file_dao.getMostContributedUsers();
//		}
//
//		return new ArrayList<BasicUserInfo>();
	    return null;
	}

	@Override
	public List<BasicRepositoryInfo> getCreatedRepoFromGit(String login) {
		// TODO Auto-generated method stub
		return dao.getCreatedRepoFromGit(login);
	}

	@Override
	public Map<Integer, Integer> getCreateWithUser() {
		// TODO Auto-generated method stub

		return statDataFactory.getStatData("user-create");
	}

	@Override
	public Map<Integer, Integer> getYearWithUser() {
		// TODO Auto-generated method stub
		return statDataFactory.getStatData("user-year");
	}

}
