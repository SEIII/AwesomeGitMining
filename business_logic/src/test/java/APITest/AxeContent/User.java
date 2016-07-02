package APITest.AxeContent;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class User {

	String login;
	long id;
	String avatar_url;
	String repos_url;
	String events_url;
	String type;
	String name;
	String location;
	String email;
	int public_repos;
	long followers;
	long following;
	String created_at;
	String updated_at;
	
    Map<String, Object> otherProperties =
    		new HashMap<String, Object>();

	
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getRepos_url() {
		return repos_url;
	}

	public void setRepos_url(String repos_url) {
		this.repos_url = repos_url;
	}

	public String getEvents_url() {
		return events_url;
	}

	public void setEvents_url(String events_url) {
		this.events_url = events_url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPublic_repos() {
		return public_repos;
	}

	public void setPublic_repos(int public_repos) {
		this.public_repos = public_repos;
	}

	public long getFollowers() {
		return followers;
	}

	public void setFollowers(long followers) {
		this.followers = followers;
	}

	public long getFollowing() {
		return following;
	}

	public void setFollowing(long following) {
		this.following = following;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	@JsonAnyGetter
	public Map<String, Object> getAny()
	{
	   return otherProperties;
	}
	    
	@JsonAnySetter
	public void set(String key, Object value)
	{
	   otherProperties.put(key, value);
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", \nid=" + id + ", \navatar_url="
				+ avatar_url + ", \nrepos_url=" + repos_url + ", \nevents_url="
				+ events_url + ", \ntype=" + type + ", \nname=" + name
				+ ", \nlocation=" + location + ", \nemail=" + email
				+ ", \npublic_repos=" + public_repos + ", \nfollowers=" + followers
				+ ", \nfollowing=" + following + ", \ncreated_at=" + created_at
				+ ", \nupdated_at=" + updated_at + "]";
	}
	
	
	
    
    
}
