package Application.common.praser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.User;

import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.gitAPIExtends.githubVO.SearchUser;

public class GitMiningInfoPraser {

    public List<BasicUserInfo> getBasicUserListByUser(List<User> users) {
        ArrayList<BasicUserInfo> basicUserInfos = new ArrayList<>(users.size());
        for (User user : users) {
            basicUserInfos.add(new BasicUserInfo(user));
        }
        return basicUserInfos;
    }
    
    public List<BasicRepositoryInfo> getBasicRepoListByRepo(List<Repository> repos){
        ArrayList<BasicRepositoryInfo> basicRepositoryInfos = new ArrayList<>(repos.size());
        for(Repository repository:repos) {
            basicRepositoryInfos.add(new BasicRepositoryInfo(repository));
        }
        return basicRepositoryInfos;
    }

    public List<BasicUserInfo> getBasicUserListBySearch(
            List<SearchUser> searchUsers) {
        ArrayList<BasicUserInfo> basicUserInfos = new ArrayList<>(
                searchUsers.size());
        for (SearchUser searchUser : searchUsers) {
            basicUserInfos.add(new BasicUserInfo(searchUser));
        }
        return basicUserInfos;
    }

    public List<BasicRepositoryInfo> getBasicRepoListBySearch(
            List<SearchRepository> searchRepos) {
        ArrayList<BasicRepositoryInfo> basicRepositoryInfos = new ArrayList<>(
                searchRepos.size());
        for (SearchRepository searchRepo : searchRepos) {
            basicRepositoryInfos.add(new BasicRepositoryInfo(searchRepo));
        }
        return basicRepositoryInfos;
    }
}
