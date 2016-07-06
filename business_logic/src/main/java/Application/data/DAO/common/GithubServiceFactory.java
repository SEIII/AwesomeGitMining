package Application.data.DAO.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CollaboratorService;
import org.eclipse.egit.github.core.service.EventService;
import org.eclipse.egit.github.core.service.OrganizationService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import Application.AppContextSupport;
import Application.GitMiningServer;
import Application.common.data_service.GitUserTotalInfoService;
import Application.data.DAO.sql.SQLTemplate;
import Application.gitAPIExtends.GitMiningRepoService;
import Application.gitAPIExtends.GitMiningUserService;
import Application.gitAPIExtends.GithubRepoStatsService;
import Application.gitAPIExtends.UserSearchService;

@Component
public class GithubServiceFactory {

    static String[] gitHubTokens;

    static Random random;

    static {
        random = new Random();
    }

    public static GitMiningRepoService getRepoitoryService(GitHubClient client) {
        return new GitMiningRepoService(client);
    }

    public static GitMiningUserService getUserService(GitHubClient client) {
        return new GitMiningUserService(client);
    }

    public static OrganizationService getOrganizationService(
            GitHubClient client) {
        return new OrganizationService(client);
    }

    public static EventService getEventService(GitHubClient client) {
        return new EventService(client);
    }

    public static GithubRepoStatsService getGithubRepoStatsService(
            GitHubClient client) {
        return new GithubRepoStatsService(client);
    }

    public static GitMiningRepoService getGitMiningRepoService() {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(getToken());
        GitMiningRepoService service = new GitMiningRepoService(client);
        return service;
    }

    public static GitMiningUserService getGitMiningUserService() {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(getToken());
        GitMiningUserService service = new GitMiningUserService(client);
        return service;
    }

    public static RepositoryService getRepoitoryService() {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(getToken());
        RepositoryService service = new RepositoryService(client);
        return service;
    }

    public static UserService getUserService() {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(getToken());
        UserService service = new UserService(client);
        return service;
    }

    public static OrganizationService getOrganizationService() {
        OrganizationService service = new OrganizationService();
        service.getClient().setOAuth2Token(getToken());
        return service;
    }

    public static EventService getEventService() {
        EventService service = new EventService();
        service.getClient().setOAuth2Token(getToken());
        return service;
    }

    public static GithubRepoStatsService getGithubRepoStatsService() {
        GithubRepoStatsService service = new GithubRepoStatsService();
        service.getClient().setOAuth2Token(getToken());
        return service;
    }

    public static UserSearchService getGitHubUserSearchService() {
        UserSearchService searchService = new UserSearchService();
        searchService.getClient().setOAuth2Token(getToken());
        return searchService;
    }

    public static CollaboratorService getCollaboratorService() {
        CollaboratorService service = new CollaboratorService();
        service.getClient().setOAuth2Token(getToken());
        return service;
    }

	private static String getToken() {

		if(gitHubTokens == null){



			SQLTemplate template = AppContextSupport.getApplicationContext()
					.getBean(SQLTemplate.class);

			gitHubTokens = template.getGithubTokens();
		}

	    int tokenIndex = random.nextInt(gitHubTokens.length);
	    return gitHubTokens[tokenIndex];
	}

}
