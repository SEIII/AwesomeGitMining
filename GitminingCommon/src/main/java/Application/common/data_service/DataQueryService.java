package Application.common.data_service;

import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;

public interface DataQueryService {
    BasicRepositoryInfo findReposInfo(String ownerName,String repoName);

    BasicUserInfo findUserInfo(String login);

}
