package Application.data.data_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.common.DAOService.RepoAndUserDAOService;
import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.BasicUserInfo;
import Application.common.data_service.DataQueryService;

@Component
public class DataQueryImpl implements DataQueryService{

    @Autowired
    RepoAndUserDAOService dao;

    @Override
    public BasicRepositoryInfo findReposInfo(String ownerName,
            String repoName) {
        return dao.getSingleBasicRepoInfo(ownerName, repoName);
    }

    @Override
    public BasicUserInfo findUserInfo(String login) {
        return dao.getSingleBasicUserInfo(login);
    }

}
