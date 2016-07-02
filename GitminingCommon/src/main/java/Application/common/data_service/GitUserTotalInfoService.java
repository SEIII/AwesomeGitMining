package Application.common.data_service;


import Application.common.DTO.GitUserTotalInfo;
import Application.common.blService.statService.callback.StatTotalCallback;

/**
 * 统计一个git user的总体信息,供逻辑层统计.
 *
 * @author admin
 *
 */
public interface GitUserTotalInfoService {

    GitUserTotalInfo getGitUserTotalInfo(String login,StatTotalCallback callback);

    GitUserTotalInfo getGitUserTotalInfo(String login);
    
    boolean isHit(String login);

}
