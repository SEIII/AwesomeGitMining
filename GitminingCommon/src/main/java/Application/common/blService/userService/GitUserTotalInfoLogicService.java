package Application.common.blService.userService;

import java.util.List;

import Application.common.DTO.GitUserTotalInfo;
import Application.common.blService.statService.callback.StatTotalCallback;
import Application.common.info.ReposInfo;

public interface GitUserTotalInfoLogicService {
    GitUserTotalInfo cacheUser(String login,StatTotalCallback callback);
    
    List<ReposInfo> getCreatedRepos(String login);
    List<ReposInfo> getContributedRepos(String login);
    
    boolean isHit(String login);
}
