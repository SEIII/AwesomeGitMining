package Application.common.info;

public class GitMiningUserInfo {

	int id;
	String account;
	String password;
	String gitid;
	String avatar_url;



	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGitid() {
		return gitid;
	}
	public void setGitid(String gitid) {
		this.gitid = gitid;
	}
	@Override
	public String toString() {
		return "GitUserInfo [id=" + id + ", account=" + account + ", password=" + password + ", gitid=" + gitid + "]";
	}


}
