package org.GitminingCommon;

import java.util.List;

import org.eclipse.egit.github.core.client.GitHubClient;

import Application.gitAPIExtends.UserSearchService;
import Application.gitAPIExtends.githubVO.SearchUser;


public class GithubApiTest {
    
    public static void main(String[] args) {
        
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token("f6d2b6464f71e59be799e24082a0dd350696dacd");
        
        UserSearchService userSearchService = new UserSearchService(client);
        long beginTime = System.currentTimeMillis();
        List<SearchUser> users = userSearchService.searchUser("rubin");
        System.out.println(users.get(1));
        long endTime = System.currentTimeMillis();
        System.out.println("delta time: " + (endTime - beginTime));
        
    }
}
