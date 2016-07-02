package Application.data.data_impl.git;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.CollaboratorService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import Application.common.DTO.GitRepoTotalInfo;
import Application.data.DAO.common.GithubServiceFactory;
import Application.gitAPIExtends.GithubRepoStatsService;
import Application.gitAPIExtends.githubVO.CodeFrequency;
import Application.gitAPIExtends.githubVO.CommitActivity;
import Application.gitAPIExtends.githubVO.HourCommit;
import Application.gitAPIExtends.githubVO.Participation;
import lombok.Setter;

@Component
@Scope("prototype")
public class StatRepoInfoObj {


	@Setter StatRepoInfoObj selfService;

	public GitRepoTotalInfo getGitRepoInfo(String repoFullName) {
		// TODO Auto-generated method stub

		GitRepoTotalInfo repoTotalInfo = new GitRepoTotalInfo();

		String[] tempList = repoFullName.split("/");
		String owner = tempList[0];
		String repoName = tempList[1];

		Future<List<CodeFrequency>> codeFrequencyF = selfService.getCodeFrequency(owner, repoName);
//		Future<List<CommitActivity>> commitActivityF = selfService.getCommitActivity(owner, repoName);
//		Future<Participation> participationF = selfService.getParticipation(owner, repoName);
//		Future<List<HourCommit>> hourCommitF = selfService.getHourCommit(owner, repoName);
		Future<Repository> repoF = selfService.getRepoInfo(owner, repoName);
//		Future<Integer> contributeF = selfService.getRepoContributorCount(owner, repoName);
//		Future<Integer> collaF = selfService.getRepoCollaboratorCount(owner, repoName);


		while(!(codeFrequencyF.isDone() && repoF.isDone()
				)){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			List<CodeFrequency> codeFrequency = codeFrequencyF.get();
//			List<CommitActivity> commitActivity = commitActivityF.get();
//			Participation participation = participationF.get();
			Repository repoInfo = repoF.get();
//			List<HourCommit> hourCommit = hourCommitF.get();
//			int contribute = contributeF.get();
//			int colla = collaF.get();


			repoTotalInfo.setCodeFrequency(codeFrequency);
//			repoTotalInfo.setCommitActivity(commitActivity);
//			repoTotalInfo.setHourCommit(hourCommit);
//			repoTotalInfo.setParticipation(participation);
			repoTotalInfo.setRepository(repoInfo);
//			repoTotalInfo.setCollaboratorCount(colla);
//			repoTotalInfo.setContributorCount(contribute);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repoTotalInfo;
	}



	@Async
	public Future<Repository> getRepoInfo(String owner, String repoName){

		System.out.println("start get repo info");

		RepositoryService service = GithubServiceFactory.getRepoitoryService();
		Repository repoInfo = new Repository();

		try {
			repoInfo = service.getRepository(owner, repoName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new AsyncResult<Repository>(null);
		}

		return new AsyncResult<Repository>(repoInfo);
	}

	@Async
	public Future<Integer> getRepoContributorCount(String owner, String repoName){


		System.out.println("start get contributor count");
		long start_time = System.currentTimeMillis();

		int contributor_count = 0;
		RepositoryService repoService = GithubServiceFactory.getRepoitoryService();
		try {
			contributor_count= repoService.getContributors(new StarRepositoryIDProvider(owner, repoName), false).size();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new AsyncResult<Integer>(0);
		}
		System.out.println("contributor: "+contributor_count);
		long end_time = System.currentTimeMillis();
		System.out.println("get contributors takes "+ (end_time-start_time) +" ms");

		return new AsyncResult<Integer>(contributor_count);
	}

	@Async
	public Future<Integer> getRepoCollaboratorCount(String owner, String repoName){

		System.out.println("start get collaborator count");

		int collaborator_count = 0;
		CollaboratorService collaService = GithubServiceFactory.getCollaboratorService();

		try {
			collaborator_count= collaService.getCollaborators(new StarRepositoryIDProvider(owner, repoName)).size();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new AsyncResult<Integer>(0);
		}
		System.out.println("colla: "+collaborator_count);
		return new AsyncResult<Integer>(collaborator_count);
	}

	@Async
	public Future<List<CodeFrequency>> getCodeFrequency(String owner, String repoName){

		System.out.println("start get code frequency");

		List<CodeFrequency> codeFrequency = new ArrayList<CodeFrequency>();
		GithubRepoStatsService statService = GithubServiceFactory.getGithubRepoStatsService();

		try {
			codeFrequency = statService.getCodeFrequency(owner, repoName);
		} catch (Exception e) {
			// TODO: handle exception
			return new AsyncResult<List<CodeFrequency>>(null);
		}

		return new AsyncResult<List<CodeFrequency>>(codeFrequency);
	}

	@Async
	public Future<List<CommitActivity>> getCommitActivity(String owner, String repoName){

		System.out.println("start get commit activity");

		List<CommitActivity> commitActivity = new ArrayList<CommitActivity>();
		GithubRepoStatsService statService = GithubServiceFactory.getGithubRepoStatsService();

		try {
			commitActivity = statService.getCommitActivity(owner, repoName);
		} catch (Exception e) {
			// TODO: handle exception
			return new AsyncResult<List<CommitActivity>>(null);
		}

		return new AsyncResult<List<CommitActivity>>(commitActivity);
	}

	@Async
	public Future<Participation> getParticipation(String owner, String repoName){

		System.out.println("start get participation");

		Participation participation = new Participation();
		GithubRepoStatsService statService = GithubServiceFactory.getGithubRepoStatsService();

		try {
			participation = statService.getParticipation(owner, repoName);
		} catch (Exception e) {
			// TODO: handle exception
			return new AsyncResult<Participation>(null);
		}

		return new AsyncResult<Participation>(participation);
	}

	@Async
	public Future<List<HourCommit>> getHourCommit(String owner, String repoName){

		System.out.println("start get hour commit");

		List<HourCommit> hourCommit = new ArrayList<HourCommit>();
		GithubRepoStatsService statService = GithubServiceFactory.getGithubRepoStatsService();

		try {
			hourCommit = statService.getHourCommit(owner, repoName);
		} catch (Exception e) {
			// TODO: handle exception
			return new AsyncResult<List<HourCommit>>(null);
		}

		return new AsyncResult<List<HourCommit>>(hourCommit);
	}
}
