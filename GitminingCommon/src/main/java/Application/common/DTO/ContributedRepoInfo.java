package Application.common.DTO;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContributedRepoInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	String username;

	List<String> repos;

	int eventCount;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRepos() {
		return repos;
	}

	public void setRepos(List<String> repos) {
		this.repos = repos;
	}

	public int getEventCount() {
		return eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}

	@Override
	public String toString() {
		return "ContributedRepoInfo [username=" + username + ", repos=" + repos + ", eventCount=" + eventCount + "]";
	}



}
