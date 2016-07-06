package Application.business_logic.bl.stat.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import Application.business_logic.bl.stat.StatGitRepoValue;
import Application.common.DTO.GitRepoTotalInfo;
import Application.common.DTO.GitUserTotalInfo;
import Application.common.DTO.UserRelatedRepository;
import Application.common.blService.statService.StatResult;
import Application.common.blService.statService.StatSingleRepoService;
import Application.common.data_service.GitRepoInfoService;
import Application.data.DAO.common.GithubServiceFactory;
import Application.gitAPIExtends.GithubRepoStatsService;
import Application.gitAPIExtends.githubVO.CodeFrequency;
import Application.gitAPIExtends.githubVO.Contribution;

@Component
public class StatSingleRepoImpl implements StatSingleRepoService{

	@Autowired
	GitRepoInfoService gitRepoInfoService;

	@Autowired
	StatGitRepoValue statGitRepoValue;

	@Autowired
	CacheManager cacheManager;


	static double starExp = 250;
	static double forkExp = 45;
	static double issueExp = 15;
	static double sizeExp = 12132;

	@Override
	public StatResult<String, Double> statSingleGitRepos(String repoFullName) {
		// TODO Auto-generated method stub

		StatResult<String, Double> resultStat = new StatResult<>();
		GitRepoTotalInfo info = gitRepoInfoService.getGitRepoInfo(repoFullName);

		Repository repoInfo = info.getRepository();
		int starCount = repoInfo.getWatchers();
		int forkCount = repoInfo.getForks();
		int issueCount = repoInfo.getOpenIssues();
		Date updated_at = repoInfo.getUpdatedAt();
		Date created_at = repoInfo.getCreatedAt();

		UserRelatedRepository userRelatedRepo = new UserRelatedRepository(repoInfo);
		statGitRepoValue.statSingleRepoValue(userRelatedRepo, 100, 300, 500, 300);

		double value = userRelatedRepo.getValue();
		double rate = userRelatedRepo.getRate();

		double starRate = magicFunction(starCount, starExp);
		double forkRate = magicFunction(forkCount, forkExp);
		double issueRate = magicFunction(issueCount, issueExp);
		double dateRate = statUpdatedRate(updated_at);
		double createRate = statCreatedRate(created_at);
		double valueRate = rate;


		resultStat.put("star", starRate*10);
		resultStat.put("fork", forkRate*10);
		resultStat.put("issue", issueRate*10);
		resultStat.put("create", createRate*10);
		resultStat.put("update", dateRate*10);
		resultStat.put("value", valueRate*10);
		resultStat.put("valueCount", value);

		return resultStat;
	}

	private double magicFunction(int count, double exp){
		int value = count;
		double expectation = exp;

		double rate = (double)value / (double) expectation;
		rate = (rate)/(1+rate);

		return rate;

	}

	private double statUpdatedRate(Date date){
		int days = caculateIntervalTime(date);
		if(days<=2)
			return 1;
		else{
			return 0.3 + (double)2/(double)days;
		}
	}

	private double statCreatedRate(Date date){
		int days = caculateIntervalTime(date);
		if(days<365)
			return 0.5;
		else{
			int year = days / 365 ;
			return magicFunction(year+1, 1);
		}

	}

	private int caculateIntervalTime(Date date){
		long past = date.getTime();
		long current = System.currentTimeMillis();

		long interval = (current - past);
		int days = (int) ((double)interval/(1000*3600*24));

		return days;
	}

	@Override
	public List<CodeFrequency> getRepoCodeFrequency(String repoFullName) {
		// TODO Auto-generated method stub

		GitRepoTotalInfo info = gitRepoInfoService.getGitRepoInfo(repoFullName);

		return info.getCodeFrequency();
	}

	@Override
	@Cacheable(value = "getRepoContributors", key="#repoFullName")
	public List<Contribution> getRepoContributors(String repoFullName) {
		// TODO Auto-generated method stub

		String owner = repoFullName.split("/")[0];
		String repoName = repoFullName.split("/")[1];

		GithubRepoStatsService statService = GithubServiceFactory.getGithubRepoStatsService();
		List<Contribution> allContributors = statService.getContributions(owner, repoName);


		if(allContributors == null)
			return new ArrayList<>();

		int size = allContributors.size();
		if(size<=10)
			return allContributors;


		List<Contribution> particalContributors = new ArrayList<Contribution>();
		for(int index=0; index<10; index++){
			particalContributors.add(allContributors.get(size-index-1));
		}

		return particalContributors;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getRepoIsHit(String repoFullName) {
		// TODO Auto-generated method stub
		Cache cache = cacheManager.getCache("getRepoContributors");

        if(cache==null) {
            return false;
        }

        if(cache.get(repoFullName) == null)
        	return false;

        List<Contribution> contributions = (List<Contribution>) cache.get(repoFullName).get();
        if(contributions==null) {
            return false;
        }

        return true;
	}

}
