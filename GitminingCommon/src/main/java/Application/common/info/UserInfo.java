package Application.common.info;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import Application.common.UserType;
import Application.common.DTO.BasicUserInfo;
import Application.common.SearchPage.SearchPage;
import Application.common.blService.SearchService;
import Application.common.util.StrConverter;


/**
 * @author 申彬
 * user的逻辑层dto
 */
@JsonInclude(value=Include.NON_NULL)
public class UserInfo implements AbstractInfo{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	BasicUserInfo basicInfo;
	transient SearchService userService;

	transient SearchPage<ReposInfo> createdReposList;
	transient SearchPage<ReposInfo> contributedReposList;

	public UserInfo(BasicUserInfo basicInfo){
		this.basicInfo = basicInfo;
	}

	public SearchPage<ReposInfo> getCreatedReposList() {

		if(createdReposList == null && userService!=null){
			createdReposList = userService.searchUserCreateRepos(getLogin());
		}

		return createdReposList;
	}

	public SearchPage<ReposInfo> getContributedReposList() {

		if(contributedReposList == null && userService!=null){
			contributedReposList = userService.searchUserContributeRepos(getLogin());
		}
		return contributedReposList;
	}


	public String getLogin() {
		return basicInfo.getLogin();
	}


	public String getAvatar_url() {
		return basicInfo.getAvatar_url();
	}


	public UserType getType() {
		return basicInfo.getType();
	}

	public String getName() {
		return basicInfo.getName();
	}

	public String getLocation() {
		return basicInfo.getLocation();
	}



	public String getEmail() {
		String email = basicInfo.getEmail();
		return (email==null)?"Unknown":email;
	}

	public String getBio() {
		return basicInfo.getBio();
	}

	public int getPublic_repos() {
		return basicInfo.getPublic_repos();
	}

	public int getFollowers() {
		return basicInfo.getFollowers();
	}

	public int getFollowing() {
		return basicInfo.getFollowing();
	}

    @JsonFormat(pattern="yyyy-MM-dd")
	public Date getCreated_at() {
		return basicInfo.getCreated_at();
	}


    @JsonFormat(pattern="yyyy-MM-dd")
	public Date getUpdated_at() {
		return basicInfo.getUpdated_at();
	}

    public String getCreatedDateString() {
        return StrConverter.getString(basicInfo.getCreated_at());
    }


    public String getUpdatedDateString() {
        Date date = basicInfo.getUpdated_at();
        if(date==null) {
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        	return df.format(new Date());
        }
        return StrConverter.getString(basicInfo.getUpdated_at());
    }

	public int getContributedRepoNum(){
		return basicInfo.getContributedRepoNumber();
	}

	public int getCreatedRepoNum(){
		return basicInfo.getCreatedRepoNumber();
	}

	public String getMainLanguage(){
		String mainLanguage = basicInfo.getMainLanguage();
		if(mainLanguage == null)
			return "Unknown";
		else{
			return mainLanguage;
		}
	}
	public String getReceived_events_url() {
		return basicInfo.getReceived_events_url();
	}


	@Override
	public void setService(Object blService) {
		this.userService = (SearchService) blService;
	}


}
