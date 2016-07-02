package Application.common.blService.statService;

import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;

public interface QueryService {

    ReposInfo findReposInfo(String ownerName,String repoName);

    UserInfo findUserInfo(String login);

}
