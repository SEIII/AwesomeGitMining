package Application.common.info;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.egit.github.core.IRepositoryIdProvider;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import Application.common.DTO.BasicRepositoryInfo;
import Application.common.SearchPage.SearchPage;
import Application.common.blService.SearchService;
import Application.common.util.StrConverter;

/**
 * @author Shenbin 仓库的逻辑层dto，提供各种信息查询功能
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class ReposInfo implements AbstractInfo,IRepositoryIdProvider {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    BasicRepositoryInfo basicInfo;

    transient SearchService reposService;

    transient SearchPage<UserInfo> contributors;

    transient SearchPage<UserInfo> collaborators;

    public ReposInfo(BasicRepositoryInfo info) {
        basicInfo = info;
    }

    public SearchPage<UserInfo> getContributors() {
        if (contributors == null && reposService != null) {
            contributors = reposService.searchReposContributors(getOwnerName(),
                    getReposName());
        }
        return contributors;
    }

    public SearchPage<UserInfo> getCollaborators() {

        if (collaborators == null && reposService != null) {
            collaborators = reposService
                    .searchReposCollaborators(getOwnerName(), getReposName());
        }
        return collaborators;
    }

    public String getFull_name() {
        return basicInfo.getFull_name();
    }

    public String getDescription() {
        return basicInfo.getDescription();
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getCreated_at() {
        return basicInfo.getCreated_at();
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getUpdated_at() {
        Date date = basicInfo.getUpdated_at();
        if(date == null) {
            return new Date();
        }else {
            return date;
        }
    }

    public String getCreatedDateString() {
        return StrConverter.getString(getCreated_at());
    }

    public String getUpdatedDateString() {
        return StrConverter.getString(getUpdated_at());
    }

    public int getSize() {
        return basicInfo.getSize();
    }

    public int getStargazers_count() {
        return basicInfo.getStargazers_count();
    }

    public int getWatchers_count() {
        return basicInfo.getWatchers_count();
    }

    public String getLanguage() {
        String language = basicInfo.getLanguage();
        if(language==null || language.equals("")) {
            return "unknow";
        }
        return basicInfo.getLanguage();
    }

    public int getForks_count() {
        return basicInfo.getForks_count();
    }



    public int getOpen_issues_count() {
        return basicInfo.getOpen_issues_count();
    }



    public String getOwnerName() {
        return basicInfo.getOwnerName();
    }

    public String getReposName() {
        return basicInfo.getReposName();
    }

    public int getContributorCount() {
        return basicInfo.getContributorCount();
    }

    public int getCollaboratorCount() {
        return basicInfo.getCollaboratorCount();
    }



    public String getHtml_url() {
        return basicInfo.getHtml_url();
    }

    public void setService(Object blService) {
        this.reposService = (SearchService) blService;
    }


    public String getSizeInMB() {
        return StrConverter.getString(getSize() / 1024.0);
    }

    public Double getSizeInMBDouble() {
        return (double)getSize()*1.0 / 1024.0;
    }

    @Override
    public String generateId() {
        return basicInfo.generateId();
    }

    public Map<String, Long> getLanguageMap(){
    	return basicInfo.getLanguageMap();
    }
}
