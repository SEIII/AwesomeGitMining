package Application.business_logic.bl.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.common.blService.statService.QueryService;
import Application.common.data_service.DataQueryService;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;

@Component
public class QueryImpl implements QueryService{

    @Autowired
    DataQueryService dataQueryService;

    @Cacheable("repoInfo")
    public ReposInfo findReposInfo(String owner,String repoName) {
        BasicRepositoryInfo basic = dataQueryService.findReposInfo(owner, repoName);
        return new ReposInfo(basic);
    }

    @Cacheable("userInfo")
    public UserInfo findUserInfo(String login) {
        BasicUserInfo basic = dataQueryService.findUserInfo(login);
        return new UserInfo(basic);
    }
}
