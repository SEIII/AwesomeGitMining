package Application.business_logic.bl.stat.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.business_logic.bl.stat.StatGitRepoValue;
import Application.business_logic.bl.stat.StatInfoFactory;
import Application.common.DTO.GitUserTotalInfo;
import Application.common.DTO.UserRelatedRepository;
import Application.common.blService.statService.StatResult;
import Application.common.blService.statService.StatSingleUserService;
import Application.common.data_service.GitUserTotalInfoService;
import Application.common.data_service.SearchUserService;
import Application.common.info.UserInfo;

@Component
public class StatSingleUserImpl implements StatSingleUserService{



    @Autowired
    StatInfoFactory statInfoFactory;

    @Autowired
    SearchUserService searchUserService;

    @Autowired
    GitUserTotalInfoService statUserTotalInfoService;

    @Autowired
    StatGitRepoValue statGitRepoValue;

    List<UserInfo> userList;
    List<List<Integer>> userStatInfoList;
    List<String> userStatTypeList;

    static int followerExp = 29;
    static double languageExp = 3.3;


    @Override
    public List<Integer[]> statSingleUserWeekEvent(String login) {
        return searchUserService.getUserEvent(login);
    }

    @Override
    public StatResult<String, Double> statUserRadar(String login){
    	StatResult<String, Double> result = new StatResult<String, Double>();

    	GitUserTotalInfo userInfo = statUserTotalInfoService.getGitUserTotalInfo(login);
    	List<UserRelatedRepository> createdRepos = userInfo.getCreatedRepos();
    	List<UserRelatedRepository> contributedRepos = userInfo.getContributedRepos();
    	List<Event> events = userInfo.getLastestEvent();
    	User user = userInfo.getUser();

    	for(UserRelatedRepository relatedRepo: createdRepos)
    		statGitRepoValue.statSingleRepoValue(relatedRepo, 100, 300, 500, 300);

    	for(UserRelatedRepository relatedRepo: contributedRepos)
    		statGitRepoValue.statSingleRepoValue(relatedRepo, 100, 300, 500, 300);

    	List<UserRelatedRepository> allRepos = new ArrayList<UserRelatedRepository>();
    	allRepos.addAll(createdRepos);
    	allRepos.addAll(contributedRepos);

    	double creativity = statUserRepoValue(createdRepos);
    	double geek = statUserRepoValue(contributedRepos);
    	double famous = statUserFamous(user.getFollowers());
    	double variety = statUserVariety(contributedRepos);
    	double core = statUserCore(allRepos);
    	double activity = statUserActivity(events);



    	result.put("creativity", creativity*10);
    	result.put("geek", geek*10);
    	result.put("famous", famous*10);
    	result.put("variety", variety*10);
    	result.put("core", core*10);
    	result.put("activity", activity*10);

    	return result;
    }


	@Override
	public StatResult<String, Double> statUserProductive(String login) {
		// TODO Auto-generated method stub

		StatResult<String, Double> resultStat = new StatResult<>();
		StatResult<String, Double> disOrderedResult = new StatResult<>();

		GitUserTotalInfo userInfo = statUserTotalInfoService.getGitUserTotalInfo(login);

		List<UserRelatedRepository> createdRepos = userInfo.getCreatedRepos();
    	List<UserRelatedRepository> contributedRepos = userInfo.getContributedRepos();


    	List<UserRelatedRepository> allRepos = new ArrayList<UserRelatedRepository>();
    	allRepos.addAll(createdRepos);
    	allRepos.addAll(contributedRepos);

    	for(UserRelatedRepository repo: allRepos){

    		Repository repoInfo = repo.getRepository();
    		String ownerName = repoInfo.getOwner().getLogin();

    		int repoContributorCount = repo.getTotalContributorNum();
    		int repoTotalCommitCount = repo.getTotalCommitCount();
    		int userContributeCount = repo.getUserContributeCount();

    		if(repoTotalCommitCount == 0)
    			repoTotalCommitCount = 1;

    		double rate = (double)(userContributeCount* repoContributorCount)/(double)repoTotalCommitCount;

    		resultStat.put(ownerName+"/"+repoInfo.getName(), rate);
    	}

    	resultStat.sortByValue();



    	Map<String, Double> tempMap = resultStat.getMap();

    	if(tempMap.size()>15){
    		int index = 0;
    		for(Map.Entry<String, Double> entry: tempMap.entrySet()){

    			if(index>15)
    				break;

        		disOrderedResult.put(entry.getKey(), entry.getValue());
        		index++;
        	}
    		disOrderedResult.sortByKey();
    		return disOrderedResult;
    	}
    	return resultStat;
	}

	@Override
	public StatResult<String, Integer> statUserContributionPie(String login) {
		// TODO Auto-generated method stub
		StatResult<String, Integer> resultStat = new StatResult<>();
		StatResult<String, Integer> disOrderedResult = new StatResult<>();

		GitUserTotalInfo userInfo = statUserTotalInfoService.getGitUserTotalInfo(login);
    	List<UserRelatedRepository> createdRepos = userInfo.getCreatedRepos();
    	List<UserRelatedRepository> contributedRepos = userInfo.getContributedRepos();


    	List<UserRelatedRepository> allRepos = new ArrayList<UserRelatedRepository>();
    	allRepos.addAll(createdRepos);
    	allRepos.addAll(contributedRepos);

    	for(UserRelatedRepository repo: allRepos){
    		Repository repoInfo = repo.getRepository();
    		String ownerName = repoInfo.getOwner().getLogin();

    		resultStat.put(ownerName+"/"+repoInfo.getName(), repo.getUserContributeCount());
    	}

//    	resultStat.sortByValue();
    	Map<String, Integer> tempMap = resultStat.getMap();

    	if(tempMap.size()>15){
    		int index = 0;
    		for(Map.Entry<String, Integer> entry: tempMap.entrySet()){

    			if(index>15)
    				break;

        		disOrderedResult.put(entry.getKey(), entry.getValue());
        		index++;
        	}
//    		disOrderedResult.sortByKey();
    		return disOrderedResult;
    	}

    	return resultStat;
	}

	@Override
	public StatResult<String, Long> statUserCode(String login) {
		// TODO Auto-generated method stub

		StatResult<String, Long> resultStat = new StatResult<>();

		GitUserTotalInfo userInfo = statUserTotalInfoService.getGitUserTotalInfo(login);
    	List<UserRelatedRepository> createdRepos = userInfo.getCreatedRepos();
    	List<UserRelatedRepository> contributedRepos = userInfo.getContributedRepos();


    	List<UserRelatedRepository> allRepos = new ArrayList<UserRelatedRepository>();
    	allRepos.addAll(createdRepos);
    	allRepos.addAll(contributedRepos);

    	Map<String, Long> resultLanguageMap = new TreeMap<String, Long>();

    	for(UserRelatedRepository repo: allRepos){

    		double contributeRate = repo.getUserContribution();

    		if(contributeRate == 0)
    			continue;


    		Map<String, Long> languageMap =repo.getLanguageList();

    		for(Map.Entry<String, Long> entry: languageMap.entrySet()){
    			String key = entry.getKey();
    			Long value = entry.getValue();
    			if(resultLanguageMap.containsKey(key)){
    				double temp =resultLanguageMap.get(key) + contributeRate*value;
    				resultLanguageMap.put(key, (long) temp);
    			}else{
    				resultLanguageMap.put(key, value);
    			}
    		}

    	}

    	for(Map.Entry<String, Long> entry: resultLanguageMap.entrySet()){
    		if(entry.getValue() != 0)
    			resultStat.put(entry.getKey(), entry.getValue());
    	}

//    	resultStat.sortByValue();
    	StatResult<String, Long> disOrderedResult = new StatResult<>();
    	Map<String, Long> tempMap = resultStat.getMap();

    	if(tempMap.size()>10){
    		int index = 0;
    		for(Map.Entry<String, Long> entry: tempMap.entrySet()){

    			if(index>9)
    				break;

        		disOrderedResult.put(entry.getKey(), entry.getValue());
        		index++;
        	}
//    		disOrderedResult.sortByKey();
    		return disOrderedResult;
    	}


		return resultStat;
	}

	@Override
	public StatResult<String, Double> statUserValue(String login) {
		// TODO Auto-generated method stub

		StatResult<String, Double> resultStat = new StatResult<>();
		StatResult<String, Double> disOrderedResult = new StatResult<String, Double>();

		GitUserTotalInfo userInfo = statUserTotalInfoService.getGitUserTotalInfo(login);

		List<UserRelatedRepository> createdRepos = userInfo.getCreatedRepos();
    	List<UserRelatedRepository> contributedRepos = userInfo.getContributedRepos();


    	List<UserRelatedRepository> allRepos = new ArrayList<UserRelatedRepository>();
    	allRepos.addAll(createdRepos);
    	allRepos.addAll(contributedRepos);


    	for(UserRelatedRepository repo: allRepos){
    		statGitRepoValue.statSingleRepoValue(repo, 100, 300, 500, 300);
    	}

    	for(UserRelatedRepository repo: allRepos){

    		Repository repoInfo = repo.getRepository();
    		String ownerName = repoInfo.getOwner().getLogin();

    		double contributionRate = repo.getUserContribution();
    		double userValue = repo.getValue()*contributionRate;


    		if(userValue != 0)
    			resultStat.put(ownerName+"/"+repoInfo.getName(), userValue);
    	}


    	Map<String, Double> tempMap = resultStat.getMap();

    	if(tempMap.size()>15){
    		int index = 0;
    		for(Map.Entry<String, Double> entry: tempMap.entrySet()){

    			if(index>14)
    				break;

        		disOrderedResult.put(entry.getKey(), entry.getValue());
        		index++;
        	}
    		disOrderedResult.sortByKey();
    		return disOrderedResult;
    	}


		return resultStat;
	}

	@Override
	public StatResult<String, Double> statUserCodeValue(String login) {
		// TODO Auto-generated method stub


		StatResult<String, Double> resultStat = new StatResult<>();

		GitUserTotalInfo userInfo = statUserTotalInfoService.getGitUserTotalInfo(login);
    	List<UserRelatedRepository> createdRepos = userInfo.getCreatedRepos();
    	List<UserRelatedRepository> contributedRepos = userInfo.getContributedRepos();


    	List<UserRelatedRepository> allRepos = new ArrayList<UserRelatedRepository>();
    	allRepos.addAll(createdRepos);
    	allRepos.addAll(contributedRepos);

    	for(UserRelatedRepository repo: allRepos)
    		statGitRepoValue.statSingleRepoValue(repo, 100, 300, 500, 300);

    	Map<String, Double> resultLanguageMap = new TreeMap<String, Double>();

    	for(UserRelatedRepository repo: allRepos){

    		double contributeRate = repo.getUserContribution();
    		
    		
    		Map<String, Long> languageMap =repo.getLanguageList();
    		BigDecimal totalByte = new BigDecimal(repo.getTotalSizeInByte());
    		Double repoValue = repo.getValue();

    		if(contributeRate == 0){
    			continue;
    		}

    		for(Map.Entry<String, Long> entry: languageMap.entrySet()){
    			String key = entry.getKey();
    			Long value = entry.getValue();
    			BigDecimal oneSize = new BigDecimal(value);
    			
    			double languageRate = oneSize.divide(totalByte,20, RoundingMode.HALF_UP)
    			        .doubleValue();

    			double languageValue =contributeRate*languageRate*repoValue;

    			if(resultLanguageMap.containsKey(key)){
    				double temp =resultLanguageMap.get(key) + languageValue;
    				resultLanguageMap.put(key,temp);
    			}else{
    				resultLanguageMap.put(key, languageValue);
    			}
    		}

    	}
    	for(Map.Entry<String, Double> entry: resultLanguageMap.entrySet()){
    		if(entry.getValue() != 0)
    			resultStat.put(entry.getKey(), entry.getValue());
    	}

    	resultStat.sortByValue();
		return resultStat;
	}

	private double statUserActivity(List<Event> events){

		if(events!=null && events.size()!=0){
			Date first = events.get(0).getCreatedAt();
	    	long current = System.currentTimeMillis();

	    	int days = (int) (current-first.getTime())/(1000*24*3600);
	    	if(days==0||days==1)
	    		return 1;
	    	else{
	    		return 0.3 + (double)1/(double)days;
	    	}
		}

    	return 0;
    }

    private double statUserVariety(List<UserRelatedRepository> repos){


    	if(repos!=null && repos.size()!=0){
    		int max =0;
        	for(UserRelatedRepository relatedRepo: repos){
        		int repoLanguageNum = relatedRepo.getLanguageList().size();
        		if(repoLanguageNum>max)
        			max = repoLanguageNum;
        	}
        	double rate = (double)max/languageExp;
        	rate = (rate)/(1+rate);
        	return rate;
    	}

    	return 0;
    }


    private double statUserCore(List<UserRelatedRepository> repos){

    	if(repos != null && repos.size() != 0){
    		double result = 0;
        	for(UserRelatedRepository repo: repos){
        		result += repo.getUserContribution();
        	}
        	result /= repos.size();

        	result = (result)/(1+result);
        	return result;
    	}

    	return 0;
    }

    private double statUserFamous(int followerCount){
    	double rate = ((double)followerCount/(double)followerExp) ;
    	rate = (rate)/(1+rate);
    	return rate;
    }

    private double statUserRepoValue(List<UserRelatedRepository> repos){

    	if(repos != null && repos.size()!=0){
    		double result = 0;
        	double max = 0;

        	for(UserRelatedRepository repo: repos){
        		double rate = repo.getRate();
        		result += rate;

        		if(rate>max)
        			max = rate;
        	}

        	return (result>=1)?max:result;
    	}

    	return 0;
    }

}
