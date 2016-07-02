package APITest.AxeContent;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 仓库对象
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class RepositoryInfo_AXE {

	int id;
	String name;
	String full_name;
	
	/*是否fork过其他项目*/
    boolean fork;
    
    String url;
    String created_at;
    String updated_at;
    String pushed_at;
    long size;
    
    /*点赞人数*/
    long stargazers_count;
    
    /*关注人数*/
    long watchers_count;
    
    String language;
    
    /*被fork次数*/
    long forks_count;
    
    long network_count;
    
    /*订阅量*/
    long subscribers_count;
    
    /*其他属性*/
    Map<String, Object> otherProperties =
    		new HashMap<String, Object>();

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public boolean isFork() {
		return fork;
	}

	public void setFork(boolean fork) {
		this.fork = fork;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getPushed_at() {
		return pushed_at;
	}

	public void setPushed_at(String pushed_at) {
		this.pushed_at = pushed_at;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getStargazers_count() {
		return stargazers_count;
	}

	public void setStargazers_count(long stargazers_count) {
		this.stargazers_count = stargazers_count;
	}

	public long getWatchers_count() {
		return watchers_count;
	}

	public void setWatchers_count(long watchers_count) {
		this.watchers_count = watchers_count;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public long getForks_count() {
		return forks_count;
	}

	public void setForks_count(long forks_count) {
		this.forks_count = forks_count;
	}

	public long getNetwork_count() {
		return network_count;
	}

	public void setNetwork_count(long network_count) {
		this.network_count = network_count;
	}

	public long getSubscribers_count() {
		return subscribers_count;
	}

	public void setSubscribers_count(long subscribers_count) {
		this.subscribers_count = subscribers_count;
	}
    
//    @JsonAnyGetter
//    public Map<String, Object> getAny()
//    {
//    	return otherProperties;
//    }
//    
//    @JsonAnySetter
//    public void set(String key, Object value)
//    {
//    	otherProperties.put(key, value);
//    }
    
    
    @Override
	public String toString() {
		return "RepositoryInfo [id=" + id + ", \nname=" + name + ", \nfull_name="
				+ full_name + ", \nfork=" + fork + ", \nurl=" + url
				+ ", \ncreated_at=" + created_at + ", \nupdated_at=" + updated_at
				+ ", \npushed_at=" + pushed_at + ", \nsize=" + size
				+ ", \nstargazers_count=" + stargazers_count
				+ ", \nwatchers_count=" + watchers_count + ", \nlanguage="
				+ language + ", \nforks_count=" + forks_count
				+ ", \nnetwork_count=" + network_count + ", \nsubscribers_count="
				+ subscribers_count + "]";
	}
    
}
