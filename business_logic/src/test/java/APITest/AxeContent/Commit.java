package APITest.AxeContent;

public class Commit {
	
	String sha;
	String url;
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	@Override
	public String toString() {
		return "Commit [sha=" + sha + ", url=" + url + "]";
	}
	
	
	
	
}
