package Application.common.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.SearchRepository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 仓库基本信息
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicRepositoryInfo implements Serializable,IRepositoryIdProvider{

	/**
	 *
	 */






	private static final long serialVersionUID = 1L;

	/*项目全称*/
	private String full_name;

	private String ownerName;

	private String reposName;

	/*项目描述*/
	private String description;

	private String html_url;

	/*创建日期*/
	private Date created_at;

	/*最后更新日期*/
	private Date updated_at;

	/*项目大小*/
	int size;

	/*点赞数量*/
	int stargazers_count;

	/*关注数量*/
	int watchers_count;

	/*使用语言*/
	String language;

	/*fork次数*/
	int forks_count;

	/*open_issue次数*/
	int open_issues_count;

	/*订阅数量*/
	int subscribers_count;

	/*contributor数量*/
	int contributor_count;

	/*collaborators数量*/
	int collaborators_count;

	Map<String, Long> languageMap;

	double value;


	public BasicRepositoryInfo() {}

	public BasicRepositoryInfo(SearchRepository searchRepository) {

	    reposName = searchRepository.getName();
	    ownerName = searchRepository.getOwner();

	    full_name = ownerName+"/"+reposName;

	    description = searchRepository.getDescription();
	    html_url = searchRepository.getUrl();
	    language = searchRepository.getLanguage();
	    size = searchRepository.getSize();
	    open_issues_count = searchRepository.getOpenIssues();
	    watchers_count = searchRepository.getWatchers();
	    forks_count = searchRepository.getForks();
	    stargazers_count = searchRepository.getWatchers();
	    created_at = searchRepository.getCreatedAt();

	}

	public BasicRepositoryInfo(Repository repository) {
	    ownerName = repository.getOwner().getLogin();
	    reposName = repository.getName();
	    full_name = ownerName+"/"+reposName;
	    description = repository.getDescription();
	    html_url = repository.getHtmlUrl();
	    language = repository.getLanguage();
	    created_at = repository.getCreatedAt();
	    updated_at = repository.getUpdatedAt();

	    size = repository.getSize();
	    stargazers_count = repository.getWatchers();
	    forks_count = repository.getForks();
	    open_issues_count = repository.getOpenIssues();

	}

	public String getFull_name() {

	    if(full_name==null||full_name.equals("")) {
	        full_name = ownerName+"/"+reposName;
	    }

		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
		String[] splits = full_name.split("/");
		ownerName = splits[0];
		reposName = splits[1];
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStargazers_count() {
		return stargazers_count;
	}

	public void setStargazers_count(int stargazers_count) {
		this.stargazers_count = stargazers_count;
	}

	public int getWatchers_count() {
		return watchers_count;
	}

	public void setWatchers_count(int watchers_count) {
		this.watchers_count = watchers_count;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
	    if(language!=null && !language.equals("")) {
	        this.language = language;
	    }else {
	        language = "unknow";
	    }

	}

	public int getForks_count() {
		return forks_count;
	}

	public void setForks_count(int forks_count) {
		this.forks_count = forks_count;
	}

	public int getOpen_issues_count() {
		return open_issues_count;
	}

	public void setOpen_issues_count(int open_issues_count) {
		this.open_issues_count = open_issues_count;
	}

	public int getSubscribers_count() {
		return subscribers_count;
	}

	public void setSubscribers_count(int subscribers_count) {
		this.subscribers_count = subscribers_count;
	}



	@Override
	public String toString() {
		return "BasicRepositoryInfo [ownerName=" + ownerName + ", reposName="
				+ reposName + ", description=" + description + ", created_at="
				+ created_at + ", updated_at=" + updated_at + ", size=" + size
				+ ", stargazers_count=" + stargazers_count
				+ ", watchers_count=" + watchers_count + ", language="
				+ language + ", forks_count=" + forks_count
				+ ", open_issues_count=" + open_issues_count
				+ ", subscribers_count=" + subscribers_count
				+ ", contributor_count=" + contributor_count + "]";
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getReposName() {
		return reposName;
	}

	public void setReposName(String reposName) {
		this.reposName = reposName;
	}


	public int getCollaboratorCount(){
		return this.collaborators_count;
	}


	public void setCollaboratorCount(int collaborator_count){
		this.collaborators_count = collaborator_count;
	}



	public int getContributorCount()
	{
		return this.contributor_count;
	}


	public void setContributorCount(int contributor_count){
		this.contributor_count = contributor_count;
	}


	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}


    @Override
    public String generateId() {
        return full_name;
    }

	public Map<String, Long> getLanguageMap() {
		return languageMap;
	}

	public void setLanguageMap(Map<String, Long> languageMap) {
		this.languageMap = languageMap;
	}


}
