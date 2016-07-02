package Application.common.DTO;

import java.io.Serializable;

import lombok.Data;


public class FollowRepoInfo implements Serializable {/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	String repofullname;

	String description;

	String mainLanguage;

	int star;

	int fork;

	int issue;

	double size;

	String ownerName;

	String repoName;

	public String getRepofullname() {
		return repofullname;
	}

	public void setRepofullname(String repofullname) {
		this.repofullname = repofullname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMainLanguage() {
		return mainLanguage;
	}

	public void setMainLanguage(String mainLanguage) {
		this.mainLanguage = mainLanguage;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getFork() {
		return fork;
	}

	public void setFork(int fork) {
		this.fork = fork;
	}

	public int getIssue() {
		return issue;
	}

	public void setIssue(int issue) {
		this.issue = issue;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getOwnerName() {
		String repoFullName = getRepofullname();
		String[] list = repoFullName.split("/");
		return list[0];
	}




	public String getRepoName() {
		String repoFullName = getRepofullname();
		String[] list = repoFullName.split("/");
		String repoName = list[1];
		if(repoName.length()>13)
			return repoName.substring(0, 13);
		else
			return repoName;
	}



}
