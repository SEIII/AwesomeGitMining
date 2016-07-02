package Application.common.DTO;

import java.io.Serializable;
import java.util.Date;

import org.eclipse.egit.github.core.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import Application.common.UserType;
import Application.gitAPIExtends.githubVO.SearchUser;

/**
 * 用户类型
 * 可细分为User和Organization
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicUserInfo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/*登录名*/
	String login;

	/*头像url*/
	String avatar_url;

	/*贡献url*/
	String received_events_url;


	/*用户类型*/
	UserType type;

	/*用户名称*/
	String name;

	/*位置*/
	String location;

	/*email*/
	String email;

	/*个人简介*/
	String bio;

	/*创建仓库数*/
	int public_repos;

	/*粉丝*/
	int followers;

	/*关注*/
	int following;

	/*贡献仓库数*/
	int contributed_repo_num;

	/*创建仓库数*/
	int created_repo_num;

	/*用户用的语言*/
	String mainLanguage;

	/*用户创建日期*/
	Date created_at;

	/*创建日期*/
	Date updated_at;

	public BasicUserInfo() {}

	public BasicUserInfo(SearchUser searchUser) {
	    login = searchUser.getLogin();
	    name = searchUser.getName();
	    location = searchUser.getLocation();
	    mainLanguage = searchUser.getLanguage();
	    avatar_url = searchUser.getAvatar_url();


	    public_repos = searchUser.getRepos();
	    type = UserType.valueOf(searchUser.getType());
	    followers = searchUser.getFollowers();
	    created_repo_num = searchUser.getRepos();
	    created_at = searchUser.getCreated_at();

	}

	public BasicUserInfo(User user) {
	    login = user.getLogin();
	    name = user.getName();
	    location = user.getLocation();
	    email = user.getEmail();
	    avatar_url = user.getAvatarUrl();

	    public_repos = user.getPublicRepos();
	    type = UserType.valueOf(user.getType());
	    followers = user.getFollowers();
	    following = user.getFollowing();
	    created_repo_num = user.getPublicRepos();
	    created_at = user.getCreatedAt();

	}




	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public String getName() {
		return (name==null)?"unknown":name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return (location==null)?"unknown":location;
	}
	public void setLocation(String location) {
	    if(location!=null && !location.equals("")) {
            this.location = location;
        }
	    else {
	        this.location = "unknown location";
	    }

	}



	public String getEmail() {
		return (email==null)?"unknown":email;
	}
	public void setEmail(String email) {
	    if(email!=null && !email.equals("")) {
            this.email = email;
        }
        else {
            this.email = "unknow email";
        }
	}
	public String getBio() {
	    return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public int getPublic_repos() {
		return public_repos;
	}
	public void setPublic_repos(int public_repos) {
		this.public_repos = public_repos;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int follwers) {
		this.followers = follwers;
	}
	public int getFollowing() {
		return following;
	}
	public void setFollowing(int following) {
		this.following = following;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
//	    if(updated_at == null) {
//	        throw new NullPointerException("user:"+login+" updata_at is null,check it");
//	    }
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public int  getContributedRepoNumber(){
		return contributed_repo_num;
	}
	public String getMainLanguage(){
		return this.mainLanguage;
	}
	public void setMainLanguage(String language){
		this.mainLanguage = language;
	}
	public void setContributedRepoNumber(int number){
		this.contributed_repo_num = number;
	}
	public void setCreatedRepoNumber(int number){
		this.created_repo_num = number;
	}
	public int  getCreatedRepoNumber(){
		return public_repos;
	}
	public String getReceived_events_url() {
		return received_events_url;
	}
	public void setReceived_events_url(String received_events_url) {
		this.received_events_url = received_events_url;
	}
	@Override
	public String toString() {
		return "User [login=" + login + ", avatar_url=" + avatar_url
				+ ", type=" + type + ", name=" + name + ", location="
				+ location + ", email=" + email + ", bio=" + bio
				+ ", public_repos=" + public_repos + ", followers=" + followers
				+ ", following=" + following + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + "]";
	}




}
