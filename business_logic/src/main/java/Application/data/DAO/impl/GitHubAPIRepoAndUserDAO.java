package Application.data.DAO.impl;

import java.awt.Image;
import java.io.IOException;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;

import Application.common.DAOService.RepoAndUserDAOService;
import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.data.DAO.common.GithubServiceFactory;
import Application.gitAPIExtends.GitMiningRepoService;
import Application.gitAPIExtends.GitMiningUserService;

@Component
public class GitHubAPIRepoAndUserDAO implements RepoAndUserDAOService {


    @Override
    public List<String> getRepoFullNameList() {
        throw new RuntimeException("Cannot do it in github api");
    }

    @Override
    public List<String> getUserList() {
        throw new RuntimeException("Cannot do it in github api");
    }

    @Override
    public int getContributorCount(String username, String reponame) {
        throw new RuntimeException("Cannot do it in github api");
    }

    @Override
    public int getCollaboratorCount(String username, String reponame) {
        throw new RuntimeException("Cannot do it in github api");
    }

    @Override
    public int getContributedRepoNum(String login) {
        throw new RuntimeException("Cannot do it in github api");
    }

    @Override
    public int getCreatedRepoNum(String login) {
        throw new RuntimeException("Cannot do it in github api");
    }

    @Override
    public String getUserMainLanguage(String login) {
        throw new RuntimeException("Cannot do it in github api");
    }

    @Override
    public BasicRepositoryInfo getSingleBasicRepoInfo(String username,
            String reponame) {
        
        GitMiningRepoService repositoryServie 
                    = GithubServiceFactory.getGitMiningRepoService();
        
        try {
            return repositoryServie.getGitMiningBasicRepo(username, reponame);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BasicUserInfo getSingleBasicUserInfo(String login) {
        
        GitMiningUserService userService = 
                GithubServiceFactory.getGitMiningUserService();
        
        try {
            return userService.getGitMiningBasicUser(login);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BasicRepositoryInfo> getRepositoryForkList(String username,
            String reponame) {
        
        GitMiningRepoService repositoryServie 
        = GithubServiceFactory.getGitMiningRepoService();
        
        try {
            return repositoryServie.getGitMiningForkList(username, reponame);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BasicUserInfo> getRepositoryContributorList(String username,
            String reponame) {
        
        GitMiningRepoService repositoryServie 
        = GithubServiceFactory.getGitMiningRepoService();
        
        return repositoryServie.getContributors(username, reponame);
        
    }

    @Override
    public List<BasicUserInfo> getRepositoryCollaboratorList(String username,
            String reponame) {
        
        GitMiningRepoService repositoryServie 
        = GithubServiceFactory.getGitMiningRepoService();
        
        try {
            List<BasicUserInfo> userInfos = repositoryServie.getCollaborators(username, reponame);
            return userInfos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    
    }

    @Override
    public List<BasicRepositoryInfo> getUserCreatedRepoList(String username) {
//         repositoryServie.get
        return null;
    }

    @Override
    public List<BasicRepositoryInfo> getUserContributedRepoList(
            String username) {
        return null;
    }

    @Override
    public List<BasicRepositoryInfo> getSomeBasicRepoInfo(
            List<String> fullNameList) {
        throw new RuntimeException("Cannot do it in github api");
    }

    @Override
    public List<BasicRepositoryInfo> getAllRepoInfo() {
        throw new RuntimeException("Cannot do it in github api");
    }

    @Override
    public List<BasicUserInfo> getAllUserInfo() {
        throw new RuntimeException("Cannot do it in github api");
    }

    @Override
    public Image getUserIcon(String username) {
        return null;
    }

    @Override
    public Map<String, Integer> getAllLanguageLine() {
        return null;
    }

    @Override
    public List<Integer[]> getUserEvent(String login) {
        return new UserEventsGetter().getUserEvent(login);
    }

    @Override
    public List<BasicRepositoryInfo> getRepoListByKey(String key) {
        throw new RuntimeException("Cannot do it in github api");
    }

    @Override
    public List<BasicRepositoryInfo> getCreatedRepoFromGit(String login) {
        return getUserCreatedRepoList(login);
    }

    @Override
    public List<BasicRepositoryInfo> getSearchRepoFromGit(List<String> keys) {
        throw new RuntimeException("Cannot do it in github api");
    }
    
    public List<BasicRepositoryInfo> searchRepos(String keyword){
        
        GitMiningRepoService repositoryServie 
        = GithubServiceFactory.getGitMiningRepoService();
        
        try {
            List<BasicRepositoryInfo> searchRepos = repositoryServie.searchRepos(keyword);
            return searchRepos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    public List<BasicUserInfo> searchUser(String keyword){
        GitMiningUserService userService = 
                GithubServiceFactory.getGitMiningUserService();
        return userService.searchUser(keyword);
    }


}
