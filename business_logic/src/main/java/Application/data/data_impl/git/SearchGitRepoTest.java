package Application.data.data_impl.git;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.service.*;
import org.springframework.stereotype.Component;

import Application.data.DAO.common.GithubServiceFactory;

@Component
public class SearchGitRepoTest {

	RepositoryService service;
	UserService userService;

	public void test(String query){
		service = GithubServiceFactory.getRepoitoryService();

		
		try {
			List<SearchRepository> repos = service.searchRepositories(query);
			System.out.println(repos.size());
			for(SearchRepository repo: repos){
				System.out.println(repo.getOwner()+":"+repo.getName());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
