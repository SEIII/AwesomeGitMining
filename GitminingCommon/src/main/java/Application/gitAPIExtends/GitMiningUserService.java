package Application.gitAPIExtends;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;

import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.common.praser.GitMiningInfoPraser;
import Application.gitAPIExtends.githubVO.SearchUser;

public class GitMiningUserService {


    UserService userService;
    RepositoryService repositoryService;
    GitMiningInfoPraser praser;
    UserSearchService userSearchService;

    public GitMiningUserService(GitHubClient client) {
        userService = new UserService(client);
        repositoryService = new RepositoryService(client);
        praser = new GitMiningInfoPraser();
        userSearchService = new UserSearchService(client);
    }

    public BasicUserInfo getGitMiningBasicUser(String login)
            throws IOException {
        return new BasicUserInfo(userService.getUser(login));
    }


    public List<BasicUserInfo> searchUser(String keyword){
        List<SearchUser> searchUsers = userSearchService.searchUser(keyword);
        return praser.getBasicUserListBySearch(searchUsers);
    }

}
