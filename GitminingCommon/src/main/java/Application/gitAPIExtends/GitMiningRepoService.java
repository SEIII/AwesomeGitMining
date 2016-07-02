package Application.gitAPIExtends;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CollaboratorService;
import org.eclipse.egit.github.core.service.RepositoryService;

import Application.common.UserType;
import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.common.praser.GitMiningInfoPraser;
import Application.common.praser.SimpleRepoProvider;

/**
 * 返回自家po的repoService
 *
 */
public class GitMiningRepoService{

    GitMiningInfoPraser praser;
    CollaboratorService collaService;
    RepositoryService repositoryService;

    public GitMiningRepoService(GitHubClient client) {
        repositoryService = new RepositoryService(client);
        praser = new GitMiningInfoPraser();
        collaService = new CollaboratorService(client);
    }

    public BasicRepositoryInfo getGitMiningBasicRepo(String owner,String name)
            throws IOException {

        Repository repository = repositoryService.getRepository(owner, name);
        Map<String, Long> languageMap = repositoryService.getLanguages(repository);
        BasicRepositoryInfo info = new BasicRepositoryInfo(repository);
        Map<String, Long> resultMap = new TreeMap<String, Long>();

        if(languageMap.size()>6){
        	int index = 0;
        	for(Map.Entry<String, Long> entry: languageMap.entrySet()){
        		index++;
        		if(index>6)
        			break;


        		resultMap.put(entry.getKey(), entry.getValue());
        		info.setLanguageMap(resultMap);

        	}
        }else{
        	info.setLanguageMap(languageMap);
        }


        return info;

    }

    public List<BasicRepositoryInfo> getGitMiningForkList(String owner,String name) throws IOException{

        List<Repository> repositories = repositoryService.getForks(new SimpleRepoProvider(owner, name));
        return praser.getBasicRepoListByRepo(repositories);

    }

    public List<BasicUserInfo> getContributors(String username,String reponame){
        SimpleRepoProvider provider = new SimpleRepoProvider(username, reponame);
        try {
            List<Contributor> contributors = repositoryService.getContributors(provider, false);

            List<BasicUserInfo> basicUserInfos = new ArrayList<>(contributors.size());
            for(Contributor contributor:contributors) {
                BasicUserInfo userInfo = new BasicUserInfo();
                userInfo.setLogin(contributor.getLogin());
                userInfo.setName(contributor.getName());
                userInfo.setType(UserType.valueOf(contributor.getType()));
                basicUserInfos.add(userInfo);
            }

            return basicUserInfos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<BasicUserInfo> getCollaborators(String owner,String name) throws IOException{

        List<User> users = collaService.getCollaborators(new SimpleRepoProvider(owner, name));
        return praser.getBasicUserListByUser(users);
    }

    public List<BasicRepositoryInfo> searchRepos(String query) throws IOException{
        List<SearchRepository> searchRepositories = repositoryService.searchRepositories(query);
        return praser.getBasicRepoListBySearch(searchRepositories);
    }



}
