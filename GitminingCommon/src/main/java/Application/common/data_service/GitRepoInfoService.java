package Application.common.data_service;

import Application.common.DTO.GitRepoTotalInfo;

public interface GitRepoInfoService {

	GitRepoTotalInfo getGitRepoInfo(String repoFullName);
}
