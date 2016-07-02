package Application.business_logic.bl.userService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.business_logic.bl.stat.StatGitRepoValue;
import Application.common.DTO.BasicRepositoryInfo;
import Application.common.DTO.GitUserTotalInfo;
import Application.common.DTO.UserRelatedRepository;
import Application.common.blService.statService.callback.StatTotalCallback;
import Application.common.blService.userService.GitUserTotalInfoLogicService;
import Application.common.data_service.GitUserTotalInfoService;
import Application.common.info.ReposInfo;

@Component
public class GitUserTotalInfoLogicImpl implements GitUserTotalInfoLogicService{

    @Autowired
    GitUserTotalInfoService gitUserTotalInfoService;
    
    @Autowired
    StatGitRepoValue statGitRepoValue;

    @Override
    public GitUserTotalInfo cacheUser(String login,StatTotalCallback callback) {
        GitUserTotalInfo totalInfo = 
                gitUserTotalInfoService.getGitUserTotalInfo(login, callback);
        statReposValue(totalInfo);
        return totalInfo;
    }
    
    public boolean isHit(String login) {
        return gitUserTotalInfoService.isHit(login);
    }

    @Override
    public List<ReposInfo> getCreatedRepos(String login) {
        GitUserTotalInfo info = gitUserTotalInfoService.getGitUserTotalInfo(login);
        return getReposInfo(info.getCreatedRepos());
    }
    

    @Override
    public List<ReposInfo> getContributedRepos(String login) {
        GitUserTotalInfo info = gitUserTotalInfoService.getGitUserTotalInfo(login);
        return getReposInfo(info.getContributedRepos());
    }
    
    protected void statReposValue(GitUserTotalInfo totalInfo) {
        for(UserRelatedRepository repo:totalInfo.getCreatedRepos()) {
            statGitRepoValue.statSingleRepoValue(repo);
        }
        
        for(UserRelatedRepository repo:totalInfo.getContributedRepos()) {
            statGitRepoValue.statSingleRepoValue(repo);
        }
    }

    protected List<ReposInfo> getReposInfo(List<UserRelatedRepository> repos){
        List<ReposInfo> reposInfos = new ArrayList<>();
        for(UserRelatedRepository userRelatedRepository:repos) {
            BasicRepositoryInfo basicInfo = 
                    new BasicRepositoryInfo(userRelatedRepository.getRepository());
            reposInfos.add(new ReposInfo(basicInfo));
        }
        return reposInfos;
    }

}
