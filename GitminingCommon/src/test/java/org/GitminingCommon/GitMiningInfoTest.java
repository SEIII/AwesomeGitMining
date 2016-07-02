package org.GitminingCommon;

import java.io.IOException;

import org.eclipse.egit.github.core.client.GitHubClient;

import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.gitAPIExtends.GitMiningRepoService;
import Application.gitAPIExtends.GitMiningUserService;

public class GitMiningInfoTest {

    public static void main(String[] args) {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token("f6d2b6464f71e59be799e24082a0dd350696dacd");
        GitMiningRepoService service = new GitMiningRepoService(client);
        
        
        try {
            BasicRepositoryInfo info = service.getGitMiningBasicRepo("rubinius", "rubinius");
            System.out.println(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        GitMiningUserService userService = new GitMiningUserService(client);
        
        try {
            BasicUserInfo info = userService.getGitMiningBasicUser("rubinius");
            System.out.println(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
        
}
