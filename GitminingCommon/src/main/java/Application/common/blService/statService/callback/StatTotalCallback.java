package Application.common.blService.statService.callback;

import Application.common.ReposType;

public interface StatTotalCallback {

    void start();
    
    void setCreatedNum(int num);
    
    void setContributedNum(int num);
    
    void completeCreatedBasicInfo(int num);
    
    void getOneRepoBasicInfo(String repoName);
    
    void getOneRepoContributionInfo(String repoName);
    
    void done();
    
}
