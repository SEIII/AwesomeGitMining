package Application.common.blService.statService;

import java.util.List;

import Application.gitAPIExtends.githubVO.CodeFrequency;

public interface StatSingleRepoService {
    /**
     * @param reposInfo
     * @return
     * 统计单个仓库的雷达图
     */

    StatResult<String, Double> statSingleGitRepos(String repoFullName);

    List<CodeFrequency> getRepoCodeFrequency(String repoFullName);
}
