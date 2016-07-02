package Application.common.DTO;

import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;

import Application.gitAPIExtends.githubVO.CodeFrequency;
import Application.gitAPIExtends.githubVO.CommitActivity;
import Application.gitAPIExtends.githubVO.HourCommit;
import Application.gitAPIExtends.githubVO.Participation;

/**
 * @author lenovo
 *
 */
public class GitRepoTotalInfo {

	Repository repository;

	List<CodeFrequency> codeFrequency;

	List<CommitActivity> commitActivity;

	List<HourCommit> hourCommit;

	int contributorCount;

	int collaboratorCount;

	Participation participation;


	public List<CodeFrequency> getCodeFrequency() {
		return codeFrequency;
	}

	public void setCodeFrequency(List<CodeFrequency> codeFrequency) {
		this.codeFrequency = codeFrequency;
	}

	public List<CommitActivity> getCommitActivity() {
		return commitActivity;
	}

	public void setCommitActivity(List<CommitActivity> commitActivity) {
		this.commitActivity = commitActivity;
	}

	public List<HourCommit> getHourCommit() {
		return hourCommit;
	}

	public void setHourCommit(List<HourCommit> hourCommit) {
		this.hourCommit = hourCommit;
	}



	public void setParticipation(Participation participation) {
		this.participation = participation;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public int getContributorCount() {
		return contributorCount;
	}

	public void setContributorCount(int contributorCount) {
		this.contributorCount = contributorCount;
	}

	public int getCollaboratorCount() {
		return collaboratorCount;
	}

	public void setCollaboratorCount(int collaboratorCount) {
		this.collaboratorCount = collaboratorCount;
	}




}
